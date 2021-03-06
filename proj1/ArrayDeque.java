/**
 * Created by Administrator on 2016/7/22.
 */
public class ArrayDeque<Item> {
    private int size = 8;//initial spaces
    private int numbers=0;//number of items
    private int nextFirst;
    private int nextLast;
    private Item[] items;

    //: Creates an empty linked list deque.
    //this runs when the LinkedListDeque class is instantiated.
    public ArrayDeque() {
        items=(Item[])new Object[8];//different when instantiating a generic array.
        nextFirst=0;
        nextLast=1;
    }

    //add out of size
    public Item[] reSize() {
        int inisize=size;
        size *= 2;//final size
        Item[] newitems = (Item[]) new Object[size];
        nextLast += 1;
        int i = nextLast;
        //nextFirst, not final yet

        while (nextLast != nextFirst + inisize) {
            if (i >= inisize) {
                i -= inisize;
            }
            if (i<0){i+=inisize;}
            newitems[nextLast] = items[i];
            i += 1;

            nextLast += 1;
        }
        return newitems;
    }
    //: Adds an item to the front of the Deque.
    public void addFirst(Item itm) {
        if (nextFirst==nextLast){
            items=reSize();//final items
            }
            items[nextFirst]=itm;
            nextFirst=nextFirst-1;//final nextFirst
            if (nextFirst<0){nextFirst+=size;}
            numbers+=1;//final numbers
    }


    //: Adds an item to the back of the Deque.
    public void addLast(Item itm) {
        if (nextFirst==nextLast){
            items=reSize();//final items
        }
            items[nextLast] = itm;
            nextLast = nextLast + 1;
            if (nextLast >= size) {
                nextLast -= size;
            }
            numbers += 1;
    }


    //: Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        if (numbers==0){
            return true;
        }
        else {return false;}
    }

    //: Returns the number of items in the Deque.
    public int size() {
        return numbers;
    }

    //: Prints the items in the Deque from first to last, separated by a space.
    public void printDeque() {
        int i=nextFirst+1;
        while (i!=nextLast){
            if (i>=size){i=i-size;}
            System.out.print(items[i]+" ");
            i+=1;
        }
    }

    //resize when memory is not effeciently used
    public Item[] reSizeSmaller(){
        if (size/numbers<4){
            return items;
        }
        else {
            int inisize = size;
            if (size / 2 >= 8) {
                size /= 2;
            } else {
                size = 8;
            }//final size

            Item[] newitems = (Item[]) new Object[size];
            int i = nextFirst+1;
            nextFirst = 0;
            nextLast = 1;

            while (nextLast != numbers+1) {
                if (i >= inisize) {
                    i -= inisize;
                }
                if (i < 0) {
                    i += inisize;
                }
                newitems[nextLast] = items[i];
                i += 1;
                nextLast += 1;
            }
            return newitems;
        }
    }
    //: Removes and returns the item at the front of the Deque.
    // If no such item exists, returns null.
    public void removeFirst() {
        items=reSizeSmaller();
        nextFirst+=1;
        if (nextFirst>=size){nextFirst-=size;}
        items[nextFirst]=null;
        numbers-=1;
    }

    //: Removes and returns the item at the back of the Deque.
    // If no such item exists, returns null.
    public void removeLast() {
        items=reSizeSmaller();
        nextLast-=1;
        if (nextLast<0){nextLast+=size;}
        items[nextLast]=null;
        numbers-=1;
    }

    //: Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    // If no such item exists, returns null. Must not alter the deque!
    public Item get(int index) {
        if (index>numbers-1){return null;}
        int i=0;
        int m=nextFirst+1;
        if(m>=size){m-=size;}
        while (i!=index){
            i+=1;
            m+=1;
            if(m>=size){m-=size;}
        }
        return items[m];
    }
}
