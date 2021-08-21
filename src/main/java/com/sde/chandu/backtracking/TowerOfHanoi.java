package com.sde.chandu.backtracking;

public class TowerOfHanoi {
    public static void main(String[] args) {
        int n = 3;
        System.out.println("Tower of Hanoi using recursion: ");
        towerOfHanoiRecursive(n, 'A', 'C', 'B');
    }

    // Time complexity : O(2^n)
    // Space complexity : O(1)
    private static void towerOfHanoiRecursive(int n, char from, char to, char aux) {
        if (n == 1) {
            System.out.println("Moving disk 1 from rod " + from + " to rod " + to);
            return;
        }
        towerOfHanoiRecursive(n - 1, from, aux, to);
        System.out.println("Moving disk " + n + " from rod " + from + " to rod " + to);
        towerOfHanoiRecursive(n - 1, aux, to, from);
    }
}
