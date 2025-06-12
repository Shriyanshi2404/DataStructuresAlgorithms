package com.data.structures.algorithms.Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
Each number in candidates may only be used once in the combination.
Note: The solution set must not contain duplicate combinations.
Practice link: https://leetcode.com/problems/combination-sum-ii/description/
*/
public class CombinationSumII {

    /*
    Intuition: Whenever the problem is related to picking up elements from an array to form a combination, start thinking about the “pick and non-pick” approach.
    - Before starting the recursive call make sure to sort the elements because the ans should contain the combinations in sorted order and should not be repeated.
    - Initially, We start with the index 0, At index 0 we have n - 1 way to pick the first element of our subsequence.
    - Also initialise the target as given and the data structure(list) will be empty and take temporary list too.
    - Run a for loop to Check if the current index value can be added to our list.
      - If yes add it to the temp list and move the index by 1.
      - while moving the index skip the consecutive repeated elements because they will form duplicate sequences.
      - Reduce the target by arr[i],call the recursive call for f(idx + 1,target - 1,ds,ans) after the call make sure to pop the element from the temp list.
      - if(arr[i] > target) then terminate the recursive call because there is no use to check as the array is sorted in the next recursive call the index will be moving by 1 all the elements to its right will be in increasing order.
    - Base Condition: Whenever the target value is zero add the ds to the ans return.

    Time Complexity: O(2ⁿ*K) (Exponential) -> Because each call can generate multiple calls unless it hits a base case, k is the average length
    Space Complexity: O(n*K) -> due to recursion stack (function calls), k is the average length
    */
    public static void findCombinationSum(int index, int[] arr, int target, List<List<Integer>> list, List<Integer> temp)
    {

        if(target == 0)
        {
            list.add(new ArrayList<>(temp));
            return;
        }

        for(int i=index; i<arr.length; i++)
        {
            // to skip duplicates elements
            if(i>index && arr[i] == arr[i-1])
                continue;

            if(arr[i] > target)
                break;

            // case 1: pick the element
            temp.add(arr[i]);
            findCombinationSum(i+1, arr, target-arr[i], list, temp);

            // case 2: Not to pick the element
            temp.remove(temp.size()-1);
        }
    }
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        findCombinationSum(0, candidates, target, list, temp);
        return list;
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] candidates = new int[n];
        for(int i=0; i<n; i++)
        {
            candidates[i] = sc.nextInt();
        }

        int target = sc.nextInt();
        List<List<Integer>> list = combinationSum2(candidates, target);
        if(list.isEmpty())
            System.out.println("[]");

        else {
            for (List<Integer> temp : list)
                System.out.println(temp);
        }
    }
}
