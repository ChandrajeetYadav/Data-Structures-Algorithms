package com.sde.chandu.hashing;

import java.util.Arrays;

public class LinearProbing {
    public static void main(String[] args) {
        int[] hashTable = new int[10];
        int[] arr = {4, 14, 24, 44};

        System.out.println("Original hashTable: ");
        printArray(hashTable);
        linearProbing(hashTable, arr);
        System.out.println("Hashtable after linear probing: ");
        printArray(hashTable); // O/p: -1	-1	-1	-1	4	14	24	44	-1	-1
    }

    // Time complexity: O(n)
    // Space complexity: O(1)
    private static void linearProbing(int[] hash, int[] arr) {
        Arrays.fill(hash, -1);
        int size = hash.length;

        int index;
        for (int i = 0; i < arr.length; i++) {
            if (size == 0)
                return;
            index = arr[i] % hash.length;
            while (hash[index] != -1 && hash[index] != arr[i])
                index = (index + 1) % hash.length;
            if (hash[index] != arr[i]) {
                hash[index] = arr[i];
                size--;
            }
        }
    }

    private static void printArray(int[] arr) {
        for (int i : arr)
            System.out.print(i + "\t");
        System.out.println();
    }
}
