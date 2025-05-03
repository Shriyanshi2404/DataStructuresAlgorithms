package com.data.structures.algorithms.Stack.Easy;

import java.util.Scanner;
import java.util.Stack;

public class ValidParenthesis {

    /*
    Intuition:  We have to keep track of previous as well as most recent opening brackets and also keep in mind the sequence, as after opening of the bracket there should be opposite pairs of brackets.
    Also handle the corner cases like [ ) ( ] where closing bracket occurs first and opening bracket after it, which is an invalid sequence, as well as [ ( ] ) where the most recent opening didn't get its opposite pair hence it will also not be valid.
    So we have to use a data structure that will keep track of first in and last out, hence we will use the stack.

    Approach:
    1. Whenever we get the opening bracket we will push it into the stack. I.e ‘{‘, ’[’, ’(‘.
    2. Whenever we get the closing bracket we will check if the stack is non-empty or not.
    3. If the stack is empty we will return false, else if it is nonempty then we will check if the topmost element of the stack is the opposite pair of the closing bracket or not.
    4. If it is not the opposite pair of the closing bracket then return false, else move ahead.
    5.After we move out of the string the stack has to be empty if it is non-empty then return it as invalid else it is a valid string.
    */
    public static boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        char[] arr = s.toCharArray();
        for(int i=0; i<arr.length; i++)
        {
            char ch = arr[i];
            if(ch == '(' || ch == '[' || ch == '{')
                st.push(ch);

            else if(ch == ')')
            {
                if(st.isEmpty() || st.peek() != '(')
                    return false;
                st.pop();
            }
            else if(ch == ']')
            {
                if(st.isEmpty() || st.peek() != '[')
                    return false;
                st.pop();
            }
            else if(ch == '}')
            {
                if(st.isEmpty() || st.peek() != '{')
                    return false;
                st.pop();
            }
        }
        if(st.size() == 0)
            return true;
        return false;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.print("Given string has valid paranthesis: "+ isValid(s));
    }
}
