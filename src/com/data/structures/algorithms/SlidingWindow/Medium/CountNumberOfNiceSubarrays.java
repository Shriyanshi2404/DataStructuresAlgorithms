package com.data.structures.algorithms.SlidingWindow.Medium;

/** Problem Link: https://leetcode.com/problems/count-number-of-nice-subarrays/ */
public class CountNumberOfNiceSubarrays {

    /**
     * Algorithm: (Counting Subarrays with Sum Equal to K)
     * Idea: Count subarrays with sum <= K say count as 'x' and Count subarrays with sum <= K-1 say count as 'y'
     * And then do (x-y) to get the count of subarrays with sum equal to K.
     * 1. We will use a sliding window approach to find the number of nice subarrays.
     * 2. We will maintain two pointers, l and r, to represent the left and right ends of the window.
     * 3. We will calculate the sum of the current window and check if it is less than or equal to the goal.
     *    - If it is, we will count all the subarrays that lie between these two pointers.
     *    - If the sum exceeds the goal, we will shrink the window from the left until it is less than or equal to the goal.
     * 6. Finally, we will return the count of nice subarrays with a sum equal to k.
     * NOTE: while adding the elements to the sum or subtracting the elements from the sum,
     *       we will transform the array such that each number is replaced with nums[i] % 2,
     *       Without %2, sum would represent actual values (e.g., 1 + 2 + 1...) instead of the count of odd numbers, which is not what we want.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int findSumLessThanEqualToGoal(int[] nums, int goal, int count)
    {
        if(goal < 0)
            return 0;
        int l=0, r=0;
        int sum = 0;
        while(r < nums.length)
        {
            sum += nums[r]%2;
            // if sum exceed goal, then shrink from left
            while(sum > goal && l<nums.length)
            {
                sum -= nums[l]%2;
                l++;
            }
            if(sum <= goal)
            {
                // to count all the subarrays that lies between these two pointers
                // we will simply add current length as it will give count
                count = count + (r-l+1);
            }
            r++;
        }
        return count;
    }

    /**
     * Function to count the number of nice subarrays.
     * A nice subarray is defined as an array that contains exactly k odd numbers.
     * 1. Transform the array:
     *    - each number is replaced with nums[i] % 2, so: Now the array represents oddness: 1 = odd and 0 = even
     * 2. Use the sliding window technique to count subarrays with exactly k odd numbers, considering K as sum of odd numbers.
     */
    public static int numberOfSubarrays(int[] nums, int k) {
        for(int i=0; i<nums.length; i++)
        {
            if(nums[i] % 2 == 0)
                nums[i] = 0;
            else
                nums[i] = 1;
        }

        int count1 = findSumLessThanEqualToGoal(nums, k, 0);
        int count2 = findSumLessThanEqualToGoal(nums, k-1, 0);
        return count1-count2;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 1, 1};
        int k = 3;
        System.out.println("Number of nice subarrays: " + numberOfSubarrays(nums, k)); // Output: 2
    }
}
