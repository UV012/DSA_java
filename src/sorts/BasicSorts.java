package sorts;

import java.util.Arrays;

public class BasicSorts {
    public void bubbleSort(int[] array) {
        for (int i = array.length; i > 1; i--) {
            for (int j = 1; j < i; j++) {
                if (array[j - 1] > array[j]) {
                    int temp = array[j -1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public void selectionSort (int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i+1; j < array.length; j++) {
                if (array[minIndex] > array[j]) minIndex = j;
            }
            if (minIndex != i) {
                int temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
            }
        }
    }

    public void insertionSort (int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i-1;
            while (j > -1 && temp < array[j]) {
                array[j+1] = array[j];
                array[j] = temp;
                j--;
            }
        }
    }

    static void main(String[] args) {

        int[] myArray = {4,2,6,5,1,3};

        new BasicSorts().insertionSort(myArray);

        System.out.println( Arrays.toString(myArray) );
    }
}
