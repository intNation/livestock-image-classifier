package acsse.csc3a.ds.priorityqueue;

import acsse.csc3a.util.Entry;

public interface PriorityQueue<K extends Comparable<K>, V> {
	int size();

	boolean isEmpty();

	Entry<K, V> insert(K k, V v);

	Entry<K, V> removeMin();

	Entry<K, V> min();

	void remove(Entry<K, V> e);

	void replaceKey(Entry<K, V> e, K k);

	void replaceValue(Entry<K, V> e, V v);

	
}