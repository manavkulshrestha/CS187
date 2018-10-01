package sets;

import java.util.Iterator;
import java.util.NoSuchElementException;

class LinkedNodeIterator<E> implements Iterator<E> {
    private LinkedNode<E> iter;

    // Constructors
    public LinkedNodeIterator(LinkedNode<E> head) {
        this.iter = head;
    }

    @Override
    public boolean hasNext() {
        return (this.iter != null);
    }

    @Override
    public E next() {
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
