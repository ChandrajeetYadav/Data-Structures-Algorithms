package com.sde.chandu.array;

public class MinMax {
    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 56, 1000, 167};
        Pair pair = getMinMax(arr);
        System.out.println("Min: " + pair.min + " , Max: " + pair.max);
        pair = getMinMaxOptimized(arr);
        System.out.println("Efficient approach, Min: " + pair.min + " , Max: " + pair.max);
    }

    // Time complexity: O(n)
    // Space complexity: O(1)
    //In this method, the total number of comparisons is 1 + 2(n-2) in the worst case
    // and 1 + n – 2 in the best case.
    private static Pair getMinMax(int[] arr) {
        int min, max, n = arr.length;
        if (n == 1)
            return new Pair(arr[0], arr[0]);
        if (arr[0] < arr[1]) {
            min = arr[0];
            max = arr[1];
        } else {
            min = arr[1];
            max = arr[0];
        }
        for (int i = 2; i < n; i++) {
            if (arr[i] < min)
                min = arr[i];
            else if (arr[i] > max)
                max = arr[i];
        }
        return new Pair(min, max);
    }

    /* Number of comparisons:
     If n is odd:    3*(n-1)/2
     If n is even:   1 Initial comparison for initializing min and max,
     and 3(n-2)/2 comparisons for rest of the elements
             =  1 + 3*(n-2)/2 = 3n/2 -2
     Time complexity: O(n)
     Space complexity: O(1)*/
    private static Pair getMinMaxOptimized(int[] arr) {
        int min, max, n = arr.length, i;
        if (n % 2 == 0) {
            if (arr[0] < arr[1]) {
                min = arr[0];
                max = arr[1];
            } else {
                max = arr[0];
                min = arr[1];
            }
            i = 2;
        } else {
            min = max = arr[0];
            i = 1;
        }
        while (i < n - 1) {
            if (arr[i] < arr[i + 1]) {
                if (arr[i] < min)
                    min = arr[i];
                if (arr[i + 1] > max)
                    max = arr[i + 1];
            } else {
                if (arr[i + 1] < min)
                    min = arr[i + 1];
                if (arr[i] > max)
                    max = arr[i];
            }
            i += 2;
        }
        return new Pair(min, max);
    }

    private static class Pair {
        long min;
        long max;

        Pair(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
}
