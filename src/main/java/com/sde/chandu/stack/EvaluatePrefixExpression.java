package com.sde.chandu.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluatePrefixExpression {
    public static void main(String[] args) {
        String expSingleDigit = "+9*26";
        System.out.println("Value of prefix expression " + expSingleDigit + " : " + evaluatePrefixWithSingleDigitNumber(expSingleDigit));
        System.out.println();

        String expMultipleDigit = "+ 9 * 12 6";
        System.out.println("Value of prefix expression " + expMultipleDigit + " : " + evaluatePrefixWithMultipleDigitNumber(expMultipleDigit));
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    private static int evaluatePrefixWithSingleDigitNumber(String s) {
        if (s == null || s.length() == 0)
            return 0;
        Deque<Integer> stack = new ArrayDeque<>();
        char ch;
        int a, b;
        for (int i = s.length() - 1; i >= 0; i--) {
            ch = s.charAt(i);
            if (Character.isDigit(ch))
                stack.push(ch - '0');
            else {
                a = stack.pop();
                b = stack.pop();
                switch (ch) {
                    case '+':
                        stack.push(a + b);
                        break;
                    case '-':
                        stack.push(a - b);
                        break;
                    case '*':
                        stack.push(a * b);
                        break;
                    case '/':
                        stack.push(a / b);
                        break;
                }
            }
        }
        return stack.pop();
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    private static int evaluatePrefixWithMultipleDigitNumber(String s) {
        if (s == null || s.length() == 0)
            return 0;
        Deque<Integer> stack = new ArrayDeque<>();
        char ch;
        int num, a, b, p;
        for (int i = s.length() - 1; i >= 0; i--) {
            ch = s.charAt(i);
            if (ch == ' ')
                continue;
            else if (Character.isDigit(ch)) {
                num = 0;
                p = 0;
                while (i >= 0 && Character.isDigit(ch)) {
                    num = (int) Math.pow(10, p++) * (ch - '0') + num;
                    i--;
                    ch = s.charAt(i);
                }
                i++;
                stack.push(num);
            } else {
                a = stack.pop();
                b = stack.pop();
                switch (ch) {
                    case '+':
                        stack.push(a + b);
                        break;
                    case '-':
                        stack.push(a - b);
                        break;
                    case '*':
                        stack.push(a * b);
                        break;
                    case '/':
                        stack.push(a / b);
                        break;
                }
            }
        }
        return stack.pop();
    }
}
