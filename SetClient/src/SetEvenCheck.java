import ADT.Set;
import Exceptions.SetEmptyException;
import Exceptions.SetFullException;
import arraybased.ArraySet;

import java.util.Iterator;

public class SetEvenCheck
{
    public static void main(String[] args) throws SetFullException, SetEmptyException
    {
        Set<Integer> set = new ArraySet<>(10);
        set.add(2);set.add(4);set.add(8);set.add(26);set.add(32);
        set.add(14);set.add(52);set.add(11);set.add(94);set.add(6);
        System.out.println(isEven(set));
//        Iterator<Integer> iter = set.iterator();
//        while (iter.hasNext())
//            System.out.println(iter.next());
    }

    public static boolean isEven (Set<Integer> s)
    {
        int count = 0;
        int[] copy = new int[s.size()];
        Iterator<Integer> iter = s.iterator();
        while (iter.hasNext())
            copy[count++] = iter.next();

        for (int value : copy)
        {
            if (value % 2 != 0)
            {
                return false;
            }
        }
        return true;
    }
}
