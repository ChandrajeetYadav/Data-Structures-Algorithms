package com.sde.chandu.array;

public class Sort0And1inArray {
    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 1, 0, 0, 1, 1, 1, 0};
        sortUsingCounting(arr);
        print(arr);

        int[] arr1 = {0, 1, 0, 1, 1, 1};
        sortUsingTwoPointer(arr1);
        print(arr1);
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    private static void sortUsingCounting(int[] arr){
        if(arr==null || arr.length<=1)
            return;
        int noOfZero = 0;
        for (int i=0; i<arr.length; i++){
            if(arr[i] == 0)
                noOfZero++;
        }
        for (int i=0; i<noOfZero; i++)
            arr[i] = 0;
        for (int i=noOfZero; i<arr.length; i++)
            arr[i] = 1;
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    private static void sortUsingTwoPointer(int[] arr){
        if(arr==null || arr.length<=1)
            return;
        int left = 0, right = arr.length-1;
        while (left < right){
            while (arr[left]==0 && left<right)
                left++;
            while (arr[right]==1 && left<right)
                right--;
            if(left < right){
                arr[left] = 0;
                arr[right] = 1;
                left++;
                right--;
            }
        }
    }

    private static void print(int[] arr){
        if(arr==null || arr.length==0)
            return;
        for (int i : arr)
            System.out.print(i + "\t");
        System.out.println();
    }
}
