package com.sde.chandu.linkedlist;

public class RearrangeLinkedListOddEvenPosition {
    public static void main(String[] args) {
        Node head = Node.createLinkedList(4);
        System.out.println("Original list: ");
        head.display();
        System.out.println("Linked list after rearranging: ");
        head = rearrange(head);
        head.display();
        System.out.println();

        head = Node.createLinkedList(5);
        System.out.println("Original list: ");
        head.display();
        System.out.println("Linked list after rearranging: ");
        head = rearrangeApproach2(head);
        head.display();
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static Node rearrange(Node head) {
        if (head == null)
            return head;
        Node evenP = new Node(0, null);
        Node odd = head, even = evenP, temp = head.next;
        boolean isOdd = false;
        while (temp != null) {
            if (isOdd) {
                odd.next = temp;
                odd = odd.next;
                isOdd = false;
            } else {
                even.next = temp;
                even = even.next;
                isOdd = true;
            }
            temp = temp.next;
        }
        even.next = null;
        odd.next = evenP.next;
        return head;
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static Node rearrangeApproach2(Node head) {
        if (head == null)
            return head;
        Node odd = head, evenFirst = head.next, even = evenFirst;
        while (true) {
            if (even == null || even.next == null) {
                odd.next = evenFirst;
                break;
            }
            odd.next = even.next;
            odd = odd.next;
            if (odd.next == null) {
                even.next = null;
                odd.next = evenFirst;
                break;
            }
            even.next = odd.next;
            even = even.next;
        }
        return head;
    }
}
