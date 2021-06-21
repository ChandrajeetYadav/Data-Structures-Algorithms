package com.sde.chandu.linkedlist;

public class Subtract2NumbersRepresentedByLinkedList {
    public static void main(String[] args) {
        /*int[] arr1 = {1, 0, 0};
        int[] arr2 = {1};*/
        int[] arr1 = {0, 2, 5, 2, 6, 9, 6, 7, 2, 4, 5, 0, 2, 1, 7, 5};
        int[] arr2 = {7, 0, 4, 7, 9, 1, 4, 5, 6, 0, 8, 9, 0, 0, 6};
        Node head1 = Node.createLinkedList(arr1);
        Node head2 = Node.createLinkedList(arr2);
        System.out.println("Original list 1: ");
        head1.display();
        System.out.println("Original list 2: ");
        head2.display();
        System.out.println("Resultant list after subtracting lists 1 and 2: ");
        Node res = subtractLinkedList(head1, head2);
        res.display();
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    private static Node subtractLinkedList(Node head1, Node head2) {
        if (head1 == null && head2 == null)
            return null;
        head1 = removeLeadingZeros(head1);
        head2 = removeLeadingZeros(head2);
        int len1 = getLength(head1);
        int len2 = getLength(head2);

        Node lNode = null, sNode = null, temp1 = head1, temp2 = head2;
        if (len1 != len2) {
            lNode = len1 >= len2 ? head1 : head2;
            sNode = len1 >= len2 ? head2 : head1;
            sNode = paddZerosToFront(sNode, Math.abs(len1 - len2));
        } else {
            while (temp1 != null && temp2 != null) {
                if (temp1.data != temp2.data) {
                    lNode = temp1.data > temp2.data ? head1 : head2;
                    sNode = temp1.data > temp2.data ? head2 : head1;
                    break;
                }
                temp1 = temp1.next;
                temp2 = temp2.next;
            }
        }
        if (lNode == null)
            return new Node(0, null);
        Helper helper = new Helper();
        subtractLinkedListHelper(lNode, sNode, helper);
        helper.head = removeLeadingZeros(helper.head);
        return helper.head;
    }

    private static void subtractLinkedListHelper(Node node1, Node node2, Helper helper) {
        if (node1 == null && !helper.borrow)
            return;
        subtractLinkedListHelper(node1.next, node2.next, helper);
        int data1 = node1.data;
        int data2 = node2.data;
        if (helper.borrow) {
            data1--;
            helper.borrow = false;
        }
        if (data1 < data2) {
            helper.borrow = true;
            data1 += 10;
        }
        int sub = data1 - data2;
        helper.head = new Node(sub, helper.head);
    }

    private static Node removeLeadingZeros(Node head) {
        while (head != null && head.next != null && head.data == 0)
            head = head.next;
        return head;
    }

    private static Node paddZerosToFront(Node sNode, int diff) {
        Node temp;
        for (int i = 0; i < diff; i++) {
            temp = new Node(0, sNode);
            sNode = temp;
        }
        return sNode;
    }

    private static int getLength(Node head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    static class Helper {
        boolean borrow;
        Node head;
    }
}
