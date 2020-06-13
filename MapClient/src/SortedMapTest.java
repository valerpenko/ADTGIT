import ADT.Entry;
import arraybased.SortedTableMap;


public class SortedMapTest
{
    public static void main(String[] args)
    {
        SortedTableMap<Integer, String> sortMap = new SortedTableMap<>();
        sortMap.put(4, "Kiev");
        sortMap.put(26, "Moscow");
        sortMap.put(11, "Paris");
        sortMap.put(7, "Berlin");
        sortMap.put(4, "Washington");
        sortMap.put(8, "Kiev");
        sortMap.put(13, "Moscow");
        sortMap.put(55, "Paris");
        sortMap.put(37, "Berlin");
        sortMap.put(2, "Washington");

        for (Entry<Integer, String> obj : sortMap.entrySet())
            System.out.println(obj.getKey());
    }
}
