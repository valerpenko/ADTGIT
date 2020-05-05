package arraybased;

import ADT.Iterator;
import ADT.Set;
import ADT.SetUtil;
import Exceptions.SetEmptyException;
import Exceptions.SetFullException;

import java.util.NoSuchElementException;


public class ArraySet <E> implements Set <E>, SetUtil <E>
{
    private static int CAPACITY = 1000;
    private E[] data;
    private int count = 0;

    public ArraySet() {this(CAPACITY);}

    public ArraySet(int capacity)
    {
        data = (E[]) new Object[capacity];
    }

    public int size() {return count;}

    public boolean isEmpty() {return count == 0;}

    public void add(E obj) throws SetFullException
    {
        if (!(contains(obj)))
        {
            if (size() == data.length) throw new SetFullException("Set overflow.");
            data[count] = obj;
            count++;
        }
    }

    public E remove(E obj) throws SetEmptyException
    {
        if (isEmpty())
            throw new SetEmptyException("Set is Empty.");

        int search = -1;
        for (int i = 0; i < count; i ++)
            if (data[i].equals(obj))
                search = i;

        if (search == -1)
            throw new NoSuchElementException();

        E result = data[search];
        //data[search] = null;
        data[search] = data[count-1];
        data[count-1] = null;
        count--;
        return result;
    }

    public boolean contains (E obj)
    {
        for (int i = 0; i < count; i ++)
            if(data[i].equals(obj))return true;
        return false;
    }

    public Set<E> union(Set<E> obj) throws SetFullException
    {
        Set<E> combine=new ArraySet<E>(this.count+obj.size());
        for (int i = 0; i < count; i++)
            combine.add(data[i]);
        Iterator<E> it = obj.iterator();
        while(it.hasNext())
            combine.add(it.next());
        return combine;
//        ArraySet<E> combine;
//
//        for (int i = 0; i < count; i++)
//            obj.add(data[i]);
//        combine = (ArraySet<E>) obj;
//        return combine;
    }

    public Set<E> intersection(Set<E> obj) throws SetFullException
    {
        //ArraySet<E> copy = (ArraySet<E>) obj;
        ArraySet<E> mutualMembers = new ArraySet<>(obj.size());
        for (E element : data)
        {
            if (obj.contains(element))
            {
                mutualMembers.add(element);
            }
        }

//        for(int i = 0; i < data.length; i++)
//            if(this.contains(copy.data[i]))
//                mutualMembers.add(copy.data[i]);
        return mutualMembers;
    }

    private class SetIterator implements ADT.Iterator<E>
    {
        private int j = 0; // index of the next element to report
        private boolean removable = false; // can remove be called at this time?

        // Tests whether the iterator has a next object.
        public boolean hasNext() { return j < count; } // size is ﬁeld of outer instance

        // Returns the next object in the iterator.
        public E next() throws NoSuchElementException
        {
            if (j == count) throw new NoSuchElementException("No next element");
            removable = true; // this element can subsequently be removed
            return data[j++]; // post-increment j, so it is ready for future call to next
        }

        // Removes the element returned by most recent call to next.
        public void remove() throws IllegalStateException
        {
            if (!removable) throw new IllegalStateException("nothing to remove");
            try
            {
                ArraySet.this.remove(data[j - 1]); // that was the last one returned
            }
            catch (SetEmptyException e)
            {
                e.printStackTrace();
            }

            j -- ; // next element has shifted one cell to the left
            removable = false; // do not allow remove again until next is called
        }
    }

    // Returns an iterator of the elements stored in the list.
    public Iterator<E> iterator()
    {
        return new SetIterator(); // create a new instance of the inner class
    }
}
