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

    // Constructors
    public LinkedSet() {
        this.head = null;
    }

    public LinkedSet(E e) {
        this.head = new LinkedNode<>(e, null);
    }

    private LinkedSet(LinkedNode<E> head) {
        this.head = head;
    }

    @Override
    public int size() {
        int size = 0;

        for(E e: this)
            size++;

        return size;
    }

    @Override
    public boolean isEmpty() {
        return (this.head == null);
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedNodeIterator<>(this.head);
    }

    @Override
    public boolean contains(Object o) {
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
        if(this.contains(e))
            return new LinkedSet<>(this.head);
        return new LinkedSet<>(new LinkedNode<>(e, this.head));
    }

    @Override
    public Set<E> union(Set<E> that) {
        Set<E> union = new LinkedSet<>(this.head);

        for(E e: that)
            union = union.adjoin(e);

        return union;
    }

    @Override
    public Set<E> intersect(Set<E> that) {
        Set<E> intersection = new LinkedSet<>();

        for(E e: that)
            if(this.contains(e))
                intersection = intersection.adjoin(e);

        return intersection;
    }

    @Override
    public Set<E> subtract(Set<E> that) {
        Set<E> subtracted = new LinkedSet<>();

        for(E e: this)
            if(!that.contains(e))
                subtracted = subtracted.adjoin(e);

        return subtracted;
    }

    @Override
    public Set<E> remove(E e) {
        Set<E> ret = new LinkedSet<>();

        for(E entry: this)
            if(!entry.equals(e))
                ret = ret.adjoin(entry);

        return ret;
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
