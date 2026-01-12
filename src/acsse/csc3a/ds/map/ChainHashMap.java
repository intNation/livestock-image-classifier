package acsse.csc3a.ds.map;

import acsse.csc3a.ds.list.LinkedPositionalList;
import acsse.csc3a.ds.list.Position;

public class ChainHashMap<K, V> implements Map<K, V> {


	private LinkedPositionalList<MapEntry<K, V>>[] table;
	private int size = 0;
	private int cap = 17;
	private double load = 0.75;

	@SuppressWarnings("unchecked")
	public ChainHashMap() {
		table = (LinkedPositionalList<MapEntry<K, V>>[]) new LinkedPositionalList[cap];
	}

	private int hash(K k) {
		return (k == null ? 0 : k.hashCode()) & 0x7fffffff % cap;
	}

	private LinkedPositionalList<MapEntry<K, V>> bucket(int h) {
		if (table[h] == null)
			table[h] = new LinkedPositionalList<>();
		return table[h];
	}

	private void rehash() {
		LinkedPositionalList<MapEntry<K, V>>[] old = table;
		cap *= 2;
		size = 0;
		table = (LinkedPositionalList<MapEntry<K, V>>[]) new LinkedPositionalList[cap];
		for (LinkedPositionalList<MapEntry<K, V>> lst : old)
			if (lst != null)
				for (MapEntry<K, V> e : lst)
					put(e.getKey(), e.getValue());
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public V get(K k) {
		for (MapEntry<K, V> e : bucket(hash(k)))
			if ((k == null && e.getKey() == null) || (k != null && k.equals(e.getKey())))
				return e.getValue();
		return null;
	}

	public V put(K k, V v) {
		if ((double) size / cap > load)
			rehash();
		LinkedPositionalList<MapEntry<K, V>> b = bucket(hash(k));
		for (MapEntry<K, V> e : b)
			if ((k == null && e.getKey() == null) || (k != null && k.equals(e.getKey()))) {
				V old = e.getValue();
				e.setValue(v);
				return old;
			}
		b.addLast(new MapEntry<>(k, v));
		size++;
		return null;
	}

	public V remove(K k) {
		LinkedPositionalList<MapEntry<K, V>> b = bucket(hash(k));
		
		for (Position<MapEntry<K, V>> p = b.first(); p != null; p = b.after(p)) {
			MapEntry<K, V> e = p.getElement();
			
			if ((k == null && e.getKey() == null) || (k != null && k.equals(e.getKey()))) {
				V val = e.getValue();
				b.remove(p);
				size--;
				return val;
			}
		}
		return null;
	}

	public Iterable<K> keySet() {
		LinkedPositionalList<K> ks = new LinkedPositionalList<>();
		for (LinkedPositionalList<MapEntry<K, V>> b : table)
			if (b != null)
				for (MapEntry<K, V> e : b)
					ks.addLast(e.getKey());
		return ks;
	}

	public Iterable<V> values() {
		LinkedPositionalList<V> vs = new LinkedPositionalList<>();
		for (LinkedPositionalList<MapEntry<K, V>> b : table)
			if (b != null)
				for (MapEntry<K, V> e : b)
					vs.addLast(e.getValue());
		return vs;
	}
}
