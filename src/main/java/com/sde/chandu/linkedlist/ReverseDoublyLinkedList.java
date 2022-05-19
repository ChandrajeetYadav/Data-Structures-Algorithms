package com.sde.chandu.linkedlist;

import java.util.Stack;

public class ReverseDoublyLinkedList {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        DoublyLinkedList head = DoublyLinkedList.createList(arr);
        System.out.println("Original list: ");
        head.display();
        System.out.println("Doubly Linked list after reversing, efficient approach: ");
        head = reverse(head);
        head.display();
        System.out.println();

        System.out.println("Original list: ");
        head.display();
        System.out.println("Doubly Linked list after reversing, using stack: ");
        head = reverseUsingStack(head);
        head.display();
        System.out.println();

        System.out.println("Original list: ");
        head.display();
        System.out.println("Doubly Linked list after reversing, using recursion: ");
        head = reverseUsingRecursion(head);
        head.display();
        System.out.println();
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static DoublyLinkedList reverse(DoublyLinkedList head) {
        DoublyLinkedList cur = head, temp = null;
        while (cur != null) {
            temp = cur.prev;
            cur.prev = cur.next;
            cur.next = temp;
            cur = cur.prev;
        }
        if (temp != null)
            head = temp.prev;
        return head;
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    private static DoublyLinkedList reverseUsingStack(DoublyLinkedList head) {
        Stack<Integer> stack = new Stack<>();
        DoublyLinkedList temp = head;
        while (temp != null) {
            stack.push(temp.data);
            temp = temp.next;
        }
        temp = head;
        while (temp != null) {
            temp.data = stack.pop();
            temp = temp.next;
        }
        return head;
    }

    // Time complexity : O(n)
    // Space complexity : O(1), ignoring recursion stack
    private static DoublyLinkedList reverseUsingRecursion(DoublyLinkedList head) {
        if (head == null)
            return null;
        DoublyLinkedList next = head.next;
        head.next = head.prev;
        head.prev = next;
        if (head.prev == null)
            return head;
        return reverseUsingRecursion(head.prev);
    }
}
