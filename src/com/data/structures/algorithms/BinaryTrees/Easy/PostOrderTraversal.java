package com.data.structures.algorithms.BinaryTrees.Easy;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Problem Link: https://leetcode.com/problems/binary-tree-postorder-traversal/
 * PostOrder Traversal is a depth-first traversal method where the left subtree is visited first,
 * followed by the right subtree, and then the root node.
 */
public class PostOrderTraversal {

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
     * PostOrder Traversal of a Binary Tree (LRN)
     * 1. Traverse the left subtree in post-order.
     * 2. Traverse the right subtree in post-order.
     * 3. Visit the root node.
     * Time Complexity: O(n)
     * Space Complexity: O(n) for storing the result
     */
    public static void findPostOrder(TreeNode root, List<Integer> list) {
        if(root == null)
            return;

        findPostOrder(root.left, list);
        findPostOrder(root.right, list);
        list.add(root.val);
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
        List<Integer> postOrderList = new LinkedList<>();
        findPostOrder(root, postOrderList);
        System.out.println("PostOrder Traversal: " + postOrderList);
    }
}
