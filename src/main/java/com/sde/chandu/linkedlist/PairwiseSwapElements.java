package com.sde.chandu.linkedlist;

public class PairwiseSwapElements {
    public static void main(String[] args) {
        Node head = Node.createLinkedList(7);
        System.out.println("Original list: ");
        head.display();
        System.out.println("List after pairwise swap: ");
        head = pairWiseSwap(head);
        head.display();
        System.out.println();

        System.out.println("Original list: ");
        head.display();
        System.out.println("List after pairwise swap, using recursion: ");
        head = pairWiseSwapUsingRecursion(head);
        head.display();
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static Node pairWiseSwap(Node head) {
        if (head == null || head.next == null)
            return head;
        Node prev = head, cur = head.next, next;
        head = cur;
        while (true) {
            next = cur.next;
            cur.next = prev;
            if (next == null || next.next == null) {
                prev.next = next;
                break;
            }
            prev.next = next.next;
            prev = next;
            cur = prev.next;
        }
        return head;
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static Node pairWiseSwapUsingRecursion(Node head) {
        if (head == null || head.next == null)
            return head;
        Node remaining = head.next.next;
        Node newHead = head.next;
        head.next.next = head;
        head.next = pairWiseSwapUsingRecursion(remaining);
        return newHead;
    }
}
