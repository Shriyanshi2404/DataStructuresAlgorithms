package com.data.structures.algorithms.LinkedLists.Medium;

import java.util.Scanner;

public class OddEvenLinkedList {

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
    Brute force approach
    T(n) = O(N) S(n) = O(N)
    1. Create two linked list "even" and "odd"
    2. Once created, then assign i=1 and 'curr' pointer to head
    3. Now traverse through the linked list until 'curr' pointer reach end of Linked list
    4. during traversal, check the index 'i' whether it's even index or odd index
       - if even index, then add curr node to even linked list
       - if odd index, then add curr node to odd linked list
       - and everytime increment i by 1
       - also move curr pointer to next address
    5. Once traversal is completed, simply join even linked list after odd linked list
    6. return odd linked list
    */

    public static ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null)
            return head;

        // even linked list
        ListNode even = null;
        ListNode tail1 = null;

        // odd linked list
        ListNode odd = null;
        ListNode tail2 = null;

        ListNode curr = head;
        int i = 1;
        while(curr!=null)
        {
            int val = curr.val;
            // even node
            if(i % 2 == 0)
            {
                if(even == null)
                {
                    even = new ListNode(val);
                    tail1 = even;
                }
                else
                {
                    tail1.next = new ListNode(val);
                    tail1 = tail1.next;
                }
            }
            // odd node
            else
            {
                if(odd == null)
                {
                    odd = new ListNode(val);
                    tail2 = odd;
                }
                else
                {
                    tail2.next = new ListNode(val);
                    tail2 = tail2.next;
                }
            }
            curr = curr.next;
            i++;
        }

        tail2.next = even;
        return odd;
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
        ListNode curr = oddEvenList(head);
        while(curr!=null)
        {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
    }
}
