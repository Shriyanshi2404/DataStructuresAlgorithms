package com.data.structures.algorithms.Arrays.Easy;

import java.util.HashMap;
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

    /**
     * T(N) = O(N^2)
     * Select all possible starting indices(say i) and all possible ending indices(say j) to generate all possible subarrays.
     * For every index i, the possible ending index j can vary from i to n-1.
     * In inner loop we will calculate the sum of all the elements(of that particular subarray)
     * If the sum is equal to k, we will do count++
     * at last return count
     **/
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

    // Optimal Solution T(N) = O(2N) S(n) = O(N)
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

    /**
     * Using HashMap
     * T(N) = O(N) S(N) = O(N) for map data structure
     * Declare a map to store the prefix sums and it's occurence
     * we will run a loop(say i) from index 0 to n-1
     * For each index i, we will do the following:
     -- We will add the current element i.e. arr[i] to the sum.
     -- calculate prefixSum = sum-K
     -- Check if prefixSum exists in hashmap, if YES, then count += value of prefixSum from map
     -- Check if sum exists in hashmap, if NO, then add sum with map.getOrDefault(sum, 0)+1  in map
     * return count
     **/
    public static int subarraySumOptimalSol(int[] nums, int k) {
        int sum = 0;
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        // storing sum, occurence in map
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            // calculate prefix sum of remaining subarray
            int prefSum = sum - k;

            // checking is prefSum exists as key in map
            if (map.containsKey(prefSum) == true) {
                count += map.get(prefSum);
            }

            // Update the map with the current sum and its frequency
            map.put(sum, map.getOrDefault(sum, 0) + 1);
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
        System.out.println(subarraySumOptimalSol(arr, K));
    }
}
