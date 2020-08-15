package com.chandu.dsa.heap;

public class MaxHeap {
    public int[] heap;
    public int capacity;
    public int size;
    
    MaxHeap(int capacity){
        this.capacity = capacity;
        this.size = 0;
        heap = new int[capacity];
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 17, 10, 84, 19, 6, 22, 9};
        MaxHeap maxHeap = new MaxHeap(arr.length);
        maxHeap.buildMaxHeap(arr);
        System.out.println("Max heap is: ");
        maxHeap.print();
        System.out.println();
        System.out.println("Maximum element in heap is: " + maxHeap.getMax());
        maxHeap.extractMax();
        System.out.println("Max heap is: ");
        maxHeap.print();
        System.out.println();
        maxHeap.insert(84);
        System.out.println("Max heap is: ");
        maxHeap.print();
    }

    public void buildMaxHeap(int[] arr){
        if(arr.length > capacity){
            System.out.println("Memory overflow: ");
            return;
        }
        for(int i=0; i<arr.length; i++)
            heap[i] = arr[i];
        size = arr.length;
        maxHeap();
    }

    public void maxHeap(){
        for(int i=size/2-1; i>=0; i--)
            maxHeapify(i);
    }

    public void maxHeapify(int pos){
        if(isLeaf(pos))
            return;
        int left = leftChild(pos);
        int right = rightChild(pos);
        if(heap[pos]<heap[left] || heap[pos]<heap[right]){
            int maxPos = heap[left]>heap[right] ? left : right;
            swap(pos, maxPos);
            maxHeapify(maxPos);
        }
    }

    public boolean isLeaf(int pos){
        return pos>(size)/2-1 && pos<=size;
    }

    public int leftChild(int pos){
        return 2*pos + 1;
    }

    public int rightChild(int pos){
        return 2*pos + 2;
    }

    public int parent(int pos){
        return (pos-1)/2;
    }

    public void swap(int i, int j){
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public int extractMax(){
        if(size == 0)
            return -1;
        if(size == 1){
            size--;
            return heap[0];
        }
        int data = heap[0];
        heap[0] = heap[--size];
        maxHeapify(0);
        return data;
    }

    public int getMax(){
        return size>=1 ? heap[0] : -1;
    }

    public void print(){
        for(int i=0; i<size; i++)
            System.out.print(heap[i] + " ");
    }

    public void insert(int data){
        if(size == capacity){
            System.out.println("Memory overflow");
            return;
        }
        size++;
        int i = size-1;
        while (i!=0 && data > heap[parent(i)]){
            heap[i] = heap[parent(i)];
            i = parent(i);
        }
        heap[i] = data;
    }
}
