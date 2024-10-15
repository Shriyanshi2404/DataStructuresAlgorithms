package com.data.structures.algorithms.Arrays;

import java.util.Scanner;

public class LeftRotateArrayByDPlaces {

    // Brute Force approach
    // T(n) = O(N+D)
    // According to value of d, shift the first-d elements into temp. array
    // Now, run a loop to shift the remaining elements towards the left of original array
    // Now, again run a loop to put the elements stored in temp. array to the end of original array
    public static void rotateArrBruteForce(int arr[], int d, int n) {
        d=d%n;
        int temp[]=new int[d];

        // storing first D places elements into temp. array
        for(int i=0;i<d;i++)
        {
            temp[i]=arr[i];
        }
        // shifting rest of the elements towards left
        int j=0;
        for(int i=d;i<n;i++)
        {
            arr[j]=arr[i];
            j++;
        }
        // shifting elements in temp. array to end of array
        j=0;
        for(int i=n-d;i<n;i++)
        {
            arr[i]=temp[j];
            j++;
        }
    }

    public static void reverse(int arr[], int start, int end)
    {
        int temp;
        while(start<end)
        {
            temp=arr[start];
            arr[start]=arr[end];
            arr[end]=temp;
            start++;
            end--;
        }
    }

    // Optimal approach
    // T(n) = O(2N)
    // Step-1 Reverse first D elements from index 0 to D-1
    // Step-2 Reverse elements from index D to N-1 (remaining elements)
    // Step-3 Reverse complete array from index 0 to N-1
    public static void rotateArr(int arr[], int d) {
        int n=arr.length;
        d=d%n;
        reverse(arr,0,d-1);
        reverse(arr,d,n-1);
        reverse(arr,0,n-1);
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
        int k=s.nextInt();
        rotateArr(nums, k);
    }
}
