package com.sde.chandu.linkedlist;

public class FindMiddleElement {
    public static void main(String[] args) {
        int num1 = 5;
        Node head1 = Node.createLinkedList(num1);
        System.out.println("List 1: ");
        head1.display();

        int num2 = 6;
        Node head2 = Node.createLinkedList(num2);
        System.out.println("List 2: ");
        head2.display();

        System.out.println("Middle element in list 1, counting approach : " + getMiddleElementUsingCounting(head1));
        System.out.println("Middle element in list 2, counting approach : " + getMiddleElementUsingCounting(head2));
        System.out.println();

        System.out.println("Middle element in list 1, 2 pointer approach : " + getMiddleElementUsing2Pointer(head1));
        System.out.println("Middle element in list 2, 2 pointer approach : " + getMiddleElementUsing2Pointer(head2));
        System.out.println();

        System.out.println("Middle element in list 1, odd even approach : " + getMiddleElementUsingOddEvenApproach(head1));
        System.out.println("Middle element in list 2, odd even approach : " + getMiddleElementUsingOddEvenApproach(head2));
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static int getMiddleElementUsingCounting(Node head) {
        if (head == null)
            return -1;
        int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        temp = head;
        for (int i = 1; i <= count / 2; i++)
            temp = temp.next;
        return temp.data;
    }

    // Time complexity : O(n/2) = O(n)
    // Space complexity : O(1)
    private static int getMiddleElementUsing2Pointer(Node head) {
        if (head == null)
            return -1;
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow.data;
    }

    // Time complexity : O(n/2) = O(n)
    // Space complexity : O(1)
    private static int getMiddleElementUsingOddEvenApproach(Node head) {
        if (head == null)
            return -1;
        Node mid = head;
        int counter = 0;
        while (head != null) {
            if (counter % 2 == 1)
                mid = mid.next;
            head = head.next;
            counter++;
        }
        return mid.data;
    }
}
