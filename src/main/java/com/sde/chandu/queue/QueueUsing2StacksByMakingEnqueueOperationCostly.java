package com.sde.chandu.queue;

import java.util.Stack;

public class QueueUsing2StacksByMakingEnqueueOperationCostly {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    QueueUsing2StacksByMakingEnqueueOperationCostly() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public static void main(String[] args) {
        QueueUsing2StacksByMakingEnqueueOperationCostly queue = new QueueUsing2StacksByMakingEnqueueOperationCostly();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        System.out.println("Dequeued element is: " + queue.dequeue());
        System.out.println("Dequeued element is: " + queue.dequeue());
        System.out.println("Dequeued element is: " + queue.dequeue());
    }

    // Time complexity : O(n)
    void enqueue(int x) {
        while (!stack1.isEmpty())
            stack2.push(stack1.pop());
        stack1.push(x);
        while (!stack2.isEmpty())
            stack1.push(stack2.pop());
    }

    // Time complexity : O(1)
    int dequeue() {
        if (stack1.isEmpty())
            return -1;
        return stack1.pop();
    }
}
