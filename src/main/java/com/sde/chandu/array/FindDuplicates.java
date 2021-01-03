package com.sde.chandu.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDuplicates {
    public static void main(String[] args) {
        int[] arr1 = {2, 10, 10, 100, 2, 10, 11, 2, 11, 2};
        int[] arr2 = {5, 40, 1, 40, 100000, 1, 5, 1};
        int[] arr3 = {1, 2, 3};

        findDuplicatesBrute(arr1);
        findDuplicatesBrute(arr2);
        findDuplicatesBrute(arr3);

        System.out.println();
        findDuplicatesEfficient(arr1);
        findDuplicatesEfficient(arr2);
        findDuplicatesEfficient(arr3);
    }

    //Time Complexity: O(n^2)
    //Space Complexity: O(n)
    private static void findDuplicatesBrute(int[] arr) {
        if (arr == null || arr.length < 2) {
            System.out.println("No duplicates found in the array");
            return;
        }
        boolean isDupPresent = false;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]){
                    if (list.contains(arr[i]))
                        break;
                    else {
                        list.add(arr[i]);
                        isDupPresent = true;
                    }
                }
            }
        }
        if (isDupPresent)
            list.forEach(n-> System.out.print(n + "\t"));
        else
            System.out.print("No duplicates found in the array");
        System.out.println();
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    private static void findDuplicatesEfficient(int[] arr){
        if (arr==null || arr.length<2){
            System.out.println("No duplicates found in the array");
            return;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr){
            if (map.containsKey(i))
                map.put(i, map.get(i)+1);
            else
                map.put(i, 1);
        }
        boolean isDupPresent = false;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            if (entry.getValue() > 1){
                System.out.print(entry.getKey() + "\t");
                isDupPresent = true;
            }
        }
        if (!isDupPresent)
            System.out.println("No duplicates found in the array");
        System.out.println();
    }
}
