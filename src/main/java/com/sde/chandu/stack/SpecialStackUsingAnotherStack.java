package com.sde.chandu.stack;

import java.util.Stack;

public class SpecialStackUsingAnotherStack {
    private Stack<Integer> min = new Stack<>();
    private Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) {
        SpecialStackUsingAnotherStack obj = new SpecialStackUsingAnotherStack();
        obj.push(10);
        obj.push(20);
        obj.push(30);
        System.out.println("Cuurent stack:" + obj.stack);
        System.out.println("Min stack:" + obj.min);
        System.out.println("Min element: " + obj.getMin());
        obj.push(5);
        System.out.println("Cuurent stack:" + obj.stack);
        System.out.println("Min stack:" + obj.min);
        System.out.println("Min element: " + obj.getMin());
    }

    // Time complexity : O(1)
    // Space complexity : O(1)
    void push(int x) {
        if (stack.isEmpty()) {
            stack.push(x);
            min.push(x);
        } else {
            stack.push(x);
            int y = min.peek();
            if (x < y)
                min.push(x);
            else
                min.push(y);
        }
    }

    // Time complexity : O(1)
    // Space complexity : O(1)
    int pop() {
        if (stack.isEmpty())
            return -1;
        min.pop();
        return stack.pop();
    }

    // Time complexity : O(1)
    // Space complexity : O(n)
    int getMin() {
        if (min.isEmpty())
            return -1;
        return min.peek();
    }
}
