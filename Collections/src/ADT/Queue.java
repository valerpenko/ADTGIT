package ADT;

import Exceptions.QueueEmptyException;
import Exceptions.QueueFullException;

public interface Queue <E> {
    int size();
    Boolean isEmpty();
    E first() throws QueueEmptyException;
    void enqueue(E element) throws QueueFullException;
    E dequeue() throws QueueEmptyException;
}
