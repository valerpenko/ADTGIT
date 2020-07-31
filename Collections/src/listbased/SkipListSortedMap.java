package listbased;

import ADT.AbstractSortedMap;
import ADT.Position;
import ADT.SkipList;
import listbased.LinkedPositionalList;

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
        ArrayList <LinkedPositionalList<E>> horizontalLists;  // approx ln n
        LinkedPositionalList<E>[] verticalLists;    // source.Length+2
        private Random rnd= new Random();

        public  LinkedSkipList(ArrayList<E> source)
        {
            // first of all we have to sort source

            //see Goodrich p.436
            E sentinel=null; //need to correct!!!
            int level =0;
            horizontalLists= new ArrayList<>();
            LinkedPositionalList<E> hlBase=new LinkedPositionalList<E>();

            LinkedPositionalList<E> hlAbove;
            //fill hlBase with source and 2 sentinels
            hlBase.addFirst(sentinel);
            for(int i = 0; i < source.size(); i++) {hlBase.addLast(source.get(i));}
            hlBase.addLast(sentinel);
            horizontalLists.add(hlBase);

            while(hlBase.size()>2) //including 2 sentinels
            {
                hlAbove = MakeLevelAbove(hlBase);
                horizontalLists.add(hlAbove);
                hlBase=hlAbove;
                level++;
            }

            //generate verticalLists
            verticalLists = new LinkedPositionalList[source.size()+2];

            //whole set of towers created under the base of 0-level get(0) through loop interation
            LinkedPositionalList<E> basement=horizontalLists.get(0);
            Position<E> towerElement = basement.first();
            E x;
            int i=0;
            while (towerElement!=null)
            {
                verticalLists[i]=new LinkedPositionalList<E>();
                x = towerElement.getElement();
                verticalLists[i].addFirst(x);

                int stage=1;
                //x presents in currentLevel
                boolean present=false;
                LinkedPositionalList<E> currentLevel=horizontalLists.get(stage);
                Position<E> currentPosition=currentLevel.first();
                while(currentPosition.getElement()!=x) {
                    currentPosition=currentLevel.after(currentPosition);
                }
                //!!!!!!!!!!!!!!!
                while(present)
                {
                    verticalLists[i].addLast(x);
                    stage++;
                }

                towerElement=basement.after(towerElement);
            }

        }
        private LinkedPositionalList<E> MakeLevelAbove(LinkedPositionalList<E> hlBase)
        //fills level randomly with subset from hlBase
        //makes vertical links for towers
        {
            LinkedPositionalList<E> levelAbove = new LinkedPositionalList<E>();
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
