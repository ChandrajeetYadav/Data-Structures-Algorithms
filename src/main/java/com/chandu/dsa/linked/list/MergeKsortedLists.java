package com.chandu.dsa.linked.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKsortedLists {
    public static void main(String[] args) {
        Node head1 = Node.createLinkedList(new int[]{1, 3, 5, 7});
        Node head2 = Node.createLinkedList(new int[]{2, 4, 6, 8});
        Node head3 = Node.createLinkedList(new int[]{0, 9, 10, 11});
        System.out.println("List 1:");
        Node.printList(head1);
        System.out.println("List 2:");
        Node.printList(head2);
        System.out.println("List 3:");
        Node.printList(head3);
        Node[] arr = {head1, head2, head3};
        System.out.println("Sorted List after merging list 1, 2 and 3: ");
        //Node result = mergeKsortedListsDivideAndConquer(arr);
        //Node result = mergeKsortedListsOneByOne(arr);
        //Node result = mergeKsortedListsBrute(arr);
        Node result = mergeKsortedListsUsingPriorityQueue(arr);
        Node.printList(result);
    }

    //Time Complexity: O(N log k), where N = kn and n=number of nodes
    //Space Complexity: O(k)
    public static Node mergeKsortedListsUsingPriorityQueue(Node[] arr) {
        if(arr==null || arr.length==0)
            return null;
        Node head=null, last=null;
        PriorityQueue<Node> pq = new PriorityQueue<>((Node a, Node b) -> a.data - b.data);
        for(int i=0; i<arr.length; i++){
            if(arr[i] != null)
                pq.add(arr[i]);
        }
        if(!pq.isEmpty()){
            head = pq.poll();
            last = head;
            if(head.next != null)
                pq.add(head.next);
        }
        Node temp;
        while (!pq.isEmpty()){
            temp = pq.poll();
            if (temp.next != null)
                pq.add(temp.next);
            last.next = temp;
            last = last.next;
        }
        return head;
    }

    //Time Complexity: O(N log k), where N = kn and n=number of nodes
    //Space Complexity: O(1)
    public static Node mergeKsortedListsDivideAndConquer(Node[] arr) {
        if (arr == null || arr.length == 0)
            return null;
        int i, j;
        int last = arr.length - 1;
        while (last != 0) {
            i = 0;
            j = last;
            while (i < j) {
                arr[i] = sortedMerge(arr[i], arr[j]);
                i++;
                j--;
                if (i >= j)
                    last = j;
            }
        }
        return arr[0];
    }

    private static Node sortedMerge(Node head1, Node head2) {
        Node dummy = new Node(0);
        Node tail = dummy;

        while (true) {
            if (head1 == null) {
                tail.next = head2;
                break;
            }
            if (head2 == null) {
                tail.next = head1;
                break;
            }
            if (head1.data < head2.data) {
                tail.next = head1;
                head1 = head1.next;
            } else {
                tail.next = head2;
                head2 = head2.next;
            }
            tail = tail.next;
        }
        tail = dummy.next;
        dummy.next = null;
        return tail;
    }

    //Time Complexity: O(kN), where N = kn and n=number of nodes
    //Space Complexity: O(1)
    public static Node mergeKsortedListsOneByOne(Node[] arr) {
        if (arr == null || arr.length == 0)
            return null;
        for (int i = 1; i < arr.length; i++) {
            arr[0] = sortedMerge(arr[0], arr[i]);
        }
        return arr[0];
    }

    //Time Complexity: O(N log N), where N = kn and n=number of nodes
    //Space Complexity: O(N)
    public static Node mergeKsortedListsBrute(Node[] arr){
        if (arr == null || arr.length == 0)
            return null;
        Node temp;
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<arr.length; i++){
            temp = arr[i];
            while(temp != null){
                list.add(temp.data);
                temp = temp.next;
            }
        }
        Collections.sort(list);
        Node result = null;
        if(!list.isEmpty())
            result = new Node(list.get(0));
        temp = result;
        for(int i=1; i<list.size(); i++){
            temp.next = new Node(list.get(i));
            temp = temp.next;
        }
        return result;
    }
}