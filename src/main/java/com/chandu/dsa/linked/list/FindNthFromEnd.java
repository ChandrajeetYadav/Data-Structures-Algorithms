package com.chandu.dsa.linked.list;

public class FindNthFromEnd {
    public static void main(String[] args) {
        Node head = Node.createDemoLinkedList();
        System.out.println("Original linked list: ");
        Node.printList(head);
        int n = 4;
        System.out.println("Iterative, Node at position " + n +" from end is: " + getNthFromEndIterative(head, n));
        System.out.println("Recursive, Node at position " + n +" from end is: " + getNthFromEndRecursive(head, n));
        System.out.println("Two Pointer, Node at position " + n +" from end is: " + getNthFromEndTwoPointer(head, n));
    }

    //Time Complexity: O(n)
    //Space Complexity: O(1)
    public static int getNthFromEndTwoPointer(Node head, int n) {
        if(head==null || n<1)
            return -1;
        int count = 0;
        Node temp = head;
        while (count++ < n){
            if (temp == null)
                return -1;
            temp = temp.next;
        }
        while (temp != null){
            temp = temp.next;
            head = head.next;
        }
        return head.data;
    }

    //Time Complexity: O(n)
    //Space Complexity: O(1)
    public static int getNthFromEndIterative(Node head, int n) {
        if(head==null || n<1)
            return -1;
        int len = length(head);
        if(n > len)
            return  - 1;
        for(int i=1; i<len-n+1; i++)
            head = head.next;
        return head.data;
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static int getNthFromEndRecursive(Node head, int n){
        Helper helper = new Helper();
        getNthFromEndRecursive(head, n, helper);
        return helper.res;
    }

    public static void getNthFromEndRecursive(Node head, int n, Helper helper){
        if(head == null)
            return;
        getNthFromEndRecursive(head.next, n, helper);
        helper.count = helper.count + 1;
        if(helper.count == n)
            helper.res = head.data;
    }

    public static int length(Node head){
        int len = 0;
        while(head != null){
            len++;
            head = head.next;
        }
        return len;
    }

    static class Helper{
        int count;
        int res = -1;
    }
}
