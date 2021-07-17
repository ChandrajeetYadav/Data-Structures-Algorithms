package com.sde.chandu.stack;

public class StackUsingArray {
    private static final int MAX = 1000;
    private int top;
    private int[] arr = new int[MAX];

    StackUsingArray() {
        top = -1;
    }

    public static void main(String[] args) {
        StackUsingArray stack = new StackUsingArray();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println(stack);
    }

    // Time Complexity : O(1)
    public boolean isEmpty() {
        return top < 0;
    }

    // Time Complexity : O(1)
    public boolean push(int data) {
        if (top >= MAX) {
            System.out.println("Stack Overflow");
            return false;
        }
        arr[++top] = data;
        return true;
    }

    // Time Complexity : O(1)
    public int pop() {
        if (top < 0) {
            System.out.println("Stack underflow");
            return -1;
        }
        return arr[top--];
    }

    // Time Complexity : O(1)
    public int peek() {
        if (top < 0) {
            System.out.println("Stack underflow");
            return 0 - 1;
        }
        return arr[top];
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= top; i++)
            sb.append(arr[i]).append(" ");
        return sb.toString();
    }
}
