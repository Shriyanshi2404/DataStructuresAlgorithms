package com.data.structures.algorithms.BST.Medium;

import java.util.*;

/** Problem link: https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/description/ */
public class ConstructBSTFromPreorderTraversal {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int item) {
            val = item;
            left = right = null;
        }
    }

    static int preIndex = 0;
    public static TreeNode createBST(int[] preorder, int[] inorder, int is, int ie, Map<Integer, Integer> map)
    {
        if(is>ie)
            return null;

        TreeNode root = new TreeNode(preorder[preIndex++]);

        int index = map.get(root.val);

        root.left = createBST(preorder, inorder, is, index-1, map);
        root.right = createBST(preorder, inorder, index+1, ie, map);
        return root;
    }

    /**
     * Constructs a Binary Search Tree (BST) from the given preorder traversal array.
     * Algorithm:
     * - Sort the preorder array to get the inorder traversal.
     * - Create a map to store the indices of the inorder elements for quick access.
     * - Recursively build the BST using the preorder array and the inorder indices.
     * - The root of the BST is created from the first element of the preorder array.
     * - The left and right subtrees are constructed recursively by finding the appropriate indices in the inorder array.
     * - The base case for recursion is when the start index is greater than the end index.
     * - The function returns the root of the constructed BST.
     * Time Complexity: O(n log n) due to sorting the inorder array.
     * Space Complexity: O(n) for the map and recursion stack.
     */
    public static TreeNode bstFromPreorder(int[] preorder) {
        if(preorder.length < 1)
            return null;

        int[] inorder = preorder.clone();
        Arrays.sort(inorder);

        // to store indices
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<inorder.length; i++)
            map.put(inorder[i], i);

        return createBST(preorder, inorder, 0, inorder.length-1, map);
    }

    public static TreeNode constructBinaryTree(Integer[] arr) {
        if(arr.length == 0 || arr[0] == null)
            return null;

        TreeNode root = new TreeNode(arr[0]);
        int i = 1;
        for(TreeNode curr : Arrays.asList(root)) {
            if(i < arr.length && arr[i] != null) {
                curr.left = new TreeNode(arr[i]);
                i++;
            }
            if(i < arr.length && arr[i] != null) {
                curr.right = new TreeNode(arr[i]);
                i++;
            }
        }
        return root;
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
        int[] preorder = {8, 5, 1, 7, 10, 12};
        TreeNode root = bstFromPreorder(preorder);

        // Print the constructed BST (in-order traversal)
        printBinaryTree(root);
    }
}
