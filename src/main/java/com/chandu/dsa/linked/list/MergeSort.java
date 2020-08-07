package com.chandu.dsa.linked.list;

public class MergeSort {
    public static void main(String[] args) {
        Node head = Node.createLinkedList(new int[] {40, 20, 60, 10, 50, 30});
        System.out.println("Original list: ");
        Node.printList(head);
        System.out.println("Sorted list: ");
        Node.printList(mergeSort(head));
    }

    //Time Complexity: O(n)
    //Space Complexity: O(1)
    private static Node mergeSort(Node head){
        if(head==null || head.next==null)
            return head;
        Node middle = getMiddle(head);
        Node nextOfMiddle = middle.next;
        middle.next = null;
        Node left = mergeSort(head);
        Node right = mergeSort(nextOfMiddle);

        return merge(left, right);
    }

    private static Node getMiddle(Node head){
        if(head == null)
            return null;
        Node slow = head, fast = head;
        while(fast.next!=null && fast.next.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private static Node merge(Node head1, Node head2){
        Node dummy = new Node(0);
        Node tail = dummy;
        while (true){
            if (head1 == null){
                tail.next = head2;
                break;
            }
            if(head2 == null){
                tail.next = head1;
                break;
            }
            if(head1.data < head2.data){
                tail.next = head1;
                head1 = head1.next;
            }else {
                tail.next = head2;
                head2 = head2.next;
            }
            tail = tail.next;
        }
        tail = dummy.next;
        dummy.next = null;
        return  tail;
    }
}
