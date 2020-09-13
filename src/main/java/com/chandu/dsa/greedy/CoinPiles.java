package com.chandu.dsa.greedy;

import java.util.Arrays;

public class CoinPiles {
    public static void main(String[] args) {
        int[] coins = { 1, 5, 1, 2, 5, 1 };
        int k = 3;
        System.out.println("Minimum number of coins to be removed: " + findMinCoinsToRemove(coins, k));
    }

    //Time Complexity: O(n ^ 2)
    //Space Complexity: O(1)
    private static int findMinCoinsToRemove(int[] coins, int k){
        Arrays.sort(coins);
        int temp, noOfCoins=Integer.MAX_VALUE, prevCoins = 0;

        for(int i=0; i<coins.length-1; i++){
            temp = prevCoins;
            prevCoins += coins[i];
            for(int j=i+1; j<coins.length; j++){
                if(coins[j] - coins[i] - k > 0)
                    temp += coins[j] -coins[i] - k;
            }
            noOfCoins = Math.min(temp, noOfCoins);
        }
        return noOfCoins;
    }
}
