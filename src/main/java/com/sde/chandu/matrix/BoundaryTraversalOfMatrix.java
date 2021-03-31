package com.sde.chandu.matrix;

public class BoundaryTraversalOfMatrix {
    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 15,16}};
        // O/p: 1,2,3,4,8,12,16,15,14,13,9,5

        System.out.println("Boundary traversal of matrix: ");
        boundaryTraversal(arr);
    }

    // Time complexity: O(2m + 2n) => O(m + n)
    // Space complexity: O(1)
    private static void boundaryTraversal(int[][] arr){
        int r = arr.length-1;
        int c = arr[0].length-1;

        for (int i=0; i<=c; i++)
            System.out.print(arr[0][i] + "\t");
        for (int i=1; i<=r; i++)
            System.out.print(arr[i][c] + "\t");
        if (r > 1){
            for (int i=c-1; i>=0; i--)
                System.out.print(arr[r][i] + "\t");
        }
        if (c > 1){
            for (int i=r-1; i>0; i--)
                System.out.print(arr[i][0] + "\t");
        }
    }
}
