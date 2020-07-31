package listbased;

import ADT.Entry;
import ADT.Map;

import java.util.Random;

public class SkipList <K,V> implements Map<K,V>
{
    private static class SLentry<K,V> implements Entry<K,V>
    {
        private K key;
        private V value;

        private SLentry<K,V> up, down, left, right;

        public SLentry(K k, V v, SLentry<K,V> up, SLentry<K,V> down, SLentry<K,V> left, SLentry<K,V> right)
        {
            key = k;
            value = v;
            this.up = up;
            this.down = down;
            this.left = left;
            this.right = right;
        }
        public K getKey() { return key; }
        public V getValue() { return value; }

        public SLentry<K,V> leftEntry(){return left;}
        public SLentry<K,V> rightEntry(){return right;}
        public SLentry<K,V> upperEntry(){return up;}
        public SLentry<K,V> lowerEntry(){return down;}

        public void setLeft(SLentry<K,V> sle){left = sle;}
        public void setRight(SLentry<K,V> sle){right = sle;}
        public void setUpper(SLentry<K,V> sle){up = sle;}
        public void setLower(SLentry<K,V> sle){down = sle;}

        public void setKey(K k) { key = k; }
        public V setValue(V v)
        {
            V old = value;
            value = v;
            return old;
        }
    }

    private SLentry<K,V> head;
    private SLentry<K,V> tail;
    private int size = 0;
    private Random rnd;

    public SkipList()
    {
        head = new SkipList.SLentry<>(null, null, null, null, null, null);
        tail = new SkipList.SLentry<>(null, null, null, null, head, null);
        head.setRight(tail);
        rnd = new Random();
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }
}
