package com.data.structures.algorithms.Recursion;

import java.util.ArrayList;
import java.util.Scanner;

/*
Given a array arr of integers, return the sums of all subsets in the list. Return the sums in any order.
Practice link: https://www.geeksforgeeks.org/problems/subset-sums2234/1
*/
public class SubsetSumI {

    /*
     Approach : Recursive method
     - Maintain a arraylist (say list),which is empty initially.
     - Define a function say "findSubsetSums" where you will be passing below parameters
        - initial index as 0
        - original array
        - list to store the sum of subsets
        - variable "currentSum" to store the sum of each subset
     - If the base condition is hit, i.e. index >= arr.length() ,then add the currentSum to the list i.e. sum of the current subset and return
     - Either you can TAKE the element or NOT TAKE the element and move to the next index.
     - 1st option: TAKE the element at current index, add it to currentSum then move to the next index recursively calling function itself
     - 2nd option: NOT TAKE the element at current index, remove it from the currentSum then move to next index recursively calling function itself
     - Now while backtracking we have to pop the last element since now we have to implement the non-pick condition and then move to next index.

     Time Complexity: O(2ⁿ) (Exponential) -> Because each call generates two more calls unless it hits a base case
     Space Complexity: O(n) -> due to recursion stack (function calls)
    */
    public static void findSubsetSums(int index, int[] nums, ArrayList<Integer> list, int currentSum)
    {
        if(index >= nums.length)
        {
            list.add(currentSum);
            return;
        }

        // take the element
        currentSum += nums[index];
        findSubsetSums(index+1, nums, list, currentSum);

        // not take the element
        currentSum -= nums[index];
        findSubsetSums(index+1, nums, list, currentSum);
    }

    public static ArrayList<Integer> subsetSums(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        findSubsetSums(0, nums, list, 0);
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
        ArrayList<Integer> result = subsetSums(nums);
        System.out.println(result);
    }
}
