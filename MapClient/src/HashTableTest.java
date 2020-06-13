import ADT.Map;
import arraybased.ChainHashMap;

public class HashTableTest
{
    public static void main(String[] args)
    {
        Map<Integer, String> hTable = new ChainHashMap<>(5);
        hTable.put(4, "Kiev");
        hTable.put(26, "Moscow");
        hTable.put(11, "Paris");
        hTable.put(7, "Berlin");
        hTable.put(4, "Washington");
        hTable.put(8, "Kiev");
        hTable.put(13, "Moscow");
        hTable.put(55, "Paris");
        hTable.put(37, "Berlin");
        hTable.put(2, "Washington");

        System.out.println(hTable.get(4));
    }
}
