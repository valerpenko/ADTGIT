package ADT;

public interface SkipList <E> {
    Position<E> next(Position<E> p); //Returns the position following p on the same level.
    Position<E> prev(Position<E>p); //Returns the position preceding p on the same level.
    Position<E> above(Position<E>p); //Returns the position above p in the same tower.
    Position<E> below(Position<E>p); //Returns the position below p in the same tower.
}
