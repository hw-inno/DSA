import java.util.LinkedList;
import java.util.Queue;

public class Tree<K extends Comparable<K>, V> implements Map<K,V>{
    protected Node<K, V> root = null;

    protected int size = 0;

    public void add(K  newKey) {
        size++;
        if (root == null) {
            root = new Node<K, V>(newKey);
            return;
        }

        Node<K, V> current = root;
        Node<K, V> parent = null;
        while (true) {
            parent = current;
            if(newKey.compareTo(current.key) == 0 ){
                System.out.println("This key have been already included");
                return;
            }
            else if(newKey.compareTo(current.key) < 0) {
                current = current.left;
                if (current == null) {
                    parent.left = new Node(newKey);
                    current = parent.left;
                    current.parent = parent;
                    rebalance(current);
                    return;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = new Node(newKey);
                    current = parent.right;
                    current.parent = parent;
                    rebalance(current);
                    return;
                }
            }
        }
    }
    public int height(Node<K, V> p) {
        if (p == null)
            return 0;
        int leftHeight = height(p.left);
        int rightHeight = height(p.right);
        return (leftHeight > rightHeight) ? leftHeight + 1 : rightHeight + 1;
    }
    public Node<K, V> remove(K delKey) {
        Node<K, V> current = root;
        Node<K, V> parent = root;
        boolean isLeftChild = false;

        while (current.key != delKey) {
            parent = current;
            if (delKey.compareTo(current.key) < 0) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }
            if (current == null)
                return null;
        }

        size--;
        // if node has no children
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            }
            if (isLeftChild == true)
                parent.left = null;
            else
                parent.right = null;
        }

        // if node has one child
        else if (current.right == null) {
            if (current == root)
                root = current.left;
            else if (isLeftChild)
                parent.left = current.left;
            else
                parent.right = current.left;
        } else if (current.left == null) {
            if (current == root)
                root = current.right;
            else if (isLeftChild)
                parent.left = current.right;
            else
                parent.right = current.right;
        }

        // if node has two children
        else if (current.left != null && current.right != null) {
            Node<K, V> successor = successor(current);
            if (current == root)
                root = successor;
            else if (isLeftChild)
                parent.left = successor;
            else
                parent.right = successor;
            successor.left = current.left;
        }
        rebalance(current);
        return current;
    }

    /*-------------------- Protected Utility functions  ---------------------------*/
    protected Node<K, V> successor(Node<K, V> node) {
        if (node == null)
            return null;
        if (node.right != null) {
            return findMin(node.right);
        }

        Node<K, V> p = node.parent;
        while (p != null && node == p.right) {
            node = p;
            p = p.parent;
        }
        return p;
    }
    protected Node<K, V> findMin(Node<K, V> node) {
        if (node == null)
            return null;
        Node<K, V> current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }
    protected Node<K, V> predecessor(Node<K, V> node) {
        if (node == null)
            return null;
        if (node.left != null)
            return findMax(node.left);

        Node<K, V> p = node.parent;
        while (p != null && node == p.left) {
            node = p;
            p = p.parent;
        }
        return p;
    }
    protected Node<K, V> findMax(Node<K, V> node) {
        if (node == null)
            return null;
        Node<K, V> current = node;
        while (current.right != null)
            current = current.right;
        return current;
    }
    protected void display(Node<K, V> root) {
        if (root != null) {
            display(root.left);
            System.out.print(" " + root.key);
            display(root.right);
        }
    }
    /*----------------- End of Protected Utility functions  ----------------------*/

    /* ----------------------------- Balancing functions ------------------------------------------- */
    protected void rebalance(Node<K, V> node) {
        setBalance(node);
        int balance = node.balance;

        if (balance == -2) {
            if (height(node.left.left) >= height(node.left.right))
                node = rotateRight(node);
            else
                node = rotateLeftRight(node);
        } else if (balance == 2) {
            if (height(node.right.right) >= height(node.right.left))
                node = rotateLeft(node);
            else
                node = rotateRightLeft(node);
        }

        if (node.parent != null) {
            rebalance(node.parent);
        } else {
            this.root = node;
            //System.out.println("BALANCED");
        }
    }
    protected void setBalance(Node<K, V> node) {
        node.balance = height(node.right) - height(node.left);
    }
    protected Node<K, V> rotateLeft(Node<K, V> n) {
        Node<K, V> ref = n.right;
        ref.parent = n.parent;

        n.right = ref.left;
        if (n.right != null)
            n.right.parent = n;

        ref.left = n;
        n.parent = ref;

        if (ref.parent != null) {
            if (ref.parent.right == n) {
                ref.parent.right = ref;
            } else if (ref.parent.left == n) {
                ref.parent.left = ref;
            }
        }

        setBalance(n);
        setBalance(ref);
        return ref;
    }
    protected Node<K, V> rotateRight(Node<K, V> n) {
        Node<K, V> ref = n.left;
        ref.parent = n.parent;

        n.left = ref.right;

        if (n.left != null) {
            n.left.parent = n;
        }

        ref.right = n;
        n.parent = ref;


        if (ref.parent != null) {
            if (ref.parent.right == n) {
                ref.parent.right = ref;
            } else if (ref.parent.left == n) {
                ref.parent.left = ref;
            }
        }

        setBalance(n);
        setBalance(ref);

        return ref;
    }
    protected Node<K, V> rotateLeftRight(Node<K, V> n) {
        n.left = rotateLeft(n.left);
        return rotateRight(n);
    }
    protected Node<K, V> rotateRightLeft(Node<K, V> n) {
        n.right = rotateRight(n.right);
        return rotateLeft(n);
    }
    /* ------------------------ End of Balancing functions ----------------------------------- */

    /*----------------- Public interface methods ----------------------*/
    @Override
    public int size(){return size;}
    @Override
    public boolean isContains(K key) {
        return get(key) != null;
    }
    @Override
    public void put(K newKey, V newVal){
        size++;
        if (root == null) {
            root = new Node<K, V>(newKey, newVal);
            return;
        }

        Node<K, V> current = root;
        Node<K, V> parent = null;
        while (true) {
            parent = current;
            if(newKey.compareTo(current.key) == 0 ){
                System.out.println("Already in tree");
                return;
            }
            else if(newKey.compareTo(current.key) < 0) {
                current = current.left;
                if (current == null) {
                    parent.left = new Node(newKey, newVal);
                    current = parent.left;
                    current.parent = parent;
                    rebalance(current);
                    return;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = new Node(newKey, newVal);
                    current = parent.right;
                    current.parent = parent;
                    rebalance(current);
                    return;
                }
            }
        }
    }
    @Override
    public Node<K, V> get(K key) {
        Node<K, V> current = root;

        while (current != null) {
            if(key.compareTo(current.key) == 0)
                return current;
            else if(key.compareTo(current.key) < 0)
                current = current.left;
            else
                current = current.right;
        }
        return null;
    }
    @Override
    public V delete(K delKey) {
        Node<K, V> current = root;
        Node<K, V> parent = root;
        boolean isLeftChild = false;

        while (current.key != delKey) {
            parent = current;
            if (delKey.compareTo(current.key) < 0) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }
            if (current == null)
                return null;
        }

        size--;
        // if node has no children
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            }
            if (isLeftChild == true)
                parent.left = null;
            else
                parent.right = null;
        }

        // if node has one child
        else if (current.right == null) {
            if (current == root)
                root = current.left;
            else if (isLeftChild)
                parent.left = current.left;
            else
                parent.right = current.left;
        } else if (current.left == null) {
            if (current == root)
                root = current.right;
            else if (isLeftChild)
                parent.left = current.right;
            else
                parent.right = current.right;
        }

        // if node has two children
        else if (current.left != null && current.right != null) {
            Node<K, V> successor = successor(current);
            if (current == root)
                root = successor;
            else if (isLeftChild)
                parent.left = successor;
            else
                parent.right = successor;
            successor.left = current.left;
        }
        rebalance(current);
        return current.value;
    }
    @Override
    public void display() {
        if (root != null) {
            display(root.left);
            System.out.println(" " + root.key + ":" + root.value);
            display(root.right);
        }
        System.out.println();
    }
    @Override
    public void levelOrderDisplay() {
        Node<K, V> start = root;
        Queue<Node<K, V>> queue = new LinkedList<>();
        queue.add(start);
        int k = 0, i = 0;
        int[] arr = {1, 2, 4, 8, 16};

        while (!queue.isEmpty()) {
            Node<K, V> temp = queue.poll();
            System.out.print(temp.key + " ");
            k++;

            if (k == Math.pow(2, i)) {
                k = 0;
                System.out.println();
                i++;
            }

            if (temp.left != null)
                queue.add(temp.left);
            if (temp.right != null)
                queue.add(temp.right);
        }
    }
    @Override
    public void traverse(K from, K to) {
        Node<K, V> start, end, temp;
        start = get(from);
        temp = start;
        end = get(to);
        if (start == null || end == null) {
            System.out.println("\nWrong key");
            return;
        }
        while (temp != end) {
            System.out.println(temp.key);
            temp = successor(temp);
        }
        System.out.println(temp.key);
    }
    /*------------- End of Public interface methods ------------------*/
}