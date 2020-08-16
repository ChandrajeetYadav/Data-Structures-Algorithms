package com.chandu.dsa.heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianInStream {
    public static void main(String[] args) {
        int[] arr = {5, 15, 1, 3};
        PriorityQueue<Integer> smaller = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> greater = new PriorityQueue<>();

        double median = 0;
        for (int i=0; i< arr.length; i++){
            median = median(arr[i], median, smaller, greater);
            System.out.println("Median: " + median);
        }
    }

    //Time Complexity: O(log n)
    //Space Complexity: O(n)
    public static double median(int n, double median, PriorityQueue<Integer> smaller, PriorityQueue<Integer> greater){
        if(smaller.size() > greater.size()){
            if(n < median){
                greater.add(smaller.poll());
                smaller.add(n);
            }else
                greater.add(n);
            return (smaller.peek() + greater.peek())/2.0;
        }else if(smaller.size() < greater.size()){
            if(n > median){
                smaller.add(greater.poll());
                greater.add(n);
            }else
                smaller.add(n);
            return (smaller.peek() + greater.peek())/2.0;
        }else {
            if(n < median){
                smaller.add(n);
                return smaller.peek();
            }else {
                greater.add(n);
                return greater.peek();
            }
        }
    }
}
