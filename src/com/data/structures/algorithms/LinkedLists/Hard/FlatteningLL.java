package com.data.structures.algorithms.LinkedLists.Hard;

import java.util.ArrayList;
import java.util.Collections;

public class FlatteningLL {

    static class Node
    {
        int data;
        Node next;
        Node bottom;
        Node(int data)
        {
            this.data = data;
            this.next = null;
            this.bottom = null;
        }
    }

    /*
    Brute force approach
    T(n)=O(N)+O(N*Log N)+O(N) ~= O(N*Log N)
    S(n)=O(N)
    1. Create a empty array list that will store all node values
    2. Take pointer "curr" and assign it to root of linked list
    3. Now, start traversing until curr pointer reaches to end of linked list
        - add curr pointer data to array list
        - Now traverse through child nodes of curr pointer
        - keep adding child node values in array list
    4. Now, sort the array list
    5. traverse through the array list and start creating nodes and attach to new linked list bottom
    6. return new linked list
    */
    public static Node flatten(Node root) {
        ArrayList<Integer> arr = new ArrayList<>();

        Node curr = root;
        while(curr != null)
        {
            arr.add(curr.data);

            // traversing through child nodes
            Node child = curr.bottom;
            while(child != null)
            {
                arr.add(child.data);
                child = child.bottom;
            }
            curr = curr.next;
        }

        Collections.sort(arr);

        Node dummyNode = new Node(-1);
        curr = dummyNode;
        for(int ele : arr)
        {
            curr.bottom = new Node(ele);
            curr = curr.bottom;
        }
        return dummyNode.bottom;
    }

    public static Node mergeTwoLists(Node list1, Node list2)
    {
        Node dummyNode = new Node(-1);
        Node curr = dummyNode;

        while(list1 != null && list2 != null)
        {
            if(list1.data <= list2.data)
            {
                curr.bottom = list1;
                curr = list1;
                list1 = list1.bottom;
            }
            else
            {
                curr.bottom = list2;
                curr = list2;
                list2 = list2.bottom;
            }
            curr.next = null;
        }
        if(list1 != null)
            curr.bottom = list1;
        else
            curr.bottom = list2;

        return dummyNode.bottom;
    }

    /*
    Optimal approach
    T(n)=O(N*(2M)) ~= O(2 N*M)
    S(n)=O(1)
    Idea: Instead of collecting all node values into an array and then sorting them, we can merge these pre-sorted lists directly during the traversal, eliminating the need for additional sorting steps. This merge operation can be performed efficiently in place without allocating extra space for the combined linked list.
    1. Establish Base Case Conditions :
       Check if the base case conditions are met
       - If the head is null, indicating the end of the list, it is already flattened or there are no further nodes. Return the head as it is.
       - if there's no next node, meaning there's only one node left in the list, return the head as it is since it's already flattened.

    2. Recursively Merge the List:
       This recursion continues until it reaches the base case, gradually flattening the linked list and merging the resultant with the previous node.
       - Initiate the recursive flattening process by calling `flattenLinkedList` on the next node (`head -> next`).
       - The result of this recursive call is the head of the flattened and merged linked list.

    3. Merge Operations:
       - Inside the recursive call, call the merge function which takes care of the merging of these two lists based on their data values.
       - The merged list is updated in the head, which is then returned as the result of the flattening process.

    4. Following the recursion, the function returns the merged head of the new flattened linked list. This head marks the new head of the merged list starting from the end, which will now be merged with the present head.
    */
    public static Node flattenLists(Node root) {
        if(root == null || root.next == null)
        {
            return root;
        }

        Node currentHead = flatten(root.next);
        return mergeTwoLists(root, currentHead);
    }

    public static void main(String[] args)
    {
        Node firstNode = new Node(5);
        Node secondNode = new Node(10);
        Node thirdNode = new Node(19);
        Node fourthNode = new Node(28);
        firstNode.next = secondNode;
        secondNode.next = thirdNode;
        thirdNode.next = fourthNode;
        fourthNode.next = null;

        Node f1Node = new Node(7);
        Node f2Node = new Node(8);
        Node f3Node = new Node(30);
        firstNode.bottom = f1Node;
        f1Node.bottom = f2Node;
        f2Node.bottom = f3Node;
        f3Node.bottom = null;

        Node s1Node = new Node(20);
        secondNode.bottom = s1Node;
        s1Node.bottom = null;

        Node t1Node = new Node(22);
        Node t2Node = new Node(50);
        thirdNode.bottom = t1Node;
        t1Node.bottom = t2Node;
        t2Node.bottom = null;

        Node fo1Node = new Node(35);
        Node fo2Node = new Node(40);
        Node fo3Node = new Node(45);
        fourthNode.bottom = fo1Node;
        fo1Node.bottom = fo2Node;
        fo2Node.bottom = fo3Node;
        fo3Node.bottom = null;

        Node dummyNode = flattenLists(firstNode);
        while(dummyNode != null) {
            System.out.print(dummyNode.data + " ");
            dummyNode = dummyNode.bottom;
        }
    }
}
