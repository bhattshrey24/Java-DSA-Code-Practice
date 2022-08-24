package Algorithms;

public class BinarySearch {
    public static void main(String args[]) {

          int arr[] = new int[]{1, 3, 5, 7, 10, 15, 17, 19, 20, 21, 22, 24, 25};
//        int arr[] = new int[]{1, 1, 1, 1, 2, 2, 2, 3, 3, 19, 19, 19,};
//        int arr[] = new int[]{1, 3, 5};
//        int arr[] = new int[]{1, 3};
//        int arr[] = new int[]{1};
//        int arr[] = new int[]{};

        System.out.println("Using Iterative method Element found at : " + BinarySearch.binarySearchIterative(arr, 19));
        System.out.println("Using recursive method Element found at : " + BinarySearch.binarySearchRecursive(arr, 19, 0, arr.length - 1));

    }

    // Whenever we are given a sorted array always think whether we can use binary search in it
    public static int binarySearchIterative(int arr[], int eleToBeFound) {
        int indexOfEleToBeFound = -1;//-1 means not found
        int low = 0, high = arr.length - 1, mid;
        while (low <= high) {
            mid = low + (high - low) / 2; // If you simplify it then you'll get (low + high) / 2 ,
            // but if we implement it this way then we save ourselves from going out of bound
            // of int(or any data type that we are using) like suppose 'high' is the max value
            // that int can take(ie. Integer.MAX_VALUE) or even some high value then if we add
            // something to it (ie. here we are adding 'low' to it) then it
            // will go out of bound of int and we will get an exception before even dividing that number.

            // mid = (low + high) / 2; // we have to take the floor value of this and
            // since compiler will convert this to int therefore it will automatically
            // round it off to floor value therefore we don't have to do it on our own
            if (arr[mid] == eleToBeFound) {
                return mid;
            }
            // while doing questions on binary search it
            // might depend on question whether you have to do mid-1 or mid or mid+1
            if (arr[mid] < eleToBeFound) {
                low = mid + 1; // mid+1 because we know mid is not our element so simply reject it too
            } else {
                high = mid - 1;
            }
        }

        return indexOfEleToBeFound;
    }

    public static int binarySearchRecursive(int arr[], int eleToBeFound, int low, int high) { // Iterative implementation
        // is better because here space complexity will not be O(1) it will be simply O(logn)
        if (low > high) { // Base case ie. when array is empty or you can think of it like we checked all elements but haven't founf eleToBeFound so simply return
            return -1;
        }
        int mid = (low + high) / 2;
        if (eleToBeFound == arr[mid]) {
            return mid;
        } else if (eleToBeFound > arr[mid]) {
            return binarySearchRecursive(arr, eleToBeFound, mid + 1, high); //Have faith that recursion with find the element in remaining part if ts present
        } else {
            return binarySearchRecursive(arr, eleToBeFound, low, mid - 1);
        }
    }
}
