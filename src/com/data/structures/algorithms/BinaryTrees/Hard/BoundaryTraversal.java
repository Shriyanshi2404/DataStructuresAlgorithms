package com.data.structures.algorithms.BinaryTrees.Hard;

import java.util.ArrayList;
import java.util.Collections;

/** Problem link: https://www.geeksforgeeks.org/problems/boundary-traversal-of-binary-tree/1 */
public class BoundaryTraversal {

    static class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    /** * This function adds the left boundary nodes of the binary tree to the given array list.
     * It traverses the left boundary of the tree and adds non-leaf nodes to the array list.
     * Steps:
     * 1. Start from the left child of the root.
     * 2. Traverse down the left boundary, adding non-leaf nodes to a temporary list.
     *    - If the left child exists, move to the left child;
     *    - otherwise, move to the right child.
     * This ensures that only non-leaf nodes are added to the array list.
     * Time Complexity: O(n) where n is the number of nodes in the tree.
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack).
     */
    public static void addLeftBoundaryNodes(Node root, ArrayList<Integer> arr)
    {
        Node curr = root.left;
        while(curr != null)
        {
            if(!isLeaf(curr))
                arr.add(curr.data);

            if(curr.left != null)
                curr = curr.left;
            else
                curr = curr.right;
        }
    }

    /** * This function adds the leaf nodes of the binary tree to the given array list.
     * It traverses the tree recursively and adds all leaf nodes to the array list.
     * Steps:
     * 1. If the current node is a leaf node, add its data to the array list.
     * 2. If the current node is not a leaf, recursively call this function for its left and right children.
     * 3. This ensures that all leaf nodes are collected in the array list.
     * Time Complexity: O(n) where n is the number of nodes in the tree.
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack).
     */
    public static void addLeafNodes(Node root, ArrayList<Integer> arr)
    {
        if(isLeaf(root))
        {
            arr.add(root.data);
            return;
        }

        if(root.left != null)
            addLeafNodes(root.left, arr);

        if(root.right != null)
            addLeafNodes(root.right, arr);
    }

    /** * This function adds the right boundary nodes of the binary tree to the given array list.
     * It traverses the right boundary of the tree and adds non-leaf nodes to a temporary list,
     * which is then reversed and added to the original array list.
     * Steps:
     * 1. Start from the right child of the root.
     * 2. Traverse down the right boundary, adding non-leaf nodes to a temporary list.
     *   - If the right child exists, move to the right child;
     *   - otherwise, move to the left child.
     * 3. After reaching the bottom, reverse the temporary list.
     * 4. Add the reversed list to the original array list.
     * This ensures that the right boundary nodes are added in the correct order.
     * Time Complexity: O(n) where n is the number of nodes in the tree.
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack).
     */
    public static void addRightBoundaryNodes(Node root, ArrayList<Integer> arr)
    {
        Node curr = root.right;
        ArrayList<Integer> temp = new ArrayList<>();
        while(curr != null)
        {
            if(!isLeaf(curr))
                temp.add(curr.data);

            if(curr.right != null)
                curr = curr.right;
            else
                curr = curr.left;
        }

        // Reverse and add the values from
        // the temporary list to the original array list
        Collections.reverse(temp);
        arr.addAll(temp);
    }

    /** * This function checks if a node is a leaf node.
     * A leaf node is defined as a node that does not have any children.
     * Steps:
     * 1. If both left and right children of the node are null, it is a leaf node, return true.
     * 2. Otherwise, return false.
     */
    public static boolean isLeaf(Node root)
    {
        if(root.left == null && root.right == null)
            return true;
        return false;
    }

    /** This function performs boundary traversal of a binary tree.
     * Idea: Add the root node, left boundary nodes, leaf nodes, and right boundary nodes to an array list.
     * Steps:
     * 1. If the root is null, return an empty array list.
     * 2. If the root is not a leaf, add its data to the array list.
     * 3. Add left boundary nodes to the array list by calling `addLeftBoundaryNodes`.
     * 4. Add leaf nodes to the array list by calling `addLeafNodes`.
     * 5. Add right boundary nodes to the array list (in reverse order) by calling `addRightBoundaryNodes`.
     * 6. Return the array list containing the boundary traversal of the binary tree.
     * Time Complexity: O(n) where n is the number of nodes in the tree.
     * Space Complexity: O(h) where h is the height of the tree (due to recursion stack).
     */
    public static ArrayList<Integer> boundaryTraversal(Node root) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        if(root == null)
            return arr;

        if(!isLeaf(root))
            arr.add(root.data);

        addLeftBoundaryNodes(root, arr);
        addLeafNodes(root, arr);
        addRightBoundaryNodes(root, arr);
        return arr;
    }

    public static Node constructBinaryTree(Integer[] arr) {
        if(arr.length == 0 || arr[0] == null)
            return null;

        Node root = new Node(arr[0]);
        ArrayList<Node> queue = new ArrayList<>();
        queue.add(root);
        int i = 1;
        while(i < arr.length)
        {
            Node curr = queue.remove(0);
            // Left child
            if(i < arr.length && arr[i] != null)
            {
                curr.left = new Node(arr[i]);
                queue.add(curr.left);
            }
            i++;
            // Right child
            if(i < arr.length && arr[i] != null)
            {
                curr.right = new Node(arr[i]);
                queue.add(curr.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, null, null, 8, 9, null, null, null, null};
        Node root = constructBinaryTree(arr);
        ArrayList<Integer> boundary = boundaryTraversal(root);
        System.out.println("Boundary Traversal: " + boundary);  // Output: [1, 2, 4, 8, 9, 6, 7, 3]
    }
}
