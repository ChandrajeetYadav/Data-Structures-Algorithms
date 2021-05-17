package com.sde.chandu.hashing;

import java.util.Arrays;

public class QuadraticProbing {
    public static void main(String[] args) {
        int[] hashTable = new int[11];
        Arrays.fill(hashTable, -1);
        int[] arr = {21, 10, 32, 43};

        System.out.println("Original hashTable: ");
        printArray(hashTable);
        quadraticProbing(hashTable, arr);
        System.out.println("Hashtable after quadratic probing: ");
        printArray(hashTable);
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    private static void quadraticProbing(int[] hashTable, int[] arr) {
        int hash, tempHash;
        for (int i : arr) {
            hash = i % hashTable.length;
            if (hashTable[hash] == -1)
                hashTable[hash] = i;
            else {
                for (int j = 0; j < arr.length; j++) {
                    tempHash = (hash + j * j) % hashTable.length;
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
