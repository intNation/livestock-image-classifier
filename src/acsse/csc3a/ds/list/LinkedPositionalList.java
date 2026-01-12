package acsse.csc3a.ds.list;

import java.util.Iterator;

public class LinkedPositionalList<E> implements PositionalList<E> {
	// ----- nested Node class -----
	private static class Node<E> implements Position<E> {
		E element;
		Node<E> prev, next;

		Node(E e, Node<E> p, Node<E> n) {
			element = e;
			prev = p;
			next = n;
		}

		public E getElement() {
			return element;
		}
	}

	// ----- sentinel nodes -----
	private Node<E> header = new Node<>(null, null, null);
	private Node<E> trailer = new Node<>(null, header, null);
	private int size = 0;

	public LinkedPositionalList() {
		header.next = trailer;
	}

	// ------ utility ------
	private Node<E> validate(Position<?> p) {
		if (!(p instanceof Node))
			throw new IllegalArgumentException();
		return (Node<E>) p;
	}

	private Position<E> position(Node<E> n) {
		return n == header || n == trailer ? null : n;
	}

	// ------ interface impl ------
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Position<E> first() {
		return position(header.next);
	}

	public Position<E> last() {
		return position(trailer.prev);
	}

	public Position<E> before(Position<E> p) {
		return position(validate(p).prev);
	}

	public Position<E> after(Position<E> p) {
		return position(validate(p).next);
	}

	private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
		Node<E> newest = new Node<>(e, pred, succ);
		pred.next = newest;
		succ.prev = newest;
		size++;
		return newest;
	}

	public Position<E> addFirst(E e) {
		return addBetween(e, header, header.next);
	}

	public Position<E> addLast(E e) {
		return addBetween(e, trailer.prev, trailer);
	}

	public Position<E> addAfter(Position<E> p, E e) {
		Node<E> node = validate(p);
		return addBetween(e, node, node.next);
	}

	public Position<E> addBefore(Position<E> p, E e) {
		Node<E> node = validate(p);
		return addBetween(e, node.prev, node);
	}

	public E remove(Position<?> p) {
		Node<E> node = validate(p);
		node.prev.next = node.next;
		node.next.prev = node.prev;
		size--;
		E ans = node.element;
		node.prev = node.next = null;
		return ans;
	}

	public E set(Position<E> p, E e) {
		Node<E> node = validate(p);
		E old = node.element;
		node.element = e;
		return old;
	}

	// ------ iterator ------
	private class PosIterator implements Iterator<E> {
		Position<E> cursor = first();

		public boolean hasNext() {
			return cursor != null;
		}

		public E next() {
			E val = cursor.getElement();
			cursor = after(cursor);
			return val;
		}
	}

	public Iterator<E> iterator() {
		return new PosIterator();
	}
}