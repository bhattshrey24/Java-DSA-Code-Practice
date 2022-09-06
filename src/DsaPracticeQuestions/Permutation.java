package DsaPracticeQuestions;

public class Permutation {
    public static void main(String args[]) {
        permutationUsingBacktrack(new int[4], 2, 1, 0);
    }


    public static void permutationUsingBacktrack(int[] boxes, int r, int ci, int nosi) { // ci = current
        // item which we are placing at a box , nosi = number of selected items

        if (nosi == r) { // This will hit always in every branch because at
            // each level we are choosing a box unlike subsequence where we
            // either selected a box or rejected it
            System.out.println();

            for (int i = 0; i < boxes.length; i++) {
                if (boxes[i] == 0) { // means we haven't selected this box in this current branch
                    System.out.print("_ ");
                } else {
                    System.out.print(boxes[i] + " ");
                }
            }
            return;
        }

        for (int i = 0; i < boxes.length; i++) {
            if (boxes[i] == 0) {// checking if we visited it or not , i.e checking whether we already placed
                // an item in this boxes in current branch of recursive or not , 0 means not
                // visited ie. we haven't put any item in this box , any
                // other number means visited

                boxes[i] = ci;// we put current item in this box

                permutationUsingBacktrack(boxes, r, ci + 1, nosi + 1);// have faith that this will
                // print answer for remaining gap i.e. ci se aage wale gap ie. ci + 1 or you can think of it like
                // let recursion decide where to put next element

                 boxes[i] = 0; // backtrack , ie. emptying the box so that other branches
                // can use it to find their answer
            }
        }
    }


}
