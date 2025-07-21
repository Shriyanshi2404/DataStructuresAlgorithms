package com.data.structures.algorithms.BST.Medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/** Problem link: https://leetcode.com/problems/two-sum-iv-input-is-a-bst/description/ */
public class TwoSumInBST {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * Algorithm:
     * 1. Traverse the BST using a recursive function.
     * 2. Use a HashMap to store the values of the nodes as we traverse.
     * 3. For each node, check if the complement (k - node value) exists in the HashMap.
     *    - If it exists, set the ans variable to true and return.
     *    - If it does not exist, add the current node's value to the HashMap.
     * 4. Continue traversing the left and right subtrees.
     * 5. If the ans variable is already true, return immediately to avoid unnecessary computations.
     */
    static boolean ans = false;
    public static void findPair(TreeNode root, int k, Map<Integer, Boolean> map)
    {
        if(root == null)
            return;

        if(ans == true)
            return;

        if(map.containsKey(k-root.val))
        {
            ans = true;
            return;
        }

        map.put(root.val, true);
        findPair(root.left, k, map);
        findPair(root.right, k, map);
    }

    /**
     * This method checks if there are two elements in the Binary Search Tree (BST) that sum up to a given value k.
     * It uses a HashMap to store the values of the nodes as it traverses the tree.
     * If it finds a pair of values that sum to k, it sets the ans variable to true.
     * The time complexity is O(n) where n is the number of nodes in the BST.
     * The space complexity is O(n) for storing the values in the HashMap.
     */
    public static boolean findTarget(TreeNode root, int k) {
        Map<Integer, Boolean> map = new HashMap<>();
        findPair(root, k, map);
        return ans;
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
            if(i < arr.length && arr[i] != null) {
                curr.right = new TreeNode(arr[i]);
                q.add(curr.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        Integer[] arr = {5, 3, 6, 2, 4, null, 7};
        TreeNode root = constructBinaryTree(arr);

        int k = 9;
        boolean result = findTarget(root, k);
        System.out.println("Is there a pair with sum " + k + "? " + result);
    }
}
