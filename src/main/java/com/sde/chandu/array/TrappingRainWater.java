package com.sde.chandu.array;

public class TrappingRainWater {
    public static void main(String[] args) {
        int[] arr = {3, 0, 2, 0, 4}; // O/p: 7
        System.out.println("Max water stored, Brute: " + maxWaterBrute(arr));
        System.out.println("Max water stored, using extra space: " + maxWaterUsingExtraSpace(arr));
        System.out.println("Max water stored, using 2 pointer: " + maxWaterUsing2Pointer(arr));
    }

    //Time complexity: O(n^2)
    //Space complexity: O(1)
    private static int maxWaterBrute(int[] arr){
        if(arr==null || arr.length==0)
            return 0;
        int res=0, leftMax, rightMax;
        for(int i=1; i<arr.length-1; i++){
            leftMax=arr[i];
            for(int j=0; j<i; j++)
                leftMax = Math.max(leftMax, arr[j]);
            rightMax=arr[i];
            for(int j=i+1; j<arr.length; j++)
                rightMax = Math.max(rightMax, arr[j]);
            res += Math.min(leftMax, rightMax) - arr[i];
        }
        return res;
    }

    //Time complexity: O(n)
    //Space complexity: O(n)
    private static int maxWaterUsingExtraSpace(int[] arr){
        if(arr==null || arr.length==0)
            return 0;
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];
        int res = 0;
        left[0] = arr[0];
        for(int i=1; i<arr.length; i++)
            left[i] = Math.max(arr[i], left[i-1]);
        right[arr.length-1] = arr[arr.length-1];
        for(int i=arr.length-2; i>=0; i--)
            right[i] = Math.max(arr[i], right[i+1]);
        for(int i=0; i<arr.length; i++)
            res += Math.min(left[i], right[i]) - arr[i];
        return res;
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    private static int maxWaterUsing2Pointer(int[] arr){
        if(arr==null || arr.length==0)
            return 0;
        int low=0, high=arr.length-1, leftMax=0, rightMax=0;
        int res = 0;
        while(low <= high){
            if(arr[low] < arr[high]){
                if(leftMax < arr[low])
                    leftMax = arr[low];
                else
                    res += leftMax - arr[low];
                low++;
            }else{
                if(rightMax < arr[high])
                    rightMax = arr[high];
                else
                    res += rightMax - arr[high];
                high--;
            }
        }
        return res;
    }
}
