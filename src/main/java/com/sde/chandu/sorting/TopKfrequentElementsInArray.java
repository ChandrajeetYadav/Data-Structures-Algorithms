package com.sde.chandu.sorting;

import java.util.*;

public class TopKfrequentElementsInArray {
    public static void main(String[] args) {
        int[] arr1 = {1,1,1,2,2,3};
        int k1 = 2; // O/p: {1, 2}
        int[] arr2 = {1,1,2,2,3,3,3,4};
        int k2 = 2; // O/p: {3, 2}
        System.out.println("Top k frequent elements in arr1, hashmap: ");
        printTopKElementsUsingMap(arr1, k1);
        System.out.println("Top k frequent elements in arr2, hashmap: ");
        printTopKElementsUsingMap(arr2, k2);
        System.out.println();
        System.out.println("Top k frequent elements in arr1, priority queue: ");
        printTopKElementsUsingPQ(arr1, k1);
        System.out.println("Top k frequent elements in arr2, priority queue: ");
        printTopKElementsUsingPQ(arr2, k2);

    }

    //Time complexity: O(n log n)
    //Space complexity: O(n)
    private static void printTopKElementsUsingMap(int[] arr, int k){
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr)
            map.put(i, map.getOrDefault(i, 0)+1);
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((a,b)->a.getValue().equals(b.getValue()) ? b.getKey()-a.getKey() : b.getValue()-a.getValue());
        for (int i=0; i<k; i++)
            System.out.print(list.get(i).getKey() + "\t");
        System.out.println();
    }

    //Time complexity: O(n log n)
    //Space complexity: O(n)
    private static void printTopKElementsUsingPQ(int[] arr, int k){
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr)
            map.put(i, map.getOrDefault(i, 0)+1); // O(n)
        PriorityQueue<Map.Entry<Integer, Integer>> pq =
                new PriorityQueue<>((a,b)->a.getValue()==b.getValue() ? b.getKey()-a.getKey() : b.getValue()-a.getValue());
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) // O(n log n)
            pq.add(entry);
        for (int i=0; i<k; i++) // O(k log n)
            System.out.print(pq.poll().getKey() + "\t");
        System.out.println();
    }
}
