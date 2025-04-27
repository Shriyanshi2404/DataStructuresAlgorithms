package com.data.structures.algorithms.LinkedLists.Medium;

import java.util.HashMap;
import java.util.Scanner;

public class DetectCycle {

    static class Node {
        Node next;
        int val;
        Node(int val)
        {
            this.val = val;
            this.next = null;
        }
    }

    /*
    Brute force approach
    T(n) = O(N) S(n) = O(N)
    1. Create a hashmap that will store nodes of linked list
    2. Assign pointer "curr" to head of linked list
    3. Start traversing through the linked list until curr pointer reached at end of Linked list
    4. during traversal, check if node is present in hashmap or not
        - if present, simply return true as we found a cycle
        - if not present, then add node in hashmap
    5. Once you reach end of linked list and still you don't find any loop then simply return false
     */
    public static boolean detectCycle(Node head) {
        HashMap<Node, Boolean> map = new HashMap<>();
        Node curr = head;
        while(curr!=null)
        {
            if(map.containsKey(curr))
                return true;
            else
                map.put(curr, true);

            curr = curr.next;
        }
        return false;
    }

    /*
    Optimal Approach
    T(n) = O(N) S(n) = O(1)
    1.Initialise two pointers, `slow` and `fast`, and point them to the head of the linked list.
      - `slow` will advance one step at a time,
      - `fast` will advance two steps at a time.
      - These pointers will move simultaneously.

    2. Traverse the linked list with the `slow` and `fast` pointers.
      - While traversing, repeatedly move `slow` one step and `fast` two steps at a time.

    3. Continue this traversal until one of the following conditions is met:
      - `fast` or `fast.next` reaches the end of the linked list (i.e., becomes null). In this case, there is no loop in the linked list ie. the linked list is linear, and the algorithm terminates by returning false.
      - fast` and `slow` pointers meet at the same node.
      - This indicates the presence of a loop in the linked list, and the algorithm terminates by returning `true`.
     */
    public static boolean hasCycle(Node head) {
        if(head == null || head.next==null)
            return false;

        Node slow = head;
        Node fast = head;
        while(fast!=null && fast.next!=null)
        {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)
                return true;
        }
        return false;
    }
    public static void main(String[] args)
    {
        // Create a sample linked list
        // with a loop for testing
        Node head = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);
        Node fourth = new Node(4);
        Node fifth = new Node(5);

        head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        // Create a loop
        fifth.next = third;
        System.out.print(hasCycle(head));
    }
}
