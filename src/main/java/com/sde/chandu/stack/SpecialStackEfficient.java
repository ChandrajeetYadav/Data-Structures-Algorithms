package com.sde.chandu.stack;

import java.util.Stack;

public class SpecialStackEfficient {
    private Stack<Integer> stack = new Stack<>();
    private int min = -1;

    public static void main(String[] args) {
        SpecialStackEfficient obj = new SpecialStackEfficient();
        obj.push(10);
        obj.push(20);
        obj.push(30);
        System.out.println("Cuurent stack:" + obj.stack);
        System.out.println("Min element: " + obj.getMin());
        obj.push(5);
        System.out.println("Cuurent stack:" + obj.stack);
        System.out.println("Cuurent top element:" + obj.peek());
        System.out.println("Min element: " + obj.getMin());
        System.out.println("Popping element: " + obj.pop());
    }

    // Time complexity : O(1)
    // Space complexity : O(1)
    void push(int x) {
        if (stack.isEmpty()) {
            min = x;
            stack.push(x);
        } else {
            if (x < min) {
                stack.push(2 * x - min);
                min = x;
            } else
                stack.push(x);
        }
    }

    // Time complexity : O(1)
    // Space complexity : O(1)
    int pop() {
        if (stack.isEmpty())
            return -1;
        int y = stack.pop();
        int num = y;
        if (y < min) {
            num = min;
            min = 2 * min - y;
        }
        return num;
    }

    // Time complexity : O(1)
    // Space complexity : O(1)
    int getMin() {
        if (stack.isEmpty())
            return -1;
        return min;
    }

    // Time complexity : O(1)
    // Space complexity : O(1)
    int peek() {
        if (stack.isEmpty())
            return -1;
        return stack.peek() < min ? min : stack.peek();
    }
}
