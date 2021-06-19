package com.sde.chandu.linkedlist;

public class RotateALinkedList {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50, 60};
        Node head = Node.createLinkedList(arr);
        System.out.println("Original list: ");
        head.display();
        int k = 4;
        System.out.println("List after rotating by " + k + " nodes, efficient approach: ");
        head = rotateEfficient(head, k);
        head.display();
        System.out.println();

        System.out.println("Original list: ");
        head.display();
        System.out.println("List after rotating by " + k + " nodes, approach 2: ");
        head = rotateApproach2(head, k);
        head.display();
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static Node rotateEfficient(Node head, int k) {
        if (k <= 0)
            return head;
        Node cur = head;
        int count = 1;
        while (count < k && cur != null) {
            cur = cur.next;
            count++;
        }
        if (cur == null)
            return head;
        Node kNode = cur;
        while (cur.next != null)
            cur = cur.next;
        cur.next = head;
        head = kNode.next;
        kNode.next = null;
        return head;
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static Node rotateApproach2(Node head, int k) {
        if (k <= 0 || head == null)
            return head;
        Node cur = head;
        while (cur.next != null)
            cur = cur.next;
        cur.next = head;
        cur = head;
        for (int i = 1; i < k; i++)
            cur = cur.next;
        head = cur.next;
        cur.next = null;
        return head;
    }
}
