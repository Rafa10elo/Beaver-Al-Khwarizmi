package Model;

import java.util.ArrayList;

public class Heap {

    // we will use an arraylist
    private ArrayList<Shipment> heap = new ArrayList<>();

    //getting indexes of parent/left,right children (just to make it clear for you)
    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    private void heapifyUp(int index) {
        if (index == 0) return;

        int l = heap.get(index).getDeliveryDate().compareTo(heap.get(parent(index)).getDeliveryDate());
        if (l < 0) {
            Shipment temp = heap.get(index);
            heap.set(index, heap.get(parent(index)));
            heap.set(parent(index), temp);
            heapifyUp(parent(index));
        }
    }


    void heapify(int index) {
        int size = heap.size();
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;


        int test1 = heap.get(left).compareTo(heap.get(largest));
        if (left < size && test1 < 0) {
            largest = left;
        }
        test1 = heap.get(right).compareTo(heap.get(largest));
        if (right < size && test1 < 0) {
            largest = right;
        }


        if (largest != index) {
            Shipment temp = heap.get(index);
            heap.set(index, heap.get(largest));
            heap.set(largest, temp);
            heapify(largest);
        }
    }


    public void insert(Shipment value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    public void delete(Shipment value) {
        int index = heap.indexOf(value);
        if (index == -1) return;

        int lastIndex = heap.size() - 1;
        Shipment temp = heap.get(index);
        heap.set(index, heap.get(lastIndex));
        heap.set(lastIndex, temp);
        heap.remove(lastIndex);

        int test2 = heap.get(index).compareTo(heap.get(parent(index)));
        if (index < heap.size()) {
            if (index > 0 && test2 < 0) {
                heapifyUp(index);
            } else {
                heapify(index);
            }

        }
    }

    public void printHeap() {
        System.out.println(heap);
    }
}

