package com.data.structures.algorithms.BinaryTrees.Easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/** Problem link: https://www.geeksforgeeks.org/problems/level-order-traversal-line-by-line/1 */
public class PrintingLineByLine {

    static class TreeNode {
        int data;
        TreeNode left, right;

        TreeNode(int item) {
            data = item;
            left = right = null;
        }
    }

    /** This method performs level order traversal of a binary tree and returns the result as a list of lists.
     * It uses a queue to traverse the tree level by level, collecting nodes at each level into separate lists.
     * 1. Initialize an empty list to store the result.
     * 2. If the root is null, return the empty list.
     * 3. Add the root node to a queue for level order traversal.
     * 4. While the queue is not empty, repeat the following steps:
     *    - Get the size of the current level.
     *    - For each node at the current level, do the following:
     *      - Poll the front node from the queue and add its data to a temporary list.
     *      - Add its left child to the queue if it exists.
     *      - Add its right child to the queue if it exists.
     *    - Add the temporary list to the result list after processing all nodes at this level.
     * 5. Return the result list containing nodes grouped by levels.
     * Time Complexity: O(n) where n is the number of nodes in the tree.
     * Space Complexity: O(n) for storing the queue and result list.
     */
    public static ArrayList<ArrayList<Integer>> levelOrder(TreeNode node) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        if(node == null)
            return list;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(node);
        while(!q.isEmpty())
        {
            int size = q.size();
            ArrayList<Integer> arr = new ArrayList<>();
            for(int i=0; i<size; i++)
            {
                TreeNode curr = q.poll();
                arr.add(curr.data);

                if(curr.left != null)
                    q.add(curr.left);
                if(curr.right != null)
                    q.add(curr.right);
            }
            list.add(arr);
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
        Integer[] arr = {1, 2, 3, 4, null, null, 5};
        TreeNode root = constructBinaryTree(arr);
        ArrayList<ArrayList<Integer>> result = levelOrder(root);
        System.out.println("Level order traversal of the binary tree: " + result);
    }
}
