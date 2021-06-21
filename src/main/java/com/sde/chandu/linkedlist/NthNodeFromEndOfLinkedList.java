package com.sde.chandu.linkedlist;

public class NthNodeFromEndOfLinkedList {
    public static void main(String[] args) {
        Node head = Node.createLinkedList(9);
        int n = 2;
        System.out.println("Original list: ");
        head.display();
        System.out.println(n + "th node from end, by finding length: " + getNthNodeFromEndByFindingLength(head, n));

        n = 3;
        System.out.println(n + "th node from end, using recursion: " + getNthNodeFromEndUsingRecursion(head, n));

        n = 10;
        System.out.println(n + "th node from end, using 2 pointer: " + getNthNodeFromEndUsing2Pointer(head, n));
        n = 9;
        System.out.println(n + "th node from end, using 2 pointer: " + getNthNodeFromEndUsing2Pointer(head, n));
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static int getNthNodeFromEndByFindingLength(Node head, int n) {
        int length = getLength(head);
        if (length < n)
            return -1;
        for (int i = 1; i < length - n + 1; i++)
            head = head.next;
        return head.data;
    }

    private static int getLength(Node head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }

    // Time complexity : O(n)
    // Space complexity : O(n), for recursion stack
    private static int getNthNodeFromEndUsingRecursion(Node head, int n) {
        Helper helper = new Helper();
        getNthNodeFromEndUsingRecursionHelper(head, n, helper);
        return helper.res;
    }

    private static void getNthNodeFromEndUsingRecursionHelper(Node head, int n, Helper helper) {
        if (head == null)
            return;
        getNthNodeFromEndUsingRecursionHelper(head.next, n, helper);
        if (++helper.count == n)
            helper.res = head.data;
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static int getNthNodeFromEndUsing2Pointer(Node head, int n) {
        Node cur = head;
        int i;
        for (i = 1; cur != null && i <= n; i++)
            cur = cur.next;
        if (i <= n)
            return -1;
        while (cur != null) {
            cur = cur.next;
            head = head.next;
        }
        return head.data;
    }

    static class Helper {
        int count;
        int res = -1;
    }
}
