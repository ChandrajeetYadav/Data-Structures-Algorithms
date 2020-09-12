package com.chandu.dsa.greedy;

import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class MaximizeToys {
    public static void main(String[] args) {
        int money = 50;
        int[] toysCost = {1, 12, 5, 111, 200, 1000, 10, 9, 12, 15};
        System.out.println("Maximum toys that can be bought is : " + getMaximumNumberOfToys(toysCost, money));
        System.out.println("Maximum toys that can be bought is : " + getMaximumNumberOfToysUsingPriorityQueue(toysCost, money));
    }

    //Time Complexity: O(n log n)
    //Space Complexity: O(1)
    private static int getMaximumNumberOfToys(int[] toysCost, int money){
        Arrays.sort(toysCost);
        int count = 0;
        for (int i : toysCost){
            if (money >= i){
                count++;
                money -= i;
            }else
                return count;
        }
        return count;
    }

    //Time Complexity: O(n log n)
    //Space Complexity: O(n)
    private static int getMaximumNumberOfToysUsingPriorityQueue(int[] toysCost, int money){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : toysCost)
            pq.add(i);
        int count = 0;
        int temp;
        while (!pq.isEmpty()){
            temp = pq.poll();
            if (money >= temp){
                count++;
                money -= temp;
            }else
                return count;
        }
        return count;
    }
}
