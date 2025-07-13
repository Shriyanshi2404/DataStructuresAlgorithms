package com.data.structures.algorithms.BinaryTrees.Easy;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Problem Link: https://leetcode.com/problems/binary-tree-inorder-traversal/
 * InOrder Traversal is a depth-first traversal method where the left subtree is visited first,
 * followed by the root node, and then the right subtree.
 */
public class InorderTraversal {

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
     * InOrder Traversal of a Binary Tree (LNR)
     * 1. Traverse the left subtree in in-order.
     * 2. Visit the root node.
     * 3. Traverse the right subtree in in-order.
     * Time Complexity: O(n)
     * Space Complexity: O(n) for storing the result
     */
    public static void findInOrder(TreeNode root, List<Integer> list) {
        if(root == null)
            return;

        findInOrder(root.left, list);
        list.add(root.val);
        findInOrder(root.right, list);
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
            TreeNode current = q.poll();
            // Left child
            if(i < arr.length && arr[i] != null)
            {
                current.left = new TreeNode(arr[i]);
                q.add(current.left);
            }
            i++;
            // Right child
            if(i < arr.length && arr[i] != null)
            {
                current.right = new TreeNode(arr[i]);
                q.add(current.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        Integer[] arr = {1,2,3,4,5,null,8,null,null,6,7,9};
        TreeNode root = constructBinaryTree(arr);
        List<Integer> result = new LinkedList<>();
        findInOrder(root, result);
        System.out.println("Inorder traversal is: " + result); // Output: [4, 2, 6, 5, 7, 1, 3, 9, 8]
    }
}
