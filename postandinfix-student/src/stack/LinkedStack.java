package stack;

/**
 * A {@link LinkedStack} is a generic stack that is implemented using
 * a Linked List structure to allow for unbounded size.
 */
public class LinkedStack<T> {
	
	private LLNode<T> top = null;
	private int size = 0;

	/**
	 * Remove and return the top element on this stack.
	 * If stack is empty, return null (instead of throw exception)
	 */
	public T pop() {
		if(isEmpty())
			return null;
		T ret = this.top.info;
		LLNode<T> newTop = this.top.link;
		this.top.link = null;
		this.top = newTop;
		this.size--;
		return ret;
	}

	/**
	 * Return the top element of this stack (do not remove the top element).
	 * If stack is empty, return null (instead of throw exception)
	 */
	public T top() {
		if(isEmpty())
			return null;
		return top.info;
	}

	/**
	 * Return true if the stack is empty and false otherwise.
	 */
	public boolean isEmpty() {
		return (this.size == 0);
	}

	/**
	 * Return the number of elements in this stack.
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Pushes a new element to the top of this stack.
	 */
	public void push(T elem) {
		LLNode<T> newTop = new LLNode<>(elem);
		newTop.link = this.top;
		this.top = newTop;
		this.size++;
	}

	@Override
	public String toString() {
		return (this.top == null) ? "null" : this.top.toString();
	}
}
