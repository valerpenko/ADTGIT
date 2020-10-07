package listbased;

import ADT.AbstractTree;
import ADT.DefaultComparator;
import ADT.Position;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator;
import java.util.List;

public class _2_4_Tree<E extends Comparable<E>> //extends AbstractTree<E>
{
    private class MWTNode<E extends Comparable<E>>// implements Position<ArrayList<E>>
    {
        private MWTNode<E> parent;
        private static final int MinChildCount = 2;
        private static final int MaxChildCount = 4;
        private ArrayList<E> entries = new ArrayList<>(3);
        private ArrayList<MWTNode<E>> children = new ArrayList<>(4);

        protected boolean itemSearch(E item)
        {
                for(E e: entries)
                {
                    if(entries.contains(item))
                        return true;
                }
            return false;
        }
        protected MWTNode<E> childSearch(E entry)
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
        public E getEntry(E item) throws Exception
        {
            if (itemSearch(item)) return item;
            else throw new Exception("Entry not found");
        }
        //public MWTNode<E> getChild(MWTNode<E> node) throws Exception {return childSearch(node);}

        public void setParent(MWTNode<E> parentNode) { parent = parentNode; }

        public void addEntry(E entry) throws Exception
        {
            Comparator<E> comp = new DefaultComparator<>();
            //!!!  sorted order
            entries.add(entry);
            entries.sort(comp);
            if (numEntries()>3)
                throw new Exception();
        }

        public void addChild(MWTNode<E> node) {children.add(node);}

    }

    //////////////////////////////////////////////////////////////////////

    private MWTNode<E> root = null;
    private int size = 0;

    public _2_4_Tree(){}

    protected MWTNode<E> validate(MWTNode<E> p) throws IllegalArgumentException
    {
        if (p == null)
            throw new IllegalArgumentException("Not valid position type");
        if (p.getParent() == p) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        //check range requirements
        return p;
    }

    private MWTNode<E> findEntry(E entry, MWTNode<E> node) throws Exception
    ///!!!рекурсия
    /// если не найдет - возвращает узел, на котором поиск завершился (туда будет добавляться entry)
    {
        MWTNode<E> curNode = node;
        while (curNode.childrenCount()>0)    ///childrenCount!!!
        {
            if (curNode.itemSearch(entry)) return  curNode;
            else curNode = curNode.childSearch(entry);
        }
        return curNode;
        //throw new Exception();
    }

    // splitAndLift performs after insertion of the entry in case when node consists of 4 entries
    private  MWTNode<E> splitAndLift(MWTNode <E> node, E entry) throws Exception
    {
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
        MWTNode<E> parent=new MWTNode<>(); //for new root
        if (node==root)
        {
            //parent here will be the new root
            parent.children.add(node1);
            parent.children.add(node2);
            parent.addEntry(entryToLift);
            root=parent;
            size+=1;
        }
        else
        {
            try
            {
                parent = node.getParent();

                int insertionPoint = parent.children.indexOf(node);
                parent.children.remove(node);

                parent.children.add(insertionPoint, node1);
                parent.children.add(insertionPoint + 1, node2);

                parent.addEntry(entryToLift);
            }
            catch(Exception e)
            {
                splitAndLift(parent, parent.entries.get(2));
            }
        }
        return findEntry(entry,root);
        ///it is tremendously ineffective search trick!!!
        ///instead of defining needed node between small number (probably 3) of nodes
        //we run full tree search
        //Task: given nodes parent, node1, node2 consider and return node containing entry
    }

    private void fusion(Position<ArrayList<E>> node1, Position<ArrayList<E>> node2){}

    public int size() { return size; }

    public MWTNode<E> root() { return root; }

    public MWTNode<E> parent(MWTNode<E> p) throws IllegalArgumentException
    {
        MWTNode<E> node = validate(p);
        return node.getParent();
    }

    public Iterable<MWTNode<E>> children(MWTNode<E> p) throws IllegalArgumentException
    {
        MWTNode<E> node = validate(p);
        return node.getChildren();
    }

    public int numChildren(MWTNode<E> p) throws IllegalArgumentException
    {
        MWTNode<E> node = validate(p);
        return node.childrenCount();
    }

    public MWTNode<E> insert(E entry) throws Exception
    //if entry already presents in tree
    // insertion is not comopleted, method returns node containing entry
    //Otherwise insertion specified for 2_4_trees is completed
    // and method returns tree node containing entry after its insertion
    {
        if (this.size == 0)
        {
            root = new MWTNode<>();
            root.addEntry(entry);
            size = 1;
            return root;
        }
        else
        {
            MWTNode<E> curNode = findEntry(entry, root());
            for (E el:curNode.entries)
            {
                if(el==entry)
                {
                    return curNode;
                }
            }
            //we need perform insertion
            try
            {
                curNode.addEntry(entry);
                return curNode;
            }
            catch (Exception e)
            {
                size+=1;
                return splitAndLift(curNode, entry);
            }
        }
    }

    public void delete(Position<Position<ArrayList<E>>> pos)
    {}

    // Adds positions of the subtree rooted at Position p to the given snapshot.
    private void preorderSubtree(MWTNode<E> p, List<MWTNode<E>> snapshot)
    {
        snapshot.add(p); // for preorder, we add position p before exploring subtrees
        for (MWTNode<E> c : children(p))
            preorderSubtree(c, snapshot);
    }

    // Returns an iterable collection of positions of the tree, reported in preorder.
    public Iterable<MWTNode<E>> preorder()
    {
        List<MWTNode<E>> snapshot = new ArrayList<>();
        if (this.size != 0)
            preorderSubtree(root(), snapshot); // fill the snapshot recursively
        return snapshot;
    }

    private class ElementIterator implements Iterator<ArrayList<E>>
    {
        Iterator<MWTNode<E>> posIterator = positions().iterator();
        public boolean hasNext() { return posIterator.hasNext(); }
        public ArrayList<E> next() { return posIterator.next().getElement(); } // return element!
        public void remove() { posIterator.remove(); }
    }

    public Iterator<ArrayList<E>> iterator() { return new ElementIterator(); }

    public Iterable<MWTNode<E>> positions() { return preorder(); }
}
