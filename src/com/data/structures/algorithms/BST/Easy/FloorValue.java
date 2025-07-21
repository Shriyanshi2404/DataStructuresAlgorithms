package com.data.structures.algorithms.BST.Easy;

import java.util.LinkedList;
import java.util.Queue;

/** Problem link: https://www.geeksforgeeks.org/problems/floor-in-bst/1 */
public class FloorValue {

    static class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    /** Algorithm
     * 1. If the root is null, return -1 as there is no floor value.
     * 2. Initialize a queue to perform level order traversal starting from the root node.
     * 3. Initialize variables to keep track of the floor value and the minimum difference.
     * 4. While the queue is not empty:
     *    - Dequeue the current node.
     *    - If the current node's data is less than or equal to the key and the difference between the key and the current node's data is less than the minimum difference:
     *      - Update the floor value to the current node's data.
     *      - Update the minimum difference to the difference between the key and the current node's data.
     *    - If the current node has a left child, enqueue it.
     *    - If the current node has a right child, enqueue it.
     * 5. After processing all nodes, return the floor value.
     * Time Complexity: O(n), where n is the number of nodes in the tree, as we visit each node once.
     * Space Complexity: O(n), for the queue used in level order traversal.
     */
    public static int floor(Node root, int key) {
        if(root == null)
            return -1;

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int ans = -1;
        int diff = Integer.MAX_VALUE;
        while(!q.isEmpty())
        {
            int size = q.size();
            for(int i=0; i<size; i++)
            {
                Node curr = q.poll();
                if(curr.data <= key && key-curr.data < diff)
                {
                    ans = curr.data;
                    diff = key-curr.data;
                }
                if(curr.left != null)
                    q.add(curr.left);

                if(curr.right != null)
                    q.add(curr.right);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.left.left = new Node(3);
        root.left.right = new Node(7);
        root.right.right = new Node(20);

        int key = 6;
        int floorValue = floor(root, key);
        System.out.println("Floor value of " + key + " is: " + floorValue); // Output: 5
    }
}
