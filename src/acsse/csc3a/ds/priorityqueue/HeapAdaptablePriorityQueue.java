package acsse.csc3a.ds.priorityqueue;

import java.util.Arrays;

import acsse.csc3a.util.Entry;

public class HeapAdaptablePriorityQueue<K extends Comparable<K>, V> implements PriorityQueue<K, V> {
	private static class PQEntry<K, V> implements Entry<K, V> {
		K k;
		V v;
		int idx;

		PQEntry(K k, V v, int i) {
			this.k = k;
			this.v = v;
			this.idx = i;
		}

		public K getKey() {
			return k;
		}

		public V getValue() {
			return v;
		}
	}

	private Entry<K, V>[] heap;
	private int size = 0;

	@SuppressWarnings("unchecked")
	public HeapAdaptablePriorityQueue() {
		heap = new Entry[16];
	}

	private int parent(int i) {
		return (i - 1) / 2;
	}

	private int left(int i) {
		return 2 * i + 1;
	}

	private int right(int i) {
		return 2 * i + 2;
	}

	private void swap(int i, int j) {
		Entry<K, V> t = heap[i];
		heap[i] = heap[j];
		((PQEntry<K, V>) heap[i]).idx = i;
		heap[j] = t;
		((PQEntry<K, V>) heap[j]).idx = j;
	}

	private void upheap(int i) {
		while (i > 0 && heap[i].getKey().compareTo(heap[parent(i)].getKey()) < 0) {
			swap(i, parent(i));
			i = parent(i);
		}
	}

	private void downheap(int i) {
		int small = i;
		if (left(i) < size && heap[left(i)].getKey().compareTo(heap[small].getKey()) < 0)
			small = left(i);
		if (right(i) < size && heap[right(i)].getKey().compareTo(heap[small].getKey()) < 0)
			small = right(i);
		if (small != i) {
			swap(i, small);
			downheap(small);
		}
	}

	@SuppressWarnings("unchecked")
	private void resize() {
		heap = Arrays.copyOf(heap, heap.length * 2);
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Entry<K, V> insert(K k, V v) {
		if (size == heap.length)
			resize();
		PQEntry<K, V> e = new PQEntry<>(k, v, size);
		heap[size] = e;
		size++;
		upheap(size - 1);
		return e;
	}

	public Entry<K, V> min() {
		return isEmpty() ? null : heap[0];
	}

	public Entry<K, V> removeMin() {
		if (isEmpty())
			return null;
		Entry<K, V> res = heap[0];
		heap[0] = heap[--size];
		if (size > 0) {
			((PQEntry<K, V>) heap[0]).idx = 0;
			downheap(0);
		}
		return res;
	}

	private PQEntry<K, V> validate(Entry<K, V> e) {
		return (PQEntry<K, V>) e;
	}

	public void remove(Entry<K, V> e) {
		PQEntry<K, V> loc = validate(e);
		int i = loc.idx;
		swap(i, size - 1);
		size--;
		downheap(i);
		upheap(i);
	}

	public void replaceKey(Entry<K, V> e, K k) {
		PQEntry<K, V> loc = validate(e);
		loc.k = k;
		downheap(loc.idx);
		upheap(loc.idx);
	}

	public void replaceValue(Entry<K, V> e, V v) {
		validate(e).v = v;
	}
}
