/**
 * Created by Administrator on 2016/7/24.
 */
public interface Deque<Item> {
    void addFirst(Item itm);
    void addLast(Item itm);
    boolean isEmpty();
    int size();
    void printDeque();
    Item removeFirst();
    Item removeLast();
    Item get(int index);
}
