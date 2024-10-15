package com.data.structures.algorithms.Arrays;

import java.util.ArrayList;
import java.util.Scanner;

public class LargestElement {

    // Brute Force approach
    // T(n) = O(N)+O(N*logN)
    // sorting the array and then returning last element of array
    public static int largestBruteForce(int[] arr) {
        int n=arr.length;
        // bubble sort
        // pushing largest element to last of array
        for(int i=n-1;i>=1;i--)
        {
            for(int j=0;j<i;j++)
            {
                if(arr[j]>arr[j+1])
                {
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        return arr[arr.length-1];
    }

    // Optimal approach
    // T(n) = O(N)
    // Single traversal
    public static int largest(int[] arr) {
        int max=arr[0];
        for(int i=1;i<arr.length;i++)
        {
            if(arr[i]>max)
                max=arr[i];
        }
        return max;
    }

    // main function
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int nums[] = new int[n];
        for(int i=0; i<n; i++)
        {
            nums[i] = s.nextInt();
        }
        System.out.println(largest(nums));
    }
}
