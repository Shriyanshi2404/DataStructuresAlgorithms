package com.data.structures.algorithms.Stack.Medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class NextGreaterElement {
    
    public static ArrayList<Integer> nextLargerElement(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(-1);
        
        Stack<Integer> st = new Stack<>();
        // adding last element of array into stack
        st.push(arr[arr.length-1]);
        
        for(int i=arr.length-2; i>=0; i--)
        {
            int ele = arr[i];
            
            if(ele < st.peek())
            {
                int top_ele = st.peek();
                list.add(top_ele);
                st.push(ele);
            }
            else if(ele >= st.peek())
            {
                while(!st.isEmpty() && st.peek() <= ele)
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
        int[] arr = {4, 5, 2, 25};
        ArrayList<Integer> list = nextLargerElement(arr);
        System.out.println(list);
    }
}
