package listbased;

import ADT.AbstractSortedMap;
//import ADT.Position;
import ADT.SkipList;
import listbased.DoublyLinkedList;

import java.util.ArrayList;
import java.util.Random;

public class SkipListSortedMap<K,V> extends AbstractSortedMap<K,V>
{
    SkipList skipList=new LinkedSkipList();


    class LinkedSkipList<E> implements SkipList<E>
    {
        ArrayList <DoublyLinkedList<E>> horizontalLists;  // approx ln n
        DoublyLinkedList<E>[] verticalLists;    // n+2
        private Random rnd= new Random();

        public  LinkedSkipList(ArrayList<E> source)
        {
            //see Goodrich p.436
            int level =0;
            DoublyLinkedList<E> hlBase=new DoublyLinkedList<E>();
            DoublyLinkedList<E> hlAbove;
            //fill hl with source
            while(hlBase.size()>1)
            {
                hlAbove = MakeLevelAbove(hlBase);
                horizontalLists.add(hlAbove);
                hlBase=hlAbove;
                level++;
            }
        }
        private DoublyLinkedList<E> MakeLevelAbove(DoublyLinkedList<E> hlBase)
        //fills level randomly with subset from hlBase
        //makes vertical links for towers
        {
            DoublyLinkedList<E> levelAbove = new DoublyLinkedList<E>();
            E item=hlBase.first();
            while (item!=null)
            {
                if (rnd.nextBoolean())
                {

                }
                item=hlBase.
            }
        }

        Position<E> next(Position<E> p); //Returns the position following p on the same level.
        Position<E> prev(Position<E>p); //Returns the position preceding p on the same level.
        Position<E> above(Position<E>p); //Returns the position above p in the same tower.
        Position<E> below(Position<E>p); //Returns the position below p in the same tower.

    }
}
