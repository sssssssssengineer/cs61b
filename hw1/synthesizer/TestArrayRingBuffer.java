package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        arb.isEmpty();
        assertEquals(arb.isEmpty(),true);
        arb.enqueue(3);
        arb.isEmpty();
        assertEquals(arb.isEmpty(),false);
        arb.enqueue(5);
        assertEquals(arb.peek(),3);

            ArrayRingBuffer arb2 = new ArrayRingBuffer(10);
            arb2.isEmpty();
            assertEquals(arb2.isEmpty(),true);
            arb2.enqueue(3);
            assertEquals(arb2.checkiterator(),"");
            arb2.enqueue(5);
            assertEquals(arb2.checkiterator(),"3");

        }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
