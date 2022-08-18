package Sorting;

public class BubbleSort {
    public static void main(String args[]) {
        int arr[] = new int[]{1, 3, 2, 5, 4, 7, 6, 8, 9, 3, 4, 2, 4, 1,};
        BubbleSort.bubbleSort(arr);
        System.out.println("Printing sorted array :-");
        for (int ele : arr) {
            System.out.println(ele);
        }
    }

    public static void bubbleSort(int arr[]) { // TC - O(n^2)
        // After 1st pass the biggest element will be at the last of array
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < arr.length; j++) {
                if (arr[j - 1] > arr[j]) {
                    swapElements(arr, j, j - 1);
                }
            }
        }

    }

    public static void swapElements(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
