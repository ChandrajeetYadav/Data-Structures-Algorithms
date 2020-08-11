package com.chandu.dsa.linked.list;

public class StablePartitionListAroundX {
    public static void main(String[] args) {
        Node head = Node.createLinkedList(new int[] {1, 4, 3, 2, 5, 2, 3});
        System.out.println("Original list: ");
        Node.printList(head);
        System.out.println("Linked list after partitioning: ");
        int x = 3;
        head = partition(head, x);
        Node.printList(head);
    }

    //Time Complexity: O(n)
    //Space Complexity: O(1)
    public static Node partition(Node head, int x){
        if(head == null)
            return null;
        /*Node smaller = new Node(0);
        Node equal = new Node(0);
        Node greater = new Node(0);
        Node temp1 = smaller, temp2 = equal, temp3 = greater;

        while (head != null){
            if(head.data < x){
                temp1.next = head;
                temp1 = temp1.next;
            }else if (head.data == x){
                temp2.next = head;
                temp2 = temp2.next;
            }else {
                temp3.next = head;
                temp3 = temp3.next;
            }
            head = head.next;
        }
        temp1.next = equal.next!=null ? equal.next : greater.next;
        temp2.next = greater.next;
        temp3.next = null;
        head = smaller.next;
        smaller.next = equal.next = greater.next = null;
        return head;*/
        Node start = new Node(0);
        Node after = new Node(0);
        Node res = start, mid = after;
        while(head != null){
            if(head.data < x){
                start.next = head;
                start = start.next;
            }else{
                after.next = head;
                after = after.next;
            }
            head = head.next;
        }
        start.next = mid.next;
        after.next = null;
        return res.next;
    }
}
