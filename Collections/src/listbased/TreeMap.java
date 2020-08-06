package listbased;

import ADT.AbstractSortedMap;
import ADT.Entry;
import ADT.Position;
import java.util.ArrayList;
import java.util.Comparator;

public class TreeMap<K,V> extends AbstractSortedMap<K,V>
{
    // A specialized version of LinkedBinaryTree with support for balancing.
    protected static class BalanceableBinaryTree<K,V> extends LinkedBinaryTree<Entry<K,V>>
    {
        //-------------- nested BSTNode class --------------
        // this extends the inherited LinkedBinaryTree.Node class
        protected static class BSTNode<E> extends Node<E>
        {
            int aux=0;
            BSTNode(E e, Node<E> parent, Node<E> leftChild, Node<E> rightChild)
            {
                super(e, parent, leftChild, rightChild);
            }
            public int getAux() { return aux; }
            public void setAux(int value) { aux = value; }
        } //--------- end of nested BSTNode class ---------

        // positional-based methods related to aux field
        public int getAux(Position<Entry<K,V>> p){ return ((BSTNode<Entry<K,V>>) p).getAux(); }

        public void setAux(Position<Entry<K,V>> p, int value) { ((BSTNode<Entry<K,V>>) p).setAux(value); }

        // Override node factory function to produce a BSTNode (rather than a Node)
        protected Node<Entry<K,V>> createNode(Entry<K,V> e, Node<Entry<K,V>> parent, Node<Entry<K,V>> left, Node<Entry<K,V>> right)
        {
            return new BSTNode<>(e, parent, left, right);
        }

        // Relinks a parent node with its oriented child node.
        private void relink(Node<Entry<K,V>> parent, Node<Entry<K,V>> child, boolean makeLeftChild)
        {
            child.setParent(parent);
            if (makeLeftChild)
                parent.setLeft(child);
            else
            parent.setRight(child);
        }
        // Rotates Position p above its parent.
        public void rotate(Position<Entry<K,V>> p)
        {
            Node<Entry<K,V>> x = validate(p);
            Node<Entry<K,V>> y = x.getParent(); // we assume this exists
            Node<Entry<K,V>> z = y.getParent(); // grandparent (possibly null)
            if (z == null)
            {
                root = x; // x becomes root of the tree
                x.setParent(null);
            }
            else
                relink(z, x, y == z.getLeft()); // x becomes direct child of z
            // now rotate x and y, including transfer of middle subtree
            if (x == y.getLeft())
            {
                relink(y, x.getRight(), true); // x’s right child becomes y’s left
                relink(x, y, false); // y becomes x’s right child
            }
            else
                {
                    relink(y, x.getLeft(), false); // x’s left child becomes y’s right
                    relink(x, y, true); // y becomes left child of x
                }
        }
        // Performs a trinode restructuring of Position x with its parent/grandparent.
        public Position<Entry<K,V>> restructure(Position<Entry<K,V>> x)
        {
            Position<Entry<K,V>> y = parent(x);
            Position<Entry<K,V>> z = parent(y);
            if ((x == right(y)) == (y == right(z)))
            { // matching alignments
                rotate(y); // single rotation (of y)
                return y; // y is new subtree root
            }
            else
                { // opposite alignments
                    rotate(x); // double rotation (of x)
                    rotate(x);
                    return x; // x is new subtree root
                }
        }
    }///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // To represent the underlying tree structure, we use a specialized subclass of the
    // LinkedBinaryTree class that we name BalanceableBinaryTree (see Section 11.2).
    protected BalanceableBinaryTree<K,V> tree = new BalanceableBinaryTree<>();

    // Constructs an empty map using the natural ordering of keys.
    public TreeMap()
    {
        super(); // the AbstractSortedMap constructor
        tree.addRoot(null); // create a sentinel leaf as root
    }
    // Constructs an empty map using the given comparator to order keys.
    public TreeMap(Comparator<K> comp)
    {
        super(comp); // the AbstractSortedMap constructor
        tree.addRoot(null); // create a sentinel leaf as root
    }
    // Returns the number of entries in the map.
    public int size()
    {
        return (tree.size() - 1) / 2; // only internal nodes have entries
    }
    // Utility used when inserting a new entry at a leaf of the tree
    private void expandExternal(Position<Entry<K,V>> p, Entry<K,V> entry)
    {
        tree.set(p, entry); // store new entry at p
        tree.addLeft(p, null); // add new sentinel leaves as children
        tree.addRight(p, null);
    }

    // Omitted from this code fragment, but included in the online version of the code,
    // are a series of protected methods that provide notational shorthands to wrap
    // operations on the underlying linked binary tree. For example, we support the
    // protected syntax root() as shorthand for tree.root() with the following utility:
    protected Position<Entry<K,V>> root() { return tree.root(); }

    // Returns the position in p's subtree having given key (or else the terminal leaf).
    private Position<Entry<K,V>> treeSearch(Position<Entry<K,V>> p, K key)
    {
        if (tree.isExternal(p))
            return p; // key not found; return the ﬁnal leaf
        int comp = compare(key, p.getElement());
        if (comp == 0)
            return p; // key found; return its position
        else if (comp < 0)
            return treeSearch(tree.left(p), key); // search left subtree
        else
            return treeSearch(tree.right(p), key); // search right subtree
    }

    public V get(K key) throws IllegalArgumentException
    {
        checkKey(key); // may throw IllegalArgumentException
        Position<Entry<K,V>> p = treeSearch(root(), key);
        rebalanceAccess(p); // hook for balanced tree subclasses
        if (tree.isExternal(p)) return null; // unsuccessful search
        return p.getElement().getValue(); // match found
    }
    // Associates the given value with the given key, returning any overridden value.
    public V put(K key, V value) throws IllegalArgumentException
    {
        checkKey(key); // may throw IllegalArgumentException
        Entry<K,V> newEntry = new MapEntry<>(key, value);
        Position<Entry<K,V>> p = treeSearch(root(), key);
        if (tree.isExternal(p))
        { // key is new
            expandExternal(p, newEntry);
            rebalanceInsert(p); // hook for balanced tree subclasses
            return null;
        }
        else
        {   // replacing existing key
            V old = p.getElement().getValue();
            tree.set(p, newEntry);
            rebalanceAccess(p); // hook for balanced tree subclasses
            return old;
        }
    }
    // Removes the entry having key k (if any) and returns its associated value.
    public V remove(K key) throws IllegalArgumentException
    {
        checkKey(key); // may throw IllegalArgumentException
        Position<Entry<K,V>> p = treeSearch(root(), key);
        if (tree.isExternal(p))
        { // key not found
            rebalanceAccess(p); // hook for balanced tree subclasses
            return null;
        }
        else
        {
            V old = p.getElement().getValue();
            if (tree.isInternal(tree.left(p)) && tree.isInternal(tree.right(p)))
            {   // both children are internal
                Position<Entry<K,V>> replacement = treeMax(tree.left(p));
                tree.set(p, replacement.getElement());
                p = replacement;
            }   // now p has at most one child that is an internal node
            Position<Entry<K,V>> leaf = (tree.isExternal(tree.left(p)) ? tree.left(p) : tree.right(p));
            Position<Entry<K,V>> sib = tree.sibling(leaf);
            remove(leaf.getElement().getKey());
            remove(p.getElement().getKey()); // sib is promoted in p’s place
            rebalanceDelete(sib); // hook for balanced tree subclasses
            return old;
        }
    }

    //Returns the position with the maximum key in subtree rooted at Position p.
    protected Position<Entry<K,V>> treeMax(Position<Entry<K,V>> p)
    {
        Position<Entry<K,V>> walk = p;
        while (tree.isInternal(walk))
            walk = tree.right(walk);
        return tree.parent(walk); // we want the parent of the leaf
    }
    // Returns the entry having the greatest key (or null if map is empty).
    public Entry<K,V> lastEntry()
    {
        if (isEmpty()) return null;
        return treeMax(root()).getElement();
    }
    // Returns the entry with greatest key less than or equal to given key (if any).
    public Entry<K,V> floorEntry(K key) throws IllegalArgumentException
    {
        checkKey(key); // may throw IllegalArgumentException
        Position<Entry<K,V>> p = treeSearch(root(), key);
        if (tree.isInternal(p)) return p.getElement(); // exact match
        while (!tree.isRoot(p))
        {
            if (p == tree.right(tree.parent(p)))
                return tree.parent(p).getElement(); // parent has next lesser key
            else
            p = tree.parent(p);
        }
        return null; // no such ﬂoor exists
    }
    // Returns the entry with greatest key strictly less than given key (if any).
    public Entry<K,V> lowerEntry(K key) throws IllegalArgumentException
    {
        checkKey(key); // may throw IllegalArgumentException
        Position<Entry<K,V>> p = treeSearch(root(), key);
        if (tree.isInternal(p) && tree.isInternal(tree.left(p)))
            return treeMax(tree.left(p)).getElement(); // this is the predecessor to p
        // otherwise, we had failed search, or match with no left child
        while (!tree.isRoot(p))
        {
            if (p == tree.right(tree.parent(p)))
                return tree.parent(p).getElement(); // parent has next lesser key
            else
            p = tree.parent(p);
        }
        return null; // no such lesser key exists
    }

    //Returns an iterable collection of all key-value entries of the map.
    public Iterable<Entry<K,V>> entrySet()
    {
        ArrayList<Entry<K,V>> buffer = new ArrayList<>(size());
        for (Position<Entry<K,V>> p : tree.inorder())
            if (tree.isInternal(p)) buffer.add(p.getElement());
        return buffer;
    }
    // Returns an iterable of entries with keys in range [fromKey, toKey).
    public Iterable<Entry<K,V>> subMap(K fromKey, K toKey)
    {
        ArrayList<Entry<K,V>> buffer = new ArrayList<>(size());
        if (compare(fromKey, toKey) < 0) // ensure that fromKey < toKey
            subMapRecurse(fromKey, toKey, root(), buffer);
        return buffer;
    }
    private void subMapRecurse(K fromKey, K toKey, Position<Entry<K,V>> p, ArrayList<Entry<K,V>> buffer)
    {
        if (tree.isInternal(p))
            if (compare(p.getElement(), fromKey) < 0)
            // p's key is less than fromKey, so any relevant entries are to the right
        subMapRecurse(fromKey, toKey, tree.right(p), buffer);
        else
        {
            subMapRecurse(fromKey, toKey, tree.left(p), buffer); // ﬁrst consider left subtree
            if (compare(p.getElement(), toKey) < 0)
            { // p is within range
                buffer.add(p.getElement()); // so add it to buffer, and consider
                subMapRecurse(fromKey, toKey, tree.right(p), buffer); // right subtree as well
            }
        }
    }

    protected void rebalanceInsert(Position<Entry<K,V>> p) { }
    protected void rebalanceDelete(Position<Entry<K,V>> p) { }
    protected void rebalanceAccess(Position<Entry<K,V>> p) { }
}
