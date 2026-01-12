package acsse.csc3a.ds.graph;

import acsse.csc3a.ds.map.*;
import acsse.csc3a.ds.list.*;

public class AdjacencyMapGraph<V, E> implements Graph<V, E> {
	
	private LinkedPositionalList<Position<V>> vertexList = new LinkedPositionalList<>();
	private ChainHashMap<Position<V>, ChainHashMap<Position<V>, Edge<E>>> incidents = new ChainHashMap<>();


	public int numVertices() {
		return vertexList.size();
	}

	public int numEdges() {
		int total = 0;
		for (Position<V> v : vertexList)
			total += incidents.get(v).size();
		return total / 2;
	}

	public Iterable<Position<V>> vertices() {
		return vertexList;
	}

	public Iterable<Edge<E>> edges() {
		LinkedPositionalList<Edge<E>> list = new LinkedPositionalList<>();
		for (Position<V> v : vertexList)
			for (Edge<E> e : incidents.get(v).values())
				list.addLast(e);
		return list;
	}

	public E getEdge(Position<V> u, Position<V> v) {
		Edge<E> e = incidents.get(u).get(v);
		return e == null ? null : e.getWeight();
	}

	public Position<V> insertVertex(V v) {
		Position<V> p = new VPos(v);
		vertexList.addLast(p);
		incidents.put(p, new ChainHashMap<>());
		return p;
	}

	public Edge<E> insertEdge(Position<V> u, Position<V> v, E w) {
		Edge<E> e = new Edge<>(u, v, w);
		incidents.get(u).put(v, e);
		incidents.get(v).put(u, e);
		return e;
	}

	public V removeVertex(Position<V> v) {
		for (Position<V> o : incidents.get(v).keySet())
			incidents.get(o).remove(v);
		incidents.remove(v);
		vertexList.remove(v);
		return v.getElement();
	}

	@SuppressWarnings("unchecked")
	public E removeEdge(Edge<E> e) {
		@SuppressWarnings("unchecked")
		Position<V> u = (Position<V>) e.getU();
		Position<V> v = (Position<V>) e.getV();
		incidents.get(u).remove(v);
		incidents.get(v).remove(u);
		return e.getWeight();
	}
}