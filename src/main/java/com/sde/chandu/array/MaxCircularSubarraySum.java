package com.sde.chandu.array;

public class MaxCircularSubarraySum {
    public static void main(String[] args) {
        //int[] arr = { 11, 10, -20, 5, -3, -5, 8, -13, 10 }; // O/p: 31
        int[] arr = { -16, 21, 28, -6 }; // O/p: 49
        System.out.println("Max circular subarray sum, brute: " + maxCircularSumBrute(arr));
        System.out.println("Max circular subarray sum, without wrapping: " + maxCircularSumWithoutWrapping(arr));
        System.out.println("Max circular subarray sum, with wrapping: " + maxCircularSumWithWrapping(arr));
    }

    //Time complexity : O(n^2)
    //Space complexity : O(1)
    private static int maxCircularSumBrute(int[] arr){
        if(arr==null || arr.length==0)
            return Integer.MIN_VALUE;
        if(arr.length == 1)
            return arr[0];
        int res=Integer.MIN_VALUE, curMax, max=Integer.MIN_VALUE;
        int index;
        for(int i=0; i<arr.length; i++){
            curMax=0;
            for (int j=0; j<arr.length; j++){
                index = (i+j)%arr.length;
                curMax = curMax + arr[index];
                if(curMax > max)
                    max = curMax;
                if(curMax < 0)
                    curMax = 0;
            }
            res = Math.max(res, max);
        }
        return res;
    }

    //Time complexity : O(n)
    //Space complexity : O(1)
    private static int maxCircularSumWithoutWrapping(int[] arr){
        if(arr==null || arr.length==0)
            return Integer.MIN_VALUE;
        if(arr.length == 1)
            return arr[0];
        int min, max, curMin, curMax, sum;
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        curMin = curMax = sum = 0;
        for (int i=0; i<arr.length; i++){
            sum += arr[i];
            curMin += arr[i];
            min = Math.min(min, curMin);
            if(curMin > 0)
                curMin = 0;

            curMax += arr[i];
            max = Math.max(max, curMax);
            if(curMax < 0)
                curMax = 0;
        }
        if (min == sum)
            return max;
        return Math.max(max, sum - min);
    }

    //Time complexity : O(n)
    //Space complexity : O(1)
    //Below algorithm doesn’t work if all numbers are negative e.g., {-1, -2, -3}
    private static int maxCircularSumWithWrapping(int[] arr){
        if(arr==null || arr.length==0)
            return Integer.MIN_VALUE;
        if(arr.length == 1)
            return arr[0];
        int max =maxKadane(arr);
        int sum = 0;
        for (int i=0; i<arr.length; i++){
            sum += arr[i];
            arr[i] = -arr[i];
        }
        int maxWrap = sum + maxKadane(arr);
        return max>maxWrap ? max : maxWrap;
    }

    private static int maxKadane(int[] arr){
        int max, curMax;
        max = curMax = arr[0];
        for(int i=1; i<arr.length; i++){
            curMax = Math.max(arr[i], curMax + arr[i]);
            max = Math.max(max, curMax);
        }
        return max;
    }
}
