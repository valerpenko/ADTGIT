package ADT;
import java.util.NoSuchElementException;

<<<<<<< HEAD
public interface Iterator <E> extends java.util.Iterator<E>
=======
public interface Iterator <E>  extends java.util.Iterator
>>>>>>> e98679d605d3dc318b0e60f740f69be157d55a41
{
    boolean hasNext();
    E next() throws NoSuchElementException;
    void remove();
}
