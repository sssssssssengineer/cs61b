/** Performs some basic linked list tests. */
import static org.junit.Assert.*;

import org.junit.Test;
public class LinkedListDequeTest {


	/* Utility method for printing out empty checks. */
	public static boolean checkEmpty(boolean expected, boolean actual) {
		if (expected != actual) {
			System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Utility method for printing out empty checks. */
	public static boolean checkSize(int expected, int actual) {
		if (expected != actual) {
			System.out.println("size() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Prints a nice message based on whether a test passed. 
	 * The \n means newline. */
	public static void printTestStatus(boolean passed) {
		if (passed) {
			System.out.println("Test passed!\n");
		} else {
			System.out.println("Test failed!\n");
		}
	}

	/** Adds a few things to the list, checking isEmpty() and size() are correct, 
	  * finally printing the results. 
	  *
	  * && is the "and" operation. */
	public static void addIsEmptySizeTest() {
		System.out.println("Running add/isEmpty/Size test.");

		LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst("front");
		passed = checkSize(1, lld1.size()) && passed;
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.addLast("middle");
		passed = checkSize(2, lld1.size()) && passed;

		lld1.addLast("back");
		passed = checkSize(3, lld1.size()) && passed;

		System.out.println("Printing out deque: ");
		lld1.printDeque();

		printTestStatus(passed);

	}

	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
	public static void addRemoveTest() {

		System.out.println("Running add/remove test.");


		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty 
		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty 
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.removeFirst();
		// should be empty 
		passed = checkEmpty(true, lld1.isEmpty()) && passed;

		printTestStatus(passed);

	}

		//@Test
		public static void testaddFirst() {
			System.out.println("Running addFirst test.");
			LinkedListDeque a=new LinkedListDeque();
			a.addFirst(5.3);
			a.addLast("apple");
			//System.out.println(a.sentinel.prev.items);
			assertEquals(a.sentinel.next.items,5.3);
			assertEquals(a.sentinel.prev.items,"apple");
			System.out.println("Test passed");
		}
	//@Test
	public static void testget() {
		System.out.println("Running get test.");
		LinkedListDeque a=new LinkedListDeque();
		a.addFirst(5.3);
		a.addLast("apple");
		a.addLast("banana");
		a.addLast(7);
		assertEquals(a.get(3),7);
		assertEquals(a.getRecursive(3),7);
	}


	public static void main(String[] args) {
		System.out.println("Running tests.\n");
		testaddFirst();
		addIsEmptySizeTest();
		addRemoveTest();
		testget();
	}
} 