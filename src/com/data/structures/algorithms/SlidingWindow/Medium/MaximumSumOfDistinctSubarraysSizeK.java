package com.data.structures.algorithms.SlidingWindow.Medium;

import java.util.HashSet;
import java.util.Set;

/** Problem Link: https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-of-size-k/ */
public class MaximumSumOfDistinctSubarraysSizeK {

    /**
     * Algorithm:
     * 1. We will use a sliding window approach with two pointers, left (l) and right (r).
     * 2. We will maintain a set to store the distinct elements in the current subarray.
     * 3. As we iterate through the array with the right pointer, we will check if the element is already in the set.
     *    - If it is not, we add it to the set and update the sum.
     *    - If it is, we will remove elements from the left in the set until we can add the current element without duplicates.
     *      - Also decreases the sum by the elements we remove from the left and increments the left pointer.
     * 4. When the size of the window reaches k, we will check if the sum is greater than the maximum sum found so far.
     *    - If yes, we update the maximum sum.
     *    - We found a valid window of size k, so we will remove the leftmost element from the set and decrease the sum accordingly.
     *    - To check the next window, we increment the left pointer.
     * 5. Finally, we return the maximum sum of distinct subarrays of size k.
     * Time Complexity: O(n)
     * Space Complexity: O(k)
     */
    public static long maximumSubarraySum(int[] nums, int k) {
        long maxSum = 0;
        int l=0;
        long sum = 0;
        Set<Integer> set = new HashSet<>();
        for(int r=0; r<nums.length; r++)
        {
            // if set contains current element
            while(set.contains(nums[r]))
            {
                set.remove(nums[l]);
                sum = sum - nums[l];
                l++;
            }

            set.add(nums[r]);
            sum = sum + nums[r];

            if(r-l+1 == k)
            {
                System.out.println(sum);
                maxSum = Math.max(maxSum, sum);
                // since we checked for valid window,
                // now we can shift forward to check for another window
                set.remove(nums[l]);
                sum = sum - nums[l];
                l++;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;
        System.out.println("Maximum sum of distinct subarray of size " + k + ": " + maximumSubarraySum(arr, k));
    }
}
