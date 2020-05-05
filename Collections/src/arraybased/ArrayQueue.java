package arraybased;
import ADT.Queue;
import Exceptions.QueueEmptyException;
import Exceptions.QueueFullException;

public class ArrayQueue <E> implements Queue <E> {

    private static int CAPACITY = 1000;
    private E[] data;

    private int first = 0;
    private int sz = 0;

    public ArrayQueue() { this(CAPACITY); }

    public ArrayQueue(int capacity)
    {
        data = (E[])new Object[capacity];
    }

    public int size() { return sz; }

    public Boolean isEmpty() { return (sz == 0); }

    public E first() throws QueueEmptyException
    {
        if (isEmpty()) throw new QueueEmptyException("Queue is Empty.");
        return data[first];
    }

    public void enqueue(E obj) throws QueueFullException
    {
        if (sz == data.length) throw new QueueFullException("Queue overflow.");
        int avail = (first + sz) % data.length;
        data[avail] = obj;
        sz++;
    }

    public E dequeue() throws QueueEmptyException
    {
        if (isEmpty()) throw new QueueEmptyException("Queue is Empty.");
        E temp = data[first];
        data[first] = null;
        first = (first + 1) % data.length;
        return temp;
    }
}
