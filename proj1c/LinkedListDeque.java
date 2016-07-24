/**
 * Created by Administrator on 2016/7/22.
 */
public class LinkedListDeque<Item> implements Deque<Item>{
    private int size = 0;
    public List sentinel;
    private List P;//pointer   delete

    //: Creates an empty linked list deque.
    //this runs when the LinkedListDeque class is instantiated.
    public LinkedListDeque() {
        sentinel = new List(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        P = sentinel;//delete
    }

    //}
    //create list
    public class List {
        public List next;
        public List prev;
        public Item items;

        //add an item at the specified place.
        public List(Item itm, List p, List n) {
            items = itm;
            prev = p;
            next = n;
        }
    }

    //: Adds an item to the front of the Deque.
    @Override
    public void addFirst(Item itm) {
        List pointer = sentinel.next;
        List first = new List(itm, sentinel, sentinel.next);
        sentinel.next = first;
        pointer.prev = first;
        size += 1;
    }

    //: Adds an item to the back of the Deque.
    @Override
    public void addLast(Item itm) {
        List pointer = sentinel.prev;
        List last = new List(itm, sentinel.prev, sentinel);
        sentinel.prev = last;
        pointer.next = last;
        size += 1;
    }


    //: Returns true if deque is empty, false otherwise.
    @Override
    public boolean isEmpty() {
        if (sentinel.next.items == null) {
            return true;
        } else {
            return false;
        }
    }

    //: Returns the number of items in the Deque.
    @Override
    public int size() {
        return size;
    }

    //: Prints the items in the Deque from first to last, separated by a space.
    @Override
    public void printDeque() {
        int i = 0;
        List pointer = sentinel.next;
        while (i != size) {
            System.out.print(pointer.items + " ");
            i += 1;
        }
    }

    //: Removes and returns the item at the front of the Deque.
    // If no such item exists, returns null.
    @Override
    public Item removeFirst() {
        if (sentinel.next == null) {
            return null;
        }
        List pointer = sentinel.next.next;
        Item a = sentinel.next.items;
        sentinel.next.items = null;
        sentinel.next = pointer;
        pointer.prev = sentinel;
        size -= 1;
        return a;
    }

    //: Removes and returns the item at the back of the Deque.
    // If no such item exists, returns null.
    @Override
    public Item removeLast() {
        if (sentinel.prev == null) {
            return null;
        }
        List pointer = sentinel.prev.prev;
        Item a = sentinel.prev.items;
        sentinel.prev.items = null;
        sentinel.prev = pointer;
        pointer.next = sentinel;
        size -= 1;
        return a;
    }

    //: Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    // If no such item exists, returns null. Must not alter the deque!
    @Override
    public Item get(int index) {
        //iteratively
        if (index > size - 1) {
            return null;
        }
        List pointer = sentinel.next;
        int i = 0;
        while (i != index) {
            pointer = pointer.next;
            i+=1;
        }
        return pointer.items;
    }

    //: Same as get, but uses recursion.
    public Item getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }
        P = P.next;
        if (index!=0) {
            return getRecursive(index-1);
        } else {
            return P.items;
        }
    }
}
