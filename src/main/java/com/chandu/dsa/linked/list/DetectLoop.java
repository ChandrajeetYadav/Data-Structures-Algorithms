package com.chandu.dsa.linked.list;

import java.util.HashSet;
import java.util.Set;

public class DetectLoop {
    public static void main(String[] args) {
        Node head1 = Node.createDemoLinkedList();
        Node head2 = Node.createLinkedList(new int[] {1, 2, 3, 4, 5, 6});
        head1.next.next.next.next = head1.next;
        //System.out.println("Is loop present in List 1: " + detectLoopUsingHashing(head1));
        //System.out.println("Is loop present in List 2: " + detectLoopUsingHashing(head2));
        //System.out.println("Is loop present in List 1: " + detectLoopByMarkingDummyNode(head1));
        //System.out.println("Is loop present in List 2: " + detectLoopByMarkingDummyNode(head2));
        System.out.println("Is loop present in List 1: " + detectLoopUsing2Pointers(head1));
        System.out.println("Is loop present in List 2: " + detectLoopUsing2Pointers(head2));
    }

    //Time Complexity: O(n)
    //Space Complexity: O(1)
    private static boolean detectLoopUsing2Pointers(Node head){
        Node slow = head, fast = head;
        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow)
                return true;
        }
        return false;
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    private static boolean detectLoopUsingHashing(Node head){
        Set<Node> set = new HashSet<>();
        while(head != null){
            if(set.contains(head))
                return true;
            set.add(head);
            head = head.next;
        }
        return false;
    }

    //Time Complexity: O(n)
    //Space Complexity: O(1)
    private static boolean detectLoopByMarkingDummyNode(Node head){
        Node dummy = new Node(0);
        Node temp;
        while(head != null){
            if(head.next == dummy)
                return true;
            temp = head;
            head = head.next;
            temp.next = dummy;
        }
        return false;
    }
}
