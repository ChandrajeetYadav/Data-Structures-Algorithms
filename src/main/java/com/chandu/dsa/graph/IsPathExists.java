package com.chandu.dsa.graph;

public class IsPathExists {
    private static int row;
    private static int col;
    private static boolean flag = false;
    public static void main(String[] args) {
        int[][] arr = {
                {0, 3, 1, 0},
                {3, 0, 3, 3},
                {2, 3, 0, 3},
                {0, 3, 3, 3}
        };
        row = arr.length;
        col = arr[0].length;
        doesPathExist(arr);
        System.out.println("Does path exist: " + flag);
    }

    //Time Complexity : O(row * col)
    //Space Complexity : O(row * col)
    private static void doesPathExist(int[][] arr){
        if (arr==null || arr.length==0)
            return;
        for (int i=0; i<row; i++){
            for (int j=0; j<col; j++){
                if (arr[i][j] == 1){
                    dfsUtil(arr, i, j);
                    return;
                }
            }
        }
    }

    private static void dfsUtil(int[][] arr, int r, int c){
        if(!isValidCell(r, c))
            return;
        if (arr[r][c] == 2){
            flag = true;
            return;
        }
        if(arr[r][c]==1 || arr[r][c]==3){
            arr[r][c] = -arr[r][c];
            dfsUtil(arr, r, c-1);
            dfsUtil(arr, r, c+1);
            dfsUtil(arr, r-1, c);
            dfsUtil(arr, r+1, c);
        }
    }

    private static boolean isValidCell(int r, int c){
        if(r<0 || r>=row || c<0 || c>=col)
            return false;
        return true;
    }
}
