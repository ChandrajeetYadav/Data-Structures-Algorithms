package com.chandu.dsa.heap;

import java.util.PriorityQueue;

public class KthLargestInStream {

    public static void main(String[] args) {
        int[] inp = {10, 20, 11, 70, 50, 40, 100, 5};
        int k = 3;
        int[] arr = new int[k];
        int count = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        for(int i=0; i<inp.length; i++){
            count++;
            //System.out.println(getKthLargestUsingArray(inp[i], arr, count, k));
            System.out.println(getKthLargestUsingPriorityQueue(inp[i], pq, k));
        }
    }

    public static int getKthLargestUsingPriorityQueue(int num, PriorityQueue<Integer> pq, int k){
        if(pq.size() < k)
            pq.add(num);
        else{
            if(num >= pq.peek()){
                pq.poll();
                pq.add(num);
            }
        }
        return pq.size()==k ? pq.peek() : -1;
    }

    public static int getKthLargestUsingArray(int num, int[] arr, int count, int k){
        addElement(arr, num, k);
        if(count < k)
            return -1;
        return arr[0];
    }

    public static void addElement(int[] arr, int num, int k){
        if(num < arr[0])
            return;
        int i=0;
        while(i<k-1 && arr[i+1]<num){
            arr[i] = arr[i+1];
            i++;
        }
        arr[i] = num;
    }
}
