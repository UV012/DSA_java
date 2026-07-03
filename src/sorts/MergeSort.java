package sorts;

import java.util.Arrays;

public class MergeSort {
    public int[] merge(int[] arr1, int[] arr2) {
        int len = arr1.length + arr2.length;
        int[] result = new int[len];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                result[k] = arr1[i];
                i++;
            } else {
                result[k] = arr2[j];
                j++;
            }
            k++;
        }
        while (i < arr1.length) {
            result[k] = arr1[i];
            i++;
            k++;
        }
        while (j < arr2.length) {
            result[k] = arr2[j];
            j++;
            k++;
        }
        return result;
    }

    /*public int[] mergeSort (int[] arr) {
        if (arr.length < 2) return arr;
        int midIndex = arr.length / 2;
        int[] left = new int[midIndex];
        int[] right = new int[arr.length - midIndex];
        int[] result = new int[arr.length];
        for (int i = 0; i < midIndex; i++) {
            left[i] = arr[i];
        }
        for (int i = midIndex; i < arr.length; i++) {
            right[i] = arr[i];
        }
        if (left.length == 1 && right.length == 1) merge(left, right);
    }*/

    public int[] mergeSort (int[] array) {
        if (array.length < 2) return array;
        int midIndex = array.length / 2;
        int[] left = mergeSort(Arrays.copyOfRange(array, 0, midIndex));
        int[] right = mergeSort(Arrays.copyOfRange(array, midIndex, array.length));
        return merge(left, right);
    }

    static void main(String[] args) {

        int[] originalArray = {3,1,4,2};

        MergeSort mergesort = new MergeSort();
        int [] sortedArray = mergesort.mergeSort(originalArray);

        System.out.println( "Original Array: " + Arrays.toString( originalArray ) );

        System.out.println( "\nSorted Array: " + Arrays.toString( sortedArray ) );
    }
}
