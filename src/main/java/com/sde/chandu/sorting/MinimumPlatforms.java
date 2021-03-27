package com.sde.chandu.sorting;

import java.util.Arrays;

public class MinimumPlatforms {
    public static void main(String[] args) {
        int[] arr = { 900, 940, 950, 1100, 1500, 1800 };
        int[] dep = { 910, 1200, 1120, 1130, 1900, 2000 };

        System.out.println("Minimum number of platforms, brute: " + getMinNumberOfPlatformsBrute(arr, dep));
        System.out.println("Minimum number of platforms, sorting: " + getMinNumberOfPlatformsSorting(arr, dep));
        System.out.println("Minimum number of platforms, linear: " + getMinNumberOfPlatformsLinear(arr, dep));
    }

    //Time complexity: O(n^2)
    //Space complexity: O(1)
    private static int getMinNumberOfPlatformsBrute(int[] arr, int[] dep){
        int result=1, platform;
        for (int i=0; i<arr.length-1; i++){
            platform = 1;
            for (int j=i+1; j<arr.length; j++){
                if (arr[i]>=arr[j] && arr[i]<=dep[j] || arr[j]>=arr[i] &&  arr[j]<=dep[i])
                    platform++;
            }
            result = Math.max(result, platform);
        }
        return result;
    }

    //Time complexity: O(n log n)
    //Space complexity: O(1)
    private static int getMinNumberOfPlatformsSorting(int[] arr, int[] dep){
        Arrays.sort(arr);
        Arrays.sort(dep);
        int platform=1, result=1;
        int i=1, j=0;
        while (i<arr.length && j<arr.length){
            if (arr[i] <= dep[j]){
                platform++;
                i++;
            }else{
                platform--;
                j++;
            }
            result = Math.max(result, platform);
        }
        return result;
    }

    //Time complexity: O(n)
    //Space complexity: constant
    private static int getMinNumberOfPlatformsLinear(int[] arr, int[] dep){
        int[] platform = new int[2361];
        int reqPlatform = 1;
        int sum = 0;
        for (int i=0; i<arr.length; i++){
            ++platform[arr[i]];
            --platform[dep[i]+1];
        }
        for (int i=0; i<2361; i++){
            sum += platform[i];
            reqPlatform = Math.max(reqPlatform, sum);
        }
        return reqPlatform;
    }
}
