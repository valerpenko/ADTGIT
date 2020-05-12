import Exceptions.*;
import arraybased.ArrayQueue;
import arraybased.ArrayStack;

public class QueueReverse
{
    public static void main(String[] args) throws QueueEmptyException, StackEmptyException, QueueFullException, StackFullException
    {
        ArrayQueue <Integer> q1 = new ArrayQueue <>(5);
        q1.enqueue(1);q1.enqueue(2);q1.enqueue(3);q1.enqueue(4);q1.enqueue(5);
        reverse(q1);
    }

    private static void reverse(ArrayQueue<Integer> q) throws StackEmptyException, QueueFullException, QueueEmptyException, StackFullException
    {
        ArrayStack <Integer> s = new ArrayStack <>(5);

        while(!q.isEmpty())
        {
            s.Push(q.dequeue());
        }
        while(!s.IsEmpty())
        {
            q.enqueue(s.Pop());
        }
    }
}
