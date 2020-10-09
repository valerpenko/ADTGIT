import ADT.DefaultComparator;
import ADT.Entry;
import listbased._2_4_Tree;
import java.util.Comparator;

public class _2_4_TreeTest
{
    public static void main(String[] args) throws Exception
    {
        _2_4_Tree<MWTEntry<Integer,Character>> tree = new _2_4_Tree<>();
        //_2_4_Tree<Integer> tree = new _2_4_Tree<>();
//        tree.insert(1);
//        tree.insert(5);
//        tree.insert(8);
//        tree.insert(15);
//        tree.insert(11);
//        tree.insert(26);
//        tree.insert(4);
//        tree.insert(3);

        MWTEntry<Integer,Character> entry1 = new MWTEntry<>(1,'a');
        MWTEntry<Integer,Character> entry2 = new MWTEntry<>(5,'b');
        MWTEntry<Integer,Character> entry3 = new MWTEntry<>(8,'c');
        MWTEntry<Integer,Character> entry4 = new MWTEntry<>(15,'d');
        MWTEntry<Integer,Character> entry5 = new MWTEntry<>(11,'e');
        MWTEntry<Integer,Character> entry6 = new MWTEntry<>(26,'f');
        MWTEntry<Integer,Character> entry7 = new MWTEntry<>(4,'g');
        MWTEntry<Integer,Character> entry8 = new MWTEntry<>(3,'h');

        tree.insert(entry1);
        tree.insert(entry2);
        tree.insert(entry3);
        tree.insert(entry4);
        tree.insert(entry5);
        tree.insert(entry6);
        tree.insert(entry7);
        tree.insert(entry8);

    }
    private static class MWTEntry<K,V> implements Entry<K,V>, Comparable<MWTEntry<K,V>>
    {
        private K k;
        private V v;
        public MWTEntry(K key, V value)
        {
            k = key;
            v = value;
        }
        public K getKey() { return k; }
        public V getValue() { return v; }

        public int compareTo(MWTEntry<K, V> e)
        {
            Comparator<K> comp = new DefaultComparator<>();
            if(comp.compare(this.getKey(),e.getKey())< 0)
                return -1;
            else if(comp.compare(this.getKey(),e.getKey()) > 0)
                return 1;
            else return 0;
        }
    }
}
