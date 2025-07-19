package com.data.structures.algorithms.BinaryTrees.Medium;

import java.util.ArrayList;

/** Problem link: https://www.geeksforgeeks.org/problems/root-to-leaf-paths/1 */
public class RootToLeafPaths {

    static class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }


    /** A helper method to find all paths from root to leaf nodes in a binary tree.
     * 1. If the current node is null, return.
     * 2. Add the current node's data to the current path.
     * 3. If the current node is a leaf node (both left and right children are null), add a copy of the current path to the list.
     * 4. Recursively call this method for the left and right children of the current node.
     * 5. After exploring both subtrees, backtrack by removing the last element from the current path.
     * Time Complexity: O(n) where n is the number of nodes in the tree.
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack).
     */
    public static void findAllPaths(Node root, ArrayList<ArrayList<Integer>> list, ArrayList<Integer> arr)
    {
        if(root == null)
            return;

        arr.add(root.data);

        // if we found a leaf node then add current path into resultant list
        if(root.left == null && root.right == null)
        {
            ArrayList<Integer> temp = new ArrayList<>(arr);
            list.add(temp);
        }

        findAllPaths(root.left, list, arr);
        findAllPaths(root.right, list, arr);

        // since we need to backtrack so remove last indexed element from current list
        arr.remove(arr.size()-1);
    }

    /** A method to find all paths from root to leaf nodes in a binary tree.
     * 1. Initialize an empty list to store the paths and an empty array list to store the current path.
     * 2. If the root is null, return the empty list.
     * 3. Call the helper function `findAllPaths` with the root node, the list, and the current path.
     * 4. The helper function recursively traverses the tree, adding nodes to the current path.
     * 5. When a leaf node is reached, it adds a copy of the current path to the list.
     * 6. Finally, return the list containing all paths from root to leaf nodes.
     * Time Complexity: O(n) where n is the number of nodes in the tree.
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack).
     */
    public static ArrayList<ArrayList<Integer>> Paths(Node root) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        ArrayList<Integer> arr = new ArrayList<>();
        if(root == null)
            return list;

        findAllPaths(root, list, arr);
        return list;
    }

    public static Node constructBinaryTree(Integer[] arr) {
        if(arr.length == 0 || arr[0] == null)
            return null;

        Node root = new Node(arr[0]);
        ArrayList<Node> queue = new ArrayList<>();
        queue.add(root);
        int i = 1;
        while(i < arr.length) {
            Node current = queue.remove(0);
            if(i < arr.length && arr[i] != null) {
                current.left = new Node(arr[i]);
                queue.add(current.left);
            }
            i++;
            if(i < arr.length && arr[i] != null) {
                current.right = new Node(arr[i]);
                queue.add(current.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        // Example usage
        Integer[] arr = {1, 2, 3, 4, 5, null, null};
        Node root = constructBinaryTree(arr);
        ArrayList<ArrayList<Integer>> result = Paths(root);

        // Print the paths
        for(ArrayList<Integer> path : result) {
            System.out.println(path);
        }
        // Output: [[1, 2, 4], [1, 2, 5], [1, 3]]
        // Explanation: All the possible paths from root node to leaf nodes are: 1 -> 2 -> 4, 1 -> 2 -> 5 and 1 -> 3
    }
}
