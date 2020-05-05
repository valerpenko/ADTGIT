package ADT;

import Exceptions.QueueEmptyException;
import Exceptions.QueueFullException;
import listbased.LinkedQueue;

import java.util.ArrayList;
import java.util.List;
import java.lang.Iterable;

public abstract class AbstractTree <E> implements Tree<E>
{
    public boolean isInternal(Position<E> p) { return numChildren(p) > 0; }
    public boolean isExternal(Position<E> p) { return numChildren(p) == 0; }
    public boolean isRoot(Position<E> p) { return p == root(); }
    public boolean isEmpty() { return size() == 0; }

    public int depth(Position<E> p)
    {
        if (isRoot(p))
            return 0;
        else
            return 1 + depth(parent(p));
    }

    public int height(Position<E> p)
    {
        int h = 0; // base case if p is external
        for (Object obj : children(p)) {
            Position<E> c=(Position<E>)obj;
            h = Math.max(h, 1 + height(c));
        }
        return h;
    }

    // Adds positions of the subtree rooted at Position p to the given snapshot.
    private void preorderSubtree(Position<E> p, List<Position<E>> snapshot)
    {
        snapshot.add(p); // for preorder, we add position p before exploring subtrees
        for (Position<E> c : children(p))
            preorderSubtree(c, snapshot);
    }

    // Returns an iterable collection of positions of the tree, reported in preorder.
    public Iterable<Position<E>> preorder()
    {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty())
            preorderSubtree(root(), snapshot); // ﬁll the snapshot recursively
        return snapshot;
    }

    //public Iterable<Position<E>> positions() { return preorder(); }

    // Adds positions of the subtree rooted at Position p to the given snapshot.
    private void postorderSubtree(Position<E> p, List<Position<E>> snapshot)
    {
        for (Position<E> c : children(p))
            postorderSubtree(c, snapshot);
        snapshot.add(p); // for postorder, we add position p after exploring subtrees
    }
    // Returns an iterable collection of positions of the tree, reported in postorder.
    public Iterable<Position<E>> postorder()
    {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty())
            postorderSubtree(root(), snapshot); // ﬁll the snapshot recursively
        return snapshot;
    }

    //public Iterable<Position<E>> positions() { return postorder(); }

    // Returns an iterable collection of positions of the tree in breadth-ﬁrst order.
    public Iterable<Position<E>> breadthfirst() throws QueueFullException, QueueEmptyException
    {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty())
        {
            Queue<Position<E>> fringe = new LinkedQueue<>();
            fringe.enqueue(root()); // start with the root
            while (!fringe.isEmpty())
            {
                Position<E> p = fringe.dequeue(); // remove from front of the queue
                snapshot.add(p); // report this position
                for (Position<E> c : children(p))
                    fringe.enqueue(c); // add children to back of queue
            }
        }
    return snapshot;
    }

}
