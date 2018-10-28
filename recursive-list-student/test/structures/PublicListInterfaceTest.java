package structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PublicListInterfaceTest {

	private ListInterface<String> list;
	private RecursiveList<Integer> li;

	@Before
	public void setup(){
		list = new RecursiveList<>();
		li = new RecursiveList<>();
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
	public void testInsertion() {
		assertTrue(li.isEmpty());
		for(int i=0; i<10; i++)
			li.insertLast(i);
        assertTrue(li.valsEqual(new Integer[] {1,2,3,4,5,6,7,8,9}));
	}
}
