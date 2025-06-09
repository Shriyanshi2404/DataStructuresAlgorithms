package com.data.structures.algorithms.Recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrintAllPossibleSubsets {

    /*
     Approach : Recursive method
     - Maintain a temp arraylist (say temp),which is empty initally.
     - Now you have two options,either you can pick the element or not pick the element and move to the next index.
     - Take the element at ith index and then move to the next index (temp + arr[i])
     - If the base condition is hit,i.e i==arr.length() ,then add the temp list into final array list and return.
     - Now while backtracking we have to pop the last element since now we have to implement the non-pick condition and then move to next index.

     Time Complexity: O(2ⁿ) (Exponential) -> Because each call generates two more calls unless it hits a base case
     Space Complexity: O(n) -> due to recursion stack (function calls)
    */
    public static void printAllSubsets(int index, List<List<Integer>> arr, int[] nums, List<Integer> temp)
    {
        if(index >= nums.length)
        {
            arr.add(new ArrayList<>(temp));
            return;
        }

        temp.add(nums[index]);
        printAllSubsets(index+1, arr, nums, temp);

        temp.remove(temp.size()-1);
        printAllSubsets(index+1, arr, nums, temp);
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> arr = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        printAllSubsets(0, arr, nums, temp);
        return arr;
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i=0; i<n; i++)
        {
            nums[i] = sc.nextInt();
        }
        List<List<Integer>> list = subsets(nums);
        for (List<Integer> sublist : list) {
            System.out.println(sublist);
        }
    }
}
