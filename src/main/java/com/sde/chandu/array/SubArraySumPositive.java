package com.sde.chandu.array;

public class SubArraySumPositive {
    public static void main(String[] args) {
        int sum = 33;
        int[] arr = {1, 4, 20, 3, 10, 5}; // O/p : 3 and 5
        System.out.println("Sub array sum, brute");
        subArraySumBrute(arr, sum);
        System.out.println("Sub array sum, efficient");
        subArraySumEfficient(arr, sum);
    }

    //Time complexity : O(n^2)
    //Space complexity : O(1)
    private static void subArraySumBrute(int[] arr, int targetSum){
        if(arr==null || arr.length==0)
            return;
        int currSum;
        for(int i=0; i<arr.length; i++){
            currSum = arr[i];
            for(int j=i+1; j<=arr.length; j++){
                if(currSum == targetSum){
                    System.out.println("Sum found between " + (i+1) + " and " + j + " position");
                    return;
                }
                else if(currSum>targetSum || j== arr.length)
                    break;
                currSum += arr[j];
            }
        }
        System.out.println("No sub array found");
    }

    //Time complexity : O(n)
    //Space complexity : O(1)
    private static void subArraySumEfficient(int[] arr, int targetSum){
        if(arr==null || arr.length==0)
            return;
        int currSum = arr[0], start=0;
        for(int i=1; i<=arr.length; i++){
            while(currSum>targetSum && start<i-1){
                currSum -= arr[start];
                start++;
            }
            if(currSum == targetSum){
                System.out.println("Sum found between " + (start+1) + " and " + i + " position");
                return;
            }
            if(i < arr.length)
                currSum += arr[i];
        }
        System.out.println("No sub array found");
    }
}
