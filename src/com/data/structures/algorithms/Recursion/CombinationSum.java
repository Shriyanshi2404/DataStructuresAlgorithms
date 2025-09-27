package com.data.structures.algorithms.Recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.
Practice link: https://leetcode.com/problems/combination-sum/description/
*/
public class CombinationSum {

    /*
    Intuition: Whenever the problem is related to picking up elements from an array to form a combination, start thinking about the “pick and non-pick” approach.
    - Initially, the index will be 0, sum as 0 and target as given and the data structure(list) will be empty and take temporary list too.
    - there are 2 options : to pick or not pick the current index element.
    - If you pick the element
      - insert the current element into temp list
      - add current element in sum
      - again come back at the same index as multiple occurrences of the same element is possible and rest all parameters will be passed same
    - If you decide not to pick the current element
      - remove the current element from temp list
      - remove the current element from sum
      - move on to the next index and the target value stays as it is.
    - Keep on repeating this process while index < size of the array for a particular recursion call.
    - You can simply return if your sum > target, as we can't add the current element so we will backtrack
    - You can also stop the recursion when the sum == target value, but here a generalized version without adding too many conditions is considered.
    - Using this approach, we can get all the combinations.
     Time Complexity: O(2ⁿ*K) (Exponential) -> Because each call can generate multiple calls unless it hits a base case, k is the average length
     Space Complexity: O(n*K) -> due to recursion stack (function calls), k is the average length
    */
    public static void findCombinationSum(int index, List<List<Integer>> list, int[] arr, int target, int sum, List<Integer> temp)
    {
        if(sum > target)
            return;

        if(sum == target)
        {
            list.add(new ArrayList<>(temp));
            return;
        }

        if(index >= arr.length)
            return;

        // take the current element
        temp.add(arr[index]);
        sum += arr[index];
        findCombinationSum(index, list, arr, target, sum, temp);

        // not take the current element
        temp.remove(temp.size()-1);
        sum -= arr[index];
        findCombinationSum(index+1, list, arr, target, sum, temp);

    }
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        findCombinationSum(0, list, candidates, target, 0, temp);
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
        List<List<Integer>> list = combinationSum(candidates, target);
        if(list.isEmpty())
            System.out.println("[]");

        else {
            for (List<Integer> temp : list)
                System.out.println(temp);
        }
    }
}
