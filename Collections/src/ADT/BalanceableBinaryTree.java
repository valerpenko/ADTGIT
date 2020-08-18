package ADT;

import listbased.LinkedBinaryTree;

public abstract class BalanceableBinaryTree<K,V> extends LinkedBinaryTree<Entry<K,V>>
{
    //-------------- nested BSTNode class --------------
    // this extends the inherited LinkedBinaryTree.Node class
    protected static class BSTNode<E> extends Node<E>
    {
        int aux = 0;

        BSTNode(E element, Node<E> parent, Node<E> leftChild, Node<E> rightChild)
        {
            super(element, parent, leftChild, rightChild);
        }

        public int getAux() {
            return aux;
        }

        public void setAux(int aux) {
            this.aux = aux;
        }
    } //--------- end of nested BSTNode class ---------

    // positional-based methods related to aux field
    public int getAux(Position<Entry<K, V>> position) {
        return ((BalanceableBinaryTree.BSTNode<Entry<K, V>>) position).getAux();
    }

    public void setAux(Position<Entry<K, V>> position, int value) { ((BalanceableBinaryTree.BSTNode<Entry<K, V>>) position).setAux(value); }

    // Override node factory function to produce a BSTNode (rather than a Node)
    @Override
    protected Node<Entry<K, V>> createNode(Entry<K, V> element, Node<Entry<K, V>> parent, Node<Entry<K, V>> left, Node<Entry<K, V>> right)
    {
        return new BalanceableBinaryTree.BSTNode<>(element, parent, left, right);
    }

    //Relinks a parent node with its oriented child node.
    private void relink(Node<Entry<K, V>> parent, Node<Entry<K, V>> child, boolean makeLeftChild)
    {
        child.setParent(parent);

        if (makeLeftChild)
        {
            parent.setLeft(child);
        }
        else
        {
            parent.setRight(child);
        }
    }

    //Rotates a position above its parent.
    public void rotate(Position<Entry<K, V>> position)
    {
        Node<Entry<K, V>> x = validate(position);
        Node<Entry<K, V>> y = x.getParent();        // we assume this exists
        Node<Entry<K, V>> z = y.getParent();        // grandparent (possibly null)

        if (z == null)
        {
            root = x;                               // x becomes root of the tree
            x.setParent(null);
        }
        else
        {
            boolean makeLeftChild = y == z.getLeft();
            relink(z, x, makeLeftChild);            // x becomes direct child of z
        }
        // now rotate x and y, including transfer of middle subtree
        if (x == y.getLeft())
        {
            relink(y, x.getRight(), true);          // x’s right child becomes y’s left
            relink(x, y, false);                    // y becomes x’s right child
        }
        else
        {
            relink(y, x.getLeft(), false);          // x’s left child becomes y’s right
            relink(x, y, true);                     // y becomes left child of x
        }
    }

    //Performs a trinode restructuring of Position x with its
    public Position<Entry<K, V>> restructure(Position<Entry<K, V>> x)
    {
        Position<Entry<K, V>> y = parent(x);
        Position<Entry<K, V>> z = parent(y);

        if ((x == right(y)) == (y == right(z)))
        {   // matching alignments
            rotate(y);                              // single rotation (of y)
            return y;                               // y is new subtree root
        }
        else
        {   // opposite alignments
            // double rotation (of x)
            rotate(x);
            rotate(x);
            return x;                               // x is new subtree root
        }
    }

    //Stubs for balanced search tree operations (subclasses can override)
    //Rebalances the tree after an insertion of specified position. This
    //version of the method does not do anything, but it can be overridden by subclasses.
    //@param p the position which was recently inserted
    protected abstract void rebalanceInsert(Position<Entry<K, V>> p);

    //Rebalances the tree after a child of specified position has been removed.
    //This version of the method does not do anything, but it can be overridden by subclasses.
    //@param p the position of the sibling of the removed leaf
    protected abstract void rebalanceDelete(Position<Entry<K, V>> p);

    //Rebalances the tree after an access of specified position. This version
    //of the method does not do anything, but it can be overridden by a subclasses.
    //@param p the Position which was recently accessed (possibly a leaf)
    protected abstract void rebalanceAccess(Position<Entry<K, V>> p);
}
