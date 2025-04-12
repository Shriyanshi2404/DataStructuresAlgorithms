package com.data.structures.algorithms.Arrays.Easy;

import java.util.Scanner;

public class LeftRotateArrayBy1Place {

    // Optimal approach
    // T(n) = O(N)
    // At first, we have to shift the array towards the left so, we store the value of the first index in a variable (let it be x).
    // Then we iterate the array from the 1st index to the n-1th index
    // And then store the value present in the current index to the previous index like this : arr[i-1] = arr[i]
    public static int[] rotateArray(int[] arr, int n) {
        int element=arr[0];
        int len=arr.length;
        for(int i=1;i<len;i++)
        {
            arr[i-1]=arr[i];
        }
        arr[len-1]=element;
        return arr;
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
        System.out.println(rotateArray(nums, n));
    }
}
