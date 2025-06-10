package Model;

import java.time.LocalDate;
import java.util.Comparator;

public class Shipment implements Comparable<Shipment>{
    int shipmentId;
    private LocalDate deliveryDate;
    String destination;
    double price;


    // Constructor
    public Shipment(int shipmentId, LocalDate deliveryDate) {
        this.shipmentId = shipmentId;
        this.deliveryDate = deliveryDate;
    }
    @Override
    public int compareTo(Shipment o) {
        return Integer.compare(o.shipmentId,this.shipmentId);
    }

    // Getter for delivery date
    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }
}
