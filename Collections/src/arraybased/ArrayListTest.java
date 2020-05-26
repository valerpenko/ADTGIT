package arraybased;
import java.util.Iterator;
import arraybased._ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class _ArrayListTest {

    @Test
    void size() {
        _ArrayList<Integer> al = new _ArrayList<>();
        al.add(0,1);al.add(1,2);al.add(2,3);
        int actual = al.size();
        int expected = 3;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void isEmpty() {
        _ArrayList<Integer> al = new _ArrayList<>();
        //al.add(0,1);
        Assertions.assertTrue(al.isEmpty());
    }

    @Test
    void get() {
        _ArrayList<Integer> al = new _ArrayList<>();
        al.add(0,1);al.add(1,17);al.add(2,9);
        int actual = al.get(1);
        int expected = 17;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void set() {
        _ArrayList<Integer> al = new _ArrayList<>();
        al.add(0,1);al.add(1,17);al.add(2,9);
        al.set(2, 50);
        int actual = al.get(2);
        int expected = 50;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void add() {
    }

    @Test
    void remove() {
        _ArrayList<Integer> al = new _ArrayList<>();
        al.add(0,1);al.add(1,7);al.add(2,25);
        al.remove(0);
        int actual = al.get(0);
        int expected = 7;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void iterator() {
        _ArrayList<Integer> al = new _ArrayList<>();
        al.add(0,5);
        al.add(1,10);
        al.add(2,15);
        al.add(3,20);

        int count = 0;
        int[] actual = new int[4];
        int[] expected = {5, 10, 15, 20};

        Iterator<Integer> iter = al.iterator();
        while (iter.hasNext())
            actual[count++] = iter.next();
        Assertions.assertArrayEquals(expected, actual);
    }
}