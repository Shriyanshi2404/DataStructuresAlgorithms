package com.data.structures.algorithms.BinaryTrees.Easy;

import java.util.*;

/**
 * Problem Link: https://leetcode.com/problems/binary-tree-inorder-traversal/
 * InOrder Traversal is a depth-first traversal method where the left subtree is visited first,
 * followed by the root node, and then the right subtree.
 */
public class InorderTraversal {

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
     * InOrder Traversal of a Binary Tree (LNR)
     * 1. Traverse the left subtree in in-order.
     * 2. Visit the root node.
     * 3. Traverse the right subtree in in-order.
     * Time Complexity: O(n)
     * Space Complexity: O(n) for storing the result
     */
    public static void findInOrder(TreeNode root, List<Integer> list) {
        if(root == null)
            return;

        findInOrder(root.left, list);
        list.add(root.val);
        findInOrder(root.right, list);
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

    /**
     * Approach for Iterative Inorder Traversal:
     * 1. Initialize:
     *    - Create empty ArrayList for result
     *    - Create empty Stack for tracking nodes
     *    - If root is null, return empty list
     *    - Set current pointer to root
     * 2. Process Left Subtree:
     *    - While the current node is not null
     *      - Push current node to stack
     *      - Move to left child (similarly we used to do in recursive approach)
     * 3. Process Node and Right Subtree:
     *    - When current becomes null that means we have reached the leftmost node
     *      - If stack is empty, break
     *      - Pop node from stack
     *      - Add node's value to result list
     *      - Move to right child of the popped node
     * 4. Repeat Steps 2-3 until stack is empty and current is null
     *
     * - LNR pattern maintained by:
     *   - Going left as far as possible
     *   - Processing node (adding to result)
     *   - Moving to right subtree
     * Time: O(n) - visit each node once
     * Space: O(h) - h is the height of the tree
     */

    public static List<Integer> inorderTraversalIterative(TreeNode root, List<Integer> list) {
        Stack<TreeNode> st = new Stack<>();
        if(root == null)
            return list;

        TreeNode curr = root;
        while(true)
        {
            if(curr != null)
            {
                st.push(curr);
                curr = curr.left;
            }
            else
            {
                if(st.isEmpty())
                    break;
                curr = st.pop();
                list.add(curr.val);
                curr = curr.right;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        Integer[] arr = {1,2,3,4,5,null,8,null,null,6,7,9};
        TreeNode root = constructBinaryTree(arr);
        List<Integer> result = new LinkedList<>();
        findInOrder(root, result);
        System.out.println("Inorder traversal is: " + result); // Output: [4, 2, 6, 5, 7, 1, 3, 9, 8]
    }
}
