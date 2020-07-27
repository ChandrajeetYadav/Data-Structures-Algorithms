package com.chandu.dsa.linked.list;

public class StackUsingLinkedList {
    public static Node top;

    public static void main(String[] args) {
        System.out.println("Initial stack: ");
        Node.printList(top);
        System.out.println(pop());
        push(1);
        push(2);
        push(3);
        System.out.println("Stack after pushing elements: ");
        Node.printList(top);
        pop();
        System.out.println("Stack after popping elements: ");
        Node.printList(top);
    }

    //Time Complexity: O(1)
    //Space Complexity: O(1)
    public static void push(int data){
        Node node = new Node(data);
        node.next = top;
        top = node;
    }

    //Time Complexity: O(1)
    //Space Complexity: O(1)
    public static int pop(){
        if(top == null)
            return -1;
        Node temp = top;
        top = top.next;
        temp.next = null;
        return temp.data;
    }
}
