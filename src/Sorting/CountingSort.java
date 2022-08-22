package Sorting;

public class CountingSort {
    public static void main(String args[]) {
         int arr[] = new int[]{1, 3, 2, 5, 4, 7, 6, 8, 9, 3, 4, 2, 4, 1, 0};
        // int arr[] = new int[]{1, 3, 2, 3, 4, 1, 6, 4, 3};
        // int arr[] = new int[]{};
        int sortedArr[] = CountingSort.countingSort(arr);
        System.out.println("Printing sorted array :-");
        for (int ele : sortedArr) {
            System.out.println(ele);
        }
    }

    // This sort is useful when the range of number in
    // array is low because it's TC is O(n+k) where n is size of array and k is
    // range of numbers so suppose it's not good to use this algo if you have an array
    // like [0,1,3,10000000000] now here range is 0-10000000000 so Tc
    // will be O(4+10000000000) = O(10000000000) but elements are just 4

    // It is useful for sorting Strings since number of
    // characters in english are just 26 so TC will approximately be O(n+26) = O(n)

    // It's a stable sorting algorithm ie. if there are duplicates then the number which appeared
    // first will be placed first this is useful when
    // you are trying to sort some custom clas object on the basis of dob or salary or something

    // See this video for reference : https://www.youtube.com/watch?v=imqr13aIBAY
    public static int[] countingSort(int arr[]) {
        if (arr.length == 0) { // ie. if array is empty simply return the same given array
            return arr;
        }

        // Step 1 find range
        int range = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) { // find the range ie. max element in array
            range = Math.max(arr[i], range);
        }

        //Step 2 create freqency array which contains frequency count of each element in the range
        int freqArr[] = new int[range + 1];
        for (int i = 0; i < arr.length; i++) { // calculate frequency of each number
            freqArr[arr[i]] += 1;
        }

        //Step 3 create a prefixSum array of frequency array , this will tell where to put which element.
        int prefixArr[] = new int[freqArr.length];
        prefixArr[0] = freqArr[0];
        for (int i = 1; i < prefixArr.length; i++) {// fill the prefix array
            prefixArr[i] = prefixArr[i - 1] + freqArr[i];
        }

        int ans[] = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) { // traversing from back
            int element = arr[i];
            if (prefixArr[element] <= 0) {// this means we placed all duplicates of current element ie. arr[i] so process next element
                continue;
            } else {
                prefixArr[element] = prefixArr[element] - 1; // decrease frequency since we are placing one of it currently
                ans[prefixArr[element]] = element; // place it
            }
        }

        return ans;

    }
}
