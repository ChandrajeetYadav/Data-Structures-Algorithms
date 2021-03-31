package com.sde.chandu.matrix;

public class PrintMatrixInDiagonalPattern {
    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9}};
        // O/p: 1, 2, 4, 7, 5, 3, 6, 8, 9
        System.out.println("Matrix elements in diagonal pattern: ");
        printMatrixInDiagonalPattern(arr);
    }

    // Time complexity: O(n*n)
    // Space complexity: O(1)
    private static void printMatrixInDiagonalPattern(int[][] arr){
        int n = arr.length;
        boolean isUp = true;
        for (int k=0, i=0, j=0; k<n*n; ){
            if (isUp){
                for (; i>=0 && j<n; i--, j++){
                    System.out.print(arr[i][j] + "\t");
                    k++;
                }
                if (i<0 && j<n)
                    i = 0;
                if (j == n){
                    i += 2;
                    j--;
                }
            }else {
                for (; j>=0 && i<n; i++, j--){
                    System.out.print(arr[i][j] + "\t");
                    k++;
                }
                if (j<0 && i<n)
                    j = 0;
                if (i == n){
                    j += 2;
                    i--;
                }
            }
            isUp = !isUp;
        }
    }
}
