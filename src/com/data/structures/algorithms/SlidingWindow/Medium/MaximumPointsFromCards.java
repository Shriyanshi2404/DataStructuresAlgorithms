package com.data.structures.algorithms.SlidingWindow.Medium;

/** Problem Link: https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/ */
/** Idea: Approach is Constant Window Sliding */
public class MaximumPointsFromCards {

    /**
     * Algorithm:
     * 1. We will use a sliding window approach to find the maximum points from cards.
     * 2. Initialize two pointers, l and r, to represent the left and right ends of the window.
     * 3. Calculate the sum of the first k elements, that is the 1st window of size k and store it in 'leftSum'
     * 4. Define the maximum sum variable to store the maximum sum found so far, for now initialize it to the sum of the first k elements.
     * 5. Now, run a while loop until the left pointer reaches 0:
     *    (Idea is to take cards from the end of the row and keep removing from the left)
     *    - subtract the element at the left pointer from the 'leftSum', as it is no longer in the new window, decrement the left pointer.
     *    - Add the next element to the right pointer to the 'rightSum' as it is now included in the new window, decrement the right pointer.
     *    - Update the maximum sum if the current sum (leftSum + rightSum) is greater than the previous maximum.
     * 6. Repeat until we reach the end of the array.
     * 7. Finally, return the maximum sum found.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int maxScore(int[] cardPoints, int k) {
        int maxSum = 0;
        // to calculate sum from beginning
        int leftSum = 0;
        // to calculate sum from end
        int rightSum = 0;

        // Taking first-k cards from array
        for(int i=0; i<=k-1; i++)
            leftSum += cardPoints[i];

        // Now taking card from end of the row
        int l = k-1, r = cardPoints.length-1;
        maxSum = leftSum;
        // while we have cards to take from end of the row
        while(l >= 0)
        {
            // removing the card point from left side to pick from right of row
            leftSum = leftSum - cardPoints[l];
            l--;
            // adding the card point from right side
            rightSum = rightSum + cardPoints[r];
            r--;

            int sum = leftSum + rightSum;
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] cardPoints = {1, 2, 3, 4, 5, 6, 1};
        int k = 3;
        System.out.println("Maximum points from cards: " + maxScore(cardPoints, k)); // Output: 12
    }

}
