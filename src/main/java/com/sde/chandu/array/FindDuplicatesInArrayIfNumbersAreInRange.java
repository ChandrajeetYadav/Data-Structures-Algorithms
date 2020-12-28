package com.sde.chandu.array;

import java.util.ArrayList;
import java.util.List;

public class FindDuplicatesInArrayIfNumbersAreInRange {
    public static void main(String[] args) {
        int[] arr = {0,3,1,2};
        int[] arr1 = {2,3,1,2,3};

        List<Integer> res = findDuplicates(arr);
        System.out.println("Duplicates in arr: " + res);

        res = findDuplicates(arr1);
        System.out.println("Duplicates in arr1: " + res);
    }

    //Time complexity: O(n)
    //Space complexity: O(1), not considering resultant list
    private static List<Integer> findDuplicates(int[] arr){
        List<Integer> res = new ArrayList<>();
        if(arr==null || arr.length<=2){
            res.add(-1);
            return res;
        }
        for(int i=0, index; i<arr.length; i++){
            index = arr[i] % arr.length;
            arr[index] += arr.length;
        }

        for(int i=0; i<arr.length; i++){
            if(arr[i]/arr.length >= 2)
                res.add(i);
        }
        if(res.isEmpty())
            res.add(-1);
        return res;
    }
}
