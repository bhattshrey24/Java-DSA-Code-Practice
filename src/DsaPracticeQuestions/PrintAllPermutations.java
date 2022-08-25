package DsaPracticeQuestions;

public class PrintAllPermutations {
    public static void main(String args[]) {
        printPerm("ABC", "");

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

}
