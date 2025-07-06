package com.data.structures.algorithms.Stack.Hard;

import java.util.Stack;

/** Problem Link: https://leetcode.com/problems/largest-rectangle-in-histogram/ */
public class LargestRectangleInHistogram {

    /**
     * Time Complexity: O(2N)
     * Space Complexity: O(2N)
     */
    public static void findNSE(int[] arr, int[] NSE)
    {
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

    /**
     * Time Complexity: O(2N)
     * Space Complexity: O(2N)
     */
    public static void findPSE(int[] arr, int[] PSE)
    {
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

    /**
     * Algorithm: Pre compute NSE and PSE to find width of rectangle
     * 1. We will find the next smaller element (NSE) and previous smaller element (PSE) for each element in the array.
     * 2. For each element, we will calculate the width of the rectangle that can be formed using that element.
     * 3. The width is determined by the indices of the NSE and PSE.
     * 4. Finally, we will calculate the area for each rectangle
     *    - area = height * width, where height is the height of the rectangle (the value of the element) and width is the difference between the indices of NSE and PSE.
     * 5. Compare the area with the maximum area and update it if required
     * 6. Return the maximum area found.
     * Time Complexity: 2*O(2N) + O(N) = O(4N) + O(N) = O(5N)
     * Space Complexity: O(2N) + O(2N) = O(4N)
     */

    /**
     * Time Complexity: O(N)
     * Space Complexity: O(2N)
     */
    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] NSE = new int[n];
        int[] PSE = new int[n];
        findNSE(heights, NSE);
        findPSE(heights, PSE);

        int maxArea = 0;
        for(int i=0; i<n; i++)
        {
            int leftSmallerElementIndex = PSE[i];
            int rightSmallertElementIndex = NSE[i];
            int width = rightSmallertElementIndex - leftSmallerElementIndex - 1;
            int area = heights[i] * width;
            maxArea = Math.max(area, maxArea);
        }
        return maxArea;
    }

    /** Optimized Approach: Using Stack
     * Intuition: The intuition behind the approach is the same as finding the smaller element on both sides but in an optimized way using the concept of the next greater element and the next smaller element.
     * 1. We will iterate through the heights array and maintain a stack to keep track of the indices of heights
     * 2. For each element height, we will check if it is smaller than the height of the index stored at the top of the stack,
     *    - it means the rectangle with the height of the bar at the top of the stack ends here.
     * 3. If it is smaller, we pop the index from the stack and calculate the area using that height as the smallest height.
     *    - If the stack is empty, it means there is no previous smaller element, so we use -1 as PSE
     *    - If the stack is not empty, we use the index of the new top of the stack as PSE.
     *    - NSE is the current index.
     *    - The width is calculated as (NSE - PSE - 1).
     *    - Finally, calculates the area for the popped bar as the smallest bar as (height * width).
     *    - We update the maximum area if the calculated area is greater than the current maximum area.
     * 4. We continue this loop until we find a height that is smaller than or equal to the current height.
     * 5. Finally, we calculate the area for any remaining heights in the stack, considering the end of the histogram i.e heights.length() as the next smaller element.
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public static int largestRectangleAreaInHistogram(int[] heights) {
        Stack<Integer> st = new Stack<>();
        int maxArea = 0;
        for(int i=0; i<heights.length; i++)
        {
            int ele = heights[i];

            // computing the previous smaller element
            while(!st.isEmpty() && heights[st.peek()] > ele)
            {
                int top_ele_index = st.peek();
                st.pop();
                int nse = i;
                int pse = -1;

                if(st.isEmpty())
                    pse = -1;
                else
                    pse = st.peek();

                int area = heights[top_ele_index] * (nse-pse-1);
                maxArea = Math.max(area,maxArea);
            }
            st.push(i);
        }
        while(!st.isEmpty())
        {
            int top_ele_index = st.peek();
            st.pop();
            int nse = heights.length;
            int pse = -1;
            if(st.isEmpty())
                pse = -1;
            else
                pse = st.peek();

            int area = heights[top_ele_index] * (nse-pse-1);
            maxArea = Math.max(area,maxArea);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {2,1,5,6,2,3};
        System.out.println("Largest Rectangle Area: " + largestRectangleAreaInHistogram(heights));
    }
}
