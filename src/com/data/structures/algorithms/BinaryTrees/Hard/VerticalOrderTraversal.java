package com.data.structures.algorithms.BinaryTrees.Hard;

import java.util.*;

/**
 * Problem link: https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/description/
 * This class implements vertical order traversal of a binary tree.
 * It uses a queue to perform level order traversal and a TreeMap to store nodes by their vertical line and level.
 */
public class VerticalOrderTraversal {

    /** TreeNode class to represent a node in the binary tree */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val)
        {
            this.val = val;
        }
    }

    /** Pair class to hold the node, its vertical line, and level in the tree */
    class Pair {
        TreeNode node;
        int verticalLine;
        int level;
        Pair(TreeNode node, int v, int l)
        {
            this.node = node;
            this.verticalLine = v;
            this.level = l;
        }
    }

    /** Idea:
     * We can assign a vertical and level to every node. This will help us in categorizing nodes based on their position in the binary tree.
     *    Vertical Coordinates (x): The vertical coordinate, denoted as 'x', represents the vertical column in the tree. It essentially signifies the horizontal position of a node in relation to its parent. Nodes with the same 'x' value are aligned vertically, forming a column.
     *    Level Coordinates (y): The level coordinate, denoted as 'y', represents the depth or level of a node in the tree. It signifies the vertical position of a node within the hierarchy of levels. As we traverse down the tree, the 'y' value increases, indicating a deeper level.
     *
     * We create a map that serves as our organizational structure. The map is based on the vertical and level information of each node.
     *   - The vertical information, represented by 'x', signifies the vertical column,
     *   - The level information, denoted as 'y', acts as the key within the nested map.
     *   - This nested map uses a multiset to ensure that node values are stored in a unique and sorted order.
     *
     * We initiate a level order BFS traversal using a queue.
     * Each element in the queue is a pair containing the current node and its corresponding vertical and level coordinates.
     * Starting with the root node, we enqueue it with initial vertical and level values (0, 0).
     * During traversal, for each dequeued node, we update the map by inserting the node value at its corresponding coordinates and enqueue its left and right children with adjusted vertical and level information.
     *    - When traversing to the left child, the vertical value decreases by 1 and the level increases by 1,
     *    - while traversal to the right child leads to an increase in both vertical and level by 1.
     *
     * After completing the BFS traversal, we prepare the final result vector.
     * We iterate through the map, creating array list 'arr' for each vertical column. This involves gathering node values from the multiset and inserting them into the arraylist here 'arr'.
     * This arraylist 'arr' is then added to the final result list 'list', resulting in a 2D representation of the vertical order traversal of the binary tree.
     *
     * Algorithm:
     * 1: Create an empty map to store the nodes based on their vertical and horizontal levels. The key of the map ‘x’ represents the vertical column, and the nested map uses ‘y’ as the key for the level.
     * 2. Initialise a ‘multiset’ say 'map' to store node values at a specific vertical and level to ensure unique and sorted order of nodes.
     * 3. Initialise a queue for level order BFS traversal. Each element in the queue should be a Pair of <Node, verticalLine, level>.
     * 4. Enqueue the root node into the queue with its initial vertical and level order values as (0, 0)
     * 5. While the queue is not empty, repeat the following steps:
     *    - Get the size of the queue to determine the number of nodes at the current level.
     *    - For each node at the current level, do the following:
     *      - Dequeue the front node from the queue and retrieve its vertical line and level.
     *      - Insert the current node value into the map at its corresponding vertical and level coordinates.
     *      - If the current node has a left child, add it with (vertical line - 1) and (level + 1).
     *      - If the current node has a right child, add it with (vertical line + 1) and (level + 1).
     *      - This ensures that nodes are categorized based on their vertical and level positions.
     * 6. After processing all nodes, iterate through the map to prepare the final result:
     *    - For each vertical line in the map, retrieve the nested map for levels.
     *    - For each level in the nested levelMap, retrieve the list of node values.
     *      - Sort the list of node values to ensure they are in ascending order.
     *      - add the sorted list to a temporary array list 'arr'
     *    - Add the sorted list to the result list 'list'
     * 7. Finally, return the result list containing lists of node values for each vertical line.
     *
     * Time Complexity: O(n log n) where n is the number of nodes in the tree, due to sorting of node values at each vertical line.
     * Space Complexity: O(n) for storing the queue and the map.
     */
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if(root == null)
            return list;

        // Queue: {node, verticalLine, level}
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0, 0));

        // Map: {verticalLine : {level : [nodes]}}
        TreeMap<Integer, TreeMap<Integer, List<Integer>>> map = new TreeMap<>();

        while(!q.isEmpty())
        {
            int size = q.size();
            for(int i=0; i<size; i++)
            {
                Pair pair = q.poll();
                TreeNode curr = pair.node;
                int verticalLine = pair.verticalLine;
                int level = pair.level;

                // Insert the current node value into the
                // corresponding vertical and level in the map
                map.computeIfAbsent(verticalLine, k -> new TreeMap<>())
                        .computeIfAbsent(level, k -> new ArrayList<>())
                        .add(curr.val);

                if(curr.left != null)
                    q.add(new Pair(curr.left, verticalLine-1, level+1));
                if(curr.right != null)
                    q.add(new Pair(curr.right, verticalLine+1, level+1));
            }
        }

        for(Map.Entry<Integer, TreeMap<Integer, List<Integer>>> entry : map.entrySet())
        {
            TreeMap<Integer, List<Integer>> levelMap = entry.getValue();
            List<Integer> arr = new ArrayList<>();

            // traversing through each level in the vertical line
            for(Map.Entry<Integer, List<Integer>> levelEntry : levelMap.entrySet())
            {
                List<Integer> currNodes = levelEntry.getValue();
                Collections.sort(currNodes);
                arr.addAll(currNodes);
            }
            list.add(arr);
        }
        return list;
    }

    public static TreeNode constructBinaryTree(Integer[] arr) {
        if(arr.length == 0 || arr[0] == null)
            return null;

        TreeNode root = new VerticalOrderTraversal().new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while(!q.isEmpty())
        {
            TreeNode curr = q.poll();
            // Left child
            if(i < arr.length && arr[i] != null)
            {
                curr.left = new VerticalOrderTraversal().new TreeNode(arr[i]);
                q.add(curr.left);
            }
            i++;
            // Right child
            if(i < arr.length && arr[i] != null)
            {
                curr.right = new VerticalOrderTraversal().new TreeNode(arr[i]);
                q.add(curr.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        // Example usage
        Integer[] arr = {3,9,20,null,null,15,7};
        VerticalOrderTraversal traversal = new VerticalOrderTraversal();
        TreeNode root = constructBinaryTree(arr);
        List<List<Integer>> result = traversal.verticalTraversal(root);
        System.out.println(result); // Output: [[9],[3,15],[20],[7]]
    }
}
