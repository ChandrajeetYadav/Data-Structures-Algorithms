package com.chandu.dsa.heap;

public class HeapSort {
    public static void main(String[] args) {
        //int[] arr = {10,9,8,7,6,5,4,3,2,1};
        int[] arr = {4,1,3,9,7};
        System.out.println("Original array: ");
        print(arr);
        heapSort(arr, arr.length);
        System.out.println("Sorted array:");
        print(arr);
    }

    //Time Complexity: O(n * log n)
    //Space Complexity: O(1)
    public static void heapSort(int[] arr, int size){
        if(size<=1)
            return;
        for (int i=size/2-1; i>=0; i--)
            maxHeapify(arr, i, size);
        for (int i=size-1; i>0; i--){
            swap(arr, i, 0);
            maxHeapify(arr, 0, i);
        }
    }

    public static void maxHeapify(int[] heap, int pos, int size){
        if (isLeaf(pos, size))
            return;
        int left = 2*pos + 1;
        int right = 2*pos + 2;
        int largest = pos;
        if(left<size && heap[pos]<heap[left])
            largest = left;
        if(right<size && heap[largest]<heap[right])
            largest = right;
        if(pos != largest){
            swap(heap, pos, largest);
            maxHeapify(heap, largest, size);
        }
    }

    public static boolean isLeaf(int pos, int size){
        return pos>size/2-1 && pos<size;
    }

    public static void swap(int[] heap, int i, int j){
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public static void print(int[] arr){
        for(int i : arr)
            System.out.print(i + " ");
        System.out.println();
    }
}
