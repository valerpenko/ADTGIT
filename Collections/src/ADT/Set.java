package ADT;

import Exceptions.SetEmptyException;
import Exceptions.SetFullException;

public interface Set <E> extends Iterable <E>
{
    int size();

    boolean isEmpty();

    void add (E obj) throws SetFullException;

    E remove(E obj) throws SetEmptyException;

    boolean contains (E obj);
}
