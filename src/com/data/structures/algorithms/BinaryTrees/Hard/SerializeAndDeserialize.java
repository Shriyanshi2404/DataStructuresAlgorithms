package com.data.structures.algorithms.BinaryTrees.Hard;

import java.util.LinkedList;
import java.util.Queue;

/** Problem link: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/ */
public class SerializeAndDeserialize {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
            left = right = null;
        }
    }

    /** Encodes a tree to a single string.
     * Serializes a binary tree to a string representation.
     * The tree is traversed in level order, and null nodes are represented by "#".
     * Algorithm:
     * 1. Initialize a queue to perform level order traversal starting from the root node.
     * 2. While the queue is not empty:
     *    - Dequeue the current node.
     *    - If the current node is null, append "#," to the string.
     *    - If the current node is not null, append its value followed by a comma to the string.
     *    - Enqueue the left and right children of the current node (which may be null).
     *    - Continue until all nodes have been processed.
     * 3. Return the serialized string.
     * Time Complexity: O(n), where n is the number of nodes in the tree, as each node is processed once.
     * Space Complexity: O(n), for the queue and the string builder used to store the serialized data.
     */
    public static String serialize(TreeNode root) {
        if(root == null)
            return "";

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        StringBuilder str = new StringBuilder();
        while(!q.isEmpty()) {
            TreeNode curr = q.poll();
            if(curr == null)
            {
                str.append("#,");
                continue;
            }
            else
            {
                str.append(curr.val).append(",");
                q.add(curr.left);
                q.add(curr.right);
            }
        }
        return str.toString();
    }

    /** Decodes your encoded data to tree.
     * Deserializes a string representation of a binary tree back into a tree structure.
     * The string is expected to be in level order format, with null nodes represented by "#".
     * Algorithm:
     * 1. If the input string is empty, return null.
     * 2. Split the input string by commas to get an array of node values.
     * 3. Create the root node from the first value in the array.
     * 4. Initialize a queue to perform level order traversal and pointer 'i' to 1
     * 5. Build the tree:
     *    - Dequeue the current node.
     *    - If the next value is not "#", create a left child and enqueue it. Increment 'i'.
     *    - If the next value is not "#", create a right child and enqueue it. Increment 'i'.
     *    - The, continue until all values in the array have been processed.
     * 6. Return the constructed tree i.e. root node
     * Time Complexity: O(n), where n is the number of nodes in the tree, as each node is processed once.
     * Space Complexity: O(n), for the queue used to build the tree.
     */
    public static TreeNode deserialize(String data) {
        if(data.length() == 0)
            return null;

        String[] arr = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i =1;
        while(!q.isEmpty())
        {
            TreeNode curr = q.poll();
            if(i < arr.length && !arr[i].equals("#"))
            {
                curr.left = new TreeNode(Integer.parseInt(arr[i]));
                q.add(curr.left);
            }
            i++;
            if(i < arr.length && !arr[i].equals("#"))
            {
                curr.right = new TreeNode(Integer.parseInt(arr[i]));
                q.add(curr.right);
            }
            i++;
        }
        return root;
    }

    public static void printBinaryTree(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            TreeNode curr = q.poll();
            if(curr == null) {
                System.out.print("null ");
                continue;
            }
            else {
                q.add(curr.left);
                q.add(curr.right);
            }
            System.out.print(curr.val + " ");
        }
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
        Integer[] arr = {1,2,3,null,null,4,5};
        TreeNode root = constructBinaryTree(arr);
        System.out.println("Original Tree: ");
        printBinaryTree(root);
        System.out.println();

        String serializedData = serialize(root);
        System.out.println("Serialized Data: " + serializedData);

        TreeNode deserializedRoot = deserialize(serializedData);
        System.out.print("Deserialized Tree: ");
        printBinaryTree(deserializedRoot);
    }


}
