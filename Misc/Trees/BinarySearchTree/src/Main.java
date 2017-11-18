public class Main {
    public static void main(String[] args) {
        Tree<Integer> t = new Tree<>();

        t.add(25);
        t.add(12);
        t.add(29);
        t.add(20);
        t.add(166);
        t.add(6);

        Node<Integer> n = t.find(12);
        System.out.println(n.data);
        System.out.println("Successor of " + n.data + " is "
                + t.successor(n).data);
        System.out.println("Predecessor of " + n.data + " is "
                + t.predecessor(n).data);
    }
}
