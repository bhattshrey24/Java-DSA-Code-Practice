package DsaPracticeQuestions;

import java.util.Stack;

public class ValidParanthesis {

    public boolean isValid(String s) {

        Stack stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '[' || s.charAt(i) == '{' || s.charAt(i) == '(') { // simply push the open brackets
                stack.push(s.charAt(i));
            } else { // if encountered closed bracket then we simply check if the top character of the stack is opening of the current closing bracket if not simply return false
                if (stack.isEmpty()) {// this means there's no opening bracket left and we still encountered a closing bracket
                    return false;
                } else {
                    char removedBrace = (char) stack.pop();
                    if (removedBrace == '{') {
                        if (s.charAt(i) != '}') {
                            return false;
                        }
                    } else if (removedBrace == '[') {
                        if (s.charAt(i) != ']') {
                            return false;
                        }
                    } else {
                        if (s.charAt(i) != ')') {
                            return false;
                        }
                    }
                }
            }
        }

        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }
}
