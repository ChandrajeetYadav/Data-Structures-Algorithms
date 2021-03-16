package com.sde.chandu.searching;

import java.util.Arrays;
import java.util.HashMap;

public class CountMoreThanNbyKoccurrences {
    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 2, 1, 2, 3, 3};
        int k = 4; // O/p: 2, 3

        System.out.println("Required elements, brute: ");
        printElementsBrute(arr, k);
        System.out.println("Required elements, hashing: ");
        printElementsHashing(arr, k);
        System.out.println("Required elements, in kn time: ");
        printElements(arr, k);
        System.out.println("Required elements, sorting: ");
        printElementsSorting(arr, k);
    }

    //Time complexity: O(n^2)
    //Space complexity: O(1)
    //This will not work for all the test cases
    private static void printElementsBrute(int[] arr, int k){
        if(k < 2)
            return;
        int count;
        for (int i=0; i<arr.length-1; i++){
            count = 1;
            for (int j=i+1; j<arr.length; j++){
                if (arr[i] == arr[j])
                    count++;
            }
            if(count > arr.length/k)
                System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }

    //Time complexity: O(n log n) + O(n) => O(n log n)
    //Space complexity: O(1)
    private static void printElementsSorting(int[] arr, int k){
        if(k < 2)
            return;
        Arrays.sort(arr);
        int count;
        for (int i=0; i<arr.length; i++){
            count = 1;
            while (i<arr.length-1 && arr[i]==arr[i+1]){
                count++;
                i++;
            }
            if(count > arr.length/k)
                System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }

    //Time complexity: O(n)
    //Space complexity: O(n)
    private static void printElementsHashing(int[] arr, int k){
        if(k < 2)
            return;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr){
            if (map.containsKey(i))
                map.put(i, map.get(i) + 1);
            else
                map.put(i, 1);
        }
        map.forEach((m, n)->{
            if(n > arr.length/k)
                System.out.print(m + "\t");
        });
        System.out.println();
    }

    //Time complexity: O(n * k)
    //Space complexity: O(k)
    private static void printElements(int[] arr, int k){
        if(k<2)
            return;
        Node[] temp = new Node[k-1];
        for (int i=0; i<k-1; i++)
            temp[i] = new Node();
        for (int i=0; i<arr.length; i++){
            int j;
            for (j=0; j<k-1; j++){
                if (arr[i] == temp[j].num){
                    temp[j].count += 1;
                    break;
                }
            }
            if(j == k-1){
                int l;
                for (l=0; l<k-1; l++){
                    if (temp[l].count == 0){
                        temp[l].num = arr[i];
                        temp[l].count = 1;
                        break;
                    }
                }
                if(l == k-1){
                    for (l=0; l<k-1; l++)
                        temp[l].count -= 1;
                }
            }
        }
        for (int i=0, count; i<k-1; i++){
            count = 0;
            for (int j : arr){
                if (temp[i].num == j)
                    count++;
            }
            if (count > arr.length/k)
                System.out.print(temp[i].num + "\t");
        }
        System.out.println();
    }

    private static class Node{
        int num;
        int count;
    }
}
