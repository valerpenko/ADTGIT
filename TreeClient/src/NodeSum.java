import java.util.Iterator;
import listbased.LinkedBinaryTree;

public class NodeSum
{
    public static void main(String[] args)
    {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.addRoot(5);
        bt.addLeft(bt.root(), 20);
        bt.addRight(bt.root(), 7);
        bt.addLeft(bt.left(bt.root()),11);
        bt.addRight(bt.left(bt.root()),3);
        bt.addLeft(bt.right(bt.root()),15);
        bt.addRight(bt.right(bt.root()),4);

        System.out.println(SumOfNodes(bt));
    }

    public static int SumOfNodes(LinkedBinaryTree<Integer> tree)
    {
        int count = 0;
        Iterator<Integer> iter = tree.iterator();
        while(iter.hasNext())
            count = count + iter.next();
        return count;
    }
}
