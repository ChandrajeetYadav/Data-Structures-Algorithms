package com.sde.chandu.array;

public class EquilibriumPoint {
    public static void main(String[] args) {
        int[] arr1 = {1}; // 0
        int[] arr2 = { 1, 2, 3 }; // -1
        int[] arr3 = { -7, 1, 5, 2, -4, 3, 0 }; // 3

        System.out.println("arr1, equilibrium point brute: " + getEquilibriumIndex(arr1));
        System.out.println("arr2, equilibrium point brute: " + getEquilibriumIndex(arr2));
        System.out.println("arr3, equilibrium point brute: " + getEquilibriumIndex(arr3));

        System.out.println("arr1, equilibrium point efficient1: " + getEquilibriumIndexEfficient1(arr1));
        System.out.println("arr2, equilibrium point efficient1: " + getEquilibriumIndexEfficient1(arr2));
        System.out.println("arr3, equilibrium point efficient1: " + getEquilibriumIndexEfficient1(arr3));

        System.out.println("arr1, equilibrium point extra space: " + getEquilibriumIndexUsingExtraSpace(arr1));
        System.out.println("arr2, equilibrium point extra space: " + getEquilibriumIndexUsingExtraSpace(arr2));
        System.out.println("arr3, equilibrium point extra space: " + getEquilibriumIndexUsingExtraSpace(arr3));

        int[] arr4 = {2, 3, 4, 1, 4, 5}; // 3
        System.out.println();
        System.out.println("arr4, equilibrium point 2 pointer: " + getEquilibriumIndexUsing2Pointer(arr4));

        System.out.println();
        System.out.println("arr4, equilibrium point efficient2: " + getEquilibriumIndexEfficient2(arr4));

    }

    //Time complexity : O(n^2)
    //Space complexity : O(1)
    private static int getEquilibriumIndex(int[] arr){
        if(arr==null || arr.length==0)
            return -1;
        else if(arr.length == 1)
            return 0;
        int leftSum, rightSum;
        for(int i=0; i<arr.length; i++){
            leftSum = rightSum = 0;
            for(int j=0; j<i; j++)
                leftSum += arr[j];
            for(int j=i+1; j<arr.length; j++)
                rightSum += arr[j];
            if(leftSum == rightSum)
                return i;
        }
        return -1;
    }

    //Time complexity : O(n)
    //Space complexity : O(1)
    private static int getEquilibriumIndexEfficient1(int[] arr){
        if(arr==null || arr.length==0)
            return -1;
        if(arr.length == 1)
            return 0;
        int sum = 0;
        for(int i : arr)
            sum += i;
        int leftSum = 0;
        for(int i=0; i<arr.length; i++){
            sum -= arr[i];
            if(sum == leftSum)
                return i;
            leftSum += arr[i];
        }
        return -1;
    }

    //Time complexity : O(n)
    //Space complexity : O(n)
    private static int getEquilibriumIndexUsingExtraSpace(int[] arr){
        if(arr==null || arr.length==0)
            return -1;
        if(arr.length == 1)
            return 0;
        int[] prefixSum = new int[arr.length];
        int[] suffixSum = new int[arr.length];

        prefixSum[0] = arr[0];
        for (int i=1; i<arr.length; i++)
            prefixSum[i] = arr[i] + prefixSum[i-1];

        suffixSum[arr.length-1] = arr[arr.length-1];
        for(int i=arr.length-2; i>=0; i--)
            suffixSum[i] = arr[i] + suffixSum[i+1];

        for (int i=1; i<arr.length-1; i++)
            if(prefixSum[i] == suffixSum[i])
                return i;

        return -1;
    }

    //Time complexity : O(n)
    //Space complexity : O(1)
    //This solution is only applicable if the array contains only positive elements.
    private static int getEquilibriumIndexUsing2Pointer(int[] arr){
        if(arr==null || arr.length==0)
            return -1;
        if(arr.length == 1)
            return 0;
        int leftSum=0, rightSum=0, i, j;
        for(i=0, j=arr.length-1; i<j; i++, j--){
            leftSum += arr[i];
            rightSum += arr[j];

            while (leftSum<rightSum && i<j){
                i++;
                leftSum += arr[i];
            }

            while (rightSum<leftSum && i<j){
                j--;
                rightSum += arr[j];
            }
        }
        if(leftSum==rightSum && i==j)
            return i;
        return -1;
    }

    //Time complexity : O(n)
    //Space complexity : O(1)
    //This solution is only applicable if the array contains only positive elements.
    private static int getEquilibriumIndexEfficient2(int[] arr){
        if(arr==null || arr.length==0)
            return -1;
        if(arr.length == 1)
            return 0;
        int start=0, end=arr.length-1, leftSum=0, rightSum=0;

        for (int i=0; i<arr.length; i++){
            if(start==end && leftSum==rightSum)
                return start;
            else if(start == end)
                return -1;
            if (leftSum > rightSum){
                rightSum += arr[end];
                end--;
            } else if(leftSum < rightSum){
                leftSum += arr[start];
                start++;
            }else {
                rightSum += arr[end];
                end--;
            }
        }
        return -1;
    }
}
