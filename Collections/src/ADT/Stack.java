package ADT;

public interface Stack <E> {
    int Size();
    Boolean IsEmpty();
    E Top() throws Exceptions.StackEmptyException;
    void Push(E element) throws Exceptions.StackFullException;
    E Pop() throws Exceptions.StackEmptyException;
}
