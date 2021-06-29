package com.sde.chandu.linkedlist;

public class LinkedListInZigZagFashion {
    public static void main(String[] args) {
        //int[] arr = {11, 15, 20, 5, 10};
        int[] arr = {2, 95, 30, 11, 48};
        Node head = Node.createLinkedList(arr);
        System.out.println("Original list: ");
        head.display();
        System.out.println();

        head = zigZagLinkedListUsingSorting(head);
        System.out.println("Zig zag linked list, using sorting: ");
        head.display();
        System.out.println();

        head = Node.createLinkedList(arr);
        System.out.println("Original list: ");
        head.display();
        System.out.println();

        head = zigZagLinkedListEfficientChangingLinks(head);
        System.out.println("Zig zag linked list, by changing links: ");
        head.display();
        System.out.println();

        head = Node.createLinkedList(arr);
        System.out.println("Original list: ");
        head.display();
        System.out.println();

        head = zigZagLinkedListEfficientChangingData(head);
        System.out.println("Zig zag linked list, by changing data: ");
        head.display();
        System.out.println();
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static Node zigZagLinkedListEfficientChangingLinks(Node head) {
        if (head == null || head.next == null)
            return head;
        boolean nextGreater = true;
        Node cur = head, prev = null;
        while (cur != null && cur.next != null) {
            if (nextGreater) {
                if (cur.data > cur.next.data)
                    cur = swap(prev, cur, cur.next);
            } else {
                if (cur.data < cur.next.data)
                    cur = swap(prev, cur, cur.next);
            }
            if (prev == null)
                head = cur;
            prev = cur;
            cur = cur.next;
            nextGreater = !nextGreater;
        }
        return head;
    }

    private static Node swap(Node prev, Node cur, Node next) {
        cur.next = next.next;
        next.next = cur;
        if (prev != null)
            prev.next = next;
        return next;
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static Node zigZagLinkedListEfficientChangingData(Node head) {
        if (head == null || head.next == null)
            return head;
        Node cur = head;
        boolean nextGreater = true;
        while (cur != null && cur.next != null) {
            if (nextGreater) {
                if (cur.data > cur.next.data)
                    swapData(cur, cur.next);
            } else {
                if (cur.data < cur.next.data)
                    swapData(cur, cur.next);
            }
            cur = cur.next;
            nextGreater = !nextGreater;
        }
        return head;
    }

    private static void swapData(Node a, Node b) {
        int temp = a.data;
        a.data = b.data;
        b.data = temp;
    }

    // Time complexity : O(n log n)
    // Space complexity : O(log n)
    private static Node zigZagLinkedListUsingSorting(Node head) {
        if (head == null || head.next == null)
            return head;
        head = mergeSort(head);
        head.display();
        Node prev = head, cur = head.next, next;
        while (cur != null && cur.next != null) {
            next = cur.next;
            cur.next = cur.next.next;
            prev.next = next;
            next.next = cur;
            prev = cur;
            cur = cur.next;
        }
        return head;
    }

    private static Node mergeSort(Node head) {
        if (head == null || head.next == null)
            return head;
        Node mid = getMiddle(head);
        Node nextOfMid = mid.next;
        mid.next = null;
        Node left = mergeSort(head);
        Node right = mergeSort(nextOfMid);
        return merge(left, right);
    }

    private static Node merge(Node head1, Node head2) {
        if (head1 == null)
            return head2;
        if (head2 == null)
            return head1;
        if (head1.data <= head2.data) {
            head1.next = merge(head1.next, head2);
            return head1;
        } else {
            head2.next = merge(head1, head2.next);
            return head2;
        }
    }

    private static Node getMiddle(Node head) {
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
