package com.data.structures.algorithms.BinaryTrees.Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/** Problem link: https://leetcode.com/problems/binary-tree-right-side-view/description/ */
public class RightViewOfBinaryTree {

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

    /** This method returns the right side view of a binary tree.
     * It uses a queue to perform level order traversal and collects the last node of each level.
     * Idea: The right side view of a binary tree is the set of nodes visible when the tree is viewed from the right side.
     * 1. Initialize a list to store the right side view.
     * 2. If the root is null, return an empty list.
     * 3. Add the root node to a queue for level order traversal.
     * 4. While the queue is not empty, repeat the following steps:
     *    - Get the size of the current level.
     *    - For each node at the current level, do the following:
     *      - Poll the front node from the queue.
     *      - If it is the last node of this level, add its value to the list i.e. current index is size-1.
     *      - Add its left child to the queue if it exists.
     *      - Add its right child to the queue if it exists.
     * 5. Return the list containing the right side view of the binary tree.
     * Time Complexity: O(n) where n is the number of nodes in the tree.
     * Space Complexity: O(n) for storing the queue and the result list.
     */
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null)
            return list;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty())
        {
            int size = q.size();
            for(int i=0; i<size; i++)
            {
                TreeNode curr = q.poll();
                if(i == size-1)
                    list.add(curr.val);

                if(curr.left != null)
                    q.add(curr.left);
                if(curr.right != null)
                    q.add(curr.right);
            }
        }
        return list;
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
        Integer[] arr = {1,2,3,4,null,null,null,5};
        TreeNode root = constructBinaryTree(arr);
        List<Integer> rightView = rightSideView(root);
        System.out.println("Right view of the binary tree: " + rightView); // Output: [1, 3, 4]
    }
}
