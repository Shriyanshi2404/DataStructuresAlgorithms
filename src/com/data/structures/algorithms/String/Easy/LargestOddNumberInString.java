package com.data.structures.algorithms.String.Easy;

/** Problem Link: https://leetcode.com/problems/largest-odd-number-in-string/ **/
public class LargestOddNumberInString {

    /**
     * Algorithm:
     * Intuition: To find the largest odd number as a prefix, we only need to find the rightmost odd digit in the string.
     *            Why? Because any prefix ending with an odd digit will itself be an odd number.
     *            We can safely ignore everything after the last odd digit and return the prefix.
     * 1. We will iterate through the string from the end to the beginning.
     * 2. For each character, we will check if it is an odd digit.
     * 3. If we find an odd digit, we will return the substring from the start to that index (inclusive).
     * 4. If no odd digit is found, we will return an empty string.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static String largestOddNumber(String num) {
        int endIndex = -1;
        for(int i=num.length()-1; i>=0 ;i--)
        {
            int ele = Character.getNumericValue(num.charAt(i));
            if(ele % 2 != 0)
            {
                endIndex = i;
                break;
            }
        }
        if(endIndex == -1)
            return "";
        return num.substring(0, endIndex+1);
    }

    public static void main(String[] args) {
        String num1 = "52";
        System.out.println("Largest odd number in '" + num1 + "': " + largestOddNumber(num1)); // Output: "5"
        String num2 = "4206";
        System.out.println("Largest odd number in '" + num2 + "': " + largestOddNumber(num2)); // Output: ""
        String num3 = "123456789";
        System.out.println("Largest odd number in '" + num3 + "': " + largestOddNumber(num3)); // Output: "123456789"
    }
}
