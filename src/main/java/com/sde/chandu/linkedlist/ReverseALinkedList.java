package com.sde.chandu.linkedlist;

import java.util.Stack;

public class ReverseALinkedList {
    public static void main(String[] args) {
        Node head = Node.createLinkedList(5);
        System.out.println("Original list: ");
        head.display();
        head = reverse(head);
        System.out.println("Reverse linked list: ");
        head.display();
        System.out.println();

        System.out.println("Original list: ");
        head.display();
        head = reverseUsingRecursion(head);
        System.out.println("Reverse linked list using recursion: ");
        head.display();
        System.out.println();

        System.out.println("Original list: ");
        head.display();
        head = reverseUsingStack(head);
        System.out.println("Reverse linked list using stack: ");
        head.display();
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static Node reverse(Node head) {
        Node prev = null, cur = head, next;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static Node reverseUsingRecursion(Node head) {
        if (head == null || head.next == null)
            return head;
        Node rest = reverseUsingRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return rest;
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    private static Node reverseUsingStack(Node head) {
        if (head == null)
            return head;
        Stack<Node> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        head = stack.pop();
        Node temp = head;
        while (!stack.isEmpty()) {
            temp.next = stack.pop();
            temp = temp.next;
        }
        temp.next = null;
        return head;
    }
}
