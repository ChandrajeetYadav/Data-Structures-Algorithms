package com.sde.chandu.matrix;

public class NthElementOfSpiralMatrix {
    public static void main(String[] args) {
        int[][] arr1 = {{1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 15, 16}};
        int n1 = 6; // O/p: 12

        int[][] arr2 = {{1, 2, 3, 4, 5, 6},
                        {7, 8, 9, 10, 11, 12},
                        {13, 14, 15, 16, 17, 18}};
        int n2 = 17; //O /p: 10

        System.out.println("arr1, "+n1 +" element: " + getNthElement(arr1, n1));
        System.out.println("arr2, "+n2 +" element: " + getNthElement(arr2, n2));

        System.out.println("arr1, "+n1 +" element, efficient: " + getNthElementEfficient(arr1, n1));
        System.out.println("arr2, "+n2 +" element, efficient: " + getNthElementEfficient(arr2, n2));
    }

    //Time complexity: O(m*n)
    //Space complexity: O(1)
    private static int getNthElement(int[][] arr, int n){
        if (arr==null)
            return -1;
        int startRow=0, endRow=arr.length-1;
        int startCol=0, endCol=arr[0].length-1;
        int count = 0;

        while (startRow<=endRow && startCol<=endCol){
            for (int i=startCol; i<=endCol; i++){
                count++;
                if (count == n)
                    return arr[startRow][i];
            }
            startRow++;

            for (int i=startRow; i<=endRow; i++){
                count++;
                if (count == n)
                    return arr[i][endCol];
            }
            endCol--;

            if (startRow <= endRow){
                for (int i=endCol; i>=startCol; i--){
                    count++;
                    if (count == n)
                        return arr[endRow][i];
                }
                endRow--;
            }

            if (startCol <= endCol){
                for (int i=endRow; i>=startRow; i--){
                    count++;
                    if (count == n)
                        return arr[i][startCol];
                }
                startCol++;
            }
        }
        return -1;
    }

    //Time Complexity: O(c), where c is number of outer circular rings w.r.t n’th element.
    //Space Complexity: O(1)
    private static int getNthElementEfficient(int[][] arr, int n){
        if (arr == null)
            return -1;
        int r = arr.length;
        int c = arr[0].length;
        int i=0, j=0;
        while (r>=1 && c>=1){
            if (n <= c)
                return arr[i][j+n-1];
            if (n <= r+c-1)
                return arr[i+n-c][j+c-1];
            if(n <= r+c-1 +c-1)
                return arr[i+r-1][j+c-1 - (n - (r+c-1))];
            if(n <= r+c-1 +c-1 + r-2)
                return arr[i+r-1 - (n - (r+c-1 + c-1))][j];
            i++;
            j++;
            n = n - (2*r + 2*c -4);
            r -= 2;
            c -= 2;
        }
        return -1;
    }
}
