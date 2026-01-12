package acsse.csc3a.ds.map;

class MapEntry<K, V> {
	K k;
	V v;

	MapEntry(K k, V v) {
		this.k = k;
		this.v = v;
	}

	public K getKey() {
		return k;
	}

	public V getValue() {
		return v;
	}

	void setValue(V v) {
		this.v = v;
	}
}