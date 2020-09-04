package listbased;

import ADT.AbstractTree;
import ADT.Entry;
import ADT.Position;
import arraybased.SortedTableMap;

import java.util.Iterator;
import java.util.SortedMap;

public class _2_4_Tree<K,V> extends AbstractTree<SortedMap<K,V>>
{
    private class MWTNode<K,V> implements Position<SortedMap<K,V>>
    {
        private MWTNode<K,V> parent;
        private final int MinChildCount = 2;
        private final int MaxChildCount = 4;
        private SortedTableMap<K,V> entries = new SortedTableMap<>();
        private SortedTableMap<K,V> children = new SortedTableMap<>();
        protected Entry <K,V> itemSearch(K key, char ch) throws Exception
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

    public Iterator<SortedMap<K, V>> iterator() {
        return null;
    }

    public Iterable<Position<SortedMap<K, V>>> positions() {
        return null;
    }
}
