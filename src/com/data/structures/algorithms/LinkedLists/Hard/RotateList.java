package com.data.structures.algorithms.LinkedLists.Hard;

import java.util.Scanner;

public class RotateList {

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
    Optimal Approach
    T(n)=O(N) S(n)=O(1)
    Idea: We can see that for every k which is multiple of the length of the list, we get back the original list.
    Try to operate approach on any linked list for k as a multiple of the length of the list.
    This gives us a hint that for k greater than the length of the list, we have to rotate the list for k%length of the list. This reduces our time complexity.

    1. Find length of linked list and along with that store the address of last node in "tail"
    2. Connect the last node i.e. "tail" to the first node i.e. "head", converting it to a circular linked list.
    3.Find kth node by iterating through linked list and passing kth position to cut the link of the last node and start a node of k%length of the list rotated list.
    4. Once kthNode is found store it's address as "lastNode"
    5. Now, head will point to lastNode's next and lastNode's next will point to null to break the link.
    6. return head of linked list
    */
    public static ListNode findKthNode(ListNode head, int k)
    {
        ListNode temp = head;
        int count = 1;
        while(temp != null)
        {
            if(count == k)
                return temp;

            temp = temp.next;
            count++;
        }
        return temp;
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if(head == null || k == 0)
            return head;

        int length = 1;
        ListNode tail = head;
        while(tail.next != null)
        {
            tail = tail.next;
            length++;
        }

        if(k%length == 0)
            return head;

        k = k%length;

        // assigning tail's next to head
        tail.next = head;

        // find kthNode
        ListNode lastNode = findKthNode(head, length-k);
        head = lastNode.next;
        lastNode.next = null;
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
        int k = sc.nextInt();
        ListNode curr = rotateRight(head, k);
        while(curr != null)
        {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }
}
