package com.data.structures.algorithms.BST.Easy;

import java.util.LinkedList;
import java.util.Queue;

import static com.data.structures.algorithms.BST.Easy.SearchInBST.searchBST;

/** Problem link: https://leetcode.com/problems/search-in-a-binary-search-tree/description/ */
public class SearchInBST {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    /** This method searches for a value in a binary search tree (BST).
     * It uses recursion to traverse the tree based on the properties of BST.
     * 1. If the root is null, return null/root (value not found).
     * 2. If the value matches the root's value, return the root.
     * 3. If the value is less than the root's value, search in the left subtree.
     * 4. If the value is greater than the root's value, search in the right subtree.
     * Time Complexity: O(h) where h is the height of the tree.
     * Space Complexity: O(h) due to recursion stack.
     */
    public static TreeNode searchBST(TreeNode root, int val) {
        if(root == null)
            return root;

        if(root.val == val)
            return root;

        if(val < root.val)
            return searchBST(root.left, val);

        else
            return searchBST(root.right, val);
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
        Integer[] arr = {4, 2, 7, 1, 3};
        TreeNode root = constructBinaryTree(arr);
        int valToSearch = 2;
        TreeNode result = searchBST(root, valToSearch);

        if(result != null) {
            System.out.println("Node found with value: " + result.val);
        } else {
            System.out.println("Node not found.");
        }
    }
}
