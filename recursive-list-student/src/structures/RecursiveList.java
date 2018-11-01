/*
    Manav Kulshrestha
    RecursiveList.java
    10/27/18
*/
package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RecursiveList<T> implements ListInterface<T> {
    public Node<T> head;
    public Node<T> tail;
    public int size;

    public RecursiveList() {
        this.head = null;
        this.tail = null;
        this.size == 0;
    }

    /**
     * Returns the number of elements in this {@link ListInterface}. This method
     * runs in O(1) time.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Adds an element to the front of this {@link ListInterface}. This method
     * runs in O(1) time. For convenience, this method returns the
     * {@link ListInterface} that was modified.
     *
     * @param elem
     *            the element to add
     * @throws NullPointerException
     *             if {@code elem} is {@code null}
     * @return The modified {@link ListInterface}
     */
    @Override
    public ListInterface<T> insertFirst(T elem) throws NullPointerException {
        if(elem == null)
            throw new NullPointerException();
        if(this.size++ == 0) {
            this.head = new Node<>(null, elem, null);
            this.tail = this.head;
        } else {
            this.head.prev = new Node<>(null, elem, this.head);
            this.head = this.head.prev;
        }
        return this;
    }

    /**
     * Adds an element to the end of this {@link ListInterface}. This method
     * runs in O(size) time. For convenience, this method returns the
     * {@link ListInterface} that was modified.
     *
     * @param elem
     *            the element to add
     * @throws NullPointerException
     *             if {@code elem} is {@code null}
     * @return the modified {@link ListInterface}
     */
    @Override
    public ListInterface<T> insertLast(T elem) throws NullPointerException {
        if(elem == null)
            throw new NullPointerException();
        if(this.size++ == 0) {
            this.tail = new Node<>(null, elem, null);
            this.head = this.tail;
        } else {
            this.tail.next = new Node<>(this.tail, elem, null);
            this.tail = this.tail.next;
        }
        return this;
    }

    /**
     * Adds an element at the specified index such that a subsequent call to
     * {@link ListInterface#get(int)} at {@code index} will return the inserted
     * value. This method runs in O(index) time. For convenience, this method
     * returns the {@link ListInterface} that was modified.
     *
     * @param index
     *            the index to add the element at
     * @param elem
     *            the element to add
     * @throws NullPointerException
     *             if {@code elem} is {@code null}
     * @throws IndexOutOfBoundsException
     *             if {@code index} is less than 0 or greater than
     *             {@link ListInterface#size()}
     * @return The modified {@link ListInterface}
     */
    @Override
    public ListInterface<T> insertAt(int index, T elem) throws NullPointerException, IndexOutOfBoundsException {
        if(elem == null)
            throw new NullPointerException();
        if(index < 0 || index > this.size)
            throw new IndexOutOfBoundsException();
        if(index == 0)
            return insertFirst(elem);
        if(index == this.size)
            return insertLast(elem);
        return insertAt(index, elem, 1, this.head.next);
    }

    private ListInterface<T> insertAt(int index, T elem, int iterIndex, Node<T> iterNode) {
        if(index == iterIndex) {
            iterNode.prev.next = new Node<>(iterNode.prev, elem, iterNode);
            iterNode.prev = iterNode.prev.next;
            this.size++;
            return this;
        }
        return insertAt(index, elem, iterIndex+1, iterNode.next);
    }

    /**
     * Removes the first element from this {@link ListInterface} and returns it.
     * This method runs in O(1) time.
     *
     * @throws IllegalStateException
     *             if the {@link ListInterface} is empty.
     * @return the removed element
     */
    @Override
    public T removeFirst() throws IllegalStateException{
        if(isEmpty())
            throw new IllegalStateException();
        T ret = this.head.data;
        if(this.size-- == 1) {
            this.head = null;
            this.tail = null;
        } else {
            this.head = this.head.next;
            this.head.prev = null;
        }
        return ret;
    }

    /**
     * <p>
     * Removes the last element from this {@link ListInterface} and returns it.
     * This method runs in O(size) time.
     *</p>
     *
     * @throws IllegalStateException
     *             if the {@link ListInterface} is empty.
     * @return the removed element
     */
    @Override
    public T removeLast() throws IllegalStateException {
        if(isEmpty())
            throw new IllegalStateException();
        T ret = this.tail.data;
        if(this.size-- == 1) {
            this.head = null;
            this.tail = null;
        } else {
            this.tail = this.tail.prev;
            this.tail.next = null;
        }
        return ret;
    }

    /**
     * Removes the ith element in this {@link ListInterface} and returns it.
     * This method runs in O(i) time.
     *
     * @param i
     *            the index of the element to remove
     * @throws IndexOutOfBoundsException
     *             if {@code i} is less than 0 or {@code i} is greater than or
     *             equal to {@link ListInterface#size()}
     * @return The removed element
     */
    @Override
    public T removeAt(int i) throws IndexOutOfBoundsException {
        if(i < 0 || i >= this.size)
            throw new IndexOutOfBoundsException();
        if(i == 0)
            return removeFirst();
        if(i == this.size-1)
            return removeLast();
        return removeAt(i, 1, this.head.next);
    }

    private T removeAt(int i, int iterIndex, Node<T> iterNode) {
        if(i == iterIndex) {
            iterNode.prev.next = iterNode.next;
            this.size--;
            return iterNode.data;
        }
        return removeAt(i, iterIndex+1, iterNode.next);
    }

    /**
     * Returns the first element in this {@link ListInterface}. This method runs
     * in O(1) time.
     *
     * @throws IllegalStateException
     *             if the {@link ListInterface} is empty.
     * @return the first element in this {@link ListInterface}.
     */
    @Override
    public T getFirst() throws IllegalStateException {
        if(isEmpty())
            throw new IllegalStateException();
        return this.head.data;
    }

    /**
     * Returns the last element in this {@link ListInterface}. This method runs
     * in O(size) time.
     *
     * @throws IllegalStateException
     *             if the {@link ListInterface} is empty.
     * @return the last element in this {@link ListInterface}.
     */
    @Override
    public T getLast() throws IllegalStateException {
        if(isEmpty())
            throw new IllegalStateException();
        return this.tail.data;
    }

    /**
     * Returns the ith element in this {@link ListInterface}. This method runs
     * in O(i) time.
     *
     * @param i
     *            the index to lookup
     * @throws IndexOutOfBoundsException
     *             if {@code i} is less than 0 or {@code i} is greater than or
     *             equal to {@link ListInterface#size()}
     * @return the ith element in this {@link ListInterface}.
     */
    @Override
    public T get(int i) throws IndexOutOfBoundsException {
        if(i < 0 || i >= this.size)
            throw new IndexOutOfBoundsException();
        if(i == 0)
            return getFirst();
        if(i == this.size-1)
            return getLast();
        return get(i, 1, this.head.next);
    }

    private T get(int i, int iterIndex, Node<T> iterNode) {
        if(i == iterIndex)
            return iterNode.data;
        return get(i, iterIndex+1, iterNode.next);
    }

    /**
     * Removes {@code elem} from this {@link ListInterface} if it exists. If
     * multiple instances of {@code elem} exist in this {@link ListInterface}
     * the one associated with the smallest index is removed. This method runs
     * in O(size) time.
     *
     * @param elem
     *            the element to remove
     * @throws NullPointerException
     *             if {@code elem} is {@code null}
     * @return {@code true} if this {@link ListInterface} was altered and
     *         {@code false} otherwise.
     */
    @Override
    public boolean remove(T elem) throws NullPointerException {
        if(elem == null)
            throw new NullPointerException();
        int index = indexOf(elem);
        if(index == -1)
            return false;
        return (removeAt(index) != null);//should never be null
    }

    /**
     * Returns the smallest index which contains {@code elem}. If there is no
     * instance of {@code elem} in this {@link ListInterface} then -1 is
     * returned. This method runs in O(size) time.
     *
     * @param elem
     *            the element to search for
     * @throws NullPointerException
     *             if {@code elem} is {@code null}
     * @return the smallest index which contains {@code elem} or -1 if
     *         {@code elem} is not in this {@link ListInterface}
     */
    @Override
    public int indexOf(T elem) throws NullPointerException {
        if(elem == null)
            throw new NullPointerException();
        return indexOf(elem, this.head, 0);
    }

    private int indexOf(T elem, Node<T> iterNode, int iterIndex) {
        if(iterIndex >= this.size)
            return -1;
        if(elem == iterNode.data)
            return iterIndex;
        return indexOf(elem, iterNode.next, iterIndex+1);
    }

    /**
     * Returns {@code true} if this {@link ListInterface} contains no elements
     * and {@code false} otherwise. This method runs in O(1) time.
     *
     * @return {@code true} if this {@link ListInterface} contains no elements
     *         and {@code false} otherwise.
     */
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
        return (isEmpty()) ? "" : recStringBuilder(this.head);
    }

    private String recStringBuilder(Node<T> node) {
        return node.data+((node.next != null) ? ","+recStringBuilder(node.next) : "");
    }
}

class Node<T> {
    public Node<T> prev;
    public T data;
    public Node<T> next;

    public Node(Node<T> prev, T data, Node<T> next) {
        this.prev = prev;
        this.data = data;
        this.next = next;
    }

    @Override
    public String toString() {
        return this.data.toString();
    }
}

class RecursiveListIterator<T> implements Iterator<T> {
    Node<T> iter;

    public RecursiveListIterator(Node<T> head) {
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