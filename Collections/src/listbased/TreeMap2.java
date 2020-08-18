package listbased;
import java.util.ArrayList;
import java.util.Comparator;
import ADT.AbstractSortedMap;
import ADT.BalanceableBinaryTree;
import ADT.Entry;
import ADT.Position;

//An implementation of a sorted map using a binary search tree
//@author Rogerio J. Gentil
public class TreeMap2<K, V> extends AbstractSortedMap<K, V>
{
    //protected BalanceableBinaryTree<K, V> tree = new BalanceableBinaryTree<>();
    private static class BBTree<K,V> extends BalanceableBinaryTree<K,V>
    {
        protected void rebalanceInsert(Position<Entry<K, V>> p) { }
        protected void rebalanceDelete(Position<Entry<K, V>> p) { }
        protected void rebalanceAccess(Position<Entry<K, V>> p) { }
    }

    protected BBTree<K, V> tree = new BBTree<>();

    public TreeMap2()
    {
        super();
        tree.addRoot(null);
    }

    public TreeMap2(Comparator<K> comp)
    {
        super(comp);
        tree.addRoot(null);
    }

    @Override
    public int size() {
        return (tree.size() - 1) / 2;   // only internal nodes have entries
    }

    //Utility used when inserting a new entry at a leaf of the tree.
    private void expandExternal(Position<Entry<K, V>> position, Entry<K, V> entry)
    {
        tree.set(position, entry);
        // add new sentinel leaves as children
        tree.addLeft(position, null);
        tree.addRight(position, null);
    }

    protected Position<Entry<K, V>> root() {
        return tree.root();
    }

    protected Position<Entry<K, V>> parent(Position<Entry<K, V>> p) {
        return tree.parent(p);
    }

    protected Position<Entry<K, V>> left(Position<Entry<K, V>> p) {
        return tree.left(p);
    }

    protected Position<Entry<K, V>> right(Position<Entry<K, V>> p) {
        return tree.right(p);
    }

    protected Position<Entry<K, V>> sibling(Position<Entry<K, V>> p) {
        return tree.sibling(p);
    }

    protected boolean isRoot(Position<Entry<K, V>> p) {
        return tree.isRoot(p);
    }

    protected boolean isExternal(Position<Entry<K, V>> p) {
        return tree.isExternal(p);
    }

    protected boolean isInternal(Position<Entry<K, V>> p) {
        return tree.isInternal(p);
    }

    protected void set(Position<Entry<K, V>> p, Entry<K, V> e) {
        tree.set(p, e);
    }

    protected Entry<K, V> remove(Position<Entry<K, V>> p) { return tree.remove(p); }

    protected void rotate(Position<Entry<K, V>> p) {
        tree.rotate(p);
    }

    protected Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) {
        return tree.restructure(x);
    }

     //Returns the position in p's subtree having given key (or else the terminal leaf)
    private Position<Entry<K, V>> treeSearch(Position<Entry<K, V>> position, K key)
    {
        if (isExternal(position))
        {
            return position;         // key not found; return the final leaf
        }

        int comp = compare(key, position.getElement());
        if (comp == 0)
        {
            return position;                         // key found; return its position
        }
        else if (comp < 0)
        {
            return treeSearch(left(position), key);  // search left subtree
        }
        else
        {
            return treeSearch(right(position), key); // search right subtree
        }
    }

    @Override
    public V get(K key) throws IllegalArgumentException
    {
        checkKey(key);
        Position<Entry<K, V>> position = treeSearch(root(), key);
        tree.rebalanceAccess(position);

        if (isExternal(position)) { return null; }

        return position.getElement().getValue();
    }

    //Associates the given value with the given key, returning any overridden value.
    @Override
    public V put(K key, V value) throws IllegalArgumentException
    {
        checkKey(key);
        Entry<K, V> newEntry = new MapEntry<>(key, value);
        Position<Entry<K, V>> position = treeSearch(root(), key);

        if (isExternal(position))
        {             // key is new
            expandExternal(position, newEntry);
            tree.rebalanceInsert(position);         // hook for balanced tree subclasses
            return null;
        }
        else
        {                                // replacing existing key
            V old = position.getElement().getValue();
            set(position, newEntry);
            tree.rebalanceAccess(position);         // hook for balanced tree subclasses
            return old;
        }
    }

    //Removes the entry having key k (if any) and returns its associated value.
    @Override
    public V remove(K key) throws IllegalArgumentException
    {
        checkKey(key);
        Position<Entry<K, V>> position = treeSearch(root(), key);

        if (isExternal(position))
        {             // key not found
            tree.rebalanceAccess(position);          // hook for balanced tree subclasses
            return null;
        }
        else
        {
            V old = position.getElement().getValue();

            // both children are internal
            if (isInternal(left(position)) && isExternal(right(position)))
            {
                Position<Entry<K, V>> replacement = treeMax(left(position));
                set(position, replacement.getElement());
                position = replacement;
            } // now p has at most one child that is an internal node

            Position<Entry<K, V>> leaf = isExternal(left(position)) ? left(position) : right(position);
            Position<Entry<K, V>> sibling = sibling(leaf);
            remove(leaf);
            remove(position);               // sibling is promoted in p’s place
            tree.rebalanceDelete(sibling);      // hook for balanced tree subclasses
            return old;
        }
    }

   //Returns the position with the maximum key in subtree rooted.
    protected Position<Entry<K, V>> treeMax(Position<Entry<K, V>> position)
    {
        Position<Entry<K, V>> walk = position;
        while (isInternal(walk)) {
            walk = right(walk);
        }

        return parent(walk);            // we want the parent of the leaf
    }

    //Returns the entry having the greatest key (or null if map is empty).
    @Override
    public Entry<K, V> lastEntry()
    {
        if (isEmpty()) { return null; }

        return treeMax(root()).getElement();
    }

    //Returns the entry with greatest key less than or equal to given key (if any).
    @Override
    public Entry<K, V> floorEntry(K key) throws IllegalArgumentException
    {
        checkKey(key);
        Position<Entry<K, V>> position = treeSearch(root(), key);

        if (isInternal(position))
        {         // exact match
            return position.getElement();
        }

        while (!isRoot(position))
        {
            if (position == right(parent(position)))
            {
                return parent(position).getElement();   // parent has next lesser key
            }
            else
            {
                position = parent(position);
            }
        }
        return null;
    }

    @Override
    public Entry<K, V> lowerEntry(K key) throws IllegalArgumentException
    {
        checkKey(key);
        Position<Entry<K, V>> position = treeSearch(root(), key);

        if (isInternal(position) && isInternal(left(position)))
        {
            return treeMax(left(position)).getElement();        // this is the predecessor to p
        }

        // otherwise, we had failed search, or match with no left child
        while (!isRoot(position))
        {
            if (position == right(parent(position)))
            {
                return parent(position).getElement();   // parent has next lesser key
            }
            else
            {
                position = parent(position);
            }
        }
        return null;            // no such lesser key exists
    }

    //Returns an iterable collection of all key-value entries of the map.
    @Override
    public Iterable<Entry<K, V>> entrySet()
    {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>(size());

        for (Position<Entry<K, V>> position : tree.inorder())
        {
            if (isInternal(position))
            {
                buffer.add(position.getElement());
            }
        }
        return buffer;
    }

    //Returns an iterable of entries with keys in range [fromKey, toKey).
    @Override
    public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) throws IllegalArgumentException
    {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>(size());

        if (compare(fromKey, toKey) < 0)
        {                  // ensure that fromKey < toKey
            subMapRecurse(fromKey, toKey, root(), buffer);
        }
        return buffer;
    }

    private void subMapRecurse(K fromKey, K toKey, Position<Entry<K, V>> position, ArrayList<Entry<K, V>> buffer)
    {
        if (isInternal(position))
        {
            if (compare(position.getElement(), fromKey) < 0)
            {
                // p's key is less than fromKey, so any relevant entries are to the right
                subMapRecurse(fromKey, toKey, right(position), buffer);
            }
            else
            {
                subMapRecurse(fromKey, toKey, left(position), buffer);           // first consider left subtree
                if (compare(position.getElement(), toKey) < 0)
                {                // p is within range
                    buffer.add(position.getElement());                          // so add it to buffer, and consider
                    subMapRecurse(fromKey, toKey, right(position), buffer);      // right subtree as well
                }
            }
        }
    }

    //Returns position with the minimal key in the subtree rooted at Position p.
    //@param p a Position of the tree serving as root of a subtree
    //@return Position with minimal key in subtree
    protected Position<Entry<K, V>> treeMin(Position<Entry<K, V>> p)
    {
        Position<Entry<K, V>> walk = p;
        while (isInternal(walk))
        {
            walk = left(walk);
        }
        return parent(walk);              // we want the parent of the leaf
    }

    //additional behaviors of the SortedMap interface
    //Returns the entry having the least key (or null if map is empty).
    //@return entry with least key (or null if map is empty)
    @Override
    public Entry<K, V> firstEntry()
    {
        if (isEmpty()) { return null; }
        return treeMin(root()).getElement();
    }

    //Returns the entry with least key greater than or equal to given key (or null if no such key exists).
    //@return entry with least key greater than or equal to given (or null if no such entry)
    //@throws IllegalArgumentException if the key is not compatible with the map
    @Override
    public Entry<K, V> ceilingEntry(K key) throws IllegalArgumentException
    {
        checkKey(key);                              // may throw IllegalArgumentException
        Position<Entry<K, V>> p = treeSearch(root(), key);
        if (isInternal(p))
        {
            return p.getElement();   // exact match
        }
        while (!isRoot(p))
        {
            if (p == left(parent(p)))
            {
                return parent(p).getElement();          // parent has next greater key
            }
            else
            {
                p = parent(p);
            }
        }
        return null;                                // no such ceiling exists
    }

    //Returns the entry with least key strictly greater than given key (or null if no such key exists).
    //@return entry with least key strictly greater than given (or null if no such entry)
    //@throws IllegalArgumentException if the key is not compatible with the map
    @Override
    public Entry<K, V> higherEntry(K key) throws IllegalArgumentException
    {
        checkKey(key);                               // may throw IllegalArgumentException
        Position<Entry<K, V>> p = treeSearch(root(), key);
        if (isInternal(p) && isInternal(right(p)))
        {
            return treeMin(right(p)).getElement();     // this is the successor to p
        }    // otherwise, we had failed search, or match with no right child
        while (!isRoot(p))
        {
            if (p == left(parent(p)))
            {
                return parent(p).getElement();           // parent has next lesser key
            }
            else
            {
                p = parent(p);
            }
        }
        return null;                                 // no such greater key exists
    }
}
