public class Node<T extends Comparable<T>>{
    public Node left, right, parent;
    T data;
    Node(T t, Node<T> above, Node<T> leftChild, Node<T> rightChild){
        data = t;
        parent = above;
        left = leftChild;
        right = rightChild;
    }
    Node(T t){ this(t, null, null, null); }
}