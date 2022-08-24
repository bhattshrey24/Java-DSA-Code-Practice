package Algorithms;

public class KadaneAlgorithm {
    //53. Maximum Subarray (leetcode)
    public static void main(String args[]) {

    }

    // It uses DP
    // Its fastest way to find Max Subarray
    public int maxSubArray(int[] nums) {

        int max = Integer.MIN_VALUE, prevSumForPrevSubArray = 0; // we can also use an
        // array but since we only care about the current element of nums and previous
        // element of dp array therefore one variable is also enough

        //Preprocessing
        prevSumForPrevSubArray = nums[0];
        max = Math.max(prevSumForPrevSubArray, max);

        for (int i = 1; i < nums.length; i++) {
            prevSumForPrevSubArray = Math.max(nums[i], nums[i] + prevSumForPrevSubArray); // if
            // nums[i]>nums[i] + prevSumForPrevSubArray , it means that abhi tk jo sub array hume maximum
            // lag rha tha woh maximum nhi hai so lets drop it and abhi wale element
            // se dubara sub array start krte hai so let it be the 1st element of the new subarray.

            max = Math.max(max, prevSumForPrevSubArray); // now simply compare that current new
            // subarray abhi tk ka overall max hai ya previously koi sub array maximum tha

        }

        return max;
    }
}
