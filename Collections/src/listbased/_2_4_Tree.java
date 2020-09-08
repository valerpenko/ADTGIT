package listbased;

import ADT.AbstractTree;
import ADT.Entry;
import ADT.Position;
import arraybased.SortedTableMap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedMap;

public class _2_4_Tree<K,V> extends AbstractTree<SortedMap<K,V>>
{
    private class MWTNode<E> implements Position<ArrayList<E>>
    {
        private MWTNode<E> parent;
        private final int MinChildCount = 2;
        private final int MaxChildCount = 4;
        private ArrayList<E> entries = new ArrayList<>(3);
        private ArrayList<E> children = new ArrayList<>();

        protected Entry <K,V> entrySearch(K key, char ch) throws Exception
        {
            if(ch == 'e')
            {
                for(Entry <K,V> e: entries.entrySet())
                {
                    if(e.getKey() == key)
                        return e;
                    else if(e.getKey() == null)
                        throw new Exception("Entry not found");
                }
            }
            else if(ch == 'c')
            {
                for(Entry <K,V> e: children.entrySet())
                {
                    if(e.getKey() == key)
                        return e;
                    else if(e.getKey() == null)
                        throw new Exception("Entry not found");
                }
            }
            return null;
        }


        public MWTNode(){}

        public MWTNode(K k, V v){entries.put(k,v);}

        public SortedMap<K,V> getElement() throws IllegalStateException { return (SortedMap<K,V>) entries; }

        public MWTNode<K,V> getParent() {return parent;}
        public int numEntries() {return entries.size();}
        public int childrenCount() {return children.size();}

        public SortedMap<K,V> getChildren(){return (SortedMap<K,V>) children;}
        public Entry <K,V> getEntry(K key) throws Exception {return itemSearch(key,'e');}
        public Entry <K,V> getChild(K key) throws Exception {return itemSearch(key,'c');}

        public void setParent(MWTNode<K,V> parentNode) { parent = parentNode; }
        public void addEntry(K key, V value){entries.put(key, value);}
        public void addChild(K key, V value){children.put(key, value);}

    }

    private MWTNode<K,V> root = null;
    private int size = 0;

    public _2_4_Tree(){}

    protected MWTNode<K,V> validate(Position<SortedMap<K,V>> p) throws IllegalArgumentException
    {
        if (!(p instanceof MWTNode))
            throw new IllegalArgumentException("Not valid position type");
        MWTNode<K,V> node = (MWTNode<K,V>) p; // safe cast
        if (node.getParent() == node) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        //check range requirements
        return node;
    }

    private K find(K key) throws Exception
    {
        MWTNode<K,V> curNode = root;
        while(true)
        {
            if((curNode.itemSearch(key,'e') ).getKey() == key)
                return key;
            else if(isExternal(curNode))
                return null;
            else
                curNode = curNode.getChild(key);
        }
    }

    private void split(Position<SortedMap<K,V>> node){}

    private void fusion(Position<SortedMap<K,V>> node1, Position<SortedMap<K,V>> node2){}

    public int size() { return size; }

    public Position<SortedMap<K,V>> root() { return root; }

    public Position<SortedMap<K,V>> parent(Position<SortedMap<K,V>> p) throws IllegalArgumentException
    {
        MWTNode<K,V> node = validate(p);
        return node.getParent();
    }

    public Iterable<Position<SortedMap<K,V>>> children(Position<SortedMap<K, V>> p) throws IllegalArgumentException
    {
        MWTNode<K,V> node = validate(p);
        return (Iterable<Position<SortedMap<K, V>>>) node.getChildren();
    }

    public int numChildren(Position<SortedMap<K,V>> p) throws IllegalArgumentException
    {
        MWTNode<K,V> node = validate(p);
        return node.childrenCount();
    }

    public Position<SortedMap<K,V>> insert(K key, V value)
    {
        if (isEmpty())
        {
            root = new MWTNode<>(key,value);
            size = 1;
            return root;
        }
    }

    public void delete(Position<SortedMap<K,V>> pos)
    {}

    private class ElementIterator implements Iterator<SortedMap<K,V>>
    {
        Iterator<Position<SortedMap<K,V>>> posIterator = positions().iterator();
        public boolean hasNext() { return posIterator.hasNext(); }
        public SortedMap<K,V> next() { return posIterator.next().getElement(); } // return element!
        public void remove() { posIterator.remove(); }
    }

    public Iterator<SortedMap<K, V>> iterator() { return new ElementIterator(); }

    public Iterable<Position<SortedMap<K, V>>> positions() { return preorder(); }
}
