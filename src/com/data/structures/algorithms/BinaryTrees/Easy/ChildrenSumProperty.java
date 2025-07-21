package com.data.structures.algorithms.BinaryTrees.Easy;

/** Problem link: https://leetcode.com/problems/root-equals-sum-of-children/ */
public class ChildrenSumProperty {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /** This method checks if a binary tree satisfies the children sum property.
     * The children sum property states that for every node in the tree, the value of the node should be equal to the sum of the values of its left and right children.
     * 1. If the root is null, return true (an empty tree satisfies the property).
     * 2. If the root is a leaf node (both children are null), return true.
     * 3. Calculate the sum of the values of the left and right children.
     * 4. If the value of the root node is not equal to this sum, return false.
     * 5. Recursively check the left and right subtrees.
     * Time Complexity: O(n) where n is the number of nodes in the tree.
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack).
     */
    public static boolean checkTree(TreeNode root) {
        if(root == null)
            return true;
        if(root.left == null && root.right == null)
            return true;

        int sum = 0;
        if(root.left != null)
            sum += root.left.val;
        if(root.right != null)
            sum += root.right.val;

        if(root.val != sum)
            return false;

        return checkTree(root.left) && checkTree(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(4);
        root.right = new TreeNode(6);
        System.out.println(checkTree(root)); // Output: true

        root.left.right = new TreeNode(5);
        System.out.println(checkTree(root)); // Output: false
    }
}
