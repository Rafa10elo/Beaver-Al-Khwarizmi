package repository;

import Model.Avl;
import Model.Heap;
import Model.Product;
import Model.Shipment;

import java.util.ArrayList;
import java.util.Optional;

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
    public boolean promoteToVip(int id,int days){
        Shipment shipment;
        Shipment shipment1;
        shipment=shipmentAvl.searchHelper(id);
        if (shipment.isPriority()){
            shipment1=new Shipment(days, shipment.getDestination(), shipment.getPrice());
            shipment1.setShipmentId(id);
            delete(id);
            insert(shipment1);
        }
            return false;
    }

    public boolean demoteFromVip(int id){
        Shipment shipment,shipment1;
        shipment=shipmentAvl.searchHelper(id);
        if(shipment.isPriority()){
            delete(id);
            //the minus 15 ,cus the vip shipment has an increased price
            // (it's now a normal shipment so there is no need to the raise)
            shipment1=new Shipment(shipment.getDestination(),shipment.getPrice()-15);
            shipment1.setShipmentId(id);
            insert(shipment1);
            return true;
        }
            return false;

    }

    // I wanted to name it updateTheDate,but i have to be professional
    public boolean rescheduleShipment(int id, int days){
        Shipment shipment,shipment1;
        shipment=shipmentAvl.searchHelper(id);
        if(shipment.isPriority()){
            delete(id);
            shipment1=new Shipment(days,shipment.getDestination(),shipment.getPrice());
            shipment1.setShipmentId(id);
            insert(shipment1);
            return true;
        }
            return false;

    }

    public Shipment theMostPriorityShipment(){
        return shipments.peek();
    }

    public Shipment mostExpensiveShipment(){
        ArrayList<Shipment> shipment=getList();
        double max=shipment.get(0).getPrice();
        int j=0;
        for(int i=1;i<shipment.size();i++){
            if(max<shipment.get(i).getPrice()) {
                max=shipment.get(i).getPrice();
                j=i;
            }
        }
        return shipment.get(j);
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

}
