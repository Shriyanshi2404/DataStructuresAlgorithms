package com.data.structures.algorithms.BST.Easy;

import java.util.LinkedList;
import java.util.Queue;

/** Problem link: https://www.geeksforgeeks.org/problems/implementing-ceil-in-bst/1 */
public class CeilValue {

    static class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    /** Algorithm
     * 1. If the root is null, return -1 as there is no ceil value.
     * 2. Initialize a queue to perform level order traversal starting from the root node.
     * 3. Initialize variables to keep track of the ceil value and the minimum difference.
     * 4. While the queue is not empty:
     *    - Dequeue the current node.
     *    - If the current node's data is greater than or equal to the key and the difference between the current node's data and the key is less than the minimum difference:
     *      - Update the ceil value to the current node's data.
     *      - Update the minimum difference to the difference between the current node's data and the key.
     *    - If the current node has a left child, enqueue it.
     *    - If the current node has a right child, enqueue it.
     * 5. After processing all nodes, return the ceil value.
     * Time Complexity: O(n), where n is the number of nodes in the tree, as we visit each node once.
     * Space Complexity: O(n), for the queue used in level order traversal.
     */
    static int findCeil(Node root, int key) {
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
                if(curr.data >= key && curr.data-key < diff)
                {
                    ans = curr.data;
                    diff = curr.data-key;
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
        Node root = new Node(8);
        root.left = new Node(3);
        root.right = new Node(10);
        root.left.left = new Node(1);
        root.left.right = new Node(6);
        root.right.right = new Node(14);
        root.left.right.left = new Node(4);
        root.left.right.right = new Node(7);
        root.right.right.left = new Node(13);

        int key = 5;
        int ceilValue = findCeil(root, key);
        System.out.println("Ceil value of " + key + " is: " + ceilValue); // Output: 6
    }
}
