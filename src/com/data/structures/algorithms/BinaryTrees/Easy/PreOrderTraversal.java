package com.data.structures.algorithms.BinaryTrees.Easy;

import java.util.*;

/**
 * Problem Link: https://leetcode.com/problems/binary-tree-preorder-traversal/
 * PreOrder Traversal is a depth-first traversal method where the root node is visited first,
 * followed by the left subtree, and then the right subtree.
 */
public class PreOrderTraversal {

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

    /** Iterative PreOrder Traversal of a Binary Tree (NLR)
     * 1. Initialize:
     *    - Create an empty ArrayList to store the result
     *    - If root is null, return empty list
     *    - Create an empty Stack to simulate recursion
     *
     * 2. Start Traversal:
     *    - Push root node to stack initially
     *
     * 3. Process Nodes (while stack is not empty):
     *    - Pop current node from stack
     *    - Add current node's value to result list
     *    - Push right child first (if exists)
     *    - Push left child second (if exists)
     *    - Note: We push right first so left gets processed first (LIFO)
     *            So, Order of pushing (right then left) ensures NLR pattern
     *
     * 4. Return: Final result list containing preorder traversal
     * Time Complexity: O(n)
     * Space Complexity: O(n) for storing the result
     */
    public static List<Integer> preorderTraversalIterative(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null)
            return list;

        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        while(!st.isEmpty())
        {
            TreeNode curr = st.pop();
            list.add(curr.val);
            if(curr.right != null)
                st.push(curr.right);
            if(curr.left != null)
                st.push(curr.left);
        }
        return list;
    }


    /**
     * PreOrder Traversal of a Binary Tree (NLR)
     * 1. Visit the root node first.
     * 2. Traverse the left subtree in pre-order.
     * 3. Traverse the right subtree in pre-order.
     * Time Complexity: O(n)
     * Space Complexity: O(n) for storing the result
     */
    public static void findPreOrder(TreeNode root, List<Integer> list)
    {
        if(root == null)
            return;

        list.add(root.val);
        findPreOrder(root.left, list);
        findPreOrder(root.right, list);
        return;
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        findPreOrder(root, list);
        return list;
    }

    public static TreeNode constructBinaryTree(Integer[] arr) {
        if(arr[0] == -1 || arr.length == 0)
            return null;

        Queue<TreeNode> q = new LinkedList<>();
        // adding the root node to the queue
        TreeNode root = new TreeNode(arr[0]);
        q.add(root);
        // Start from the second element in the array, as the first is the root
        int i=1;
        while(!q.isEmpty())
        {
            TreeNode current = q.poll();
            // Left child
            if(i<arr.length && arr[i] != null)
            {
                current.left = new TreeNode(arr[i]);
                q.add(current.left);
            }
            i++;
            // Right child
            if(i<arr.length && arr[i] != null)
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
        // Assuming constructBinaryTree is implemented to create a binary tree from the array
        TreeNode root = constructBinaryTree(arr);
        List<Integer> result = preorderTraversal(root);
        System.out.println("PreOrder traversal is: " + result); // Output: [1, 2, 4, 5, 6, 7, 3, 8, 9]
    }
}
