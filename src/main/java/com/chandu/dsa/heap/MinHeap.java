package com.chandu.dsa.heap;

public class MinHeap {
    private int[] heap;
    private int heapSize;
    private int capacity;

    MinHeap(int capacity){
        this.capacity = capacity;
        this.heapSize = 0;
        heap = new int[capacity];
    }
    public static void main(String[] args) {
        int[] arr = {5, 3, 17, 10, 84, 19, 6, 22, 9};
        MinHeap minHeap = new MinHeap(arr.length);
        minHeap.buildHeap(arr);
        System.out.println("Min heap is as below:");
        minHeap.print();
        minHeap.delete(3);
        System.out.println("Min heap after deleting element");
        minHeap.print();
        minHeap.insert(7);
        System.out.println("Min heap after inserting element");
        minHeap.print();
        minHeap.deleteMin();
        System.out.println("Min heap after deleting min element");
        minHeap.print();
    }

    public int parent(int i){
        return (i-1)/2;
    }

    public int leftChild(int i){
        return 2*i + 1;
    }

    public int rightChild(int i){
        return 2*i + 2;
    }

    public boolean isLeaf(int i){
        return i>(heapSize-1)/2 && i<=heapSize;
    }

    public void swap(int i, int j){
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void buildHeap(int[] arr){
        if(capacity < arr.length){
            System.out.println("Heap capacity is less than array size");
            return;
        }
        heapSize = arr.length;
        for (int i=0; i<arr.length; i++)
            heap[i] = arr[i];
        minHeap();
    }

    public void minHeap(){
        for(int i=heapSize/2-1; i>=0; i--)
            minHeapify(i);
    }

    public void minHeapify(int pos){
        if(isLeaf(pos))
            return;
        int left = leftChild(pos);
        int right = rightChild(pos);
        if(heap[pos]>heap[left] || heap[pos]>heap[right]){
            int min = heap[left]<heap[right] ? left : right;
            swap(pos, min);
            minHeapify(min);
        }
    }

    public void insert(int data){
        if (heapSize >= capacity){
            System.out.println("Memory overflow");
            return;
        }
        heapSize++;
        int i= heapSize-1;
        while (i!=0 && data<heap[(i-1)/2]){
            heap[i] = heap[(i-1)/2];
            i = (i-1)/2;
        }
        heap[i] = data;
    }

    public int deleteMin(){
        int data = heap[0];
        heap[0] = heap[--heapSize];
        minHeapify(0);
        return data;
    }

    public int delete(int key){
        if(heapSize == 0){
            System.out.println("Heap is empty.");
            return -1;
        }
        int i=0;
        for( ; i<heapSize; i++){
            if(heap[i] == key)
                break;
        }
        if (i == heapSize){
            System.out.println("Key " + key + " is not present in heap.");
            return -1;
        }
        heap[i] = heap[--heapSize];
        minHeapify(i);
        return key;
    }

    public void print(){
        for(int i=0; i<heapSize; i++)
            System.out.print(heap[i] + " ");
        System.out.println();
    }
}
