package acsse.csc3a.ds.graph;

import acsse.csc3a.ds.list.Position;

public class VPos<V> implements Position<V> {
	V elem;

	VPos(V e) {
		elem = e;
	}

	public V getElement() {
		return elem;
	}
}