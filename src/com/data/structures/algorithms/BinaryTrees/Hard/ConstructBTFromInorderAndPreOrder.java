package com.data.structures.algorithms.BinaryTrees.Hard;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/** Problem link: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/ */
public class ConstructBTFromInorderAndPreOrder {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
            left = right = null;
        }
    }

    static int preIndex = 0;

    /**
     * Algorithm to construct binary tree from preorder and inorder traversals:
     * 1. Use a recursive function to build the tree:
     *   - The root of the tree is the first element in the preorder array.
     *   - Find the index of this root in the inorder array using the map.
     *   - Recursively construct the left subtree using the elements before the root in the inorder array and assign the next element in the preorder array as the root of the left subtree.
     *   - Recursively construct the right subtree using the elements after the root in the inorder array and assign the next element in the preorder array as the root of the right subtree.
     *   - Return the constructed tree.
     * Time Complexity: O(n), where n is the number of nodes in the tree, as we visit each node once.
     * Space Complexity: O(n), for the map and the recursion stack.
     */
    public static TreeNode createTree(int[] preorder, int[] inorder, Map<Integer, Integer> map, int is, int ie) {
        if(is > ie)
            return null;

        TreeNode root = new TreeNode(preorder[preIndex++]);
        int index = map.get(root.val);

        root.left = createTree(preorder, inorder, map, is, index-1);
        root.right = createTree(preorder, inorder, map, index+1, ie);
        return root;
    }

    /**
     * Constructs a binary tree from given preorder and inorder traversals.
     * Algorithm:
     * 1. Create a map to store the indices of elements in the inorder array for quick access.
     * 2. Use a recursive function to build the tree by passing starting index (is) as 0 and ending index (ie) as the last index of the inorder array.
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<inorder.length; i++)
        {
            map.put(inorder[i], i);
        }
        return createTree(preorder, inorder, map, 0, inorder.length-1);
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
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        TreeNode root = buildTree(preorder, inorder);
        printBinaryTree(root);
        // You can add code here to print or verify the constructed tree
    }
}
