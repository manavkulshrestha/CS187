package structures;

import comparators.ReverseIntegerComparator;

import java.util.Comparator;
import java.util.Iterator;

public class MinQueue<V> implements PriorityQueue<Integer, V> {
    private Comparator<Integer> comparator;
    private StudentArrayHeap<Integer, V> heap;


    public MinQueue() {
        this.heap = new StudentArrayHeap<>(this.comparator = new ReverseIntegerComparator());
    }

    @Override
    public PriorityQueue<Integer, V> enqueue(Integer priority, V value) {
        this.heap.add(priority, value);
        return this;
    }

    @Override
    public V dequeue() {
        return this.heap.remove();
    }

    @Override
    public V peek() {
        return this.heap.peek();
    }

    @Override
    public Iterator<Entry<Integer, V>> iterator() {
        return this.heap.heap.iterator();
    }

    @Override
    public Comparator<Integer> getComparator() {
        return this.comparator;
    }

    @Override
    public int size() {
        return this.heap.size();
    }

    @Override
    public boolean isEmpty() {
        return this.heap.isEmpty();
    }
}

