package com.sde.chandu.hashing;

import java.util.*;

public class SortAccordingToAnotherArray {
    public static void main(String[] args) {
        int[] a1 = {2, 1, 2, 5, 7, 1, 9, 3, 6, 8, 8};
        int[] a2 = {2, 1, 8, 3};

        System.out.println("Original array: ");
        printArray(a1);
        sortAccordingToAnotherUsingSortingAndBinarySearch(a1, a2);
        System.out.println("Array after sorting, using sorting and binary search");
        printArray(a1);

        int[] a3 = {9, 9, 9, 9, 1, 1, 1, 1, 2, 2, 2, 2, 3, 4, 4, 4, 3, 9, 8, 10};
        int[] a4 = {8, 2, 1, 3};
        System.out.println("Original array: ");
        printArray(a3);
        sortAccordingToAnotherUsingHashing(a3, a4);
        System.out.println("Array after sorting, using hashing");
        printArray(a3);
    }

    // m = length of a1
    // n = length of a2
    // p = number of elements remained after considering elements of a2
    //Time complexity: O(m + n + p log p)
    //Space complexity: O(m)
    private static void sortAccordingToAnotherUsingHashing(int[] a1, int[] a2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : a1) // O(m)
            map.put(i, map.getOrDefault(i, 0) + 1);
        int ind = 0;
        for (int i : a2) { // O(n)
            if (map.get(i) != null) {
                for (int j = 0; j < map.get(i); j++)
                    a1[ind++] = i;
                map.remove(i);
            }
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) -> a.getKey() - b.getKey()); // O(p log p)
        for (Map.Entry<Integer, Integer> entry : list) {
            for (int i = 0; i < entry.getValue(); i++)
                a1[ind++] = entry.getKey();
        }
    }

    // m = length of a1
    // n = length of a2
    //Time complexity: O(m log m + n log m)
    //Space complexity: O(m)
    private static void sortAccordingToAnotherUsingSortingAndBinarySearch(int[] a1, int[] a2) {
        int[] temp = new int[a1.length];
        boolean[] visited = new boolean[a1.length];
        for (int i = 0; i < a1.length; i++)
            temp[i] = a1[i];
        Arrays.sort(temp); // O(m log m)
        int ind = 0, pos;
        for (int i = 0; i < a2.length; i++) { // O(n)
            pos = binarySearch(temp, a2[i]); // O(log m)
            if (pos == -1)
                continue;
            for (int j = pos; j < a1.length && temp[j] == a2[i]; j++) {
                a1[ind++] = temp[j];
                visited[j] = true;
            }
        }
        for (int i = 0; i < a1.length; i++) {
            if (!visited[i])
                a1[ind++] = temp[i];
        }
    }

    private static int binarySearch(int[] arr, int num) {
        int low = 0, high = arr.length - 1;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if ((mid == 0 || num > arr[mid - 1]) && arr[mid] == num)
                return mid;
            else if (num > arr[mid])
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }


    private static void printArray(int[] arr) {
        for (int i : arr)
            System.out.print(i + "\t");
        System.out.println();
    }
}
