package com.data.structures.algorithms.LinkedLists.Medium;

import com.data.structures.algorithms.LinkedLists.Easy.LengthOfLL;

import java.util.Scanner;
import java.util.Stack;

public class PalindromeLinkedList {

    static class Node {
        int val;
        Node next;
        public Node(int val)
        {
            this.val = val;
            this.next = null;
        }
    }

    /*
    Brute force approach
    T(n) = O(2*N) S(n) = O(N)
    1. Create an empty stack. This stack will be used to temporarily store the nodes from the original linked list as we traverse it.
    2. Traverse the linked list using a temporary variable `curr` till it reaches null.
        - At each node, push the value at the current node onto the stack.
    3. Set variable `curr` back to the head of the linked list.
    4. While the stack is not empty, compare the value at the curr node to the value at the top of the stack.
       - Pop the stack and move the curr to the next node till it reaches the end.
    5. After traversing complete linked list, simply return true
     */
    public static boolean isPalindrome(Node head) {

        Stack<Integer> st = new Stack<>();
        // storing elements into stack
        Node curr = head;
        while(curr!=null)
        {
            st.push(curr.val);
            curr=curr.next;
        }
        curr = head;
        while(curr!=null)
        {
            // removing top element of stack
            int val = st.pop();
            if(val != curr.val)
                return false;

            curr=curr.next;
        }
        return true;
    }

     /*
    Optimal approach
    T(n) = O(2*N) S(n) = O(1)
    1. Check if the linked list is empty or has only one node. If that’s the case, it is a palindrome by definition, so return true.
    2. Initialise two pointers, ‘slow’ and ‘fast’, to find the middle of the linked list.
       - ‘slow’ pointer advances by one step at a time
       - ‘fast’ pointer advances by two steps at a time.
       - Continue this until the ‘fast’ pointer reaches the end of the list or is the second last on the list.
       - The ‘slow’ pointer will now be in the middle of the linked list.
    3. Reverse the second half of the linked list starting from the middle (the ‘slow->next’ node). This is done by calling the reverse linked list function and returning the head of the new reversed linked list.
    4. Create two pointers, ‘first’ and ‘second’, where ‘first’ points to the head of the linked list, and ‘second’ points to the new head of the reversed second half.
    5. Traverse through linked list until 'second' pointer reaches end of reverse linked list
       - check if 'first' and 'second' pointer value matches or not
       - if it does not match, then return false
       - else move pointer ahead by doing first.next and second.next
    6. At end, return true as it means linked list is palindrome
    */
    public static boolean isPalindromeOptimal(Node head) {
        Node slow = head;
        Node fast = head;
        while(fast.next!=null && fast.next.next!=null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node firstList = head;
        Node secondList = reverse(slow.next);

        while(secondList!=null)
        {
            if(secondList.val != firstList.val)
                return false;

            firstList = firstList.next;
            secondList = secondList.next;
        }
        return true;
    }
    public static Node reverse(Node head)
    {
        Node prev = null;
        Node curr = head;
        Node next = null;
        while(curr!=null)
        {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }


    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Node head = null;
        Node tail = null;
        for(int i=0;i<n;i++)
        {
            int ele = sc.nextInt();
            if (head == null) {
                head = new Node(ele);
                tail = head;
            } else {
                tail.next = new Node(ele);
                tail = tail.next;
            }
        }
        System.out.print(isPalindromeOptimal(head));
    }
}
