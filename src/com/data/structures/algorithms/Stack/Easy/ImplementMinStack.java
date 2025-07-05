package com.data.structures.algorithms.Stack.Easy;

import java.util.Stack;

/** Problem link: <a href="https://leetcode.com/problems/min-stack/description/">...</a> */

/**
 * Algorithm: Take two stacks, one for the main stack and another for keeping track of the minimum elements.
 * For Push operation:
 * - If the main stack is empty, push the element onto both stacks.
 * - If the new element is less than or equal to the current minimum (top of the min stack), push the element onto both stacks.
 * - If the new element is greater than the current minimum, push it only onto the main stack.
 * For pop operation:
 * - If the main stack is not empty, pop the top element and check if it is equal to the top of the min stack, if yes, pop from the min stack as well.
 * - If the main stack is empty, then return.
 * For top operation:
 * - Return the top element of the main stack if it is not empty, otherwise return -1.
 * For getMin operation:
 * - Return the top element of the min stack if it is not empty, otherwise return -1.
 */
public class ImplementMinStack {
    static class MinStack {
        Stack<Integer> st;
        Stack<Integer> minStack;

        public MinStack()
        {
            st = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val){
            if(st.isEmpty())
            {
                st.push(val);
                minStack.push(val);
            }
            else
            {
                st.push(val);
                int minEle = minStack.peek();
                if(val <= minEle)
                    minStack.push(val);
            }
        }

        public void pop(){
            if(st.isEmpty())
                return;

            int topEle = st.pop();
            if(topEle == minStack.peek())
                minStack.pop();
        }

        public int top(){
            if(st.isEmpty())
                return -1;

            return st.peek();
        }

        public int getMin(){
            if(minStack.size() == 0)
                return -1;

            return minStack.peek();
        }
    }

    public static void main(String[] args) {
        String[] operations = {"MinStack", "push", "push", "push", "getMin", "pop", "top", "getMin"};
        int[][] values = {{}, {-2}, {0}, {-3}, {}, {}, {}, {}};

        processMinStackOperations(operations, values);
    }

    public static void processMinStackOperations(String[] operations, int[][] values) {
        MinStack minStack = null;
        for (int i = 0; i < operations.length; i++) {
            String op = operations[i];
            switch (op) {
                case "MinStack":
                    minStack = new MinStack();
                    System.out.println("null");
                    break;
                case "push":
                    minStack.push(values[i][0]);
                    System.out.println("null");
                    break;
                case "pop":
                    minStack.pop();
                    System.out.println("null");
                    break;
                case "top":
                    System.out.println(minStack.top());
                    break;
                case "getMin":
                    System.out.println(minStack.getMin());
                    break;
                default:
                    System.out.println("Unknown operation: " + op);
            }
        }
    }
}
