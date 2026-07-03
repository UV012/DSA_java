package binarysearchtree;

import java.util.ArrayList;
import java.util.Stack;

public class BinarySearchTree {
    private Node root;

    class Node {
        int value;
        Node left;
        Node right;
        Node (int value) {
            this.value = value;
        }
    }

    public Node getRoot() {
        return root;
    }

    public boolean insert (int value) {
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode;
            return true;
        }
        Node temp = root;
        while (true) {
            if (newNode.value == temp.value) return false;
            if (newNode.value < temp.value) {
                if (temp.left == null) {
                    temp.left = newNode;
                    return true;
                }
                temp = temp.left;
            } else {
                if (temp.right == null) {
                    temp.right = newNode;
                    return true;
                }
                temp = temp.right;
            }
        }
    }

    public boolean contains (int value) {
        if (root == null) return false;
        Node temp = root;
        while (temp != null) {
            if (value == temp.value) return true;
            else if (value < temp.value) temp = temp.left;
            else temp = temp.right;
        }
        return false;
    }

    private boolean rContains (Node currentNode, int value) {
        if (currentNode == null) return false;
        if (currentNode.value == value) return true;
        else if (value < currentNode.value) return rContains(currentNode.left, value);
        else return rContains(currentNode.right, value);
    }

    public boolean rContains (int value) {
        return rContains(root, value);
    }

    private Node rInsert (Node currentNode, int value) {
        if (currentNode == null) return new Node(value);
        if (value < currentNode.value) currentNode.left = rInsert(currentNode.left, value);
        else if (value > currentNode.value) currentNode.right = rInsert(currentNode.right, value);
        return currentNode;
    }

    public void rInsert (int value) {
        if (root == null) root = new Node(value);
        rInsert(root, value);
    }

    public int minValue (Node root) {
        if (root.left == null) return root.value;
        else return minValue(root.left);
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

    public ArrayList<Integer> BFS2 () {
        ArrayList<Node> queue = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();
        Node currentNode = root;
        queue.add(currentNode);
        result.add(currentNode.value);
        queue.removeFirst();
        while (currentNode.left != null && currentNode.right != null) {
            queue.add(currentNode.left);
            queue.add(currentNode.right);
            currentNode = queue.getFirst();
            result.add(currentNode.value);
            queue.removeFirst();
        }
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            result.add(queue.getFirst().value);
            queue.removeFirst();
        }
        return result;
    }

    public ArrayList<Integer> BFS () {
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Node> queue = new ArrayList<>();
        Node currentNode = root;
        queue.add(currentNode);
        while (!queue.isEmpty()) {
            currentNode = queue.removeFirst();
            result.add(currentNode.value);
            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }
        return result;
    }

    public ArrayList<Integer> DFSPreOrder () {
        ArrayList<Integer> results = new ArrayList<>();
        class Traverse {
            Traverse (Node node) {
                results.add(node.value);
                if (node.left != null) new Traverse(node.left);
                if (node.right != null) new Traverse(node.right);
            }
        }
        new Traverse(root);
        return results;
    }

    public ArrayList<Integer> DFSPostOrder () {
        ArrayList<Integer> results = new ArrayList<>();
        class Traverse {
            Traverse (Node node) {
                if (node.left != null) new Traverse(node.left);
                if (node.right != null) new Traverse(node.right);
                results.add(node.value);
            }
        }
        new Traverse(root);
        return results;
    }

    public ArrayList<Integer> DFSInOrder () {
        ArrayList<Integer> results = new ArrayList<>();
        class Traverse {
            Traverse (Node node) {
                if (node.left != null) new Traverse(node.left);
                results.add(node.value);
                if (node.right != null) new Traverse(node.right);
            }
        }
        new Traverse(root);
        return results;
    }

    public boolean isValidBST () {
        ArrayList<Integer> valuesInOrder = DFSInOrder();
        for (int i = 1; i < valuesInOrder.size(); i++) {
            if (valuesInOrder.get(i-1) >= valuesInOrder.get(i)) return false;
        }
        return true;
    }

    public Integer kthSmallest (int k) {
        Stack<Node> nodes = new Stack<>();
        Node node = root;
        while (!nodes.isEmpty() || node != null) {
            while (node != null) {
                nodes.push(node);
                node = node.left;
            }
            node = nodes.pop();
            k--;
            if (k == 0) return node.value;
            node = node.right;
        }
        return null;
    }

    private Node sortedArrayToBST (int[] array, int left, int right) {
        if (left > right) return null;
        int mid = array.length / 2;
        Node node = new Node(array[mid]);
        node.left = sortedArrayToBST(array, left, mid - 1);
        node.right = sortedArrayToBST(array, mid + 1, right);
        return node;
    }

    public void sortedArrayToBST (int[] array) { this.root = sortedArrayToBST(array, 0, array.length - 1); }

    private Node invertTree (Node node) {
        if (node == null) return null;
        Node temp = node.left;
        node.left = invertTree(node.right);
        node.right = invertTree(temp);
        return node;
    }

    public void invertTree () { this.root = invertTree(root); }
}
