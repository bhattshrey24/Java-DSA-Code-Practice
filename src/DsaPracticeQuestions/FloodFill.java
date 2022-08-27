package DsaPracticeQuestions;

public class FloodFill {
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        boolean vis[][] = new boolean[3][3];
        int[][] grid = {{0, 0, 0}, {0, 0, 0}, {1, 0, 0}};

        // wrongFloodFill(grid, 0, 0, 2, 2,"");
           floodFill(grid, vis, 0, 0, 2, 2, "");

    }

    // normal but Wrong Approach , we NEED TO SEE THIS to understand why dfs is
    // required,
    // the normal approach that we used so far didn't work here cause here we can
    // come up and go right too , so if we don't keep track of whether we visited the
    // cell or not then we
    // will be coming back to same cell again and again , suppose u did down call at
    // (i,j)th cell now you're at (i+1,j)th cell so now 1st call is of top so see u
    // will
    // again reach (i,j)th cell so its like infinite loop , see vid of sumit sir he
    // explained even better
    // also we won't be able to backtrack here without visited boolean array , see
    // the correct solution to understand this
    public static void wrongFloodFill(int[][] grid, int i, int j, int m, int n, String psf) {// psf means path so far
        if (!isValid(grid, i, j)) {// negative base case , reached out of grid
            return;
        }
        if (isValid(grid, i, j) && grid[i][j] == 1) {// negative base case, inside grid but encountered obstacle
            return;
        }
        if (i == m && j == n) {// positive base case , reached destination so simply print solution
            System.out.println(psf);
            return;
        }

        wrongFloodFill(grid, i - 1, j, m, n, psf + "T");
        wrongFloodFill(grid, i, j - 1, m, n, psf + "L");
        wrongFloodFill(grid, i + 1, j, m, n, psf + "D");
        wrongFloodFill(grid, i, j + 1, m, n, psf + "R");

    }

    // this is simply dfs
    public static void floodFill(int[][] grid, boolean[][] vis, int i, int j, int m, int n, String psf) {// psf means
        // path
        // so far

        if (vis[i][j] == true || grid[i][j] == 1) {// Base Case , no need to check isValid here cause we are doing it
            // when we are adding child
            return;
        }

        vis[i][j] = true;// MARK

        if (i == m && j == n) {// WORK
            System.out.println(psf);
        }

        if (isValid(grid, i - 1, j) && vis[i - 1][j] == false) {// Add child only if it is not visited and isValid
            floodFill(grid, vis, i - 1, j, m, n, psf + "T");
        }

        if (isValid(grid, i, j - 1) && vis[i][j - 1] == false) {
            floodFill(grid, vis, i, j - 1, m, n, psf + "L");
        }

        if (isValid(grid, i + 1, j) && vis[i + 1][j] == false) {
            floodFill(grid, vis, i + 1, j, m, n, psf + "D");
        }

        if (isValid(grid, i, j + 1) && vis[i][j + 1] == false) {
            floodFill(grid, vis, i, j + 1, m, n, psf + "R");
        }

        vis[i][j] = false; // backtrack by making it false cause we want to explore all path and not just 1
        // path which dfs gives , remove this line and it will only give u one path

    }

    public static boolean isValid(int grid[][], int i, int j) {
        return !(i < 0 || j < 0 || i >= grid.length || j >= grid.length);
    }
}
