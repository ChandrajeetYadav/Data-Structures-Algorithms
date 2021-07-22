package com.sde.chandu.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class ParenthesisChecker {
    public static void main(String[] args) {
        String s1 = "[()]{}{[()()]()}";
        String s2 = "[(])";
        System.out.println("Has " + s1 + " balanced paranthesis: " + isBalanced(s1));
        System.out.println("Has " + s2 + " balanced paranthesis: " + isBalanced(s2));
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    private static boolean isBalanced(String exp) {
        Deque<Character> stack = new ArrayDeque<>();
        char ch, storedCh;
        for (int i = 0; i < exp.length(); i++) {
            ch = exp.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
                continue;
            }
            if (stack.isEmpty())
                return false;
            storedCh = stack.pop();
            switch (ch) {
                case ')':
                    if (storedCh == '{' || storedCh == '[')
                        return false;
                    break;
                case '}':
                    if (storedCh == '(' || storedCh == '[')
                        return false;
                    break;
                case ']':
                    if (storedCh == '(' || storedCh == '{')
                        return false;
                    break;
            }
        }
        return stack.isEmpty();
    }
}
