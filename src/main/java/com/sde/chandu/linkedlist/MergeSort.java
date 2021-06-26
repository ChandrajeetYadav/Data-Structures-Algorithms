package com.sde.chandu.linkedlist;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {40, 20, 60, 10, 50, 30};
        Node head = Node.createLinkedList(arr);
        System.out.println("Original list: ");
        head.display();
        head = mergeSort(head);
        System.out.println("Sorted list: ");
        head.display();
    }

    // Time complexity : O(n log n)
    // Space complexity : O(log n)
    private static Node mergeSort(Node head) {
        if (head == null || head.next == null)
            return head;
        Node middle = getMiddle(head);
        Node nextOfMiddle = middle.next;
        middle.next = null;
        Node left = mergeSort(head);
        Node right = mergeSort(nextOfMiddle);
        return merge(left, right);
    }

    private static Node merge(Node head1, Node head2) {
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

    private static Node getMiddle(Node head) {
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
