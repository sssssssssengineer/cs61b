/**
 * Created by Administrator on 2016/7/22.
 */
import static org.junit.Assert.*;
import org.junit.Test;
public class TestFlik {

    @Test
    public void testFlik() {

        assertTrue(Flik.isSameNumber(3, 3));
        assertTrue(Flik.isSameNumber(127, 127));
        assertTrue(Flik.isSameNumber(128, 128));
        assertTrue(Flik.isSameNumber(129, 129));
    }
}
