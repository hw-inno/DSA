public interface Map<K extends  Comparable<K>, V> {
    int size();
    void put(K key, V value);
    Node<K,V> get(K key);
    V delete(K key);
    boolean isContains(K key);
    void traverse(K from, K to);
    void display();
    void levelOrderDisplay();
}
