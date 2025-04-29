package com.data.structures.algorithms.LinkedLists.Medium;

import java.util.Scanner;

public class DeleteMiddleNode {

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
    1. Find the length of linked list, and we know middle position is (length/2)
    2. define a pointer "prev" as null and "curr" pointing to head of linked list and run a loop from i=0 to i<pos
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

    public static ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null) {
            // If there's one node or no nodes, just return null
            return null;
        }

        int length = findLength(head);
        int pos = length / 2;  // Use length / 2 to get the middle node position

        ListNode prev = null;
        ListNode curr = head;
        // Traverse to the node before the one to delete
        for(int i=0;i<pos;i++)
        {
            prev = curr;
            curr = curr.next;
        }
        if(prev == null)
            return head.next;
        else
        {
            prev.next = prev.next.next;
            curr = curr.next;
        }
        return head;
    }

    /*
    Optimal Approach
    T(n)=O(N/2) S(n)=O(1)
    1.Check if the list is empty or contains only one node. If so, no middle node exists to delete, return NULL.
    2. Initialise 'slow' and 'fast' pointers at the head of the list.
       - Move ‘fast’ two nodes ahead for the initial position as we have to reach the node previous to the middle node via slow.
    3. Move the 'fast' pointer two nodes ahead and the 'slow' pointer one node ahead in each iteration. Continue this process until the 'fast' pointer reaches the end of the list.
    4. Once the 'fast' pointer reaches the end, the 'slow' pointer will be at the middle node. Delete the middle node by skipping it from the linked list.
    5. Return the head of the modified linked list.
    */
    public static ListNode deleteMiddleNode(ListNode head) {
        if (head == null || head.next == null) {
            // If there's one node or no nodes, just return null
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        // shifting fast pointers two nodes ahead of head
        fast = head.next.next;
        while(fast != null && fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }

        if(slow.next != null)
            slow.next = slow.next.next;
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
        ListNode curr = deleteMiddleNode(head);
        while(curr != null)
        {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }
}
