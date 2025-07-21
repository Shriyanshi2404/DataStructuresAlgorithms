package com.data.structures.algorithms.BinaryTrees.Hard;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/** Problem link: https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/ */
public class ConstructBTFromInorderAndPostOrder {
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
            left = right = null;
        }
    }

    /** * Algorithm to construct a binary tree from inorder and postorder traversals:
     * 1. Use a recursive function to build the tree:
     *   - The root of the tree is the last element in the postorder array.
     *   - Find the index of this root in the inorder array using a map.
     *   - Recursively construct the right subtree using the elements after the root in the inorder array and assign the next element in the postorder array as the root of the right subtree.
     *   - Recursively construct the left subtree using the elements before the root in the inorder array and assign the next element in the postorder array as the root of the left subtree.
     *   - Return the constructed tree.
     * Time Complexity: O(n), where n is the number of nodes in the tree, as we visit each node once.
     * Space Complexity: O(n), for the map and recursion stack.
     */
    public static TreeNode createTree(int[] inorder, int[] postorder, Map<Integer, Integer> map, int is, int ie, int[] postIndex)
    {
        if(is > ie || postIndex[0] < 0)
            return null;

        TreeNode root = new TreeNode(postorder[postIndex[0]]);

        int index = map.get(root.val);
        postIndex[0]--;

        root.right = createTree(inorder, postorder, map, index+1, ie, postIndex);
        root.left = createTree(inorder, postorder, map, is, index-1, postIndex);
        return root;
    }

    /**
     * Constructs a binary tree from given inorder and postorder traversals.
     * Algorithm:
     * 1. Create a map to store the indices of elements in the inorder array for quick access.
     * 2. Use a recursive function to build the tree by passing starting index (is) as 0 and ending index (ie) as the last index of the inorder array.
     */
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<inorder.length; i++)
        {
            map.put(inorder[i], i);
        }

        int[] postIndex = new int[] {postorder.length - 1}; // array wrapper
        return createTree(inorder, postorder, map, 0, inorder.length-1, postIndex);
    }

    public static void printBinaryTree(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            TreeNode curr = q.poll();
            if(curr == null) {
                System.out.print("null ");
                continue;
            }
            else {
                q.add(curr.left);
                q.add(curr.right);
            }
            System.out.print(curr.val + " ");
        }
    }

    public static void main(String[] args) {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};

        TreeNode root = buildTree(inorder, postorder);
        printBinaryTree(root); // Output: 3 9 20 null null 15 null null 7 null null
    }
}
