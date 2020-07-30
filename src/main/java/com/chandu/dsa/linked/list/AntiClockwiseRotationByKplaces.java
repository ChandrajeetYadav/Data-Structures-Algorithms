package com.chandu.dsa.linked.list;

public class AntiClockwiseRotationByKplaces {
    public static void main(String[] args) {
        Node head = Node.createDemoLinkedList();
        System.out.println("Original linked list: ");
        Node.printList(head);
        int k = 3;
        head = rotate(head, k);
        System.out.println("Linked list after rotating by " + k + " places: ");
        Node.printList(head);
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    public static Node rotate(Node head, int k){
        if(k==0 || head==null)
            return head;
        Node curr = head;
        int count = 1;
        while(count<k && curr!=null){
            curr = curr.next;
            count++;
        }
        if(curr == null)
            return head;
        Node kthNode = curr;
        while (curr.next != null)
            curr = curr.next;
        curr.next = head;
        head = kthNode.next;
        kthNode.next = null;
        return  head;
    }
}
