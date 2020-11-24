package com.sde.chandu.array;

public class ReplaceAll0sWith5 {
    public static void main(String[] args) {
        int num = 1004;
        System.out.println(num + " after converting all 0 to 5: " + convert0to5(num));

        System.out.println(num + " after converting all 0 to 5 using recursion: " + convert0to5Recursive(num));

        num = 121;
        System.out.println(num + " after converting all 0 to 5: " + convert0to5(num));
    }

    //Time Complexity : O(k) , k = number of digits of the number
    //Space Complexity : O(1)
    private static int convert0to5(int num){
        if(num == 0)
            return 5;
        int decimalPlace = 1;
        int rem, res = 0;

        while(num > 0){
            rem = num % 10;
            if(rem == 0)
                rem = 5;
            res += rem * decimalPlace;
            decimalPlace *= 10;
            num /= 10;
        }
        return res;
    }

    //Time Complexity : O(k) , k = number of digits of the number
    //Space Complexity : O(1)
    private static int convert0to5Recursive(int num){
        if(num == 0)
            return 5;
        return convert0to5RecursiveHelper(num);
    }

    private static int convert0to5RecursiveHelper(int num){
        if(num == 0)
            return 0;
        int rem = num % 10;
        if(rem == 0)
            rem = 5;
        return convert0to5RecursiveHelper(num / 10) * 10 + rem;
    }
}
