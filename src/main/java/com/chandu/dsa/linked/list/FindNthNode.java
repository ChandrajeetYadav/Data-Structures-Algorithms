package com.chandu.dsa.linked.list;

public class FindNthNode {
    public static void main(String[] args) {
        Node head = Node.createDemoLinkedList();
        System.out.println("Original linked list: ");
        Node.printList(head);
        int n = 3;
        System.out.println("Iterative, Node at position " + n + " is : " + getNthNodeIterative(head, n));
        System.out.println("Recursive, Node at position " + n + " is : " + getNthNodeRecursive(head, n));
    }

    //Time Complexity: O(n)
    //Space Complexity: O(1)
    public static int getNthNodeIterative(Node head, int n){
        if(head == null || n<1)
            return -1;
        int count = 1;
        while(head!=null){
            if(count == n)
                return head.data;
            head = head.next;
            count++;
        }
        return -1;
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static int getNthNodeRecursive(Node head, int n){
        if(n < 1)
            return -1;
        return getNthNodeRecursive(head, n, 1);
    }

    public static int getNthNodeRecursive(Node head, int n, int pos){
        if(head == null)
            return -1;
        if(pos == n)
            return head.data;
        return getNthNodeRecursive(head.next, n, pos+1);
    }
}
