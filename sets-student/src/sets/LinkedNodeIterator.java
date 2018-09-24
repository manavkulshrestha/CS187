package sets;

import java.util.Iterator;
import java.util.NoSuchElementException;

class LinkedNodeIterator<E> implements Iterator<E> {
    // TODO (1) define data variables
    private LinkedNode<E> iter;

    // Constructors
    public LinkedNodeIterator(LinkedNode<E> head) {
        // TODO (2) choose appropriate parameters and do the initialization
        this.iter = head;
    }

    @Override
    public boolean hasNext() {
        // TODO (3)
        return (this.iter != null);
    }

    @Override
    public E next() {
        // TODO (4)
        if(this.hasNext()) {
          E ret = this.iter.getData();
          this.iter = iter.getNext();
          return ret;
        }
        throw new NoSuchElementException();
    }

    @Override
    public void remove() {
        // Nothing to change for this method
        throw new UnsupportedOperationException();
    }
}
