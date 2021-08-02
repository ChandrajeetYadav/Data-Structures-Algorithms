package com.sde.chandu.queue;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class QueueUsingJavaLibraray {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(10);
        queue.add(20);
        queue.add(30);
        queue.remove();
        System.out.println("Queue using LinkedList : \t" + queue);
        System.out.println("Front of the queue: " + queue.peek());

        queue = new ArrayDeque<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.remove();
        System.out.println("Queue using ArrayDeque : \t" + queue);
        System.out.println("Front of the queue: " + queue.peek());
    }
}
