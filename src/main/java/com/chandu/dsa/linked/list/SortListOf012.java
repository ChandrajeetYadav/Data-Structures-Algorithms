package com.chandu.dsa.linked.list;

import java.util.Arrays;

public class SortListOf012 {
    public static void main(String[] args) {
        Node head = Node.createLinkedList(new int[]{1, 2, 0, 1, 0, 0, 2, 2, 1, 1, 0});
        System.out.println("Original linked list: ");
        Node.printList(head);
        //sortBrute2(head);
        head = sortWithoutCounting(head);
        System.out.println("Linked List after sorting: ");
        Node.printList(head);
    }

    //Time Complexity : O(n)
    //Space Complexity : O(1)
    public static Node sortWithoutCounting(Node head){
        if(head==null || head.next==null)
            return head;
        Node zeroD = new Node(0);
        Node oneD = new Node(0);
        Node twoD = new Node(0);
        Node zero = zeroD, one = oneD, two = twoD;
        Node temp = head;
        while (temp != null){
            if (temp.data == 0){
                zero.next = temp;
                zero = zero.next;
            }else if (temp.data == 1){
                one.next = temp;
                one = one.next;
            }else {
                two.next = temp;
                two = two.next;
            }
            temp = temp.next;
        }
        zero.next = oneD.next != null ? oneD.next : twoD.next;
        one.next = twoD.next;
        two.next = null;

        head = zeroD.next;
        return head;
    }

    //Time Complexity : O(n)
    //Space Complexity : O(1)
    public static void sortBrute2(Node head){
        if(head==null || head.next==null)
            return;
        int[] arr = {0, 0, 0};
        Node temp = head;
        while(temp != null){
            arr[temp.data]++;
            temp = temp.next;
        }
        temp = head;
        int i=0;
        while(temp != null){
            if(arr[i] == 0)
                i++;
            else{
                temp.data = i;
                arr[i]--;
                temp = temp.next;
            }
        }
    }

    //Time Complexity : O(n)
    //Space Complexity : O(1)
    public static void sortBrute(Node head){
        if(head == null || head.next==null)
            return;
        int zero, one, two;
        zero = one = two = 0;
        Node temp = head;
        while(temp != null){
            if(temp.data == 0)
                zero++;
            else if(temp.data == 1)
                one++;
            else
                two++;
            temp = temp.next;
        }
        temp = head;
        for(int i=1; i<=zero; i++, temp = temp.next)
            temp.data = 0;
        for(int i=1; i<=one; i++, temp = temp.next)
            temp.data = 1;
        for(int i=1; i<=two; i++, temp = temp.next)
            temp.data = 2;
    }
}
