import Control.ProductController;
import Model.Avl;
import Model.Heap;
import Model.Product;
import Model.Shipment;
import repository.ProductRepo;
import repository.ShipmentRepo;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Avl<Shipment> shipmentAvl=new Avl<>(Shipment::getShipmentId);
        Heap shipmentHeap= new Heap();
        ShipmentRepo shipmentRepo=new ShipmentRepo(shipmentHeap,shipmentAvl);
        Shipment shipment=new Shipment("london",20.0);
        Shipment shipment1=new Shipment("damascus",30.0);
        Shipment shipment2=new Shipment("tokyo",15.0);
        Shipment shipment3=new Shipment("my home",50.0);
        Shipment shipment4=new Shipment("your home",7.0);
        Shipment shipment5=new Shipment(3,"guess",55.0);
//        Shipment shipment6=new Shipment(-1,"your home",20.0);
        Shipment shipment7=new Shipment(3,"your home",20.0);
        shipmentRepo.insert(shipment);
        shipmentRepo.insert(shipment1);
        shipmentRepo.insert(shipment2);
        shipmentRepo.insert(shipment3);
        shipmentRepo.insert(shipment4);
        shipmentRepo.insert(shipment5);
//        shipmentRepo.insert(shipment6);
        shipmentRepo.insert(shipment7);
        shipmentHeap.printHeap();



    }
}