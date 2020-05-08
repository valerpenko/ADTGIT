import ADT.AbstractPriorityQueue;
import ADT.Entry;
//import ADT.Iterator;
import java.util.Iterator;
import ADT.Position;
import listbased.LinkedBinaryTree;
import listbased.SortedPriorityQueue;

public class C_9_31
{
    public static void main(String[] args)
    {
        LinkedBinaryTreePQ<Integer,String> bt = new LinkedBinaryTreePQ<>(10, "First");
        bt.insert(5, "A");
        bt.insert(20, "B");
        bt.insert(7, "C");
        bt.insert(11, "D");
        bt.insert(3, "E");
        bt.insert(15, "F");
        bt.insert(4, "G");
    }
}
 class LinkedBinaryTreePQ<K,V> extends AbstractPriorityQueue<K,V>//implements PriorityQueue
{
//    protected static class Node<Entry> //Position<E>
//    {
//        //private K key;
//        //private V value; // an element stored at this node     //private E element
//        private Node<K,V> parent; // a reference to the parent node (if any)
//        private Node<K,V> left; // a reference to the left child (if any)
//        private Node<K,V> right; // a reference to the right child (if any)
//
//        // Constructs a node with the given element and neighbors.
//        public Node(K k, V v, Node<K,V> above, Node<K,V> leftChild, Node<K,V> rightChild)
//        {
//            key = k;
//            value = v;
//            parent = above;
//            left = leftChild;
//            right = rightChild;
//        }
//        // accessor methods
//        public K getKey() {return key;}
//        public V getValue() { return value; }    //public E getElement()
//        public Node<K,V> getParent() { return parent; }
//        public Node<K,V> getLeft() { return left; }
//        public Node<K,V> getRight() { return right; }
//        // update methods
//        public void setKey(K k) { key = k; }
//        public void setValue(V v) { value = v; }
//        public void setParent(Node<K,V> parentNode) { parent = parentNode; }
//        public void setLeft(Node<K,V> leftChild) { left = leftChild; }
//        public void setRight(Node<K,V> rightChild) { right = rightChild; }
//    } //----------- end of nested Node class -----------

    LinkedBinaryTree <Entry<K,V>>  lbTree;
    //Position<Entry<K,V>> lastNode;
    LinkedBinaryTree.Node<Entry<K,V>> lastNode;

    public LinkedBinaryTreePQ(K key, V value)
    {
        //lastNode = null;
        lbTree = new  LinkedBinaryTree<>();
        Entry<K, V> newEntry = new PQEntry<>(key, value);
        lbTree.addRoot(newEntry);
        lastNode = (LinkedBinaryTree.Node<Entry<K,V>>)lbTree.root();
    }

    public int size() { return lbTree.size(); }

    public Entry<K,V> insert(K key, V value) {
        Entry<K, V> newEntry = new PQEntry<>(key, value);
        Position<Entry<K, V>> p = lastNode;

//        if (lbTree.isEmpty()) {
//            lbTree.addRoot(newEntry);
//            return  newEntry;
//        }

        if (lbTree.left(lbTree.parent(p))==p ) //1 lastNode is left child
        {
            lbTree.addRight(p, newEntry);
            lastNode = (LinkedBinaryTree.Node<Entry<K,V>>)lbTree.right(p);
        }
        else
        {
            p = findFreeParent(lastNode);
            lbTree.addLeft(p, newEntry);
            lastNode = (LinkedBinaryTree.Node<Entry<K,V>>)lbTree.left(p);
        }
        return newEntry;
    }
    private Position<Entry<K,V>> findFreeParent (Position<Entry<K,V>> lastNode)
    {
        Position<Entry<K, V>> p = lastNode;
        Position<Entry<K, V>> par = lbTree.parent(p);
        while (par!=lbTree.root() && lbTree.right(par) == p)  // && par.right()==p
        {
            p = lastNode;
            par=lbTree.parent(p);
        }
        if (par==lbTree.root())
        {//!!!!
            p = lbTree.left(par);
            while(!lbTree.isExternal(p))
            {p = lbTree.right(p);}
            return p;
        }
        else
        {
            p = lbTree.right(par); //par.right()
            while(!lbTree.isExternal(p))  //p!=external
            {p = lbTree.left(p);} //p.left
            return p;
        }
        //return null;
    }

    private Entry<K,V> minKeyEntry()
    {
        SortedPriorityQueue<K,V> sq = new SortedPriorityQueue<>();
        Iterator<Entry<K,V>> iter = lbTree.iterator();
        if (lbTree.isEmpty())
            return null;
        while (iter.hasNext())
            sq.insert(iter.next().getKey(), iter.next().getValue());
        return sq.min();
    }

    public Entry<K,V> min() { return minKeyEntry(); }

    public Entry<K,V> removeMin()
    {
        return null;
    }
}
