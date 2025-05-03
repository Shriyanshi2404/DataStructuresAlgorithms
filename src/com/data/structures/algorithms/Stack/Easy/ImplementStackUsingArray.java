package com.data.structures.algorithms.Stack.Easy;

import java.util.Scanner;

public class ImplementStackUsingArray {
    static class Stack {
        int top;
        int[] arr;
        int capacity = 0;

        Stack(int capacity) {
            this.capacity = capacity;
            this.arr = new int[capacity];
            this.top = -1;
        }

        public void push(int num){
            if(isFull() == 1)
                return;

            top++;
            arr[top] = num;
        }
        public int pop(){
            if(isEmpty() == 1)
                return -1;

            int ele = arr[top];
            top--;
            return ele;
        }
        public int top(){
            if(isEmpty() == 1)
                return -1;
            return arr[top];
        }
        public int isEmpty()
        {
            if(top == -1)
                return 1;
            return 0;
        }
        public int isFull(){
            if(top == capacity-1)
                return 1;
            return 0;
        }
        public int size(){
            return top+1;
        }
    }

    /*
    1. Push(num): Push the given number in the stack if the stack is not full.
    2. Pop: Remove and print the top element from the stack if present, else print -1.
    3. Top: Print the top element of the stack if present, else print -1.
    4. isEmpty: Print 1 if the stack is empty, else print 0.
    5. isFull: Print 1 if the stack is full, else print 0.
    When operation 1 1 is performed, we insert 1 in the stack.
    When operation 1 2  is performed, we insert 2 in the stack.
    When operation 2 is performed, we remove the top element from the stack and print 2.
    When operation 3 is performed, we print the top element of the stack, i.e., 3.
    When operation 4 is performed, we print 0 because the stack is not empty.
    When operation 5 is performed, we print 0 because the stack is size 1, which is not equal to its capacity.
    */

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        // max capacity of stack
        int cap = sc.nextInt();

        Stack st = new Stack(cap);
        st.push(1);
        st.push(2);
        st.push(3);
        System.out.println("Top of the stack before deleting any element " + st.top());
        System.out.println("Size of the stack after deleting an element " + st.size());
        System.out.println("The element deleted is " + st.pop());
        System.out.println("Size of the stack after deleting an element " + st.size());
        System.out.println("Top of the stack after deleting an element " + st.top());
    }
}
