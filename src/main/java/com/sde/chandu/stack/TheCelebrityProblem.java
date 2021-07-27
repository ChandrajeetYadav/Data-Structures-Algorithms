package com.sde.chandu.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class TheCelebrityProblem {
    private static int[][] mat1 = {{0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}, {0, 0, 1, 0}};
    private static int[][] mat2 = {{0, 0, 1, 0}, {0, 0, 1, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}};

    public static void main(String[] args) {
        System.out.println("Celebrity in mat1, using graph: " + findCelebrityUsingGraph(mat1));
        System.out.println("Celebrity in mat2, using graph: " + findCelebrityUsingGraph(mat2));
        System.out.println();

        System.out.println("Celebrity in mat1, using recursion: " + findCelebrityUsingRecursion(mat1));
        System.out.println("Celebrity in mat2, using recursion: " + findCelebrityUsingRecursion(mat2));
        System.out.println();

        System.out.println("Celebrity in mat1, using stack: " + findCelebrityUsingStack(mat1));
        System.out.println("Celebrity in mat2, using stack: " + findCelebrityUsingStack(mat2));
        System.out.println();

        System.out.println("Celebrity in mat1, using 2 pointer: " + findCelebrityUsing2Pointer(mat1));
        System.out.println("Celebrity in mat2, using 2 pointer: " + findCelebrityUsing2Pointer(mat2));
    }

    // Time complexity : O(n^2)
    // Space complexity : O(n)
    private static int findCelebrityUsingGraph(int[][] mat) {
        if (mat == null || mat.length == 0)
            return -1;
        int[] inDegree = new int[mat.length];
        int[] outDegree = new int[mat.length];

        int temp;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                temp = knows(mat, i, j);
                outDegree[i] += temp;
                inDegree[j] += temp;
            }
        }
        for (int i = 0; i < mat.length; i++) {
            if (inDegree[i] == mat.length - 1 && outDegree[i] == 0)
                return i;
        }
        return -1;
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    private static int findCelebrityUsingStack(int[][] mat) {
        if (mat == null || mat.length == 0)
            return -1;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < mat.length; i++)
            stack.push(i);
        int a, b;
        while (stack.size() > 1) {
            a = stack.pop();
            b = stack.pop();
            if (knows(mat, a, b) == 1)
                stack.push(b);
            else
                stack.push(a);
        }
        if (stack.isEmpty())
            return -1;
        a = stack.pop();
        for (int i = 0; i < mat.length; i++) {
            if (i != a && (knows(mat, a, i) == 1 || knows(mat, i, a) != 1))
                return -1;
        }
        return a;
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static int findCelebrityUsing2Pointer(int[][] mat) {
        if (mat == null || mat.length == 0)
            return -1;
        int i = 0, j = mat.length - 1;
        while (i < j) {
            if (knows(mat, i, j) == 1)
                i++;
            else
                j--;
        }
        int celebrity = i;
        for (i = 0; i < mat.length; i++) {
            if (i != celebrity && (knows(mat, celebrity, i) == 1 || knows(mat, i, celebrity) != 1))
                return -1;
        }
        return celebrity;
    }

    // Time complexity : O(n)
    // Space complexity : O(n), for method stack call
    private static int findCelebrityUsingRecursion(int[][] mat) {
        if (mat == null || mat.length == 0)
            return -1;
        int id = findCelebrityUsingRecursionUtil(mat, mat.length);
        if (id == -1)
            return -1;
        for (int i = 0; i < mat.length; i++) {
            if (id != i && (knows(mat, id, i) == 1 || knows(mat, i, id) != 1))
                return -1;
        }
        return id;
    }

    private static int findCelebrityUsingRecursionUtil(int[][] mat, int n) {
        if (n == 0)
            return -1;
        int id = findCelebrityUsingRecursionUtil(mat, n - 1);
        if (id == -1)
            return n - 1;
        else if (knows(mat, id, n - 1) == 1)
            return n - 1;
        else if (knows(mat, n - 1, id) == 1)
            return id;
        return -1;
    }

    private static int knows(int[][] mat, int a, int b) {
        return mat[a][b];
    }
}
