package heap;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static int findKthSmallest2 (int[] nums, int k) {
        Heap2 heap = new Heap2();
        for (int num : nums) {
            heap.insert(num);
        }
        for (int i = 0; i < nums.length - k; i++) {
            heap.remove();
        }
        return heap.getHeap().getFirst();
    }

    public static int findKthSmallest (int[] nums, int k) {
        Heap2 heap = new Heap2();
        for (int num : nums) {
            heap.insert(num);
            if (heap.getHeap().size() > k) heap.remove();
        }
        return heap.remove();
    }

    public static List<Integer> streamMax (int[] nums) {
        Heap2 heap = new Heap2();
        List<Integer> result = new ArrayList<>();
        for (int num : nums) {
            heap.insert(num);
            result.add(heap.getHeap().getFirst());
        }
        return result;
    }

    static void main() {
        // Test case 1
        int[] nums1 = {1, 5, 2, 9, 3, 6, 8};
        System.out.println("Test case 1:");
        System.out.println("Input: [1, 5, 2, 9, 3, 6, 8]");
        System.out.println("Expected output: [1, 5, 5, 9, 9, 9, 9]");
        System.out.println("Actual output: " + streamMax(nums1));
        System.out.println();

        // Test case 2
        int[] nums2 = {10, 2, 5, 1, 0, 11, 6};
        System.out.println("Test case 2:");
        System.out.println("Input: [10, 2, 5, 1, 0, 11, 6]");
        System.out.println("Expected output: [10, 10, 10, 10, 10, 11, 11]");
        System.out.println("Actual output: " + streamMax(nums2));
        System.out.println();

        // Test case 3
        int[] nums3 = {3, 3, 3, 3, 3};
        System.out.println("Test case 3:");
        System.out.println("Input: [3, 3, 3, 3, 3]");
        System.out.println("Expected output: [3, 3, 3, 3, 3]");
        System.out.println("Actual output: " + streamMax(nums3));
        System.out.println();
    }
}
