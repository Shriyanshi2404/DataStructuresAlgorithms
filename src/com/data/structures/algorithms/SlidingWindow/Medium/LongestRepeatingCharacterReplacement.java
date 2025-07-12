package com.data.structures.algorithms.SlidingWindow.Medium;

/** Problem Link: https://leetcode.com/problems/longest-repeating-character-replacement/ */
public class LongestRepeatingCharacterReplacement {

    /**
     * Algorithm: Sliding Window
     * 1. We will use a sliding window approach to find the length of the longest substring that can be formed by replacing at most k characters.
     * 2. We will maintain 'maxFreq' a count of the most frequent character in the current window and maxLength to store the maximum length of the valid window.
     * 3. Assign a left and right pointer to 0 and iterate through the string with the right pointer.
     * 4. Create a empty hashmap to store the frequency of characters in the current window.
     * 5. Start iterating through the string with the right pointer:
     *    - For each character at the right pointer, increment its count in the hashmap.
     *    - Update the maxFreq to be the maximum frequency of any character in the current window.
     *    - If the length of the current window minus the count of the most frequent character is less than or equal to k, we can replace characters.
     *      - keep updating the maxLength with the current window size (right - left + 1).
     *    - If not, we will shrink the window from the left until it is valid again.
     *      - make sure to decrement the count of the character at the left pointer in the hashmap and increment the left pointer
     *      - continue this until the window is valid again.
     * 6. Finally, we will return the maximum length of the valid window found.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int maxCount = 0;
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            maxCount = Math.max(maxCount, ++count[s.charAt(right) - 'A']);

            while (right - left + 1 - maxCount > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String s = "AABABBA";
        int k = 1;
        System.out.println("Length of longest substring with at most " + k + " replacements: " + characterReplacement(s, k)); // Output: 4
    }
}
