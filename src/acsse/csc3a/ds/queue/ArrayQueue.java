package acsse.csc3a.ds.queue;

public class ArrayQueue<E> implements Queue<E> {
	private E[] data;
	private int f = 0, size = 0;

	@SuppressWarnings("unchecked")
	public ArrayQueue() {
		data = (E[]) new Object[16];
	}

	private int index(int i) {
		return (f + i) % data.length;
	}

	private void resize() {
		E[] nd = java.util.Arrays.copyOf(data, data.length * 2);
		for (int i = 0; i < size; i++)
			nd[i] = data[index(i)];
		data = nd;
		f = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void enqueue(E e) {
		if (size == data.length)
			resize();
		int avail = index(size);
		data[avail] = e;
		size++;
	}

	public E dequeue() {
		if (isEmpty())
			return null;
		E e = data[f];
		data[f] = null;
		f = index(1);
		size--;
		return e;
	}

	public E first() {
		return isEmpty() ? null : data[f];
	}
}