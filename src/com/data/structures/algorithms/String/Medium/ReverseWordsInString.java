package com.data.structures.algorithms.String.Medium;

/** Problem Link: https://leetcode.com/problems/reverse-words-in-a-string/ **/
public class ReverseWordsInString {

    /**
     * Algorithm:
     * 1. We will first trim the input string to remove leading and trailing spaces.
     * 2. Then, we will split the string into words using space as a delimiter.
     * 3. We will reverse the order of the words and join them with a single space.
     * 4. Finally, we will return the reversed string.
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static String reverseWords(String s) {
        // removing extra spaces
        s = s.trim().replaceAll("\\s+", " ");
        // converting string to array of words
        String[] words = s.split(" ");
        String str = "";
        for(int i=words.length-1; i>=0; i--)
        {
            str = str + words[i];
            if(i != 0)
                str = str + " "; // adding space between words
        }
        return str;
    }

    public static void main(String[] args) {
        String s1 = "  Hello   World  ";
        System.out.println("Reversed words: '" + reverseWords(s1) + "'"); // Output: "World Hello"
        String s2 = " a good   example ";
        System.out.println("Reversed words: '" + reverseWords(s2) + "'"); // Output: "example good a"
    }
}
