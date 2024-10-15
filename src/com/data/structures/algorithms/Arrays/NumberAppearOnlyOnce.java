package com.data.structures.algorithms.Arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class NumberAppearOnlyOnce {

    // Brute Force Approach T(N) = O(N^2)
    // Like linear search
    static int singleNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int ele = nums[i];
            int count = 0;
            // will be searching for element by traversing through array again and keep increasing count if found.
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == ele) {
                    count++;
                }
            }
            if (count == 1)
                return ele;
        }
        return -1;
    }

    // Better approach T(N) = O(N/2 +1)
    // using Hashmap
    static int singleNumberBetterApproach(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        // storing each number and their frequency in map
        for(int i=0; i<nums.length; i++)
        {
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
        }

        // iterate over each key to check which key has value as 1, return that key
        for(Map.Entry<Integer, Integer> entry : map.entrySet())
        {
            if(entry.getValue() == 1)
            {
                return entry.getKey();
            }
        }
        return -1;
    }

    // Optimal Solution T(N) = O(N)
    // using XOR operator
    static int singleNumberOptimalApproach(int[] nums) {
        int xor=0;
        for(int i=0; i<nums.length; i++)
        {
            xor = xor ^ nums[i];
        }
        // at last we will get a number which does not appear twice
        return xor;
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
        System.out.println(singleNumberOptimalApproach(arr));
    }
}
