package com.data.structures.algorithms.BinaryTrees.Medium;

import java.util.ArrayList;

/** Problem link: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/ */
public class LCA {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * This method finds the path from the root to a given node in a binary tree.
     * It uses recursion to traverse the tree and stores the path in an ArrayList.
     * If the node is found, it returns true and the path is stored.
     * If the node is not found, it removes the last node from the path and returns false.
     * Time Complexity: O(n), where n is the number of nodes in the tree.
     * Space Complexity: O(h), where h is the height of the tree due to recursion stack space and path storage.
     */
    public static boolean findPath(TreeNode root, TreeNode node, ArrayList<TreeNode> path)
    {
        if(root == null)
            return false;

        path.add(root);

        if(root.val == node.val)
            return true;

        // if path is found from left subtree
        if(findPath(root.left, node, path))
            return true;

        // if path is found from right subtree
        if(findPath(root.right, node, path))
            return true;

        path.remove(path.size()-1);
        return false;
    }

    /**
     * This method finds the lowest common ancestor (LCA) of two nodes in a binary tree.
     * It uses a path-finding approach to determine the paths from the root to each of the nodes.
     * The LCA is the last common node in both paths.
     * Idea:
     * 1. Find the path from the root to node p and store it in path1.
     * 2. Find the path from the root to node q and store it in path2.
     * 3. If either path is not found, return null.
     * 4. Compare the two paths to find the last common node.
     *    - Iterate through both paths until the nodes differ.
     *    - Check if the nodes at the same index in both paths are the same.
     *      - If they differ, the last node before the difference is the LCA, so break the loop.
     *      - If they are the same, continue to the next index.
     * 5. The last node before the difference is the LCA.
     * 6. Return the last common node as the LCA.
     * Time Complexity: O(n), where n is the number of nodes in the tree.
     * Space Complexity: O(h), where h is the height of the tree due to recursion stack space and path storage.
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode> path1 = new ArrayList<>();
        ArrayList<TreeNode> path2 = new ArrayList<>();
        boolean ans1 = findPath(root, p, path1);
        boolean ans2 = findPath(root, q, path2);

        if(ans1 == false || ans2 == false)
            return null;

        int i=0;
        while(i<path1.size() && i<path2.size())
        {
            if(path1.get(i) != path2.get(i))
                break;

            i++;
        }
        return path1.get(i-1);
    }

    public static TreeNode constructBinaryTree(Integer[] arr) {
        if(arr.length == 0 || arr[0] == null)
            return null;

        TreeNode root = new TreeNode(arr[0]);
        int i = 1;
        ArrayList<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        while(i < arr.length) {
            TreeNode curr = queue.remove(0);

            if(i < arr.length && arr[i] != null) {
                curr.left = new TreeNode(arr[i]);
                queue.add(curr.left);
            }
            i++;
            if(i < arr.length && arr[i] != null) {
                curr.right = new TreeNode(arr[i]);
                queue.add(curr.right);
            }
            i++;
        }
        return root;
    }

    /**
     * Algorithm to find the Lowest Common Ancestor (LCA) of two nodes in a binary tree.
     * 1. Start from the root node.
     * 2. If the current node is null, return null.
     * 3. If the current node is equal to either p or q, return the current node.
     * 4. Recursively search for p and q in the left and right subtrees.
     * 5. If both left and right subtree calls return non-null values, it means p and q are found in different subtrees, so return the current node as LCA.
     * 6. If left subtree calls says LCA1 is not null, it means both p and q are in that subtree, so return that LCA1.
     * 7. If right subtree calls says LCA2 is not null, it means both p and q are in that subtree, so return that LCA2.
     * 8. If both subtree calls return null, it means neither p nor q is found in the current subtree, so return null.
     * Time Complexity: O(n), where n is the number of nodes in the tree.
     * Space Complexity: O(h), where h is the height of the tree due to recursion stack space.
     */
    public static TreeNode findLCA(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null)
            return null;

        // if current node is same as p or q
        if(root.val == p.val || root.val == q.val)
            return root;

        TreeNode lca1 = findLCA(root.left, p, q);
        TreeNode lca2 = findLCA(root.right, p, q);

        // if one of subtree contains p and other subtree contains q, then both won't be null
        if(lca1 != null && lca2 != null)
            return root;

        // if both nodes lies in left subtree
        if(lca1 != null)
            return lca1;

            // if both nodes lies in right subtree
        else
            return lca2;
    }


    public static void main(String[] args) {
        Integer[] arr = {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
        TreeNode root = constructBinaryTree(arr);
        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(1);

        TreeNode lca = findLCA(root, p, q);

        if(lca != null) {
            System.out.println("LCA of " + p.val + " and " + q.val + " is: " + lca.val);
        } else {
            System.out.println("LCA not found.");
        }
    }
}
