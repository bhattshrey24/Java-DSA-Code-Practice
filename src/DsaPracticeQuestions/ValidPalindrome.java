package DsaPracticeQuestions;

public class ValidPalindrome {
    public static void main(String args[]) {
        ValidPalindrome ob = new ValidPalindrome();
        System.out.println(ob.isSpecial('0'));
        System.out.println(ob.isPalindrome("......a....."));
    }

    public boolean isPalindrome(String s) {
        String ans = "";
        s = s.toLowerCase();

        for (int i = 0; i < s.length(); i++) {
            if (isSpecial(s.charAt(i))) {
                continue;
            }
            ans += s.charAt(i); // making a new string without special characters then we'll simply check for palindrom
        }

        if (ans.isEmpty()) {
            return true;
        }
        if (ans.length() == 1) {
            return true;
        }

        for (int i = 0, j = ans.length() - 1; i < j; i++, j--) {
            if (ans.charAt(i) != ans.charAt(j)) {
                return false;
            }
        }

        return true;
    }

    public boolean isSpecial(char ch) {
        return !(((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9')));
    }
}
