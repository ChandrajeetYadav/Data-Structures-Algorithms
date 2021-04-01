package com.sde.chandu.matrix;

public class SearchInSortedMatrix {
    public static void main(String[] args) {
        int[][] arr = {{1,   3,  5,  7},
                        {10, 11, 16, 20},
                        {23, 30, 34, 50}};
        int num1 = 3;
        int num2 = 13;

        System.out.println("Search "+ num1 + ", brute:");
        searchBrute(arr, num1);
        System.out.println("Search "+ num2 + ", brute:");
        searchBrute(arr, num2);
        System.out.println();

        System.out.println("Search "+ num1 + ", efficient:");
        searchEfficient(arr, num1);
        System.out.println("Search "+ num2 + ", efficient:");
        searchEfficient(arr, num2);
    }

    // Time complexity: O(r*c)
    // Space complexity: O(1)
    private static void searchBrute(int[][] arr, int num){
        int r = arr.length;
        int c = arr[0].length;
        for (int i=0; i<r; i++){
            for (int j=0; j<c; j++){
                if (arr[i][j] == num){
                    System.out.println(num + " is present at position: " + i + "," + j);
                    return;
                }
            }
        }
        System.out.println(num + " is not present");
    }

    // Time complexity: O(log r + log c)
    // Space complexity: O(1)
    private static void searchEfficient(int[][] arr, int num){
        int c = arr[0].length;
        int low = 0;
        int high = arr.length;
        int mid;
        while (low <= high){
            mid = (low + high)/2;
            if (num>=arr[mid][0] && num<=arr[mid][c-1]){
                int index = binarySearch(arr[mid], num);
                if (index != -1)
                    System.out.println(num + " is present at position: " + mid + "," + index);
                else
                    System.out.println(num + " is not present");
                return;
            }else if (num < arr[mid][0])
                high = mid-1;
            else
                low = mid+1;
        }
        System.out.println(num + " is not present");
    }

    private static int binarySearch(int[] arr, int num){
        int low = 0, high = arr.length-1, mid;
        while (low <= high){
            mid = (low+high)/2;
            if (arr[mid] == num)
                return mid;
            else if (arr[mid] < num)
                low = mid+1;
            else
                high = mid-1;
        }
        return -1;
    }
}
