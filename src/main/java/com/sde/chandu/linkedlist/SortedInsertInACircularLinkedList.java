package com.sde.chandu.linkedlist;

public class SortedInsertInACircularLinkedList {
    public static void main(String[] args) {
        int[] arr = {3, 7, 9, 11};
        CircularLinkedList head = CircularLinkedList.createCircularLL(arr);
        System.out.println("Original circular linked list: ");
        head.display();
        int data = 2;
        System.out.println("List after inserting " + data + ", linear approach:");
        head = sortednsert(head, data);
        head.display();
        data = 1;
        System.out.println("List after inserting " + data + ", linear approach enhanced:");
        head = sortednsertEnhanced(head, data);
        head.display();
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static CircularLinkedList sortednsert(CircularLinkedList head, int data) {
        CircularLinkedList newNode = new CircularLinkedList(data);
        CircularLinkedList cur = head;
        if (head == null) {
            newNode.next = newNode;
            head = newNode;
        } else if (newNode.data < head.data) {
            while (cur.next != head)
                cur = cur.next;
            cur.next = newNode;
            newNode.next = head;
            head = newNode;
        } else {
            while (cur.next != head && cur.next.data < newNode.data)
                cur = cur.next;
            newNode.next = cur.next;
            cur.next = newNode;
        }
        return head;
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static CircularLinkedList sortednsertEnhanced(CircularLinkedList head, int data) {
        CircularLinkedList newNode = new CircularLinkedList(data);
        CircularLinkedList temp = head;
        if (head == null) {
            newNode.next = newNode;
            head = newNode;
        } else if (newNode.data < head.data) {
            newNode.data = head.data;
            head.data = data;
            newNode.next = head.next;
            head.next = newNode;
        } else {
            while (temp.next != head && temp.next.data < newNode.data)
                temp = temp.next;
            newNode = temp.next;
            temp.next = newNode;
        }
        return head;
    }
}
