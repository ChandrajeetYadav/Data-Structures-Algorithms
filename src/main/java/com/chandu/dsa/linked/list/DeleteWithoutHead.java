package com.chandu.dsa.linked.list;

public class DeleteWithoutHead {
    public static void main(String[] args) {
        Node head = Node.createDemoLinkedList();
        System.out.println("Original linked list: ");
        Node.printList(head);
        Node thirdNode = head.next.next;
        delete(thirdNode);
        System.out.println("Linked list after deleting third node: ");
        Node.printList(head);

        head = Node.createDemoLinkedList();
        Node fourthNode = head.next.next.next;
        delete(fourthNode);
        System.out.println("Linked list after deleting fourth node: ");
        Node.printList(head);

        head = Node.createDemoLinkedList();
        Node lastNode = head.next.next.next.next;
        delete(lastNode);
    }

    //Time Complexity: O(1)
    //Space Complexity: O(1)
    public static void delete(Node node){
        if(node == null || node.next==null){
            System.out.println("Can't perform this operation as node is last node or null");
            return;
        }
        Node temp = node.next;
        node.data = temp.data;
        node.next = temp.next;
        temp.next = null;
    }
}
