package DsaPracticeQuestions;

import java.util.ArrayList;

public class PrintAllPermutations {
    public static void main(String args[]) {
        System.out.println("Print Permutation answer :-");
        printPerm("ABC", "");
        System.out.println("Get Permutation answer :-");
        ArrayList<String> ans = getPermutation("ABC");
        for (String str : ans) {
            System.out.println(str);
        }
    }

    public static void printPerm(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i); //take out ith element
            String ros = str.substring(0, i) + str.substring(i + 1); // generate
            // remaining string without ith element
            printPerm(ros, ans + ch);// now have faith that
            // recursion will find answer for ros and bring it back to
            // you as 'ans' so simply add 'ch' in front of it
        }
    }

    public static ArrayList<String> getPermutation(String str) {
        if (str.length() == 0) {
            ArrayList<String> baseAns = new ArrayList<String>();
            baseAns.add("");
            return baseAns;
        }

        ArrayList<String> ans = new ArrayList<String>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String ros = str.substring(0, i) + str.substring(i + 1);
            ArrayList<String> recAns = getPermutation(ros);
            for (String rec : recAns) {
                ans.add(ch + rec);
            }
        }
        return ans;
    }
}
