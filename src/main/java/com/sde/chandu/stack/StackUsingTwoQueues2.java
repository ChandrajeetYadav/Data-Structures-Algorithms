package com.sde.chandu.stack;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingTwoQueues2 {
    private static Queue<Integer> q1 = new LinkedList<>();
    private static Queue<Integer> q2 = new LinkedList<>();

    public static void main(String[] args) {
        StackUsingTwoQueues2 stack = new StackUsingTwoQueues2();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Current stack size: " + stack.size());
        System.out.println("Popped element: " + stack.pop());
        System.out.println("Popped element: " + stack.pop());
        System.out.println("Current stack size: " + stack.size());
    }

    // Time complexity : O(1)
    // Space complexity : O(1)
    void push(int data) {
        q1.add(data);
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    int pop() {
        if (q1.isEmpty())
            return -1;
        while (q1.size() != 1)
            q2.add(q1.remove());
        int data = q1.remove();
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
        return data;
    }

    int size() {
        return q1.size();
    }
}
