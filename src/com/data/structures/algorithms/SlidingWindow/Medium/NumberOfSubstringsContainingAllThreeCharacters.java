package com.data.structures.algorithms.SlidingWindow.Medium;

/** Problem Link: https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/description/ */
public class NumberOfSubstringsContainingAllThreeCharacters {

    /**
     * Algorithm:
     * 1. We will use a sliding window approach to find the number of substrings containing all three characters 'a', 'b', and 'c'.
     * 2. Initialize two pointers, l and r, to represent the left and right ends of the window.
     * 3. Use a StringBuilder to keep track of the current substring.
     * 4. As we iterate through the string with the right pointer, we will append characters to the StringBuilder.
     * 5. If the substring contains all three characters, we will count all valid substrings that can be formed from this point.
     * 6. We will then shrink the window from the left until it no longer contains all three characters.
     * 7. Finally, return the count of such substrings.
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int numberOfSubstrings(String s) {
        int count = 0;
        int l = 0, r = 0;
        int n = s.length();
        StringBuilder str = new StringBuilder();
        while(r < n)
        {
            char ch = s.charAt(r);
            str.append(ch);

            /** * If the substring contains all three characters 'a', 'b', and 'c',
             * we will count all valid substrings that can be formed from this point.
             */
            while(str.toString().indexOf('a') != -1 &&
                    str.toString().indexOf('b') != -1 &&
                    str.toString().indexOf('c') != -1) {

                count += (n-r);
                str.deleteCharAt(0);
                l++;
            }
            r++;
        }
        return count;
    }

    public static void main(String[] args) {
        String s = "abcabc";
        System.out.println("Number of substrings containing all three characters: " + numberOfSubstrings(s)); // Output: 10
    }
}
