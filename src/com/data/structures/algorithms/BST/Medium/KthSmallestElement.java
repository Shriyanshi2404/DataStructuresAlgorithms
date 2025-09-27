package com.data.structures.algorithms.BST.Medium;

import java.util.*;

/** Problem link: https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/ */
public class KthSmallestElement {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int item) {
            val = item;
            left = right = null;
        }
    }

    /** Algorithm
     * 1. If the root is null, return -1 as there is no kth smallest element.
     * 2. If the root has no children, return the root's value.
     * 3. Initialize a queue to perform level order traversal starting from the root node.
     * 4. Initialize a list to store the values of the nodes.
     * 5. While the queue is not empty:
     *    - Dequeue the current node and add its value to the list.
     *    - If the current node has a left child, enqueue it.
     *    - If the current node has a right child, enqueue it.
     * 6. Sort the list of values.
     * 7. Return the (k-1)th element from the sorted list as the kth smallest element.
     * Time Complexity: O(n log n), where n is the number of nodes in the tree, due to sorting.
     * Space Complexity: O(n), for storing node values in a list.
     */
    public static int kthSmallest(TreeNode root, int k) {
        if(root == null)
            return -1;

        if(root.left == null && root.right == null)
            return root.val;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        List<Integer> list = new ArrayList<>();
        while(!q.isEmpty())
        {
            TreeNode curr = q.poll();
            list.add(curr.val);
            if(curr.left != null)
                q.add(curr.left);
            if(curr.right != null)
                q.add(curr.right);
        }
        Collections.sort(list);
        return list.get(k-1);
    }

    /**
     * Algorithm
     * 1. Perform an in-order traversal of the binary search tree (BST).
     * 2. Maintain a count of the nodes visited during the traversal.
     * 3. When the count reaches k, store the current node's value as the answer.
     * 4. Continue the traversal until all nodes are visited.
     * 5. Kth the smallest element is the element stored in 'ans' variable
     */
    static int count=0, ans=0;
    public static void inorderTraversal(TreeNode root, int k)
    {
        if(root == null)
            return;

        inorderTraversal(root.left, k);
        count++;
        if(count == k)
            ans = root.val;
        inorderTraversal(root.right, k);
    }

    /**
     * Idea:
     * 1. Perform an in-order traversal of the BST.
     * 2. Return the value of the kth node visited during the traversal.
     * Time Complexity: O(n), where n is the number of nodes in the tree, as we visit each node once.
     * Space Complexity: O(h), where h is the height of the tree due to recursion stack space.
     */
    public static int kthSmallestOptimal(TreeNode root, int k) {
        inorderTraversal(root, k);
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
        Integer[] arr = {5, 3, 6, 2, 4, null, null, 1};
        TreeNode root = constructBinaryTree(arr);

        int k = 3; // Example: find the 3rd smallest element
        int result = kthSmallestOptimal(root, k);

        System.out.println("The " + k + "rd smallest element is: " + result); // Output: The 3rd smallest element is: 3
    }
}
