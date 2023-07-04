package Important_Misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sorting1D_And_2DMatrix {
    // This class shows how we can sort 2d matrix w.r.t column using comparator
    public static void main(String args[]) {
        sort1DAndList();
        sort2DMatWithDimNX2(new int[][]{{6, 10}, {8, 9}, {1, 5}, {4, 6}});
        sort2DMatWithDimNX3(new int[][]{{6, 10, 16}, {8, 9, 15}, {1, 5, 18}, {4, 6, 111}});
    }
    public static void sort1DAndList() {
        // Sorting 1D array
        int arr[] = new int[]{1, 2, 5, 4, 3};
        Arrays.sort(arr); // Method 1

        //Sorting list
        ArrayList<Integer> arrList = new ArrayList<Integer>();
        arrList.add(20);
        arrList.add(30);
        arrList.add(10);

        Collections.sort(arrList);

        for (Integer ele : arrList) {
            System.out.println(ele);
        }
    }
    public static void sort2DMatWithDimNX2(int[][] mat) {
        System.out.println("NX2 Matrix :- ");
        displayMat(mat, "Original Matrix");
        Arrays.sort(mat, (a, b) -> Integer.compare(a[0], b[0])); // This a,b means 2 rows which comparator will compare and we are telling it which one to column to compare and choose
        displayMat(mat, "Sort 1 (in ascending order based on col0)");
        Arrays.sort(mat, (a, b) -> Integer.compare(b[0], a[0]));
        displayMat(mat, "Sort 2 (in descending order based on col0)");
        Arrays.sort(mat, (a, b) -> Integer.compare(a[1], b[1]));
        displayMat(mat, "Sort 3 (in ascending order based on col1)");
        Arrays.sort(mat, (a, b) -> Integer.compare(b[1], a[1]));
        displayMat(mat, "Sort 4 (in descending order based on col1)");
    }
    public static void sort2DMatWithDimNX3(int[][] mat) {
        System.out.println("NX3 Matrix :- ");
        displayMat(mat, "Original Matrix");
        Arrays.sort(mat, (a, b) -> Integer.compare(a[0], b[0]));
        displayMat(mat, "Sort 1 (in ascending order based on col0)");
        Arrays.sort(mat, (a, b) -> Integer.compare(b[0], a[0]));
        displayMat(mat, "Sort 2 (in descending order based on col0)");
        Arrays.sort(mat, (a, b) -> Integer.compare(a[1], b[1]));
        displayMat(mat, "Sort 3 (in ascending order based on col1)");
        Arrays.sort(mat, (a, b) -> Integer.compare(b[1], a[1]));
        displayMat(mat, "Sort 4 (in descending order based on col1)");
        Arrays.sort(mat, (a, b) -> Integer.compare(a[2], b[2]));
        displayMat(mat, "Sort 5 (in ascending order based on col2)");
        Arrays.sort(mat, (a, b) -> Integer.compare(b[2], a[2]));
        displayMat(mat, "Sort 6 (in descending order based on col2)");
    }
    public static void displayMat(int[][] mat, String message) {
        System.out.println(message + " :-");
        for (int[] row : mat) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

}
