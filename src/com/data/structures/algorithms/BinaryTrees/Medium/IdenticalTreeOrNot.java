package com.data.structures.algorithms.BinaryTrees.Medium;

import java.util.LinkedList;
import java.util.Queue;

/** Problem link: https://leetcode.com/problems/same-tree/description/ */
public class IdenticalTreeOrNot {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /** This method checks if two binary trees are identical.
     * Two trees are considered identical if they have the same structure and node values.
     * 1. If both nodes are null, return true (base case).
     * 2. If one of the nodes is null, return false (trees are not identical).
     * 3. If the values of the nodes are different, return false.
     * 4. Recursively check the left and right subtrees for equality.
     * Time Complexity: O(n) where n is the number of nodes in the trees.
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack).
     */
    public static boolean CheckIfTreeIsSame(TreeNode p, TreeNode q)
    {
        if(p == null || q == null)
            return p == q;

        if(p.val != q.val)
            return false;

        return CheckIfTreeIsSame(p.left, q.left) && CheckIfTreeIsSame(p.right, q.right);
    }

    /** This method checks if two binary trees are identical.
     * It uses a helper function to compare the structure and values of the trees.
     * 1. If both trees are null, they are identical.
     * 2. If one tree is null and the other is not, they are not identical.
     * 3. Use the helper function to check if the trees are structurally and value-wise identical.
     * Time Complexity: O(n) where n is the number of nodes in the trees.
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack).
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null)
            return true;
        if(p == null || q == null)
            return false;

        return CheckIfTreeIsSame(p, q);
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
        Integer[] arr1 = {1, 2, 3};
        Integer[] arr2 = {1, 2, 3};

        TreeNode tree1 = constructBinaryTree(arr1);
        TreeNode tree2 = constructBinaryTree(arr2);

        boolean result = isSameTree(tree1, tree2);
        System.out.println("Are the two trees identical? " + result); // Output: true

        Integer[] p = {1,2};
        Integer[] q = {1,null,2};

        TreeNode Tree1 = constructBinaryTree(p);
        TreeNode Tree2 = constructBinaryTree(q);

        boolean ans = isSameTree(Tree1, Tree2);
        System.out.println("Are the two trees identical? " + ans); // Output: false

    }
}
