package com.sde.chandu.sorting;

import java.util.Arrays;
import java.util.HashSet;

public class FindTripletsWithZeroSum {
    public static void main(String[] args) {
        int[] arr = {0, -1, 2, -3, 1};
        System.out.println("Triplets, brute");
        findTripletBrute(arr);
        System.out.println("Triplets, hashing");
        findTripletHashing(arr);
        System.out.println("Triplets, sorting");
        findTripletSorting(arr);
    }

    //Time complexity: O(n^3)
    //Space complexity: O(1)
    private static void findTripletBrute(int[] arr){
        if (arr==null)
            return;
        boolean isTripletFound = false;
        for (int i=0; i<arr.length-2; i++){
            for (int j=i+1; j<arr.length-1; j++){
                for (int k=j+1; k<arr.length; k++){
                    if (arr[i]+arr[j]+arr[k] == 0){
                        isTripletFound = true;
                        System.out.print(arr[i]+","+arr[j]+","+arr[k]+"\t");
                    }
                }
            }
        }
        if (!isTripletFound)
            System.out.println("No triplets found");
        System.out.println();
    }

    //Time complexity: O(n^2)
    //Space complexity: O(n)
    private static void findTripletHashing(int[] arr){
        if(arr==null)
            return;
        boolean isTripletPresent = false;
        int temp;
        HashSet<Integer> set = new HashSet<>();
        for (int i=0; i<arr.length-1; i++){
            set.clear();
            for (int j=i+1; j<arr.length; j++){
                temp = -(arr[i] + arr[j]);
                if (set.contains(temp)){
                    isTripletPresent = true;
                    System.out.print(arr[i]+","+arr[j]+","+temp+"\t");
                }else
                    set.add(arr[j]);
            }
        }
        if (!isTripletPresent)
            System.out.println("No triplets found");
        System.out.println();
    }

    //Time complexity: O(n^2)
    //Space complexity: O(1)
    private static void findTripletSorting(int[] arr){
        if (arr == null)
            return;
        boolean isTripletPresent = false;
        Arrays.sort(arr);
        int low, high, sum;
        for (int i=0; i<arr.length-2; i++){
            low = i+1;
            high = arr.length-1;
            while (low < high){
                sum = arr[i]+arr[low]+arr[high];
                if (sum == 0){
                    isTripletPresent = true;
                    System.out.print(arr[i]+","+arr[low]+","+arr[high]+"\t");
                    low++;
                    high--;
                }else if(sum < 0)
                    low++;
                else
                    high--;
            }
        }
        if (!isTripletPresent)
            System.out.println("No triplets found");
        System.out.println();
    }
}
