package ADT;
import java.util.NoSuchElementException;


public interface Iterator <E> extends java.util.Iterator<E>
{
    boolean hasNext();
    E next() throws NoSuchElementException;
    void remove();
}
