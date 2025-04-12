package com.data.structures.algorithms.Arrays.Easy;

import java.util.Scanner;

// Note all array elements will be Positive
public class CountSubarraySumEqualsK {

    // Brute Force approach T(N) = O(N^3)
    static int subarraySum(int nums[], int K) {
        int count = 0;
        for(int i=0; i< nums.length; i++)
        {
            for(int j=i; j<nums.length; j++)
            {
                long sum = 0;
                for (int index = i; index <= j; index++) {
                    sum += nums[index];
                }
                if(sum == K)
                    count++;
            }
        }
        return count;
    }

    // Better Solution T(N) = O(N^2)
    static int subarraySumBetter(int[] nums, int k) {
        int count = 0;
        for(int i=0; i<nums.length; i++)
        {
            int sum = 0;
            for(int j=i; j<nums.length; j++)
            {
                sum += nums[j];

                if(sum == k)
                {
                    count++;
                }
            }
        }
        return count;
    }

    // Optimal Solution T(N) = O(2N)
    static int subarraySumOptimal(int[] nums, int k) {
        if(nums.length == 1)
        {
            if(nums[0] == k)
                return 1;
            else
                return 0;
        }
        int count = 0, sum = nums[0];
        int i = 0, j = 0;
        while(j < nums.length)
        {
            // will check if sum > K then we will increment left pointer
            // and subtract left pointer element from sum
            while(i <= j && sum > k)
            {
                sum -= nums[i];
                i++;
            }

            // check if sum is equal to K, then increment count of subarrays
            if(sum == k)
                count++;

            // increment right pointer
            j++;
            // will be adding current jth pointer element to sum till (n-1)th index only
            if(j < nums.length)
                sum += nums[j];
        }
        return count;
    }

    // main function
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int arr[] = new int[n];
        for(int i=0; i<n; i++)
        {
            arr[i] = s.nextInt();
        }
        int K = s.nextInt();
        System.out.println(subarraySumOptimal(arr, K));
    }
}
