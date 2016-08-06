/**
 * Created by Administrator on 2016/7/25.
 */
package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;
import java.util.List;
public class TestClorus {
    /* Replace with the magic word given in lab.
     * If you are submitting early, just put in "early" */
    public static final String MAGIC_WORD = "";

    @Test
    public void testStay() {
        Clorus p1 = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded1 = new HashMap<Direction, Occupant>();
        surrounded1.put(Direction.TOP, new Plip(1));
        surrounded1.put(Direction.BOTTOM, new Plip(1));
        surrounded1.put(Direction.LEFT, new Impassible());
        surrounded1.put(Direction.RIGHT, new Impassible());

        Action actual1 = p1.chooseAction(surrounded1);
        List<Direction> empties1 = p1.getNeighborsOfType(surrounded1, "empty");
        Direction moveDir1 = empties1.get(0);

        Action expected1 = new Action(Action.ActionType.STAY);

        assertEquals(expected1, actual1);
    }

    public void testAttack() {
        Clorus p1 = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded1 = new HashMap<Direction, Occupant>();
        surrounded1.put(Direction.TOP, new Empty());
        surrounded1.put(Direction.BOTTOM, new Plip(1));
        surrounded1.put(Direction.LEFT, new Impassible());
        surrounded1.put(Direction.RIGHT, new Impassible());

        Action actual1 = p1.chooseAction(surrounded1);
        List<Direction> empties1 = p1.getNeighborsOfType(surrounded1, "plip");
        Direction moveDir1 = empties1.get(0);

        Action expected1 = new Action(Action.ActionType.ATTACK,moveDir1);

        assertEquals(expected1, actual1);

        Clorus p = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Empty());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = p.chooseAction(surrounded);
        List<Direction> empties = p.getNeighborsOfType(surrounded, "empty");
        Direction moveDir = empties.get(0);

        Action expected = new Action(Action.ActionType.REPLICATE,moveDir);

        assertEquals(expected, actual);
    }

    @Test
    public void testMove() {
        Clorus p1 = new Clorus(0.9);
        HashMap<Direction, Occupant> surrounded1 = new HashMap<Direction, Occupant>();
        surrounded1.put(Direction.TOP, new Empty());
        surrounded1.put(Direction.BOTTOM, new Impassible());
        surrounded1.put(Direction.LEFT, new Impassible());
        surrounded1.put(Direction.RIGHT, new Impassible());

        Action actual1 = p1.chooseAction(surrounded1);
        List<Direction> empties1 = p1.getNeighborsOfType(surrounded1, "empty");
        Direction moveDir1 = empties1.get(0);

        Action expected1 = new Action(Action.ActionType.MOVE,moveDir1);

        assertEquals(expected1, actual1);
    }
    public static void main(String[] args) {
        System.exit(jh61b.junit.textui.runClasses(TestPlip.class));
    }
}

