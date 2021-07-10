package com.sde.chandu.linkedlist;

public class CircularLinkedList {
    int data;
    CircularLinkedList next;

    CircularLinkedList(int data) {
        this.data = data;
    }

    static CircularLinkedList createCircularLL(int[] arr) {
        if (arr == null || arr.length == 0)
            return null;
        CircularLinkedList head = new CircularLinkedList(arr[0]);
        CircularLinkedList temp = head;
        for (int i = 1; i < arr.length; i++) {
            temp.next = new CircularLinkedList(arr[i]);
            temp = temp.next;
        }
        temp.next = head;
        return head;
    }

    public void display() {
        CircularLinkedList temp = this;
        System.out.print(temp.data + " ");
        while (temp.next != this) {
            System.out.print(temp.next.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}
