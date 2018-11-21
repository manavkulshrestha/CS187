package structures;

import java.util.Comparator;
import java.util.Iterator;

public class StudentArrayHeap<P, V> extends AbstractArrayHeap<P, V> {

    protected StudentArrayHeap(Comparator<P> comparator) {
        super(comparator);
    }

    @Override
    protected int getLeftChildOf(int index) throws IndexOutOfBoundsException {
        if(index < 0)
            throw new IndexOutOfBoundsException();
        return 2*index+1;
    }

    @Override
    protected int getRightChildOf(int index) throws IndexOutOfBoundsException {
        if(index < 0)
            throw new IndexOutOfBoundsException();
        return 2*index+2;
    }

    @Override
    protected int getParentOf(int index) throws IndexOutOfBoundsException {
        if(index < 1)
            throw new IndexOutOfBoundsException();
        return (index-1)/2;
    }

    @Override
    protected void bubbleUp(int index) {
        try {
            int parentIndex = getParentOf(index);
            Entry<P,V> parent = this.heap.get(parentIndex), entry = this.heap.get(index);

            if(this.comparator.compare(parent.getPriority(), entry.getPriority()) < 0) {
                swap(parentIndex, index);
                bubbleUp(parentIndex);
            }
        } catch(IndexOutOfBoundsException e) {
            return;
        }
    }

    @Override
    protected void bubbleDown(int index) {
        try {
            int leftIndex = getLeftChildOf(index), rightIndex = getRightChildOf(index);
            Entry<P,V> parent = this.heap.get(index), left = this.heap.get(leftIndex), right = this.heap.get(rightIndex);

            if(rightIndex<size()
                    && comparator.compare(right.getPriority(), parent.getPriority())>0
                    && comparator.compare(right.getPriority(), left.getPriority())>0) {
                swap(rightIndex, index);
                bubbleDown(rightIndex);
            } else if(leftIndex<size()
                    && comparator.compare(left.getPriority(), parent.getPriority())>0) {
                swap(leftIndex, index);
                bubbleDown(leftIndex);
            }
        } catch(IndexOutOfBoundsException e) {
            return;
        }

    }
}

