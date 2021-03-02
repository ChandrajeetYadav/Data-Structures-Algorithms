package com.sde.chandu.array;

public class SortedSubsequenceSize3 {
    public static void main(String[] args) {
        int[] arr1 = { 12, 11, 10, 5, 6, 2, 30 };
        int[] arr2 = {4, 3, 2, 1};
        System.out.println("arr1, sequence brute");
        sortedSubsequence3Brute(arr1);
        System.out.println("arr2, sequence brute");
        sortedSubsequence3Brute(arr2);

        System.out.println();
        System.out.println("arr1, sequence extra space");
        sortedSubsequence3ExtraSpace(arr1);
        System.out.println("arr2, sequence extra space");
        sortedSubsequence3ExtraSpace(arr2);

        System.out.println();
        System.out.println("arr1, efficient");
        sortedSubsequence3Efficient1(arr1);
        System.out.println("arr2, efficient");
        sortedSubsequence3Efficient1(arr2);
    }

    //Time complexity: O(n^3)
    //Space complexity: O(1)
    private static void sortedSubsequence3Brute(int[] arr){
        if(arr==null || arr.length<3){
            System.out.println("No such triplet found");
            return;
        }
        for(int i=0; i<arr.length-2; i++){
            for(int j=i+1; j<arr.length-1; j++){
                if(arr[i] < arr[j]){
                    for(int k=j+1; k<arr.length; k++){
                        if(arr[j] < arr[k]){
                            String msg = String.format("Triplets are: %s, %s , %s", arr[i], arr[j], arr[k]);
                            System.out.println(msg);
                            return;
                        }
                    }
                }
            }
        }
        System.out.println("No such triplet found");
    }

    //Time complexity: O(n)
    //Space complexity: O(n)
    private static void sortedSubsequence3ExtraSpace(int[] arr){
        if(arr==null || arr.length<3){
            System.out.println("No such triplet found");
            return;
        }
        int[] leftMin = new int[arr.length];
        int[] rightMax = new int[arr.length];
        leftMin[0] = -1;
        for(int i=1, min=0; i<arr.length; i++){
            if(arr[i] <= arr[min]){
                leftMin[i] = -1;
                min = i;
            }else
                leftMin[i] = min;
        }
        rightMax[arr.length-1] = -1;
        for(int max=arr.length-1, i=max-1; i>=0; i--){
            if(arr[i] >= arr[max]){
                max = i;
                rightMax[i] = -1;
            }else
                rightMax[i] = max;
        }

        for(int i=0; i<arr.length; i++){
            if (leftMin[i]!=-1 && rightMax[i]!=-1){
                String msg = String.format("Triplets are: %s, %s , %s", arr[leftMin[i]], arr[i], arr[rightMax[i]]);
                System.out.println(msg);
                return;
            }
        }
        System.out.println("No such triplet found");
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    private static void sortedSubsequence3Efficient1(int[] arr){
        if(arr==null || arr.length<3){
            System.out.println("No such triplet found");
            return;
        }
        int small=Integer.MAX_VALUE, large=Integer.MAX_VALUE;
        int i;
        for (i=0; i<arr.length; i++){
            if (arr[i] <= small)
                small = arr[i];
            else if(arr[i] <= large)
                large = arr[i];
            else
                break;
        }
        if(i==arr.length){
            System.out.println("No such triplet found");
            return;
        }
        for (int j=0; j<i; j++){
            if (arr[j] < large){
                small = arr[j];
                break;
            }
        }
        String msg = String.format("Triplets are: %s, %s , %s", small, large, arr[i]);
        System.out.println(msg);
    }
}
