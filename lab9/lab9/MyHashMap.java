/**
 * Created by Administrator on 2016/8/7.
 */
package lab9;
import lab9.Map61B;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K,V> {
    /** Keys and values are stored in a linked list of Entry objects.
     *  This variable stores the first pair in this linked list. */
    private Entry list;
    private HashSet hskeys;
    private ArrayList<Entry> arKV;
    private int Initcapcity = 5;
    private int m;// hash table size
    private int n;// number of key-value pairs

    public MyHashMap(){
        this(Initcapcity);
    }
    public MyHashMap(int Size){
        hskeys = new HashSet(Size);
        arKV = new ArrayList<Entry>(Size);
        m = Size;
    }
    public MyHashMap(int initialSize, float loadFactor){
        //hskeys = new HashSet(initialSize,loadFactor);

    }

    /** Removes all of the mappings from this map. */
    public void clear(){
        m = 0;
        n = 0;
        hskeys = null;
        arKV = null;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key){
        return hskeys.contains(key)==true;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key){

    }

    // hash value between 0 and m-1
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }
    /* Returns the number of key-value mappings in this map. */
    public int size(){return n;}

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value){
        hskeys.add(key);
        int i = hash(key);
        arKV.add(i,new Entry(key,value,null));
    }

    /* Returns a Set view of the keys contained in this map. */
    Set<K> keySet();

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    V remove(K key);

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    V remove(K key, V value);

    /** Represents one node in the linked list that stores the key-value pairs
     *  in the dictionary. */
    private class Entry {

        /** Stores KEY as the key in this key-value pair, VAL as the value, and
         *  NEXT as the next node in the linked list. */
        Entry(K k, V v, Entry n) {
            key = k;
            val = v;
            prev = n;
        }

        /** Returns the Entry in this linked list of key-value pairs whose key
         *  is equal to KEY, or null if no such Entry exists. */
        Entry get(K k) {
            if (k != null && k.equals(key)) {
                return this;
            }/**not done yet*/
            return prev.get(k);
        }

        /** Stores the key of the key-value pair of this node in the list. */
        K key;
        /** Stores the value of the key-value pair of this node in the list. */
        V val;
        /** Stores the next Entry in the linked list. */
        Entry prev;

    }
}
