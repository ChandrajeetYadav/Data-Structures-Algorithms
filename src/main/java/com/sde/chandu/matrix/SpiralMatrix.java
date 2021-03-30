package com.sde.chandu.matrix;

public class SpiralMatrix {
    public static void main(String[] args) {
        int[][] arr1 = { { 1, 2, 3, 4, 5, 6 },
                { 7, 8, 9, 10, 11, 12 },
                { 13, 14, 15, 16, 17, 18 } };

        int[][] arr2 = {{5,11,30},
                {5,19,19}};

        System.out.println("arr1, Matrix printed in spiral form: ");
        printMatrixSpirally(arr1);
        System.out.println("arr2, Matrix printed in spiral form: ");
        printMatrixSpirally(arr2);

        System.out.println("arr1, Matrix printed in spiral form, recursive: ");
        printMatrixSpirallyRecursive(arr1);
        System.out.println("arr2, Matrix printed in spiral form, recursive: ");
        printMatrixSpirallyRecursive(arr2);
    }

    //Time complexity : O(m*n)
    //Space complexity : O(1)
    private static void printMatrixSpirally(int[][] arr){
        if (arr==null || arr[0]==null)
            return;
        int startRow = 0, endRow = arr.length-1;
        int startCol = 0, endCol = arr[0].length-1;
        while (startRow<=endRow && startCol<=endCol){
            for (int i=startCol; i<=endCol; i++)
                System.out.print(arr[startRow][i] + "\t");
            startRow++;
            for (int j=startRow; j<=endRow; j++)
                System.out.print(arr[j][endCol] + "\t");
            endCol--;
            if (startRow <= endRow){
                for (int k=endCol; k>=startCol; k--)
                    System.out.print(arr[endRow][k] + "\t");
                endRow--;
            }
            if (startCol <= endCol){
                for (int l=endRow; l>=startRow; l--)
                    System.out.print(arr[l][startCol] + "\t");
                startCol++;
            }
        }
        System.out.println();
    }

    //Time complexity : O(m*n)
    //Space complexity : O(1)
    private static void printMatrixSpirallyRecursive(int[][] arr){
        if(arr == null)
            return;
        printMatrixSpirallyRecursiveUtil(arr, 0, arr.length-1, 0, arr[0].length-1);
        System.out.println();
    }

    private static void printMatrixSpirallyRecursiveUtil(int[][] arr, int startRow, int endRow, int startCol, int endCol){
        if (!(startRow<=endRow && startCol<=endCol))
            return;
        for (int i=startCol; i<=endCol; i++)
            System.out.print(arr[startRow][i] + "\t");
        startRow++;
        for (int j=startRow; j<=endRow; j++)
            System.out.print(arr[j][endCol] + "\t");
        endCol--;
        if (startRow <= endRow){
            for (int k=endCol; k>=startCol; k--)
                System.out.print(arr[endRow][k] + "\t");
            endRow--;
        }
        if (startCol <= endCol){
            for (int l=endRow; l>=startRow; l--)
                System.out.print(arr[l][startCol] + "\t");
            startCol++;
        }
        printMatrixSpirallyRecursiveUtil(arr, startRow, endRow, startCol, endCol);
    }
}
