package structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PublicListInterfaceTest {

	private ListInterface<String> list;
	private RecursiveList<Integer> li;
	private RecursiveList<Integer> l1;
	private RecursiveList<Integer> l2;
	private RecursiveList<Integer> l3;
	private RecursiveList<Integer> l4;
	private RecursiveList<Integer> l5;
	private RecursiveList<Integer> l6;
	private RecursiveList<Integer> l7;
	private RecursiveList<Integer> l8;
	private RecursiveList<Integer> l9;

	@Before
	public void setup(){
		list = new RecursiveList<>();
		li = new RecursiveList<>();
		l1 = new RecursiveList<>();
		l2 = new RecursiveList<>();
		l3 = new RecursiveList<>();
		l4 = new RecursiveList<>();
		l5 = new RecursiveList<>();
		l6 = new RecursiveList<>();
		l7 = new RecursiveList<>();
		l8 = new RecursiveList<>();
		l9 = new RecursiveList<>();
	}

	@Test (timeout = 500)
	public void testInsertFirstIsEmptySizeAndGetFirst1() {
		assertTrue("Newly constructed list should be empty.", list.isEmpty());
		assertEquals("Newly constructed list should be size 0.", 0, list.size());
		assertEquals("Insert First should return instance of self", list, list.insertFirst("hello"));
		assertFalse("List should now have elements.", list.isEmpty());
		assertEquals("List should now have 1 element.", 1, list.size());
		assertEquals("First element should .equals \"hello\".", "hello", list.getFirst());
		list.insertFirst("world");
		assertEquals(2, list.size());
		list.insertFirst("foo");
		assertEquals(3, list.size());
		assertEquals("First element should .equals \"foo\".", "foo", list.getFirst());
	}

	@Test
	public void testLastInsertion() {
		assertTrue(li.isEmpty());
		for(int i=0; i<10; i++)
			li.insertLast(i);
        assertTrue(valsEqual(li, new Integer[] {0,1,2,3,4,5,6,7,8,9}));
	}

	@Test
	public void testEmptyRemove() {
		assertTrue(l1.isEmpty());
		l1.removeFirst();
	}

	@Test
	public void testIndexOf() {
		assertTrue(l2.isEmpty());
		for(int i=0; i<10; i++)
			l2.insertLast(i);
		for(int i=0; i<10; i++)
			assertEquals(i, l2.indexOf(i));
	}

	@Test
	public void testInsertionAndRemoval() {
		assertTrue(l3.isEmpty());
		for(int i=0; i<10; i++)
			l3.insertLast(i);
		assertTrue(valsEqual(l3, new Integer[] {0,1,2,3,4,5,6,7,8,9}));

		for(int i=0; i<5; i++)
			l3.removeLast();
		assertTrue(valsEqual(l3, new Integer[] {0,1,2,3,4}));

		for(int i=0; i<5; i++)
			l3.removeLast();
		assertTrue(l3.isEmpty());
		assertTrue(l3.size() == 0);
	}

	public boolean valsEqual(RecursiveList<Integer> l, Integer... obs) {
		Node<Integer> iter = l.head;
		for(Object o: obs) {
			if(!iter.data.equals(o))
				return false;
			iter=iter.next;
		}
		return iter == null;
	}
}
