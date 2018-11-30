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
        if(index < 1)
            return;
        int parentIndex = getParentOf(index);

        if(comparator.compare(heap.get(parentIndex).getPriority(), heap.get(index).getPriority())<0) {
            swap(index, parentIndex);
            bubbleUp(parentIndex);
        }
    }

    @Override
    protected void bubbleDown(int index) {
        if(index >= size()/2)
            return;
        int lIndex = getLeftChildOf(index), rIndex = getRightChildOf(index), lcIndex;

        if(rIndex<size() && comparator.compare(heap.get(lIndex).getPriority(), heap.get(rIndex).getPriority())<0)
            lcIndex = rIndex;
        else
            lcIndex = lIndex;

        if(comparator.compare(heap.get(index).getPriority(), heap.get(lcIndex).getPriority())<0) {
            swap(index, lcIndex);
            bubbleDown(lcIndex);
        }
    }
}

