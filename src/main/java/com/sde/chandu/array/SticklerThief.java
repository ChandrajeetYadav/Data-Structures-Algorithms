package com.sde.chandu.array;

public class SticklerThief {
    public static void main(String[] args) {
        int[] arr = {6, 7, 1, 3, 8, 2, 4}; // O/p: 19

        System.out.println("Max loot using DP : " + maxLootUsingDP(arr));

        System.out.println("Max loot without extra space : " + maxLootWithoutExtraSpace(arr));
    }

    //Time complexity: O(n)
    //Space complexity: O(n)
    private static int maxLootUsingDP(int[] arr){
        if(arr==null || arr.length==0)
            return -1;
        else if(arr.length==1)
            return arr[0];
        else if(arr.length==2)
            return Math.max(arr[0], arr[1]);
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);
        for(int i=2; i<arr.length; i++)
            dp[i] = Math.max(dp[i-2]+arr[i], dp[i-1]);
        return dp[arr.length-1];
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    private static int maxLootWithoutExtraSpace(int[] arr){
        if(arr==null || arr.length==0)
            return -1;
        if(arr.length == 1)
            return arr[0];
        int val1 = arr[0], val2 = Math.max(arr[0], arr[1]), maxVal = 0;
        if(arr.length == 2)
            return val2;
        for(int i=2; i<arr.length; i++){
            maxVal = Math.max(val1 + arr[i], val2);
            val1 = val2;
            val2 = maxVal;
        }
        return maxVal;
    }
}
