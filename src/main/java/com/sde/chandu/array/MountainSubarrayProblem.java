package com.sde.chandu.array;

public class MountainSubarrayProblem {
    public static void main(String[] args) {
        int[] arr = {2, 3, 2, 4, 4, 6, 3, 2};
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];
        preProcess(arr, left, right);

        int l=0, r=2;
        if(isSubarrayMountainForm(left, right, l, r))
            System.out.println(l+" and "+r + " is in mountain form");
        else
            System.out.println(l+" and "+r + " is not in mountain form");

        l=1;
        r=3;
        if(isSubarrayMountainForm(left, right, l, r))
            System.out.println(l+" and "+r + " is in mountain form");
        else
            System.out.println(l+" and "+r + " is not in mountain form");
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    private static void preProcess(int[] arr, int[] left, int[] right){
        int lastIncrement, firstDecrement;
        left[0] = 0;
        lastIncrement = 0;
        for(int i=1; i<arr.length; i++){
            if(arr[i] > arr[i-1])
                lastIncrement = i;
            left[i] = lastIncrement;
        }
        right[arr.length-1] = arr.length-1;
        firstDecrement = arr.length-1;
        for(int i=arr.length-2; i>=0; i--){
            if(arr[i] > arr[i+1])
                firstDecrement = i;
            right[i] = firstDecrement;
        }
    }

    private static boolean isSubarrayMountainForm(int[] left, int[] right, int l, int r){
        return right[l] >= left[r];
    }
}
