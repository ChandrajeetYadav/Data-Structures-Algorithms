package com.sde.chandu.linkedlist;

public class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
    }

    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }

    public static Node createLinkedList(int[] arr) {
        if (arr == null || arr.length == 0)
            return null;
        Node head = new Node(arr[0], null);
        Node temp = head;
        for (int i = 1; i < arr.length; i++) {
            temp.next = new Node(arr[i], null);
            temp = temp.next;
        }
        return head;
    }

    public static Node createLinkedList(int noOfNodes) {
        if (noOfNodes <= 0)
            return null;
        Node head = new Node(1, null);
        Node temp = head;
        for (int i = 2; i <= noOfNodes; i++) {
            temp.next = new Node(i, null);
            temp = temp.next;
        }
        return head;
    }

    public void display() {
        Node temp = this;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}
