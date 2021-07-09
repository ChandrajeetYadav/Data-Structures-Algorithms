package com.sde.chandu.linkedlist;

public class DeleteNodeAtGivenPositionInDoublyLinkedList {
    public static void main(String[] args) {
        int[] arr = {10, 8, 4, 2, 5};
        DoublyLinkedList head = DoublyLinkedList.createList(arr);
        System.out.println("Original list: ");
        head.display();
        int pos = 2;
        System.out.println("List after deleting node at position: " + pos);
        head = deleteNodeAtGivenPosition(head, pos);
        head.display();
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static DoublyLinkedList deleteNodeAtGivenPosition(DoublyLinkedList head, int pos) {
        if (head == null || pos <= 0)
            return head;
        DoublyLinkedList temp = head;
        for (int i = 1; temp != null && i < pos; i++)
            temp = temp.next;
        if (temp == null)
            return head;
        return deleteNode(head, temp);
    }

    private static DoublyLinkedList deleteNode(DoublyLinkedList head, DoublyLinkedList del) {
        if (head == null || del == null)
            return head;
        if (head == del)
            head = head.next;
        if (del.next != null)
            del.next.prev = del.prev;
        if (del.prev != null)
            del.prev.next = del.next;
        del.next = null;
        del.prev = null;
        return head;
    }
}
