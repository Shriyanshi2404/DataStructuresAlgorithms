package com.data.structures.algorithms.BinaryTrees.Hard;

import java.util.*;

/** Problem link: https://www.geeksforgeeks.org/bottom-view-binary-tree/ */
public class BottomView {

    /** Node class to represent a node in the binary tree */
    static class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    /** Pair class to hold the node, its vertical line, and level in the tree */
    static class Pair {
        Node node;
        int verticalLine;
        int level;
        Pair(Node node, int verticalLine, int level)
        {
            this.node = node;
            this.verticalLine = verticalLine;
            this.level = level;
        }
    }

    /** This method returns the bottom view of a binary tree.
     * Idea: In a map: update the key 'vertical line' with the current node's data, meaning this node can be viewed from the bottom view, every time we encounter a node at the same vertical line, we update it to the latest node.
     *
     * It uses a queue for level order traversal and a TreeMap to store the last node encountered at each vertical line.
     * 1. Initialize an empty list to store the bottom view nodes.
     * 2. If the root is null, return the empty list.
     * 3. Create a queue to hold pairs of nodes, their vertical line, and level.
     * 4. Add the root node with vertical line 0 and level 0 to the queue.
     * 5. Create a TreeMap to store the last node encountered at each vertical line.
     * 6. While the queue is not empty, repeat the following steps:
     *    - Get the size of the current level.
     *    - For each node at this level, do the following:
     *      - Poll the front pair from the queue.
     *      - Update the map with this vertical line and current node's data, meaning this node can be viewed from bottom view, every time we encounter a node at the same vertical line, we update it to the latest node.
     *      - Add its left child with vertical line -1 and incremented level +1 to the queue if it exists.
     *      - Add its right child with vertical line +1 and incremented level +1 to the queue if it exists.
     * 7. After processing all nodes, iterate through the map and add each value to the result list.
     * 8. Return the list containing nodes visible from the bottom view of the binary tree.
     * Time Complexity: O(n) where n is the number of nodes in the tree.
     * Space Complexity: O(n) for storing the queue and map.
     */
    public static ArrayList<Integer> bottomView(Node root) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(root == null)
            return list;

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0, 0));

        // Map: {verticalLine : node}
        // Idea: the first node which is associated with particular vertical line will be visible from top view.
        TreeMap<Integer, Integer> map = new TreeMap<>();

        while(!q.isEmpty())
        {
            int size = q.size();
            for(int i=0; i<size; i++)
            {
                Pair pair = q.poll();
                int verticalLine = pair.verticalLine;
                int level = pair.level;
                Node curr = pair.node;

                map.put(verticalLine, curr.data);

                if(curr.left != null)
                    q.add(new Pair(curr.left, verticalLine-1, level+1));
                if(curr.right != null)
                    q.add(new Pair(curr.right, verticalLine+1, level+1));
            }
        }

        for(Map.Entry<Integer,Integer> entry : map.entrySet())
        {
            int value = entry.getValue();
            list.add(value);
        }
        return list;
    }

    public static Node constructBinaryTree(Integer[] arr) {
        if(arr.length == 0 || arr[0] == null)
            return null;

        Node root = new Node(arr[0]);
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while(!q.isEmpty())
        {
            Node curr = q.poll();
            // Left child
            if(i < arr.length && arr[i] != null)
            {
                curr.left = new Node(arr[i]);
                q.add(curr.left);
            }
            i++;
            // Right child
            if(i < arr.length && arr[i] != null)
            {
                curr.right = new Node(arr[i]);
                q.add(curr.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        // Example usage
        Integer[] arr = {10, 20, 30, 40, 60, 90, 100};
        Node root = constructBinaryTree(arr);
        ArrayList<Integer> result = bottomView(root);
        System.out.println(result); // Output: [40, 20, 90, 30, 100]
    }
}
