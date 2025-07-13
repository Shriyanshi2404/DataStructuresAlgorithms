package com.data.structures.algorithms.BinaryTrees.Medium;

import java.util.*;

/**
 * Problem Link: https://leetcode.com/problems/binary-tree-postorder-traversal/
 * PostOrder Traversal using two stacks is a method to traverse a binary tree in post-order (LRN)
 * without using recursion.
 */
public class PostOrderTraversalUsingTwoStacks {

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
     * PostOrder Traversal of a Binary Tree (LRN) using two stacks
     * 1. Use the first stack to traverse the tree and push nodes onto the second stack.
     * 2. Pop nodes from the second stack to get the post-order traversal.
     * Time Complexity: O(n)
     * Space Complexity: O(n) for storing the result
     */
    public static void postorderTraversal(TreeNode root, List<Integer> list) {
        if(root == null)
            return;

        Stack<TreeNode> st1 = new Stack<>();
        Stack<TreeNode> st2 = new Stack<>();
        st1.push(root);
        while(!st1.isEmpty())
        {
            TreeNode curr = st1.pop();
            st2.push(curr);
            if(curr.left != null)
                st1.push(curr.left);
            if(curr.right != null)
                st1.push(curr.right);
        }

        while(!st2.isEmpty())
        {
            list.add(st2.pop().val);
        }
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
        postorderTraversal(root, postOrderList);
        System.out.println("PostOrder Traversal: " + postOrderList); // [4, 6, 7, 5, 2, 9, 8, 3, 1]
    }
}
