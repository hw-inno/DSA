/*--- Simple Priority Queue implementation using array-based max heap ---*/
import java.util.ArrayList;

public class PQ<K extends Comparable<K>,V>{
    private ArrayList<Entry<K,V>> heap = new ArrayList<>();

    private int parent(int i){return ((i-1)/2);}
    private int left(int i){return 2*i + 1;}
    private int right(int i) {return 2*i + 2;}
    private boolean hasLeft(int i){return left(i) < heap.size();}
    private boolean hasRight(int i){return right(i) < heap.size();}
    private void upheap(int i){
        K parent;
        K child;

        while(i > 0){
            parent = heap.get(parent(i)).key;
            child = heap.get(i).key;

            if(parent.compareTo(child) >= 0)
                break;
            swap(i, parent(i));
            i = parent(i);
        }
    }
    private void downheap(){
        int i = 0;
        K left, right;

        while (hasLeft(i)){
            int leftIndex = left(i);
            int smaller = leftIndex;
            left = heap.get(leftIndex).key;

            if(hasRight(i)){
                int rightIndex = right(i);
                right = heap.get(rightIndex).key;
                if(left.compareTo(right) <= 0)
                    smaller = rightIndex;
            }


            if(heap.get(smaller).key.compareTo(heap.get(i).key) < 0)
                break;
            swap(i, smaller);
            i = smaller;
        }
    }
    private void swap(int i, int j){
        Entry<K,V> a = heap.get(i);
        Entry<K,V> b = heap.get(j);

        heap.set(i, b);
        heap.set(j, a);
    }


    public int size() {return heap.size();}

    public boolean isEmpty() {return size() == 0;}

    public void add(K key, V value) {
        Entry<K,V> newest = new Entry<K, V>(key, value);
        heap.add(newest);
        upheap(heap.size() - 1);
    }

    public Entry<K,V> peek() {
        if(heap.isEmpty())
            return null;
        return heap.get(0);
    }

    public Entry<K,V> poll() {
        if(isEmpty())
           return null;
        Entry<K,V> temp = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        downheap();
        return temp;
    }
}
