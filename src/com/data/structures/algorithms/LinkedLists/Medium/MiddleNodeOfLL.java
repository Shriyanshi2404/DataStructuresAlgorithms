package com.data.structures.algorithms.LinkedLists.Medium;

import java.util.Scanner;

public class MiddleNodeOfLL {

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
    Brute Force
    T(n) = O(N+N/2)  S(n) = O(1)
    1. Find length of Linked list
    2. Determine middle position that will be length/2
    3. Traverse through linkedlist until you reach middle position node
    4. By keeping pointer "curr" to the head and traverse the list by iteratively moving curr = curr.next mid number of times.
    5. The node pointed to by current after this traversal is the middle node of the linked list.
     */
    public static int findLength(Node head)
    {
        Node curr = head;
        int count = 0;
        while(curr != null)
        {
            curr = curr.next;
            count++;
        }
        return count;
    }

    public static Node findMiddleNode(Node head)
    {
        int length = findLength(head);
        int midPos = length/2;

        Node curr = head;
        while(midPos != 0)
        {
            curr = curr.next;
            midPos--;
        }
        return curr;
    }

    /*
    Optimal Approach
    T(n) = O(N)  S(n) = O(1)
    1. Initialise two pointers,`slow` and `fast, to the head of the linked list.
    2. `slow` will advance one step at a time, while `fast` will move two steps at a time.
    3. These pointers will move simultaneously.
    4. Traverse the linked list with the `slow` and `fast` pointers.
       - While traversing, repeatedly move `slow` one step and `fast` two steps at a time.
    5. Continue this traversal until fast reaches the end of the list (i.e., fast or fast.next is null),
    6. once fast pointer reached end of linked list then, the slow pointer will be at the middle of the list.
    7. return slow pointer
     */
    public static Node middleNode(Node head) {
        if(head == null || head.next == null)
            return head;

        Node slow = head;
        Node fast = head;
        while(fast!=null && fast.next!=null)
        {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)
                break;
        }
        return slow;
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
        System.out.print(middleNode(head).val);
    }
}
