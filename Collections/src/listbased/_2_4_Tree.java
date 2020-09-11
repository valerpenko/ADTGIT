package listbased;

import ADT.AbstractTree;
import ADT.Entry;
import ADT.Position;
import java.util.ArrayList;
import java.util.Iterator;

public class _2_4_Tree<E> extends AbstractTree<E>
{
    private class MWTNode<E> implements Position<ArrayList<E>>
    {
        private MWTNode<E> parent;
        private final int MinChildCount = 2;
        private final int MaxChildCount = 4;
        private ArrayList<E> entries = new ArrayList<>(3);
        private ArrayList<MWTNode<E>> children = new ArrayList<>(4);

        protected E itemSearch(E item) throws Exception
        {
                for(E e: entries)
                {
                    if(entries.contains(item))
                        return e;
                    else
                        throw new Exception("Entry not found");
                }
            return null;
        }
        protected MWTNode<E> childSearch(MWTNode<E> node) throws Exception
        {
            for(MWTNode<E> e: children)
            {
                if(children.contains(node))
                    return e;
                else
                    throw new Exception("Entry not found");
            }

            return null;
        }

        public MWTNode(){}

//        public MWTNode(K k, V v)
//        {
//            entries.put(k,v);
//        }

        public  ArrayList<E> getElement() throws IllegalStateException { return entries; }

        public MWTNode<E> getParent() {return parent;}
        public int numEntries() {return entries.size();}
        public int childrenCount() {return children.size();}

        public ArrayList<MWTNode<E>> getChildren(){return children;}
        public E getEntry(E item) throws Exception {return itemSearch(item);}
        public MWTNode<E> getChild(MWTNode<E> node) throws Exception {return childSearch(node);}

        public void setParent(MWTNode<E> parentNode) { parent = parentNode; }
        public void addEntry(K key, V value){entries.put(key, value);}
        public void addChild(K key, V value){children.put(key, value);}

    }

    private MWTNode<E> root = null;
    private int size = 0;

    public _2_4_Tree(){}

    protected MWTNode<E> validate(Position<ArrayList<E>> p) throws IllegalArgumentException
    {
        if (!(p instanceof MWTNode))
            throw new IllegalArgumentException("Not valid position type");
        MWTNode<E> node = (MWTNode<E>) p; // safe cast
        if (node.getParent() == node) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        //check range requirements
        return node;
    }

    private K findKey(K key) throws Exception
    {
        MWTNode<Entry<K,V>> curNode = root;
        while(true)
        {
            if((curNode.itemSearch(key) ).getKey() == key)
                return key;
            else if(isExternal(curNode))
                return null;
            else
                curNode = curNode.getChild(key);
        }
    }

    private void split(Position<ArrayList<E>> node){}

    private void fusion(Position<ArrayList<E>> node1, Position<ArrayList<E>> node2){}

    public int size() { return size; }

    public Position<E> root() { return (Position<E>) root; }

    public Position<E> parent(Position<E> p) throws IllegalArgumentException
    {
        MWTNode<E> node = validate((Position<ArrayList<E>>) p);
        return (Position<E>) node.getParent();
    }

    public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException
    {
        MWTNode<E> node = validate((Position<ArrayList<E>>) p);
        return (Iterable<Position<E>>) node.getChildren();
    }

    public int numChildren(Position<E> p) throws IllegalArgumentException
    {
        MWTNode<E> node = validate((Position<ArrayList<E>>) p);
        return node.childrenCount();
    }

    public Position<ArrayList<E>> insert(K key, V value)
    {
        if (isEmpty())
        {
            root = new MWTNode<>(key,value);
            size = 1;
            return root;
        }
    }

    public void delete(Position<Position<ArrayList<E>>> pos)
    {}

    private class ElementIterator implements Iterator<ArrayList<E>>
    {
        Iterator<Position<E>> posIterator = positions().iterator();
        public boolean hasNext() { return posIterator.hasNext(); }
        public ArrayList<E> next() { return (ArrayList<E>) posIterator.next().getElement(); } // return element!
        public void remove() { posIterator.remove(); }
    }

    public Iterator<E> iterator() { return (Iterator<E>) new ElementIterator(); }

    public Iterable<Position<E>> positions() { return preorder(); }
}
