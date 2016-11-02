public class Node<K extends Comparable<K>, V>{
    public Node left, right, parent;
    K key;
    V value;
    int balance;
    Node(K k, Node<K, V> above, Node<K, V> leftChild, Node<K, V> rightChild, V v){
        this.key = k;
        this.value = v;
        parent = above;
        left = leftChild;
        right = rightChild;
        balance = 0;
    }
    Node(K k, V v){this(k, null, null, null, v); }
    Node(K k){ this(k, null); }
}