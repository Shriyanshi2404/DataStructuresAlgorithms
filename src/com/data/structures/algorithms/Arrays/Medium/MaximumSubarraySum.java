package com.data.structures.algorithms.Arrays.Medium;

import java.util.Scanner;

public class MaximumSubarraySum {

    // Brute force approach
    // T(n) = O(N^3)
    public static int maxSubarraySum(int[] arr) {
        int n = arr.length;
        int sum=0, maxSum=Integer.MIN_VALUE;

        for(int i=0; i<n; i++)
        {
            for(int j=i; j<n; j++)
            {
                sum = 0;
                for(int k=i; k<=j; k++)
                {
                    sum += arr[k];
                }
                maxSum = Math.max(sum, maxSum);
            }
        }
        return maxSum;
    }

    // Better approach
    // T(n) = O(N^2)
    public static int maxSubarraySumBetterApproach(int[] arr) {
        int n = arr.length;
        int sum=0, maxSum=Integer.MIN_VALUE;

        for(int i=0; i<n; i++)
        {
            sum=0;
            for(int j=i; j<n; j++)
            {
                sum += arr[j];
                maxSum = Math.max(sum, maxSum);
            }
        }
        return maxSum;
    }

    // Optimal approach -- Kadane's Algorithm
    // T(n) = O(N)
    // The intuition of the algorithm is not to consider the subarray as a part of the answer if its sum is less than 0.
    // A subarray with a sum less than 0 will always reduce our answer and so this type of subarray cannot be a part of the subarray with maximum sum.
    public static int maxSubarraySumOptimalAppaorach(int[] arr) {
        int n = arr.length;
        int sum=0, maxSum=Integer.MIN_VALUE;

        for(int i=0; i<n; i++)
        {
            sum += arr[i];
            maxSum = Math.max(sum, maxSum);

            if(sum < 0)
            {
                sum = 0;
            }
        }

        // if in question we have to consider empty array also
        if(maxSum < 0)
        {
            maxSum = 0;
        }
        return maxSum;
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
        System.out.println(maxSubarraySum(nums));
    }
}
