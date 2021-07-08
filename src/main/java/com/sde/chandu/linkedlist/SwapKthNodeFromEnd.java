package com.sde.chandu.linkedlist;

public class SwapKthNodeFromEnd {
    public static void main(String[] args) {
        Node head = Node.createLinkedList(5);
        System.out.println("Original list: ");
        head.display();
        int k = 3;
        System.out.println("List after swapping " + k + "th node: ");
        head = swapKth(head, k);
        head.display();

        System.out.println("Original list: ");
        head.display();
        k = 2;
        System.out.println("List after swapping " + k + "th node: ");
        head = swapKth(head, k);
        head.display();
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static Node swapKth(Node head, int k) {
        int len = length(head);
        if ((len < k) || (2 * k - 1 == len))
            return head;
        Node start = head, startPrev = null, end = head, endPrev = null;
        for (int i = 1; i < k; i++) {
            startPrev = start;
            start = start.next;

        }
        for (int i = 1; i < len - k + 1; i++) {
            endPrev = end;
            end = end.next;
        }
        if (startPrev != null)
            startPrev.next = end;
        if (endPrev != null)
            endPrev.next = start;
        Node temp = start.next;
        start.next = end.next;
        end.next = temp;
        if (k == 1)
            head = end;
        if (k == len)
            head = start;
        return head;
    }

    private static int length(Node head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }
}
