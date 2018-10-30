/*
    Manav Kulshrestha
    RecursiveList.java
    10/27/18
*/
package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RecursiveList<T> implements ListInterface<T> {
    public LLNode<T> head;
    public LLNode<T> tail;
    public int size;

    public RecursiveList() {
        this.head = null;
        this.tail = this.head;
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public ListInterface<T> insertFirst(T elem) {
        this.head = new LLNode<>(null, elem, this.head);
        this.size++;
        return this;
    }

    @Override
    public ListInterface<T> insertLast(T elem) {
        this.tail = new LLNode<>(this.tail, elem, null);
        this.size++;
        return this;
    }

    @Override
    public ListInterface<T> insertAt(int index, T elem) {
        if(this.isEmpty() || index < 0 || index >= this.size)
            return null;
        if(index == 0)
            return this.insertFirst(elem);
        if(index == this.size-1)
            return this.insertLast(elem);
        return insertAt(index, elem, 1, this.head.next);
    }

    private ListInterface<T> insertAt(int index, T elem, int iterIndex, LLNode<T> currNode) {
        if(iterIndex > this.size)// you're taking care of size-1. look at edgecase
            return null;
        if(index == iterIndex) {
            currNode.prev.next = new LLNode<>(currNode.prev, elem, currNode);
            currNode.prev = currNode.prev.next;
            return this;
        }
        return insertAt(index, elem, iterIndex+1, currNode.next);
    }

    @Override
    public T removeFirst() {
        T ret = this.head.data;
        this.head = this.head.next;
        this.size--;
        return ret;
    }

    @Override
    public T removeLast() {
        T ret = this.tail.data;
        this.tail = this.tail.prev;
        this.size--;
        return ret;
    }

    @Override
    public T removeAt(int i) {
        if(this.isEmpty() || i < 0 || i >= this.size)
            return null;
        if(i == 0)
            return removeFirst();
        else if(i == this.size-1)
            return removeLast();
        return removeAt(i, 1, this.head.next);
    }

    private T removeAt(int i, int iterIndex, LLNode<T> currNode) {
        if(iterIndex > this.size)
            return null;
        if(i == iterIndex) {
            T ret = currNode.data;
            currNode.prev.next = currNode.next;
            currNode.next.prev = currNode.prev;
            this.size--;
            return ret;
        }
        return removeAt(i, iterIndex+1, currNode.next);
    }
    //HERE

    @Override
    public T getFirst() {
        return (this.isEmpty()) ? null : this.head.data;
    }

    @Override
    public T getLast() {
        return (this.isEmpty()) ? null : this.tail.data;
    }

    @Override
    public T get(int i) {
        return ((i >= this.size || i < 0) ? null : get(i, 0, this.head));
    }

    private T get(int i, int iterIndex, LLNode<T> currNode) {
        if(iterIndex >= this.size)
            return null;
        if(i == iterIndex)
            return currNode.data;
        return get(i, iterIndex+1, currNode.next);
    }

    @Override
    public boolean remove(T elem) {
        return (removeAt(indexOf(elem)) != null);
    }

    @Override
    public int indexOf(T elem) {
        return indexOf(elem, 1, this.head);
    }

    private int indexOf(T elem, int iterIndex, LLNode<T> currNode) {
        if(iterIndex >= this.size)
            return -1;
        if(currNode.data == elem)
            return iterIndex;
        return indexOf(elem, iterIndex+1, currNode.next);
    }

    @Override
    public boolean isEmpty() {
        return (this.size == 0);
    }

    @Override
    public Iterator<T> iterator() {
        return new RecursiveListIterator<>(this.head);
    }

    @Override
    public String toString() {
        return (this.isEmpty()) ? "" : this.head.toString();
    }

    public boolean valsEqual(Object[] objects) {
        LLNode<T> iter = this.head;
        for(Object o: objects) {
            if(!o.equals(iter.data))
                return false;
            iter=iter.next;
        }
        return true;
    }
}

class LLNode<T> {
    public LLNode<T> prev;
    public T data;
    public LLNode<T> next;

    public LLNode(LLNode<T> prev, T data, LLNode<T> next) {
        this.prev = prev;
        this.data = data;
        this.next = next;
    }

    @Override
    public String toString() {
        return this.data+","+((this.next != null) ? this.next : "");
    }
}

class RecursiveListIterator<T> implements Iterator<T> {
    LLNode<T> iter;

    public RecursiveListIterator(LLNode<T> head) {
        this.iter = head;
    }

    @Override
    public boolean hasNext() {
        return (this.iter != null);
    }

    @Override
    public T next() {
        if(this.hasNext()) {
            T ret = this.iter.data;
            this.iter = iter.next;
            return ret;
        }
        throw new NoSuchElementException();
    }
}