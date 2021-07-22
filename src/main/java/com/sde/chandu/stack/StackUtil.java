package com.sde.chandu.stack;

import java.util.Stack;

public class StackUtil {

    public static Stack<Integer> createStack(int[] arr) {
        if (arr == null)
            return null;
        Stack<Integer> stack = new Stack<>();
        for (int i : arr)
            stack.push(i);
        return stack;
    }

    public static String printStackFromTop(Stack<Integer> stack) {
        if (stack == null || stack.isEmpty())
            return "";
        String res = "";
        for (int i : stack)
            res = i + " " + res;
        return res;
    }
}
