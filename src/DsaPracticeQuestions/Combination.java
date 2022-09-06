package DsaPracticeQuestions;

public class Combination {
    public static void main(String args[]) {
        combination1(4, 2, 0, 0, "");
    }

    // We will find combination using subsequence method
    public static void combination1(int n, int r, int nosb, int cb, String asf) { // nosb = number
        // of selected boxes , cb = current box , asf = answer so far

        if (cb == n) { // if current box = total number of boxes ie. it means we
            // processed all boxes in this current branch
            if (nosb == r) { // This means number of selected boxes in this branch
                // is same as r so this is our answer so simply print it
                System.out.println(asf);
            }
            return;
        }
        combination1(n, r, nosb + 1, cb + 1, asf + "i "); // When current box is selected , 'i' means
        // we selected the box
        combination1(n, r, nosb, cb + 1, asf + "_ "); // When current box is not selected
    }

    // Here we will find combination using permutation
    public static void combination2() {

    }
}
