package DsaPracticeQuestions;

public class PrintSubSequence {
    public static void main(String args[]) {
        printSS("ABC", "");
    }

    public static void printSS(String str, String ans) {
        if (str.equals("")) { // base case ie. if given
            // string is empty then simply print the answer or you
            // can think of it like you reached end of the current branch
            // nothing left to divide so simply print ans because if we reached till
            // here then it means that ans contains the actual answer of this branch
            System.out.println(ans);
            return;
        }
        char ch = str.charAt(0);
        String ros = str.substring(1);
        printSS(ros, ch + ans); // have faith that recursion will get you
        // the subsequence of remaining string (ie. ros) and in that you are simply adding
        // 0th character of str
        printSS(ros, ans);

    }
}
