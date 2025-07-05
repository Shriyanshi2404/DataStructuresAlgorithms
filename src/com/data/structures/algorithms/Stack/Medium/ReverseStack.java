package com.data.structures.algorithms.Stack.Medium;

import java.util.Stack;

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
