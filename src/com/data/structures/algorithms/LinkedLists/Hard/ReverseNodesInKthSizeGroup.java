package com.data.structures.algorithms.LinkedLists.Hard;

import com.data.structures.algorithms.LinkedLists.Medium.RemoveNthNodeFromEnd;

import java.util.Scanner;

public class ReverseNodesInKthSizeGroup {

    static class ListNode{
        int data;
        ListNode next;
        public ListNode(int data)
        {
            this.data = data;
            this.next = null;
        }
    }
    
    /*
    Optimal Approach
    T(n)=O(2N) S(n)=O(1)
    The approach simplifies reversing linked list nodes by breaking the list into segments of K nodes and reversing each segment individually. Starting from the head, the algorithm traverses the list to identify segments of K nodes. Upon finding a segment, it reverses it, returning the modified list. If a segment has less than K nodes left (ie. remaining nodes at the end), they are left unaltered.
    ReverseLinkedList: This function takes the head of a segment as input and reverses the linked list formed by that segment. It operates by utilizing the classic iterative 3-pointer method to reverse the direction of pointers within the segment.
    getKthNode: The purpose of this function is to identify the end of a segment of K nodes in the linked list. Given a starting node, it traverses K nodes in the list and returns the Kth node, allowing the segmentation of the list into smaller parts for reversal.
    kReverse: The main function orchestrates the reversal process. It iterates through the linked list and identifies segments of K nodes using getKthNode. For each identified segment, it utilizes reverseLinkedList to reverse the nodes within that segment. This iterative approach efficiently reverses the linked list nodes in groups of K.

    1. Initialise a pointer `temp` to the head of the linked list. Using `temp`, traverse to the Kth Node iteratively.
    2. On reaching the Kth Node, preserve the Kth Node’s next node as `nextNode` and set the Kth Node’s next pointer to `null`. This effectively breaks the linked list in a smaller list of size K that can be reversed and attached back.
    3. Treat this segment from `temp` to Kth Node as an individual linked list and reverse it. This can be done via the help of a helper function `reverseLinkedList`
    4. The reversed linked list segment returns a modified list with `temp` now at its tail  and the `KthNode` pointing to its head. Update the `temp`s `next` pointer to `nextNode`.
       - if we are at the first segment of K nodes, update the head to `Kth Node`.
    5. Continue this reversal for further groups. If a segment has fewer than K Nodes, leave them unmodified and return the new head. Use the prevLast pointer to maintain the link between the end of the previous reversed segment and the current segment.
    6. return head
    */
    public static ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k == 0)
            return head;

        ListNode temp = head;
        ListNode prevNode = null;
        ListNode nextNode = null;
        while(temp != null)
        {
            ListNode kthNode = findKthNode(temp, k);
            // when no group of size 'k' exists anymore
            if(kthNode == null)
            {
                prevNode.next = temp;
                break;
            }
            // storing next node address of next group
            nextNode = kthNode.next;
            // breaking link in order to reverse the group of size 'k'
            kthNode.next = null;
            // reverse the list of group size 'k'
            reverse(temp);

            // when we have reversed 1st group then head will change
            if(temp == head)
                head = kthNode;
            else
                prevNode.next = kthNode;

            prevNode = temp;
            temp = nextNode;
        }
        return head;
    }
    
    public static ListNode findKthNode(ListNode head, int k)
    {
        int count = 1;
        ListNode curr = head;
        while(curr != null)
        {
            if(count == k)
                return curr;
            curr = curr.next;
            count++;
        }
        return curr;
    }

    public static void reverse(ListNode head)
    {
        ListNode prev = null;
        ListNode next = null;
        ListNode curr = head;
        while(curr != null)
        {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
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
        int k = sc.nextInt();
        ListNode curr = reverseKGroup(head, k);
        while(curr != null)
        {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }
}
