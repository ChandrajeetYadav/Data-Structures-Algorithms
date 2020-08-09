package com.chandu.dsa.linked.list;

import java.util.Comparator;
import java.util.PriorityQueue;

public class FlatteningLinkedList {
    int data;
    FlatteningLinkedList right;
    FlatteningLinkedList down;
    FlatteningLinkedList(int data){
        this.data = data;
    }

    public static void main(String[] args) {
        FlatteningLinkedList head = createLinkedList();
        //FlatteningLinkedList result = flattenUsingMerge(head);
        FlatteningLinkedList result = flattenUsingHeap(head);
        System.out.println("Flattened list is: ");
        printList(result);
    }

    //Time Complexity: O(N)
    //Space Complexity: O(M)
    public static FlatteningLinkedList flattenUsingHeap(FlatteningLinkedList head){
        PriorityQueue<FlatteningLinkedList> pq = new PriorityQueue<>(
                new Comparator<FlatteningLinkedList>(){
                    public int compare(FlatteningLinkedList a, FlatteningLinkedList b){
                        return a.data - b.data;
                    }
                });
        while (head != null){
            pq.add(head);
            head = head.right;
        }
        FlatteningLinkedList result=null, tail=null;
        if(!pq.isEmpty()){
            result = pq.poll();
            tail = result;
            if(result.down != null)
                pq.add(result.down);
        }
        while(!pq.isEmpty()){
            tail.down = pq.poll();
            tail = tail.down;
            if(tail.down != null)
                pq.add(tail.down);
        }
        return  result;
    }

    //Time Complexity: O(N*M)
    //Space Complexity: O(1), ignoring method stack
    public static FlatteningLinkedList flattenUsingMerge(FlatteningLinkedList head){
        if(head==null || head.right==null)
            return head;
        head.right = flattenUsingMerge(head.right);
        return  merge(head, head.right);
    }

    public static FlatteningLinkedList merge(FlatteningLinkedList head1, FlatteningLinkedList head2){
        if(head1 == null)
            return head2;
        if(head2 == null)
            return head1;
        FlatteningLinkedList result;
        if(head1.data < head2.data){
            result = head1;
            result.down = merge(head1.down, head2);
        }else{
            result = head2;
            result.down = merge(head1, head2.down);
        }
        result.right = null;
        return result;
    }

    public static FlatteningLinkedList createLinkedList(){
        FlatteningLinkedList head1 = create(new int[] {5, 7, 8, 30});
        FlatteningLinkedList head2 = create(new int[] {10, 20});
        FlatteningLinkedList head3 = create(new int[] {19, 22, 50});
        FlatteningLinkedList head4 = create(new int[] {28, 35, 40, 45});

        head1.right = head2;
        head2.right = head3;
        head3.right = head4;

        return head1;
    }

    public static FlatteningLinkedList create(int[] arr){
        if(arr==null || arr.length==0)
            return null;
        FlatteningLinkedList head = new FlatteningLinkedList(arr[0]);
        FlatteningLinkedList temp  = head;
        for(int i=1; i<arr.length; i++){
            temp.down = new FlatteningLinkedList(arr[i]);
            temp = temp.down;
        }
        return head;
    }

    public static void printList(FlatteningLinkedList head){
        while (head != null){
            System.out.print(head.data + " ");
            head = head.down;
        }
    }
}
