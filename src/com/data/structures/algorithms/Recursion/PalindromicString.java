package com.data.structures.algorithms.Recursion;

import java.util.Scanner;

public class PalindromicString {

    /*
     Iterative method
     Idea: Compare each letter from start and end using two pointers
     If at any point, characters does not match, then return false, else keep on iterating
     At last, return true;
     Time Complexity: O(n)
     Space Complexity: O(1)
    */
    public static boolean isPalindromeIterative(String str)
    {
        int low = 0;
        int high = str.length()-1;
        while(low < high)
        {
            if(str.charAt(low) != str.charAt(high))
                return false;

            low++;
            high--;
        }
        return true;
    }

    /*
     Recursion Optimal Solution
     Base case: Once the current index 'i' reaches or exceeds the midpoint (n/2), all elements have been swapped. So, recursion stops.
     Else, compare the character at index 'i' with the character at the symmetric position from the end: 'n - i - 1'
     Then, recursively call the function with (i+1, n, str).
     Time Complexity: O(n/2) → O(n)
     Space Complexity: O(n/2) → due to recursion stack (function calls)
   */
    public static boolean isPalindrome(int i, int n, String str)
    {
        if(i >= (n/1))
            return true;

        if(str.charAt(i) != str.charAt(n-i-1))
            return false;

        return isPalindrome(i+1, n, str);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        System.out.println("Given string " + str + " is Palindrome : " + isPalindrome(0, str.length(), str));
    }
}
