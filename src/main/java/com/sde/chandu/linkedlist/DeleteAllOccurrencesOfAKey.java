package com.sde.chandu.linkedlist;

public class DeleteAllOccurrencesOfAKey {
    public static void main(String[] args) {
        int[] arr = {2, 2, 1, 8, 2, 3, 2, 7, 2};
        Node head = Node.createLinkedList(arr);
        System.out.println("Original list: ");
        head.display();
        int key = 2;
        System.out.println("List after deleting all occurrences of " + key + ":");
        head = deleteKey(head, key);
        head.display();
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static Node deleteKey(Node head, int key) {
        Node temp, prev = null;
        while (head != null && head.data == key)
            head = head.next;
        temp = head;
        while (temp != null) {
            while (temp != null && temp.data != key) {
                prev = temp;
                temp = temp.next;
            }
            if (temp == null)
                break;
            prev.next = temp.next;
            temp = prev.next;
        }
        return head;
    }
}
