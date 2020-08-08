package com.chandu.dsa.linked.list;

import java.util.HashSet;
import java.util.Set;

public class DetectAndRemoveLoop {
    public static void main(String[] args) {
        Node head1 = Node.createDemoLinkedList();
        Node head2 = Node.createLinkedList(new int[] {1, 2, 3, 4, 5, 6});
        head1.next.next.next.next.next = head1.next;

        //detectAndRemoveLoopUsingHashing(head1);
        //detectAndRemoveLoopUsingHashing(head2);
        detectAndRemoveLoopUsing2Pointers(head1);
        detectAndRemoveLoopUsing2Pointers(head2);
        System.out.println("List 1: ");
        Node.printList(head1);
        System.out.println("List 2: ");
        Node.printList(head2);
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    private static void detectAndRemoveLoopUsingHashing(Node head){
        Set<Node> set = new HashSet<>();
        Node prev = null;
        while (head != null){
            if(set.contains(head)){
                prev.next = null;
                return;
            }
            set.add(head);
            prev = head;
            head = head.next;
        }
    }

    private static void detectAndRemoveLoopUsing2Pointers(Node head){
        Node fast = head, slow = head;
        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow)
                break;
        }
        if(fast == slow){
            //removeLoopBrute(head, slow);
            //removeLoopByCountingLoopLength(head, slow);
            removeLoopByEfficient(head, slow);
        }
    }

    private static void removeLoopByEfficient(Node head, Node loop){
        if(loop == head){
            while(loop.next != head)
                loop = loop.next;
            loop.next = null;
            return;
        }
        Node temp1 = head, temp2 = loop;
        while(temp1.next != temp2.next){
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        temp2.next = null;
    }

    private static void removeLoopByCountingLoopLength(Node head, Node loop){
        Node temp1 = head, temp2 = loop;
        int loopLength = 1;
        while(temp2.next != loop){
            loopLength++;
            temp2 = temp2.next;
        }

        temp2 = head;
        for(int i=1; i<=loopLength; i++, temp2 = temp2.next);

        while (temp1 != temp2){
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        while (temp2.next != temp1)
            temp2 = temp2.next;

        temp2.next = null;
    }

    private static void removeLoopBrute(Node head, Node loop){
        Node temp1 = head, temp2;

        while (true){
            temp2 = loop;
            while(temp2.next!=loop && temp2.next!=temp1)
                temp2 = temp2.next;
            if(temp2.next == temp1)
                break;
            temp1 = temp1.next;
        }
        temp2.next = null;
    }
}
