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

        // The core logic is that we assume elements before i are sorted and we just have to find correct
        // position of next element (i.e. i+1th element) in this sorted array and we keep
        // shifting (not swapping) elements till we find correct position and then simply place
        // this element there and we do this for all remaining element
        // In the starting we assume 1st element is sorted therefore we start from position 1 and not 0

        // Eg : 1,2,4,6 | 3,7,5 suppose i is at 3 which means elements before it are
        // sorted. Now do dry run for this and you'll understand

        for (int i = 1; i < arr.length; i++) { // we are starting our algo from 2nd element
            key = arr[i]; // we compare elements with key. Also this way we store its value so
            // that in the end we can put it in correct position
            j = i - 1; // we start from 1 element before the current element
            while (j >= 0 && arr[j] > key) { // we go till we find element bigger than key or till we reach the end i.e. j = -1
                arr[j + 1] = arr[j];// Observe this is shifting and not swapping
                j--;
            }
            arr[j + 1] = key; // in the end we simply put key to j+1 th index
            // Even if we haven't shifted any element in current iteration this above statement won't create issue
            // because it means key > the biggest element is sorted array i.e. arr[i-1] which
            // means this statement will just put key in the same position where key was so no change
        }
    }

}
