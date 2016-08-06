/**
 * Created by Administrator on 2016/8/6.
 */
package lab8;
import lab8.Map61B;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V>  implements Map61B<K,V>{

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key){
        if (key == null) throw new NullPointerException("argument to contains() is null");
        return get(key)!=null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key){
        return get(root,key);
    }
    private V get(Node x,K key){
        if (x == null){return null;}
        int cmp = key.compareTo(x.key);
        if (cmp>0){return get(x.right,key);}
        else if (cmp<0){return get(x.left,key);}
        else {return x.val;}
    }


    /* Returns the number of key-value mappings in this map. */
    public int size(){return size(root);}
    private int size(Node node){
        if (node == null) return 0;
        else return node.size;
    }


    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value){
        if (key == null) throw new NullPointerException("first argument to put() is null");
        root = put(root,key,value);
    }
    private Node put(Node x,K key,V val){
        if (x ==null){return new Node(key,val,1);}
        int cmp = key.compareTo(x.key);
        if (cmp < 0){ x.left = put(x.left,key,val);}
        else if (cmp > 0){x.right = put(x.right,key,val);}
        else x.val = val;
        x.size = 1 +size(x.left) +size(x.right);
        return x;
    }


    public Node root;
    public class Node{

            private Node left,right;
            private K key;
            private V val;
            private int size;


        public Node(K key,V val,int size){
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }


    /** Removes all of the mappings from this map. */
    public void clear(){
        root = null;
    }

    /* Returns a Set view of the keys contained in this map. */
    public Set<K> keySet(){
        throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key){
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }

    public Iterator<K> iterator(){
        throw new UnsupportedOperationException();
    }
}
