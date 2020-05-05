package listbased;

<<<<<<< HEAD
=======
import ADT.Iterator;
>>>>>>> e98679d605d3dc318b0e60f740f69be157d55a41
import ADT.List;
import arraybased.ArrayList;

public class TreeNode <E>
{
    private E element;
    private int size = 0;
    private TreeNode<E> root;
    private TreeNode<E> parent;
    private List<TreeNode<E>> children = new ArrayList<>();

    public TreeNode(E data) { element = data;}

    public boolean isEmpty() { return size == 0; }

    public int size() { return size; }

    public void setRoot(TreeNode<E> node) throws IllegalStateException
    {
        if (!isEmpty()) throw new IllegalStateException("Tree is not empty");
        root = node;
        size = 1;
    }

    public TreeNode<E> getRoot() { return root; }

    public void addChild(TreeNode<E> child, int chIndex)
    {
        child.setParent(this);
        children.add(chIndex ,child);
        size++;
    }

    public void addChildren(List<TreeNode<E>> ch)
    {
        int count = 0;
<<<<<<< HEAD
        for (TreeNode<E> obj : ch)
            children.add(count++, obj);
=======
        Iterator<TreeNode<E>> iter=ch.iterator();
        while(iter.hasNext())
            children.add(count++, iter.next());

        //for (TreeNode<E> obj : ch)
        //    children.add(count++, obj);
>>>>>>> e98679d605d3dc318b0e60f740f69be157d55a41
    }

    public int numChildren(TreeNode<E> node)
    {
        if (isEmpty())
            return 0;
        else
            return node.children.size();
    }

    public List<TreeNode<E>> getChildren() { return children; }

    public E getData() { return element; }

    public void setData(E data) { element = data; }

    private void setParent(TreeNode<E> above) { parent = above; }

    public TreeNode<E> getParent() { return parent; }
}
