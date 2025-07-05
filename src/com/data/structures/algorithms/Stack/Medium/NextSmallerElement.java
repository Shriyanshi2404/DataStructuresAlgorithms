package com.data.structures.algorithms.Stack.Medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/** Problem link: https://www.geeksforgeeks.org/next-smaller-element/ */
/**
 * Algorithm:
 * 1. We will use a stack to store the elements. So take empty stack and empty arraylist
 * 2. Since for last element there is no next smaller element, we will add -1 to the list and push the last element of array into stack
 * 3. We will iterate through the array from right to left.
 * 4. Run a loop from 2nd last element to 0th element of the array
 * 5. If the current element is greater than the top element of the stack
 * - add top element of stack to the list and push the current element into stack
 * 6. If the current element is less than or equal to the top element of the stack
 * - we will pop all the greater or equal element from the stack until we find a smaller element or the stack is empty.
 * - if stack is empty, add -1 to the list
 * - if stack is not empty, add top element of stack to the list
 * - at last push the current element into stack
 * 7. At last reverse the list and return it
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class NextSmallerElement {

    public static ArrayList<Integer> nextSmallerElement(int[] arr){
        int n = arr.length;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(-1);

        Stack<Integer> st = new Stack<>();
        st.push(arr[n-1]);

        for(int i=n-2; i>=0; i--)
        {
            int ele = arr[i];

            // if current element is greater than top element of stack
            if(ele > st.peek())
            {
                int top_ele = st.peek();
                list.add(top_ele);
                st.push(ele);
            }
            // if current element is less than or equal to top element of stack
            else if(ele <= st.peek())
            {
                // pop all the greater or equal element from the stack until we find a smaller element or the stack is empty
                while(!st.isEmpty() && st.peek() >= ele)
                    st.pop();

                if(st.isEmpty())
                    list.add(-1);
                else
                    list.add(st.peek());
                st.push(ele);
            }
        }
        Collections.reverse(list);
        return list;
    }

    public static void main(String[] args) {
        int[] arr = {4, 8, 5, 2, 25};
        ArrayList<Integer> list = nextSmallerElement(arr);
        System.out.println(list);
    }

}
