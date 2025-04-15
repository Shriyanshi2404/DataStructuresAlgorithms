package com.data.structures.algorithms.LinkedLists.Easy;

import java.util.Scanner;

public class LengthOfLL {
    
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
    1. Take variable "length" as 0 and pointer as "curr", assign it to head
       - start traversing through the given list until "curr" pointer reaches end of LL
       - everytime keep on updating curr pointers with next element
       - and keep on incrementing length by 1
    2. Now, simply return the length of LL
    */
    public static int length(Node head){
        int length = 0;
        Node curr = head;
        while(curr != null)
        {
            curr = curr.next;
            length++;
        }
        return length;
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
        System.out.print("Length of linked list is : " + length(head));
    }
}
