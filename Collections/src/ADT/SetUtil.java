package ADT;

import Exceptions.SetFullException;

public interface SetUtil <E>
{

    Set<E> union(Set<E> obj) throws SetFullException;

    Set<E> intersection(Set<E> obj) throws SetFullException;
}
