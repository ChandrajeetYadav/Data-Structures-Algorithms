package com.sde.chandu.linkedlist;

public class SortListOf012 {
    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 2, 1, 1, 2, 1, 2};
        Node head = Node.createLinkedList(arr);
        System.out.println("Original list: ");
        head.display();
        System.out.println("Linked list after sorting using counting approach: ");
        head = sortUsingCounting(head);
        head.display();
        System.out.println();

        int[] arr2 = {1, 2, 2, 1, 2, 0, 2, 2};
        head = Node.createLinkedList(arr2);
        System.out.println("Original list: ");
        head.display();
        System.out.println("Linked list after sorting by changing links: ");
        head = sortByChangingLinks(head);
        head.display();
        System.out.println();
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static Node sortUsingCounting(Node head) {
        if (head == null || head.next == null)
            return head;
        int[] count = new int[3];
        Node temp = head;
        while (temp != null) {
            count[temp.data]++;
            temp = temp.next;
        }
        temp = head;
        int i = 0;
        while (temp != null) {
            if (count[i] == 0)
                i++;
            else {
                temp.data = i;
                temp = temp.next;
                count[i]--;
            }
        }
        return head;
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static Node sortByChangingLinks(Node head) {
        if (head == null || head.next == null)
            return head;
        Node zeroD = new Node(0, null);
        Node oneD = new Node(0, null);
        Node twoD = new Node(0, null);
        Node zero = zeroD, one = oneD, two = twoD, temp = head;

        while (temp != null) {
            if (temp.data == 0) {
                zero.next = temp;
                zero = zero.next;
            } else if (temp.data == 1) {
                one.next = temp;
                one = one.next;
            } else {
                two.next = temp;
                two = two.next;
            }
            temp = temp.next;
        }
        zero.next = oneD.next != null ? oneD.next : twoD.next;
        one.next = twoD.next;
        two.next = null;
        head = zeroD.next;
        return head;
    }
}
