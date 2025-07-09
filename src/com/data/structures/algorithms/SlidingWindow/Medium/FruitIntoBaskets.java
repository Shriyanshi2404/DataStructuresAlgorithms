package com.data.structures.algorithms.SlidingWindow.Medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/** Problem Link: https://leetcode.com/problems/fruit-into-baskets/ */
/** Similar like: No of subarrays with at most 2 distinct elements */
public class FruitIntoBaskets {

    /**
     * Algorithm: Brute Force
     * 1. Define maxLength to keep track of the maximum length of the window.
     * 2. Generate all possible subarrays of the fruits array.
     *   - Run a loop with index i from 0 to n-1 (where n is the length of the fruits array).
     *   - Define a set to keep track of the types of fruits in the current subarray.
     *      - Run another loop with index j from i to n-1.
     *      - Add the fruit type at index j to the set.
     *      - Since we can keep at most 2 types of fruits in the baskets, so we will check the size of the set.
     *        - If the size of the set is less than or equal to 2, we will update maxLength with the maximum of maxLength and (j - i + 1).
     *        - If the size of the set exceeds 2, we will break out of the inner loop as we cannot have more than 2 types of fruits in the baskets.
     * 3. Finally, return maxLength as a result i.e. maximum number of fruits in two baskets.
     * Time Complexity: O(N^2)
     * Space Complexity: O(N)
     */
    public static int totalFruit(int[] fruits) {
        int maxLength = 0;
        int n = fruits.length;
        for(int i=0; i<n; i++)
        {
            Set<Integer> set = new HashSet<>();
            for(int j=i; j<n; j++)
            {
                set.add(fruits[j]);
                if(set.size() <= 2)
                    maxLength = Math.max(maxLength, j-i+1);
                else
                    break;
            }
        }
        return maxLength;
    }


    /**
     * Function to count the maximum number of fruits in two baskets.
     * 1. We will use a sliding window approach with two pointers, l and r.
     * 2. We will maintain a HashMap to keep track of the types of fruits in the current window with their frequence.
     * 3. As we iterate through the array with the right pointer, we will add the fruit type to the HashMap and increment its frequency.
     * 4. If the size of the HashMap exceeds 2, we will shrink the window from the left until it contains at most 2 types of fruits.
     *    - Every time we need to remove a fruit type from the HashMap present at a left pointer, by decrementing its frequency.
     *    - If the frequency of a fruit type becomes 0, we will remove it from the HashMap.
     *    - And increment the left pointer to shrink the window.
     * 5. If the size of the HashMap is less than or equal to 2, we will update the maximum length of the window.
     * 6. Finally, we will return the maximum length of the window that contains at most 2 types of fruits.
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int totalFruits(int[] fruits) {
        int maxLength = 0;
        int n = fruits.length;
        int l = 0, r = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        while(r < n)
        {
            int fruitType = fruits[r];
            map.put(fruitType, map.getOrDefault(fruitType, 0) + 1);

            // If the size of the map exceeds 2, we need to shrink the window from the left
            while(map.size() > 2)
            {
                map.put(fruits[l], map.get(fruits[l]) - 1);
                if(map.get(fruits[l]) == 0)
                    map.remove(fruits[l]);
                l++;
            }
            if(map.size() <= 2)
                maxLength = Math.max(maxLength, r-l+1);
            r++;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[] fruits = {1, 2, 1};
        System.out.println("Maximum number of fruits in two baskets: " + totalFruits(fruits)); // Output: 3
    }
}
