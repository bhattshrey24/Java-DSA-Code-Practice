package Sorting;

public class MergeSort {
    public static void main(String args[]) {
        int arr[] = new int[]{1, 3, 2, 5, 4, 7, 6, 8, 9, 3, 4, 2, 4, 1, 0};
        MergeSort.mergeSort(arr, 0, arr.length - 1);
        System.out.println("Printing sorted array :-");
        for (int ele : arr) {
            System.out.println(ele);
        }
    }

    // Basic example of divide and conquer technique
    public static void mergeSort(int arr[], int start, int end) {
        if (start == end) { // base case i.e. if while dividing the array we reach a
            // point where there's just 1 element left then simply return since single
            // element is always considered sorted, or you can think of
            // it as if someone gave us an array with 1 element to sort then in that case
            // simply return since there's nothing to do
            return;
        }
        int mid = (start + end) / 2;

        mergeSort(arr, start, mid); // Have faith that this will sort the 1st half of the given array
        mergeSort(arr, mid + 1, end); // Have faith that this will sort the other half of the given array

        merge(arr, start, mid, end); // this function will simply merge the 2 sorted
        // halves , ie, start to mid and mid+1 to end
    }

    public static void merge(int array[], int start, int mid, int end) { // To avoid confusion
        // simply think of it as if we want to merge 2 arrays ie. from start to mid and from mid+1 to end
        // we will simply merge it in new array and then copy and paste it in the given array

        int i = start, j = mid + 1, k = 0; // 'i' is pointing to start of 1st array and 'j' is pointing to start of 2nd array and 'k'

        int newArr[] = new int[end - start + 1]; // size of the merged array is (end - start + 1) and not
        // simply 'end' because this function might be called for
        // start = 5 and end = 8 then in that case newArray size should be 4 and not 8

        while (i <= mid && j <= end) {// ie. stop if any of the pointers reach end of there respective array
            if (array[i] < array[j]) {
                newArr[k] = array[i];
                i++;
            } else {
                newArr[k] = array[j];
                j++;
            }
            k++;
        }
        // fill the remaining elements of the remaining array
        while (i <= mid) {
            newArr[k] = array[i];
            i++;
            k++;
        }
        while (j <= end) {
            newArr[k] = array[j];
            j++;
            k++;
        }

        // Now we are filling the sorted elements back to the given array
        i = start; // we fill the given array from start
        // till end , it's not 0 because again this function might be
        // called with start as 5 and end as 8 or 10 or something
        for (int z = 0; z < newArr.length; z++) {
            array[i] = newArr[z];
            i++;
        }
    }
}
