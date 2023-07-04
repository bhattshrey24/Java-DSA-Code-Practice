package Algorithms;

import java.util.ArrayList;

public class KMP {
    public static void main(String args[]) {
        kmp("aabaadaabaac", "aab");
    }

    // KMP is based on LPS only so if you know how to find LPS then KMP is a piece of cake.
    // It is a bit tricky to understand so watch video in interview prep course 21 Aug , 1:28:00 or my notes
    // Tc of KMP  = O(N)
    public static void kmp(String str, String pat) {
        String newStr = pat + "#" + str;
        int pLen = pat.length();
        int[] lps = lps(newStr);
        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = pLen + 1; i < lps.length; i++) { // i starts after # because from there only "str" starts
            if (lps[i] == pLen) {
                ans.add(i - 2 * pLen); // not just adding 'i' because
                // we changed the string from str to pat#str, so we did -2*pLen so that we can display
                // answer for str and not pat#str also if pattern length is 3 then 3 is
                // found at the end of the pattern in lps array but we want to add the starting of
                // the index where pattern matched
            }
        }

        // DISPLAYING ANSWER
        System.out.print("answer : ");
        for (int idx : ans) {
            System.out.print(idx + " , ");
        }
        System.out.println();
        System.out.print("LPS array : ");
        for (int ele : lps) {
            System.out.print(ele + " | ");
        }
    }

    public static int[] lps(String str) {
        int n = str.length();
        int[] lps = new int[str.length()];
        int len = 0; // helps to find proper prefix
        int i = 1; // helps to find proper suffix
        while (i < n) {
            if (str.charAt(len) == str.charAt(i)) {
                len++;
                lps[i] = len; // this is same as lps[i]= lps[i-1]+1; because len shows the prefix
                // length till now which matched with suffix for current substring
                i++;
            } else {
                if (len > 0) {
                    len = lps[len - 1]; // jumping len backwards and smartly skipping useless checks
                } else {
                    i++;
                }
            }
        }
        return lps;
    }
}
