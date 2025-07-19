package com.data.structures.algorithms.BST.Easy;

import java.util.LinkedList;
import java.util.Queue;

/** Problem link: https://www.geeksforgeeks.org/problems/minimum-element-in-bst/1 */
public class MinMaxNodeInBST {

    static class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    /** This method finds the minimum value in a binary search tree (BST).
     * Idea: In a BST, the leftmost node contains the minimum value.
     * It recursively traverses the left subtree to find the minimum value.
     * 1. If the root is null, return -1 indicating no nodes.
     * 2. If the left child is null, return the current node's data as it is the minimum.
     * 3. Recursively call this method on the left child to continue searching for the minimum.
     * Time Complexity: O(h) where h is the height of the tree.
     * Space Complexity: O(h) due to recursion stack.
     */
    public static int findMinValue(Node root)
    {
        if(root == null)
            return -1;

        if(root.left == null)
            return root.data;

        return findMinValue(root.left);
    }

    public static int minValue(Node root) {
        if(root == null)
            return -1;

        return findMinValue(root);
    }

    /** This method finds the maximum value in a binary search tree (BST).
     * Idea: In a BST, the rightmost node contains the maximum value.
     * It recursively traverses the right subtree to find the maximum value.
     * 1. If the root is null, return -1 indicating no nodes.
     * 2. If the right child is null, return the current node's data as it is the maximum.
     * 3. Recursively call this method on the right child to continue searching for the maximum.
     * Time Complexity: O(h) where h is the height of the tree.
     * Space Complexity: O(h) due to recursion stack.
     */
    public static int findMaxValue(Node root)
    {
        if(root == null)
            return -1;

        if(root.right == null)
            return root.data;

        return findMaxValue(root.right);
    }

    public static int maxValue(Node root) {
        if(root == null)
            return -1;

        return findMaxValue(root);
    }

    public static Node constructBinaryTree(Integer[] arr) {
        if(arr.length == 0 || arr[0] == null)
            return null;

        Node root = new Node(arr[0]);
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while(!q.isEmpty())
        {
            Node curr = q.poll();
            // Left child
            if(i < arr.length && arr[i] != null)
            {
                curr.left = new Node(arr[i]);
                q.add(curr.left);
            }
            i++;
            // Right child
            if(i < arr.length && arr[i] != null)
            {
                curr.right = new Node(arr[i]);
                q.add(curr.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        // Example usage
        Integer[] arr = {15, 10, 20, 8, 12, 17, 25};
        Node root = constructBinaryTree(arr);

        int minValue = minValue(root);
        int maxValue = maxValue(root);

        System.out.println("Minimum value in BST: " + minValue); // Output: 8
        System.out.println("Maximum value in BST: " + maxValue); // Output: 25
    }
}
