package heap;

import java.util.ArrayList;

public class Heap {
    private ArrayList<Node> heap = new ArrayList<>();

    class Node {
        int value;
        Node leftChild;
        Node rightChild;
        Node parent;

        Node (Integer value) {
            this.value = value;
        }
    }

    public Heap () {
        heap.add(null);
    }

    public ArrayList<Integer> getHeap() {
        ArrayList<Integer> myHeapValues = new ArrayList<>();
        for (Node node : heap) {
            myHeapValues.add(node.value);
        }
        return myHeapValues;
    }

    public Integer leftChild (int index) {
        if (heap.get(index * 2 - 1) != null) return index * 2;
        return null;
    }

    public Integer rightChild (int index) {
        if (heap.get(index * 2) != null) return index * 2 + 1;
        return null;
    }

    public Integer parentIndex (int index) {
        if (heap.get(index / 2) != null) return index / 2;
        return null;
    }

    public boolean swap (int index1, int index2) {
        if (heap.get(index1) == null || heap.get(index2) == null) return false;
        int temp = heap.get(index1).value;
        heap.get(index1).value = heap.get(index2).value;
        heap.get(index2).value = temp;
        return true;
    }

    public boolean insert (int value) {
        Node newNode = new Node(value);
        heap.addLast(newNode);
        if (newNode.value > heap.get(parentIndex(heap.size() - 1)).value) {
            int addedNodeIndex = heap.size() - 1;
            int parentNodeIndex = addedNodeIndex / 2;
            while (heap.get(addedNodeIndex).value > heap.get(parentNodeIndex).value) {
                swap(parentNodeIndex, addedNodeIndex);
                if (heap.get(addedNodeIndex).value < heap.get(parentNodeIndex).value) {
                    int temp = parentNodeIndex;
                    parentNodeIndex /= 2;
                    addedNodeIndex = temp;
                }
            }
        }
        return true;
    }
}
