package com.chandu.dsa.linked.list;

public class FindMiddleInLinkedList {
    public static void main(String[] args) {
        Node head1 = Node.createDemoLinkedList();
        System.out.println("Original linked list 1: ");
        Node.printList(head1);
        //System.out.println("Middle element in linked list 1 is: " + getMiddleBrute(head1));
        //System.out.println("Middle element in linked list 1 is: " + getMiddle(head1));
        System.out.println("Middle element in linked list 1 is: " + getMiddleUsing2Pointers(head1));

        Node head2 = Node.createLinkedList(new int[]{1,2,3,4,5,6});
        System.out.println("Original linked list 2: ");
        Node.printList(head2);
        //System.out.println("Middle element in linked list 1 is: " + getMiddleBrute(head2));
        //System.out.println("Middle element in linked list 1 is: " + getMiddle(head2));
        System.out.println("Middle element in linked list 1 is: " + getMiddleUsing2Pointers(head2));
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    public static int getMiddleUsing2Pointers(Node head){
        if(head == null)
            return -1;
        Node slow = head;
        Node fast = head;

        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow.data;
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    public static int getMiddle(Node head){
        if(head == null)
            return -1;
        Node mid = head;
        int count = 0;
        while(head != null){
            if(count%2 == 1)
                mid = mid.next;
            head = head.next;
            count++;
        }
        return mid.data;
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    public static int getMiddleBrute(Node head){
        if(head == null)
            return -1;
        Node temp = head;
        int count = 0;
        while(temp != null){
            temp = temp.next;
            count++;
        }
        temp = head;
        for(int i=1; i<=count/2; i++)
            temp = temp.next;
        return temp.data;
    }
}
