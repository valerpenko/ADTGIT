package ADT;

import Exceptions.DequeueEmptyException;

public interface Deque<E>
{
    void addFirst(E e);
    void addLast(E e);
    E removeFirst() throws DequeueEmptyException;
    E removeLast() throws DequeueEmptyException;
    E first() throws DequeueEmptyException;
    E last() throws DequeueEmptyException;
    int size();
    boolean isEmpty();
}
