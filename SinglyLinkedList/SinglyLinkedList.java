import java.util.Scanner;
public class SinglyLinkedList<E>{
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
    // instance variables of the SinglyLinkedList
    private Node<E> head = null;   /*head node of the 'list'(or null if empty)*/
    private Node<E> tail = null;   // last 'node'(or null if empty)
    private int size = 0;         //number of nodes in the list
    public SinglyLinkedList(){}
    //access methods
    public int size(){return size;}
    public boolean isEmpty(){return size == 0;}
    public E first(){            //returns(but does not remove) the first element
      if(isEmpty()) return null;
      return head.getElement();
    }
    public E last(){      //returns(but does not remove) the last element
      if(isEmpty()) return null;
      return tail.getElement();
    }
    //update methods
    public void addFirst(E e){        //adds element e to the front of the list
      head = new Node<>(e, head);   //create and link a new node
      if(size == 0){
        tail = head;       //special case: new node becomes tail also
      }
      size++;
    }
    public void addLast(E e){   //adds element e to the end of the list
      Node<E> newest = new Node<>(e, null);   //node will eventually be the tail
      if(isEmpty()){          
        head = newest;      //special case: previously empty list
      }else{
        tail.setNext(newest); //new node after existing tail
      }
      tail = newest;        //new node becomes the tail
      size++;
    }
    public E removeFirst(){     //removes and returns the first element
      if(isEmpty())  return null; //nothing to remove
      E answer = head.getElement();
      head = head.getNext();      //will become null if list had only one node
      size --;
      if(size == 0){
        tail = null;      //special case as list is not empty
      }
      return answer;
  }
    public void insertAtPos(E val, int pos){
      Node<E> nptr = new Node<E> (val,null);
      Node<E> ptr = head;
      pos = pos - 1;
      for(int i = 1; i < size; i++){
        if(i == pos){
          Node<E> tmp = ptr.getNext();
          ptr.setNext(nptr);
          nptr.setNext(tmp);
          break;
        }
        ptr = ptr.getNext();
      }
      size++;
    }
    public void deleteAtPos(int pos){
      if(pos == 1){
        head = head.getNext();
        size--;
        return;
      }
      if(pos == size){
        Node<E> s = head;
        Node<E> t = head;
        while(s != tail){
          t = s;
          s = s.getNext();
        }
        tail = t;
        tail.setNext(null);
        size--;
        return;
      }
      Node<E> ptr = head;
      pos = pos - 1;
      for(int i = 1; i < size -1; i++){
        if(i == pos){
          Node<E> tmp = ptr.getNext();
          tmp = tmp.getNext();
          ptr.setNext(tmp);
          break;
        }
        ptr = ptr.getNext();
      }
      size --;
    }
    public void display(){
      System.out.print("\nSingly Linked List = ");
      if(size == 0){
        System.out.print ("empty\n");
        return;
      }
      if (head.getNext() == null) {
          System.out.println(head.getElement());
          return;
      }
      Node<E> ptr = head;
      System.out.print(head.getElement() + "->");
      ptr = head.getNext();
      while(ptr.getNext() != null){
        System.out.print(ptr.getElement() + "->");
        ptr = ptr.getNext();
      }
      System.out.print(ptr.getElement() + "\n");
    }
    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
        System.out.println("SinglyLinkedList Test\n");
        char ch;
        do{
          System.out.println("\nSingly Linked List Operations\n");
          System.out.println("1.insert at begining");
          System.out.println("2.insert at end");
          System.out.println("3.remove nodes");
          System.out.println("4.get size");
          System.out.println("5.check whether it is empty list");
          System.out.println("6. Get the first number");
          System.out.println("7. Get the last number");
          System.out.println("8. Insert at position");
          System.out.println("9. Delete at position");
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
            case 8:
             System.out.println("Enter integer element to insert");
             int num = scan.nextInt();
             System.out.println("Enter position");
             int pos = scan.nextInt();
             if(pos <= 1|| pos > list.size()){
               System.out.println("Invalid position input");
             }else{
               list.insertAtPos(num, pos);
             }
             break;
            case 9:
              System.out.println("Enter position");
              int p = scan.nextInt();
              if(p < 1 || p > list.size()){
                System.out.println("Invalid position\n");
              }else{
                list.deleteAtPos(p);
              }
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