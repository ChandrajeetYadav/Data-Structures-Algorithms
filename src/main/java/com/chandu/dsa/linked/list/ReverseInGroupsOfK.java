package com.chandu.dsa.linked.list;

import java.util.Stack;

public class ReverseInGroupsOfK {
    public static void main(String[] args) {
        Node head = Node.createLinkedList(new int[]{1,2,3,4,5,6});
        System.out.println("Original linked list: ");
        Node.printList(head);
        int k = 2;
        //head = reverseInGroupsUsingStack(head, k);
        head = reverseInGroupsIterative(head, k);
        //head = reverseInGroupsRecursive(head, k);
        System.out.println("Linked list after reversing in group of " + k + " nodes:");
        Node.printList(head);
    }

    //Time Complexity: O(n)
    //Space Complexity: O(k)
    public static Node reverseInGroupsUsingStack(Node head, int k){
        if(head == null)
            return null;
        Stack<Node> stack = new Stack<>();
        Node curr = head;
        Node prev = null;
        int count;
        while (curr != null){
            count = 0;
            while (count<k && curr!=null){
                stack.push(curr);
                curr = curr.next;
                count++;
            }
            while (!stack.isEmpty()){
                if(prev == null){
                    prev = stack.pop();
                    head = prev;
                }else {
                    prev.next = stack.pop();
                    prev = prev.next;
                }
            }
        }
        prev.next = null;
        return head;
    }

    //Time Complexity: O(n)
    //Space Complexity: O(1)
    public static Node reverseInGroupsRecursive(Node head, int k){
        if(head == null)
            return null;
        Node curr = head;
        Node prev=null, next=null;
        int count = 0;
        while(curr!=null && count<k){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            count++;
        }
        if(next != null)
            head.next = reverseInGroupsRecursive(next, k);
        return prev;
    }

    //Time Complexity: O(n)
    //Space Complexity: O(1)
    public static Node reverseInGroupsIterative(Node head, int k){
        if(head == null)
            return null;
        Node curr = head;
        Node next;
        Node join;
        Node prev;
        Node tail = null;
        Node newHead = null;
        int count;

        while(curr != null){
            count = 0;
            join = curr;
            prev = null;
            while(curr!=null && count<k){
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
                count++;
            }
            if(newHead == null)
                newHead = prev;
            if(tail != null)
                tail.next = prev;
            tail = join;
        }
        return newHead;
    }
}
