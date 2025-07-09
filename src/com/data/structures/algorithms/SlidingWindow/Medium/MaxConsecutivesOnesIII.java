package com.data.structures.algorithms.SlidingWindow.Medium;

/** Problem Link: https://leetcode.com/problems/max-consecutive-ones-iii/description/ */
/** Similar like: Longest Subarray with at most K zeroes */
public class MaxConsecutivesOnesIII {

    /**
     * Algorithm: Brute Force
     * 1. Define maxLength to keep track of the maximum length of the window.
     * 2. Generate all possible subarrays of the nums array.
     *   - Run a loop with index i from 0 to n-1 (where n is the length of the nums array).
     *   - Define a variable zeroes to keep track of the number of zeroes in the current subarray.
     *   - Run another loop with index j from i to n-1.
     *      - If nums[j] is 0, increment zeroes.
     *      - If zeroes is less than or equal to k, update maxLength with the maximum of maxLength and (j - i + 1).
     *      - If zeroes exceeds k, break out of the inner loop as we cannot have more than k zeroes in the subarray.
     * 3. Finally, return maxLength as a result i.e. maximum length of subarray with at most k zeroes.
     * Time Complexity: O(N^2)
     * Space Complexity: O(1)
     */
    public static int longestOnes(int[] nums, int k) {
        int maxLength = 0;
        for(int i=0; i<nums.length; i++)
        {
            int zeroes = 0;
            for(int j=i; j<nums.length; j++)
            {
                if(nums[j] == 0)
                    zeroes++;

                if(zeroes <= k)
                    maxLength = Math.max(maxLength, j-i+1);
                else
                    break;
            }
        }
        return maxLength;
    }

    /**
     * Algorithm: Optimal
     * 1. We will use a sliding window approach with two pointers, left (l) and right (r).
     * 2. We will maintain a count of zeroes in the current window.
     * 3. As we iterate through the array with the right pointer, we will increment the count of zeroes if nums[r] is 0.
     * 4. If the count of zeroes exceeds k, we will shrink the window from the left until it contains at most k zeroes.
     *    - Every time we need to decrement the count of zeroes if it matches with an element stored at the left pointers.
     *    - And increment the left pointer to shrink the window.
     * 5. If the count of zeroes is less than or equal to k, we will update the maximum length of the window.
     * 6. Finally, we will return the maximum length of the window that contains at most k zeroes.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int longestOnesOptimal(int[] nums, int k) {
        int maxLength = 0;
        int l=0, r=0;
        int zeroes = 0;
        while(r < nums.length)
        {
            if(nums[r] == 0)
                zeroes++;

            while(zeroes > k)
            {
                if(nums[l] == 0)
                    zeroes--;
                l++;
            }
            if(zeroes <= k)
                maxLength = Math.max(maxLength, r-l+1);
            r++;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k = 2;
        System.out.println("Maximum length of subarray with at most " + k + " zeroes: " + longestOnesOptimal(nums, k)); // Output: 7
    }
}
