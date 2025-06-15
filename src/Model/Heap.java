package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class Heap {

    private ArrayList<Shipment> heap = new ArrayList<>();

    // this is just to compare the dates 😛
    private final Comparator<Shipment> shipmentComparator = Comparator.comparing(Shipment::getDeliveryDate);

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    //because there was so many swaps i created this function 🤯
    private void swap (int i,int j){
        Shipment temp=heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    private void heapifyUp(int index) {
        if (index == 0) return;

        if (shipmentComparator.compare(heap.get(index),heap.get(parent(index))) < 0) {
           swap(index,parent(index));
            heapifyUp(parent(index));
        }
    }

    private void heapify(int index) {
        int size = heap.size();
        int smallest = index;
        //use the functions up 🙁 (left & right)
        //int left = 2 * index + 1;
        //int right = 2 * index + 2;
        int left = left(index);
        int right = right(index);

        if (left < size && shipmentComparator.compare(heap.get(left), heap.get(smallest)) < 0) {
            smallest = left;
        }
        if (right < size && shipmentComparator.compare(heap.get(right), heap.get(smallest))  < 0) {
            smallest = right;
        }

        if (smallest != index) {
            swap(index,smallest);
            heapify(smallest);
        }
    }

    // okeyyy💅🏻 this to add the vip shipments
    // (add it to the arraylist and edit all the other dates after it by adding one day to there date)
    public void insertVIP(Shipment vipShipment) {
        LocalDate vipDate = vipShipment.getDeliveryDate();

        //this while to solve the problem if the user entered a taken date by a vip
        //so we search to the nearest available date
        while (true) {
            boolean takenDate = false;

            for (Shipment s : heap) {
                if (s.isPriority() && s.getDeliveryDate().equals(vipDate)) {
                    takenDate = true;
                    break;
                }
            }

            if (!takenDate) break;
            vipDate = vipDate.plusDays(1);
        }
        vipShipment.setDeliveryDate(vipDate);
        heap.add(vipShipment);
        heapifyUp(heap.size() - 1);

        for (Shipment s : heap) {
            if (!s.isPriority() && !s.equals(vipShipment)
                    && !s.getDeliveryDate().isBefore(vipDate)) {
                s.setDeliveryDate(s.getDeliveryDate().plusDays(1));
            }
        }

        //to rearrange the heap
        rebuildHeap();
    }

    private void rebuildHeap() {
        for (int i = heap.size() / 2; i >= 0; i--) {
            heapify(i);
        }
    }

    public void insert(Shipment value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    public void deleteHelper(int id) {
        for (Shipment s : heap) {
            if (s.getShipmentId() == id) {
                delete(s);
                return;
            }
        }
    }

    private void delete(Shipment value) {
        int index = heap.indexOf(value);
        if (index == -1) return;

        int lastIndex = heap.size() - 1;
        swap(index,lastIndex);
        heap.remove(lastIndex);

        int test2 = shipmentComparator.compare(heap.get(index), heap.get(parent(index)));
        if (index < heap.size()) {
            if (index > 0 && test2 < 0) {
                heapifyUp(index);
            } else {
                heapify(index);
            }

        }
    }

    //remember to add it somewhere
    public void removeExpiredShipments() {
        LocalDate today = LocalDate.now();
        int i = 0;
        while (i < heap.size()) {
            Shipment s=heap.get(i);
            if (!s.getDeliveryDate().isAfter(today)&& s.getDeliveryDate().isBefore(today)) {
                delete(s);
            }
            else
                i++;
        }
    }

    public void printHeap() {
        for (Shipment s : heap) {
            System.out.println("ID: " + s.getShipmentId() +
                    ", Date: " + s.getDeliveryDate() +
                    ", Priority: " + s.isPriority());
        }
    }

}

