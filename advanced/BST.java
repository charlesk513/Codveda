// class name: aTreeNode
//  description: This class makes the object of the node 
class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    // Constructor
    TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class BST {

    private TreeNode root;

    // Constructor
    public BST() {
        root = null;
    }

    /*
     * function name: put
     * arguments: int data
     * description: This function takes a value to store at that specific node and
     * calls the overloaded method
     * return type: void
     */
    public void put(int data) {
        root = put(root, data);
    }

    /*
     * function name: put
     * arguments: Node node, int data
     * description: This function takes a node and a value to store at that specific
     * node
     * return type: Node
     */
    private TreeNode put(TreeNode node, int data) {

        // If the position is empty, create a new node
        if (node == null) {
            return new TreeNode(data);
        }

        // Smaller values go to the left
        if (data < node.data) {
            node.left = put(node.left, data);

            // Larger values go to the right
        } else if (data > node.data) {
            node.right = put(node.right, data);

            // Duplicate values are ignored
        } else {
            return node;
        }

        return node;
    }

    /*
     * function name: get
     * arguments: int data
     * description: This function takes a value to check in the BST and it calls the
     * overloaded method get(node, value)
     * return type: boolean
     */
    public boolean get(int data) {
        return get(root, data);
    }

    /*
     * function name: get
     * arguments: TreeNode node, int data
     * description: This function takes a node and a value to check in the BST and
     * returns true
     * if the value exists and false otherwise
     * return type: boolean
     */
    private boolean get(TreeNode node, int data) {

        // Value was not found
        if (node == null) {
            return false;
        }

        // Value found
        if (data == node.data) {
            return true;
        }

        // Search left subtree
        if (data < node.data) {
            return get(node.left, data);
        }

        // Search right subtree
        return get(node.right, data);
    }

    /*
     * function name: delete
     * arguments: int data
     * description: This function takes a value to delete in the BST and calls te
     * overloaded delete method
     * return type: void
     */
    public void delete(int data) {
        root = delete(root, data);
    }

    /*
     * function name: delete
     * arguments: TreeNode node, int data
     * description: This function takes a node and a value to delete in the BST
     * return type: TreeNode
     */
    private TreeNode delete(TreeNode node, int data) {

        // Value was not found
        if (node == null) {
            return null;
        }

        // Search in the left subtree
        if (data < node.data) {
            node.left = delete(node.left, data);

            // Search in the right subtree
        } else if (data > node.data) {
            node.right = delete(node.right, data);

        } else {
            // Node has zero or one child

            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }
            // Node has two children
            TreeNode successor = findMinimum(node.right);

            // Replace current node's value
            node.data = successor.data;

            // Delete the duplicate successor
            node.right = delete(node.right, successor.data);
        }

        return node;
    }

    private TreeNode findMinimum() {
        return findMinimum(root);
    }

    /*
     * function name: findMinimum
     * arguments: TreeNode node
     * description: This function takes a node, looks for the leftmost node in the
     * BST
     * return type: TreeNode
     */
    private TreeNode findMinimum(TreeNode node) {

        TreeNode current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    private TreeNode findMaximum() {
        return findMaximum(root);
    }
    /*
     * function name: findMaximum
     * arguments: TreeNode node
     * description: This function takes a node, looks for the rightmost node in the
     * BST
     * return type: TreeNode
     */

    private TreeNode findMaximum(TreeNode node) {

        TreeNode current = node;

        while (current.right != null) {
            current = current.right;
        }

        return current;
    }

    /*
     * function name: inOrder
     * description: This function calls an overloaded method inOrder(TreeNode node)
     * return type: void
     */
    public void inOrder() {
        inOrderRecursive(root);
        System.out.println();
    }

    /*
     * function name: inOrder
     * description: This function traverses the BST and returns an ascending sorted
     * order of Nodes.
     * return type: void
     */
    private void inOrderRecursive(TreeNode node) {

        if (node == null) {
            return;
        }

        // Left
        inOrderRecursive(node.left);

        // Root
        System.out.print(node.data + " ");

        // Right
        inOrderRecursive(node.right);
    }

    /*
     * function name: preOrder
     * description: This function calls an overloaded method preOrder(TreeNode node)
     * return type: void
     */
    public void preOrder() {
        preOrder(root);
        System.out.println();
    }

    /*
     * function name: preOrder
     * description: This function traverses the BST and returns Nodes order from
     * Root, left, right children.
     * return type: void
     */
    private void preOrder(TreeNode node) {

        if (node == null) {
            return;
        }

        // Root
        System.out.print(node.data + " ");

        // Left
        preOrder(node.left);

        // Right
        preOrder(node.right);
    }

    /*
     * function name: postOrder
     * description: This function calls an overloaded method postOrder(TreeNode
     * node)
     * return type: void
     */
    public void postOrder() {
        postOrder(root);
        System.out.println();
    }

    /*
     * function name: postOrder
     * description: This function traverses the BST and returns Nodes order from
     * left, right, Root children.
     * return type: void
     */
    private void postOrder(TreeNode node) {

        if (node == null) {
            return;
        }

        // Left
        postOrder(node.left);

        // Right
        postOrder(node.right);

        // Root
        System.out.print(node.data + " ");
    }

    public static void main(String[] args) {

        BST bst = new BST();

        bst.put(50);
        bst.put(30);
        bst.put(70);
        bst.put(20);
        bst.put(40);
        bst.put(60);
        bst.put(80);

        System.out.println("In-order traversal:");
        bst.inOrder();

        System.out.println("Pre-order traversal:");
        bst.preOrder();

        System.out.println("Post-order traversal:");
        bst.postOrder();

        // Search
        System.out.println("\nSearch results:");

        System.out.println("Search 40: " + bst.get(40));
        System.out.println("Search 90: " + bst.get(90));

        // Delete
        System.out.println("\nDeleting 70...");
        bst.delete(70);

        System.out.println("In-order traversal after deletion:");
        bst.inOrder();

        System.out.println("The smallest node in the BST is: " + bst.findMinimum());
        System.out.println("The largest node in the BST is: " + bst.findMaximum());
    }
}