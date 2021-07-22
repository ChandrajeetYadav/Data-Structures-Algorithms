package com.sde.chandu.stack;

import java.util.Stack;

public class SortAStack {
    public static void main(String[] args) {
        int[] arr = {30, -5, 18, 14, -3};
        Stack<Integer> stack = StackUtil.createStack(arr);
        System.out.println("Original stack: ");
        System.out.println(StackUtil.printStackFromTop(stack));
        System.out.println("Stack after sorting, using recursion: ");
        sortUsingRecursion(stack);
        System.out.println(StackUtil.printStackFromTop(stack));
        System.out.println();

        stack = StackUtil.createStack(arr);
        System.out.println("Original stack: ");
        System.out.println(StackUtil.printStackFromTop(stack));
        System.out.println("Stack after sorting, using another stack: ");
        stack = sortUsingAnotherStack(stack);
        System.out.println(StackUtil.printStackFromTop(stack));
    }

    // Time complexity : O(n^2)
    // Space complexity : O(n) // for method stack calls
    private static void sortUsingRecursion(Stack<Integer> stack) {
        if (!stack.isEmpty()) {
            int num = stack.pop();
            sortUsingRecursion(stack);
            sortedInsert(num, stack);
        }
    }

    private static void sortedInsert(int num, Stack<Integer> stack) {
        if (stack.isEmpty() || stack.peek() <= num)
            stack.push(num);
        else {
            int temp = stack.pop();
            sortedInsert(num, stack);
            stack.push(temp);
        }
    }

    // Time complexity : O(n^2)
    // Space complexity : O(n)
    private static Stack<Integer> sortUsingAnotherStack(Stack<Integer> stack) {
        if (stack == null || stack.isEmpty())
            return stack;
        Stack<Integer> temp = new Stack<>();
        int num;
        while (!stack.isEmpty()) {
            num = stack.pop();
            while (!temp.isEmpty() && temp.peek() > num)
                stack.push(temp.pop());
            temp.push(num);
        }
        return temp;
    }
}
