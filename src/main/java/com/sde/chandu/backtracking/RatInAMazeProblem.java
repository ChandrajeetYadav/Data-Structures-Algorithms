package com.sde.chandu.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RatInAMazeProblem {
    private static List<String> pathList = new ArrayList<>();
    private static String path = "";

    public static void main(String[] args) {
        int[][] m = {{1, 0, 0, 0},
                {1, 1, 0, 1},
                {1, 1, 0, 0},
                {0, 1, 1, 1}};
        printPath(m);
    }

    // Time complexity : O(3^(m*n))
    // Time complexity : O(m*n)
    private static void printPath(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0)
            return;
        boolean[][] visited = new boolean[m.length][m[0].length];
        printPathUtil(m, 0, 0, visited, m.length, m[0].length);
        System.out.println("Possible paths are: ");
        Collections.sort(pathList);
        pathList.forEach(n -> System.out.println(n));
    }

    private static void printPathUtil(int[][] arr, int row, int col, boolean[][] visited, int m, int n) {
        if (!isSafeMove(row, col, arr, m, n, visited))
            return;
        if (row == m - 1 && col == n - 1) {
            pathList.add(path);
            return;
        }
        visited[row][col] = true;
        if (isSafeMove(row + 1, col, arr, m, n, visited)) {
            path += "D";
            printPathUtil(arr, row + 1, col, visited, m, n);
            path = path.substring(0, path.length() - 1);
        }
        if (isSafeMove(row, col - 1, arr, m, n, visited)) {
            path += "L";
            printPathUtil(arr, row, col - 1, visited, m, n);
            path = path.substring(0, path.length() - 1);
        }
        if (isSafeMove(row, col + 1, arr, m, n, visited)) {
            path += "R";
            printPathUtil(arr, row, col + 1, visited, m, n);
            path = path.substring(0, path.length() - 1);
        }
        if (isSafeMove(row - 1, col, arr, m, n, visited)) {
            path += "U";
            printPathUtil(arr, row - 1, col, visited, m, n);
            path = path.substring(0, path.length() - 1);
        }
        visited[row][col] = false;
    }

    private static boolean isSafeMove(int row, int col, int[][] arr, int m, int n, boolean[][] visited) {
        if (row == -1 || row == m || col == -1 || col == n || visited[row][col] || arr[row][col] == 0)
            return false;
        return true;
    }
}
