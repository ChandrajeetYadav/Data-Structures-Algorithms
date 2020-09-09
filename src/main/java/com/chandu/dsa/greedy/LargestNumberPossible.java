package com.chandu.dsa.greedy;

public class LargestNumberPossible {

    public static void main(String[] args) {
        int numberOfDigits = 2;
        int sumOfDigits = 9;

        findLargestNumberPossible(numberOfDigits, sumOfDigits);
        findLargestNumberPossible(2, 0);
        findLargestNumberPossible(2, 37);
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    // n = numberOfDigits
    private static void findLargestNumberPossible(int numberOfDigits, int sumOfDigits){
        if(sumOfDigits > 9*numberOfDigits || (sumOfDigits==0 && numberOfDigits>1)){
            System.out.println(-1);
            return;
        }
        int[] arr = new int[numberOfDigits];
        for (int i=0; i<numberOfDigits; i++){
            if (sumOfDigits >= 9){
                arr[i] = 9;
                sumOfDigits -= 9;
            }else {
                arr[i] = sumOfDigits;
                sumOfDigits = 0;
            }
        }
        for (int i : arr)
            System.out.print(i);
        System.out.println();
    }
}
