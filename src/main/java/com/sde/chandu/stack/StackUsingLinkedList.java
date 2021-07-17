package com.sde.chandu.stack;

import com.sde.chandu.linkedlist.Node;

public class StackUsingLinkedList {
    private Node top;

    public static void main(String[] args) {
        StackUsingLinkedList stack = new StackUsingLinkedList();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println(stack);
    }

    // Time Complexity : O(1)
    public boolean isEmpty() {
        return top == null;
    }

    // Time Complexity : O(1)
    public void push(int data) {
        Node node = new Node(data);
        node.next = top;
        top = node;
    }

    // Time Complexity : O(1)
    public int pop() {
        if (top == null)
            return -1;
        Node temp = top;
        top = top.next;
        temp.next = null;
        return temp.data;
    }

    // Time Complexity : O(1)
    public int peek() {
        if (top == null)
            return -1;
        return top.data;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node temp = top;
        while (temp != null) {
            sb.append(temp.data).append(" ");
            temp = temp.next;
        }
        return sb.toString();
    }
}
