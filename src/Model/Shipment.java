package Model;

import java.time.LocalDate;
import java.util.Comparator;

public class Shipment implements Comparable<Shipment>{
    private int shipmentId;
    private static int cnt=1;
    private LocalDate deliveryDate;
    private String destination;
    private double price;
    private boolean priority;
     static LocalDate date=LocalDate.now();
    private LocalDate today=LocalDate.now();

    public Shipment() {
    }

    //this constructor for the vip shipments where the user can decide the deliveryDate
    // the raise in the price because the shipment is for vip 🤣
    public Shipment(int days, String destination, double price) {
        this.shipmentId = cnt++;
        //to prevent the past dates and bullying the stupid who would enter a negative number of days
        //(by adding them to the end of the heap and making them a normal dudes)
        if(days>=0) {
            this.deliveryDate = today.plusDays(days);
            this.price = price + 15;
            this.priority = true;
        }
        else{
            this.deliveryDate = date;
            date=date.plusDays(1);
            this.price = price;
            this.priority = false;
        }
        this.destination = destination;
    }

    //this one is for the normal shipments where the deliveryDate is generated automatically
    public Shipment(String destination, double price) {
        this.shipmentId = cnt++;
        this.deliveryDate = date;
        this.destination = destination;
        this.price = price;
        this.priority = false;
        date=date.plusDays(1);
    }

    //this shit is just for testing ,fuck i am exhausted
    public Shipment(int days, String destination, double price , boolean priority) {
        this.shipmentId = cnt++;
        this.deliveryDate = today.plusDays(days);
        this.destination = destination;
        this.price = price+15;
        this.priority = priority;
    }

    public int getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(int shipmentId) {
        this.shipmentId = shipmentId;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }


    @Override
    public int compareTo(Shipment o) {
        return Integer.compare(this.shipmentId,o.shipmentId);
    }

    // Getter for delivery date
    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "shipmentId=" + shipmentId +
                ", deliveryDate=" + deliveryDate +
                ", destination='" + destination + '\'' +
                ", price=" + price +
                ", priority=" + priority +
                ", today=" + today +
                '}';
    }
}
