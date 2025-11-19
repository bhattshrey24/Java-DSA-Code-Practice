package Sorting;

public class CountingSort {
    public static void main(String args[]) {

        int arr[] = new int[]{1,3,2,1,1,3,2,1,5,3,7};

        int sortedArr[] = CountingSort.countingSort(arr);
        System.out.println("Printing sorted array :-");
        for (int ele : sortedArr) {
            System.out.println(ele);
        }
    }

    // This sort is useful when the range of number in
    // array is low because it's TC is O(n+k) where n is size of array and k is
    // range of numbers so suppose it's not good to use this algo if you have an array
    // like [0,1,3,10000000000] now here range is 10000000000 - 0 so Tc
    // will be O(4+10000000000) = O(10000000000) but elements are just 4

    // It is useful for sorting Strings since number of
    // characters in english are just 26 so TC will approximately be O(n+26) = O(n)

    // It's a stable sorting algorithm ie. if there are duplicates then the number which appeared
    // first will be placed first this is useful when
    // you are trying to sort some custom clas object on the basis of dob or salary or something

    // See this video for reference : https://www.youtube.com/watch?v=OKd534EWcdk

    // Case 1 : If all elements are positive
    public static int[] countingSort(int[] arr) {
        int n = arr.length;
        int range = 0;
        // Step 1 : Calculate range
        for(int ele : arr){
            range = Math.max(range, ele);
        }
        // Step 2 : Create Frequency Array
        int []freqArr = new int[range + 1];
        for(int ele : arr){
            freqArr[ele]++;
        }
        // Step 3 : Create Prefix Sum Array
        int []prefixSum =  new int[freqArr.length];
        prefixSum[0] = freqArr[0];
        for(int i = 1 ; i < freqArr.length ; i++){
            prefixSum[i] = prefixSum[i-1] + freqArr[i];
        }

        // Step 4 : Left shift Prefix Sum Array Values
        for(int i = prefixSum.length - 1; i > 0 ; i--){
            prefixSum[i] = prefixSum[i-1];
        }
        prefixSum[0] = 0;

        // Step 5 : Traverse the given array and create the sorted array
        int[] result = new int[n];
        for(int ele : arr){
         result[prefixSum[ele]] = ele;
         prefixSum[ele]++;
        }

        return result;
    }


}
