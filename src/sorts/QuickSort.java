package sorts;

import java.util.Arrays;

public class QuickSort {
    public static void swap (int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    public static int pivot (int[] array, int pivotIndex, int endIndex) {
        int swapIndex = pivotIndex;
        int i = pivotIndex + 1;
        while (i <= endIndex) {
            if (array[i] < array[pivotIndex]) {
                swapIndex++;
                swap(array, i, swapIndex);
            }
            i++;
        }
        swap(array, swapIndex, pivotIndex);
        return swapIndex;
    }

    private static void quickSortHelper (int[] array, int left, int right) {
        if (left < right) {
            int pivot = pivot(array, left, right);
            quickSortHelper(array, left, pivot - 1);
            quickSortHelper(array, pivot + 1, right);
        }
    }

    public static void quickSort (int[] array) {
        quickSortHelper(array, 0, array.length - 1);
    }

    static void main() {
        int[] myArray = {4,6,1,7,3,2,5};

        quickSort(myArray);

        System.out.println( Arrays.toString( myArray ) );
    }
}
