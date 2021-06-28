package com.sde.chandu.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class UnionOf2LinkedLists {
    public static void main(String[] args) {
        /*int[] arr1 = {20, 4, 15, 10};
        int[] arr2 = {10, 2, 4, 8};*/

        int[] arr1 = {9, 6, 4, 2, 3, 8};
        int[] arr2 = {1, 2, 8, 6, 2};
        Node head1 = Node.createLinkedList(arr1);
        Node head2 = Node.createLinkedList(arr2);
        System.out.println("List 1: ");
        head1.display();
        System.out.println("List 2: ");
        head2.display();

        System.out.println("Union of list 1 and 2, brute approach: ");
        Node res = getUnionBrute(head1, head2);
        res.display();
        System.out.println();

        System.out.println("Union of list 1 and 2, using hashing: ");
        res = getUnionUsingHashing(head1, head2);
        res.display();
        System.out.println();

        System.out.println("Union of list 1 and 2, using sorting: ");
        res = getUnionUsingMergeSort(head1, head2);
        res.display();
        System.out.println();
    }

    // Time complexity : O(m*n)
    // Space complexity : O(1)
    private static Node getUnionBrute(Node head1, Node head2) {
        if (head1 == null)
            return head2;
        if (head2 == null)
            return head1;
        Node res = new Node(head1.data);
        Node last = res, temp1 = head1;
        temp1 = temp1.next;
        while (temp1 != null) {
            last = addNode(last, temp1.data);
            temp1 = temp1.next;
        }
        while (head2 != null) {
            if (!isPresent(head1, head2.data))
                last = addNode(last, head2.data);
            head2 = head2.next;
        }
        return res;
    }

    private static Node addNode(Node last, int data) {
        if (last.data != data) {
            last.next = new Node(data);
            last = last.next;
        }
        return last;
    }

    private static boolean isPresent(Node head, int data) {
        while (head != null) {
            if (head.data == data)
                return true;
            head = head.next;
        }
        return false;
    }

    // Time complexity : O(m log m + n log n)
    // Space complexity : O(1)
    private static Node getUnionUsingMergeSort(Node head1, Node head2) {
        if (head1 == null)
            return head2;
        if (head2 == null)
            return head1;
        head1 = mergeSort(head1);
        head2 = mergeSort(head2);
        Node dummy = new Node(0);
        Node last = dummy;
        while (head1 != null && head2 != null) {
            if (head1.data < head2.data) {
                last = addNode(last, head1.data);
                head1 = head1.next;
            } else if (head1.data > head2.data) {
                last = addNode(last, head2.data);
                head2 = head2.next;
            } else {
                last = addNode(last, head1.data);
                head1 = head1.next;
                head2 = head2.next;
            }
        }
        while (head1 != null) {
            last = addNode(last, head1.data);
            head1 = head1.next;
        }
        while (head2 != null) {
            last = addNode(last, head2.data);
            head2 = head2.next;
        }
        return dummy.next;
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

    // Time complexity : O(m + n)
    // Space complexity : O(m + n)
    private static Node getUnionUsingHashing(Node head1, Node head2) {
        if (head1 == null)
            return head2;
        if (head2 == null)
            return head1;
        Set<Integer> set = new HashSet<>();
        while (head1 != null) {
            set.add(head1.data);
            head1 = head1.next;
        }
        while (head2 != null) {
            set.add(head2.data);
            head2 = head2.next;
        }
        Node res = new Node(0);
        Node last = res;
        for (int n : set)
            last = addNode(last, n);
        return res.next;
    }
}
