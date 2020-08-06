package com.chandu.dsa.linked.list;

public class Merge2SortedLinkedLists {
    public static void main(String[] args) {
        Node head1 = Node.createLinkedList(new int[]{5, 10, 15});
        Node head2 = Node.createLinkedList(new int[]{2, 3, 20});
        System.out.println("Linked list 1: ");
        Node.printList(head1);
        System.out.println("Linked list 2: ");
        Node.printList(head2);
        System.out.println("Merged sorted list is:");
        //Node sortedList = mergeUsingDummyNode(head1, head2);
        //Node sortedList = mergeInPlaceRecursive(head1, head2);
        Node sortedList = mergeInPlaceIterative(head1, head2);
        Node.printList(sortedList);
    }

    //Time Complexity: O(m + n)
    //Space Complexity: O(1)
    public static Node mergeUsingDummyNode(Node head1, Node head2) {
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
            if (head1.data <= head2.data) {
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

    //Time Complexity: O(Min(m+n)
    //Space Complexity: O(n)
    public static Node mergeInPlaceRecursive(Node head1, Node head2) {
        if (head1 == null)
            return head2;
        if (head2 == null)
            return head1;
        if (head1.data <= head2.data) {
            head1.next = mergeInPlaceRecursive(head1.next, head2);
            return head1;
        } else {
            head2.next = mergeInPlaceRecursive(head1, head2.next);
            return head2;
        }
    }

    //Time Complexity: O(m+n)
    //Space Complexity: O(1)
    public static Node mergeInPlaceIterative(Node head1, Node head2) {
        if(head1 == null)
            return head2;
        if(head2 == null)
            return head1;
        if(head1.data < head2.data)
            return mergeInPlaceIterativeHelper(head1, head2);
        else
            return mergeInPlaceIterativeHelper(head2, head1);
    }

    public static Node mergeInPlaceIterativeHelper(Node head1, Node head2){
        if(head1.next == null){
            head1.next = head2;
            return head1;
        }
        Node cur1 = head1, next1 = head1.next;
        Node cur2 = head2, next2;

        while (next1 != null && cur2 != null){
            if(cur2.data>=cur1.data && cur2.data<=next1.data){
                next2 = cur2.next;
                cur1.next = cur2;
                cur2.next = next1;
                cur1 = cur2;
                cur2 = next2;
            }else {
                if(next1.next != null){
                    next1 = next1.next;
                    cur1 = cur1.next;
                }else {
                    next1.next = cur2;
                    return head1;
                }
            }
        }
        return head1;
    }
}
