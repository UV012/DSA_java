package heap;

import java.util.ArrayList;
import java.util.List;

public class MinHeap {
    private List<Integer> heap;

    public MinHeap () { heap = new ArrayList<>(); }

    public List<Integer> getHeap() { return new ArrayList<>(heap); }

    public int leftChild (int index) { return 2 * index + 1; }

    public int rightChild (int index) { return 2 * index + 2; }

    public int parent (int index) { return (index - 1) / 2; }

    public void swap (int index1, int index2) {
        int temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    public void insert (int value) {
        heap.addLast(value);
        int index = heap.size() - 1;
        while (heap.get(index) < heap.get(parent(index))) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    public void sinkDown (int index) {
        int minIndex = index;
        while (true) {
            int leftIndex = leftChild(index);
            int rightIndex = rightChild(index);
            if (leftIndex < heap.size() && heap.get(leftIndex) < heap.get(minIndex)) minIndex = leftIndex;
            if (rightIndex < heap.size() && heap.get(rightIndex) < heap.get(minIndex)) minIndex = rightIndex;
            if (minIndex != index) {
                swap(minIndex, index);
                index = minIndex;
            } else return;
        }
    }

    public Integer remove () {
        if (heap.isEmpty()) return null;
        if (heap.size() == 1) return heap.removeFirst();
        int removedNode = heap.getFirst();
        heap.set(0, heap.removeLast());
        return removedNode;
    }
}
