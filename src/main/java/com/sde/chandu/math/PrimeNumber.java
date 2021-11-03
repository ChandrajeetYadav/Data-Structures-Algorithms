package com.sde.chandu.math;

public class PrimeNumber {
    public static void main(String[] args) {
        System.out.println("Is 7 prime, using iterative approach: " + isPrimeIterative(7));
        System.out.println("Is 15 prime, using iterative approach: " + isPrimeIterative(15));
        System.out.println("Is 19 prime, using recursive approach: " + isPrimeRecursive(19));
        System.out.println("Is 18 prime, using recursive approach: " + isPrimeRecursive(15));
    }

    // Time complexity: O(sqrt(n))
    // Space complexity: O(1)
    private static boolean isPrimeIterative(int n) {
        if (n <= 1)
            return false;
        if (n == 2)
            return true;
        if (n % 2 == 0)
            return false;
        for (int i = 3; i <= Math.sqrt(n); i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    // Time complexity: O(n)
    // Space complexity: O(n)
    private static boolean isPrimeRecursive(int n) {
        return isPrimeRecursiveHelper(n, 2);
    }

    private static boolean isPrimeRecursiveHelper(int n, int i) {
        if (n <= 1)
            return false;
        if (n == i)
            return true;
        if (n % i == 0)
            return false;
        return isPrimeRecursiveHelper(n, ++i);
    }
}
