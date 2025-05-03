package com.data.structures.algorithms.Stack.Easy;

import java.util.Stack;

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
