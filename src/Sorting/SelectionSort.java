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

    public static void selectionSort(int arr[]) { // TC - O(n^2)
       // at each pass we find the smallest element in the array and put it in its correct position
        int lowestEleIdx;
        for (int i = 0; i < arr.length; i++) {
            lowestEleIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                lowestEleIdx = arr[lowestEleIdx] > arr[j] ? j : lowestEleIdx;
            }// after the for loop 'lowestEleIdx' will be holding the index of the smallest element in the remaining array
            swapElements(arr, i, lowestEleIdx);
        }

    }

    public static void swapElements(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
