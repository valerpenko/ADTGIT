package arraybased;


import ADT.Iterator;
import Exceptions.SetEmptyException;
import Exceptions.SetFullException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ArraySetTest {

    @Test
    void add() throws SetFullException
    {
        ArraySet<Integer> set = new ArraySet<>();
        set.add(7);
        boolean actual = set.contains(7);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    void remove() throws SetFullException, SetEmptyException
    {
        ArraySet<Integer> set = new ArraySet<>(4);
        set.add(4);set.add(9);set.add(8);set.add(1);
        set.remove(8);
        int actual = set.size();
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    void union() throws SetFullException, SetEmptyException
    {
        ArraySet<Integer> set = new ArraySet<>(8);
        set.add(4);set.add(9);set.add(8);set.add(1);
        ArraySet<Integer> set2 = new ArraySet<>();
        set.add(2);set.add(13);set.add(5);set.add(0);
        set.union(set2);
        int actual = set.size();
        int expected = 8;
        assertEquals(expected, actual);
    }

    @Test
    void intersection() throws SetFullException
    {
        ArraySet<Integer> set = new ArraySet<>();
        set.add(0);set.add(13);set.add(1);set.add(6);
        ArraySet<Integer> set2 = new ArraySet<>();
        set2.add(7);set2.add(9);set2.add(0);set2.add(1);
        set.intersection(set2);
//        int actual = set.size();
//        int expected = 2;
//        assertEquals(expected, actual);
        int count = 0;
        int[] actual = new int[6];
        int[] expected = {0, 13, 1, 6, 0, 0};

        Iterator<Integer> iter = set.iterator();
        while (iter.hasNext())
            actual[count++] = iter.next();
        Assertions.assertArrayEquals(expected, actual);
    }
}