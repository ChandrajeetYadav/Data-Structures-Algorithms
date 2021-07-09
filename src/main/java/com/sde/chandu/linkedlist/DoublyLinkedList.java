package com.sde.chandu.linkedlist;

public class DoublyLinkedList {
    int data;
    DoublyLinkedList prev;
    DoublyLinkedList next;

    DoublyLinkedList(int data) {
        this.data = data;
    }

    public static DoublyLinkedList createList(int[] arr) {
        if (arr == null || arr.length == 0)
            return null;
        DoublyLinkedList head = new DoublyLinkedList(arr[0]);
        DoublyLinkedList temp = head;
        for (int i = 1; i < arr.length; i++) {
            temp.next = new DoublyLinkedList(arr[i]);
            temp.next.prev = temp;
            temp = temp.next;
        }
        return head;
    }

    public void display() {
        DoublyLinkedList temp = this;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}
