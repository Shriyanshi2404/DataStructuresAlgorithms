package com.data.structures.algorithms.Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
The solution set must not contain duplicate subsets. Return the solution in any order.
Practice link: https://leetcode.com/problems/subsets-ii/description/
*/
public class SubsetII {

    /*
     Approach - Recursion using
     - Sorts the array before processing (helps with skipping over duplicate elements during recursion).
     - Generates all possible subsets using recursion (classic include/exclude approach).
     - Avoids duplicate subsets by checking:
        - If current list "temp" is present in final list "list" or not
        - if not, then add "temp" list into final list "list"
        - And return
     Time Complexity: O((2^n)^2 * k) = O(4^n * k) -> Because on average, the list grows to length 2^n, so each call to contains() = O(k) per element × up to 2^n elements
     Space Complexity: O(n) -> due to recursion stack (function calls)
    */
    public static void findSubsetsWithoutDuplicates(int index, int[] nums, List<List<Integer>> list, List<Integer> temp)
    {
        if(index >= nums.length)
        {
            /*
            This leads to O(N * M) complexity for checking duplicates, where:
             - N is the number of subsets generated (up to 2^n)
             - M is the average size of subsets
           */
            if(!list.contains(temp))
                list.add(new ArrayList<>(temp));
            return;
        }
        // take the element
        temp.add(nums[index]);
        findSubsetsWithoutDuplicates(index+1, nums, list, temp);

        // not take the element
        temp.remove(temp.size()-1);
        findSubsetsWithoutDuplicates(index+1, nums, list, temp);
    }

    /*
     Approach - Recursion using
     - Sorts the array before processing (helps with skipping over duplicate elements during recursion).
     - Generates all possible subsets using recursion (classic include/exclude approach).
     - Skip recursive calls that would lead to duplicate subsets.
        - Run a while loop until index < array length and current index element is equal of next index element
        - And during iteration keep on increasing index, this will skip the calls to redundant index which has same element
     Time Complexity: O(2^n * k) -> Because 2^n: number of subsets and k: average size of a subset (to build and copy it into result)
     Space Complexity: O(n) -> due to recursion stack (function calls)
    */
    public void findSubsetsWithoutDuplicatesOptimalSolution(int index, int[] nums, List<List<Integer>> list, List<Integer> temp)
    {
        if(index >= nums.length)
        {
            list.add(new ArrayList<>(temp));
            return;
        }
        // take the element
        temp.add(nums[index]);
        findSubsetsWithoutDuplicates(index+1, nums, list, temp);

        // not take the element
        temp.remove(temp.size()-1);

        // Skip all duplicates of the current number
        while(index+1 < nums.length && nums[index] == nums[index+1])
            index++;

        // Exclude the current element (and all its duplicates)
        findSubsetsWithoutDuplicates(index+1, nums, list, temp);
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        // sorting to skip over duplicate elements during recursion
        Arrays.sort(nums);

        List<List<Integer>> list = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        findSubsetsWithoutDuplicates(0, nums, list, temp);
        return list;
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i=0; i<n; i++) {
            nums[i] = sc.nextInt();
        }

        List<List<Integer>> result = subsetsWithDup(nums);
        System.out.println(result);
    }
}
