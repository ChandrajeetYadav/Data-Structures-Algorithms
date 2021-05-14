package com.sde.chandu.hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PositiveNegativePair {
    public static void main(String[] args) {
        int[] arr = {1, 3, 6, -2, -1, -3, 2, 7}; // O/p: -1 1 -3 3 -2 2
        System.out.println("Positive negative pairs, brute: ");
        findPairsBrute(arr);

        System.out.println("Positive negative pairs, hashing: ");
        findPairHashing(arr);

        System.out.println("Positive negative pairs, sorting: ");
        findPairSorting(arr);
    }

    //Time complexity: O(n^2)
    //Space complexity: O(1)
    private static void findPairsBrute(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("No such pairs exist");
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (Math.abs(arr[i]) == Math.abs(arr[j]))
                    System.out.print(-(Math.abs(arr[i])) + "," + Math.abs(arr[i]) + "\t");
            }
        }
        System.out.println();
    }

    //Time complexity: O(n log n)
    //Space complexity: O(n)
    //Note: This won't preserve the order of elements
    private static void findPairSorting(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("No such pairs exist");
            return;
        }
        List<Integer> list = new ArrayList();
        for (int i : arr)
            list.add(i);
        list.sort((Integer a, Integer b) -> Math.abs(a) - Math.abs(b));
        for (int i = 0; i < list.size() - 1; ) {
            if (Math.abs(list.get(i)) == Math.abs(list.get(i + 1))) {
                System.out.print(list.get(i) + "," + list.get(i + 1) + "\t");
                i += 2;
            } else
                i++;
        }
        System.out.println();
    }

    //Time complexity: O(n)
    //Space complexity: O(n)
    private static void findPairHashing(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("No such pairs exist");
            return;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (map.containsKey(-i))
                System.out.print(-Math.abs(i) + "," + Math.abs(i) + "\t");
            else
                map.put(i, i);
        }
        System.out.println();
    }
}
