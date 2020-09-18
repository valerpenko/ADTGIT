package listbased;

import ADT.AbstractTree;
import ADT.DefaultComparator;
import ADT.Position;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator;

public class _2_4_Tree<E extends Comparable<E>> extends AbstractTree<E>
{
    private class MWTNode<E extends Comparable<E>> implements Position<ArrayList<E>>
    {
        private MWTNode<E> parent;
        private static final int MinChildCount = 2;
        private static final int MaxChildCount = 4;
        private ArrayList<E> entries = new ArrayList<>(3);
        private ArrayList<MWTNode<E>> children = new ArrayList<>(4);

        protected boolean itemSearch(E item) throws Exception
        {
                for(E e: entries)
                {
                    if(entries.contains(item))
                        return true;
                }
            return false;
        }
        protected MWTNode<E> childSearch(E entry) throws Exception
        {
            int childNum=0;
            while( childNum< entries.size() && entries.get(childNum).compareTo(entry)==-1)
                childNum++;
            return children.get(childNum);
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
        //public MWTNode<E> getChild(MWTNode<E> node) throws Exception {return childSearch(node);}

        public void setParent(MWTNode<E> parentNode) { parent = parentNode; }

        public void addEntry(E entry) throws Exception
        {
            Comparator<E> comp = new DefaultComparator<>();

            if (numEntries()<3)
            {
                //!!!  sorted order
                entries.add(entry);
                entries.sort(comp);
            }
            else
                throw new Exception();
        }

        public void addChild(MWTNode<E> node) {children.add(node);}

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

    private MWTNode<E> findEntry(E entry) throws Exception
    {
        MWTNode<E> curNode = root;
        while (curNode.childrenCount()>0)    ///childrenCount!!!
        {
            if (curNode.itemSearch(entry)) return true;
            else {
                curNode = curNode.childSearch(entry);
            }
        }
    }

    // splitAndLift performs after insertion of the entry in case when node consists of 4 entries
    private void splitAndLift(MWTNode <E> node, E entry) throws Exception {
        //split

        //prepare 2 new splitted nodes
        MWTNode<E> node1 = new MWTNode<>();
        MWTNode<E> node2 = new MWTNode<>();
        E entryToLift;

        node1.addEntry(node.entries.get(0));
        node1.addEntry(node.entries.get(1));
        entryToLift=node.entries.get(2);
        node2.addEntry(node.entries.get(3));

        //get parent
        try
        {
            MWTNode<E> parent =node.getParent();
            parent.addEntry(entryToLift);
            int insertionPoint = parent.children.indexOf(node);
            parent.children.remove(node);

            parent.children.add(insertionPoint,node1);
            parent.children.add(insertionPoint+1,node2);

            if (parent.children.size()> MWTNode.MaxChildCount)
            {
                splitAndLift(parent, parent.entries.get(2));
            }

        }
        catch()
        {// create new root



        }
        node1.addEntry(node.entries.get(0));
        node1.addEntry(node.entries.get(1));
        node1.addEntry(node.entries.get(3));
        //lift

    }

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

    public Position<ArrayList<E>> insert(E entry) throws Exception {
        if (isEmpty())
        {
            root = new MWTNode<>();
            root.addEntry(entry);
            size = 1;
            return root;
        }
        else
        {
            MWTNode<E> curNode = findEntry(entry);
            try
            {
                curNode.addEntry(entry);
            }
            catch (Exception e)
            {
                splitAndLift(curNode, entry);
            }
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
