package com.chandu.dsa.linked.list;

public class ReverseLinkedList {
    public static void main(String[] args) {
        Node head = Node.createDemoLinkedList();
        //Node head = Node.createLinkedList(new int[]{1, 2, 3});
        System.out.println("Original linked list: ");
        Node.printList(head);
        head = reverse(head);
        //head = reverseUsingRecursion(head);
        //head = reverseUsingTailRecursion(head);
        System.out.println("Reversed linked list:");
        Node.printList(head);
    }

    //Time Complexity: O(n)
    //Space Complexity: O(1)
    public static Node reverse(Node head){
        Node next, curr, prev;
        curr = head;
        prev = null;
        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static Node reverseUsingTailRecursion(Node head){
        return reverseUsingTailRecursion(head, null);
    }

    public static Node reverseUsingTailRecursion(Node curr, Node prev){
        if(curr == null)
            return prev;
        Node next = curr.next;
        curr.next = prev;
        prev = curr;
        return reverseUsingTailRecursion(next, curr);
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static Node reverseUsingRecursion(Node head){
        if(head == null || head.next==null)
            return head;
        Node rest = reverseUsingRecursion(head.next);
        head.next.next = head;
        head.next = null;

        return rest;
    }
}
