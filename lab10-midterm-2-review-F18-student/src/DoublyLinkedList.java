
/**
 *  Implement a link-hopping method for finding the middle node of a doubly
 *	linked list with head and tail pointers, and an odd number of nodes. Note that you
 *	should only use link-hopping (cannot use a counter, must follow next,prev references only).
 *
 *	If the list has an EVEN number of elements, return null
 * @author jrondeau
 */
public class DoublyLinkedList<T> {
	private DLNode<T> head;
	private DLNode<T> tail;	
	private int size;
	
	public DoublyLinkedList(){
		size = 0;
	}
	
	/**
	 * @param element Data to append to the list
	 */
	public void add(T element) {
		if(head == null) {
			head = new DLNode<T>(element, null, null);
			tail = head;
		} else {
			tail.next = new DLNode<T>(element, null, tail);
			tail = tail.next;
		}
		
		size++;
	}
	
	/**
	 * 
	 * @return Length of the list
	 */
	public int getLength() {
		return size;
	}
	
	/**
	 * Prints out all elements of the list in order from head to tail
	 */
	public void printList() {
		DLNode<T> temp = head;
		while(temp != null) {
			System.out.print(temp.data.toString() + " ");
			temp = temp.next;
		}
	}
	
	/**
	 * @TODO: Implement this method!
	 * @return T: data element held in the middle node of an odd-sized list. NULL if the list is even length
	 */
	public T findMiddle(){
	    if(this.size%2 == 0)
	        return null;

        DLNode<T> front = this.head, back = this.tail;
		for(; front != back; front=front.next, back=back.prev) {}

		return front.data;
	}

	/**
	 * @TODO
	 * @param a - DoublyLinkedList<Float> 
	 * @return Median number of list as a float
	 */
	public static float findMedian(DoublyLinkedList<Float> a){
        DLNode<Float> front = a.head, back = a.tail;

        while(front != back) {
            front = front.next;
            if(front == back)
                return (front.prev.data+back.data)/2;
            back = back.prev;
        }

        return front.prev.data;
	}
	
}