package com.sde.chandu.string;

public class MultiplyTwoStrings {
    public static void main(String[] args) {
        String s1 = "4154";
        String s2 = "51454";
        System.out.println(s1 + " * " + s2 + " = " + multiply(s1, s2)); // O/p: 213739916

        s1 = "4416";
        s2 = "-333";
        System.out.println(s1 + " * " + s2 + " = " + multiply(s1, s2)); // O/p: -1470528
    }

    // Time complexity : O(m * n) , m=s1.length(), n=s2.length()
    // Space complexity : O(m + n)
    private static String multiply(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0)
            return "0";
        int neg1 = 1, neg2 = 1;
        if (s1.charAt(0) == '-') {
            neg1 = -1;
            s1 = s1.substring(1);
        }
        if (s2.charAt(0) == '-') {
            neg2 = -1;
            s2 = s2.substring(1);
        }
        int[] res = new int[s1.length() + s2.length()];
        int ind1 = 0, ind2, carry, num1, num2, sum;
        for (int i = s1.length() - 1; i >= 0; i--) {
            num1 = s1.charAt(i) - '0';
            carry = 0;
            ind2 = 0;
            for (int j = s2.length() - 1; j >= 0; j--) {
                num2 = s2.charAt(j) - '0';
                sum = num1 * num2 + res[ind1 + ind2] + carry;
                carry = sum / 10;
                res[ind1 + ind2] = sum % 10;
                ind2++;
            }
            if (carry > 0)
                res[ind1 + ind2] = carry;
            ind1++;
        }
        int i = res.length - 1;
        while (i >= 0 && res[i] == 0)
            i--;
        if (i == -1)
            return "0";
        StringBuilder sb = new StringBuilder();
        if (neg1 * neg2 == -1)
            sb.append('-');
        while (i >= 0)
            sb.append(res[i--]);
        return sb.toString();
    }
}
