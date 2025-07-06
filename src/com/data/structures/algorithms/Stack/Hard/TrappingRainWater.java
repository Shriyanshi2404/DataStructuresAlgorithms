package com.data.structures.algorithms.Stack.Hard;

/**
 * Problem Link: https://leetcode.com/problems/trapping-rain-water/
 *
 * This problem involves calculating the amount of water that can be trapped between elevations represented by an array.
 * The solution uses prefix and suffix arrays to find the maximum heights to the left and right of each element.
 */
public class TrappingRainWater {

    /**
     * This method calculates the maximum height to the left of each element in the array.
     * It fills the prefixMax array with the maximum heights.
     */
    public static void findPrefixMax(int[] arr, int[] prefixMax)
    {
        prefixMax[0] = arr[0];
        for(int i=1; i<arr.length; i++)
        {
            prefixMax[i] = Math.max(prefixMax[i-1], arr[i]);
        }
    }

    /**
     * This method calculates the maximum height to the right of each element in the array.
     * It fills the suffixMax array with the maximum heights.
     */
    public static void findSuffixMax(int[] arr, int[] suffixMax)
    {
        suffixMax[arr.length-1] = arr[arr.length-1];
        for(int i=arr.length-2; i>=0; i--)
        {
            suffixMax[i] = Math.max(suffixMax[i+1], arr[i]);
        }
    }

    /** Brute force
     * Algorithm:
     * - For each index, we will find the maximum elevation to the left and right of the index as leftMax and rightMax.
     * - Then we will calculate the amount of water that can be stored at that index.
     * 1. We will find the maximum height to the left and right of each element using prefix and suffix arrays.
     * 2. For each element, we will calculate the water that can be trapped above it.
     * 3. The water trapped above an element is determined by the minimum of the maximum heights to its left and right minus the height of the element itself.
     * 4. We will sum up the water trapped above all elements to get the total water trapped.
     * NOTE: formula is: waterTrapped = min(leftMax, rightMax) - height[i]
     * Time Complexity: O(n)+O(n)+O(n) = O(3n) ~= O(n)
     * Space Complexity: O(2n) ~= O(n)
     */
    public static int trap(int[] height) {
        int total = 0;
        int n = height.length;
        int[] prefixMax = new int[n];
        int[] suffixMax = new int[n];
        findPrefixMax(height, prefixMax);
        findSuffixMax(height, suffixMax);

        for(int i=0; i<height.length; i++)
        {
            int leftMax = prefixMax[i];
            int rightMax = suffixMax[i];
            if(height[i] < leftMax && height[i] < rightMax)
                total += (Math.min(leftMax, rightMax) - height[i]);
        }
        return total;
    }

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int result = trap(height);
        System.out.println("Total water trapped: " + result); // Output: 6
    }

}
