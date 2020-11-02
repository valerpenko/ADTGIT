import ADT.DefaultComparator;

import java.util.Comparator;

public class BottomUpSortClient
{
    public static void main(String[] args) throws Exception
    {
        Integer[] numbers = {1, 23, 4, 11, 50, 15, 17, 9, 2, 10,
                1, 23, 4, 11, 50, 15, 17, 9, 2, 10};

        Comparator<Integer> comp = new DefaultComparator<>();
        BottomUpMergeSort.mergeSortBottomUp(numbers, comp);
//        for(Integer i : numbers)
//            System.out.println(i);
    }
}
