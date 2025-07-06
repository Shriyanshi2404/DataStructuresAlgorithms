package com.data.structures.algorithms.Stack.Hard;

import java.util.ArrayList;
import java.util.Stack;

public class SlidingWindowMaximum {

    /**
     * Helper function to find the maximum element in a subarray from start to end.
     * This function uses a stack to keep track of the maximum elements.
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int findMax(int[] arr, int start, int end)
    {
        int max = 0;
        Stack<Integer> st = new Stack<>();
        for(int i=start; i<end; i++)
        {
            int ele = arr[i];
            while(!st.isEmpty() && st.peek() <= ele)
                st.pop();

            if(st.isEmpty())
                max = ele;
            else
                max = Math.max(max, ele);
            st.push(ele);
        }
        return max;
    }

    /**
     * Algorithm:
     * 1. We will iterate through the array from start to end-k+1.
     * 2. For each index, we will find the maximum element in the subarray of size k starting from that index.
     * 3. We will store the maximum elements in an ArrayList and then convert it to an array.
     * Time Complexity: O(n*k)
     * Space Complexity: O(n)
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i=0; i<=nums.length-k; i++)
        {
            arr.add(findMax(nums, i, i+k));
        }
        int i=0;
        int[] res = new int[arr.size()];
        for(int ele : arr)
        {
            res[i] = ele;
            i++;
        }
        return res;
    }

    /**
     * Brute Force Approach:
     * 1. We will iterate through the array from start to end-k+1.
     * 2. For each index, we will find the maximum element in the subarray of size k starting from that index.
     * 3. We will store the maximum elements in an ArrayList and then convert it to an array.
     * Time Complexity: O(n*k)
     * Space Complexity: O(n)
     */
    public static int[] maximumSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i=0; i<=nums.length-k; i++)
        {
            int maxSoFar = nums[i];
            for(int j=i; j<i+k; j++)
            {
                maxSoFar = Math.max(maxSoFar, nums[j]);
            }
            arr.add(maxSoFar);
        }
        int i=0;
        int[] res = new int[arr.size()];
        for(int ele : arr)
        {
            res[i] = ele;
            i++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] result = maxSlidingWindow(nums, k);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
