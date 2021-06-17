package com.sde.chandu.string;

import java.util.ArrayList;
import java.util.List;

public class GenerateGrayCodeSequences {
    public static void main(String[] args) {
        int n = 2;
        System.out.println("Gray codes for " + n + " bit: ");
        generateGrayCodeSequences(n);
        System.out.println();

        n = 3;
        System.out.println("Gray codes for " + n + " bit: ");
        generateGrayCodeSequences(n);
        System.out.println();

        n = 2;
        System.out.println("Gray codes for " + n + " bit, using binary no approach: ");
        generateGrayCodeSequencesUsingBinaryNo(n);
        System.out.println();

        n = 3;
        System.out.println("Gray codes for " + n + " bit, using binary no approach: ");
        generateGrayCodeSequencesUsingBinaryNo(n);
        System.out.println();
    }

    // Time complexity : O(2^n)
    // Space complexity : O(2^n)
    private static void generateGrayCodeSequences(int n) {
        if (n <= 0)
            return;
        ArrayList<String> res = new ArrayList<>(), temp;
        res.add("0");
        res.add("1");
        for (int i = 1; i < n; i++) {
            temp = new ArrayList<>();
            for (int j = 0; j < res.size(); j++)
                temp.add("0" + res.get(j));
            for (int j = res.size() - 1; j >= 0; j--)
                temp.add("1" + res.get(j));
            res = temp;
        }
        res.forEach(k -> System.out.print(k + "\t"));
    }

    // Time complexity : O(n * 2^n)
    // Space complexity : O(n)
    private static void generateGrayCodeSequencesUsingBinaryNo(int n) {
        if (n <= 0)
            return;
        for (int i = 0; i < (1 << n); i++) {
            int num = i ^ (i >> 1);
            System.out.print(decimalToBinaryNumber(num, n) + "\t");
        }
    }

    private static String decimalToBinaryNumber(int num, int n) {
        List<Integer> list = new ArrayList<>();
        while (num > 0) {
            list.add(num % 2);
            num /= 2;
        }
        for (int i = list.size(); i < n; i++)
            list.add(0);
        StringBuilder sb = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--)
            sb.append(list.get(i));
        return sb.toString();
    }
}
