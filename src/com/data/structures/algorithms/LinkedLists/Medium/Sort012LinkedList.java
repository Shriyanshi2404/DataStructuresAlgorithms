package com.data.structures.algorithms.LinkedLists.Medium;

import java.util.HashMap;
import java.util.Scanner;

public class Sort012LinkedList {

    static class Node {
        int data;
        Node next;
        public Node(int data)
        {
            this.data = data;
            this.next = null;
        }
    }

    /*
    Brute Force Approach
    T(n)=O(N) S(n)=O(N)
    1. Create hashmap to store the frequencies of 0's, 1's and 2's
    2. Create a dummy linked list and assign "curr" pointer to dummy list
    3. Run a loop for numbers 0 to 2
        - Check if number is present in map
        - if present, find frequencey of that number
        - now run another loop until frequency of that number becomes 0
        - in inner-loop create a node with current number and attach next to curr pointer, do freq--
    4. return dummy node's next
     */
    public static Node segregate(Node head) {
        HashMap<Integer, Integer> map = new HashMap<>();
        Node curr = head;
        while(curr != null)
        {
            map.put(curr.data, map.getOrDefault(curr.data, 0)+1);
            curr = curr.next;
        }

        Node dummyNode = new Node(-1);
        curr = dummyNode;

        for(int i=0;i<=2;i++)
        {
            int ele = i;
            if(map.containsKey(ele))
            {
                int freq = map.get(ele);
                while(freq != 0)
                {
                    curr.next = new Node(ele);
                    curr = curr.next;
                    freq--;
                }
            }
        }

        return dummyNode.next;
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
    public static Node mergeLists(Node head1, Node head2)
    {
        Node dummyNode = new Node(-1);
        Node curr = dummyNode;

        while(head1 != null && head2 != null)
        {
            if(head1.data <= head2.data)
            {
                curr.next = head1;
                head1 = head1.next;
            }
            else
            {
                curr.next = head2;
                head2 = head2.next;
            }
            curr = curr.next;
        }

        if(head1 != null)
            curr.next = head1;
        else
            curr.next = head2;

        return dummyNode.next;
    }

    public static Node findMiddle(Node head)
    {
        Node slow = head;
        Node fast = head;
        while(fast.next != null && fast.next.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node segregateNodes(Node head) {
        if(head == null || head.next == null)
            return head;

        Node middle = findMiddle(head);
        Node secondList = middle.next;
        middle.next = null;
        Node firstList = head;

        firstList = segregate(firstList);
        secondList = segregate(secondList);

        return mergeLists(firstList, secondList);
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
        Node curr = segregateNodes(head);
        while(curr != null)
        {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }
}
