package com.data.structures.algorithms.Stack.Medium;

import java.util.Stack;

/** Problem Link: https://leetcode.com/problems/remove-k-digits/ */
/**
 * Algorithm:
 * 1. We will use a stack to store the digits character of the number.
 * 2. We will iterate through each digit of the number.
 *    - To keep smallest number first:
 *    - If the stack is not empty and the top element of the stack is greater than the current digit, and we still have k elements to remove, we pop the top element from the stack.
 *    -  We push the current digit onto the stack.
 * 3. After iterating through all digits, if we haven't removed all k elements, we pop the remaining k elements from the top of the stack.
 * 4. Finally, we build the result string from the stack and remove leading zeros.
 * 5. If the result string is empty, we return "0".
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class RemoveKDigits {

    public static String removeKDigits(String num, int k) {
        int n = num.length();
        // Edge cases
        if(k == 0)
            return num;
        if(k == n)
            return "0";

        Stack<Character> stack = new Stack<>();
        for(int i=0; i<n; i++)
        {
            char ch = num.charAt(i);
            // If the stack is not empty and the top element of the stack is greater than the current character
            // and we still have k elements to remove, pop the top element from the stack because we want to keep the smallest possible number
            while(!stack.isEmpty() && k!=0 && stack.peek() > ch)
            {
                stack.pop();
                k--;
            }
            stack.push(ch);
        }

        // If we haven't deleted all the k-elements, then pop the remaining k elements from top of the stack
        while(k-->0)
            stack.pop();

        StringBuilder str = new StringBuilder();
        for(char ch : stack)
            str.append(ch);

        // Remove leading zeros
        while(str.charAt(0) == '0' && str.length() > 1)
            str.deleteCharAt(0);

        return str.toString();
    }

    public static void main(String[] args) {
        String num = "1432219";
        int k = 3;
        String result = removeKDigits(num, k);
        System.out.println("Result after removing " + k + " digits: " + result);
    }
}
