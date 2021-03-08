package com.sde.chandu.searching;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
    public static void main(String[] args) {
        int[] arr1 = {3, 3, 4, 2, 4, 4, 2, 4, 4}; // O/p: 4
        int[] arr2 = {3, 3, 4, 2, 4, 4, 2, 4}; // O/p: -1

        System.out.println("arr1, majority element brute: " + findMajorityBrute(arr1));
        System.out.println("arr2, majority element brute: " + findMajorityBrute(arr2));
        System.out.println();

        System.out.println("arr1, majority element hashing: " + findMajorityHashing(arr1));
        System.out.println("arr2, majority element hashing: " + findMajorityHashing(arr2));
        System.out.println();

        System.out.println("arr1, majority element sorting: " + findMajoritySorting(arr1));
        System.out.println("arr2, majority element sorting: " + findMajoritySorting(arr2));
        System.out.println();

        System.out.println("arr1, majority element Moore Voting: " + findMajorityMooreVotingAlgorithm(arr1));
        System.out.println("arr2, majority element Moore Voting: " + findMajorityMooreVotingAlgorithm(arr2));
    }

    //Time complexity: O(n^2)
    //Space complexity: O(1)
    private static int findMajorityBrute(int[] arr){
        if(arr==null || arr.length==0)
            return -1;
        int count;
        for(int i : arr){
            count = 0;
            for (int j : arr){
                if(i == j)
                    count++;
            }
            if(count > arr.length/2)
                return i;
        }
        return -1;
    }

    //Time complexity: O(n)
    //Space complexity: O(n)
    private static int findMajorityHashing(int[] arr){
        if(arr==null || arr.length==0)
            return -1;
        else if(arr.length == 1)
            return arr[0];
        Map<Integer, Integer> map = new HashMap<>();
        int count;
        for (int i : arr){
            if(map.containsKey(i)){
                count = map.get(i);
                count++;
                if(count > arr.length/2)
                    return i;
                map.put(i, count);
            }else
                map.put(i, 1);
        }
        return -1;
    }

    //Time complexity: O(n log n)
    //Space complexity: O(1)
    private static int findMajoritySorting(int[] arr){
        if(arr==null || arr.length==0)
            return -1;
        else if(arr.length == 1)
            return arr[0];
        Arrays.sort(arr);
        int prev=arr[0], count=1;
        for (int i=1; i<arr.length; i++){
            if (arr[i] == prev){
                count++;
                if(count > arr.length/2)
                    return arr[i];
            }else {
                prev = arr[i];
                count = 1;
            }
        }
        return -1;
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    private static int findMajorityMooreVotingAlgorithm(int[] arr){
        if(arr==null || arr.length==0)
            return -1;
        int candidate = findCandidate(arr);
        return isMajority(arr, candidate) ? candidate : -1;
    }

    private static int findCandidate(int[] arr){
        int majIndex=0, count=1;
        for(int i=1; i<arr.length; i++){
            if (arr[i] == arr[majIndex])
                count++;
            else
                count--;
            if(count == 0){
                majIndex = i;
                count = 1;
            }
        }
        return arr[majIndex];
    }

    private static boolean isMajority(int[] arr, int key){
        int count=0;
        for (int i : arr){
            if (key == i)
                count++;
        }
        return count > arr.length/2;
    }
}
