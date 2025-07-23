package com.data.structures.algorithms.String.Easy;

/** Problem link: https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/description/
 * Given a valid parentheses string s, return the maximum nesting depth of s.
 * A valid parentheses string is either empty (""), a single open parenthesis "(", or a valid parentheses string enclosed in a pair of parentheses.
 * The maximum nesting depth of a valid parentheses string is the maximum number of open parentheses that appear at any point in the string.
 */
public class MaxNestingDepthOfParentheses {

    /** Algorithm:
     * 1. Initialize two counters: leftBraces and rightBraces to track the number of open and closed parentheses.
     * 2. Iterate through each character in the string:
     *    - If the character is '(', increment leftBraces.
     *    - If the character is ')', increment rightBraces.
     * 3. Calculate the current depth as leftBraces - rightBraces
     * 4. Update maxDepth if the current depth is greater than the previous maxDepth.
     * 5. Return maxDepth after processing the entire string.
     * Time Complexity: O(n), where n is the length of the string.
     * Space Complexity: O(1), since we are using a constant amount of space for counters.
     */
    public static int maxDepth(String s) {
        int maxDepth = 0;
        int leftBraces=0, rightBraces=0;
        for(int i=0; i<s.length(); i++)
        {
            char ch = s.charAt(i);
            int currDepth = leftBraces - rightBraces;
            maxDepth = Math.max(maxDepth, currDepth);
            if(ch == '(')
                leftBraces++;

            else if(ch == ')')
                rightBraces++;
        }
        return maxDepth;
    }

    public static void main(String[] args) {
        String s = "(1+(2*3)+((8)/4))+1";
        int result = maxDepth(s);
        System.out.println("Maximum nesting depth: " + result); // Output: 3
    }
}
