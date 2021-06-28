package com.sde.chandu.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class IntersectionPointInYShapedLinkedLists {
    public static void main(String[] args) {
        int[] arr1 = {3, 6, 9};
        int[] arr2 = {10, 15, 30};
        Node head1 = Node.createLinkedList(arr1);
        Node head2 = Node.createLinkedList(arr2);
        head1.next.next.next = head2.next;

        System.out.println("Intersection point, brute approach: " + getIntersectionPointBrute(head1, head2));
        System.out.println("Intersection point, by counting nodes: " + getIntersectionPointByCountingNodes(head1, head2));
        System.out.println("Intersection point, using hashing: " + getIntersectionPointUsingHahsing(head1, head2));
    }

    // Time complexity : O(m*n)
    // Space complexity : O(1)
    private static int getIntersectionPointBrute(Node head1, Node head2) {
        if (head1 == null || head2 == null)
            return -1;
        Node temp;
        while (head1 != null) {
            temp = head2;
            while (temp != null) {
                if (head1 == temp)
                    return head1.data;
                temp = temp.next;
            }
            head1 = head1.next;
        }
        return -1;
    }

    // Time complexity : O(m+n)
    // Space complexity : O(m)
    private static int getIntersectionPointUsingHahsing(Node head1, Node head2) {
        if (head1 == null || head2 == null)
            return -1;
        Set<Node> set = new HashSet<>();
        while (head1 != null) {
            set.add(head1);
            head1 = head1.next;
        }
        while (head2 != null) {
            if (set.contains(head2))
                return head2.data;
            head2 = head2.next;
        }
        return -1;
    }

    // Time complexity : O(m+n)
    // Space complexity : O(1)
    private static int getIntersectionPointByCountingNodes(Node head1, Node head2) {
        if (head1 == null || head2 == null)
            return -1;
        int len1 = getLength(head1);
        int len2 = getLength(head2);
        if (len1 >= len2)
            return getIntersectionPointByCountingNodesUtil(head1, head2, Math.abs(len1 - len2));
        else
            return getIntersectionPointByCountingNodesUtil(head2, head1, Math.abs(len1 - len2));
    }

    private static int getIntersectionPointByCountingNodesUtil(Node head1, Node head2, int diff) {
        for (int i = 0; i < diff; i++)
            head1 = head1.next;
        while (head1 != null && head2 != null) {
            if (head1 == head2)
                return head1.data;
            head1 = head1.next;
            head2 = head2.next;
        }
        return -1;
    }

    private static int getLength(Node head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }
}
