import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Administrator on 2016/7/23.
 */
public class TestArrayDeque1B<Integer> {
    @Test
    public void test1() {
        FailureSequence fs = new FailureSequence();
        StudentArrayDeque<java.lang.Integer> sad1 = new StudentArrayDeque<java.lang.Integer>();
        DequeOperation dequeOp1 = new DequeOperation("isEmpty");
        DequeOperation dequeOp2 = new DequeOperation("addFirst", 5);
        DequeOperation dequeOp3 = new DequeOperation("addFirst", 10);
        DequeOperation dequeOp4 = new DequeOperation("addLast", 14);
        DequeOperation dequeOp5 = new DequeOperation("removeFirst");
        DequeOperation dequeOp6 = new DequeOperation("removeFirst");
        DequeOperation dequeOp7 = new DequeOperation("removeFirst");
        DequeOperation dequeOp8 = new DequeOperation("removeFirst");
        DequeOperation dequeOp9 = new DequeOperation("size");

        fs.addOperation(dequeOp1);
        sad1.isEmpty();

        boolean expected=true;
        boolean actual=sad1.isEmpty();
        assertEquals(fs.toString()+"Oh noooo!\nThis is bad:\n  " + actual
                        + " not equal to " + expected + "!",
                expected, actual);

        fs.addOperation(dequeOp2);
        fs.addOperation(dequeOp3);
        fs.addOperation(dequeOp4);
        fs.addOperation(dequeOp5);
        fs.addOperation(dequeOp6);
        fs.addOperation(dequeOp7);
        fs.addOperation(dequeOp8);
        fs.addOperation(dequeOp9);

        sad1.addFirst(5);
        sad1.addFirst(10);
        sad1.addLast(14);
        sad1.removeFirst();
        sad1.removeFirst();
        sad1.removeFirst();
        sad1.removeFirst();
        sad1.size();

        int expected1=0;
        int actual1=sad1.size();
        assertEquals(fs.toString()+"Oh noooo!\nThis is bad:\n   expected " + expected1
                        + " not equal to actual " + actual1 + "!",
                expected1, actual1);

    }
    public static void main(String[] args) {

        jh61b.junit.TestRunner.runTests(TestArrayDeque1B.class);


    }
}
