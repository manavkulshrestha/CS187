package sets;

import java.util.Iterator;

/**
 * A LinkedList-based implementation of Set
 */

/********************************************************
 * NOTE: Before you start, check the Set interface in
 * Set.java for detailed description of each method.
 *******************************************************/

/********************************************************
 * NOTE: for this project you must use linked lists
 * implemented by yourself. You are NOT ALLOWED to use
 * Java arrays of any type, or any Collection-based class
 * such as ArrayList, Vector etc. You will receive a 0
 * if you use any of them.
 *******************************************************/

/********************************************************
 * NOTE: you are allowed to add new methods if necessary,
 * but do NOT add new files (as they will be ignored).
 *******************************************************/

public class LinkedSet<E> implements Set<E> {
    private LinkedNode<E> head;
    private LinkedNode<E> tail;
    private int size;

    // Constructors
    public LinkedSet() {
        this.head = null;
        this.tail = head;
        this.size = 0;
    }

    public LinkedSet(E e) {
        this.head = new LinkedNode<E>(e, null);
        this.tail = head;
        this.size = 1;
    }

    private LinkedSet(LinkedNode<E> head) {
        this.head = head;
        this.tail = head;
        this.size = 1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return (this.size == 0);
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedNodeIterator<E>(this.head);
    }

    @Override
    public boolean contains(Object o) {
        // TODO (3)
        for(LinkedNode<E> iter = this.head; iter != null; iter = iter.getNext())
            if(iter.getData().equals(o))
                return true;
        return false;
    }

    @Override
    public boolean isSubset(Set<E> that) {
        return that.isSuperset(this);
    }

    @Override
    public boolean isSuperset(Set<E> that) {
        for(E e: that)
            if(!this.contains(e))
                return false;
        return true;
    }

    @Override
    public Set<E> adjoin(E e) {
        // TODO (6)
        if(this.contains(e))
            return this;


        
        this.size++;
        return null;
    }

    @Override
    public Set<E> union(Set<E> that) {
        for(LinkedNode<E> iter = this.head; iter != null; iter = iter.getNext())
            this.adjoin(iter.getData());
        return this;
    }

    @Override
    public Set<E> intersect(Set<E> that) {
        // TODO (8)
        return null;
    }

    @Override
    public Set<E> subtract(Set<E> that) {
        // TODO (9)
        return null;
    }

    @Override
    public Set<E> remove(E e) {
        // TODO (10)
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (! (o instanceof Set)) {
            return false;
        }
        Set<E> that = (Set<E>)o;
        return this.isSubset(that) && that.isSubset(this);
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (E e : this) {
            result += e.hashCode();
        }
        return result;
    }
}
