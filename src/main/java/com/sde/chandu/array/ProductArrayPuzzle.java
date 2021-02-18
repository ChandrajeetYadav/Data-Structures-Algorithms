package com.sde.chandu.array;

import java.util.Arrays;

public class ProductArrayPuzzle {
    public static void main(String[] args) {
        int[] arr = {10, 3, 5, 6, 2};
        System.out.println("Original Array:");
        printArray(arr);
        System.out.println("Product array using brute approach:");
        productArrayBrute(arr);
        System.out.println("Product array using extra space:");
        productArrayUsingExtraSpace(arr);
        System.out.println("Product array using space efficient:");
        productArraySpaceEfficient(arr);
        System.out.println("Product array using log:");
        productArrayUsingLog(arr);
        System.out.println("Product array using pow:");
        productArrayUsingPow(arr);
        System.out.println("Product array using division:");
        productArrayUsingDivision(arr);
    }

    //Time Complexity: O(n^2)
    //Space Complexity: O(1)
    private static void productArrayBrute(int[] arr){
        if(arr==null || arr.length<=1)
            return;
        int prod;
        for(int i=0; i<arr.length; i++){
            prod = 1;
            for(int j=0; j<arr.length; j++){
                if(i==j)
                    continue;
                prod *= arr[j];
            }
            System.out.print(prod + "\t");
        }
        System.out.println();
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    private static void productArrayUsingExtraSpace(int[] arr){
        if(arr==null || arr.length<=1)
            return;
        int[] prod = new int[arr.length];
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];

        left[0] = right[arr.length-1] = 1;
        for(int i=1; i<arr.length; i++)
            left[i] = left[i-1] * arr[i-1];
        for(int i=arr.length-2; i>=0; i--)
            right[i] = right[i+1] * arr[i+1];
        for(int i=0; i<arr.length; i++)
            prod[i] = left[i] * right[i];
        printArray(prod);
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n) , Required for only storing end result
    private static void productArraySpaceEfficient(int[] arr){
        if(arr==null || arr.length==0)
            return;
        int[] prod = new int[arr.length];
        Arrays.fill(prod, 1);
        int temp = 1;
        for (int i=0; i<arr.length; i++){
            prod[i] = temp;
            temp *= arr[i];
        }
        temp = 1;
        for(int i=arr.length-1; i>=0; i--){
            prod[i] *= temp;
            temp *= arr[i];
        }
        printArray(prod);
    }

    //Time Complexity: O(n)
    //Space Complexity: O(1)
    //This will not work for every case
    private static void productArrayUsingLog(int[] arr){
        if(arr==null || arr.length<=1)
            return;
        final double EPS = 1e-9;
        double sum = 0;
        for(int i : arr)
            sum += Math.log10(i);
        for(int i : arr)
            System.out.print((int)(EPS +Math.pow(10, sum - Math.log10(i))) + "\t");
        System.out.println();
    }

    //Time Complexity: O(n)
    //Space Complexity: O(1)
    //This will not work for every case, {1 0}
    private static void productArrayUsingPow(int[] arr){
        if(arr==null || arr.length<=1)
            return;
        int prod = 1;
        for (int i : arr)
            prod *= i;
        for(int i : arr)
            System.out.print((int)(prod*Math.pow(i, -1))+ "\t");
        System.out.println();
    }

    //Time Complexity: O(n)
    //Space Complexity: O(1)
    private static void productArrayUsingDivision(int[] arr){
        if(arr==null || arr.length<=1)
            return;
        int zero=0, prod=1;
        for(int i : arr){
            if(i==0)
                zero++;
            else
                prod *= i;
        }
        if(zero == 0){
            for (int i : arr)
                System.out.print(prod/i + "\t");
        }else if(zero == 1){
            for(int i : arr){
                if(i == 0)
                    System.out.print(prod + "\t");
                else
                    System.out.print(0 + "\t");
            }
        }
        System.out.println();
    }

    private static void printArray(int[] arr){
        if(arr==null || arr.length==0)
            return;
        for(int i : arr)
            System.out.print(i + "\t");
        System.out.println();
    }
}
