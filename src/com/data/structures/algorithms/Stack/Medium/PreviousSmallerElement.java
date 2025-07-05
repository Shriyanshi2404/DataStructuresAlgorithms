package com.data.structures.algorithms.Stack.Medium;

import java.util.Stack;

/** Problem link: https://www.geeksforgeeks.org/problems/smallest-number-on-left3403/1 */
/**
 * Algorithm:
 * 1. We will use a stack to store the elements, so we will take an empty stack and an result array to store the result.
 * 2. Initialize an array to store the result, with the first element set to -1 as for 0th index element there is no previous smaller element.
 * 3. Iterate through the array from the second element to the last:
 *    - If the current element is greater than the top of the stack, add the top of the stack to the result and push the current element onto the stack.
 *    - If the current element is less than or equal to the top of the stack, pop elements from the stack until we find a smaller element or the stack is empty.
 *      - If the stack is empty, add -1 to the result; otherwise, add the top of the stack to the result.
 *      - Push the current element onto the stack.
 * 4. Return the result array.
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class PreviousSmallerElement {

    public static int[] leftSmaller(int[] arr) {
        Stack<Integer> st = new Stack<>();
        st.push(arr[0]);

        int[] result = new int[arr.length];
        result[0] = -1;
        int index = 1;
        for(int i=1;i<arr.length;i++)
        {
            int ele = arr[i];
            if(ele > st.peek())
            {
                result[index] = st.peek();
                st.push(ele);
                index++;
            }
            else if(ele <= st.peek())
            {
                while(!st.isEmpty() && st.peek() >= ele)
                    st.pop();

                if(st.isEmpty())
                    result[index] = -1 ;
                else
                    result[index] = st.peek();
                st.push(ele);
                index++;
            }
        }
        return result;

    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 2, 10, 8};
        int[] result = leftSmaller(arr);
        for(int i : result) {
            System.out.print(i + " ");
        }
    }
}
