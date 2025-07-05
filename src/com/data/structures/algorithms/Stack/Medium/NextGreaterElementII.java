package com.data.structures.algorithms.Stack.Medium;

import java.util.Stack;
/** Problem Link: https://leetcode.com/problems/next-greater-element-ii/ */

/**
 * Algorithm:
 * 1. We will use a stack to store the elements.
 * 2. Since the array is circular, we will iterate through the array twice (2*n) and use modulo to access elements.
 * 3. For each element, we will check if it is greater than the top of the stack and stack is not empty.
 *   - If it is greater, we pop an element from the stack until we find a greater element or the stack is empty.
 * 5. If the current index is less than n (the original array length), we will assign the next greater element to the result array.
 *  - If the stack is empty, we assign -1 to that position in the result array.
 *  - If the stack is not empty, we assign the top of the stack to that position in the result array.
 * 6. Finally, we push the current element onto the stack.
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class NextGreaterElementII {

    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n];
        Stack<Integer> st = new Stack<>();
        for(int i=2*n-1; i>=0; i--)
        {
            int ele = nums[i%n];

            while(!st.isEmpty() && st.peek() <= ele)
                st.pop();

            if(i < n)
            {
                if(st.isEmpty())
                    arr[i] = -1;
                else
                    arr[i] = st.peek();
            }

            st.push(ele);
        }
        return arr;
    }

     public static void main(String[] args) {
         int[] nums = {1, 2, 1};
         int[] result = nextGreaterElements(nums);
         for (int num : result) {
             System.out.print(num + " ");
         }
     }
}
