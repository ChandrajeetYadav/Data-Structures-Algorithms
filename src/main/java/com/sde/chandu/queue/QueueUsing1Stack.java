package com.sde.chandu.queue;

import java.util.Stack;

public class QueueUsing1Stack {
    private Stack<Integer> stack;

    QueueUsing1Stack() {
        stack = new Stack<>();
    }

    public static void main(String[] args) {
        QueueUsing1Stack queue = new QueueUsing1Stack();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        System.out.println("Dequeued element is: " + queue.dequeue());
        System.out.println("Dequeued element is: " + queue.dequeue());
        System.out.println("Dequeued element is: " + queue.dequeue());
    }

    // Time complexity : O(1)
    void enqueue(int x) {
        stack.push(x);
    }

    // Time complexity : O(n)
    int dequeue() {
        if (stack.isEmpty())
            return -1;
        else if (stack.size() == 1)
            return stack.pop();
        else {
            int x = stack.pop();
            int res = dequeue();
            enqueue(x);
            return res;
        }
    }
}
