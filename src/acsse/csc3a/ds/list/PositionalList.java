package acsse.csc3a.ds.list;

public interface PositionalList<E> extends Iterable<E> {
    int size();
    boolean isEmpty();
    Position<E> first();
    Position<E> last();
    Position<E> before(Position<E> p);
    Position<E> after(Position<E> p);
    Position<E> addFirst(E e);
    Position<E> addLast(E e);
    Position<E> addAfter(Position<E> p, E e);
    Position<E> addBefore(Position<E> p, E e);
    E remove(Position<?> p);
    E set(Position<E> p, E e);
}