package assignment7;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class MyStackTests {
	MyStack<Integer> stack = new MyStack<>();

	@Before
	public void setUp() throws Exception {
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
	
	}

	@Test
	public void testClear() {
		stack.clear();
		assertEquals(0, stack.size());
	}

	@Test
	public void testIsEmpty() {
		stack.clear();
		assertTrue(stack.isEmpty());
	}

	@Test
	public void testPeek() {
		int item = stack.peek();
		assertEquals(5, item);

	}

	@Test
	public void testPeekNoSuchElement() {
		stack.clear();
		
		assertThrows(NoSuchElementException.class, () ->

		{

			stack.peek();

		});
	}

	@Test
	public void testPop() {
		int item = stack.pop();
		assertEquals(5, item);

	}

	@Test
	public void testPopNoSuchElement() {
		stack.clear();

		assertThrows(NoSuchElementException.class, () ->

		{

			stack.pop();

		});

	}

	@Test
	public void testPush() {
		stack.push(-5);
		int topOfTheStack = stack.pop();
		assertEquals(-5, topOfTheStack);
	}

	@Test
	public void testSize() {
		assertEquals(5, stack.size());
	}

}