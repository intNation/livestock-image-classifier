package acsse.csc3a.ds.graph;

import acsse.csc3a.ds.list.Position;

public class Edge<E> {
	private Position<?> u, v;
	private E weight;

	Edge(Position<?> u, Position<?> v, E w) {
		this.u = u;
		this.v = v;
		this.weight = w;
	}
	
	public E getWeight() {
		return weight;
	}

	public Position<?> getU() {
		return u;
	}

	public Position<?> getV() {
		return v;
	}
}