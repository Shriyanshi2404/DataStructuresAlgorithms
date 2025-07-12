package com.data.structures.algorithms.String.Medium;

/** Problem Link: https://leetcode.com/problems/valid-anagram/ **/
public class ValidAnagram {

    /**
     * Algorithm:
     * 1. If the lengths of the two strings are not equal, they cannot be anagrams.
     * 2. We will use an array say 'count[]' to count the occurrences of each character in both strings.
     * 3. We will iterate through both strings simultaneously.
     *    - For each character in the first string, we will increment its count.
     *    - For each character in the second string, we will decrement its count.
     * 5. Finally, we will check if all counts are zero, which means the strings are anagrams.
     * Time Complexity: O(n)
     * Space Complexity: O(1) (since the size of the count array is fixed at 256)
     */
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length())
            return false;

        int[] count = new int[256];
        for(int i=0; i<s.length(); i++)
        {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);
            count[ch1]++;
            count[ch2]--;
        }

        for(int i=0; i<256; i++)
        {
            if(count[i] != 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        ValidAnagram validAnagram = new ValidAnagram();
        String s1 = "anagram";
        String t1 = "nagaram";
        System.out.println("Are '" + s1 + "' and '" + t1 + "' anagrams? " + validAnagram.isAnagram(s1, t1)); // Output: true

        String s2 = "rat";
        String t2 = "car";
        System.out.println("Are '" + s2 + "' and '" + t2 + "' anagrams? " + validAnagram.isAnagram(s2, t2)); // Output: false
    }
}
