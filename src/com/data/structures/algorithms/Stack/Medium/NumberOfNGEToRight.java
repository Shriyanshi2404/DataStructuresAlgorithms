package com.data.structures.algorithms.Stack.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

/** Problem link: https://www.geeksforgeeks.org/number-nges-right/ */
public class NumberOfNGEToRight {
    
    /**
     * Algorithm: Brute Force Approach
     * 1. Take a result array of size queries
     * 2. Run a loop for queries
     * 3. For each query, run a loop from index+1 to end of array
     * 4. If the current element is greater than the element at index, increment the count
     * 5. Return the result array
     * Time Complexity: O(queries * N)
     * Space Complexity: O(1)
     */
    public static int[] count_NGEs(int N, int arr[], int queries, int indices[]) {
        
        int[] result = new int[queries];
        for(int k=0; k<queries; k++)
        {
            int index = indices[k];
            int count = 0;
            for(int j=index+1; j<arr.length; j++)
            {
                if(arr[j] > arr[index])
                    count++;
            }
            result[k] = count;
        }
        return result;
    }

    /**
     * Algorithm: Optimal Approach (Using Stack)
     * Take array list to store the count of greater elements to the right of each element.
     * 1. Initialize Two Stacks
     * - main stack (st) → Maintains a monotonic decreasing stack (stores elements in decreasing order).
     * - temp stack (temp) → Temporary stack used for rearranging elements.
     * 2. Iterate Backwards from n-1 to 0. 
     * - This ensures that we process elements from right to left.
     * - We use a stack-based approach to track greater elements to the right.
     * 3. Process Each Element (arr[i]) 
     * - Remove Smaller Elements: Pop all elements from main stack that are less than or equal to arr[i] and push them into temp stack.
     * - This ensures only greater elements remain in main stack.
     * 4.tore Count of Greater Elements: 
     * - The remaining elements in main stack are greater than arr[i], so st.size() gives the NGE count for index i.
     * 5. Restore asc Stack:
     * - Move all elements from temp stack back to main stack to maintain order.
     * Answer Queries Efficiently
     * - The array list[] is precomputed, so for each query, we simply return list.get(indices[i]) in O(1) time and stored in result array.
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public static int[] count_NGEsOptimalSolution(int N, int arr[], int queries, int indices[]) {
        int[] result = new int[queries];
        ArrayList<Integer> list = new ArrayList<>();
        Stack<Integer> st = new Stack<>();
        Stack<Integer> temp = new Stack<>();
  
        for(int i=N-1; i>=0; i--)
        {
            int ele = arr[i];
            
            while(!st.isEmpty() && st.peek() <= ele)
            {
                temp.push(st.pop());
            }
            // storing the count of greater elements into list
            list.add(st.size());
            temp.push(ele);
            
            while(!temp.isEmpty())
            {
                st.push(temp.pop());
            }
        }
        
        Collections.reverse(list);
        
        for(int i=0; i<queries; i++)
        {
            result[i] = list.get(indices[i]);
        }
        return result;
    }
    
    public static void main(String[] args) {
        int[] arr = {3, 4, 2, 7, 5, 8, 10, 6};
        int queries = 2;
        int[] indices = {0, 5};
        int[] result = count_NGEsOptimalSolution(arr.length, arr, queries, indices);
        System.out.println(Arrays.toString(result));
    }
}
