public class LinkedQueue<E> implements Queue<E> {
    private LinkedList<E> list = new LinkedList<E>();
    public LinkedQueue(){}



    @Override
    public int size() {return list.size();}

    @Override
    public boolean isEmpty() {return list.isEmpty();}

    @Override
    public void enqueue(E element) { list.addLast(element);}

    @Override
    public E first() {return list.getFirst();}

    @Override
    public E dequeue() {return list.removeFirst();}

    public void printAll(){list.PrintAll();}
}
