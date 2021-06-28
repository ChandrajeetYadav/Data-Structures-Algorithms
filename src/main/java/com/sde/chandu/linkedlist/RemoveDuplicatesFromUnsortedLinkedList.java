package com.sde.chandu.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicatesFromUnsortedLinkedList {
    public static void main(String[] args) {
        int[] arr = {12, 11, 12, 21, 41, 43, 21};
        Node head = Node.createLinkedList(arr);
        System.out.println("Original list: ");
        head.display();
        System.out.println("List after removing duplicates, brute approach: ");
        head = removeDuplicateBrute(head);
        head.display();
        System.out.println();

        head = Node.createLinkedList(arr);
        System.out.println("Original list: ");
        head.display();
        System.out.println("List after removing duplicates, using sorting: ");
        head = removeDuplicateUsingSorting(head);
        head.display();
        System.out.println();

        head = Node.createLinkedList(arr);
        System.out.println("Original list: ");
        head.display();
        System.out.println("List after removing duplicates, using hashing: ");
        head = removeDuplicateUsingHashing(head);
        head.display();
        System.out.println();
    }

    // Time complexity : O(n^2)
    // Space complexity : O(1)
    private static Node removeDuplicateBrute(Node head) {
        Node dup;
        for (Node temp1 = head; temp1 != null && temp1.next != null; temp1 = temp1.next) {
            for (Node temp2 = temp1; temp2.next != null; ) {
                if (temp1.data == temp2.next.data) {
                    dup = temp2.next;
                    temp2.next = temp2.next.next;
                    dup.next = null;
                } else
                    temp2 = temp2.next;
            }
        }
        return head;
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    private static Node removeDuplicateUsingHashing(Node head) {
        Set<Integer> set = new HashSet<>();
        Node cur = head, prev = null;
        while (cur != null) {
            if (set.contains(cur.data))
                prev.next = cur.next;
            else {
                set.add(cur.data);
                prev = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    // Time complexity : O(n log n)
    // Space complexity : O(log n)
    private static Node removeDuplicateUsingSorting(Node head) {
        head = mergeSort(head);
        Node cur = head, temp;
        while (cur != null) {
            temp = cur;
            while (temp != null && cur.data == temp.data)
                temp = temp.next;
            cur.next = temp;
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
        Node dummy = new Node(0);
        Node last = dummy;
        while (true) {
            if (head1 == null) {
                last.next = head2;
                break;
            }
            if (head2 == null) {
                last.next = head1;
                break;
            }
            if (head1.data <= head2.data) {
                last.next = head1;
                head1 = head1.next;
            } else {
                last.next = head2;
                head2 = head2.next;
            }
            last = last.next;
        }
        return dummy.next;
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
