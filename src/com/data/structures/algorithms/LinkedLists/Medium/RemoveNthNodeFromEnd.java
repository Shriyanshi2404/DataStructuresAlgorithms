package com.data.structures.algorithms.LinkedLists.Medium;

import java.util.Scanner;

public class RemoveNthNodeFromEnd {

    static class ListNode
    {
        int data;
        ListNode next;
        ListNode(int data)
        {
            this.data = data;
            this.next = null;
        }
    }

    /*
    Brute force approach
    T(n) = O(N)+O(L-N) S(n) = O(1)
    1. Find the length of linked list
    2. Now instead of deleting a node from end of Linked list, what if we find the positing of that node from starting
    3. if we want to delete the node from starting then Position of the node to be deleted from end will be (length-N+1), store length-n in variable say "k"
    4. define a pointer "prev" as null and "curr" pointing to head of linked list and run a loop from i=0 to i<k
       - while traversing update prev and curr pointers
    5. Now check if prev points to null, it means node to be deleted is the first node of LL -> so simply return head.next
    6. else, point prev pointer next to prev pointer next.next so that node between that will be deleted.
       and simply shift curr pointer to next node
    */
    public static int findLength(ListNode head)
    {
        int count=0;
        ListNode curr = head;
        while(curr!=null)
        {
            count++;
            curr=curr.next;
        }
        return count;
    }
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null)
            return head;

        int length = findLength(head);
        int k = length-n;

        ListNode prev = null;
        ListNode curr = head;
        for(int i=0;i<k;i++)
        {
            prev = curr;
            curr = curr.next;
        }

        if(prev == null)
            return head.next;

        prev.next = prev.next.next;
        curr = curr.next;
        return head;
    }

    /*
    Optimal Approach
    T(n) = O(N) S(n) = O(1)
    1. Initialize two pointers, `slow` and `fast`, to the head of the linked list.
    2. Initially, only fast will move till it crosses N nodes, after which both of the pointers will move simultaneously.
    3. check if fast pointer is null, if yes it means node to be deleted is 1st node of Linked list so return head.next.
    4. Else, Traverse the linked list till the fast pointer reaches the last node, that is, the Lth Node, at this stage, the slow pointer is guaranteed to be at the (L-N)th node.
    5. Point this slow pointer to the (L-N+2)th node, effectively skipping the Nth node from the end or the (L-N+1)th node from the start.
    6. return head of linked list
    */
    public static ListNode removeNthFromEndOfLL(ListNode head, int n) {
        if(head == null)
            return head;

        ListNode slow = head;
        ListNode fast = head;

        for(int i=0;i<n;i++)
        {
            fast = fast.next;
        }

        if(fast == null)
            return head.next;

        while(fast.next != null)
        {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;
        fast = fast.next;
        return head;
    }


    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ListNode head = null;
        ListNode tail = null;
        for(int i=0; i<n; i++)
        {
            int ele = sc.nextInt();
            if(head == null)
            {
                head = new ListNode(ele);
                tail = head;
            }
            else
            {
                tail.next = new ListNode(ele);
                tail = tail.next;
            }
        }
        int N = sc.nextInt();
        ListNode curr = removeNthFromEndOfLL(head, N);
        while(curr != null)
        {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }
}
