package com.sde.chandu.searching;

import java.text.DecimalFormat;

public class SquareRoot {
    public static void main(String[] args) {
        int num1 = 4;
        int num2 = 11;
        System.out.println("Square root of "+ num1 + ", brute: "+getSquareRootBrute(num1));
        System.out.println("Square root of "+ num2 + ", brute: "+getSquareRootBrute(num2));

        System.out.println("Square root of "+ num1 + ", efficient: "+getSquareRootEfficient(num1));
        System.out.println("Square root of "+ num2 + ", efficient: "+getSquareRootEfficient(num2));

        int num = 50;
        int precision = 3;
        double sqrt = getSquareRootDecimalEfficient(num, precision);
        DecimalFormat df = new DecimalFormat("#.###");
        System.out.println("Square root of "+ num + ", decimal efficient: "+ df.format(sqrt));
    }

    //Time complexity: O(√n)
    //Space complexity: O(1)
    private static int getSquareRootBrute(int num){
        if (num==0 || num==1)
            return num;
        int i=1, result=1;
        while(result <= num){
            i++;
            result = i*i;
        }
        return i-1;
    }

    //Time complexity: O(log n)
    //Space complexity: O(1)
    private static int getSquareRootEfficient(int num){
        if (num==0 || num==1)
            return num;
        int ans=0, start=1, end=num, mid;
        while(start <= end){
            mid = (start+end)/2;
            if(mid*mid == num)
                return mid;
            else if (mid*mid < num){
                ans = mid;
                start = mid+1;
            }else
                end = mid-1;
        }
        return ans;
    }

    //Time complexity: O(log n)
    //Space complexity: O(1)
    private static double getSquareRootDecimalEfficient(int num, int precision){
        if (num==0 || num==1)
            return num;
        int start=0, end=num, mid;
        double ans = 0.0;
        while (start <= end){
            mid = (start+end)/2;
            if(mid*mid == num){
                ans = mid;
                break;
            }else if(mid*mid < num){
                start = mid+1;
                ans = mid;
            }else
                end = mid-1;
        }
        double increment = 0.1;
        for (int i=0; i<precision; i++){
            while (ans*ans <= num)
                ans += increment;
            ans -= increment;
            increment /= 10;
        }
        return ans;
    }
}
