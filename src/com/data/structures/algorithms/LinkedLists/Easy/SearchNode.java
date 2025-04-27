package com.data.structures.algorithms.LinkedLists.Easy;

import java.util.Scanner;

public class SearchNode {

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
    1. Check if given list is null, if yes then return 0, as you cannot search for the given value 'k'
    2. Take pointer as "curr", assign it to head
       - start traversing through the given list until "curr" pointer reaches end of LL
       - check if curr pointer data matches with 'k', if yes then simply return 1
       - everytime keep on updating curr pointers with next element
    3. Else, return 0
    */
    public static int searchInLinkedList(Node head, int k)
    {
        if(head == null)
            return 0;
        Node curr = head;
        while(curr!=null)
        {
            if(curr.val == k)
                return 1;
            curr = curr.next;
        }
        return 0;
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
        int k = sc.nextInt();
        System.out.print(searchInLinkedList(head, k));
    }
}
