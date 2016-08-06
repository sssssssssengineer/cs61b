/**
 * Created by Administrator on 2016/7/22.
 */
import static org.junit.Assert.*;

import org.junit.Test;
public class ArrayDequeTest {

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

        ArrayDeque<String> lld1 = new ArrayDeque<>();

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

    /** Adds an item, then removes an item, and ensures that dll is empty afterwards.*/
    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
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
    public static void testget() {

        System.out.println("Running get test.");

        ArrayDeque a = new ArrayDeque ();
        //ArrayDeque a=new ArrayDeque();
        a.addFirst(5.3);
        a.addLast("apple");
        //proved that items in the Generic Array don't have to be the same type
        //just like Generic LinkedList
        a.addLast(4.0);
        a.addLast(7.0);
        assertEquals(a.get(1),"apple");
        assertEquals(a.get(3),7.0);
        //System.out.println(a.get(3));
        //System.out.println(a.get(1));
    }
    //@Test
    public static void testaddOutOfsize() {

        System.out.println("Running add out of size test.");

        ArrayDeque a = new ArrayDeque();
        //ArrayDeque a=new ArrayDeque();
        a.addFirst(1.0);
        a.addLast(2.0);
        a.addLast(3.0);
        a.addLast(4.0);
        a.addLast(5.0);
        a.addLast(6.0);
        a.addLast(7.0);
        a.addLast(8.0);
        a.addLast(9.0);
        assertEquals(a.get(8),9.0);
        assertEquals(a.get(0),1.0);
        a.addFirst(10.0);
        a.addFirst(11.0);
        a.addFirst(12.0);
        a.addFirst(13.0);
        a.addFirst(14.0);
        a.addFirst(15.0);
        a.addFirst(16.0);
        a.addFirst(17.0);
        assertEquals(a.get(0),17.0);
        assertEquals(a.get(16),9.0);
        //test out of size removal
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        a.removeLast();
        a.removeLast();
        a.removeLast();
        a.removeLast();
        a.removeLast();
        assertEquals(a.get(0),1.0);
        assertEquals(a.get(3),4.0);
    }


    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        addIsEmptySizeTest();
        addRemoveTest();
        testget();
        testaddOutOfsize();
    }
}
