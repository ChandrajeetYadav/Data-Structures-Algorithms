package com.chandu.dsa.linked.list;

public class UnStablePartitionListAroundX {
    public static void main(String[] args) {
        Node head = Node.createLinkedList(new int[] {3, 5, 10, 2, 8, 2, 1});
        System.out.println("Original linked list: ");
        Node.printList(head);
        System.out.println("List after partitioning: ");
        int x = 5;
        head = partition(head, 5);
        Node.printList(head);
    }

    public static Node partition(Node head, int x){
        if(head == null)
            return null;
        Node tail=head, curr=head, next;
        while (curr != null){
            next = curr.next;
            if(curr.data < x){
                curr.next = head;
                head = curr;
            }else {
                tail.next = curr;
                tail = curr;
            }
            curr = next;
        }
        tail.next = null;
        return head;
    }
}
