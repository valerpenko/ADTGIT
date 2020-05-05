package listbased;
import ADT.Stack;
import Exceptions.StackEmptyException;

public class LinkedStack<E> implements Stack <E>
{
    private SinglyLinkedList <E> L = new SinglyLinkedList<>();

    public LinkedStack() {}

    public int Size() { return L.size(); }
    public Boolean IsEmpty() { return L.isEmpty(); }

    public void Push(E obj)
    {
        L.addFirst(obj);
    }
    public E Top() throws Exceptions.StackEmptyException
    {
        if (IsEmpty()) throw new Exceptions.StackEmptyException("Stack is empty.");
        return L.first();
    }
    public E Pop() throws Exceptions.StackEmptyException // время О(1).
    {
        if (IsEmpty()) throw new Exceptions.StackEmptyException("Stack is Empty.");
        return L.removeFirst();
    }
}
