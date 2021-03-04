package com.sde.chandu.array;

public class MaxSumInTheConfiguration {
    public static void main(String[] args) {
        int[] arr = {8,3,1,2}; // O/p: 29
        System.out.println("Max sum, brute: " + maxSumBrute(arr));
        System.out.println("Max sum, efficient: " + maxSumEfficient(arr));

        int[] arr2 = {2, 3, 4, 1};
        System.out.println("Max sum, sorted or rotatedSorted: " + maxSumSorted(arr2));
    }

    //Time complexity: O(n^2)
    //Space complexity: O(1)
    private static int maxSumBrute(int[] arr){
        if(arr==null || arr.length==0)
            return Integer.MIN_VALUE;
        int res = Integer.MIN_VALUE, curSum, index;
        for(int i=0; i<arr.length; i++){
            curSum = 0;
            for(int j=0; j<arr.length; j++){
                index = (i+j) % arr.length;
                curSum += j * arr[index];
            }
            res = Math.max(res, curSum);
        }
        return res;
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    private static int maxSumEfficient(int[] arr){
        if(arr==null || arr.length==0)
            return Integer.MIN_VALUE;
        int curVal=0, totalSum=0;
        for (int i=0; i<arr.length; i++){
            totalSum += arr[i];
            curVal += i * arr[i];
        }
        int res=curVal, nextVal;
        for (int i=1; i<arr.length; i++){
            curVal = curVal + totalSum - arr.length*arr[arr.length - i];
            res = Math.max(res, curVal);
        }
        return res;
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    //Note: Here, array should be sorted or can be sorted by rotation
    private static int maxSumSorted(int[] arr){
        if(arr==null || arr.length==0)
            return Integer.MIN_VALUE;
        int pivot = findPivot(arr);
        int index = arr.length - pivot -1;
        int sum = 0;
        for (int i=0; i< arr.length; i++)
            sum += ((i + index)%arr.length) * arr[i];
        return sum;
    }

    private static int findPivot(int[] arr){
        for (int i=0; i<arr.length; i++){
            if(arr[i] > arr[(i+1)%arr.length])
                return i;
        }
        return 0;
    }
}
