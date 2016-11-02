public class LinkedList<E>  {

    private static class Node<E>{
        E element;
        Node next;

        public Node(E e, Node<E> n){
            this.element = e;
            this.next = n;
        }

        public E getElement(){ return element;}
        public Node<E> getNext() { return next;}
        public void setNext(Node<E> n) { this.next = n; }
    }

    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    public E getFirst(){
        if(isEmpty())
            return null;
        return head.getElement();
    }

    public E getLast(){
        if( isEmpty() )
            return null;
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<E>(e, head);
        if (size == 0)
            tail = head;
        size++;
    }

    public void addLast(E e){
        if (size == 0) {
            addFirst(e);
            return;
        }
        Node<E> temp = tail;
        tail = new Node<E>(e, tail);
        temp.next = tail;
        temp = null;
        tail.next = null;
        size++;
    }

    public void PrintAll(){
        if(isEmpty()){
            System.out.println("List is empty \n");
            return;
        }
        Node<E> temp = head;
        while (temp != null){
            System.out.print(temp.getElement() + " ");
            temp = temp.getNext();
        }
        System.out.println();
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public E removeFirst(){
        if(isEmpty())
            return null;
        E answer = head.getElement();
        head = head.next;
        size--;
        if(size == 0)
            tail = null;
        return answer;
    }
}
