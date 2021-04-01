package com.sde.chandu.matrix;

public class SearchRowColumnSortedMatrix {
    public static void main(String[] args) {
        int[][] arr = { {10, 20, 30, 40},
                        {15, 25, 35, 45},
                        {27, 29, 37, 48},
                        {32, 33, 39, 50}};
        int num1 = 29;
        int num2 = 100;
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

    // Time complexity: O(r+c)
    // Space complexity: O(1)
    private static void searchEfficient(int[][] arr, int num){
        int r = arr.length;
        int c = arr[0].length;
        int i=0, j=c-1;
        while (i<r && j>=0){
            if (arr[i][j] == num){
                System.out.println(num + " is present at position: " + i + "," + j);
                return;
            }else if(arr[i][j] > num)
                j--;
            else
                i++;
        }
        System.out.println(num + " is not present");
    }
}
