package com.data.structures.algorithms.BinaryTrees.Medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/** Problem link: https://leetcode.com/problems/maximum-width-of-binary-tree/description/ */
public class MaximumWidth {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * This method calculates the maximum width of a binary tree.
     * The width of a level is defined as the number of nodes between the leftmost and rightmost non-null nodes in that level.
     * Algorithm:
     * 1. Use a queue to perform a level-order traversal of the tree.
     * 2. Maintain a map to store the position of each node, where the root is at position 1.
     * 3. Let currentWidth be the width of the current level, and maxWidth be the maximum width encountered so far.
     * 4. Traverse each level of the tree:
     *    - For each node, if it is the first node in the level, record its position as startPos.
     *    - If it is the last node in the level, record its position as endPos.
     *    - For each node, if it has a left child, add it to the queue with its position as 2 * current node's position.
     *    - If it has a right child, add it to the queue with its position as 2 * current node's position + 1.
     * 5. For each level, calculate the width by subtracting the position of the leftmost node from the position of the rightmost node and adding 1.
     * 6. Keep track of the maximum width encountered during the traversal.
     * 7. Return the maximum width after traversing all levels of the tree.
     * Time Complexity: O(n), where n is the number of nodes in the tree.
     * Space Complexity: O(n), for the queue and the map used to store node positions.
     */
    public static int widthOfBinaryTree(TreeNode root) {
        if(root == null)
            return 0;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        Map<TreeNode, Integer> map = new HashMap<>();
        map.put(root, 1);

        int currentWidth = 0;
        int maxWidth = 0;
        while(!q.isEmpty())
        {
            int size = q.size();
            int startPos = 0;
            int endPos = 0;
            for(int i=0; i<size; i++)
            {
                TreeNode curr = q.poll();
                if(i == 0)
                    startPos = map.get(curr);

                if(i == size-1)
                    endPos = map.get(curr);

                if(curr.left != null)
                {
                    q.add(curr.left);
                    map.put(curr.left, 2*map.get(curr));
                }

                if(curr.right != null)
                {
                    q.add(curr.right);
                    map.put(curr.right, 2*map.get(curr)+1);
                }
            }

            // width of each level is: last element position - first element position
            currentWidth = endPos-startPos+1;
            maxWidth = Math.max(currentWidth, maxWidth);
        }
        return maxWidth;
    }

    public static TreeNode constructBinaryTree(Integer[] arr) {
        if(arr.length == 0 || arr[0] == null)
            return null;

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while(!q.isEmpty()) {
            TreeNode curr = q.poll();

            if(i < arr.length && arr[i] != null) {
                curr.left = new TreeNode(arr[i]);
                q.add(curr.left);
            }
            i++;
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
        Integer[] arr = {1, 3, 2, 5, 3, null, 9};
        TreeNode root = constructBinaryTree(arr);
        System.out.println("Maximum Width of Binary Tree: " + widthOfBinaryTree(root));
    }
}
