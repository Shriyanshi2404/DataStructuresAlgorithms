package com.data.structures.algorithms.Recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
Practice link: https://leetcode.com/problems/generate-parentheses/description/
*/
public class GenerateParentheses {

    /*
    Idea:
    - Length of each combination of parentheses will be n+n (no of opening parentheses and no of closing parentheses)
    - All opening parentheses will be followed by closing parentheses
    - All combination of parentheses will be balances

    Approach:
    - Keep the initial count of opening and closing parenthesis as 0 and take initial empty string (say temp)
    - Now call function (generateAllParenthesis) by passing below parameters
        - openCount as 0
        - closeCount as 0
        - Final list "list" to store all combinations
        - temporary list "temp" to create each combination
    - Base case: whenever count of opening and closing parenthesis is equal to N then add temporary string into final list
    - Option 1: check if openCount < n -- yes then add opening parenthesis into temporary string and increment the opening count
    - Option 2: Check if closeCount < openCount -- yes then add closing parenthesis into temporary string and increment the closing count

    Time Complexity : O((2^(2*n))*n), where n is the number of pairs of parentheses. Reason: For each index 'i', we're making two recursive calls.This costs O(2^(2*n)).
    Space Complexity : O(n) -> recursion stack
    */
    public static void generateAllParenthesis(List<String> list, String temp, int openCount, int closeCount, int n)
    {
        if(openCount == n && closeCount == n)
        {
            list.add(temp);
            return;
        }

        // take the open parenthesis
        if(openCount < n)
            generateAllParenthesis(list, temp + "(", openCount+1, closeCount, n);

        /*
        take closing parenthesis only
        if you have less closing parenthesis in temp string
        when compared to count of opening parenthesis in temp string
        */
        if(closeCount < openCount)
            generateAllParenthesis(list, temp + ")", openCount, closeCount+1, n);

    }
    public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        String temp = "";
        generateAllParenthesis(list, temp, 0, 0, n);
        return list;
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<String> result = generateParenthesis(n);
        System.out.println(result);
    }
}
