package com.data.structures.algorithms.LinkedLists.Medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SortLinkedList {

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
    Brute Force
    T(n) = O(N)+O(N*LogN)+O(N)
    S(n) = O(N)
    1. Create an empty array list says "arr" to store the node values. Iterate the linked list using a 'curr' pointer to the head and push the value of curr node into the array. Move curr to the next node.
    2. Sort the array list containing node values in ascending order.
    3. Convert the sorted array list back to a linked list reassigning the values from the sorted array list and overwriting them sequentially according to their order in the array.
    */
    public static ListNode sortList(ListNode head) {
        if(head == null || head.next == null)
            return head;

        List<Integer> arr = new ArrayList<>();
        ListNode curr = head;
        while(curr != null)
        {
            arr.add(curr.data);
            curr = curr.next;
        }

        Collections.sort(arr);
        ListNode newHead = null;
        ListNode tail = null;
        for(int ele : arr)
        {
            if(newHead == null)
            {
                newHead = new ListNode(ele);
                tail = newHead;
            }
            else
            {
                tail.next = new ListNode(ele);
                tail = tail.next;
            }
        }
        return newHead;
    }

    /*
    Optimal Approach - Merge sort
    T(n) = O(N*log N) S(n) = O(1)
    1: Base Case If the linked list contains zero or one element, it is already sorted. Return the head node.
    2: Split the ListFind the middle of the linked list using a slow and a fast pointer. Split the linked list into two halves at the middle node. The two halves will be left and right.
    3: Recursion: Recursively apply merge sort to both halves obtained in the previous step. This step continues dividing the linked list until there's only one node in each half.
    4: Merge Sorted Lists: Merge the sorted halves obtained from the recursive calls into a single sorted linked list. Compare the nodes from both halves and rearrange them to form a single sorted list. Update the head pointer to the beginning of the newly sorted list.
    5: Return: Once the merging is complete, return the head of the sorted linked list.
    */

    public static ListNode sortLL(ListNode head) {
        // Base case: if the list is empty or has only one node, it is already sorted, so return the head
        if(head == null || head.next == null)
            return head;

        // Find the middle of the list
        ListNode middleNode = findMiddle(head);
        // Divide the list into two halves
        ListNode secondHalf = middleNode.next;
        middleNode.next = null;
        ListNode firstHalf = head;

        // Recursively sort the left and right halves
        firstHalf = sortLL(firstHalf);
        secondHalf = sortLL(secondHalf);
        // Merge the sorted halves using the mergeTwoSortedLinkedLists function
        return mergeLinkedLists(firstHalf, secondHalf);
    }

    public static ListNode mergeLinkedLists(ListNode head1, ListNode head2)
    {
        // Create a dummy node to serve as the head of the merged list
        ListNode dummyNode = new ListNode(-1);
        ListNode head = dummyNode;

        while(head1 != null && head2 != null)
        {
            // Compare elements of both lists and
            // link the smaller node to the merged list
            if(head1.data <= head2.data)
            {
                head.next = head1;
                head1 = head1.next;
            }
            else
            {
                head.next = head2;
                head2 = head2.next;
            }
            head = head.next;
        }

        // If any list still has remaining
        // elements, append them to the merged list
        if (head1 != null) {
            head.next = head1;
        }
        else {
            head.next = head2;
        }
        // Return the merged list starting from the next of the dummy node
        return dummyNode.next;
    }

    public static ListNode findMiddle(ListNode head)
    {
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
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

        ListNode curr = sortLL(head);
        while(curr != null)
        {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }
}
