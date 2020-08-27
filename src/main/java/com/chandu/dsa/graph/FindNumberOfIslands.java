package com.chandu.dsa.graph;

public class FindNumberOfIslands {

    public static void main(String[] args) {
        int[][] arr = {
                        { 1, 1, 0, 0, 0 },
                        { 0, 1, 0, 0, 1 },
                        { 1, 0, 0, 1, 1 },
                        { 0, 0, 0, 0, 0 },
                        { 1, 0, 1, 0, 1 }
                    };

        System.out.println("Number of islands is: " + findNumberOfIslands(arr));
    }

    //Time Complexity: O(M * N)
    //Space Complexity: O(M * N)
    // M=row size, N = column size
    private static int findNumberOfIslands(int[][] arr){
        if (arr==null || arr.length==0)
            return 0;
        int numberOfIslands = 0;
        int row = arr.length;
        int col = arr[0].length;
        for (int i=0; i<row; i++){
            for (int j=0; j<col; j++){
                if(arr[i][j] == 1){
                    dfs(arr, i, j);
                    numberOfIslands++;
                }
            }
        }
        return numberOfIslands;
    }

    private static void dfs(int[][] arr, int r, int c){
        if(r<0 || r>= arr.length || c<0 || c>=arr[0].length || arr[r][c]!=1)
            return;
        arr[r][c] += 1;
        dfs(arr, r+1, c);
        dfs(arr, r-1, c);
        dfs(arr, r, c+1);
        dfs(arr, r, c-1);
        dfs(arr, r+1, c+1);
        dfs(arr, r+1, c-1);
        dfs(arr, r-1, c-1);
        dfs(arr, r-1, c+1);
    }
}
