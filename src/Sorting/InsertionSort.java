package Sorting;

public class InsertionSort {
    public static void main(String args[]) {
        int arr[] = new int[]{1, 3, 2, 5, 4, 7, 6, 8, 9, 3, 4, 2, 4, 1};
       InsertionSort.insertionSort(arr);
        System.out.println("Printing sorted array :-");
        for (int ele : arr) {
            System.out.println(ele);
        }
    }

    public static void insertionSort(int arr[]) { // TC - O(n^2)
        int key;
        int j;

        //here we slowly generate a sorted list initially we assume that 1st element
        // is sorted so we start our algo from second algo then after 1st
        // iteration the 1st 2 elements will be sorted , then after 3rd iteration the
        // first 3 elements will be sorted and so on

        for (int i = 1; i < arr.length; i++) { // we are starting our algo from 2nd element
            key = arr[i]; // we compare elements with key
            j = i - 1; // we start from 1 element before the current element
            while (j != -1 && arr[j] > key) { // we go till we find element bigger than key or till we reach the end ie. j != -1
                arr[j + 1] = arr[j];// Observe this is shifting and not swapping
                j--;
            }
            arr[j + 1] = key; // in the end we simply put key to j+1th index
            // this works even if we haven't shifted any element because we are starting
            // j from 1 index before the current index
        }
    }

}
