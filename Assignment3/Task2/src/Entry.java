public class Entry<K extends Comparable<K>,V> {
    K key;
    V value;
    public Entry(K k, V v){
        key = k;
        value = v;
    }
    public Entry(){this(null, null);}
}
