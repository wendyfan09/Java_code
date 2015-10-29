// A basic doubly linked list implementation
public class DoublyLinkedList<E>{
	// --------Nested Node Clase ----------
	private static class Node<E>{
		private E element; 		//reference to the element stored at this node
		private Node<E> prev;	//reference to the previous node in the list
		private Node<E> next;   //reference to the subsequent node in the list
		public Node(E e, Node<E> p, Node<E> n){
			element = e;
			prev = p;
			next = n;
		}
		public E getElement(){return element;}
		public Node<E> getPrev(){return prev;}
		public Node<E> getNext(){return next;}
		public void setPrev(Node<E> p){prev = p;}
		public void setNext(Node<E> n){next = n;}
	}// end of nested node class

	//instance variables of the DoublyLinkedlist
	private Node<E> header;			//header sentinel
	private Node<E> trailer;			//tailer sentinel
	private int size = 0;			//number of elements in the list
	//Constructs a new empty list
	public DoublyLinkedlist(){
		header = new Node<>(null, null, null); 	//create header
		trailer = new Node<>(null, head, null);	//trailer is preceded by header
		header.setNext(trailer);				//header is followed by trailer
	}
	//Returns the number of elements in the linked list
	public int size(){return size;}
	//Tests whether the linked list is empty
	public boolean isEmpty(){return size == 0;}
	//Returns(but not removes) the first/last element of the list
	public E first(){
		if(isEmpty()) return null;
		return header.getNext().getElement();   //first element is beyond header
	}
	public E last(){
		if(isEmpty()) return null;
		return trailer.getPrev().getElement(); //last element is before tailer
	}
	//public update methods
	//Add element e to the front of the list
	public void addFirst(E e){
		addBetween(e, header, header.getNext());  
	}
	//Add element e to the end of the list
	public void addLast(E e){
		addBetween(e,trailer.getPrev(),trailer);
	}
	//Removes and returns the first element of the list
	public E removeFirst(){
		if(isEmpty()) return null;
		return remove(header.getNext());
	}
	public E removeLast(){
		if(isEmpty()) return null;
		return remove(trailer.getPrev());
	}

	//private update methods
	private void addBetween(E e, Node<E> predecessor, Node<E> successor){
		//create and link a new node
		Node<E> newest = new Node<>(e,predecessor, successor);
		predecessor.setNext(newest);
		successor.setPrev(newest);
		size++;
	}
	//Removes the given node from the list and returns its elemnt
	private E remove(Node<E> node){
		Node<E> predecessor = node.getPrev();
		Node<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		size--;
		return node.getElement();
	}
}//end of DoublyLinkedList class
