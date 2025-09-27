package com.data.structures.algorithms.BinaryTrees.Medium;

import java.util.LinkedList;
import java.util.Queue;

/** Problem link: https://leetcode.com/problems/diameter-of-binary-tree/description/ */
public class DiameterOfBinaryTree {

    static class  TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static int height(TreeNode root)
    {
        if(root == null)
            return 0;

        int lh = height(root.left);
        int rh = height(root.right);

        return 1+Math.max(lh, rh);
    }

    /**
     * Idea: The diameter of a binary tree is the length of the longest path between any two nodes in the tree.
     *  MAX(lh+rh+1)
     *  d1 = find height of left subtree + height of right subtree
     *  d2 = call again recursively for left subtree
     *  d3 = call again recursively for right subtree
     *  return MAX(d1,d2,d3)
     * Algorithm:
     * 1. If the tree is empty, return 0.
     * 2. Calculate the height of the left and right subtrees.
     * 3. The diameter of the current node is the sum of the heights of the left and right subtrees.
     * 4. Recursively calculate the diameter of the left and right subtrees.
     * 5. The final diameter is the maximum of the current diameter and the diameters of the left and right subtrees.
     * Time Complexity: O(N+N) = O(2N), where n is the number of nodes in the tree.
     * Space Complexity: O(h), where h is the height of the tree (due to recursion stack).
     */
    public static int diameterOfBinaryTree(TreeNode root) {
        if(root == null)
            return 0;
        int d1 = height(root.left)+height(root.right);
        int d2 = diameterOfBinaryTree(root.left);
        int d3 = diameterOfBinaryTree(root.right);

        return Math.max(d1, Math.max(d2, d3));
    }

    /**
     * Helper function to calculate the diameter of the binary tree.
     * This function calculates the height of the tree and updates the diameter as it traverses the tree.
     * The diameter is updated as the maximum of the current diameter and the sum of the heights of the left and right subtrees.
     * Similar to height function, but also updates the diameter.
     */
    public static int diameterHelper(TreeNode root, int diameter)
    {
        if(root == null)
            return 0;

        int lh = diameterHelper(root.left, diameter);
        int rh = diameterHelper(root.right, diameter);

        diameter = Math.max(diameter, lh+rh);
        return 1 + Math.max(lh, rh);
    }

    /**
     * Optimized approach:
     * 1. Instead of calculating the height of the left and right subtrees separately, we can calculate the height and update the diameter in a single traversal.
     * 2. Use a helper function that returns the height of the current node while also updating the diameter.
     * 3. The diameter is updated as we traverse the tree, ensuring that we only traverse each node once.
     * Time Complexity: O(n), where n is the number of nodes in the tree.
     * Space Complexity: O(h), where h is the height of the tree (due to recursion stack).
     */
    public static int diameterOfBinaryTreeOptimized(TreeNode root) {
        if(root == null)
            return 0;
        if(root.left == null && root.right == null)
            return 0;

        int diameter = 0;
        return diameterHelper(root, diameter);
    }

    public static TreeNode constructBinaryTree(Integer[] arr) {
        if(arr.length == 0 || arr[0] == null)
            return null;

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while(!q.isEmpty()) {
            TreeNode curr = q.poll();

            if(i < arr.length && arr[i] != null) {
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
        Integer[] arr = {1, 2, 3, 4, 5};
        TreeNode root = constructBinaryTree(arr);
        int diameter = diameterOfBinaryTreeOptimized(root);
        System.out.println("Diameter of the binary tree: " + diameter);
    }
}
