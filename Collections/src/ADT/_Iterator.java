package ADT;
import java.util.NoSuchElementException;


public interface _Iterator <E>
{
    boolean hasNext();
    E next() throws NoSuchElementException;
    void remove();
}
