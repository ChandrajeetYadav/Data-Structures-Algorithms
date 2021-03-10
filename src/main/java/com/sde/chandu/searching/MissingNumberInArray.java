package com.sde.chandu.searching;

public class MissingNumberInArray {
    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 6, 3, 7, 8}; // O/p: 5
        System.out.println("Missing number, summation formula: " + getMissingNumberSummationFormula(arr));
        System.out.println("Missing number, xor: " + getMissingNumberXor(arr));
        System.out.println("Missing number, handling overflow of sum: " + getMissingNumber(arr));
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    private static int getMissingNumberSummationFormula(int[] arr){
        int n = arr.length;
        int sum = (n+1)*(n+2)/2;
        for(int i : arr)
            sum -= i;
        return sum;
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    private static int getMissingNumberXor(int[] arr){
        int n = arr.length;
        int x1 = arr[0];
        int x2 = 1;
        for (int i=1; i<n; i++)
            x1 ^= arr[i];
        for (int i=2; i<=n+1; i++)
            x2 ^= i;
        return x1 ^ x2;
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    private static int getMissingNumber(int[] arr){
        int n = arr.length;
        int sum = 1;
        for (int i=2; i<=n+1; i++){
            sum += i;
            sum -= arr[i-2];
        }
        return sum;
    }
}
