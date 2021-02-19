package com.sde.chandu.array;

//Largest Sum Contiguous Sub array
public class KadanesAlgorithm {
    public static void main(String[] args) {
        int[] arr1 = {-2, -3, 4, -1, -2, 1, 5, -3}; // O/p: 7
        int[] arr2 = {-1,-2,-3,-4}; // O/p: -1

        System.out.println("arr1, Max sum, method1: " + maxSubArraySum1(arr1));
        System.out.println("arr2, Max sum, method1: " + maxSubArraySum1(arr2));
        System.out.println();
        System.out.println("arr1, Max sum, method1 optimized: " + maxSubArraySum1Optimized(arr1));
        System.out.println("arr2, Max sum, method1 optimized: " + maxSubArraySum1Optimized(arr2));
        System.out.println();
        System.out.println("arr1, Max sum, brute: " + maxSubArraySumBrute(arr1));
        System.out.println("arr2, Max sum, brute: " + maxSubArraySumBrute(arr2));
        System.out.println();
        System.out.println("arr1, Max sum, DP: " + maxSubArraySumDP(arr1));
        System.out.println("arr2, Max sum, DP: " + maxSubArraySumDP(arr2));
        System.out.println();
        System.out.println("arr1, Max sum, with index: " + maxSubArraySumWithIndex(arr1));
        System.out.println("arr2, Max sum, with index: " + maxSubArraySumWithIndex(arr2));
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    private static int maxSubArraySum1(int[] arr){
        if(arr==null || arr.length==0)
            return Integer.MIN_VALUE;
        int maxSoFar=Integer.MIN_VALUE, maxEndingHere=0;

        for(int i : arr){
            maxEndingHere += i;
            if(maxSoFar < maxEndingHere)
                maxSoFar = maxEndingHere;
            if(maxEndingHere < 0)
                maxEndingHere = 0;
        }
        return maxSoFar;
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    //This method will not work if all the numbers are negative
    private static int maxSubArraySum1Optimized(int[] arr){
        if(arr==null || arr.length==0)
            return Integer.MIN_VALUE;
        int maxSoFar=0, maxEndingHere=0;
        for(int i : arr){
            maxEndingHere += i;
            if(maxEndingHere < 0)
                maxEndingHere = 0;
            else if(maxSoFar < maxEndingHere)
                maxSoFar = maxEndingHere;
        }
        return maxSoFar;
    }

    //Time complexity: O(n^2)
    //Space complexity: O(1)
    private static int maxSubArraySumBrute(int[] arr){
        if(arr==null || arr.length==0)
            return Integer.MIN_VALUE;
        int max=arr[0], sum;
        for(int i=0; i<arr.length; i++){
            sum = 0;
            for(int j=i; j<arr.length; j++){
                sum += arr[j];
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    private static int maxSubArraySumDP(int[] arr){
        if(arr==null || arr.length==0)
            return Integer.MIN_VALUE;
        int currMax=arr[0], max=arr[0];
        for(int i=1; i<arr.length; i++){
            currMax = Math.max(arr[i], currMax+arr[i]);
            max = Math.max(max, currMax);
        }
        return max;
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    private static int maxSubArraySumWithIndex(int[] arr){
        if(arr==null || arr.length==0)
            return Integer.MIN_VALUE;
        int max=Integer.MIN_VALUE, currMax=0;
        int start=0, end=0, s=0;
        for(int i=0; i<arr.length; i++){
            currMax += arr[i];
            if(max < currMax){
                max = currMax;
                start = s;
                end = i;
            }
            if(currMax < 0){
                currMax = 0;
                s = i + 1;
            }
        }
        System.out.println("Start index=" + start +", end index="+end);
        return max;
    }
}
