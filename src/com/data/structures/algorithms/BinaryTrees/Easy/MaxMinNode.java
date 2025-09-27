package com.data.structures.algorithms.BinaryTrees.Easy;

/**
 * Problem link: https://www.geeksforgeeks.org/problems/max-and-min-element-in-binary-tree/1
 * This class provides methods to find the maximum and minimum values in a binary tree.
 */
public class MaxMinNode {

    static class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    /** This method finds the maximum value in a binary tree.
     * It recursively traverses the tree and compares the values of each node.
     * 1. If the root is null, return Integer.MIN_VALUE indicating no nodes.
     * 2. Recursively find the maximum in the left subtree and store it in 'leftMax'.
     * 3. Recursively find the maximum in the right subtree and store it in 'rightMax'.
     * 4. Compare both leftMax and rightMax and store the larger value in 'maxEle'. (maximum node from left and right subtrees).
     * 5. The maximum value is the largest among the current node's value, 'maxEle' and root's value.
     * 6. Return the maximum value.
     * Time Complexity: O(n) where n is the number of nodes in the tree.
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack).
     */
    public static int findMax(Node root) {
        if(root == null)
            return Integer.MIN_VALUE;
        int leftMax = findMax(root.left);
        int rightMax = findMax(root.right);
        int maxEle = Math.max(leftMax, rightMax);
        return Math.max(maxEle, root.data);
    }

    /** This method finds the minimum value in a binary tree.
     * It recursively traverses the tree and compares the values of each node.
     * 1. If the root is null, return Integer.MAX_VALUE indicating no nodes.
     * 2. Recursively find the minimum in the left subtree and store it in 'leftMin'.
     * 3. Recursively find the minimum in the right subtree and store it in 'rightMin'.
     * 4. Compare both leftMin and rightMin and store the larger value in 'minEle'. (minimum node from left and right subtrees).
     * 5. The minimum value is the smallest among the current node's value, 'minEle' and root's value.
     * 6. Return the minimum value.
     * Time Complexity: O(n) where n is the number of nodes in the tree.
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack).
     */
    public static int findMin(Node root) {
        if(root == null)
            return Integer.MAX_VALUE;
        int leftMin = findMin(root.left);
        int rightMin = findMin(root.right);
        int minEle = Math.min(leftMin, rightMin);
        return Math.min(minEle, root.data);
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(20);
        root.left.left = new Node(3);
        root.left.right = new Node(7);
        root.right.right = new Node(25);

        System.out.println("Maximum value in the tree: " + findMax(root));
        System.out.println("Minimum value in the tree: " + findMin(root));
    }
}
