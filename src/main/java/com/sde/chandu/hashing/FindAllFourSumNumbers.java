package com.sde.chandu.hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindAllFourSumNumbers {
    public static void main(String[] args) {
        int[] arr1 = {10, 20, 30, 40, 1, 2};
        int sum1 = 91;
        System.out.println("All Four sum numbers arr1, brute:");
        findAllFourSumNumbersBrute(arr1, sum1);
        System.out.println("Four sum numbers arr1, hashing:");
        findFourSumNumbersUsingHashing(arr1, sum1);

        int[] arr2 = {0, 0, 2, 1, 1};
        int sum2 = 3;
        System.out.println("All Four sum numbers arr2, sorting:");
        findAllFourSumNumbersUsingSorting(arr2, sum2);
    }

    // Note: This will not give unique quadruple
    // Time complexity: O(n^4)
    // Space complexity: O(1)
    private static void findAllFourSumNumbersBrute(int[] arr, int sum) {
        if (arr == null || arr.length < 4)
            return;
        for (int i = 0; i < arr.length - 3; i++) {
            for (int j = i + 1; j < arr.length - 2; j++) {
                for (int k = j + 1; k < arr.length - 1; k++) {
                    for (int l = k + 1; l < arr.length; l++) {
                        if (arr[i] + arr[j] + arr[k] + arr[l] == sum)
                            System.out.println(arr[i] + "\t" + arr[j] + "\t" + arr[k] + "\t" + arr[l]);
                    }
                }
            }
        }
    }

    // Time complexity: O(n^3)
    // Space complexity: O(1)
    //Note: This will remove duplicate elements in the pair
    private static void findAllFourSumNumbersUsingSorting(int[] arr, int sum) {
        if (arr == null || arr.length < 4)
            return;
        Arrays.sort(arr);
        int l, r, s;
        for (int i = 0; i < arr.length - 3; i++) {
            if (i > 0 && arr[i] == arr[i - 1])
                continue;
            for (int j = i + 1; j < arr.length - 2; j++) {
                if (j > i + 1 && arr[j] == arr[j - 1])
                    continue;
                l = j + 1;
                r = arr.length - 1;
                while (l < r) {
                    s = arr[i] + arr[j] + arr[l] + arr[r];
                    if (s == sum) {
                        System.out.println(arr[i] + "\t" + arr[j] + "\t" + arr[l] + "\t" + arr[r]);
                        do {
                            l++;
                        } while (arr[l] == arr[l - 1] && l < r);
                        do {
                            r--;
                        } while (arr[r] == arr[r + 1] && l < r);
                    } else if (s < sum)
                        l++;
                    else
                        r--;
                }
            }
        }
    }

    // Time complexity: O(n^2)
    // Space complexity: O(n^2)
    //Note: This will remove duplicate elements in the pair and return any one combination
    private static void findFourSumNumbersUsingHashing(int[] arr, int sum) {
        if (arr == null || arr.length < 4)
            return;
        Map<Integer, Pair> map = new HashMap<>();
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++)
                map.put(arr[i] + arr[j], new Pair(i, j));
        }

        for (int i = 0, s; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                s = arr[i] + arr[j];
                if (map.containsKey(sum - s)) {
                    Pair p = map.get(sum - s);
                    if (p.first != i && p.first != j && p.second != i && p.second != j) {
                        System.out.println(arr[i] + "\t" + arr[j] + "\t" + arr[p.first] + "\t" + arr[p.second]);
                        return;
                    }
                }
            }
        }
    }

    private static class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
