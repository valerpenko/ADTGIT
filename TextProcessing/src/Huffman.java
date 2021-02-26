
import ADT.Entry;
import arraybased.UnsortedTableMap;
import listbased.LinkedBinaryTree;
import listbased.UnsortedPriorityQueue;

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
        for (Entry<Character, Integer> obj : map.entrySet()) {
            LinkedBinaryTree<Entry<Character,Integer>> charTree = new LinkedBinaryTree<>();
            charTree.addRoot(obj);
            queue.insert(obj.getValue(),charTree);
        }
        
    }
}
