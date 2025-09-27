package com.data.structures.algorithms.BST.Easy;

import java.util.LinkedList;
import java.util.Queue;

/** Problem link: https://leetcode.com/problems/insert-into-a-binary-search-tree/description/ */
public class InsertInBST {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * This method inserts a value into a Binary Search Tree (BST).
     * It recursively finds the correct position for the new value and inserts it.
     * Algorithm:
     * 1. If the current node is null, create a new TreeNode with the given value and return it.
     * 2. If the value to be inserted is less than the current node's value, recursively call the method on the left subtree.
     * 3. If the value to be inserted is greater than or equal to the current node's value, recursively call the method on the right subtree.
     * 4. After the recursive call, return the current node to maintain the structure of the tree.
     * This ensures that the properties of the BST are maintained, where all values in the left subtree are less than the current node's value,
     * Time Complexity: O(h), where h is the height of the tree.
     * Space Complexity: O(h) due to recursion stack space.
     */
    public static TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null)
            return new TreeNode(val);

        if(val < root.val)
            root.left = insertIntoBST(root.left, val);
        else
            root.right = insertIntoBST(root.right, val);
        return root;
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

    public static void printBST(TreeNode root) {
        if(root == null)
            return;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                System.out.print(curr.val + " ");
                if(curr.left != null) {
                    q.add(curr.left);
                }
                if(curr.right != null) {
                    q.add(curr.right);
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {4, 2, 7, 1, 3};
        TreeNode root = constructBinaryTree(arr);
        int valToInsert = 5;
        root = insertIntoBST(root, valToInsert);
        // Output the result of insertion
        printBST(root);
    }
}
