package structures;

public class HeapLab {
    public static void main(String args[]) {
        MaxHeap<Integer> h = new MaxHeap<Integer>(100);
        System.out.println("===Test bubbleUp===");
        h.enqueue(10);
        h.enqueue(20);
        h.enqueue(30);
        System.out.println("Expected: 30 10 20");
        System.out.print("Actual:   ");h.print();
        h.enqueue(35);
        h.enqueue(25);
        h.enqueue(15);
        System.out.println("Expected: 35 30 20 10 25 15");
        System.out.print("Actual:   ");h.print();
        h.enqueue(50);
        System.out.println("Expected: 50 30 35 10 25 15 20");
        System.out.print("Actual:   ");h.print();
        System.out.println("===Test bubbleDown===");
        h.dequeue();
        System.out.println("Expected: 35 30 20 10 25 15");
        System.out.print("Actual:   ");h.print();
        h.dequeue();
        System.out.println("Expected: 30 25 20 10 15");
        System.out.print("Actual:   ");h.print();
        System.out.println("===Test bubbleUp and Down===");
        h.enqueue(21);
        h.dequeue();
        System.out.println("Expected: 25 20 21 10 15");
        System.out.print("Actual:   ");h.print();
        h.enqueue(30);
        h.enqueue(35);
        h.enqueue(40);
        h.enqueue(45);
        h.enqueue(50);
        System.out.println("Expected: 50 45 30 35 40 21 25 10 20 15");
        System.out.print("Actual:   ");h.print();
        h.set(0, 5);
        System.out.println("Expected: 45 40 30 35 15 21 25 10 20 5");
        System.out.print("Actual:   ");h.print();
        h.set(4, 50);
        System.out.println("Expected: 50 45 30 35 40 21 25 10 20 5");
        System.out.print("Actual:   ");h.print();
    }
}

class MaxHeap<T extends Comparable<T>> {
    private T[] heap = null;  // array storing heap elements
    private int size = 0;     // number of elements

    public void set(int index, T value) {
        // TODO A: set the value of a heap element
        if(index<0 || index >= size) return;
        heap[index] = value;
        int leftIndex = 2*index+1, rightIndex = 2*index+2;
        if(leftIndex >= this.size || rightIndex >= this.size) {
            bubbleUp(index);
            return;
        }
        if(value.compareTo(heap[leftIndex])<0 || value.compareTo(heap[leftIndex])<0)
            bubbleDown(index);
        else if(value.compareTo(heap[leftIndex])>0 || value.compareTo(heap[leftIndex])>0)
            bubbleUp(index);
    }

    public void bubbleUp(int index) {
        // TODO B: recursive bubbleUp
        if(index<1)
            return;
        int parentIndex = (index-1)/2;
        if(heap[parentIndex].compareTo(heap[index])<0) {
            T temp = heap[parentIndex];
            heap[parentIndex] = heap[index];
            heap[index] = temp;
            bubbleUp(parentIndex);
        }
    }


    public void bubbleDown(int index) {
        // TODO C: recursive bubbleDown
        if(index<0)
            return;
        int leftIndex = 2*index+1, rightIndex = 2*index+2;

        if(rightIndex<this.size
                && heap[rightIndex].compareTo(heap[index])>0
                && heap[rightIndex].compareTo(heap[leftIndex])>0) {
            T temp = heap[rightIndex];
            heap[rightIndex] = heap[index];
            heap[index] = temp;
            bubbleDown(rightIndex);
        } else if(leftIndex<this.size
                && heap[leftIndex].compareTo(heap[index])>0) {
            T temp = heap[leftIndex];
            heap[leftIndex] = heap[index];
            heap[index] = temp;
            bubbleDown(leftIndex);
        }
    }

    // ==========================================
    // The methods below are provided for testing
    // purpose. You do NOT need to modify any of
    // them. Feel free to add your own tests.
    // ==========================================
    public MaxHeap(int cap) { // constructor
        heap = (T[]) new Comparable[cap];
    }

    public void enqueue(T e) {
        // For now we'll just assume the capacity is
        // large enough so no need to expand array.
        heap[size++] = e;
        bubbleUp(size-1);
    }

    public T dequeue() {
        if(size==0) return null;
        T root = heap[0];
        heap[0] = heap[--size];
        bubbleDown(0);
        return root;
    }

    public void print() {
        for(int i=0;i<size;i++) System.out.print(heap[i]+" ");
        System.out.println();
    }
}