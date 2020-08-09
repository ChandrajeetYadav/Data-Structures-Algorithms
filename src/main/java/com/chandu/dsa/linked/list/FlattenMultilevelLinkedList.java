package com.chandu.dsa.linked.list;

public class FlattenMultilevelLinkedList {
    int data;
    FlattenMultilevelLinkedList next;
    FlattenMultilevelLinkedList child;
    FlattenMultilevelLinkedList(int data){
        this.data = data;
    }

    public static void main(String[] args) {
        FlattenMultilevelLinkedList head = createLinkedList();
        flattenMultilevelList(head);
        System.out.println("Linked list after flattening: ");
        printList(head);
    }

    //Time Complexity: O(n)
    //Space Complexity: O(1)
    public static void flattenMultilevelList(FlattenMultilevelLinkedList head){
        if(head == null)
            return;
        FlattenMultilevelLinkedList tail = head, temp;
        FlattenMultilevelLinkedList curr = head;

        while (tail.next != null)
            tail = tail.next;
        while (curr != tail){
            if(curr.child != null){
                tail.next = curr.child;
                temp = curr.child;
                while (temp.next != null)
                    temp = temp.next;
                tail = temp;
            }
            curr = curr.next;
        }
    }

    public static FlattenMultilevelLinkedList createLinkedList(){
        FlattenMultilevelLinkedList head = create(new int[] {10, 5, 12, 7, 11});
        head.child = create(new int[]{4, 20, 13});
        head.next.next.next.child = create(new int[]{17, 6});
        head.child.next.child = new FlattenMultilevelLinkedList(2);
        head.child.next.next.child = new FlattenMultilevelLinkedList(16);
        head.child.next.next.child.child = new FlattenMultilevelLinkedList(3);

        head.next.next.next.child.child = create(new int[]{9, 8});
        head.next.next.next.child.child.child = create(new int[]{19, 15});

        return head;
    }

    public static FlattenMultilevelLinkedList create(int[] arr){
        if(arr==null || arr.length==0)
            return null;
        FlattenMultilevelLinkedList head = new FlattenMultilevelLinkedList(arr[0]);
        FlattenMultilevelLinkedList temp  = head;
        for(int i=1; i<arr.length; i++){
            temp.next = new FlattenMultilevelLinkedList(arr[i]);
            temp = temp.next;
        }
        return head;
    }

    public static void printList(FlattenMultilevelLinkedList head){
        while (head != null){
            System.out.print(head.data + " ");
            head = head.next;
        }
    }
}
