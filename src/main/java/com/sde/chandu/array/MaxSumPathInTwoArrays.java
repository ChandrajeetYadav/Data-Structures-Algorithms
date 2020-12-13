package com.sde.chandu.array;

public class MaxSumPathInTwoArrays {

    public static void main(String[] args) {
        int[] arr1 = {2, 3, 7, 10, 12, 15, 30, 34};
        int[] arr2 = {1, 5, 7, 8, 10, 15, 16, 19};
        System.out.println("Max path sum is: " + getMaxPathSum(arr1, arr2));
    }

    //Time Complexity: O(m + n)
    //Space Complexity: O(1)
    private static int getMaxPathSum(int[] arr1, int[] arr2){
        int result = 0;
        int i=0, j=0, sum1=0, sum2=0, temp;
        while(i< arr1.length && j< arr2.length){
            if(arr1[i] < arr2[j])
                sum1 += arr1[i++];
            else if (arr1[i] > arr2[j])
                sum2 += arr2[j++];
            else {
                result += Math.max(sum1, sum2);
                sum1 = sum2 = 0;
                temp = i;
                while (i< arr1.length && arr1[i]==arr2[j])
                    sum1 += arr1[i++];
                while (j< arr2.length && arr2[j]==arr1[temp])
                    sum2 += arr2[j++];
                result += Math.max(sum1, sum2);
                sum1 = sum2 = 0;
            }
        }
        while (i < arr1.length)
            sum1 += arr1[i++];
        while (j < arr2.length)
            sum2 += arr2[j++];
        result += Math.max(sum1, sum2);
        return result;
    }
}
