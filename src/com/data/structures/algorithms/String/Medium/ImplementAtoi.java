package com.data.structures.algorithms.String.Medium;

/** Problem link: https://leetcode.com/problems/string-to-integer-atoi/description/
 * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).
 * The algorithm for myAtoi(string s) is as follows:
 * 1. Read in and ignore any leading whitespace.
 * 2. Check if the next character (if not already at the end of the string) is '-' or '+'.
 *    - If so, read this character in and remember it as a sign indicator.
 *    - If neither, assume the sign is positive.
 * 3. Read in next the characters until the next non-digit character or the end of the input is reached.
 *    - The rest of your string is assumed to be a valid integer.
 * 4. Convert these digits into an integer (i.e., "123" -> 123, "0032" -> 32).
 * 5. If no digits were read, then the integer is 0.
 * 6. The integer is capped at 32 bits signed integer range: [−231, 231 − 1]. If the integer is out of this range, return clamped value.
 */
public class ImplementAtoi {

    /** Algorithm to convert a string to an integer (atoi)
     * 1. Trim leading and trailing whitespace from the input string.
     * 2. Check if the string is empty after trimming; if so, return 0.
     * 3. Initialize a boolean flag to track if the number is negative.
     *    - If the first character is a '-' or '+', set the flag and start parsing from the next character. i.e. index=1;
     *    - else index=0
     * 4. Iterate through the string:
     *    - Append digits to a StringBuilder until a non-digit character is encountered.
     *    - Keep incrementing the index.
     * 5. If no digits were found, return 0 that means the string does not represent a valid integer.
     * 6. If the flag is set, prepend the sign to the StringBuilder.
     * 7. Parse the StringBuilder to an integer using Integer.parseInt().
     * 8. Handle potential overflow by catching NumberFormatException:
     *    - If the parsed value exceeds Integer.MAX_VALUE, return Integer.MAX_VALUE.
     *    - If the parsed value is less than Integer.MIN_VALUE, return Integer.MIN_VALUE.
     *    - If the sign is negative, return Integer.MIN_VALUE.
     * Time Complexity: O(n), where n is the length of the string.
     * Space Complexity: O(n), where n is the number of digits in the string.
     */
    public static int myAtoi(String s) {
        s = s.trim();
        if(s.isEmpty())
            return 0;

        boolean flag = false;
        int i = 0;
        if(s.charAt(0) == '-' || s.charAt(0) == '+')
        {
            flag = true;
            i=1;
        }
        StringBuilder str = new StringBuilder();
        while(i<s.length())
        {
            char ch = s.charAt(i);
            if(!Character.isDigit(ch))
                break;
            str.append(ch);
            i++;
        }
        if(str.length() == 0)
            return 0;
        if(flag == true)
        {
            str.insert(0, s.charAt(0));
        }
        // Safely parse with overflow handling
        try {
            return Integer.parseInt(str.toString());
        } catch (NumberFormatException e) {
            return s.charAt(0) == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
    }

    public static void main(String[] args) {
        String s = "   -42";
        int result = myAtoi(s);
        System.out.println("Converted integer: " + result); // Output: -42
        s = "-91283472332";
        result = myAtoi(s);
        System.out.println("Converted integer: " + result); // Output: -2147483648
        s = "4193 with words";
        result = myAtoi(s);
        System.out.println("Converted integer: " + result); // Output: 4193
        s = "1337c0d3";
        result = myAtoi(s);
        System.out.println("Converted integer: " + result); // Output: 1337
        s = "0-1";
        result = myAtoi(s);
        System.out.println("Converted integer: " + result); // Output: 0
    }
}
