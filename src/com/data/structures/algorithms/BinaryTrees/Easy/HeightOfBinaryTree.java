package com.data.structures.algorithms.BinaryTrees.Easy;

import java.util.LinkedList;
import java.util.Queue;

public class HeightOfBinaryTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
    /**
     * Height of a Binary Tree
     * 1. The height of a binary tree is the number of edges on the longest path from the root to a leaf node.
     * 2. If the tree is empty, the height is 0.
     * 3. Find the height of left subtree recursively and store it in 'leftHeight'
     * 4. Find the height of right subtree recursively and store it in 'rightHeight'
     * 5. The height of a non-empty tree is 1 plus the maximum height of its left and right subtrees.
     * Time Complexity: O(n)
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack)
     */
    public static int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        return 1+Math.max(leftHeight, rightHeight);
    }

    public static TreeNode constructBinaryTree(Integer[] arr)
    {
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
        int height = maxDepth(root);
        System.out.println("Height of the binary tree: " + height); // Output: 3
    }


}
