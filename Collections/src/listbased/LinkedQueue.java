package listbased;

import ADT.Queue;
import Exceptions.QueueEmptyException;

public class LinkedQueue <E> implements Queue <E>
{
    private SinglyLinkedList <E> L = new SinglyLinkedList<>();

    public LinkedQueue() {}

    public int size() {return L.size(); }
    public Boolean isEmpty() { return L.isEmpty(); }

    public E first() throws QueueEmptyException
    {
        if (isEmpty()) throw new QueueEmptyException("Queue is Empty.");
        return L.first();
    }
    public void enqueue(E obj)
    {
        L.addLast(obj);
    }
    public E dequeue() throws QueueEmptyException
    {
        if (isEmpty()) throw new QueueEmptyException("Queue is Empty.");
        return L.removeFirst();
    }
}
