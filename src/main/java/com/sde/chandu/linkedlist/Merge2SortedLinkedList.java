package com.sde.chandu.linkedlist;

public class Merge2SortedLinkedList {
    public static void main(String[] args) {
        int[] arr1 = {5, 10, 15};
        int[] arr2 = {2, 3, 20};
        Node head1 = Node.createLinkedList(arr1);
        Node head2 = Node.createLinkedList(arr2);
        System.out.println("List 1: ");
        head1.display();
        System.out.println("List 2: ");
        head2.display();
        Node res = sortedMergeUsingDummyNode(head1, head2);
        System.out.println("Resultant list after merging list 1 and 2, using dummy node: ");
        res.display();
        System.out.println();

        head1 = Node.createLinkedList(arr1);
        head2 = Node.createLinkedList(arr2);
        res = sortedMergeUsingRecursion(head1, head2);
        System.out.println("Resultant list after merging list 1 and 2, using recursion: ");
        res.display();
    }

    // Time complexity : O(m+n)
    // Space complexity : O(1)
    private static Node sortedMergeUsingDummyNode(Node head1, Node head2) {
        Node dummy = new Node(0, null);
        Node temp = dummy;
        while (true) {
            if (head1 == null) {
                temp.next = head2;
                break;
            }
            if (head2 == null) {
                temp.next = head1;
                break;
            }
            if (head1.data <= head2.data) {
                temp.next = head1;
                head1 = head1.next;
            } else {
                temp.next = head2;
                head2 = head2.next;
            }
            temp = temp.next;
        }
        return dummy.next;
    }

    // Time complexity : O(m+n)
    // Space complexity : O(m+n)
    private static Node sortedMergeUsingRecursion(Node head1, Node head2) {
        if (head1 == null)
            return head2;
        if (head2 == null)
            return head1;
        if (head1.data <= head2.data) {
            head1.next = sortedMergeUsingRecursion(head1.next, head2);
            return head1;
        } else {
            head2.next = sortedMergeUsingRecursion(head1, head2.next);
            return head2;
        }
    }
}
