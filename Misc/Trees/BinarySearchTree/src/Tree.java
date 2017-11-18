public class Tree<T extends Comparable<T>> {
    protected Node<T> root = null;
    private int size = 0;

    protected Node<T> createNode(T t, Node<T> parent, Node<T> left, Node<T> right){
        return new Node<T>(t, parent, left, right);
    }



    public void add(T newData) {
        if(root == null){
            root = new Node<T>(newData);
            return;
        }

        Node<T> current = root;
        Node<T> parent = null;
        while (true){
             parent = current;
             if(newData.compareTo(current.data) == -1){
                 current = current.left;
                 if (current == null){
                     parent.left = new Node(newData);
                     current = parent.left;
                     current.parent = parent;
                     return;
                 }
             }
             else {
                 current = current.right;
                 if (current == null){
                     parent.right = new Node(newData);
                     current = parent.right;
                     current.parent = parent;
                     return;
                 }
             }
        }
    }



    public Node<T> find(T info) {
        Node<T> current = root;

        while (current != null) {
            switch (current.data.compareTo(info)){
                case 0: return current;
                case +1: current = current.left;
                    break;
                case -1: current = current.right;
            }
        }
        return null;
    }

    public int height(Node<T> p){
        if(p == null)
            return 0;
        int leftHeight = height(p.left);
        int rightHeight = height(p.right);
        return (leftHeight > rightHeight) ? leftHeight + 1 : rightHeight + 1;
    }


    public Node<T> remove(T info) {
        Node<T> current = root;
        Node<T> parent = root;
        boolean isLeftChild = false;

        while (current.data != info) {
            parent = current;
            if(info.compareTo(current.data) == -1){
                isLeftChild = true;
                current = current.left;
            }
            else {
                isLeftChild = false;
                current = current.right;
            }
            if (current == null)
                return null;
        }

        // if node has no children
        if(current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            }
            if (isLeftChild == true)
                parent.left = null;
            else
                parent.right = null;
        }

        // if node has one child
        else if(current.right == null){
            if(current == root)
                root = current.left;
            else if(isLeftChild)
                parent.left = current.left;
            else
                parent.right = current.left;
        }
        else if(current.left == null){
            if(current == root)
                root = current.right;
            else if(isLeftChild)
                parent.left = current.right;
            else
                parent.right = current.right;
        }

        // if node has two children
        else if(current.left != null && current.right != null){
            Node<T> successor = successor(current);
            if(current == root)
                root = successor;
            else if(isLeftChild)
                parent.left = successor;
            else
                parent.right = successor;
            successor.left = current.left;
        }
        return current;
    }

    public Node<T> successor(Node<T> node){
        if(node == null)
            return null;
        if(node.right != null){
            return findMin(node.right);
        }

        Node<T> p = node.parent;
        while (p != null && node == p.right){
            node = p;
            p = p.parent;
        }
        return p;
    }

    public Node<T> findMin(Node<T> node){
        if(node == null)
            return null;
        Node<T> current = node;
        while (current.left != null){
            current = current.left;
        }
        return current;
    }

    public Node<T> predecessor(Node<T> node){
       if(node == null)
           return null;
        if(node.left != null)
            return findMax(node.left);

        Node<T> p = node.parent;
        while (p != null && node == p.left){
            node = p;
            p = p.parent;
        }
        return p;
    }

    public Node<T> findMax(Node<T> node){
        if(node == null)
            return null;
        Node<T> current = node;
        while (current.right != null)
            current = current.right;
        return current;
    }


    public void displayInOrder(Node<T> root){
        if(root != null){
            displayInOrder(root.left);
            System.out.print(" " + root.data);
            displayInOrder(root.right);
        }
    }

    public void displayInOrder(){
        if(root != null){
            displayInOrder(root.left);
            System.out.print(" " + root.data);
            displayInOrder(root.right);
        }
        System.out.println();
    }

    public void displayKEK(){
        System.out.println();
        if(root != null){
            System.out.print(" " + root.data);
            displayInOrder(root.left);
            displayInOrder(root.right);
        }
    }
}
