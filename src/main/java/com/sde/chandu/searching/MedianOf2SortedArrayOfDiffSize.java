package com.sde.chandu.searching;

public class MedianOf2SortedArrayOfDiffSize {
    public static void main(String[] args) {
        int[] arr1 = {-5, 3, 6, 12, 15};
        int[] arr2 = {-12, -10, -6, -3, 4, 10};  // Median(arr1, arr2) : 3
        int[] arr3 = {2, 3, 5, 8};
        int[] arr4 = {10, 12, 14, 16, 18, 20};  // Median(arr1, arr2) : 11

        System.out.println("arr1 & arr2 median, brute: " + getMedianBrute(arr1,arr2));
        System.out.println("arr3 & arr4 median, brute: " + getMedianBrute(arr3,arr4));

        System.out.println("arr1 & arr2 median, brute efficient: " + getMedianBruteEfficient(arr1,arr2));
        System.out.println("arr3 & arr4 median, brute efficient: " + getMedianBruteEfficient(arr3,arr4));

        System.out.println("arr1 & arr2 median, efficient: " + getMedianEfficient(arr1,arr2));
        System.out.println("arr3 & arr4 median, efficient: " + getMedianEfficient(arr3,arr4));
    }

    //Time Complexity: O(m+n)
    //Space Complexity: O(m+n)
    private static double getMedianBrute(int[] arr1, int[] arr2){
        if(arr1==null || arr2==null || (arr1.length==0 && arr2.length==0))
            return -1;
        int[] temp = new int[arr1.length+arr2.length];
        int i=0, j=0, k=0;
        while (i<arr1.length && j< arr2.length){
            if (arr1[i] <= arr2[j])
                temp[k++] = arr1[i++];
            else
                temp[k++] = arr2[j++];
        }
        while (i < arr1.length)
            temp[k++] = arr1[i++];
        while (j < arr2.length)
            temp[k++] = arr2[j++];
        int mid = temp.length/2;
        if (temp.length%2 == 1)
            return temp[mid];
        return (temp[mid] + temp[mid-1])/2.0;
    }

    //Time Complexity: O(m+n)
    //Space Complexity: O(1)
    private static double getMedianBruteEfficient(int[] arr1, int[] arr2){
        if(arr1==null || arr2==null || (arr1.length==0 && arr2.length==0))
            return -1;
        int m1=-1, m2=-1;
        for (int i=0,j=0,count=0; count<=(arr1.length+arr2.length)/2; count++){
            m2 = m1;
            if (i<arr1.length && j< arr2.length)
                m1 = arr1[i]<=arr2[j] ? arr1[i++] : arr2[j++];
            else if(i< arr1.length)
                m1 = arr1[i++];
            else
                m1 = arr2[j++];
        }
        if((arr1.length+ arr2.length)%2 == 1)
            return m1;
        return (m1+m2)/2.0;
    }

    //Time Complexity: O(min(log m, log n))
    //Space Complexity: O(1)
    private static double getMedianEfficient(int[] arr1, int[] arr2){
        if(arr1==null || arr2==null || (arr1.length==0 && arr2.length==0))
            return -1;
        if (arr1.length > arr2.length)
            return getMedianEfficient(arr2, arr1);
        int start=0, end=arr1.length;
        int max1, max2, min1, min2, i, j;
        while (start <= end){
            i = (start+end)/2;
            j = (arr1.length + arr2.length + 1)/2 - i;

            min1 = i==arr1.length ? Integer.MAX_VALUE : arr1[i];
            max1 = i==0 ? Integer.MIN_VALUE : arr1[i-1];
            min2 = j==arr2.length ? Integer.MAX_VALUE : arr2[j];
            max2 = j==0 ? Integer.MIN_VALUE : arr2[j-1];

            if (max1<=min2 && max2<=min1){
                if ((arr1.length + arr2.length)%2 == 0)
                    return (Math.max(max1, max2)+Math.min(min1, min2))/2.0;
                else
                    return Math.max(max1, max2);
            }else if(max1 > min2)
                end = i-1;
            else
                start = i+1;
        }
        return -1;
    }
}
