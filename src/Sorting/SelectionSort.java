package Sorting;

public class SelectionSort {
    public static void main(String args[]) {
        int arr[] = new int[]{1, 3, 2, 5, 4, 7, 6, 8, 9, 3, 4, 2, 4, 1, 0};
        SelectionSort.selectionSort(arr);
        System.out.println("Printing sorted array :-");
        for (int ele : arr) {
            System.out.println(ele);
        }
    }

    // To see if this code passes all test cases goto https://www.geeksforgeeks.org/problems/selection-sort/1

    public static void selectionSort(int arr[]) { // TC - O(n^2)
       // at each pass we find the smallest element in the array and put it in its
        // correct position
        int smallestEleIdx;
        for (int i = 0; i < arr.length; i++) {// After each iteration elements before i are sorted
            smallestEleIdx = i;
            for (int curr = i + 1; curr < arr.length; curr++) {
                smallestEleIdx = arr[smallestEleIdx] > arr[curr] ? curr : smallestEleIdx;
            }// after the for loop 'lowestEleIdx' will be holding the index of the smallest element
            // in the remaining array
            swapElements(arr, i, smallestEleIdx);
        }

    }

    public static void swapElements(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
