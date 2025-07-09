package com.data.structures.algorithms.SlidingWindow.Easy;
/** * Problem Link: https://www.geeksforgeeks.org/problems/max-sum-subarray-of-size-k5313/1 */
public class MaxSumSubarrayOfSizeK {

    /**
     * Algorithm : Two pointer approach
     * 1. We will use a sliding window approach to find the maximum sum of a subarray of size k.
     * 2. Initialize two pointers, l and r, to represent the left and right ends of the window.
     * 3. Calculate the sum of the first k elements, that is the 1st window of size k.
     * 4. Define the maximum sum variable to store the maximum sum found so far, for now initialize it to the sum of the first k elements.
     * 5. Now, run a while loop until the right pointer reaches the end of the array:
     *    - subtract the element at the left pointer from the sum, as it is no longer in the new window.
     *    - Slide the window by moving the left pointer and right pointer one step to the right.
     *    - Add the next element to the right pointer to the sum.
     *    - Update the maximum sum if the current sum is greater than the previous maximum.
     * 6. Repeat until we reach the end of the array.
     * 7. Finally, return the maximum sum found.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int maximumSumSubarray(int[] arr, int k) {
        int l = 0;
        int r = k-1;
        int sum = 0;
        for(int i=l;i<=r;i++)
            sum += arr[i];

        int maxSum = sum;
        while(r < arr.length-1)
        {
            sum = sum - arr[l];
            l++;
            r++;
            sum = sum + arr[r];
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;
        System.out.println("Maximum sum of subarray of size " + k + ": " + maximumSumSubarray(arr, k));
    }
}
