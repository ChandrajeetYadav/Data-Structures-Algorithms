package com.sde.chandu.array;

public class ThirdLargestElement {
    public static void main(String[] args) {
        int[] arr1 = {1, 14, 2, 16, 10, 20};
        int[] arr2 = {19, -10, 20, 14, 2, 16, 10};

        System.out.println("Third largest element is: " + getThirdLargestBrute(arr1));
        System.out.println("Third largest element is: " + getThirdLargestBrute(arr2));

        System.out.println("\nThird largest element is: " + getThirdLargest(arr1));
        System.out.println("Third largest element is: " + getThirdLargest(arr2));
    }

    //Time Complexity: O(n)
    //Space Complexity: O(1)
    private static int getThirdLargest(int[] arr){
        if(arr==null || arr.length<3)
            return Integer.MIN_VALUE;
        int first = arr[0], second, third;
        second =  third = Integer.MIN_VALUE;

        for (int i=1; i<arr.length; i++){
            if(arr[i] > first){
                third = second;
                second = first;
                first = arr[i];
            } else if(arr[i] > second){
                third = second;
                second = arr[i];
            } else if (arr[i] > third)
                third = arr[i];
        }
        return third;
    }

    //Time Complexity: O(n)
    //Space Complexity: O(1)
    private static int getThirdLargestBrute(int[] arr){
        if(arr==null || arr.length<3)
            return Integer.MIN_VALUE;
        int first = arr[0];
        for (int i=1; i<arr.length; i++){
            if(arr[i] > first)
                first = arr[i];
        }

        int second = Integer.MIN_VALUE;
        for (int i=0; i<arr.length; i++){
            if(arr[i]>second && arr[i]<first)
                second = arr[i];
        }

        int third = Integer.MIN_VALUE;
        for(int i=0; i<arr.length; i++){
            if(arr[i]>third && arr[i]<second)
                third = arr[i];
        }

        return third;
    }
}
