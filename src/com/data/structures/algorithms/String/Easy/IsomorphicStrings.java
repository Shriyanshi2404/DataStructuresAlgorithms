package com.data.structures.algorithms.String.Easy;

import java.util.Arrays;

/** Problem Link: https://leetcode.com/problems/isomorphic-strings/ **/
public class IsomorphicStrings {

    /**
     * Algorithm:
     * 1. We will use two arrays to map characters from string s to t and vice versa.
     *    - Two integer arrays of size 256 (ASCII size) are declared.
     *      count1[ch1] will map characters in s to characters in t.
     *      count2[ch2] will map characters in t to characters in s.
     *    - Both arrays are initialized to -1 to indicate that no mapping has been established yet.
     * 2. We will iterate through any of the strings (s or t) using a single loop.
     * 3. For each character pair (s[i], t[i]), we will check if they have been mapped before.
     * 4. If both characters have not been seen before (count1[ch1] == 0 and count2[ch2] == 0), store the mapping:
     *    - Map ch1 → ch2 in count1.
     *    - Map ch2 → ch1 in count2.
     * 5. If they have been seen before, check that the mapping is consistent:
     *    - If ch1 doesn't map to ch2 or ch2 doesn't map to ch1, then return false.
     * 6. If we complete the iteration without mismatches, we return true.
     * Time Complexity: O(n)
     * Space Complexity: O(1) - since the size of the character set is fixed (256 ASCII characters)
     */
    public boolean isIsomorphic(String s, String t) {
        int[] count1 = new int[256];
        int[] count2 = new int[256];
        Arrays.fill(count1, -1);
        Arrays.fill(count2, -1);
        for(int i=0; i<s.length(); i++)
        {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);

            if(count1[ch1] == -1 && count2[ch2] == -1)
            {
                // map characters
                // li
                count1[ch1] = ch2;
                count2[ch2] = ch1;
            }
            else if(count1[ch1] != ch2 || count2[ch2] != ch1)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        IsomorphicStrings isomorphicStrings = new IsomorphicStrings();
        String s1 = "egg";
        String t1 = "add";
        System.out.println("Are '" + s1 + "' and '" + t1 + "' isomorphic? " + isomorphicStrings.isIsomorphic(s1, t1)); // Output: true

        String s2 = "foo";
        String t2 = "bar";
        System.out.println("Are '" + s2 + "' and '" + t2 + "' isomorphic? " + isomorphicStrings.isIsomorphic(s2, t2)); // Output: false
    }
}
