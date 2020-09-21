package com.chandu.dsa.greedy;

public class GeekCollectTheBalls {
    public static void main(String[] args) {
        int[] arr1 = {1, 4, 5, 6, 8};
        int[] arr2 = {2, 3, 4, 6, 9};
        System.out.println("Maximum number of balls is : " + getMaxNoOfBalls(arr1, arr2));

        int[] arr3 = {1, 4, 5, 8, 8, 8};
        int[] arr4 = {2, 8, 9, 9, 9};
        System.out.println("Maximum number of balls is : " + getMaxNoOfBalls(arr3, arr4));
    }

    //Time Complexity : O(m + n)
    //Space Complexity : O(1)
    // m, n = length of arrays
    private static int getMaxNoOfBalls(int[] arr1, int[] arr2){
        int res = 0, first = 0, second = 0, temp, i = 0, j = 0;
        while(i<arr1.length && j<arr2.length){
            if (arr1[i] < arr2[j])
                first += arr1[i++];
            else if (arr1[i] > arr2[j])
                second += arr2[j++];
            else {
                temp = arr1[i];
                while (i< arr1.length && arr1[i]==temp)
                    first += arr1[i++];
                while (j<arr2.length && arr2[j]==temp)
                    second += arr2[j++];
                res += Math.max(first, second);
                first = second = 0;
            }
        }
        while (i< arr1.length)
            first += arr1[i++];
        while (j < arr2.length)
            second += arr2[j++];
        res += Math.max(first, second);
        return res;
    }
}
