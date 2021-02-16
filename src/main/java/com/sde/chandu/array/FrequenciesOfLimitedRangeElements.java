package com.sde.chandu.array;

public class FrequenciesOfLimitedRangeElements {
    public static void main(String[] args) {
        int[] arr = {2, 3, 3, 2, 5};
        System.out.println("Frequencies using brute force:" );
        printFrequenciesBrute(arr, arr.length);

        System.out.println("Frequencies using map or array:" );
        printFrequenciesUsingMapOrArray(arr, arr.length);

        System.out.println("Frequencies without using extra space:" );
        printFrequenciesWithoutExtraSpace(arr, arr.length);

        int[] arr1 = {2, 3, 3, 2, 5};
        System.out.println("Frequencies without using extra space method 2:" );
        printFrequenciesWithoutExtraSpace2(arr1, arr1.length);
    }

    //Time complexity: O(n^2)
    //Space complexity: O(1)
    private static void printFrequenciesBrute(int[] arr, int n){
        int count;
        for(int i=1; i<=n; i++){
            count = 0;
            for(int j : arr){
                if(i == j)
                    count++;
            }
            System.out.println(i + "->" + count);
        }
    }

    //Time complexity: O(n)
    //Space complexity: O(n)
    private static void printFrequenciesUsingMapOrArray(int[] arr, int n){
        if(arr==null || n<=0)
            return;
        int[] count = new int[n];

        for (int i : arr)
            count[i-1]++;
        for(int i=0; i<n; i++)
            System.out.println(i+1 + "->" + count[i]);
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    private static void printFrequenciesWithoutExtraSpace(int[] arr, int n){
        if(arr==null || n<=0)
            return;
        int temp;
        for(int i=0; i<n; ){
            if(arr[i] <= 0){
                i++;
                continue;
            }
            temp = arr[i] - 1;
            if(arr[temp] > 0){
                arr[i] = arr[temp];
                arr[temp] = -1;
            } else{
                arr[i] = 0;
                arr[temp]--;
                i++;
            }
        }
        for(int i=0; i<n; i++)
            System.out.println(i+1 + "->" + Math.abs(arr[i]));
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    private static void printFrequenciesWithoutExtraSpace2(int[] arr, int n){
        if(arr==null || n<=0)
            return;
        for(int i=0; i<n; i++)
            arr[i]--;
        int index;
        for(int i=0; i<n; i++){
            index = arr[i] % n;
            arr[index] += n;
        }

        for (int i=0; i<n; i++)
            System.out.println(i+1 + "->" + arr[i]/n);
    }
}
