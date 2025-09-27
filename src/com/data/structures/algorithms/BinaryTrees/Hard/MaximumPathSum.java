package com.data.structures.algorithms.BinaryTrees.Hard;

import java.util.LinkedList;
import java.util.Queue;

/** Problem link: https://leetcode.com/problems/binary-tree-maximum-path-sum/description/ */
public class MaximumPathSum {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    int maxSum = Integer.MIN_VALUE;
    /**
     * PRE REQ: Maximum height and maximum diameter of binary tree
     * This function calculates the maximum path sum in a binary tree.
     * The maximum path sum is defined as the maximum sum of values along any path
     * from one node to another node in the tree, where a path can start and end at any node.
     * Algorithm:
     * 1. If the current node is null, return 0.
     * 2. Recursively calculate the maximum path sum for the left and right subtrees.
     * 3. If the left or right subtree returns a negative sum, we consider it as 0 to avoid reducing the overall path sum.
     * 4. Update the maximum path sum by considering the current node's value plus the left and right subtree sums.
     * 5. Return the maximum path sum that can be obtained by including the current node and either the left or right subtree.
     * 6. The maximum path sum is stored in the `maxSum` variable, which is updated during the recursion.
     * Time Complexity: O(n), where n is the number of nodes in the tree.
     * Space Complexity: O(h), where h is the height of the tree due to recursion stack space.
     */
    public int findMaxPathSum(TreeNode root)
    {
        if(root == null)
            return 0;

        // to avoid negative sum comparing with 0
        int leftSum = Math.max(0, findMaxPathSum(root.left));
        int rightSum = Math.max(0, findMaxPathSum(root.right));

        maxSum = Math.max(maxSum, (root.val + leftSum + rightSum));
        return root.val + Math.max(leftSum, rightSum);
    }

    /**
     * This method calculates the maximum path sum in a binary tree.
     * It initializes the maximum sum to the minimum integer value and calls the helper function to compute the maximum path sum.
     * Time Complexity: O(n), where n is the number of nodes in the tree.
     * Space Complexity: O(h), where h is the height of the tree due to recursion stack space.
     */
    public int maxPathSum(TreeNode root) {
        if(root == null)
            return 0;

        findMaxPathSum(root);
        return maxSum;
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
        Integer[] arr = {1, 2, 3, 4, 5, null, null};
        TreeNode root = constructBinaryTree(arr);
        MaximumPathSum mps = new MaximumPathSum();
        System.out.println("Maximum Path Sum: " + mps.maxPathSum(root)); // Output: 11 (4 -> 2 -> 1 -> 3)
    }
}
