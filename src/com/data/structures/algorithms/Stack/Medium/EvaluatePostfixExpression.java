package com.data.structures.algorithms.Stack.Medium;

import java.util.Stack;
/** Problem link: <a href="https://leetcode.com/problems/evaluate-reverse-polish-notation/">...</a> */

/**
 * Algorithm:
 * 1. Initialize an empty stack.
 * 2. Iterate through each token in the input array:
 * 3. If the token is an operator ('+', '-', '*', '/'):
 * - Pop the top two elements from the stack (let's call them A and B).
 * - Perform the operation B operator A (note the order of A and B).
 * - Push the result back onto the stack.
 * 4. If the token is a number, convert it to an integer and push it onto the stack.
 * 5. After processing all tokens, the stack will contain one element, which is the result of the postfix expression.
 * 6. Return the result by popping the top element from the stack.
 */
public class EvaluatePostfixExpression {

    public static int evalRPN(String[] tokens) {
        Stack<Integer> st = new Stack<>();
        for(int i=0; i<tokens.length; i++)
        {
            String str = tokens[i];
            if(str.equals("+")
                    || str.equals("-")
                    || str.equals("*")
                    || str.equals("/")) {
                int res = 0;
                int A = st.pop();
                int B = st.pop();
                if(str.equals("+"))
                    res = B+A;
                else if(str.equals("-"))
                    res = B-A;
                else if(str.equals("*"))
                    res = B*A;
                else
                    res = B/A;
                st.push(res);
            }
            else
            {
                int ele = Integer.parseInt(str);
                st.push(ele);
            }
        }
        return st.pop();
    }

    public static void main(String[] args) {
        String[] tokens = {"2", "1", "+", "3", "*"};
        int result = evalRPN(tokens);
        System.out.println("Result of the postfix expression: " + result); // Output: 9
    }
}
