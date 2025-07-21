package com.data.structures.algorithms.BinaryTrees.Medium;

import java.util.LinkedList;
import java.util.Queue;

/** Problem link: https://leetcode.com/problems/symmetric-tree/description/ */
public class SymmetricTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }
    }


    /** This method checks if two binary trees are symmetric.
     * It compares the left subtree of one tree with the right subtree of the other tree recursively.
     * 1. If both subtrees are null, return true (they are symmetric).
     * 2. If one subtree is null and the other is not, return false (they are not symmetric).
     * 3. If the values of the current nodes are equal, recursively check their children:
     *    - Compare left child of left subtree with right child of right subtree.
     *    - Compare right child of left subtree with left child of right subtree.
     * Time Complexity: O(n) where n is the number of nodes in the tree.
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack).
     */
    public static boolean IsTreeSymmetric(TreeNode leftSubtree, TreeNode rightSubtree)
    {
        if(leftSubtree == null || rightSubtree == null)
            return leftSubtree == rightSubtree;

        return (leftSubtree.val == rightSubtree.val) &&
                IsTreeSymmetric(leftSubtree.left, rightSubtree.right) &&
                IsTreeSymmetric(leftSubtree.right, rightSubtree.left);
    }

    /** This method checks if a binary tree is symmetric.
     * A binary tree is symmetric if the left subtree is a mirror reflection of the right subtree.
     * 1. If the root is null, return true (an empty tree is symmetric).
     * 2. Call the helper method IsTreeSymmetric with the left and right children of the root.
     * 3. The helper method checks if both subtrees are null or if their values are equal and recursively check their children.
     * Time Complexity: O(n) where n is the number of nodes in the tree.
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack).
     */
    public static boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;

        return IsTreeSymmetric(root.left, root.right);
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
        Integer[] arr = {1, 2, 2, 3, 4, 4, 3};
        TreeNode root = constructBinaryTree(arr);
        boolean result = isSymmetric(root);
        System.out.println("Is the binary tree symmetric? " + result); // Output: true

        Integer[] arr2 = {1,2,2,null,3,null,3};
        TreeNode root2 = constructBinaryTree(arr2);
        boolean ans = isSymmetric(root2);
        System.out.println("Is the binary tree symmetric? " + ans); // Output: false
    }
}
