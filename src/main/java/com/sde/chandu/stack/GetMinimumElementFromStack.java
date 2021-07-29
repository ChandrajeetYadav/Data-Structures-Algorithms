package com.sde.chandu.stack;

import java.util.Stack;

public class GetMinimumElementFromStack {
    private Stack<Node> stack = new Stack<>();

    public static void main(String[] args) {
        GetMinimumElementFromStack obj = new GetMinimumElementFromStack();
        obj.push(10);
        obj.push(20);
        obj.push(30);
        System.out.println("Min element: " + obj.getMin());
        obj.push(5);
        System.out.println("Min element: " + obj.getMin());
    }

    void push(int x) {
        if (stack.isEmpty())
            stack.push(new Node(x, x));
        else {
            int min = x <= stack.peek().min ? x : stack.peek().min;
            stack.push(new Node(x, min));
        }
    }

    int pop() {
        if (stack.isEmpty())
            return -1;
        return stack.pop().data;
    }

    int getMin() {
        if (stack.isEmpty())
            return -1;
        return stack.peek().min;
    }

    static class Node {
        int data;
        int min;

        Node(int data, int min) {
            this.data = data;
            this.min = min;
        }
    }
}
