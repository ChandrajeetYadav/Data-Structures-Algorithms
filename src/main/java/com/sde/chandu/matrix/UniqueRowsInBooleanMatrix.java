package com.sde.chandu.matrix;

import java.util.HashSet;

public class UniqueRowsInBooleanMatrix {
    public static void main(String[] args) {
        int[][] arr = {{1, 1, 0, 1},
                        {1, 0, 0, 1},
                        {1, 1, 0, 1}};

        System.out.println("Unique rows, brute: ");
        printUniqueRowsBrute(arr);

        System.out.println("Unique rows, hashing: ");
        printUniqueRowsHahing(arr);
    }

    //Time complexity: O(row^2 * col)
    //Space complexity: O(1)
    private static void printUniqueRowsBrute(int[][] arr){
        int r = arr.length;
        int c = arr[0].length;

        boolean isUniqueRow;
        for (int i=0; i<r; i++){
            isUniqueRow = true;
            for (int j=0; j<i; j++){
                isUniqueRow = false;
                for (int k=0; k<c; k++){
                    if (arr[i][k] != arr[j][k]){
                        isUniqueRow = true;
                        break;
                    }
                }
                if (!isUniqueRow)
                    break;
            }
            if (isUniqueRow){
                for (int j=0; j<c; j++)
                    System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    //Time complexity: O(row * col)
    //Space complexity: O(row * col)
    private static void printUniqueRowsHahing(int[][] arr){
        HashSet<String> set = new HashSet<>();
        String s;
        for (int i=0; i<arr.length; i++){
            s = "";
            for (int j=0; j<arr[0].length; j++)
                s += arr[i][j]+" ";
            if (!set.contains(s)){
                set.add(s);
                System.out.println(s);
            }
        }
    }
}
