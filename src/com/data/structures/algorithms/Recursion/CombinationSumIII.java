package com.data.structures.algorithms.Recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CombinationSumIII {

    /*
    Intuition: Whenever the problem is related to picking up elements from an array to form a combination, start thinking about the “pick and non-pick” approach.
    - Initially, We start with the index 1, given K i.e. length of subsequence, given target and empty list "list" and "temp" list
    - Run a for loop to Check if the current index value can be added to our list
      - If yes add it to the temp list and move the index by 1.
      - Reduce the target by 'i',call the recursive call for f(idx + 1,target - i,list,temp) after the call make sure to pop the element from the temp list.
    - Base condition:
      - if size of temp list is equal to K but target is not equal to 0, then terminate the recursive call because there is no use to add the temp list into final list as target is not 0
      - if the target == 0 then check if size of temp list is equal to K, if yes then add temporary list to final list and return finally

    Time Complexity: O(2ⁿ*K) (Exponential) -> Because each call can generate two calls unless it hits a base case, k is the average length
    Space Complexity: O(n*K) -> due to recursion stack (function calls), k is the average length
    */
    public static void findCombinationSum(int index, int k, int target, List<List<Integer>> list, List<Integer> temp)
    {
        if(temp.size() == k && target != 0)
            return;

        if(target == 0)
        {
            if(temp.size() == k)
                list.add(new ArrayList<>(temp));
            return;
        }

        for(int i=index; i<=9; i++)
        {
            // case 1: pick the element
            temp.add(i);
            findCombinationSum(i+1, k, target-i, list, temp);
            // case 2: not to pick the element
            temp.remove(temp.size()-1);
        }
    }
    public static List<List<Integer>> combinationSum3(int k, int target) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        findCombinationSum(1, k, target, list, temp);
        return list;
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int target = sc.nextInt();

        List<List<Integer>> list = combinationSum3(k, target);
        if(list.isEmpty())
            System.out.println("[]");
        else {
            for (List<Integer> temp : list)
                System.out.println(temp);
        }
    }
}
