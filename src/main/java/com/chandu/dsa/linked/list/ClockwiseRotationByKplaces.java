package com.chandu.dsa.linked.list;

public class ClockwiseRotationByKplaces {
    public static void main(String[] args) {
        Node head = Node.createDemoLinkedList();
        System.out.println("Original linked list: ");
        Node.printList(head);
        int k = 1;
        head = rotate(head, k);
        System.out.println("Linked list after rotating clockwise by " + k + " places: ");
        Node.printList(head);
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    public static Node rotate(Node head, int k) {
        if(head==null)
            return null;
        int len = length(head);
        if(k > len)
            k = k % len;
        k = len - k;
        if(k==0 || k==len)
            return head;
        int count = 1;
        Node curr = head;
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
        return head;
    }

    private static int length(Node head){
        int length = 0;
        while(head != null){
            head = head.next;
            length++;
        }
        return length;
    }
}
