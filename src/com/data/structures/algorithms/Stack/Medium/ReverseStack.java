package com.data.structures.algorithms.Stack.Medium;

import java.util.Stack;

/** Problem link: https://www.geeksforgeeks.org/reverse-a-stack-using-recursion/ */
/** 
 * Algorithm:
 * 1. Idea is to pop the top element and insert it at the bottom of the stack.
 * 2. when stack becomes empty, then we start inserting the element at bottom of stack
 * 3. Let's say last element is 1 to be popped out so we will insert '1' to reverse stack
 * 2. Start popping the elements one by one and insert them at the bottom of the stack.
 * Insertion function:
 * 1. Base case: If the reverse stack is empty, push the element and return.
 * 2. Recursive case: Pop the top element and insert it at the bottom of the reverse stack.
 * 3. Insert the top element at the bottom of the reversestack.
 * 4. Push the top element back to the reverse stack.
 * 5. Return the stack.
 * Time Complexity: O(n^2)
 * Space Complexity: O(n)
 */
public class ReverseStack {

    static void reverse(Stack<Integer> s) {
        if(s.isEmpty())
            return;
        
        int top_ele = s.pop();
        reverse(s);
        insert(s, top_ele);
    }
    
    static void insert(Stack<Integer> s, int ele)
    {
        if(s.isEmpty())
        {
            s.push(ele);
            return;
        }
        
        int top_ele = s.pop();
        insert(s, ele);
        s.push(top_ele);
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);    
        reverse(s);
        System.out.println("Reversed Stack: ");
        while (!s.isEmpty()) {
            System.out.print(s.pop() + " ");
        }
    }
}
