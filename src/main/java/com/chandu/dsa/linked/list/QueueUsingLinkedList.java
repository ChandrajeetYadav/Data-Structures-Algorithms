package com.chandu.dsa.linked.list;

public class QueueUsingLinkedList {
    public Node front;
    public Node rear;

    public static void main(String[] args) {
        System.out.println("Initial Queue: ");
        QueueUsingLinkedList obj = new QueueUsingLinkedList();
        Node.printList(obj.front);
        obj.enqueue(1);
        obj.enqueue(2);
        obj.enqueue(3);
        System.out.println("Queue after adding elements: ");
        Node.printList(obj.front);
        obj.dequeue();
        obj.dequeue();
        System.out.println("Queue after removing elements: ");
        Node.printList(obj.front);
    }

    //Time Complexity: O(1)
    //Space Complexity: O(1)
    public void enqueue(int data){
        Node node = new Node(data);
        if(rear == null){
            front = rear = node;
            return;
        }
        rear.next = node;
        rear = node;
    }

    //Time Complexity: O(1)
    //Space Complexity: O(1)
    public int dequeue(){
        if(front == null)
            return -1;
        Node temp = front;
        front = front.next;
        if(front == null)
            rear = null;
        else
            temp.next = null;
        return temp.data;
    }
}
