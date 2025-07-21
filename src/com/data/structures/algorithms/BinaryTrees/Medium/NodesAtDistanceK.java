package com.data.structures.algorithms.BinaryTrees.Medium;

import java.util.*;

/** Problem link: https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/description/ */
public class NodesAtDistanceK {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
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
    public static void findParentNodes(TreeNode root, Map<TreeNode, TreeNode> parentMap)
    {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty())
        {
            TreeNode curr = q.poll();
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
     * This method finds all nodes at distance k from a target node in a binary tree.
     * It uses a BFS approach to traverse the tree and find nodes at the specified distance.
     * Algorithm:
     * 1. Build a parent map to keep track of parent-child relationships in the tree.
     * 2. Define a queue to perform a breadth-first search (BFS) starting from the target node and add the target node to it.
     * 3. Maintain a visited map to keep track of nodes that have already been visited to avoid cycles, mark target as visited.
     * 4. Initialize a distance counter to track the current distance from the target node, starting from 0.
     * 5. While the queue is not empty:
     *    - For each level, check if the current distance equals k, if yes then break the loop.
     *    - For each node in the current level:
     *      - Check if the left child exists and has not been visited, if yes then add it to the queue and mark it as visited.
     *      - Check if the right child exists and has not been visited, if yes, then add it to the queue and mark it as visited.
     *      - Check if the parent node exists and has not been visited, if yes, then add it to the queue and mark it as visited.
     *    - Increment the distance counter after processing all nodes at the current level.
     * 6. After the BFS completes, collect the values of all nodes remaining in the queue, which are at distance k from the target node.
     * Time Complexity: O(n), where n is the number of nodes in the tree.
     * Space Complexity: O(n), for storing the parent map and visited nodes.
     */
    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> list = new ArrayList<>();
        if(root == null)
            return list;

        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        findParentNodes(root, parentMap);

        Map<TreeNode, Boolean> visited = new HashMap<>();
        visited.put(target, true);

        Queue<TreeNode> q = new LinkedList<>();
        q.add(target);
        int dist = 0;

        while(!q.isEmpty())
        {
            int size = q.size();
            if(dist == k)
                break;
            for(int i=0; i<size; i++)
            {
                TreeNode curr = q.poll();

                if(curr.left != null && !visited.containsKey(curr.left))
                {
                    q.add(curr.left);
                    visited.put(curr.left, true);
                }
                if(curr.right != null && !visited.containsKey(curr.right))
                {
                    q.add(curr.right);
                    visited.put(curr.right, true);
                }
                if(parentMap.get(curr) != null && !visited.containsKey(parentMap.get(curr)))
                {
                    TreeNode parentNode = parentMap.get(curr);
                    q.add(parentNode);
                    visited.put(parentNode, true);
                }
            }
            dist++;
        }

        while(!q.isEmpty())
        {
            TreeNode currNode = q.poll();
            list.add(currNode.val);
        }
        return list;
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
        Integer[] arr = {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
        TreeNode root = constructBinaryTree(arr);
        TreeNode target = root.left; // Node with value 5
        int k = 2;

        List<Integer> result = distanceK(root, target, k);
        System.out.println(result); // Expected output: [7, 4, 1]
    }
}
