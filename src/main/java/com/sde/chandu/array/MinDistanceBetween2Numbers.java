package com.sde.chandu.array;

public class MinDistanceBetween2Numbers {
    public static void main(String[] args) {
        int[] arr1 = {1, 2};
        int[] arr2 = {3, 4, 5};
        int[] arr3 = {3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3};
        int[] arr4 = {2, 5, 3, 5, 4, 4, 2, 3};
        int[] arr5 = {86,39,90,67,84,66,62};

        System.out.println("Min dist arr1: " + minDistanceBrute(arr1, 1, 2));
        System.out.println("Min dist arr2: " + minDistanceBrute(arr2, 3, 5));
        System.out.println("Min dist arr3: " + minDistanceBrute(arr3, 3, 6));
        System.out.println("Min dist arr4: " + minDistanceBrute(arr4, 3, 2));
        System.out.println("Min dist arr5: " + minDistanceBrute(arr5, 3, 2));

        System.out.println("\nMin dist arr1: " + minDistanceEfficient(arr1, 1, 2));
        System.out.println("Min dist arr2: " + minDistanceEfficient(arr2, 3, 5));
        System.out.println("Min dist arr3: " + minDistanceEfficient(arr3, 3, 6));
        System.out.println("Min dist arr4: " + minDistanceEfficient(arr4, 3, 2));
        System.out.println("Min dist arr5: " + minDistanceEfficient(arr5, 3, 2));
    }

    //Time complexity : O(n)
    //Space complexity : O(1)
    private static int minDistanceEfficient(int[] arr, int x, int y){
        if(arr==null || arr.length==0)
            return -1;
        int dist = Integer.MAX_VALUE;
        int prev = -1;

        for (int i=0; i< arr.length; i++){
            if (arr[i]==x || arr[i]==y){
                if (prev != -1 && arr[prev] != arr[i])
                    dist = Math.min(dist, i-prev);
                prev = i;
            }
        }
        return dist==Integer.MAX_VALUE ? -1 : dist;
    }

    //Time complexity : O(n^2)
    //Space complexity : O(1)
    private static int minDistanceBrute(int[] arr, int x, int y){
        if(arr==null || arr.length==0)
            return -1;
        int dist = Integer.MAX_VALUE;
        for(int i=0; i< arr.length-1; i++){
            for (int j=i+1; j<arr.length; j++){
                if(arr[i]==x && arr[j]==y || arr[i]==y && arr[j]==x){
                    dist = Math.min(dist, j-i);
                }
            }
        }
        return dist==Integer.MAX_VALUE ? -1 : dist;
    }
}
