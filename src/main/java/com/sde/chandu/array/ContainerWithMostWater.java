package com.sde.chandu.array;

public class ContainerWithMostWater {
    public static void main(String[] args) {
        int[] arr1 = { 1, 5, 4, 3 }; // O/p: 6
        int[] arr2 = { 3, 1, 2, 4, 5 }; // O/p: 12

        System.out.println("arr1, max area brute: " + maxAreaBrute(arr1));
        System.out.println("arr1, max area brute: " + maxAreaBrute(arr2));
        System.out.println();
        System.out.println("arr1, max area efficient: " + maxAreaEfficient(arr1));
        System.out.println("arr1, max area efficient: " + maxAreaEfficient(arr2));
    }

    //Time complexity : O(n^2)
    //Space complexity : O(1)
    private static int maxAreaBrute(int[] arr){
        if(arr==null || arr.length==0)
            return 0;
        int maxArea = 0;
        for(int i=0; i<arr.length; i++){
            for(int j=i+1; j<arr.length; j++)
                maxArea = Math.max(maxArea, Math.min(arr[i], arr[j])*(j-i));
        }
        return maxArea;
    }

    //Time complexity : O(n)
    //Space complexity : O(1)
    private static int maxAreaEfficient(int[] arr){
        if(arr==null || arr.length==0)
            return 0;
        int maxArea = 0;
        int low=0, high=arr.length-1;
        while (low < high){
            maxArea = Math.max(maxArea, Math.min(arr[low], arr[high]) * (high - low));
            if(arr[low] < arr[high])
                low++;
            else
                high--;
        }
        return maxArea;
    }
}
