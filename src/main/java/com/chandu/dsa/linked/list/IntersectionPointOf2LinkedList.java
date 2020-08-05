package com.chandu.dsa.linked.list;

import java.util.HashSet;

public class IntersectionPointOf2LinkedList {
    public static void main(String[] args) {
        Node head1 = Node.createLinkedList(new int[]{3, 6, 9, 15, 30});
        Node head2 = new Node(10);
        Node head3 = Node.createDemoLinkedList();
        head2.next = head1.next.next.next;
        System.out.println("List 1 : ");
        Node.printList(head1);
        System.out.println("List 2 : ");
        Node.printList(head2);
        System.out.println("List 3 : ");
        Node.printList(head3);
        //System.out.println("Intersection point of list 1 and list 2 is: " + getIntersectionPointUsingBruteForce(head1, head2));
        //System.out.println("Intersection point of list 1 and list 3 is: " + getIntersectionPointUsingBruteForce(head1, head3));
        //System.out.println("Intersection point of list 1 and list 2 is: " + getIntersectionPointUsingHashing(head1, head2));
        //System.out.println("Intersection point of list 1 and list 3 is: " + getIntersectionPointUsingHashing(head1, head3));
        System.out.println("Intersection point of list 1 and list 2 is: " + getIntersectionPointEfficient(head1, head2));
        System.out.println("Intersection point of list 1 and list 3 is: " + getIntersectionPointEfficient(head1, head3));
        //System.out.println("Intersection point of list 1 and list 2 is: " + getIntersectionPointUsing2Pointer(head1, head2));
        //System.out.println("Intersection point of list 1 and list 3 is: " + getIntersectionPointUsing2Pointer(head1, head3));
    }

    //Time Complexity: O(m+n)
    //Space Complexity: O(1)
    public static int getIntersectionPointEfficient(Node head1, Node head2){
        if(head1==null || head2==null)
            return -1;
        int length1 = getLength(head1);
        int length2 = getLength(head2);
        int diff = Math.abs(length1 - length2);
        if(length1 > length2)
            return getIntersectionNode(head1, head2, diff);
        else
            return getIntersectionNode(head2, head1, diff);

    }

    public static int getIntersectionNode(Node head1, Node head2, int diff){
        int count = 0;
        while (count++ < diff)
            head1 = head1.next;
        while (head1!=null && head2!=null){
            if(head1 == head2)
                return head1.data;
            head1 = head1.next;
            head2 = head2.next;
        }
        return -1;
    }

    //Time Complexity: O(m+n)
    //Space Complexity: O(1)
    //Note: This will go into infinite loop if there is no intersection point.
    public static int getIntersectionPointUsing2Pointer(Node head1, Node head2){
        if(head1==null || head2==null)
            return -1;
        Node temp1 = head1;
        Node temp2 = head2;

        while (temp1 != temp2){
            temp1 = temp1.next;
            temp2 = temp2.next;
            if(temp1 == null)
                temp1 = head2;
            if(temp2 == null)
                temp2 = head1;
        }
        return temp1.data;
    }

    //Time Complexity: O(m+n)
    //Space Complexity: O(m or n)
    public static int getIntersectionPointUsingHashing(Node head1, Node head2){
        HashSet<Node> set = new HashSet<>();
        while (head1 != null){
            set.add(head1);
            head1 = head1.next;
        }
        while(head2 != null){
            if(set.contains(head2))
                return head2.data;
            head2 = head2.next;
        }
        return -1;
    }

    //Time Complexity: O(m * n)
    //Space Complexity: O(1)
    public static int getIntersectionPointUsingBruteForce(Node head1, Node head2){
        Node temp ;
        while (head1 != null){
            temp = head2;
            while (temp != null){
                if(head1 == temp)
                    return head1.data;
                temp = temp.next;
            }
            head1 = head1.next;
        }
        return -1;
    }

    private static int getLength(Node head){
        int length = 0;
        while(head != null){
            length++;
            head = head.next;
        }
        return length;
    }
}
