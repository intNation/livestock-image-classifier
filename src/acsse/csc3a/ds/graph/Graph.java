package acsse.csc3a.ds.graph;

import acsse.csc3a.ds.list.*;

public interface Graph<V, E> {
	int numVertices();

	int numEdges();

	Iterable<Position<V>> vertices();

	Iterable<Edge<E>> edges();

	E getEdge(Position<V> u, Position<V> v);

	Position<V> insertVertex(V v);

	Edge<E> insertEdge(Position<V> u, Position<V> v, E w);

	V removeVertex(Position<V> v);

	E removeEdge(Edge<E> e);
}
