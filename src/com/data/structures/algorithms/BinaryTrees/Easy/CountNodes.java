package com.data.structures.algorithms.BinaryTrees.Easy;

import java.util.LinkedList;
import java.util.Queue;

/** Problem link: https://leetcode.com/problems/count-complete-tree-nodes/description/ */
public class CountNodes {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /** This method counts the number of nodes in a binary tree.
     * It uses recursion to traverse the tree and count each node.
     * 1. If the root is null, return 0 indicating no nodes.
     * 2. Recursively count the nodes in the left subtree and store it in 'leftCount'.
     * 3. Recursively count the nodes in the right subtree and store it in 'rightCount'.
     * 4. The total count is 1 (for the current node) plus the counts from both subtrees.
     * Time Complexity: O(n) where n is the number of nodes in the tree.
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack).
     */
    public static int countNodes(TreeNode root) {
        if(root == null)
            return 0;

        int leftCount = countNodes(root.left);
        int rightCount = countNodes(root.right);
        return 1+leftCount+rightCount;
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
        Integer[] arr = {1, 2, 3, 4, null, null, 5};
        TreeNode root = constructBinaryTree(arr);
        int count = countNodes(root);
        System.out.println("Number of nodes in the binary tree: " + count); // Output: 5
    }
}
