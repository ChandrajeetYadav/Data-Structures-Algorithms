package com.chandu.dsa.linked.list;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicatesFromUnsortedList {
    public static void main(String[] args) {
        Node head = Node.createLinkedList(new int[] {12, 11, 12, 21, 41, 43, 21});
        System.out.println("Original list: ");
        Node.printList(head);
        //head = removeDuplicatesUsingHashing(head);
        head = removeDuplicatesBrute(head);
        //head = removeDuplicatesUsingSorting(head);
        System.out.println("Linked list after removing duplicates: ");
        Node.printList(head);
    }

    //Time Complexity: O(n^2)
    //Space Complexity: O(1)
    public static Node removeDuplicatesBrute(Node head){
        Node temp1 = head, temp2;
        while (temp1 != null){
            temp2 = temp1;
            while (temp2.next != null){
                if(temp1.data == temp2.next.data)
                    temp2.next = temp2.next.next;
                else
                    temp2 = temp2.next;
            }
            temp1 = temp1.next;
        }
        return head;
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static Node removeDuplicatesUsingHashing(Node head){
        Set<Integer> set = new HashSet<>();
        Node prev = null, temp = head;

        while (temp != null){
            if(set.contains(temp.data))
                prev.next = temp.next;
            else {
                set.add(temp.data);
                prev = temp;
            }
            temp = temp.next;
        }
        return head;
    }

    //Time Complexity: O(n log n)
    //Space Complexity: O(1)
    //Note: This method doesn’t preserve the original order of elements.
    public static Node removeDuplicatesUsingSorting(Node head){
        head = mergeSort(head);
        Node temp = head;
        while (temp.next != null){
            if (temp.data == temp.next.data)
                temp.next = temp.next.next;
            else
                temp = temp.next;
        }
        return head;
    }

    public static Node mergeSort(Node head){
        if(head==null || head.next==null)
            return head;
        Node middle = getMiddle(head);
        Node nextOfMiddle = middle.next;
        middle.next = null;

        Node left = mergeSort(head);
        Node right = mergeSort(nextOfMiddle);

        return merge(left, right);
    }

    public static Node merge(Node head1, Node head2){
        if(head1 == null)
            return head2;
        if(head2 == null)
            return head1;
        Node result;
        if(head1.data < head2.data){
            result = head1;
            result.next = merge(head1.next, head2);
        }else {
            result = head2;
            result.next = merge(head1, head2.next);
        }
        return result;
    }

    public static Node getMiddle(Node head){
        Node slow=head, fast=head;
        while (fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
