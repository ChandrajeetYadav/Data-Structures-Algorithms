package com.sde.chandu.array;

public class RotateArray {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int d = 2;
        System.out.print("Original Array: ");
        printArray(arr);

        //rotateArrayUsingTempArray(arr, d);
        rotateOneByOne(arr, d);
        System.out.println("After rotating by " + d + " elements: ");
        printArray(arr);

        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        d = 3;
        System.out.print("Original Array: ");
        printArray(arr1);
        rotateUsingJugglingAlgorithm(arr1, d);
        System.out.println("After rotating by " + d + " elements: ");
        printArray(arr1);
    }

    //Time Complexity: O(n)
    //Space Complexity: O(d)
    private static void rotateArrayUsingTempArray(int[] arr, int d){
        if(arr==null || arr.length==0 || d==0 || d==arr.length)
            return;

        d = d % arr.length;
        int[] temp = new int[d];
        for (int i=0; i<d; i++)
            temp[i] = arr[i];

        int i = 0;
        for (; i<arr.length-d; i++)
            arr[i] = arr[i+d];
        for (int j=0; j<d; j++)
            arr[i++] = temp[j];
    }

    //Time Complexity: O(n*d)
    //Space Complexity: O(1)
    private static void rotateOneByOne(int[] arr, int d){
        if(arr==null || arr.length==0 || d==0 || d==arr.length)
            return;
        d = d % arr.length;
        for (int i=0; i<d; i++)
            rotateLeftOneByOne(arr);
    }

    private static void rotateLeftOneByOne(int[] arr){
        int temp = arr[0];
        int i=0;
        for (; i<arr.length-1; i++)
            arr[i] = arr[i+1];
        arr[i] = temp;
    }

    //Time Complexity: O(n)
    //Space Complexity: O(1)
    private static void rotateUsingJugglingAlgorithm(int[] arr, int d){
        if(arr==null || arr.length==0 || d==0 || d==arr.length)
            return;
        d = d % arr.length;
        int gcd = gcd(arr.length, d);
        int temp;
        for (int i=0, j, k; i < gcd; i++){
            temp = arr[i];
            j = i;
            while (true){
                k = j + d;
                if(k >= arr.length)
                    k = k - arr.length;
                if (k == i)
                    break;
                arr[j] = arr[k];
                j = k;
            }
            arr[j] = temp;
        }
    }

    private static int gcd(int a, int b){
        if(b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    private static void printArray(int[] arr){
        if (arr == null)
            return;
        for(int i : arr)
            System.out.print(i + "\t");
        System.out.println();
    }
}
