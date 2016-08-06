// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T>{
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        first = 0;
        last = 0;
        fillCount = 0;
        rb=(T[]) new Object[capacity];
        this.capacity= capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        rb[last]=x;
        last+=1;
        if (last==capacity){last=0;}
        fillCount+=1;
        if (fillCount>capacity){throw new RuntimeException("Ring buffer overflow");}

    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        T a = rb[first];
        rb[first]=null;
        first+=1;
        if (first==capacity){first=0;}
        fillCount-=1;
        if (fillCount<0){throw new RuntimeException("Ring buffer underflow");}
        return a;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        if (fillCount==0){throw new RuntimeException("empty");}
        return rb[first];
    }

    private class Itr implements Iterator<T>{
        private int notionOfWhereHeIs;

        public Itr() {
            notionOfWhereHeIs = first;
        }

        public boolean hasNext() {
            return (rb[notionOfWhereHeIs+1] != null);
        }

        public T next() {
            T currentThing = rb[notionOfWhereHeIs];
            notionOfWhereHeIs += 1;
            return currentThing;
        }
    }
    public Itr iterator() {return new Itr();}
    // TODO: When you get to part 5, implement the needed code to support iteration.
    public String checkiterator(){
        if (peek()==null){return "empty";}
        else {
            Itr itr =iterator();
            String prt = "";
            while (itr.hasNext()){prt +=itr.next();}
            return prt;
        }
    }
}
