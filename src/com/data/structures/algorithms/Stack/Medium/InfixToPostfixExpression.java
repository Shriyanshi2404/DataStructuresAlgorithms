package com.data.structures.algorithms.Stack.Medium;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/** Problem link: <a href="https://www.geeksforgeeks.org/infix-to-postfix-conversion-using-stack/">...</a> */

/**
 * Algorithm:
 * 1. If character is an operand (letter or digit), add it to the result string
 * 2. If character is '(', push it onto stack
 * 3. If character is ')', pop the character from the stack and add to the result string until '(' is encountered and stack is not empty
 * 4. If character is an operator:
 * - If stack is empty or top of the stack is '(', push the operator onto the stack
 * - If the precedence of the current operator is less than or equal to the precedence of the operator at the top of the stack, pop from the stack and add to result string until the top of the stack has an operator of lower precedence or stack is empty
 * - then push the current operator onto the stack
 * 5. After processing all characters, pop all operators from the stack and add to the result string
 * 6. Return the result string as the postfix expression
 */
public class InfixToPostfixExpression {

    public static Map<Character, Integer> createPrecedenceMap(Map<Character, Integer> map) {
        map.put('^', 3); // Highest precedence for exponentiation
        map.put('*', 2);
        map.put('/', 2);
        map.put('+', 1);
        map.put('-', 1);
        return map;
    }

    public static String infixToPostfix(String s) {
        StringBuilder result = new StringBuilder();
        Stack<Character> st = new Stack<>();
        char[] arr = s.toCharArray();

        // Create a precedence map for operators
        Map<Character, Integer> map = createPrecedenceMap(new HashMap<>());

        for (char ch : arr) {
            // if a Character is an operand (Letter or Digit), add it to the result
            if (Character.isLetterOrDigit(ch))
                result.append(ch);

                // if Character is '(', push it to the stack
            else if (ch == '(')
                st.push(ch);

                // if Character is ')', pop and output from the stack until an '(' is encountered
            else if (ch == ')') {
                while (!st.isEmpty() && st.peek() != '(') {
                    result.append(st.pop());
                }
                // removing the '(' from the stack
                st.pop();
            }

            // if Character is an operator
            else {

                if (st.isEmpty() || st.peek() == '(')
                    st.push(ch);

                else {
                    // if operator precedence is lower than or equal to the top of the stack,
                    // then pop from the stack until the top of the stack has an operator of lower precedence or the stack is empty and append it to result string
                    while (!st.isEmpty() && st.peek() != '(' && map.get(st.peek()) >= map.get(ch)) {
                        result.append(st.pop());
                    }
                    // if the precedence of the current operator is higher than the top of the stack, then push it to the stack
                    // push the current operator to the stack
                    st.push(ch);
                }
            }
        }

        // pop all the operators from the stack and append them to the result
        while(!st.isEmpty())
            result.append(st.pop());

        return result.toString();
    }

    public static void main(String[] args) {
        // Example usage
        String infix = "a+b*(c^d-e)^(f+g*h)-i";
        String postfix = infixToPostfix(infix);
        System.out.println("Postfix Expression: " + postfix);
    }
}
