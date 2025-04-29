package com.data.structures.algorithms.LinkedLists.Medium;

import java.util.Scanner;

public class Add1ToNumber {

    static class Node{
        int data;
        Node next;
        public Node(int data)
        {
            this.data = data;
            this.next = null;
        }
    }

    /*
    Optimal Approach
    T(n)=O(N) S(n)=O(N) -- due to the new nodes created during summation.
    Intuition: Keep track of the carry using a variable and simulate digits-by-digits sum starting from the head of the list, which contains the least significant digit.
    Visualization of the addition of two numbers:
    123 + 1 = 124
    129 + 1 = 130

    Each node contains a single digit and the digits are stored in reverse order.
    Just like how you would sum two numbers on a piece of paper, we begin by summing the least significant digits, which is the head of l1 and l2. Since each digit is in the range of 0…9, summing two digits may "overflow".
    For example: 5 + 7 = 12. In this case, we set the current digit to 2 and bring over the carry=1 to the next iteration.
    carry must be either 0 or 1 because the largest possible sum of two digits (including the carry) is 9 + 9 + 1 = 19.

    1. Reverse the original list and store it in head1
    2. Now find how many zeroes you need to add in list2 by finding length of given list and doing -1
    3. Once you find no of zeroes, add it to new list say "head2" which has only node as 1
    4. Create a dummy node which is the head of new linked list and Create a node "curr", initialise it with dummy.
    2. Initialize carry to 0.
    3. Loop through lists head1 and head2 until you reach both ends, and until carry is present.
        - Set sum = head1.val+ head2.val + carry
        - Update carry = sum/10
        - find remainder  = sum%10
        - Create a new node with the digit value of (remainder) and set it to "curr" node's next, then advance "curr" node to next.
        - Advance both head1 and head2.
    4. If still carry is not 0, then create a new node with value as carry and add to linked list
    4. Return dummy's next node.
    */
    public static int findLength(Node head)
    {
        int count=0;
        Node curr=head;
        while(curr!=null)
        {
            curr=curr.next;
            count++;
        }
        return count;
    }

    public static Node reverse(Node head)
    {
        Node prev = null;
        Node next = null;
        Node curr = head;

        while(curr != null)
        {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static Node addZeroes(Node head, int zeroes)
    {
        Node curr = head;

        while(zeroes != 0)
        {
            curr.next = new Node(0);
            curr = curr.next;
            zeroes--;
        }
        return head;
    }

    public static Node addOne(Node head) {
        if(head == null)
            return new Node(1);

        int length = findLength(head);
        int zeroes = length-1;

        Node head1 = reverse(head);
        Node head2 = new Node(1);
        head2 = addZeroes(head2, zeroes);

        Node dummyNode = new Node(-1);
        Node curr = dummyNode;
        int carry = 0;

        while(head1 != null || head2 != null)
        {
            int sum = carry;

            if(head1 != null)
                sum += head1.data;

            if(head2 != null)
                sum += head2.data;

            int rem = sum%10;
            carry = sum/10;

            curr.next = new Node(rem);
            curr = curr.next;

            if(head1 != null)
                head1 = head1.next;
            if(head2 != null)
                head2 = head2.next;
        }
        if(carry!=0)
        {
            curr.next = new Node(carry);
            curr=curr.next;
        }
        dummyNode = reverse(dummyNode.next);
        return dummyNode;
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Node head = null;
        Node tail = null;
        for(int i=0; i<n; i++)
        {
            int ele = sc.nextInt();
            if(head == null)
            {
                head = new Node(ele);
                tail = head;
            }
            else
            {
                tail.next = new Node(ele);
                tail = tail.next;
            }
        }

        Node curr = addOne(head);
        while(curr != null)
        {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }
}
