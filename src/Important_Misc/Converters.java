package Important_Misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Converters {

    public static void main(String args[]) {
        int num1 = Integer.parseInt("102");
        String num2 = String.valueOf(102);

        // char to num
        int ascii = 'z';
        int num3 = 'z' - 'a';

        //num to char
        char ch = 97;

        // Array to ArrayList
        Integer[] ar = new Integer[]{1, 2, 3};
        List<Integer> alCon = new ArrayList<>(Arrays.asList(ar));

        for (int ele : alCon) {
            System.out.println(ele);
        }
        // ArrayList to Array
        List<Integer> al = new ArrayList<>();
        al.add(10);
        al.add(20);
        Integer[] arr = al.toArray(new Integer[0]);

        for (int ele : arr) {
            System.out.println(ele);
        }

        // String to Character Array
        String str = "abcd";
        char[] chCon = str.toCharArray();

        // Character Array to string
        char[] charAr = new char[]{'a', 'b', 'c'};
        String strCon = new String(charAr);
    }

}
