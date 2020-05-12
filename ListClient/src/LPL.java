import ADT.List;
import ADT.Position;
import ADT.PositionalList;
import arraybased.ArrayList;
import ADT.Iterator;
import listbased.LinkedPositionalList;

public class LPL
{

    public static void main(String[] args)
    {
        LinkedPositionalList<Integer> lpl = new LinkedPositionalList<>();
        lpl.addFirst(15);
        lpl.addAfter(lpl.last(),3);
        lpl.addAfter(lpl.last(),9);
        lpl.addAfter(lpl.last(),10);
        insertionSort(lpl);
        Iterator<Integer> iter = lpl.iterator();
        while (iter.hasNext())
            System.out.println(iter.next());

//        ArrayList<Integer> al = new ArrayList<>();
//        al.add(0,5);
//        al.add(1,1);
//        al.add(2,27);
//        al.add(3,12);
//
//        Iterator<Integer> iter = al.iterator();
//        while (iter.hasNext())
//            System.out.println(iter.next());
    }

    // Insertion-sort of a positional list of integers into nondecreasing order
    public static void insertionSort(PositionalList<Integer> list)
    {
        Position<Integer> marker = list.first(); // last position known to be sorted
        while (marker != list.last())
        {
            Position<Integer> pivot = list.after(marker);
            int value = pivot.getElement(); // number to be placed
            if (value > marker.getElement()) // pivot is already sorted
                marker = pivot;
            else
            { // must relocate pivot
                Position<Integer> walk = marker; // ﬁnd leftmost item greater than value
                while (walk != list.first() && list.before(walk).getElement() > value)
                    walk = list.before(walk);
                list.remove(pivot); // remove pivot entry and
                list.addBefore(walk, value); // reinsert value in front of walk
            }
        }
    }
}
