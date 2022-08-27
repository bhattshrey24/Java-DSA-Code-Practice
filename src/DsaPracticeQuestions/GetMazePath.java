package DsaPracticeQuestions;

import java.util.ArrayList;

public class GetMazePath {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(getMazePath(1, 1, 3, 3));
        // getMazePath(3, 3);
        printMazePath(1, 1, 3, 3, "");
    }

    public static ArrayList<String> getMazePath(int r, int c, int n, int m) {// n=row , m= col
        if (r == n && c == m) {// base case , if you are given 1X1 array given then no. of ways to reach
            // destination
            // is 1 i.e. don't go anywhere i.e. empty string , or you can think it this way
            // that travel krte krte we reached destination so now destination se
            // destination tk pohohne ke tareeke is only 1 i.e. dont go anywhere i.e. empty
            // string
            ArrayList<String> baseAns = new ArrayList<>();
            baseAns.add("");
            return baseAns;
        }

        if (r > n || c > m) {// base case , i.e. travel krte krte when you go out of bound i.e. out of maze
            // so since you can't
            // come upwards therefore 0 way to reach destination
            // therefore return empty arrayList
            ArrayList<String> baseAns = new ArrayList<>();

            return baseAns;
        }

        ArrayList<String> recAnsH = getMazePath(r, c + 1, n, m);// have faith that recursion will give you paths from
        // (r,c+1) to (n,m)
        ArrayList<String> recAnsV = getMazePath(r + 1, c, n, m);// have faith that recursion will give you paths from
        // (r+1,c) to (n,m)
        ArrayList<String> myAns = new ArrayList<>();

        for (String str : recAnsH) {
            myAns.add("H" + str);
        }
        for (String str : recAnsV) {
            myAns.add("V" + str);
        }

        return myAns;
    }

    public static void printMazePath(int r, int c, int m, int n, String path) {

        if (r == n && c == m) {// base case , the smallest input here can be that u are given 1X1 matric so to
            // reach destination no move is required just empty string ans remember that u
            // passed empty string in path , or u can think it this way that will travelling
            // u reached destination so have faith that "path" stores the path that u took
            // to
            // reach destination , so simply print it
            System.out.print(path + ", ");
            return;
        }
        if (r > n || c > m) {// out of board then no need to print "path" simply return , i.e. the path
            // created in this branch of recursion tree was wrong so simply go back
            return;
        }
        printMazePath(r + 1, c, m, n, "V" + path);// have faith that recursion will print all path from r+1,c to n,n ,
        // you just have to add V i.e. the step you took to reach (r+1,c)
        // cell
        printMazePath(r, c + 1, m, n, "H" + path);
    }
}
