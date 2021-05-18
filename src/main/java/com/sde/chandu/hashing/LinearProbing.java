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
    private static void linearProbing(int[] hashTable, int[] arr) {
        Arrays.fill(hashTable, -1);
        int hash, tempHash;
        for (int i : arr) {
            hash = i % hashTable.length;
            if (hashTable[hash] == -1)
                hashTable[hash] = i;
            else {
                for (int j = 0; j < hashTable.length; j++) {
                    tempHash = (hash + j) % hashTable.length;
                    if (hashTable[tempHash] == -1) {
                        hashTable[tempHash] = i;
                        break;
                    }
                }
            }
        }
    }

    private static void printArray(int[] arr) {
        for (int i : arr)
            System.out.print(i + "\t");
        System.out.println();
    }
}
