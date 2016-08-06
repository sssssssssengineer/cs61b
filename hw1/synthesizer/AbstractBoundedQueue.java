package synthesizer;
import java.util.Queue;

/**
 * Created by Administrator on 2016/7/26.
 */
public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;
    public int capacity(){
        return capacity;
    }
    public int fillCount(){
        return fillCount;
    }


}

