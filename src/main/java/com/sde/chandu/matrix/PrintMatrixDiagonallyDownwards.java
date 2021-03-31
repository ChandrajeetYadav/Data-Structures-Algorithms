package com.sde.chandu.matrix;

public class PrintMatrixDiagonallyDownwards {
    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9}};
        // O/p: 1, 2, 4, 3, 5, 7, 6, 8, 9
        System.out.println("Matrix elements in diagonally downwards pattern: ");
        printMatrixDiagonallyDownwards(arr);
    }

    // Time complexity: O(n * n)
    // Space complexity: O(1)
    private static void printMatrixDiagonallyDownwards(int[][] arr){
        int n = arr.length;
        int row, col;
        for (int i=0; i<n; i++){
            row=0;
            col=i;
            while (col >= 0){
                System.out.print(arr[row][col] + "\t");
                row++;
                col--;
            }
        }
        for (int i=1; i<n; i++){
            row = i;
            col = n-1;
            while (row < n){
                System.out.print(arr[row][col] + "\t");
                row++;
                col--;
            }
        }
    }
}
