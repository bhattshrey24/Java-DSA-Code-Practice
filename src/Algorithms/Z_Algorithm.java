package Algorithms;

import java.util.ArrayList;

public class Z_Algorithm {
    public static void main(String args[]) {
        zAlgo("aabaadaabaac", "aab");
    }

    // This is another pattern matching algorithm like KMP
    // This algo also has Tc = O(N) . It might not feel like O(n) because of nested loop
    // but it is because of the kickstart and range that we are defining
    // Here also we try to do smart work by giving kickstart to each index.
    // It is quite similar to KMP only like in KMP we find LPS and here we find Z Array
    // See the video or notes because it might feel a bit complicated
    static void zAlgo(String str, String pat) {
        String newStr = pat + "#" + str;
        int pLen = pat.length();
        int[] zArr = zArray(newStr);
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = pLen; i < zArr.length; i++) {
            if (zArr[i] == pLen) {
                ans.add(i - pLen - 1);
            }
        }

        // Displaying Answer
        System.out.print("answer : ");
        for (int idx : ans) {
            System.out.print(idx + " , ");
        }
        System.out.println();
        System.out.print("Z array: ");
        for (int ele : zArr) {
            System.out.print(ele + " | ");
        }
    }

    static int[] zArray(String str) {
        int l = 0, r = 0, n = str.length();
        int[] zArr = new int[n];
        for (int i = 1; i < n; i++) {
            if (i <= r) { // i.e. 'i' is in range that means we can give kickstart
                zArr[i] = Math.min(r - i + 1, zArr[i - l]); // we are going only giving kickstart
                // as far as the range allows us.
            }
            while ((i+zArr[i]) < n && str.charAt(zArr[i]) == str.charAt(i + zArr[i])) {// zArr[i] will
                // always start with either 0 or some kickstart. Because of the kickstart we are not doing n^2 work here.
                zArr[i]++;
            }
            if ((i + zArr[i] - 1) > r) { // changing the range since we found new range
                l = i;
                r = i + zArr[i] - 1;
            }
        }
        return zArr;
    }
}
