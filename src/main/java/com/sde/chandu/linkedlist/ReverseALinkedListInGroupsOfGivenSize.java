package com.sde.chandu.linkedlist;

import java.util.Stack;

public class ReverseALinkedListInGroupsOfGivenSize {
    public static void main(String[] args) {
        Node head = Node.createLinkedList(8);
        int k = 3;
        System.out.println("Original list: ");
        head.display();
        System.out.println("List after reversing in groups of " + k + " :");
        head = reverseInGroupsOfK(head, k);
        head.display();
        System.out.println();
        System.out.println("Original list: ");
        head.display();
        System.out.println("List after reversing in groups of " + k + " , using stack:");
        head = reverseInGroupsOfKUsingStack(head, k);
        head.display();
        System.out.println();

    }

    // Time complexity : O(n)
    // Space complexity : O(n/k)
    private static Node reverseInGroupsOfK(Node head, int k) {
        if (head == null)
            return head;
        Node cur = head, next = null, prev = null;
        int count = 0;
        while (count < k && cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
            count++;
        }
        if (next != null)
            head.next = reverseInGroupsOfK(next, k);
        return prev;
    }

    // Time complexity : O(n)
    // Space complexity : O(k)
    private static Node reverseInGroupsOfKUsingStack(Node head, int k) {
        Stack<Node> stack = new Stack<>();
        Node cur = head, prev = null;
        int count;
        while (cur != null) {
            count = 0;
            while (count < k && cur != null) {
                stack.push(cur);
                cur = cur.next;
                count++;
            }
            while (!stack.isEmpty()) {
                if (prev != null) {
                    prev.next = stack.pop();
                    prev = prev.next;
                } else {
                    prev = stack.pop();
                    head = prev;
                }
            }
        }
        prev.next = null;
        return head;
    }
}
