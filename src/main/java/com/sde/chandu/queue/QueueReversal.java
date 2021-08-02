package com.sde.chandu.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class QueueReversal {
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
        System.out.println("Original queue: ");
        System.out.println(queue);
        System.out.println("Queue after reversing using stack: ");
        reverseQueueUsingStack(queue);
        System.out.println(queue);
        System.out.println();

        System.out.println("Original queue: ");
        System.out.println(queue);
        System.out.println("Queue after reversing using recursion: ");
        reverseQueueUsingRecursion(queue);
        System.out.println(queue);

    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    private static void reverseQueueUsingStack(Queue<Integer> queue) {
        if (queue == null || queue.isEmpty())
            return;
        Stack<Integer> stack = new Stack<>();
        while (!queue.isEmpty())
            stack.push(queue.remove());
        while (!stack.isEmpty())
            queue.add(stack.pop());
    }

    // Time complexity : O(n)
    // Space complexity : O(n), for method stack
    private static Queue<Integer> reverseQueueUsingRecursion(Queue<Integer> queue) {
        if (queue.isEmpty())
            return queue;
        int item = queue.remove();
        reverseQueueUsingRecursion(queue);
        queue.add(item);
        return queue;
    }
}
