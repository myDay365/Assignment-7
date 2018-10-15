package assignment6;

import java.util.NoSuchElementException;

public class MyLinkedList<T> implements List<T> {

	Node<T> head;

	Node<T> tail;

	int size;

	public MyLinkedList() {

		size = 0;

		head = new Node<>();

		tail = new Node<>();

	}

	@Override

	public void addFirst(T element) {

		if (head == null && tail == null) {

			Node<T> temp = new Node<T>(element);

			head = temp;

			tail = temp;

		}

		else if (head != null && tail != null) {

			Node<T> newHead = new Node<T>(element);

			newHead.setNext(head);

			head.setPrev(newHead);

			head = newHead;

		}

		size++;

	}

	@Override

	public void addLast(T element) {

		if (head == null && tail == null) {

			Node<T> temp = new Node<T>(element);

			head = temp;

			tail = temp;

		}

		else if (head != null && tail != null) {

			Node<T> newTail = new Node<T>(element);

			newTail.setPrev(tail);

			tail.setNext(newTail);

			tail = newTail;

		}

		size++;

	}

	@Override

	public void add(int index, T element) throws IndexOutOfBoundsException {

		if (index > size + 1) {

			throw new IndexOutOfBoundsException();

		}

		if (index == size) {

			addLast(element);

			return;

		}

		if (index == 0) {

			addFirst(element);

			return;

		}

		Node<T> temp = new Node<T>(element);

		Node<T> left = getNode(index - 1);

		Node<T> right = getNode(index);

		left.setNext(temp);

		right.setPrev(temp);

		temp.setNext(right);

		temp.setPrev(left);

		size++;

		return;

	}

	@Override

	public T getFirst() throws NoSuchElementException {

		if (head == null && tail == null) {

			throw new NoSuchElementException();

		}

		else {

			return head.data();

		}

	}

	@Override

	public T getLast() throws NoSuchElementException {

		if (head == null && tail == null) {

			throw new NoSuchElementException();

		}

		else {

			return tail.data();

		}

	}

	@Override

	public T get(int index) throws IndexOutOfBoundsException {

		return getNode(index).data();

	}

	@Override

	public T removeFirst() throws NoSuchElementException {

		if (head == null && tail == null) {

			throw new NoSuchElementException();

		}

		else {

			T temp = head.data();

			head = head.next();

			size--;

			return temp;

		}

	}

	@Override

	public T removeLast() throws NoSuchElementException {

		if (head == null && tail == null) {

			throw new NoSuchElementException();

		}

		else {

			T temp = tail.data();

			tail = tail.prev();

			size--;

			return temp;

		}

	}

	@Override

	public T remove(int index) throws IndexOutOfBoundsException {

		if (index > size + 1) {

			throw new IndexOutOfBoundsException();

		}

		T temp = get(index);

		if (index == 0) {

			removeFirst();

		}

		if (index == size - 1) {

			removeLast();

		}

		Node<T> left = getNode(index - 1);

		Node<T> right = getNode(index + 1);

		left.setNext(right);

		right.setPrev(left);

		size--;

		return temp;

	}

	@Override

	public int indexOf(T element) {

		Node<T> currentNode = head;

		int i = 0;

		for (i = 0; i < size; i++) {

			if (currentNode.data() == element) {

				return i;

			}

			currentNode = currentNode.next();

		}

		return -1;

	}

	@Override

	public int lastIndexOf(T element) {

		Node<T> currentNode = head;

		int biggestIndex = -1;

		int i = 0;

		for (i = 0; i < size; i++) {

			if (currentNode.data() == element) {

				biggestIndex = i;

			}

			currentNode = currentNode.next();

		}

		return biggestIndex;

	}

	@Override

	public int size() {

		return size;

	}

	@Override

	public boolean isEmpty() {

		if (size == 0) {

			return true;

		}

		return false;

	}

	@Override

	public void clear() {

		head = null;

		tail = null;

		size = 0;

	}

	@Override

	public Object[] toArray() {

		Node<T> currentNode = head;

		Object[] tempArray = new Object[size];

		for (int i = 0; i < size; i++) {

			tempArray[i] = currentNode.data();

			currentNode = currentNode.next();

		}

		return tempArray;

	}

	private Node<T> getNode(int index) {

		if (index > size + 1) {

			throw new IndexOutOfBoundsException();

		}

		if (index == 0) {

			return head;

		}

		if (index == size - 1) {

			return tail;

		}

		if (index < (size / 2)) {

			Node<T> currentNode = head;

			for (int i = 0; i < index; i++) {

				currentNode = currentNode.next();

			}

			return currentNode;

		}

		Node<T> currentNode = tail;

		for (int i = size - 1; i > index; i--) {

			currentNode = currentNode.prev();

		}

		return currentNode;

	}

	private class Node<T> {

		private T data;

		private Node<T> prev;

		private Node<T> next;

		public Node() {
			prev = null;
			next = null;
		}

		public Node(T myData) {

			this.data = myData;

			prev = null;

			next = null;

		}

		public T data() {

			return data;

		}

		public Node<T> next() {

			return next;

		}

		public void setNext(Node<T> newNext) {

			next = newNext;

		}

		public Node<T> prev() {

			return prev;

		}

		public void setPrev(Node<T> newPrev) {

			prev = newPrev;

		}

	}

}