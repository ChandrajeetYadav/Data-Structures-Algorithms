package com.sde.chandu.linkedlist;

public class DeleteNodesHavingGreaterValueOnRight {
    public static void main(String[] args) {
        int[] arr = {12, 15, 10, 11, 5, 6, 2, 3};
        Node head = Node.createLinkedList(arr);
        System.out.println("Original list: ");
        head.display();
        System.out.println("List after deleting nodes having greater value on right, brute force approach:");
        head = deleteBrute(head);
        head.display();
        System.out.println();

        head = Node.createLinkedList(arr);
        System.out.println("Original list: ");
        head.display();
        System.out.println("List after deleting nodes having greater value on right, by reversing list:");
        head = deleteByReversing(head);
        head.display();
        System.out.println();

        head = Node.createLinkedList(arr);
        System.out.println("Original list: ");
        head.display();
        System.out.println("List after deleting nodes having greater value on right, using recursion:");
        head = deleteUsingRecursion(head);
        head.display();
        System.out.println();
    }

    // Time complexity : O(n^2)
    // Space complexity : O(1)
    private static Node deleteBrute(Node head) {
        if (head == null || head.next == null)
            return head;
        Node temp1 = head, temp2, prev = null;
        while (temp1 != null && temp1.next != null) {
            temp2 = temp1.next;
            while (temp2 != null) {
                if (temp1.data < temp2.data)
                    break;
                temp2 = temp2.next;
            }
            if (temp2 != null) {
                if (prev == null) {
                    head = head.next;
                    temp1.next = null;
                    temp1 = head;
                } else {
                    prev.next = temp1.next;
                    temp1.next = null;
                    temp1 = prev.next;
                }
            } else {
                prev = temp1;
                temp1 = temp1.next;
            }
        }
        return head;
    }

    // Time complexity : O(n)
    // Space complexity : O(n), for recursion stack
    private static Node deleteUsingRecursion(Node head) {
        if (head == null || head.next == null)
            return head;
        Node nextNode = deleteUsingRecursion(head.next);
        if (head.data < nextNode.data)
            return nextNode;
        head.next = nextNode;
        return head;
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static Node deleteByReversing(Node head) {
        if (head == null || head.next == null)
            return head;
        head = reverse(head);
        Node cur = head, temp;
        while (cur != null && cur.next != null) {
            if (cur.next.data < cur.data) {
                temp = cur.next;
                cur.next = cur.next.next;
                temp.next = null;
            } else {
                cur = cur.next;
            }
        }
        head = reverseRecursively(head);
        return head;
    }

    private static Node reverse(Node head) {
        Node cur = head, prev = null, next;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    private static Node reverseRecursively(Node head) {
        if (head == null || head.next == null)
            return head;
        Node rest = reverseRecursively(head.next);
        head.next.next = head;
        head.next = null;
        return rest;
    }
}
