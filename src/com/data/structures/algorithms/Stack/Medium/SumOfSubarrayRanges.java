package com.data.structures.algorithms.Stack.Medium;

/** Problem Link: https://leetcode.com/problems/sum-of-subarray-ranges/ */
public class SumOfSubarrayRanges {

    /**
     * Algorithm:
     * 1. We will iterate through each element of the array, intialising a sum variable to 0.
     * 2. We will run a loop from i=0 to n-1, where n is the length of the array
     *    - Assign current element as min and max
     * 3. Now, run a nested loop from j=i+1 to n-1 for checking the subarray starting from i
     *    - For each element, we will find the minimum and maximum in the subarray starting from that element and update if required.
     *    - We will calculate the difference between max and min and add it to the sum.
     * 4. Finally, we will return the total sum.
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public static long subArrayRanges(int[] nums) {
        long sum = 0;
        for(int i=0; i<nums.length; i++)
        {
            int min = nums[i];
            int max = nums[i];
            for(int j=i+1; j<nums.length; j++)
            {
                if(nums[j] < min)
                    min = nums[j];
                if(nums[j] > max)
                    max = nums[j];
                sum += (max-min);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        long result = subArrayRanges(nums);
        System.out.println("Sum of subarray ranges: " + result); // Output: 4
    }
}
