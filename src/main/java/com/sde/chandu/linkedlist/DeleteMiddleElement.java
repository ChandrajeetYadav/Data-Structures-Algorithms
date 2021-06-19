package com.sde.chandu.linkedlist;

public class DeleteMiddleElement {
    public static void main(String[] args) {
        int num1 = 5;
        Node head1 = Node.createLinkedList(num1);
        System.out.println("List 1: ");
        head1.display();

        System.out.println("List 1 after deleting middle element, counting approach:");
        head1 = deleteMiddleElementUsingCounting(head1);
        head1.display();

        System.out.println("List 1 after deleting middle element, 2 pointer approach:");
        head1 = deleteMiddleElementUsing2Pointer(head1);
        head1.display();

        System.out.println("List 1 after deleting middle element, odd even approach:");
        head1 = deleteMiddleElementUsingOddEvenApproach(head1);
        head1.display();

        System.out.println();

        int num2 = 6;
        Node head2 = Node.createLinkedList(num2);
        System.out.println("List 2: ");
        head2.display();

        System.out.println("List 2 after deleting middle element, counting approach:");
        head2 = deleteMiddleElementUsingCounting(head2);
        head2.display();

        System.out.println("List 2 after deleting middle element, 2 pointer approach:");
        head2 = deleteMiddleElementUsing2Pointer(head2);
        head2.display();

        System.out.println("List 2 after deleting middle element, odd even approach:");
        head2 = deleteMiddleElementUsingOddEvenApproach(head2);
        head2.display();
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static Node deleteMiddleElementUsingCounting(Node head) {
        if (head == null || head.next == null)
            return null;
        int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        temp = head;
        for (int i = 1; i < count / 2; i++)
            temp = temp.next;
        Node toBeDeleted = temp.next;
        temp.next = temp.next.next;
        toBeDeleted.next = null;
        return head;
    }

    // Time complexity : O(n/2) = O(n)
    // Space complexity : O(1)
    private static Node deleteMiddleElementUsing2Pointer(Node head) {
        if (head == null || head.next == null)
            return null;
        Node slow = head, fast = head, prev = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        prev.next = slow.next;
        slow.next = null;
        return head;
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static Node deleteMiddleElementUsingOddEvenApproach(Node head) {
        if (head == null || head.next == null)
            return null;
        int counter = 0;
        Node mid = head, temp = head, prev = null;
        while (temp != null) {
            if (counter++ % 2 == 1) {
                prev = mid;
                mid = mid.next;
            }
            temp = temp.next;
        }
        prev.next = mid.next;
        mid.next = null;
        return head;
    }
}
