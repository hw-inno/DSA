public interface Map<T extends Comparable<T>> {
    Node<T> find(T t);
    void add(T t);
    T remove(T t);

}
