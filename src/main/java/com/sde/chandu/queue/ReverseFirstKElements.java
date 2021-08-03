package com.sde.chandu.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ReverseFirstKElements {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(10);
        queue.add(20);
        queue.add(30);
        queue.add(40);
        queue.add(50);
        queue.add(60);
        queue.add(70);
        queue.add(80);
        queue.add(90);
        queue.add(100);

        int k = 5;

        System.out.println("Original queue: ");
        System.out.println(queue);
        System.out.println("Queue after reversing first " + k + " elements");
        reverseFirstKElementsUsingStack(queue, k);
        System.out.println(queue);
    }

    // Time complexity :  O(n)
    // Space complexity :  O(n)
    private static void reverseFirstKElementsUsingStack(Queue<Integer> queue, int k) {
        if (queue == null || queue.isEmpty() || k <= 0 || k > queue.size())
            return;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < k; i++)
            stack.push(queue.poll());
        while (!stack.isEmpty())
            queue.add(stack.pop());
        for (int i = 0; i < queue.size() - k; i++)
            queue.add(queue.poll());
    }
}
