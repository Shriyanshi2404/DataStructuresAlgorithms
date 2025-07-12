package com.data.structures.algorithms.SlidingWindow.Hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/** Problem Link: https://leetcode.com/problems/subarrays-with-k-different-integers/description/ */
/** Similar like: Count Subarrays with K Distinct Integers */
public class SubarraysWithKDifferentIntegers {

    /**
     * Algorithm: Brute Force
     * 1. We will generate all possible subarrays of the nums array.
     * 2. For each subarray, we will use a set to keep track of the distinct integers.
     * 3. If the size of the set is equal to k, we will increment the count.
     * 4. Finally, we will return the count as a result.
     * Time Complexity: O(N^2)
     * Space Complexity: O(N)
     */
    public static int subarraysWithKDistinct(int[] nums, int k) {
        int count = 0;
        for(int i=0; i<nums.length; i++)
        {
            Set<Integer> set = new HashSet<>();
            for(int j=i; j<nums.length; j++)
            {
                set.add(nums[j]);
                if(set.size() == k)
                    count++;
            }
        }
        return count;
    }

    /**
     * Algorithm: Sliding Window
     * 1. We will use a sliding window approach with two pointers, left (l) and right (r).
     * 2. We will maintain a map to store the frequency of each integer in the current window.
     * 3. As we iterate through the array with the right pointer, we will add the current integer to the map.
     * 4. If the size of the map exceeds k, we will shrink the window from the left until it is less than or equal to k.
     * 5. If the size of the map is less than or equal to k, we will count all subarrays that lie between these two pointers.
     * 6. Finally, we will return the count as a result.
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int noOfSubarraysWithKdistinct(int[] nums, int k, int count)
    {
        int l=0, r=0;
        HashMap<Integer, Integer> map = new HashMap<>();
        while(r < nums.length)
        {
            int ele = nums[r];
            map.put(ele, map.getOrDefault(ele, 0) + 1);

            while(map.size() > k)
            {
                map.put(nums[l], map.get(nums[l])-1);
                if(map.containsKey(nums[l]) && map.get(nums[l]) == 0)
                    map.remove(nums[l]);
                l++;
            }

            if(map.size() <= k)
                count = count + (r-l+1);
            r++;
        }
        return count;
    }

    /**
     * Algorithm: (Counting Subarrays with elements Equal to K distinct integers)
     * Idea: Count subarrays with elements are <= K say count as 'x' and Count subarrays with elements <= K-1 say count as 'y'
     *       And then do (x-y) to get the count of subarrays with elements equal to K distinct integers.
     * 1. We will use a sliding window approach to find the number of subarrays with at most k distinct integers.
     * 2. We will calculate the number of subarrays with at most k distinct integers and subtract the number of subarrays with at most k-1 distinct integers.
     * 3. Finally, we will return the difference as a result.
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int subarraysWithKDistinctNumbers(int[] nums, int k) {

        int count1 = noOfSubarraysWithKdistinct(nums, k, 0);
        int count2 = noOfSubarraysWithKdistinct(nums, k-1, 0);
        return count1-count2;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 2, 3};
        int k = 2;
        System.out.println("Number of subarrays with exactly " + k + " different integers: " + subarraysWithKDistinctNumbers(nums, k));
    }
}
