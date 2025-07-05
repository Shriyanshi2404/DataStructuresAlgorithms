package com.data.structures.algorithms.Stack.Medium;

import java.util.Stack;

public class PreviousGreaterElement {

    public static int[] leftGreater(int[] arr)
    {
        Stack<Integer> st = new Stack<>();
        st.push(arr[0]);

        int[] result = new int[arr.length];
        result[0] = -1; // For the first element, there is no previous greater element
        int index = 1;

        for(int i=1; i<arr.length; i++)
        {
            int ele = arr[i];
            if(ele < st.peek())
            {
                result[index] = st.peek();
                index++;
                st.push(ele);
            }
            else if(ele >= st.peek()) {
                while (!st.isEmpty() && st.peek() <= ele)
                    st.pop();

                if (st.isEmpty())
                    result[index] = -1; // No greater element found
                else
                    result[index] = st.peek(); // Previous greater element found

                st.push(ele);
                index++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 2, 10, 8};
        int[] result = leftGreater(arr);
        for(int i : result) {
            System.out.print(i + " ");
        }
    }
}
