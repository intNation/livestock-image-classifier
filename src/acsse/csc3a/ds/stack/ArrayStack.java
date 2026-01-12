package acsse.csc3a.ds.stack;

public class ArrayStack<E> implements Stack<E> {
	private E[] data;
	private int top = -1;

	@SuppressWarnings("unchecked")
	public ArrayStack() {
		data = (E[]) new Object[16];
	}

	private void resize() {
		data = java.util.Arrays.copyOf(data, data.length * 2);
	}

	public int size() {
		return top + 1;
	}

	public boolean isEmpty() {
		return top < 0;
	}

	public void push(E e) {
		if (size() == data.length)
			resize();
		data[++top] = e;
	}

	public E pop() {
		if (isEmpty())
			return null;
		E e = data[top];
		data[top--] = null;
		return e;
	}

	public E top() {
		return isEmpty() ? null : data[top];
	}
}