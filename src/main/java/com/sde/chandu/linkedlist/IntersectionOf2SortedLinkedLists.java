package com.sde.chandu.linkedlist;

public class IntersectionOf2SortedLinkedLists {
    public static void main(String[] args) {
        int[] arr1 = {2, 3, 4, 6};
        int[] arr2 = {2, 4, 6, 8};
        Node head1 = Node.createLinkedList(arr1);
        Node head2 = Node.createLinkedList(arr2);
        System.out.println("List 1: ");
        head1.display();
        System.out.println("List 2: ");
        head2.display();
        System.out.println("Intersection of list 1 and 2, iterative solution: ");
        Node res = sortedIntersect(head1, head2);
        res.display();
        System.out.println();

        System.out.println("Intersection of list 1 and 2, recursive solution: ");
        res = sortedIntersectRecursrive(head1, head2);
        res.display();
    }

    // Time complexity : O(m+n)
    // Space complexity : O(min(m, n))
    private static Node sortedIntersect(Node head1, Node head2) {
        Node dummy = new Node(0);
        Node temp = dummy;
        while (head1 != null && head2 != null) {
            if (head1.data == head2.data) {
                temp.next = new Node(head1.data);
                temp = temp.next;
                head1 = head1.next;
                head2 = head2.next;
            } else if (head1.data < head2.data)
                head1 = head1.next;
            else
                head2 = head2.next;
        }
        return dummy.next;
    }

    // Time complexity : O(m+n)
    // Space complexity : O(m+n))
    private static Node sortedIntersectRecursrive(Node head1, Node head2) {
        if (head1 == null || head2 == null)
            return null;
        if (head1.data < head2.data)
            return sortedIntersectRecursrive(head1.next, head2);
        if (head1.data > head2.data)
            return sortedIntersectRecursrive(head1, head2.next);
        Node res = new Node(head1.data);
        res.next = sortedIntersectRecursrive(head1.next, head2.next);
        return res;
    }
}
