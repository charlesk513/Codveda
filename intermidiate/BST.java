import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    public class Node {
        private Node left, right;
        private int size;
        private Key key;
        private Value value;

        public Node(Key key, Value value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    public BST() {

    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }

    public int size(Node node) {
        if (node == null)
            return 0;
        else
            return node.size;
    }

    public boolean contains(Key key) {
        if (key == null)
            throw new IllegalArgumentException("Error, null can not be a key");
        return get(key) == null;
    }

    public Node get(Key key) {
        if (root == null) {
            System.out.println("The tree is currently empty");
            return null;
        }
        return get(root, key);
    }

    public Node get(Node x, Key key) {
        if (x == null) {
            System.out.println("The tree is currently empty");
            return null;
        }
        int compare = key.compareTo(root.key);
        if (compare > 0)
            return get(x.left, key);
        else if (compare < 0)
            return get(x.right, key);
        else
            return x;
    }

    public void put(Key key, Value value) {
        if (key == null)
            throw new IllegalArgumentException("Error, null can not be a key");
        put(root, key, value);
        assert check();

    }

    public Node put(Node x, Key key, Value value) {
        if (x == null)
            return new Node(key, value, 1);
        int compare = key.compareTo(root.key);
        if (compare > 0)
            return x.right = put(x.right, key, value);
        else if (compare < 0)
            return x.left = put(x.left, key, value);
        else
            return x;

    }

    public int height() {
        return height(root);
    }

    public int height(Node root) {
        if (root == null)
            return -1;
        else {
            return 1 + Math.max(height(root.left), height(root.right));
        }
    }

    public Key min() {
        if (isEmpty())
            throw new NoSuchElementException("The tree is empty!");
        return min(root).key;
    }

    private Node min(Node root) {
        if (root.left == null)
            return root;
        else
            return min(root.left);
    }

    public Key max() {
        if (isEmpty())
            throw new NoSuchElementException("The tree is empty!");
        return max(root).key;
    }

    private Node max(Node root) {
        if (root.right == null)
            return root;
        else
            return min(root.right);
    }

    public boolean check() {

    }

    public boolean isBST() {

    }

}
