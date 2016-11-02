/*--- Simple Priority Queue implementation using array-based min heap ---*//*


import java.util.ArrayList;

public class MinHeapPQ<K extends Comparable<K>,V> implements PriorityQueue <K,V> {
   protected ArrayList<Entry<K,V>> heap = new ArrayList<>();

    protected int parent(int i){return ((i-1)/2);}
    protected int left(int i){return 2*i + 1;}
    protected int right(int i) {return 2*i + 2;}
    protected boolean hasLeft(int i){return left(i) < heap.size();}
    protected boolean hasRight(int i){return right(i) < heap.size();}

    protected void upheap(int i){
        K parent;
        K child;

        while(i > 0){
            parent = heap.get(parent(i)).key;
            child = heap.get(i).key;

            if(parent.compareTo(child) < 0)
                break;
            swap(i, parent(i));
            i = parent(i);
        }
    }

    protected void downheap(){
        int i = 0;
        K left, right;

        while (hasLeft(i)){
            int leftIndex = left(i);
            int smaller = leftIndex;
            left = heap.get(leftIndex).key;

            if(hasRight(i)){
                int rightIndex = right(i);
                right = heap.get(rightIndex).key;
                if(left.compareTo(right) > 0)
                    smaller = rightIndex;
            }


            if(heap.get(smaller).key.compareTo(heap.get(i).key) >= 0)
                break;
            swap(i, smaller);
            i = smaller;
        }
    }

    protected void swap(int i, int j){
        Entry<K,V> a = heap.get(i);
        Entry<K,V> b = heap.get(j);

        heap.set(i, b);
        heap.set(j, a);
    }

    public void display(){
        for(Entry<K,V> e : heap)
            System.out.println(e.key + " : " + e.value);
        System.out.println();
    }

    @Override
    public int size() {return heap.size();}

    @Override
    public boolean isEmpty() {return size() == 0;}

    @Override
    public void add(K key, V value) {
        Entry<K,V> newest = new Entry<K, V>(key, value);
        heap.add(newest);
        upheap(heap.size() - 1);
    }

    @Override
    public Entry peek() {
        if(heap.isEmpty())
            return null;
        return heap.get(0);
    }

    @Override
    public Entry poll() {
        if(isEmpty())
           return null;
        Entry<K,V> temp = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        downheap();
        return temp;
    }
}
*/
