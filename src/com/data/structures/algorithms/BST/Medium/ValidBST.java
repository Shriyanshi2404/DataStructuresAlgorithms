package com.data.structures.algorithms.BST.Medium;

import java.util.LinkedList;
import java.util.Queue;

/** Problem link: https://leetcode.com/problems/validate-binary-search-tree/description/ */
public class ValidBST {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int item) {
            val = item;
            left = right = null;
        }
    }

    /** Helper function to check if a binary tree is a valid Binary Search Tree (BST):
     * 1. If the current node is null, return true.
     * 2. Check if the current node's value is within the valid range defined by min and max.
     * 3. Recursively check the left subtree with updated max value and right subtree with updated min value.
     * 4. Return true if both subtrees are valid, otherwise return false.
     */
    public static boolean checkIfBST(TreeNode root, long min, long max)
    {
        if(root == null)
            return true;

        if(root.val >= max || root.val <= min)
            return false;

        return checkIfBST(root.left, min, (long)root.val) &&
                checkIfBST(root.right, (long)root.val, max);
    }

    /** Algorithm to check if a binary tree is a valid Binary Search Tree (BST):
     * 1. If the root is null, return true as an empty tree is a valid BST.
     * 2. If the root has no children, return true as a single node tree is a valid BST.
     * 3. Use a helper function to recursively check if each node's value is within the valid range:
     *    - The left child's value must be less than the current node's value.
     *    - The right child's value must be greater than the current node's value.
     * 4. Return true if all nodes satisfy the BST properties, otherwise return false.
     * Time Complexity: O(n), where n is the number of nodes in the tree.
     * Space Complexity: O(h), where h is the height of the tree due to recursion stack space.
     */
    public static boolean isValidBST(TreeNode root) {
        if(root == null)
            return true;

        if(root.left == null && root.right == null)
            return true;

        return checkIfBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static TreeNode constructBinaryTree(Integer[] arr) {
        if(arr.length == 0 || arr[0] == null)
            return null;

        TreeNode root = new TreeNode(arr[0]);
        int i = 1;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            TreeNode curr = q.poll();

            if(i < arr.length && arr[i] != null)
            {
                curr.left = new TreeNode(arr[i]);
                q.add(curr.left);
            }
            i++;
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
        Integer[] arr = {5,1,4,null,null,3,6};
        TreeNode root = constructBinaryTree(arr);

        boolean result = isValidBST(root);
        System.out.println("Is the tree a valid BST? " + result); // Expected output: false
    }
}
