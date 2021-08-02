package com.sde.chandu.queue;

public class QueueUsingArray {
    private int front, rear, capacity;
    private int[] queue;

    QueueUsingArray(int capacity) {
        front = 0;
        rear = 0;
        this.capacity = capacity;
        queue = new int[capacity];
    }

    public static void main(String[] args) {
        QueueUsingArray obj = new QueueUsingArray(4);
        obj.enqueue(20);
        obj.enqueue(30);
        obj.enqueue(40);
        obj.enqueue(50);
        obj.display();

        obj.enqueue(60);
        obj.display();

        obj.dequeue();
        obj.dequeue();
        System.out.println("Queue after deleting 2 elements: ");
        obj.display();
        System.out.println("Front of queue: " + obj.front());
    }

    // Time complexity : O(1)
    void enqueue(int data) {
        if (capacity == rear) {
            System.out.println("Queue is full");
            return;
        }
        queue[rear++] = data;
    }

    // Time complexity : O(n)
    int dequeue() {
        if (front == rear) {
            System.out.println("Queue is empty");
            return -1;
        }
        int data = queue[front];
        for (int i = front; i < rear - 1; i++)
            queue[i] = queue[i + 1];
        queue[--rear] = 0;
        return data;
    }

    // Time complexity : O(1)
    int front() {
        if (front == rear)
            return -1;
        return queue[front];
    }

    void display() {
        if (front == rear) {
            System.out.println("Queue is empty");
            return;
        }
        for (int i = front; i < rear; i++)
            System.out.print(queue[i] + "\t");
        System.out.println();
    }
}
