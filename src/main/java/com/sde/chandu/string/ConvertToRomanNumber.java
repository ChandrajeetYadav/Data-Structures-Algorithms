package com.sde.chandu.string;

import java.util.HashMap;
import java.util.Map;

public class ConvertToRomanNumber {
    public static void main(String[] args) {
        int num = 3549; // MMMDXLIX
        System.out.println(num + " roman representation: " + intToRoman(num));
        System.out.println(num + " roman representation 2: " + intToRoman2(num));
    }

    // Time Complexity: O(log10 N)
    // Space Complexity: O(log10 N * 10)
    private static String intToRoman(int num) {
        String[] m = {"", "M", "MM", "MMM"};
        String[] c = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] x = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] i = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        String thousands = m[num / 1000];
        String hundreds = c[(num % 1000) / 100];
        String tens = x[(num % 100) / 10];
        String ones = i[num % 10];

        return thousands + hundreds + tens + ones;
    }

    // Time Complexity: O(log10 N)
    // Space Complexity: O(log10 N * 10)
    private static String intToRoman2(int num) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "I");
        map.put(5, "V");
        map.put(10, "X");
        map.put(50, "L");
        map.put(100, "C");
        map.put(500, "D");
        map.put(1000, "M");
        map.put(5000, "G");
        map.put(10000, "H");

        int div = 1;
        while (num >= div)
            div *= 10;
        div /= 10;

        int lastNum;
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            lastNum = num / div;
            if (lastNum <= 3) {
                for (int i = 0; i < lastNum; i++)
                    sb.append(map.get(div));
            } else if (lastNum == 4) {
                sb.append(map.get(div) + map.get(div * 5));
            } else if (lastNum <= 8) {
                sb.append(map.get(div * 5));
                for (int i = 0; i < lastNum - 5; i++)
                    sb.append(map.get(div));
            } else if (lastNum == 9) {
                sb.append(map.get(div) + map.get(div * 10));
            }
            num %= div;
            div /= 10;
        }
        return sb.toString();
    }
}
