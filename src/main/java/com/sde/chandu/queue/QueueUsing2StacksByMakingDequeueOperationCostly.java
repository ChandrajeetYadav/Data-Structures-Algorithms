package com.sde.chandu.queue;

import java.util.Stack;

public class QueueUsing2StacksByMakingDequeueOperationCostly {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    QueueUsing2StacksByMakingDequeueOperationCostly() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public static void main(String[] args) {
        QueueUsing2StacksByMakingDequeueOperationCostly queue = new QueueUsing2StacksByMakingDequeueOperationCostly();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        System.out.println("Dequeued element is: " + queue.dequeue());
        System.out.println("Dequeued element is: " + queue.dequeue());
        System.out.println("Dequeued element is: " + queue.dequeue());
    }

    // Time complexity : O(1)
    void enqueue(int x) {
        stack1.push(x);
    }

    // Time complexity : O(n)
    int dequeue() {
        if (stack1.isEmpty() && stack2.isEmpty())
            return -1;
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty())
                stack2.push(stack1.pop());
        }
        return stack2.pop();
    }
}
