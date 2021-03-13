package com.sde.chandu.searching;

public class IndexOfAnExtraElement {
    public static void main(String[] args) {
        int[] a = {2, 4, 6, 8, 9, 10, 12}; // O/p: 4
        int[] b =  {2, 4, 6, 8, 10, 12};

        System.out.println("Extra element index, brute: " + findExtraBrute(a, b));
        System.out.println("Extra element index, binary search: " + findExtraBinarySearch(a, b));
        System.out.println("Extra element index, sum: " + findExtraUsingSum(a, b));
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private static int findExtraBrute(int[] a, int[] b){
        if (a==null || b==null || a.length-b.length!=1)
            return -1;
        for (int i=0; i<a.length-1; i++){
            if (a[i] != b[i])
                return i;
        }
        return a.length-1;
    }

    // Time Complexity: O(log n)
    // Space Complexity: O(1)
    private static int findExtraBinarySearch(int[] a, int[] b){
        if (a==null || b==null || a.length-b.length!=1)
            return -1;
        int low=0, high=a.length-2, mid, index = a.length-1;
        while (low <= high){
            mid = (low+high)/2;
            if (a[mid] == b[mid])
                low = mid+1;
            else {
                index = mid;
                high = mid-1;
            }
        }
        return index;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private static int findExtraUsingSum(int[] a, int[] b){
        if (a==null || b==null || a.length-b.length!=1)
            return -1;
        int extraElement = sum(a) - sum(b);
        for (int i=0; i<a.length-1; i++){
            if (a[i] == extraElement)
                return i;
        }
        return a.length-1;
    }

    private static int sum(int[] arr){
        int sum = 0;
        for (int i : arr)
            sum += i;
        return sum;
    }
}
