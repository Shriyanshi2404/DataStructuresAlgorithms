package com.data.structures.algorithms.LinkedLists.Medium;

import java.util.Scanner;

public class ReverseLinkedList {

    static class ListNode{
        int val;
        ListNode next;

        ListNode(int val)
        {
            this.val = val;
            this.next = null;
        }
    }

    /*
    Iterative method
    T(n) = O(N) S(n) = O(1)
    1. Initialise a ‘curr’ pointer at the head of the linked list and 'next' pointer to NULL
       - This pointer will be used to traverse the linked list.
       - And initialize the pointer ‘prev’ to ‘NULL’ to keep track of the previous node.
       - This will be used to reverse the direction of the ‘next’ pointers.
    2. Traverse the entire linked list by moving through each node using the 'curr' pointer until it reaches the end (marked as 'NULL').
    3. Keep traversing through the linked list using the 'curr' pointer until it reaches the end, thereby reversing the entire list.
    4. Once the 'curr' pointer reaches the end, return the new head of the reversed linked list, which is now indicated by the 'prev' pointer.
       - This 'prev' pointer becomes the first node in the newly reversed list.
    */
    public static ListNode reverseList(ListNode head) {

        if(head==null || head.next==null)
            return head;

        ListNode prev=null,curr=head,next=null;

        while(curr != null)
        {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /*
    Recursive method
    T(n) = O(N) S(n) = O(1)
     */
    public static ListNode reverseLL(ListNode head)
    {
        if(head == null ||  head.next == null)
            return head;

        ListNode smallHead  = reverseLL(head.next);
        head.next.next = head;
        head.next = null;
        return smallHead;
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ListNode head = null;
        ListNode tail = null;
        for(int i=0;i<n;i++)
        {
            int ele = sc.nextInt();
            if (head == null) {
                head = new ListNode(ele);
                tail = head;
            } else {
                tail.next = new ListNode(ele);
                tail = tail.next;
            }
        }
        ListNode curr = reverseList(head);
        while(curr!=null)
        {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
    }
}
