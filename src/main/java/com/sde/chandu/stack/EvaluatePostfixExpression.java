package com.sde.chandu.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluatePostfixExpression {
    public static void main(String[] args) {
        String expWithoutSpace = "231*+9-";
        System.out.println("Value of postfix expression " + expWithoutSpace + " : " + evaluatePostfixWithSingleDigitNumber(expWithoutSpace));
        expWithoutSpace = "123+*8-";
        System.out.println("Value of postfix expression " + expWithoutSpace + " : " + evaluatePostfixWithSingleDigitNumber(expWithoutSpace));
        System.out.println();

        String expWithSpace = "100 200 + 2 / 5 * 7 +";
        System.out.println("Value of postfix expression " + expWithSpace + " : " + evaluatePostfixWithMultipleDigitNumber(expWithSpace));
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    private static int evaluatePostfixWithSingleDigitNumber(String s) {
        if (s == null || s.length() == 0)
            return 0;
        Deque<Integer> stack = new ArrayDeque<>();
        char ch;
        int val1, val2;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            if (Character.isDigit(ch))
                stack.push(ch - '0');
            else {
                val1 = stack.pop();
                val2 = stack.pop();
                switch (ch) {
                    case '+':
                        stack.push(val2 + val1);
                        break;
                    case '-':
                        stack.push(val2 - val1);
                        break;
                    case '*':
                        stack.push(val2 * val1);
                        break;
                    case '/':
                        stack.push(val2 / val1);
                        break;
                }
            }
        }
        return stack.pop();
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    private static int evaluatePostfixWithMultipleDigitNumber(String s) {
        if (s == null || s.length() == 0)
            return 0;
        Deque<Integer> stack = new ArrayDeque<>();
        char ch;
        int num, a, b;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            if (ch == ' ')
                continue;
            else if (Character.isDigit(ch)) {
                num = 0;
                while (Character.isDigit(ch)) {
                    num = num * 10 + (ch - '0');
                    i++;
                    ch = s.charAt(i);
                }
                i--;
                stack.push(num);
            } else {
                a = stack.pop();
                b = stack.pop();
                switch (ch) {
                    case '+':
                        stack.push(b + a);
                        break;
                    case '-':
                        stack.push(b - a);
                        break;
                    case '*':
                        stack.push(b * a);
                        break;
                    case '/':
                        stack.push(b / a);
                        break;
                }
            }
        }
        return stack.pop();
    }
}
