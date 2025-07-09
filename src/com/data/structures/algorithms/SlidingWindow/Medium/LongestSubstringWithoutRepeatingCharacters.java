package com.data.structures.algorithms.SlidingWindow.Medium;

import java.util.HashSet;
import java.util.Set;

/** Problem Link: https://leetcode.com/problems/longest-substring-without-repeating-characters/ */
public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * Algorithm:
     * 1. We will use a sliding window approach with two pointers, left (l) and right (r).
     * 2. We will maintain a set to store the characters in the current substring.
     * 3. As we iterate through the string with the right pointer, we will check if the character is already in the set.
     *    - If it is not, we add it to the set and update the maximum length.
     *    - If it is, we will move the left pointer until we remove the duplicate character from the set and increment the left pointer.
     * 4. Finally, we return the maximum length of the substring without repeating characters.
     * Time Complexity: O(n)
     * Space Complexity: O(min(n, m)), where n is the length of the string and m is the size of the character set
     */
    public static int lengthOfLongestSubstring(String s) {
        char[] arr = s.toCharArray();
        int l=0;
        int maxLength = 0;
        Set<Character> set = new HashSet<>();

        for(int r=0; r<arr.length; r++)
        {
            char ch = arr[r];
            if(!set.contains(ch))
            {
                set.add(ch);
                maxLength = Math.max(maxLength, r-l+1);
            }
            else
            {
                // If the character is already in the set, then remove it from the left side
                while(arr[l] != ch)
                {
                    // If the character is already in the set,
                    // shrink the window from the left
                    set.remove(s.charAt(l));
                    l++;
                }

                /**
                 * Why are we doing this line again below?
                 * string = "pww" r=2, l=0 currentString = "pw"
                 * so we will remove 'p' from the set and increment 'l'
                 * but we now have 'w' in the set, so we will add 'w' to the set again
                 * to maintain the current substring without repeating characters.
                 */
                // removing the character which is the same as current character
                set.remove(s.charAt(l));
                l++;
                set.add(ch);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println("Length of the longest substring without repeating characters: " + lengthOfLongestSubstring(s));
    }
}
