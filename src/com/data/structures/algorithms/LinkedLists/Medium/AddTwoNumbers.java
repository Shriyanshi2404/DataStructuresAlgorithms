package com.data.structures.algorithms.LinkedLists.Medium;

import java.util.Scanner;

public class AddTwoNumbers {

    static class ListNode{
        int val;
        ListNode next;
        public ListNode(int val)
        {
            this.val = val;
            this.next = null;
        }
    }

    /*
    Optimal Approach (NOTE: Given both lists are in reverse order, else you need to reverse both lists)
    T(n)=O(max(N, M)) S(n)=O(max(N, M)) -- worst case if we traverse through all the nodes in both lists
    Intuition: Keep track of the carry using a variable and simulate digits-by-digits sum starting from the head of the list, which contains the least significant digit.
    Visualization of the addition of two numbers:
    342 + 465 = 807
    342+465=807.

    Each node contains a single digit and the digits are stored in reverse order.
    Just like how you would sum two numbers on a piece of paper, we begin by summing the least significant digits, which is the head of l1 and l2. Since each digit is in the range of 0…9, summing two digits may "overflow".
    For example: 5 + 7 = 12. In this case, we set the current digit to 2 and bring over the carry=1 to the next iteration.
    carry must be either 0 or 1 because the largest possible sum of two digits (including the carry) is 9 + 9 + 1 = 19.

    1. Create a dummy node which is the head of new linked list and Create a node "curr", initialise it with dummy.
    2. Initialize carry to 0.
    3. Loop through lists l1 and l2 until you reach both ends, and until carry is present.
        - Set sum = l1.val+ l2.val + carry
        - Update carry = sum/10
        - find remainder  = sum%10
        - Create a new node with the digit value of (remainder) and set it to "curr" node's next, then advance "curr" node to next.
        - Advance both l1 and l2.
    4. Return dummy's next node.
    */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;

        if(l2 == null)
            return l1;

        ListNode dummyNode = new ListNode(-1);
        ListNode curr = dummyNode;

        ListNode head1 = l1;
        ListNode head2 = l2;
        int carry = 0;

        while(head1 != null || head2 != null)
        {
            int sum = carry;
            // adding current data of both lists
            if(head1 != null)
                sum += head1.val;

            if(head2 != null)
                sum += head2.val;

            carry = sum/10;
            int rem = sum%10;

            // attaching new node
            curr.next = new ListNode(rem);
            curr = curr.next;

            if(head1 != null)
                head1 = head1.next;

            if(head2 != null)
                head2 = head2.next;
        }
        if(carry != 0)
        {
            curr.next = new ListNode(carry);
            curr = curr.next;
        }
        return dummyNode.next;
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ListNode head1 = null;
        ListNode tail1 = null;
        for(int i=0; i<n; i++)
        {
            int ele = sc.nextInt();
            if(head1 == null)
            {
                head1 = new ListNode(ele);
                tail1 = head1;
            }
            else
            {
                tail1.next = new ListNode(ele);
                tail1 = tail1.next;
            }
        }

        int m = sc.nextInt();
        ListNode head2 = null;
        ListNode tail2 = null;
        for(int i=0; i<m; i++)
        {
            int ele = sc.nextInt();
            if(head2 == null)
            {
                head2 = new ListNode(ele);
                tail2 = head2;
            }
            else
            {
                tail2.next = new ListNode(ele);
                tail2 = tail2.next;
            }
        }

        ListNode curr = addTwoNumbers(head1, head2);
        while(curr != null)
        {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
    }
}
