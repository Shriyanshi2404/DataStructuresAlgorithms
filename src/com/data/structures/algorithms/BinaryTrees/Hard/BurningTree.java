package com.data.structures.algorithms.BinaryTrees.Hard;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/** Problem link: https://leetcode.com/problems/burn-a-binary-tree/description/ */
public class BurningTree {

    static class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    /**
     * This method finds all parent nodes in a binary tree and stores them in a map.
     * It uses a breadth-first search (BFS) approach to traverse the tree and build the parent-child relationships.
     * Algorithm:
     * 1. Initialize a queue to perform BFS starting from the root node.
     * 2. While the queue is not empty:
     *    - Dequeue the current node.
     *    - If the left child exists, add it to the parent map with the current node as its parent and enqueue it.
     *    - If the right child exists, add it to the parent map with the current node as its parent and enqueue it.
     * 3. Continue until all nodes have been processed.
     * Time Complexity: O(n), where n is the number of nodes in the tree.
     * Space Complexity: O(n), for storing the parent map.
     */
    public static void findParentNodes(Node root, Map<Node, Node> parentMap)
    {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty())
        {
            Node curr = q.poll();
            if(curr.left != null)
            {
                parentMap.put(curr.left, curr);
                q.add(curr.left);
            }
            if(curr.right != null)
            {
                parentMap.put(curr.right, curr);
                q.add(curr.right);
            }
        }
    }

    /**
     * This method finds the target node in the binary tree.
     * It uses a recursive approach to traverse the tree and find the node with the specified value.
     * If the node is found, it returns the node; otherwise, it returns null.
     * Time Complexity: O(n), where n is the number of nodes in the tree.
     * Space Complexity: O(h), where h is the height of the tree due to recursion stack space.
     */
    public static Node findTargetNode(Node root, int target)
    {
        if(root == null)
            return null;

        if(root.data == target)
            return root;

        Node leftResult = findTargetNode(root.left, target);
        if (leftResult != null)
            return leftResult;

        return findTargetNode(root.right, target);
    }


    /**
     * This method calculates the minimum time required to burn the binary tree starting from a target node.
     * It uses a breadth-first search (BFS) approach to traverse the tree and find all nodes that can be burned.
     * The time taken to burn the tree is equal to the number of levels traversed in the BFS.
     * Algorithm:
     * 1. Build a parent map to keep track of parent-child relationships in the tree.
     * 2. Find the target node based on the given value.
     * 3. Define a queue to perform a breadth-first search (BFS) starting from the target node and add the target node to it.
     * 4. Maintain a visited map to keep track of nodes that have already been visited to avoid cycles, mark target as visited.
     * 5. Initialize a burnTime counter to track the current burn time from the target node, starting from 0.
     * 6. While the queue is not empty:
     *   - Assign a pointer "found" as false to check if any unvisited nodes are found in the current level.
     *   - For each node in the current level:
     *      - Check if the left child exists and has not been visited, if yes then add it to the queue and mark it as visited.
     *      - Check if the right child exists and has not been visited, if yes, then add it to the queue and mark it as visited.
     *      - Check if the parent node exists and has not been visited, if yes, then add it to the queue and mark it as visited.
     *      - Also assign "found" to true if any unvisited nodes are found in the current level.
     *   - If "found" is true, increment the burnTime counter.
     * 7. After the BFS completes, return the total burn time, which represents the minimum time required to burn the tree from the target node.
     * 8. Return the total burn time after processing all reachable nodes.
     * Time Complexity: O(n), where n is the number of nodes in the tree.
     * Space Complexity: O(n), for storing the parent map and visited nodes.
     */
    public static int minTime(Node root, int target) {
        if(root == null)
            return 0;
        Map<Node, Node> parentMap = new HashMap<>();
        findParentNodes(root, parentMap);

        Node targetNode = findTargetNode(root, target);
        Queue<Node> q = new LinkedList<>();
        q.add(targetNode);

        Map<Node, Boolean> visited = new HashMap<>();
        visited.put(targetNode, true);

        int burnTime = 0;

        while(!q.isEmpty())
        {
            int size = q.size();
            boolean found = false;
            for(int i=0; i<size; i++)
            {
                Node curr = q.poll();
                if(curr.left != null && !visited.containsKey(curr.left))
                {
                    q.add(curr.left);
                    visited.put(curr.left, true);
                    found = true;
                }
                if(curr.right != null && !visited.containsKey(curr.right))
                {
                    q.add(curr.right);
                    visited.put(curr.right, true);
                    found = true;
                }
                if(parentMap.get(curr) != null && !visited.containsKey(parentMap.get(curr)))

                {
                    Node parentNode = parentMap.get(curr);
                    q.add(parentNode);
                    visited.put(parentNode, true);
                    found = true;
                }
            }
            if(found == true)
                burnTime++;
        }
        return burnTime;
    }

    public static Node constructBinaryTree(Integer[] arr) {
        if(arr.length == 0 || arr[0] == null)
            return null;

        Node root = new Node(arr[0]);
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while(!q.isEmpty()) {
            Node curr = q.poll();

            if(i < arr.length && arr[i] != null) {
                curr.left = new Node(arr[i]);
                q.add(curr.left);
            }
            i++;
            if(i < arr.length && arr[i] != null) {
                curr.right = new Node(arr[i]);
                q.add(curr.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7};
        Node root = constructBinaryTree(arr);
        int target = 2; // Example target node value
        int burnTime = minTime(root, target);
        System.out.println("Minimum time to burn the tree from target node " + target + " is: " + burnTime);
    }
}
