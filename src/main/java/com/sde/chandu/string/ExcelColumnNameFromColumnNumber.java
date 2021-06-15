package com.sde.chandu.string;

import java.util.ArrayList;
import java.util.List;

public class ExcelColumnNameFromColumnNumber {
    public static void main(String[] args) {
        int columnNum = 26; // Z
        System.out.println("Column name for " + columnNum + " : " + getColumnName(columnNum));
        System.out.println("Column name for " + columnNum + ", decimal conversion approach : " + getColumnNameUsingDecimalNoConversionApproach(columnNum));

        columnNum = 51; // AY
        System.out.println("Column name for " + columnNum + " : " + getColumnName(columnNum));
        System.out.println("Column name for " + columnNum + ", decimal conversion approach : " + getColumnNameUsingDecimalNoConversionApproach(columnNum));

        columnNum = 52;  // AZ
        System.out.println("Column name for " + columnNum + " : " + getColumnName(columnNum));
        System.out.println("Column name for " + columnNum + ", decimal conversion approach : " + getColumnNameUsingDecimalNoConversionApproach(columnNum));

        columnNum = 80;  // CB
        System.out.println("Column name for " + columnNum + " : " + getColumnName(columnNum));
        System.out.println("Column name for " + columnNum + ", decimal conversion approach : " + getColumnNameUsingDecimalNoConversionApproach(columnNum));

        columnNum = 676; // YZ
        System.out.println("Column name for " + columnNum + " : " + getColumnName(columnNum));
        System.out.println("Column name for " + columnNum + ", decimal conversion approach : " + getColumnNameUsingDecimalNoConversionApproach(columnNum));

        columnNum = 702; // ZZ
        System.out.println("Column name for " + columnNum + " : " + getColumnName(columnNum));
        System.out.println("Column name for " + columnNum + ", decimal conversion approach : " + getColumnNameUsingDecimalNoConversionApproach(columnNum));

        columnNum = 705; // AAC
        System.out.println("Column name for " + columnNum + " : " + getColumnName(columnNum));
        System.out.println("Column name for " + columnNum + ", decimal conversion approach : " + getColumnNameUsingDecimalNoConversionApproach(columnNum));
    }

    // Time complexity : O( log n)
    // Time complexity : O( log n)
    private static String getColumnName(int columnNum) {
        StringBuilder colName = new StringBuilder();
        int rem;
        while (columnNum > 0) {
            rem = columnNum % 26;
            if (rem == 0) {
                colName.append('Z');
                columnNum = columnNum / 26 - 1;
            } else {
                colName.append((char) (rem - 1 + 'A'));
                columnNum /= 26;
            }
        }
        return colName.reverse().toString();
    }

    // Time complexity : O( log n)
    // Time complexity : O( log n)
    private static String getColumnNameUsingDecimalNoConversionApproach(int colNo) {
        List<Integer> list = new ArrayList<>();
        while (colNo > 0) {
            list.add(colNo % 26);
            colNo /= 26;
        }
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) <= 0) {
                list.set(i, list.get(i) + 26);
                list.set(i + 1, list.get(i + 1) - 1);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Integer i : list)
            if (i > 0)
                sb.append((char) (i - 1 + 'A'));
        return sb.reverse().toString();
    }
}
