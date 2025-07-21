package com.data.structures.algorithms.BinaryTrees.Easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/** * Problem link: https://www.geeksforgeeks.org/problems/k-distance-from-root/1 */
public class PrintNodesAtDistanceKfromRoot {

    static class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    /** Iterative method to print all nodes at distance k from the root of a binary tree.
     * 1. Initialize an empty list to store the nodes at distance k.
     * 2. If the root is null, return the empty list.
     * 3. If k is 0, add the root's data to the list and return it.
     * 4. Initialize a queue for level order traversal and add the root node to it, and set the level to 0.
     * 5. While the queue is not empty, repeat the following steps:
     *    - Get the size of the current level.
     *    - For each node at the current level, do the following:
     *      - Poll the front node from the queue.
     *      - If the current level is equal to k, add the node's data to the list.
     *      - If the current node has a left child, add it to the queue.
     *      - If the current node has a right child, add it to the queue.
     *    - Increment the level by 1.
     * 6. Continue this process until the queue is empty.
     * 7. Return the list containing the nodes at distance k from the root.
     * Time Complexity: O(n) where n is the number of nodes in the tree.
     * Space Complexity: O(n) for storing the queue.
     */
    public static ArrayList<Integer> Kdistance(Node root, int k) {
        ArrayList<Integer> list = new ArrayList<>();

        if(root == null)
            return list;

        if(k == 0)
        {
            list.add(root.data);
            return list;
        }

        int level = 0;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty())
        {
            int size = q.size();
            for(int i=0; i<size; i++)
            {
                Node curr = q.poll();
                if(level == k)
                    list.add(curr.data);

                if(curr.left != null)
                    q.add(curr.left);
                if(curr.right != null)
                    q.add(curr.right);
            }
            level++;
        }
        return list;
    }

    /** Recursive method to find all nodes at distance k from the root of a binary tree.
     * 1. If the root is null, return.
     * 2. If k is 0, add the root's data to the list.
     * 3. We will add a node when k is 0, which means we are at the required distance from the root.
     * 4. Recursively call the function for the left and right children with k-1.
     * 5. Return the list containing the nodes at distance k from the root.
     * Time Complexity: O(n) where n is the number of nodes in the tree.
     * Space Complexity: O(h) where h is the height of the tree due to recursion stack.
     */
    public static void findKDistanceNodes(Node root, int k, ArrayList<Integer> list)
    {
        if(root == null)
            return;

        if( k == 0)
            list.add(root.data);

        findKDistanceNodes(root.left, k-1, list);
        findKDistanceNodes(root.right, k-1, list);
    }

    public static ArrayList<Integer> KdistanceNodes(Node root, int k) {
        ArrayList<Integer> list = new ArrayList<Integer>();

        if(root == null)
            return list;

        if(k == 0)
        {
            list.add(root.data);
            return list;
        }

        findKDistanceNodes(root, k, list);
        return list;
    }


    public static void main(String[] args) {
        // Example usage
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        ArrayList<Integer> result = KdistanceNodes(root, 2);
        System.out.println("Nodes at distance 2 from root: " + result); // Output: [4, 5, 6, 7]
    }
}