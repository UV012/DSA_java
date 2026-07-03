package binarysearchtree;

public class RBST {
    private Node root;

    class Node {
        int value;
        Node left;
        Node right;
        Node (int value) { this.value = value; }
    }

    public Node getRoot() { return root; }

    private Node insert(Node currentNode, int value) {
        if (currentNode == null) return new Node(value);
        if (value < currentNode.value) currentNode.left = insert(currentNode.left, value);
        if (value > currentNode.value) currentNode.right = insert(currentNode.right, value);
        return currentNode;
    }

    public void insert(int value) {
        if (root == null) root = new Node(value);
        insert(root, value);
    }

    private boolean rContains (Node currentNode, int value) {
        if (currentNode.value == value) return true;
        else {
            if (currentNode.value > value) {
                if (currentNode.left == null) return false;
                return rContains(currentNode.left, value);
            }
            else {
                if (currentNode.right == null) return false;
                return rContains(currentNode.right, value);
            }
        }
    }

    public boolean rContains (int value) {
        return rContains(root, value);
    }

    public int minValue (Node currentNode) {
        while (currentNode.left != null) currentNode = currentNode.left;
        return currentNode.value;
    }

    private Node deleteNode (Node currentNode, int value) {
        if (currentNode == null) return null;
        if (value < currentNode.value) currentNode.left = deleteNode(currentNode.left, value);
        else if (value > currentNode.value) currentNode.right = deleteNode(currentNode.right, value);
        else {
            if (currentNode.left == null && currentNode.right == null) return null;
            else if (currentNode.left != null && currentNode.right == null) return currentNode.left;
            else if (currentNode.left == null && currentNode.right != null) return currentNode.right;
            else {
                int replace = minValue(currentNode.right);
                currentNode.value = replace;
                currentNode.right = deleteNode(currentNode.right, replace);
            }
        }
        return currentNode;
    }

    public void deleteNode (int value) {
        deleteNode(root, value);
    }
}
