package com.data.structures.algorithms.Stack.Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Problem Link: https://leetcode.com/problems/sliding-window-maximum/
 * Similar to: Sliding Window Maximum
 */
public class SlindingMaximumWindow {

    /**
     * Algorithm: Brute Force
     * 1. We will use a sliding window approach with two pointers, left (l) and right (r).
     * 2. We will maintain a variable to store the maximum element in the current window.
     * 3. As we iterate through the array with the right pointer, we will check if the current element is greater than the maximum element.
     *    - If the current element is greater, then we will update the maximum element.
     * 4. If the size of the window exceeds k, we will shrink the window from the left until it is equal to k.
     *    - Also recompute the maximum element in the new window if the maximum element is no longer in the window.
     * 6. Finally, we will return an array containing the maximum elements in each sliding window.
     * Time Complexity: O(n^k)
     * Space Complexity: O(n)
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int maxEle = 0;
        ArrayList<Integer> arr = new ArrayList<>();
        int l=0, r=0;
        while(r < nums.length)
        {
            int ele = nums[r];
            while(r-l+1 > k)
            {
                if(maxEle == nums[l])
                {
                    // Recompute max in the new window
                    maxEle = Integer.MIN_VALUE;
                    for(int i=l+1; i<=r; i++)
                        maxEle = Math.max(maxEle, nums[i]);
                }
                l++;
            }

            maxEle = Math.max(ele, maxEle);
            if(r-l+1 == k)
            {
                arr.add(maxEle);
            }
            r++;
        }
        int[] res = new int[arr.size()];
        int i = 0;
        for(int ele : arr)
        {
            res[i] = ele;
            i++;
        }
        return res;
    }


    /**
     * Algorithm: Sliding Window with Deque
     * 1. We will use a deque to store the indices of the elements in the current window.
     * 2. As we iterate through the array, we will maintain the deque such that it contains the indices of the maximum elements in the current window.
     * 3. First, we iterate through the first k elements and add their indices to the deque.
     *    - If the last-indexed element stored in deque is smaller than the current element, we will remove the last index from the deque,
     *    - and keep removing until we find a index which has an element that is greater than or equal to the current element.
     *    - And keep adding the current index to the last of deque.
     * 4. After processing the first k elements, we will start sliding the window.
     *    - Add the maximum element of the current window (which is at the front of the deque) to the result array.
     *    - For each new element, we will check if the first element in the deque is out of the current window (i.e., its index is less than or equal to i-k).
     *    - If it is, we will remove it from the front of the deque because it is no longer part of the current window.
     *    - Then we will repeat the process of removing elements from the back of the deque if they are smaller than the current element.
     *    - Finally, we will add the current index to the back of the deque.
     * 5. After processing all elements, we will add the maximum element of the last window to the result array.
     * 6. Finally, we will return an array containing the maximum elements in each sliding window.
     * Time Complexity: O(n)
     * Space Complexity: O(k)
     */
    public static int[] maximumSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int l=0;
        int[] arr = new int[nums.length-k+1];

        Deque<Integer> dq = new LinkedList<Integer>();
        // finding maxElement in 1st window
        for(int i=0; i<k; i++)
        {
            // if last index element of deque is smaller than current element,
            // then remove the index from last in deque
            while(!dq.isEmpty() && nums[dq.peekLast()] <= nums[i])
                dq.removeLast();

            // add current index
            dq.addLast(i);
        }

        // now run for other windows from k->n
        for(int i=k; i<n; i++)
        {
            // adding the maxElement so far
            arr[l] = nums[dq.peek()];
            l++;

            // removing the elements from deque which is not part of next window
            while(!dq.isEmpty() && dq.peek() <= i-k)
            {
                dq.removeFirst();
            }

            // if last index element of deque is smaller than current element,
            // then remove the index from last in deque
            while(!dq.isEmpty() && nums[dq.peekLast()] <= nums[i])
                dq.removeLast();

            dq.addLast(i);
        }
        arr[l] = nums[dq.peek()];
        return arr;
    }


    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] result = maximumSlidingWindow(nums, k);
        System.out.println("Maximums in sliding window: " + Arrays.toString(result)); // Output: [3, 3, 5, 5, 6, 7]
    }
}
