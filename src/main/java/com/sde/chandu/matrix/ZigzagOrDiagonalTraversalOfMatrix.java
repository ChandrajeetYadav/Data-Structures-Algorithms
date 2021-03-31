package com.sde.chandu.matrix;

public class ZigzagOrDiagonalTraversalOfMatrix {
    public static void main(String[] args) {
        int[][] arr = {{ 1, 2, 3, 4 },
                        { 5, 6, 7, 8 },
                        { 9, 10, 11, 12 },
                        { 13, 14, 15, 16 }};
        // O/p: 1, 5, 2, 9, 6, 3, 13, 10, 7, 4, 14, 11, 8, 15, 12, 16
        System.out.println("Matrix elements in zigzag or diagonal pattern: ");
        printZigzagMatrix(arr);
    }

    // Time complexity: O(n * n)
    // Space complexity: O(1)
    private static void printZigzagMatrix(int[][] arr){
        int n = arr.length;
        int row, col;
        for (int i=0; i<n; i++){
            row = i;
            col = 0;
            while (col <= i){
                System.out.print(arr[row][col] + "\t");
                row--;
                col++;
            }
        }
        for (int i=1; i<n; i++){
            row = n-1;
            col = i;
            while (col < n){
                System.out.print(arr[row][col] + "\t");
                row--;
                col++;
            }
        }
    }
}
