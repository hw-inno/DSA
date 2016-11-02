import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList<E> implements List<E> {

    public static final int CAPACITY = 16;
    private E[] data;
    private  int size = 0;

    public MyArrayList(){ this(CAPACITY);}
    public MyArrayList(int capacity) {
        data = (E[]) new Object[capacity];
    }

    protected void checkIndex(int index, int n) throws IndexOutOfBoundsException{
        if (index < 0 || index >= n)
            throw new IndexOutOfBoundsException("Illegal index: " + index);
    }


    @Override
    public int size() {return size;}

    @Override
    public boolean isEmpty() {return size == 0;}

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        checkIndex(index, size);
        return data[index];
    }

    @Override
    public E set(int index, E element) throws IndexOutOfBoundsException{
        checkIndex(index, size);
        E temp = data[index];
        data[index] = element;
        return temp;
    }

    @Override
    public void add(int index, E element) throws IndexOutOfBoundsException,
            IllegalStateException{
        checkIndex(index, size + 1);
        if(size == data.length)
            throw new IllegalStateException("Array is Full");
        for (int j = size - 1; j >= index; j--)
            data[j + 1] = data[j];
        data[index] = element;
        size++;
    }

    @Override
    public boolean add(E e) throws IndexOutOfBoundsException,
            IllegalStateException{
        if(size() == data.length){
            throw new IllegalStateException("Array is Full");
        }

        data[size++] = e;
        return true;
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException{
        checkIndex(index, size);
        E temp = data[index];
        for (int j = index; j < size - 1; j++)
            data[j] = data[j+1];
        data[size-1] = null;
        size--;
        return temp;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}
