package com.data.structures.algorithms.Stack.Easy;

public class ImplementStackUsingLinkedLists {
    static class Stack {
        StackNode top;
        int size;

        Stack() {
            this.top = null;
            this.size = 0;
        }

        private class StackNode {
            int data;
            StackNode next;

            public StackNode(int data) {
                this.data = data;
                this.next = null;
            }
        }

        /*
        push(): pushing an element at the top of a stack
        Pushing the element at the top of the stack would mean the same as pushing an element at the end of the linked list.
        We can insert it at the beginning of the linked list using the following steps:
        1. Create a node for our new element.
        2. Point to the next pointer of our element node to point to the head of the linked list.
        3. Make the newly created node our top node.
        4. Increment the size variable.
         */
        public void push(int data) {
            StackNode newNode = new StackNode(data);
            newNode.next = top;
            // assigning top as newNode
            top = newNode;
            System.out.println("Element pushed");
            size++;
        }

        /*
        pop(): removing an element from the top of a stack
        Removing an element from the top of the stack is the same as removing the element from the beginning of our linked list.
        The following steps are involved in the pop() method
        1. Check for underflow conditions in the stack.
        2. Store the top node data in another variable say "topData".
        3. To Delete top node, shift top pointer to next node
        4. Return the top node’s data.
         */
        public int pop() {
            if (top == null)
                return -1;

            int topData = top.data;
            // shifting top to next node
            top = top.next;
            return topData;
        }

        /*
        size(): returns the size of the stack
        we maintain a size variable. After each push operation, we increment the size variable and after each pop operation, we decrement the size variable.
         */
        public int getSize() {
            return size;
        }

        /*
        printStack(): print all the elements present in stack
         */
        public void printStack() {
            StackNode current = top;
            while (current != null) {
                System.out.print(current.data + " ");
                current = current.next;
            }
            System.out.println();
        }

        /*
        top(): return the element stored at top of the stack
        1. check underflow condition
        2. return element stored at top of stack
         */
        public int top() {
            if (top == null)
                return -1;

            return top.data;
        }

        /*
        isEmpty(): returns a boolean value for whether the stack is empty or not.
        we check if our top is equal to NULL.
        */
        public boolean isEmpty(){
            if(top == null)
                return true;

            return false;
        }
    }

    public static void main(String[] args)
    {
        Stack st = new Stack();
        st.push(10);
        st.push(20);
        st.printStack();
        System.out.println("Element popped " + st.pop());
        System.out.println("Stack size: " + st.getSize());
        System.out.println("Stack is empty or not: " + st.isEmpty());
    }

}
