package com.data.structures.algorithms.Stack.Medium;

import java.util.Stack;

/** Problem Link: https://www.geeksforgeeks.org/problems/expression-contains-redundant-bracket-or-not/1/ */

/**
 * Algorithm:
 * 1. Initialize an empty stack.
 * 2. Traverse each character in the string:
 * 3. If the character is a letter or an opening bracket '(', push it onto the stack.
 * 4. If the character is an operator ('+', '-', '*', '/'), push it onto the stack.
 * 5. If the character is a closing bracket ')':
 *    - Initialize a flag to true.
 *    - While the stack is not empty and the top element is not an opening bracket '(':
 *      - Pop the top element from the stack.
 *      - If the popped element is an operator, set the flag to false. (because it means there was an operator before the closing bracket so it is not redundant)
 *      - Pop the opening bracket '(' from the stack.
 *    - If the flag is still true after popping, it means there were no operators between the brackets, indicating redundancy.
 *    - Return 1 if redundancy is found
 * 6. If the loop completes without finding redundancy, return 0.
 * Time Complexity: O(n), where n is the length of the string.
 * Space Complexity: O(n), in the worst case, if all characters are pushed onto the stack.
 */
public class RedundantBracesOrNot {

    public static int checkRedundancy(String s) {
        Stack<Character> st = new Stack<>();
        for(int i=0; i<s.length(); i++)
        {
            char ch = s.charAt(i);
            if(Character.isLetter(ch) || ch == '(')
                st.push(ch);

            else if(ch == '+' || ch == '-' || ch =='*' || ch == '/')
                st.push(ch);

            else if(ch == ')')
            {
                boolean flag = true;
                while(!st.isEmpty() && st.peek() != '(')
                {
                    char top_ele = st.pop();
                    if(top_ele == '+' || top_ele == '-' || top_ele == '*' || top_ele == '/')
                        flag = false;
                }
                st.pop();
                if(flag)
                    return 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String s = "((a+b))";
        int result = checkRedundancy(s);
        if(result == 1) {
            System.out.println("Redundant braces found.");
        } else {
            System.out.println("No redundant braces.");
        }
    }
}
