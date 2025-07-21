package com.data.structures.algorithms.BinaryTrees.Medium;

import java.util.*;

/** Problem link: https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/ */
public class ZigZagTraversal {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * This method performs a zigzag level order traversal of a binary tree.
     * It uses a queue to traverse the tree level by level, and reverses the order of elements
     * at every odd level to achieve the zigzag pattern.
     * 2. Initialize a queue for level order traversal and a variable `level` to track the depth.
     * 3. Create a list `list` to store the final result of zigzag level order traversal.
     * 4. Check if the root is null, if so return an empty list indicating an empty tree.
     * 5. Add the root node to the queue.
     * 6. While the queue is not empty, repeat the following steps:
     *    - Create a variable `size` to store the current size of the queue.
     *    - Create a list `arr` to store the values of nodes at the current level.
     *    - For each node at the current level, do the following:
     *      - Poll the front node from the queue.
     *      - Add the value of the current node to the `arr` list.
     *      - If the current node has a left child, add it to the queue.
     *      - If the current node has a right child, add it to the queue.
     *    - After processing all nodes at the current level, check if the `level` is even or odd.
     *      - If the `level` is even, add the `arr` list to the result list as is.
     *      - If the `level` is odd, reverse the `arr` list and then add it to the result list.
     *    - Increment the `level` variable by 1.
     * 7. Continue this process until the queue is empty.
     * 8. The result list will contain the zigzag level order traversal of the binary tree.
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if(root == null)
            return list;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int level = 0;
        while(!q.isEmpty())
        {
            int size = q.size();
            List<Integer> arr = new ArrayList<>();
            for(int i=0; i<size; i++)
            {
                TreeNode curr = q.poll();
                arr.add(curr.val);
                if(curr.left != null)
                    q.add(curr.left);
                if(curr.right != null)
                    q.add(curr.right);
            }
            if(level % 2 == 0)
                list.add(arr);
            else
            {
                Collections.reverse(arr);
                list.add(arr);
            }
            level++;
        }
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
        Integer[] arr = {3,9,20,null,null,15,7};
        TreeNode root = constructBinaryTree(arr);
        List<List<Integer>> result = zigzagLevelOrder(root);
        System.out.println(result);
    }
}
