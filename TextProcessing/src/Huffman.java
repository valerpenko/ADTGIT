
import ADT.Entry;
import ADT.Position;
import ADT._Iterator;
import arraybased.UnsortedTableMap;
import listbased.LinkedBinaryTree;
import listbased.UnsortedPriorityQueue;

import java.util.Iterator;


public class Huffman
{
    public static void main(String[] args)
    {
        UnsortedTableMap<Character, Integer> map = new UnsortedTableMap<>();
        String s = "Unsorted Priority Queue";
        for (int i = 0; i < s.length(); i++)
        {
            //take each character
            char ch = s.charAt(i);
            //check if already in map
            Integer val = map.get(ch);
            if (val != null)
            {
                map.put(ch, val + 1);
            }
            else
            {
                map.put(ch, 1);
            }
        }
        for (Entry<Character, Integer> obj : map.entrySet())
            System.out.println(obj.getKey() + "-" + obj.getValue());

        UnsortedPriorityQueue<Integer,LinkedBinaryTree<Entry<Character,Integer>>> queue
                = new UnsortedPriorityQueue<>();
        for (Entry<Character, Integer> obj : map.entrySet())
        {
            LinkedBinaryTree<Entry<Character,Integer>> charTree = new LinkedBinaryTree<>();
            charTree.addRoot(obj);
            queue.insert(obj.getValue(),charTree);
        }
        while (queue.size() > 1)
        {
            Entry<Integer, LinkedBinaryTree<Entry<Character,Integer>>> entry1 = queue.removeMin();
            Entry<Integer, LinkedBinaryTree<Entry<Character,Integer>>> entry2 = queue.removeMin();

            LinkedBinaryTree<Entry<Character,Integer>> tree  = new LinkedBinaryTree<>();

            Position<Entry<Character, Integer>> first = tree.addRoot(null);
            tree.addLeft(first, entry1.getValue().root().getElement());
            tree.addRight(first, entry2.getValue().root().getElement());

            UnsortedTableMap<Character, Integer> tmpMap = new UnsortedTableMap<>();
            Integer val = entry1.getKey() + entry2.getKey();
            map.put('~', val);

            Entry<Character, Integer> rootEntry=map.entrySet().iterator().next();
            tree.set(first,rootEntry);

            for(Entry<Character, Integer> node : tree)
            {
                System.out.println("char - " + node.getKey() + " freq - " + node.getValue());
            }

            queue.insert(entry1.getKey() + entry2.getKey(), tree);
//            queue.show();

            Entry<Integer, LinkedBinaryTree<Entry<Character,Integer>>> entry3 = queue.removeMin();
        }
//        return tree;
    }
}
