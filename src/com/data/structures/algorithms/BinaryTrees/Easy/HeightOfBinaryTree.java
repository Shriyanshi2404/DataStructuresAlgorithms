package com.data.structures.algorithms.BinaryTrees.Easy;

import java.util.LinkedList;
import java.util.Queue;

public class HeightOfBinaryTree {

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
    /** RECURSIVE APPROACH
     * Height of a Binary Tree
     * 1. The height of a binary tree is the number of edges on the longest path from the root to a leaf node.
     * 2. If the tree is empty, the height is 0.
     * 3. Find the height of left subtree recursively and store it in 'leftHeight'
     * 4. Find the height of right subtree recursively and store it in 'rightHeight'
     * 5. The height of a non-empty tree is 1 plus the maximum height of its left and right subtrees.
     * Time Complexity: O(n)
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack)
     */
    public static int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        return 1+Math.max(leftHeight, rightHeight);
    }

    /** ITERATIVE APPROACH
     * Height of a Binary Tree using Iteration
     * 1. Use a queue to perform level order traversal.
     * 2. Initialise a queue for level order traversal and a variable `level` to track the depth.
     * 3. Check if the root is null, if so return the answer as 0 indicating an empty tree.
     * 4. Add the root node to the queue.
     * 5. While the queue is not empty, repeat the following steps:
     *    - Create a variable `size` to store the current size of the queue.
     *    - For each node at the current level, do the following:
     *      - Pop the front node from the queue.
     *      - If the node has a left child, add it to the queue.
     *      - If the node has a right child, add it to the queue.
     *      - Decrease the size of the queue by 1.
     *     - After processing all nodes at the current level, increment the `level` variable by 1.
     * 6. Continue this process until the queue is empty.
     * 7. The value of `level` at the end will be the height of the binary tree.
     * Time Complexity: O(n)
     * Space Complexity: O(n) for storing the queue
     */
    public static int heightOfBT(TreeNode root) {
        if(root == null)
            return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int level = 0;
        while(!q.isEmpty())
        {
            int size = q.size();
            for(int i=0; i<size; i++)
            {
                TreeNode curr = q.poll();
                if(curr.left != null)
                    q.add(curr.left);
                if(curr.right != null)
                    q.add(curr.right);
            }
            level++;
        }
        return level;
    }

    public static TreeNode constructBinaryTree(Integer[] arr)
    {
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
        Integer[] arr = {3,9,20,null,null,15,7};
        TreeNode root = constructBinaryTree(arr);
        int height = maxDepth(root);
        System.out.println("Height of the binary tree: " + height); // Output: 3
    }


}
