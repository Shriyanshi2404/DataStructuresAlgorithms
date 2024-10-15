package com.data.structures.algorithms.Arrays;

import java.util.Arrays;
import java.util.Scanner;


public class SecondLargestElement {

    // Brute Force approach
    // T(n) = O(N)+O(N*logN)
    public static int getSecondLargestBruteForce(int[] arr) {
        Arrays.sort(arr);
        int n=arr.length;
        int largest = arr[n-1];
        int secondLargest=-1;

        for(int i=n-2;i>=0;i--)
        {
            if(arr[i]!=largest)
            {
                secondLargest = arr[i];
                break;
            }
        }

        return secondLargest;
    }

    // Better approach
    // T(n) = O(2N)
    // finding largest element and then second largest element
    public static int getSecondLargestBetterApproach(int[] arr) {
        int n=arr.length;

        // finding largest element
        int largest=arr[0];
        for(int i=0;i<n;i++)
        {
            if(arr[i] > largest)
                largest = arr[i];
        }

        // finding second largest element
        int secondLargest=-1;
        for(int i=0;i<n;i++)
        {
            if(arr[i]!=largest && arr[i]>secondLargest)
            {
                secondLargest = arr[i];
            }
        }
        return secondLargest;
    }

    // Optimal approach
    // T(n) = O(N)
    // single traversal to find both largest and second largest element together
    public static int getSecondLargest(int[] arr) {
        int n=arr.length;
        int largest=arr[0];
        int secondLargest=-1;

        // finding largest and second largest element together
        for(int i=0;i<n;i++)
        {
            if(arr[i] > largest)
            {
                secondLargest=largest;
                largest = arr[i];
            }
            else if(arr[i]>secondLargest && arr[i]<largest)
            {
                secondLargest=arr[i];
            }
        }
        return secondLargest;
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
        System.out.println(getSecondLargest(nums));
    }
}
