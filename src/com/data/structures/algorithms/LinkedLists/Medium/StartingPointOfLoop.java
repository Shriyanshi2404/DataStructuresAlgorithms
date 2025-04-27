package com.data.structures.algorithms.LinkedLists.Medium;

import java.util.HashMap;

public class StartingPointOfLoop {

    static class Node {
        int val;
        Node next;
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
        - if present, simply return current node as we found a starting point
        - if not present, then add node in hashmap
    5. Once you reach end of linked list and still you don't find any loop then simply return null
     */
    public static boolean detectCycle(DetectCycle.Node head) {
        HashMap<DetectCycle.Node, Boolean> map = new HashMap<>();
        DetectCycle.Node curr = head;
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
    1. Initialise two pointers, `slow` and `fast`, to the head of the linked list.
       - `slow` will advance one step at a time
       - 'fast` will advance two steps at a time.
       - These pointers will move simultaneously.
    2. Traverse the linked list with the `slow` and `fast` pointers.
       - While traversing, repeatedly move `slow` one step and `fast` two steps at a time.
    3. Continue this traversal until one of the following conditions is met:
       - `fast` or `fast.next` reaches the end of the linked list (i.e., becomes null). In this case, there is no loop in the linked list, and the algorithm terminates by returning null.
       - `fast` and `slow` pointers meet at the same node.
       - This indicates the presence of a loop in the linked list.
    4. Reset the `slow` pointer to the head of the linked list.
       - Move `fast` and `slow` one step at a time until they meet again.
       - The point where they meet again is the starting point.
     */
    public static Node findStartingPoint(Node slow, Node head)
    {
        Node temp = head;
        while(slow != temp)
        {
            slow = slow.next;
            temp = temp.next;
        }
        return slow;
    }

    public static Node detectCycle(Node head) {
        Node slow = head;
        Node fast = head;
        while(fast!=null && fast.next!=null)
        {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)
            {
                return findStartingPoint(slow, head);
            }
        }
        return null;
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
        System.out.print(detectCycle(head).val);
    }
}
