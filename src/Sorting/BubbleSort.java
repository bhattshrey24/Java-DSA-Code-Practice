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

    // To see if this code passes all test cases goto https://www.geeksforgeeks.org/problems/bubble-sort/1

    public static void bubbleSort(int[] arr) {// TC - O(n^2)
        // After 1st pass the biggest element will be at the last of array
        int n = arr.length;
        for(int count = 0; count < n - 1 ; count++){ // Just take decreasing order array (i.e. worst case) and see how many time we have to run the algorithm to make it sorted in ascending order
            for(int i = 0; i < n - 1 ; i++){ // n-1 because otherwise we will get out of bound error when we do arr[i+1]
                if(arr[i]>arr[i+1]){
                    swapElements(i,i+1,arr);
                }
            }
        }
    }

    public static void swapElements(int p1 , int p2 , int []arr){
        int temp = arr[p1];
        arr[p1]=arr[p2];
        arr[p2]=temp;
    }
}
