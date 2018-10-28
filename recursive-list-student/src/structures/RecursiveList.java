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
    public int size;

    public RecursiveList() {
        this.head = null;
        this.size = 0;
    }

//    public RecursiveList(Object... ints) {
//        this.head = new LLNode<>((T) ints[0]);
//        int i = 0;
//        for(LLNode<T> iter=this.head; i<ints.length; i++, iter=iter.link) {
//            if(i != ints.length-1) {
//                iter.link = new LLNode<T>((T) ints[i], );
//            } else
//                iter.link = new LLNode<T>((T) ints[i]);
//        }
//    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public ListInterface<T> insertFirst(T elem) {
        this.head = new LLNode<>(elem, this.head);
        this.size++;
        return this;
    }

    @Override
    public ListInterface<T> insertLast(T elem) {
        return insertAt(this.size-1, elem);
    }

    @Override
    public ListInterface<T> insertAt(int index, T elem) {
        if(index == 0)
            return insertFirst(elem);
        return (index >= this.size || index < 0) ? null : insertAt(index-1, elem, 0, this.head);
    }

    private ListInterface<T> insertAt(int prevIndex, T elem, int iterIndex, LLNode<T> currNode) {
        if(iterIndex >= this.size-1)
            return null;
        if(prevIndex == iterIndex) {
            currNode.link = new LLNode<>(elem, currNode.link);
            this.size++;
            return this;
        }
        return insertAt(prevIndex, elem, iterIndex+1, currNode.link);
    }

    @Override
    public T removeFirst() {
        return removeAt(0);
    }

    @Override
    public T removeLast() {
        return removeAt(this.size-1);
    }

    @Override
    public T removeAt(int i) {
        if(i == 0) {
            T ret = this.head.data;
            this.head = this.head.link;
            return ret;
        }
        return (i >= this.size || i < 0) ? null : removeAt(i-1, 0, this.head);
    }

    private T removeAt(int iPrev, int iterIndex, LLNode<T> currNode) {
        if(iterIndex >= this.size-1)
            return null;
        if(iPrev == iterIndex) {
            T ret = currNode.link.data;
            if(iPrev == this.size-2)
                currNode.link =  null;
            else
                currNode.link = currNode.link.link;
            this.size--;
            return ret;
        }

        return removeAt(iPrev, iterIndex+1, currNode.link);
    }

    @Override
    public T getFirst() {
        return (this.isEmpty()) ? null : this.head.data;
    }

    @Override
    public T getLast() {
        return get(this.size-1);
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
        return get(i, iterIndex+1, currNode.link);
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
        return indexOf(elem, iterIndex+1, currNode.link);
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
            iter=iter.link;
        }
        return true;
    }
}

class LLNode<T> {
    public T data;
    public LLNode<T> link;

    public LLNode(T data, LLNode<T> link) {
        this.data = data;
        this.link = link;
    }

    public LLNode(T data) {
        this(data, null);
    }

    @Override
    public String toString() {
        return this.data+","+((this.link != null) ? this.link : "");
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
            this.iter = iter.link;
            return ret;
        }
        throw new NoSuchElementException();
    }
}