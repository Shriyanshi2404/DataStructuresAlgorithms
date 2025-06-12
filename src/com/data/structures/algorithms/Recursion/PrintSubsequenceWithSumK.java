package com.data.structures.algorithms.Recursion;

import java.util.ArrayList;
import java.util.Scanner;

public class PrintSubsequenceWithSumK {

    static int currentSum = 0;
    /*
     Approach : Recursive method
     - Maintain a temp arraylist (say temp),which is empty initally and currentSum as 0
     - Now you have two options,either you can pick the element or not pick the element and move to the next index.
     - Firstly we pick the element at ith index and then move to the next index (temp + arr[i])
     - If the base condition is hit,i.e i==arr.length() and then currentSum==sum ,then we print the temp list and return.
     - Now while backtracking we have to pop the last element from temp list and from currentSum since now we have to implement the non-pick condition and then move to next index.

     Time Complexity: O(2ⁿ) (Exponential) -> Because each call generates two more calls unless it hits a base case
     Space Complexity: O(n) -> due to recursion stack (function calls)
    */
    public static void printSubsequenceWithSumK(int index, ArrayList<Integer> temp, int[] arr, int sum)
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
            }
            return;
        }

        // take the element
        temp.add(arr[index]);
        currentSum += arr[index];
        printSubsequenceWithSumK(index+1, temp, arr, sum);

        // not take the element
        temp.remove(temp.size()-1);
        currentSum -= arr[index];
        printSubsequenceWithSumK(index+1, temp, arr, sum);
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
        printSubsequenceWithSumK(0, temp, arr, sum);
    }
}
