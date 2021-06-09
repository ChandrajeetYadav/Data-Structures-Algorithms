package com.sde.chandu.string;

public class ConvertIntegerToEnglish {
    public static void main(String[] args) {
        int num = 438237764;
        System.out.println(num + " = " + convertToWords(num));
    }

    // Time complexity : O(1)
    // Space complexity : O(1)
    private static String convertToWords(int num) {
        String[] one = {"", "one ", "two ", "three ", "four ", "five ", "six ", "seven ", "eight ", "nine ", "ten ",
                "eleven ", "twelve ", "thirteen ", "fourteen ", "fifteen ", "sixteen ", "seventeen ", "eighteen ", "nineteen"};
        String[] ten = {"", "", "twenty ", "thirty ", "forty ", "fifty ", "sixty ", "seventy ", "eighty ", "ninety "};

        String res = "";
        res += numToWords((num / 10000000), one, ten, "crore ");
        res += numToWords((num / 100000) % 100, one, ten, "lakh ");
        res += numToWords((num / 1000) % 100, one, ten, "thousand ");
        res += numToWords((num / 100) % 10, one, ten, "hundred ");
        if (num > 100 && num % 100 > 0)
            res += "and ";
        res += numToWords(num % 100, one, ten, "");
        return res;
    }

    private static String numToWords(int n, String[] one, String[] ten, String s) {
        String str = "";
        if (n > 19)
            str += ten[n / 10] + one[n % 10];
        else
            str += (one[n]);
        if (n != 0)
            str += s;
        return str;
    }
}
