package com.data.structures.algorithms.Stack.Medium;

import java.util.Stack;

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

    static void findNSE(int[] arr, int[] NSE)
    {
        // stores indices of mimimum element present in next subarray
        Stack<Integer> st = new Stack<>();
        for(int i=arr.length-1; i>=0; i--)
        {
            int ele = arr[i];
            while(!st.isEmpty() && arr[st.peek()] >= ele)
                st.pop();
            if(st.isEmpty())
                NSE[i] = arr.length;
            else
                NSE[i] = st.peek();

            st.push(i);
        }
    }

    static void findPSE(int[] arr, int[] PSE)
    {
        // stores indices of mimimum element present in a previous subarray
        Stack<Integer> st = new Stack<>();
        for(int i=0; i<arr.length; i++)
        {
            int ele = arr[i];
            while(!st.isEmpty() && arr[st.peek()] > ele)
                st.pop();

            if(st.isEmpty())
                PSE[i] = -1;
            else
                PSE[i] = st.peek();

            st.push(i);
        }
    }

    static void findNGE(int[] arr, int[] NGE)
    {
        // stores indices of maximum element present in next subarray
        Stack<Integer> st = new Stack<>();
        for(int i=arr.length-1; i>=0; i--)
        {
            int ele = arr[i];
            while(!st.isEmpty() && arr[st.peek()] <= ele)
                st.pop();

            if(st.isEmpty())
                NGE[i] = arr.length;
            else
                NGE[i] = st.peek();

            st.push(i);
        }
    }

    static void findPGE(int[] arr, int[] PGE)
    {
        // stores indices of maximum element present in a previous subarray
        Stack<Integer> st = new Stack<>();
        for(int i=0; i<arr.length; i++)
        {
            int ele = arr[i];
            while(!st.isEmpty() && arr[st.peek()] < ele)
                st.pop();

            if(st.isEmpty())
                PGE[i] = -1;
            else
                PGE[i] = st.peek();

            st.push(i);
        }
    }

    /**
     Algorithm:
     1. We will use the concept of Next Smaller Element (NSE), Previous Smaller Element (PSE),
        Next Greater Element (NGE) and Previous Greater Element (PGE) to find the sum of subarrays minimums and maximums.
     2. For each element in the array, we will find:
        - The number of subarrays in which it is the minimum element
        - The number of subarrays in which it is the maximum element
     3. We will calculate the contribution of each element to the sum of subarrays minimums and maximums.
     4. Finally, we will return the difference between the sum of subarrays maximums and minimums.
     5. The final result will be the sum of all subarray ranges.
     6. The contribution of each element to the sum of subarrays ranges is calculated as:
        - For minimums: sumMin += leftMin * rightMin * nums[i]
        - For maximums: sumMax += leftMax * rightMax * nums[i]
        where leftMin and rightMin are the number of subarrays in which the element is the minimum,
        and leftMax and rightMax are the number of subarrays in which the element is the maximum.
     7. The final result is the difference between the sum of subarrays maximums (sumMax) and minimums (sumMin).
     Time Complexity: O(n)
     Space Complexity: O(n)
     */
    public static long findSubArrayRanges(int[] nums) {
        int n = nums.length;

        // For finding sum of subarrays minimums
        int[] NSE = new int[n];
        int[] PSE = new int[n];
        findNSE(nums, NSE);
        findPSE(nums, PSE);

        // For finding sum of subarrays maximums
        int[] NGE = new int[n];
        int[] PGE = new int[n];
        findNGE(nums, NGE);
        findPGE(nums, PGE);
        long sumMin = 0;
        long sumMax = 0;
        for (int i=0; i<n; i++)
        {
            /**
             * PSE[i] is the index of the Previous Smaller Element for nums[i].
             * (i - PSE[i]) gives the count of possible starting positions for subarrays ending at i where nums[i] is the minimum.
             * This value is used to compute the total contribution of nums[i] as the minimum in all subarrays.
             */
            long leftMin = i - PSE[i];
            long rightMin = NSE[i] - i;
            long leftMax = i - PGE[i];
            long rightMax = NGE[i] - i;

            sumMin += leftMin * rightMin * (long) nums[i];
            sumMax += leftMax * rightMax * (long) nums[i];
        }
        return sumMax - sumMin;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        long result = findSubArrayRanges(nums);
        System.out.println("Sum of subarray ranges: " + result); // Output: 4
    }
}
