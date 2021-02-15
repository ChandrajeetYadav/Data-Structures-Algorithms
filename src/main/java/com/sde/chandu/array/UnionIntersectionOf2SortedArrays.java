package com.sde.chandu.array;

public class UnionIntersectionOf2SortedArrays {
    public static void main(String[] args) {
        int[] arr1 = {1, 3, 4, 5, 5, 7, 8, 8};
        int[] arr2 = {2, 2, 3, 5, 6};

        System.out.println("Union: ");
        printUnion(arr1, arr2);
        System.out.println("Intersection: ");
        printIntersection(arr1, arr2);
    }

    //Time complexity: O(m+n)
    //Space complexity: O(1)
    private static void printUnion(int[] arr1, int[] arr2){
        int i=0, j=0;
        while(i<arr1.length && j<arr2.length){
            if(arr1[i] < arr2[j]){
                System.out.print(arr1[i] + "\t");
                i++;
            }else if(arr1[i] > arr2[j]){
                System.out.print(arr2[j] + "\t");
                j++;
            }else {
                System.out.print(arr1[i] + "\t");
                i++;
                j++;
            }
            while (i<arr1.length && i>0 && arr1[i] == arr1[i-1])
                i++;
            while(j<arr2.length && j>0 && arr2[j] == arr2[j-1])
                j++;
        }
        while (i < arr1.length){
            System.out.print(arr1[i++] + "\t");
            while (i<arr1.length && i>0 && arr1[i] == arr1[i-1])
                i++;
        }

        while (j < arr2.length){
            System.out.print(arr2[j++] + "\t");
            while(j<arr2.length && j>0 && arr2[j] == arr2[j-1])
                j++;
        }
        System.out.println();
    }

    //Time complexity: O(m+n)
    //Space complexity: O(1)
    private static void printIntersection(int[] arr1, int[] arr2){
        int i=0, j=0;
        while(i<arr1.length && j<arr2.length){
            if(arr1[i] < arr2[j])
                i++;
            else if(arr1[i] > arr2[j])
                j++;
            else{
                System.out.print(arr1[i] + "\t");
                i++;
                j++;
            }
            while(i>0 && i<arr1.length && arr1[i]==arr1[i-1])
                i++;
            while(j>0 && j<arr2.length && arr2[j]==arr2[j-1])
                j++;
        }
        System.out.println();
    }
}
