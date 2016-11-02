public interface Map<K extends  Comparable<K>, V> {
    int size();
    void put(K key, V value);
    Node<K,V> get(K key);
    V delete(K key);
    boolean isContains(K key);
    void traverseFromTo(K from, K to);
    void traverse(Node<K,V> root);
}
