package com.data.structures.algorithms.Stack.Medium;

import java.util.ArrayList;
import java.util.Stack;

public class StockSpanProblem {

    /**
     * Algorithm: Brute Force Approach
     * Take an ArrayList to store the span of each stock price.
     * 1. We will iterate through the array from left to right, intializing count as 1.
     * 2. For each element, we will count how many consecutive elements to the left are less than or equal to the current element.
     *    - If we find an element that is greater than the current element, we will break the loop, as it breaks the span.
     *    - We will increment the count for each element that is less than or equal to the current element.
     * 3. We will store the count in an ArrayList.
     * Time Complexity: O(n^2)
     * Space Complexity: O(n)
     */
    public static ArrayList<Integer> calculateSpan(int[] arr) {
        ArrayList<Integer> list = new  ArrayList<Integer>();
        list.add(1);
        for(int i=1; i<arr.length; i++)
        {
            int count = 1;
            for(int j=i-1; j>=0; j--)
            {
                if(arr[j] > arr[i])
                    break;
                count++;
            }
            list.add(count);
        }
        return list;
    }

    /**
     * Algorithm: Optimal Approach (Using Stack)
     * Take array list to store the count of spans of each element.
     * 1. Initialize Two Stacks
     *     - main stack (st) → Maintains a monotonic decreasing stack (stores elements in decreasing order).
     *     - temp stack (temp) → Temporary stack used for rearranging elements.
     * 2. For each element:
     *    - we will assign count as 1.
     *    - We will pop all elements from the main stack that are less than or equal to the current element and push them into the temporary stack.
     *    - The count of elements popped from the stack gives us the span for the current element.
     *    - We will add the count to the list.
     *    - After processing the current element, we will push all elements from the temporary stack back to the main stack to maintain the order.
     *    - Finally, we will push the current index onto the main stack.
     * 3. Return the list containing the spans of each stock price.
     * Time Complexity: O(n)
     * Space Complexity: O(2n)
     */
    public static ArrayList<Integer> calculateSpanBetterApproach(int[] arr) {
        ArrayList<Integer> list = new  ArrayList<Integer>();
        Stack<Integer> st = new Stack<>();
        Stack<Integer> temp = new Stack<>();
        for(int i=0; i<arr.length; i++)
        {
            int ele = arr[i];
            int count = 1;

            while(!st.isEmpty() && arr[st.peek()] <= ele)
            {
                temp.push(st.pop());
                count++;
            }

            list.add(count);
            while(!temp.isEmpty())
                st.push(temp.pop());

            st.push(i);
        }
        return list;
    }

    /**
     * Algorithm: Optimal Approach (Using Stack)
     * Take an array list to store the count of spans of each element.
     * 1. Initialize a stack to maintain indices of elements in a monotonic decreasing order.
     * 2. For each element:
     *    - We will pop elements from the stack while the top element in the stack is less than or equal to the current element.
     *    - If the stack is empty, it means there are no previous elements that are greater than the current element, so we add the span as i + 1.
     *    - If the stack is not empty, we calculate the span as the difference between the current index and the index of the top element in the stack.
     *    - We then push the current index onto the stack.
     * 3. Return the list containing the spans of each stock price.
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static ArrayList<Integer> calculateSpanOptimalApproach(int[] arr) {
        ArrayList<Integer> list = new  ArrayList<>();
        Stack<Integer> st = new Stack<>();
        for(int i=0; i<arr.length; i++)
        {
            int currentPrice = arr[i];

            while(!st.isEmpty() && arr[st.peek()] <= currentPrice)
                st.pop();

            if(st.isEmpty())
                list.add(i+1);
            else
                list.add(i-st.peek());

            st.push(i);
        }
        return list;
    }

    public static void main(String[] args) {
        int[] stockPrices = {100, 80, 60, 70, 60, 75, 85};
        ArrayList<Integer> spans = calculateSpanOptimalApproach(stockPrices);
        System.out.println(spans); // Output: [1, 1, 1, 2, 1, 4, 6]
    }
}
