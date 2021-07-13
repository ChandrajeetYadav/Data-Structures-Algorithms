package com.sde.chandu.linkedlist;

public class ReverseAlternateNodesAndAppendAtEnd {
    public static void main(String[] args) {
        Node head = Node.createLinkedList(6);
        System.out.println("Original linked list: ");
        head.display();
        System.out.println("Linked list after reversing alternate nodes and appending at end: ");
        reverseAlternateNodes(head);
        head.display();
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static void reverseAlternateNodes(Node head) {
        if (head == null || head.next == null || head.next.next == null)
            return;
        Node even = head.next, odd = head, temp;
        odd.next = odd.next.next;
        odd = odd.next;
        even.next = null;
        while (odd.next != null) {
            temp = odd.next.next;
            odd.next.next = even;
            even = odd.next;
            odd.next = temp;
            if (temp != null)
                odd = temp;
        }
        odd.next = even;
    }
}
