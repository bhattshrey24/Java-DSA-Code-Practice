package DsaPracticeQuestions;

import java.util.HashMap;

public class ValidAnagram {
    //242 leetcode
    public static void main(String args[]) {
        System.out.println(isAnagram("anagram", "nagaram"));
    }

    public static boolean isAnagram(String s, String t) {
        boolean isAnag = true;

        HashMap<Character, Integer> hmForS = new HashMap<>();

        for (int i = 0; i < s.length(); i++) { // simply create a frequency HM for each character in String s
            char ch = s.charAt(i);
            if (hmForS.containsKey(ch)) {
                hmForS.put(ch, hmForS.get(ch) + 1);
            } else {
                hmForS.put(ch, 1);
            }
        }

        for (int i = 0; i < t.length(); i++) { // Now traverse String t and keep
            // reducing the character count from hmForS of the matched character
            // if no match then simply return false
            char ch = t.charAt(i);
            if (hmForS.containsKey(ch)) {
                int count = hmForS.get(ch);
                if (count == 1) { // that means this is the last frequency of 'ch' left so simply remove it from HM
                    hmForS.remove(ch);
                } else {
                    hmForS.put(ch, count - 1);
                }
            } else {
                isAnag = false;
                break;
            }
        }
        if (!hmForS.isEmpty()) { // if still some characters are left then it means its not an anagram
            return false;
        }

        return isAnag;
    }
}
