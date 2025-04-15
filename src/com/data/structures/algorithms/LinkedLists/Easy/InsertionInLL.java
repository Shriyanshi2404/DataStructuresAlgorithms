package com.data.structures.algorithms.LinkedLists.Easy;

import java.util.Scanner;

public class InsertionInLL {

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
    Optimal solution
    T(n) = O(N)  S(n) = O(1)
    Note: new node is created in O(1) time
    1. Create a new node with data as the given value and pointing to the head. This node will be our new head of the linked list.
    2. If the pos is starting position i.e. 0 then simply insert new node at head
    3. Else take two pointers prev and curr, point them to head
       - start traversing through the given head for i=0 to i<pos
       - everytime keep on updating prev and curr pointers
       - once we come out from for-loop, perform below actions
       - prev's next pointer will point to new Node
       - new node's next pointer will point to curr node
    4. Now, simply return the new node as the head of the updated Linked List.
    */
    public static Node insert(Node head, int n, int pos, int val) {
        Node newNode = new Node(val);
        if(pos == 0 || head == null)
        {
            newNode.next = head;
            head = newNode;
            return head;
        }
        Node prev = head;
        Node curr = head;
        for(int i=0;i<pos;i++)
        {
            prev = curr;
            curr = curr.next;
        }
        prev.next = newNode;
        newNode.next = curr;
        return head;
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

        int value = sc.nextInt();
        int pos = sc.nextInt();
        head = insert(head, n, pos, value);
        Node curr = head;
        while(curr!=null)
        {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
    }
}
