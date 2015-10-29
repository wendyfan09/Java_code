import java.util.Scanner;
public class CircularlyLinkedList<E>{
    // nested Node class
    private static class Node<E>{
      private E element;       //reference to the element stored at this node
      private Node<E> next;    //reference to the subsequent node in the list
      public Node(E e, Node<E> n){
        element = e;
        next = n;
      }
      public E getElement(){
        return element;
      }
      public Node<E> getNext(){
        return next;
      }
      public void setNext(Node<E> n){
        next = n;
      }
    }  //end of nested Node 

  private static final int E = 0;
    // instance variables of the CircularlyLinkedlist
    private Node<E> tail = null;   // we store 'tail'(but not head)
    private int size = 0;         //number of nodes in the list
    public CircularlyLinkedList(){}
    //access methods
    public int size(){return size;}
    public boolean isEmpty(){return size == 0;}
    public E first(){            //returns(but does not remove) the first element
      if(isEmpty()) return null;
      return tail.getNext().getElement();  //the head is 'after' the tail
    }
    public E last(){      //returns(but does not remove) the last element
      if(isEmpty()) return null;
      return tail.getElement();
    }
    public void rotate(){    //rotate the first element ot the back of the list
      if(tail != null){     //if empty, do nothing
        tail = tail.getNext();  //the old head becomes the new tail
      }
    }
    //update methods
    public void addFirst(E e){        //adds element e to the front of the list
      if(size == 0){
        tail = new Node<>(e, null);
        tail.setNext(tail);   //link to itself circularly    
      }else{
        Node<E> newest = new Node<>(e,tail.getNext());
        tail.setNext(newest);
      }
      size++;
    }
    public void addLast(E e){   //adds element e to the end of the list
      addFirst(e);              //insert new element at front of list
      tail = tail.getNext();    //now new element becomes the tail
    }
    public E removeFirst(){     //removes and returns the first element
      if(isEmpty())  return null; //nothing to remove
      Node<E> head = tail.getNext();
      if(head == tail){         // must be the only node left
        tail = null;
      }else{
        tail.setNext(head.getNext());   //removes "head" from the list
      }
      size --;
      return head.getElement();
  }
    public void display(){
        System.out.print("\n Circularly Linked List = ");
        if(size == 0){
          System.out.print ("empty\n");
          return;
        }
        Node<E> ptr = tail.getNext();
        System.out.print(tail.getNext().getElement() + "->");
        ptr = tail.getNext().getNext();
        while(ptr.getNext() != tail.getNext().getNext()){
          System.out.print(ptr.getElement() + "->");
          ptr = ptr.getNext();
        }
        System.out.print(ptr.getElement() + "\n");
    }
    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        CircularlyLinkedList<Integer> list = new CircularlyLinkedList<Integer>();
        System.out.println("CircularlyLinkedlist Test\n");
        char ch;
        do{
          System.out.println("\ncircularly Linked List Operations\n");
          System.out.println("1.insert at begining");
          System.out.println("2.insert at end");
          System.out.println("3.remove nodes");
          System.out.println("4.get size");
          System.out.println("5.check whether it is empty list");
          System.out.println("6. Get the first number");
          System.out.println("7. Get the last number");
          int choice = scan.nextInt();
          switch(choice)
          {
            case 1:
              System.out.println("Enter integer element to insert");
              list.addFirst(scan.nextInt());
              break;
            case 2:
              System.out.println("Enter integer element to insert");
              list.addLast(scan.nextInt());
              break;
            case 3:
              System.out.println("Delete the First");
              list.removeFirst();
              break;
            case 4:
              System.out.println("Size = " + list.size() + "\n");
              break;
            case 5:
              System.out.println("The list is empty?" + list.isEmpty() + "\n");
              break;
            case 6:
              System.out.println("The first number is:" + list.first() + "\n");
              break;
            case 7:
              System.out.println("The last number is:" + list.last() + "\n");
              break;
            default:
            System.out.println("Plese enter the right option \n");
            break;
          }
          list.display();
          System.out.println("\n Do you want to continue(Type y or n) \n");
          ch = scan.next().charAt(0);
        }while(ch == 'Y' || ch =='y');
    }
  }