package com.chandu.dsa.linked.list;

public class SwapPairwise {
    public static void main(String[] args) {
        Node head1 = Node.createDemoLinkedList();
        Node head2 = Node.createLinkedList(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println("Original linked list 1: ");
        Node.printList(head1);
        System.out.println("Original linked list 2: ");
        Node.printList(head2);

        System.out.println("Recursive,Linked list 1 after swapping pairwise by exchanging data: ");
        //swapByExchangingDataRecursive(head1);
        head1 = swapByExchangingLinksRecursive(head1);
        Node.printList(head1);
        System.out.println("Iterative,Linked list 2 after swapping pairwise by exchanging data: ");
        //swapByExchangingDataIterative(head2);
        head2 = swapByExchangingLinksIterative(head2);
        Node.printList(head2);
    }

    private static Node swapByExchangingLinksIterative(Node head){
        if(head==null || head.next==null)
            return head;
        Node next;
        Node curr = head.next.next;
        Node prev = head;
        head = head.next;
        head.next = prev;

        while (curr!=null && curr.next!=null){
            prev.next = curr.next;
            prev = curr;
            next = curr.next.next;
            curr.next.next = curr;
            curr = next;
        }
        prev.next = curr;
        return head;
    }

    private static Node swapByExchangingLinksRecursive(Node head){
        if(head==null || head.next==null)
            return head;
        Node remaining = head.next.next;
        Node newHead = head.next;
        head.next.next = head;
        head.next = swapByExchangingLinksRecursive(remaining);
        return newHead;
    }

    //Time Complexity: O(n)
    //Space Complexity: O(1)
    private static void swapByExchangingDataIterative(Node head){
        while (head!=null && head.next!=null){
            swapData(head, head.next);
            head = head.next.next;
        }
    }

    //Time Complexity: O(n)
    //Space Complexity: O(1)
    private static void swapByExchangingDataRecursive(Node head){
        if(head==null || head.next==null)
            return;
        swapData(head, head.next);
        swapByExchangingDataRecursive(head.next.next);
    }

    private static void swapData(Node head1, Node head2){
        int temp = head1.data;
        head1.data = head2.data;
        head2.data = temp;
    }
}
