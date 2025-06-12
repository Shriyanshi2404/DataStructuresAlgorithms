package com.data.structures.algorithms.Recursion;

import java.util.ArrayList;
import java.util.Scanner;

public class PrintAny1SubsequenceWithSumK {

    /*
     Approach : Recursive method
     - Maintain a temp arraylist (say temp),which is empty initially and currentSum as 0
     - Now you have two options,either you can pick the element or not pick the element and move to the next index.
     - Firstly we pick the element at ith index and then move to the next index (temp + arr[i])
     - If the base condition is hit,i.e i==arr.length() and then currentSum==sum ,then we print the temp list and return true, else false
     - Now while backtracking we have to pop the last element from temp list and from currentSum
     - since now we have to implement the non-pick condition only if we do not find any subsequence by taking the element and then move to next index.

     Time Complexity: O(2ⁿ) (Exponential) -> Because each call generates two more calls unless it hits a base case
     Space Complexity: O(n) -> due to recursion stack (function calls)
    */
    public static boolean printAnySubsequenceWithSumK(int index, ArrayList<Integer> temp, int[] arr, int sum, int currentSum)
    {
        if(index == arr.length)
        {
            if(currentSum == sum)
            {
                for(int ele : temp)
                {
                    System.out.print(ele + " ");
                }
                System.out.println();
                return true;
            }
            return false;
        }

        // take the element
        temp.add(arr[index]);
        currentSum += arr[index];
        if(printAnySubsequenceWithSumK(index+1, temp, arr, sum, currentSum))
            return true;

        // not take the element
        temp.remove(temp.size()-1);
        currentSum -= arr[index];
        if(printAnySubsequenceWithSumK(index+1, temp, arr, sum, currentSum))
            return true;

        return false;
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++)
        {
            arr[i] = sc.nextInt();
        }
        int sum = sc.nextInt();

        ArrayList<Integer> temp = new ArrayList<>();
        printAnySubsequenceWithSumK(0, temp, arr, sum, 0);
    }
}
