package Sorting;

public class QuickSort {

    public static void main(String args[]) {
        int arr[] = new int[]{3,1,8,2,6,4,4};
        QuickSort.quickSort(arr, 0, arr.length - 1);
        for (int ele : arr) {
            System.out.println(ele);
        }
    }

    // First see the merge sort
    // Another example of divide and conquer
    public static void quickSort(int arr[], int low, int high) {
        if (high <= low) {
            return;
        }

        int pivotIdx = partition(arr, low, high); // this partition() will simply put
        // pivot (which initially will be the 1st element of the unsorted array)
        // in it's correct position and put all elements smaller than it to its left and all
        // elements bigger then it to its right and return the index where it has put the pivot

        quickSort(arr, low, pivotIdx - 1); // have faith that this will sort left
        // side of pivot and right side of pivot . This function is similar to merge sort but
        // here we are
        // dividing till 'pivotIdx - 1' and not till 'pivotIdx' because here we don't include the
        // pivot element since it's already in its correct position because of partition()
        // so we don't disturb it and
        // tell recursion to sort other remaining elements on left of pivot and right of pivot

        quickSort(arr, pivotIdx + 1, high);
    }


    // see this video to understand this function better https://www.youtube.com/watch?v=QXum8HQd_l4&t=1159s
    public static int partition(int arr[], int low, int high) { // This implementation of partition is
        // quite different from the famous one that you have read where there are while loop
        // inside while loop

        int pivot = arr[high]; // we consider the high element as
        // pivot , we can also take low as pivot , or mid (ie. (high+low)/2) as pivot or any random element in between low and high as pivot

        int i = low; // elements before i are smaller than pivot

        for (int j = low; j < high; j++) { // observe that here last value of j
            // will be high - 1 ie. we wont process high because it is already considered as
            // pivot element

            if (arr[j] < pivot) { // here the logic is that if we put all the elements
                // smaller than pivot on its left then obviously all the elements bigger
                // than p ivot will automatically be placed at its right

                if(i!=j) { // This "If" is not necessary for quick sort to work
                    // but adding this will ensure that we skip swapping the elements with itself which is unnecessary
                    // and only swap when its necessary
                    swap(arr, i, j);
                }

                i++; // since we found another element smaller than pivot therefore we move i
            }
        }
        swap(arr, i, high);// in the end i will be pointing to the correct position where pivot should come
        return i; // returning index where we placed pivot element
    }


    public static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
