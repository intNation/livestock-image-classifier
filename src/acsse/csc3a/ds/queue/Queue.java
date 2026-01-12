package acsse.csc3a.ds.queue;

public interface Queue<E> {
	int size();

	boolean isEmpty();

	void enqueue(E e);

	E dequeue();

	E first();
}