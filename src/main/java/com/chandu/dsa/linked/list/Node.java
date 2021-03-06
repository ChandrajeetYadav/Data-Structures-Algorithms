package com.chandu.dsa.linked.list;

public class Node {
    public int data;
    public Node next;

    public static void main(String[] args) {
        Node head = createDemoLinkedList();
        printList(head);
    }

    Node(int data){
        this.data = data;
    }

    public static Node createDemoLinkedList(){
        Node head = new Node(1);
        Node temp = head;
        for(int i=2; i<=5; i++){
            temp.next = new Node(i);
            temp = temp.next;
        }
        return head;
    }

    public static void printList(Node head){
        while(head != null){
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static Node createLinkedList(int[] arr){
        if(arr==null || arr.length==0)
            return null;
        Node head = new Node(arr[0]);
        Node temp = head;
        for(int i=1; i< arr.length; i++){
            temp.next = new Node(arr[i]);
            temp = temp.next;
        }
        return head;
    }
}
