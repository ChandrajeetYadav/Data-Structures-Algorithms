package com.chandu.dsa.linked.list;

public class RemoveDuplicatesFromSortedList {
    public static void main(String[] args) {
        Node head = Node.createLinkedList(new int[] {11, 11, 11, 21, 43, 43, 60});
        //Node head = Node.createDemoLinkedList();
        System.out.println("Original list: ");
        Node.printList(head);
        System.out.println("List after removing duplicates: ");
        //head = removeDuplicates(head);
        head = removeDuplicatesRecursive(head);
        Node.printList(head);
    }

    //Time Complexity: O(n)
    //Space Complexity: O(1)
    public static Node removeDuplicates(Node head){
        if(head == null)
            return  null;
        Node temp = head;
        while (temp.next != null){
            if(temp.data == temp.next.data)
                temp.next = temp.next.next;
            else
                temp = temp.next;
        }
        return head;
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static Node removeDuplicatesRecursive(Node head){
        if(head == null)
            return null;
        if(head.next != null){
            if(head.data == head.next.data){
                head.next = head.next.next;
                removeDuplicates(head);
            }
            else
                removeDuplicatesRecursive(head.next);
        }
        return head;
    }
}
