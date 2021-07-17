package com.sde.chandu.stack;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingTwoQueues1 {
    private static Queue<Integer> q1 = new LinkedList<>();
    private static Queue<Integer> q2 = new LinkedList<>();

    public static void main(String[] args) {
        StackUsingTwoQueues1 stack = new StackUsingTwoQueues1();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Current stack size: " + stack.size());
        System.out.println("Popped element: " + stack.pop());
        System.out.println("Popped element: " + stack.pop());
        System.out.println("Top element: " + stack.peek());
        System.out.println("Current stack size: " + stack.size());
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    void push(int data) {
        q2.add(data);
        while (!q1.isEmpty())
            q2.add(q1.remove());
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    // Time complexity : O(1)
    // Space complexity : O(1)
    int pop() {
        if (q1.isEmpty())
            return -1;
        return q1.remove();
    }

    // Time complexity : O(1)
    // Space complexity : O(1)
    int peek() {
        if (q1.isEmpty())
            return -1;
        return q1.peek();
    }

    // Time complexity : O(1)
    // Space complexity : O()
    int size() {
        return q1.size();
    }

    public String toString() {
        return q1.toString();
    }
}
