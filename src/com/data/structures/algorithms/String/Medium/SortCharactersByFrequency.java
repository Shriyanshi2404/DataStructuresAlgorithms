package com.data.structures.algorithms.String.Medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/** Problem link: https://leetcode.com/problems/sort-characters-by-frequency/description/
 * Given a string s, sort it in decreasing order based on the frequency of the characters.
 * The frequency of a character is the number of times it appears in the string.
 * Return the sorted string. If there are multiple characters with the same frequency, sort them in alphabetical order.
 */
public class SortCharactersByFrequency {

    /** Algorithm:
     * 1. Create a map to store the frequency of each character.
     * 2. Iterate through the string and populate the map with character frequencies.
     * 3. Sort the map entries by frequency in descending order.
     * 4. Build the result string by appending characters based on their frequency.
     * 5. Return the result string.
     * Time Complexity: O(n log n), where n is the length of the string.
     * Space Complexity: O(n), where n is the number of unique characters in the string.
     */
    public static String frequencySort(String s) {
        Map<Character, Integer> map = new TreeMap<>();
        for(int i=0; i<s.length(); i++)
        {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // Sort the map entries by frequency
        List<Map.Entry<Character, Integer>> sortedMap = new ArrayList<>(map.entrySet());
        sortedMap.sort((a, b) -> b.getValue() - a.getValue());

        StringBuilder str = new StringBuilder();
        for(Map.Entry<Character, Integer> entry : sortedMap)
        {
            for (int i=0; i<entry.getValue(); i++)
                str.append(entry.getKey());
        }
        return str.toString();
    }

    public static void main(String[] args) {
        String result = frequencySort("tree");
        System.out.println(result); // Output: "eetr" or "eert"
    }
}
