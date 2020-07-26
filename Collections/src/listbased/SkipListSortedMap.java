package listbased;

import ADT.AbstractSortedMap;
import ADT.Position;
import ADT.SkipList;
import listbased.DoublyLinkedList;

import java.util.ArrayList;
import java.util.Random;

public class SkipListSortedMap<K,V> extends AbstractSortedMap<K,V>
{
    SkipList skipList=new LinkedSkipList();

    public int size() {
        return 0; //size is not implemented in ;
    }

    @Override
    public V get(K key) {
        return null; //см. page 439 SkipSearch
    }


    class LinkedSkipList<E> implements SkipList<E>
    {
        ArrayList <DoublyLinkedList<E>> horizontalLists;  // approx ln n
        DoublyLinkedList<E>[] verticalLists;    // n+2
        private Random rnd= new Random();

        public  LinkedSkipList(ArrayList<E> source)
        {
            // first of all we have to sort source

            //see Goodrich p.436
            E sentinel=null; //need to correct!!!
            int level =0;
            DoublyLinkedList<E> hlBase=new DoublyLinkedList<E>();
            DoublyLinkedList<E> hlAbove;
            //fill hl with source
            hlBase.addFirst(sentinel);
            for(int i = 0; i < source.size(); i++) {hlBase.addLast(source.get(i));}
            hlBase.addLast(sentinel);

            while(hlBase.size()>2) //including 2 sentinels
            {
                hlAbove = MakeLevelAbove(hlBase);
                horizontalLists.add(hlAbove);
                hlBase=hlAbove;
                level++;
            }

            //generate verticalLists
            verticalLists = new DoublyLinkedList[source.size()+2];

            x=horizontalLists.get(0).first();
            while (x!=null)
            {
                while() //x presents in horlist above
                {
                    verticalLists[i].addLast(x);
                }
                x=horizontalLists.get(0).next();
            }

        }
        private DoublyLinkedList<E> MakeLevelAbove(DoublyLinkedList<E> hlBase)
        //fills level randomly with subset from hlBase
        //makes vertical links for towers
        {
            DoublyLinkedList<E> levelAbove = new DoublyLinkedList<E>();
            E aboveItem= sentinel;
            levelAbove.addFirst(sentinel);
            E baseItem=hlBase.first();
            do
            {
                if (rnd.nextBoolean())
                {
                    baseItem = hlBase.next(baseItem);
                    levelAbove.addLast(baseItem);
                    aboveItem = levelAbove.last();
                }
                item=hlBase.
            }
            while (item!=null);
            levelAbove.addFirst(sentinel);
        }

        Position<E> next(Position<E> p); //Returns the position following p on the same level.
        Position<E> prev(Position<E>p); //Returns the position preceding p on the same level.
        Position<E> above(Position<E>p); //Returns the position above p in the same tower.
        Position<E> below(Position<E>p); //Returns the position below p in the same tower.

    }
}
