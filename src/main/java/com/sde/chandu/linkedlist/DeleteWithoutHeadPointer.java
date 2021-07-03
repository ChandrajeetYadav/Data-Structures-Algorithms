package com.sde.chandu.linkedlist;

public class DeleteWithoutHeadPointer {
    public static void main(String[] args) {
        Node head = Node.createLinkedList(5);
        Node toBeDeleted = head.next.next;
        System.out.println("Original linked list: ");
        head.display();
        deleteWithoutHead(toBeDeleted);
        System.out.println("List after deleting 3rd element");
        head.display();
    }

    // Time complexity : O(1)
    // Space complexity : O(1)
    private static void deleteWithoutHead(Node node) {
        if (node == null || node.next == null)
            return;
        Node toBeDeleted = node.next;
        node.data = node.next.data;
        node.next = node.next.next;
        toBeDeleted.next = null;
    }
}
