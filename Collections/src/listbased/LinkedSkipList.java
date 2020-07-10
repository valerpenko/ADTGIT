package listbased;

import ADT.AbstractSortedMap;
//import ADT.Position;
import ADT.SkipList;
import listbased.DoublyLinkedList;

import java.util.ArrayList;

public class SkipListSortedMap<K,V> extends AbstractSortedMap<K,V>
{
    SkipList skipList=new LinkedSkipList();


    class LinkedSkipList<E> implements SkipList<E>
    {
        ArrayList <DoublyLinkedList<E>> horizontalLists;  // approx ln n
        DoublyLinkedList<E>[] verticalLists;    // n+2

        public  LinkedSkipList(ArrayList<E> source)
        {
            //see Goodrich p.436
        }

        Position<E> next(Position<E> p); //Returns the position following p on the same level.
        Position<E> prev(Position<E>p); //Returns the position preceding p on the same level.
        Position<E> above(Position<E>p); //Returns the position above p in the same tower.
        Position<E> below(Position<E>p); //Returns the position below p in the same tower.

    }
}
