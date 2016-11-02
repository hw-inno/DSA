public class LinkedStack<E> implements Stack<E> {

    private LinkedList<E> list = new LinkedList<E>();
    public LinkedStack(){}


    @Override
    public int size() {return list.size();}

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E element) { list.addFirst(element);}

    @Override
    public E top() {
        return list.getFirst();
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }
}
