package structures;

import java.util.NoSuchElementException;

/**************************************************************************************
 * NOTE: before starting to code, check support/structures/UnboundedQueueInterface.java
 * for detailed explanation of each interface method, including the parameters, return
 * values, assumptions, and requirements
 ***************************************************************************************/
public class Queue<T> implements UnboundedQueueInterface<T> {

    public Node<T> front;
    public Node<T> rear;
    public int size;

    public Queue() {
        this.front = null;
        this.rear = this.front;
        this.size = 0;
    }
	
	public Queue(Queue<T> other) {
        Queue<T> reversed = (Queue<T>) other.reversed();

        for(Node<T> iter=reversed.front; iter != null; iter=iter.next)
            this.enqueue(iter.data);
	}
	
	@Override
	public boolean isEmpty() {
        return (this.front == null);
	}

	@Override
	public int size() {
        return this.size;
	}

	@Override
	public void enqueue(T element) {
        if(this.rear == null) {
            this.front = new Node<T>(element);
            this.rear = this.front;
        } else {
            this.rear.next = new Node<T>(element);
            this.rear = this.rear.next;
        }
        this.size++;
	}

	@Override
	public T dequeue() throws NoSuchElementException {
        T dequeued = this.front.data;
        this.front = this.front.next;
        this.size--;
        return dequeued;
	}

	@Override
	public T peek() throws NoSuchElementException {
        return this.front.data;
	}

	
	@Override
	public UnboundedQueueInterface<T> reversed() {
        UnboundedQueueInterface<T> reversed = new Queue<>();

        for(Node<T> iter=this.front; iter != null; iter=iter.next)
            reversed.enqueue(iter.data);

        return reversed;
	}

	@Override
    public String toString() {
        return (!this.isEmpty()) ? this.front.toString() : "";
    }

}

class Node<T> {
	public T data;
	public Node<T> next;
	public Node(T data) { this.data=data;}
	public Node(T data, Node<T> next) {
		this.data = data; this.next=next;
	}
    @Override
    public String toString() {
        return this.data+""+((this.next != null) ? ","+this.next : "");
    }
}

