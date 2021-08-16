package com.sde.chandu.backtracking;

public class Sudoku {
    public static void main(String[] args) {
        int[][] board = new int[][]{
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}
        };
        System.out.println("Solving sudoku using brute approach: ");
        if (solveSudokuBrute(board, 0, 0))
            printBoard(board);
        else
            System.out.println("No solution exists");
        System.out.println();
        int[][] board2 = new int[][]{
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}
        };
        System.out.println("Solving sudoku using backtracking: ");
        if (solveSudokuBacktracking(board))
            printBoard(board);
        else
            System.out.println("No solution exists");
    }

    // Time complexity : O(9^(n*n))
    // Space complexity : O(n*n) , To store the output array a matrix is needed.
    private static boolean solveSudokuBrute(int[][] board, int row, int col) {
        if (row == board.length - 1 && col == board.length)
            return true;
        if (col == board.length) {
            row++;
            col = 0;
        }
        if (board[row][col] != 0)
            return solveSudokuBrute(board, row, col + 1);
        for (int num = 1; num < 10; num++) {
            if (isSafe(board, row, col, num)) {
                board[row][col] = num;
                if (solveSudokuBrute(board, row, col + 1))
                    return true;
            }
            board[row][col] = 0;
        }
        return false;
    }

    // Time complexity : O(9^(n*n))
    // Space complexity : O(n*n) , To store the output array a matrix is needed.
    private static boolean solveSudokuBacktracking(int[][] board) {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty)
                break;
        }
        if (isEmpty)
            return true;
        for (int num = 1; num < 10; num++) {
            if (isSafe(board, row, col, num)) {
                board[row][col] = num;
                if (solveSudokuBacktracking(board))
                    return true;
                else
                    board[row][col] = 0;
            }
        }
        return false;
    }

    private static boolean isSafe(int[][] board, int row, int col, int num) {
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == num)
                return false;
        }
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == num)
                return false;
        }
        int sqrt = (int) Math.sqrt(board.length);
        int startRow = row - row % sqrt;
        int startCol = col - col % sqrt;
        for (int i = startRow; i < startRow + sqrt; i++) {
            for (int j = startCol; j < startCol + sqrt; j++) {
                if (board[i][j] == num)
                    return false;
            }
        }
        return true;
    }

    private static void printBoard(int[][] arr) {
        if (arr == null)
            return;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++)
                System.out.print(arr[i][j] + "\t");
            System.out.println();
        }
    }
}
