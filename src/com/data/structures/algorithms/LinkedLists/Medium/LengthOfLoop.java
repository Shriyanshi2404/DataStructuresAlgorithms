package com.data.structures.algorithms.LinkedLists.Medium;

import java.util.HashMap;

public class LengthOfLoop {
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
    Brute force approach
    T(n)=O(N) S(n)=O(N)
    Idea: to keep track of nodes and the timer value associated with them. This can be implemented using a hashmap with nodes as the key and the timer as the value.
    1. Traverse through the LL using the traversal technique of assigning a "curr" node to the head and iterating by moving to the next element till we reach null.
       - While traversing, keep track of the Visited nodes and the timer value associated in the map data structure.
    2. Continue traversing till a node that has already been visited is found. The difference between its timer value in the hashmap and the current timers value will be the length of the linked list.
    3.  If the traversal is completed, and we reach the last point of the linked list which is null, it means there was no loop, hence we return false.
    */
    public static int countNodesinLoopBruteForce(Node head) {
        if(head == null || head.next == null)
            return 0;

        HashMap<Node, Integer> visitedNodes = new HashMap<>();
        int timer = 0;
        Node curr = head;
        while(curr != null)
        {
            if(visitedNodes.containsKey(curr))
            {
                int length = (timer - visitedNodes.get(curr));
                return length;
            }

            visitedNodes.put(curr, timer);
            timer++;
            curr = curr.next;
        }
        return 0;
    }

    /*
    Optimal Approach
    T(n)=O(N) S(n)=O(1)
    1. Initialise two pointers, `slow` and `fast`, to the head of the linked list. `slow` will advance one step at a time, while `fast` will advance two steps at a time. These pointers will move simultaneously.
    2. Traverse the linked list with the `slow` and `fast` pointers. While traversing, repeatedly move `slow` one step and `fast` two steps at a time.
    3.Continue this traversal until one of the following conditions is met:
      - `fast` or `fast.next` reaches the end of the linked list (i.e., becomes null). In this case, there is no loop in the linked list hence it is linear, and the algorithm terminates by returning 0.
      - `fast` and `slow` pointers meet at the same node. This indicates the presence of a loop in the linked list as we have seen in the detection of loop.
    4. Now, This is the point where the slow and fast have met proving that there is a loop in the linked list. From here onwards we start counting for the length of this loop.
    5. Initialise a counter with zero and traverse the linked list using the `fast` pointer again, but this time only move one step at a time while incrementing the counter with each node visited.
       - As the fast pointer reaches back at the slow pointer, the value of the counter will represent the length of the loop.
    6. return count
    */
    public static int findLength(Node slow, Node fast)
    {
        int count = 1;
        fast = fast.next;
        while(fast != slow)
        {
            fast = fast.next;
            count++;
        }
        return count;
    }

    public static int countNodesinLoop(Node head) {
        if(head == null || head.next == null)
            return 0;

        Node slow = head;
        Node fast = head;
        while(fast!=null && fast.next!=null)
        {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)
            {
                return findLength(slow, fast);
            }
        }

        return 0;
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
        System.out.print("Length of loop is: " + countNodesinLoop(head));
    }
}
