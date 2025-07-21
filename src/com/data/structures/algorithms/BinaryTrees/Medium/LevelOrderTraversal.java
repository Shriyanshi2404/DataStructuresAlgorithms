package com.data.structures.algorithms.BinaryTrees.Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Problem Link: https://leetcode.com/problems/binary-tree-level-order-traversal/
 * Level Order Traversal is a breadth-first traversal method where nodes are visited level by level.
 */
public class LevelOrderTraversal {

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
     * Level Order Traversal of a Binary Tree
     * 1. Use a queue to traverse the tree level by level.
     * 2. Initialise an empty queue data structure to store the nodes during traversal. Create a ArrayList to store the level order traversal.
     *    - If the tree is empty, return this empty arraylist.
     * 3. Add the root node to the queue.
     * 4. While the queue is not empty, repeat the following steps:
     *    - Create a new inner ArrayList to store the values of the current level.
     *    - Get the current size of the queue. This size indicates the number of nodes at the current level.
     *    - For each node at the current level, do the following:
     *      - Pop the front node from the queue.
     *      - Store the current node to the inner ArrayList.
     *      - If node has a left child, add it to the queue.
     *      - If node has a right child, add it to the queue.
     *      - Decrease the size of the queue by 1.
     *    - After processing all nodes at the current level, add the inner ArrayList to the result list.
     * 5. Continue this process until the queue is empty.
     * 6. Return the result list containing the level order traversal of the binary tree.
     * Time Complexity: O(n)
     * Space Complexity: O(n) for storing the result
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if(root == null)
            return list;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty())
        {
            List<Integer> arr = new ArrayList<>();
            int size = q.size();
            while(size != 0)
            {
                TreeNode curr = q.poll();
                arr.add(curr.val);

                if(curr.left != null)
                    q.add(curr.left);

                if(curr.right != null)
                    q.add(curr.right);
                size--;
            }
            list.add(arr);
        }
        return list;
    }

    public static TreeNode constructBinaryTree(Integer[] arr) {
        if(arr.length == 0 || arr[0] == null)
            return null;

        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(arr[0]);
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
        Integer[] arr = {1,2,3,4,5,null,8,null,null,6,7,9};
        TreeNode root = constructBinaryTree(arr);
        List<List<Integer>> levelOrderList = levelOrder(root);
        System.out.println("Level Order Traversal: " + levelOrderList); // [[1], [2, 3], [4, 5, 8], [6, 7, 9]]
    }
}
