package com.sde.chandu.queue;

public class QueueUsingArrayEfficient {
    private int front, rear, capacity, size;
    private int[] arr;

    QueueUsingArrayEfficient(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        front = size = 0;
        rear = capacity - 1;
    }

    public static void main(String[] args) {
        QueueUsingArrayEfficient queue = new QueueUsingArrayEfficient(4);
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        System.out.println("Queue is as below");
        queue.display();
        queue.enqueue(50);
        System.out.println(queue.dequeue() + " dequeued from queue.");
        System.out.println("Queue is as below");
        queue.display();
        System.out.println("Front item is " + queue.front());
        System.out.println("Rear item is " + queue.rear());
        queue.enqueue(50);
        System.out.println("Queue is as below");
        queue.display();

    }

    // Time complexity : O(1)
    boolean isFull() {
        return size == capacity;
    }

    // Time complexity : O(1)
    boolean isEmpty() {
        return size == 0;
    }

    // Time complexity : O(1)
    void enqueue(int data) {
        if (isFull()) {
            System.out.println("Queue is full");
            return;
        }
        rear = (rear + 1) % capacity;
        arr[rear] = data;
        size++;
    }

    // Time complexity : O(1)
    int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return -1;
        }
        int item = arr[front];
        front = (front + 1) % capacity;
        size--;
        return item;
    }

    // Time complexity : O(1)
    int front() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return -1;
        }
        return arr[front];
    }

    // Time complexity : O(1)
    int rear() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return -1;
        }
        return arr[rear];
    }

    public void display() {
        if (isEmpty())
            return;
        if (front <= rear) {
            for (int i = front; i <= rear; i++)
                System.out.print(arr[i] + "\t");
        } else {
            for (int i = front; i < capacity; i++)
                System.out.print(arr[i] + "\t");
            for (int i = 0; i <= rear; i++)
                System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }
}
