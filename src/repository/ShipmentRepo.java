package repository;

import Model.Avl;
import Model.Heap;
import Model.Shipment;
import java.util.ArrayList;

public class ShipmentRepo {
    private Heap shipments;
    private Avl<Shipment> shipmentAvl;

    public ShipmentRepo(Heap shipments, Avl<Shipment> shipmentAvl) {
        this.shipments = shipments;
        this.shipmentAvl = shipmentAvl;
    }

    //smart insert, like really smart one, it adds the shipments to the avl and the heap
    //and also can guess if it's a vip one or no 🤯🤯
    public void insert(Shipment shipment){
        if (shipment.isPriority())
            shipments.insertVIP(shipment);
        else
            shipments.insert(shipment);
        shipmentAvl.insertHelper(shipment);
    }

    //delete from everywhere 😡😡
    public void delete(int id){
        shipmentAvl.deleteHelper(id);
        shipments.deleteHelper(id);
    }

    //this is specific to made you a noble (non vip shipment to a vip shipment)
    public boolean promoteToVip(Shipment shipment,int days){
        Shipment shipment1;
        if (!shipment.isPriority()){
            shipment1=new Shipment(shipment.getShipmentId(),days, shipment.getDestination(), shipment.getPrice());
            delete(shipment.getShipmentId());
            insert(shipment1);
        }
            return false;
    }

    public void demoteFromVip(Shipment shipment){
        Shipment shipment1;
            //the minus 15 ,cus the vip shipment has an increased price
            // (it's now a normal shipment so there is no need to the raise)
            shipment1=new Shipment(shipment.getDestination(),shipment.getPrice()-15,shipment.getShipmentId());
            delete(shipment.getShipmentId());
            insert(shipment1);

    }

    // I wanted to name it updateTheDate,but i have to be professional
    public void rescheduleShipment(Shipment shipment, int days){
        Shipment shipment1;
        if(days>=0){
            delete(shipment.getShipmentId());
            shipment1=new Shipment(shipment.getShipmentId(),days,shipment.getDestination(),shipment.getPrice());
            insert(shipment1);
            }

    }

    public Shipment theMostPriorityShipment(){
        return shipments.peek();
    }

    public ArrayList<Shipment> mostExpensiveShipment(){
        ArrayList<Shipment> shipment=getList();
        ArrayList<Shipment> shipmentArrayList=new ArrayList<>();
        double avg=allCosts()/shipment.size();
        int j=0;
        for(int i=1;i<shipment.size();i++){
            if(avg<shipment.get(i).getPrice()) {
                shipmentArrayList.add(shipment.get(i));
            }
        }
        return shipmentArrayList;
    }

    public Shipment searchShipment(int id){
        return shipmentAvl.searchHelper(id);
    }

    public Double allCosts(){
        ArrayList<Shipment> shipment=getList();
        double sum=0;
        for(int i=0;i<shipment.size();i++){
            sum+=shipment.get(i).getPrice();
        }
        return sum;
    }

    public ArrayList<Shipment> getList(){
        ArrayList <Shipment> James;
        James=shipments.Array();
        return James;

    }

    public void expiredShipments(){
        shipments.removeExpiredShipments();
    }

    public void print (){
        shipments.printHeap();
    }

}
