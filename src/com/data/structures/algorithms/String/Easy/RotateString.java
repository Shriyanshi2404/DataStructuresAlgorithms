package com.data.structures.algorithms.String.Easy;

/** Problem Link: https://leetcode.com/problems/rotate-string/ **/
public class RotateString {

    /**
     * Algorithm: Brute Force Approach
     * 1. First, we check if the lengths of the two strings are equal. If not, we return false.
     * 2. We iterate through the original string
     *    - We then create a temporary string by rotating the original string by one character at a time.
     *    - For each rotation, we concatenate the substring from the end of the string to the beginning.
     *    - This gives us all possible rotations of the string.
     *    - For each rotation, we check if it matches the goal string, if yes then return true.
     * 4. Otherwise, we return false after checking all rotations.
     * Time Complexity: O(n^2) because we are generating all rotations of the string and checking each one.
     * Space Complexity: O(n)
     */
    public static boolean rotateString(String s, String goal) {
        if(s.length() != goal.length())
            return false;

        String temp = "";
        int n = s.length();
        for(int i=0; i<n; i++)
        {
            temp = s.substring(i+1, n);
            temp += s.substring(0,i+1);
            if(temp.equals(goal))
                return true;
        }
        return false;
    }

    /**
     * Algorithm: Optimal Approach
     * 1. First, we check if the lengths of the two strings are equal. If not, we return false.
     * 2. We concatenate the original string with itself to create a new string that contains all possible rotations.
     *    - This is because any rotation of the string will appear as a substring in this concatenated string.
     * 3. We then check if the goal string is a substring of this concatenated string.
     * 4. If it is, we return true; otherwise, we return false.
     * Time Complexity: O(n) where n is the length of the string.
     * Space Complexity: O(n) for the concatenated string.
     */
    public static boolean rotateStringOptimal(String s, String goal) {
        if(s.length() != goal.length())
            return false;

        String combinedString = s+s;
        if(combinedString.contains(goal))
            return true;
        return false;
    }

    public static void main(String[] args) {
        String s1 = "abcde";
        String goal1 = "cdeab";
        System.out.println("Can '" + s1 + "' be rotated to '" + goal1 + "'? " + rotateString(s1, goal1)); // Output: true

        String s2 = "abcde";
        String goal2 = "abced";
        System.out.println("Can '" + s2 + "' be rotated to '" + goal2 + "'? " + rotateStringOptimal(s2, goal2)); // Output: false
    }
}
