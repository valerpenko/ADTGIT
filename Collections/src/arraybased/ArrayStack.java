package arraybased;
import ADT.Stack;
public class ArrayStack <E> implements Stack <E> {

    // длина по умолчанию массива, используемого для реализации стека.
    private static int CAPACITY = 1000;
    //  массив, используемый для реализации стека.
    private E[] S;
    // индекс последнего элемента стека в массиве.
    private int top = -1;
    // первоначальное присвоение стеку длины массива по умолчанию (CAPACITY).
    public ArrayStack() //: this(CAPACITY)
    {this(CAPACITY); }
    // первоначальное присвоение стеку массива определенной длины.
    // параметр cap длина массива.
    public ArrayStack(int capacity)
    {
        S = (E[])new Object[capacity];
    }
    public int Size() { return (top + 1); } // время О(1).
    public Boolean IsEmpty() { return (top < 0); } // время О(1).
    // время 0(1). исключение StackFullExcBption, если массив ужв заполнен.

    public void Push(E obj) throws Exceptions.StackFullException //throws StackFulIException
    {
        if (Size() == S.length) throw new Exceptions.StackFullException("Stack overflow.");
        S[++top] = obj;
    }
    public E Top() throws Exceptions.StackEmptyException // время О(1).
    {
        if (IsEmpty()) throw new Exceptions.StackEmptyException("Stack is empty.");
        return S[top];
    }
    public E Pop() throws Exceptions.StackEmptyException // время О(1).
    {   E elem;
        if (IsEmpty()) throw new Exceptions.StackEmptyException("Stack is Empty.");
        elem = S[top];
        S[top--] = null; // отправляет S[top] для утилизации сборщиком мусора.
        return elem;
    }
}
