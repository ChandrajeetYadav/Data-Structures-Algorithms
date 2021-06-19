package com.sde.chandu.linkedlist;

public class InsertInASortedList {
    public static void main(String[] args) {
        int[] arr = {2, 5, 7, 10, 15};
        Node head = Node.createLinkedList(arr);
        System.out.println("Original list: ");
        head.display();
        int num = 9;
        head = sortedInsert(head, num);
        System.out.println("Linked list after inserting " + num + " : ");
        head.display();
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static Node sortedInsert(Node head, int data) {
        Node newNode = new Node(data, null);
        if (head == null || head.data >= data) {
            newNode.next = head;
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null && temp.next.data < data)
                temp = temp.next;
            newNode.next = temp.next;
            temp.next = newNode;
        }
        return head;
    }
}
