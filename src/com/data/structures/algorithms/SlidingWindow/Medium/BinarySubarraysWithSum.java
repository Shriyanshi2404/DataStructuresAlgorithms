package com.data.structures.algorithms.SlidingWindow.Medium;

/** Problem Link: https://leetcode.com/problems/binary-subarrays-with-sum/ */
public class BinarySubarraysWithSum {

    /**
     * Algorithm: (Counting Subarrays with Sum Equal to K)
     * Idea: Count subarrays with sum <= K say count as 'x' and Count subarrays with sum <= K-1 say count as 'y'
     * And then do (x-y) to get the count of subarrays with sum equal to K.
     * 1. We will use a sliding window approach to find the number of subarrays with a sum equal to the goal.
     * 2. We will maintain two pointers, l and r, to represent the left and right ends of the window.
     * 3. We will calculate the sum of the current window and check if it is less than or equal to the goal.
     *    - If it is, we will count all the subarrays that lie between these two pointers.
     *    - If the sum exceeds the goal, we will shrink the window from the left until it is less than or equal to the goal.
     * 6. Finally, we will return the count of subarrays with a sum equal to the goal.
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
            sum = sum + nums[r];
            // if sum exceed goal, then shrink from left
            while(sum > goal && l<nums.length)
            {
                sum = sum - nums[l];
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
    public static int numSubarraysWithSum(int[] nums, int goal) {
        int count1 = findSumLessThanEqualToGoal(nums, goal, 0);
        System.out.println(count1);
        int count2 = findSumLessThanEqualToGoal(nums, goal-1, 0);
        return count1-count2;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, 1, 0, 1};
        int goal = 2;
        System.out.println("Number of subarrays with sum " + goal + ": " + numSubarraysWithSum(nums, goal)); // Output: 4
    }
}
