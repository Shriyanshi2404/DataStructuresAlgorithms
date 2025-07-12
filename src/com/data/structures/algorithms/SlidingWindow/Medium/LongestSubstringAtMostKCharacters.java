package com.data.structures.algorithms.SlidingWindow.Medium;

import java.util.HashMap;

/**
 * Problem Link: https://www.naukri.com/code360/problems/distinct-characters_2221410
 * Similar to: Longest Substring with At Most K Distinct Characters
 */
public class LongestSubstringAtMostKCharacters {

    /**
     * Algorithm:
     * 1. We will use a sliding window approach with two pointers, left (l) and right (r).
     * 2. We will maintain a map to store the frequency of each character in the current substring.
     * 3. As we iterate through the string with the right pointer, we will add the current character to the map.
     * 4. If the size of the map exceeds k, we will shrink the window from the left until it is less than or equal to k.
     * 5. If the size of the map is less than or equal to k, we will update the maximum length of the substring.
     * 6. Finally, we return the maximum length of the substring with at most k distinct characters.
     * Time Complexity: O(n)
     * Space Complexity: O(k)
     */
    public static int kDistinctChars(int k, String str) {
        if(k == 1)
            return 1;

        int maxLength = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int l=0, r=0;
        while(r < str.length())
        {
            char ch = str.charAt(r);
            while(map.size() > k)
            {
                char leftChar = str.charAt(l);
                map.put(leftChar, map.get(leftChar) - 1);
                if(map.get(leftChar) == 0)
                    map.remove(leftChar);
                l++;
            }

            map.put(ch, map.getOrDefault(ch, 0) + 1);
            if(map.size() <= k)
                maxLength = Math.max(maxLength, r-l+1);
            r++;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String str = "abcabcabc";
        int k = 2;
        System.out.println("Length of longest substring with at most " + k + " distinct characters: " + kDistinctChars(k, str)); // Output: 5
    }
}
