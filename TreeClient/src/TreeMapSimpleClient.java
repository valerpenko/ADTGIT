import ADT.Entry;
import listbased.AVLTreeMap;
import listbased.SplayTreeMap;
import listbased.TreeMap;
import listbased.TreeMap2;

public class TreeMapSimpleClient
{
    public static void main(String[] args)
    {
        //TreeMap<Integer,String> tm = new TreeMap<>();
        //TreeMap2<Integer,String> tm = new TreeMap2<>();
        //AVLTreeMap<Integer,String> tm = new AVLTreeMap<>();
        SplayTreeMap<Integer,String> tm = new SplayTreeMap<>();

        tm.put(7,"bucket");
        tm.put(2,"paint");
        tm.put(8, "rabbit");
        tm.put(11, "cheap");
        tm.put(15, "modest");
        tm.put(6, "guest");
        tm.put(1, "error");

        System.out.println(tm.get(11));

        System.out.println(tm.higherEntry(7).getValue());

        System.out.println(tm.lastEntry().getValue());

        //tm.remove(11);

        for(Entry<Integer,String> obj : tm.entrySet())
            System.out.println(obj.getKey());
    }
}