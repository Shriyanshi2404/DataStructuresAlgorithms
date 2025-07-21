package com.data.structures.algorithms.BinaryTrees.Easy;

import java.util.LinkedList;
import java.util.Queue;

public class BalancedBinaryTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /** This method calculates the height of a binary tree.
     * The height of a binary tree is defined as the number of edges on the longest path from the root to a leaf node.
     * 1. If the root is null, return 0 (an empty tree has height 0).
     * 2. Recursively calculate the height of the left and right subtrees.
     * 3. The height of the current node is 1 plus the maximum of the heights of its left and right subtrees.
     * Time Complexity: O(n) where n is the number of nodes in the tree.
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack).
     */
    public static int height(TreeNode root)
    {
        if(root == null)
            return 0;

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return 1+Math.max(leftHeight, rightHeight);
    }

    /** Brute Force Approach
     * This method checks if a binary tree is balanced.
     * A balanced binary tree is defined as a tree where the height of the left subtree subtracting the height of the right subtree should not exceeds 1.
     * 1. If the root is null, return true (an empty tree is balanced).
     * 2. Calculate the height of the left and right subtrees by passing the left and right children of the root to the height function.
     * 3. If the absolute difference between the heights of the left and right subtrees is greater than 1, return false.
     * 4. Recursively check if the left and right subtrees are balanced.
     * Time Complexity: O(N^2) (height function is called for each node, leading to O(n) calls, and isBalances takes O(n) time).
     * Space Complexity: O(N) where h is the height of the tree (due to recursion stack).
     */
    public static boolean isBalanced(TreeNode root) {
        if(root == null)
            return true;

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        if(Math.abs(leftHeight-rightHeight) > 1)
            return false;

        return isBalanced(root.left) && isBalanced(root.right);
    }

    /** Optimal Approach
     * It calculates the height of the tree while checking if it is balanced, avoiding multiple height calculations.
     * 1. If the root is null, return 0 (an empty tree is balanced).
     * 2. Recursively calculate the height of the left subtree and store it in 'leftHeight'.
     *    - If 'leftHeight' is -1, it means the left subtree is not balanced, so return -1.
     * 3. Recursively calculate the height of the right subtree and store it in 'rightHeight'.
     *   - If 'rightHeight' is -1, it means the right subtree is not balanced, so return -1.
     * 4. If the absolute difference between 'leftHeight' and 'rightHeight' is greater than 1, return -1 (indicating the tree is not balanced).
     * 5. Otherwise, return the height of the current node, which is 1 plus the maximum of 'leftHeight' and 'rightHeight'.
     * Time Complexity: O(n) where n is the number of nodes in the tree.
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack).
     */
    public static int findIfBalancedOrNot(TreeNode root)
    {
        if(root == null)
            return 0;

        int leftHeight = findIfBalancedOrNot(root.left);
        if(leftHeight == -1)
            return -1;

        int rightHeight = findIfBalancedOrNot(root.right);
        if(rightHeight == -1)
            return -1;

        if(Math.abs(leftHeight-rightHeight) > 1)
            return -1;

        return 1+Math.max(leftHeight, rightHeight);
    }

    public static boolean isBalancedOptimal(TreeNode root) {
        if(root == null)
            return true;

        int res = findIfBalancedOrNot(root);
        if(res == -1 || res == 0)
            return false;
        return true;
    }

    public static TreeNode constructBinaryTree(Integer[] arr) {
        if(arr.length == 0 || arr[0] == null)
            return null;

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while(!q.isEmpty())
        {
            TreeNode curr = q.poll();
            // Left child
            if(i < arr.length && arr[i] != null)
            {
                curr.left = new TreeNode(arr[i]);
                q.add(curr.left);
            }
            i++;
            // Right child
            if(i < arr.length && arr[i] != null)
            {
                curr.right = new TreeNode(arr[i]);
                q.add(curr.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        // Example usage
        Integer[] arr = {3,9,20,null,null,15,7};
        TreeNode root = constructBinaryTree(arr);
        boolean balanced = isBalancedOptimal(root);
        System.out.println("Is the binary tree balanced? " + balanced); // Output: true

        Integer[] res = {1,2,2,3,3,null,null,4,4};
        TreeNode rootNode = constructBinaryTree(res);
        boolean isBalanced = isBalancedOptimal(rootNode);
        System.out.println("Is the binary tree balanced? " + isBalanced); // Output: false

    }
}
