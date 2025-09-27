package com.data.structures.algorithms.Recursion;

import java.util.ArrayList;
import java.util.Scanner;

public class CountSubsequencesWithSumK {

    /*
     Approach : Recursive method
     - Maintain a variable currentSum as 0
     - Now you have two options, either you can pick the element or not pick the element and move to the next index.
     - Take the current element → Add its value to the running currentSum and then move to the next index (temp + arr[i])
     - If the base condition is hit,i.e i==arr.length() and then currentSum==sum ,then return 1 since you find a subsequence, else 0
     - Now while backtracking we have to pop the last element added from currentSum
     - Do not take the current element → Ignore it, and remove the last added element from currentSum and then move to next index.
     - keep storing result
       - One where the element is included in the sum (leftCount).
       - One where it is excluded (rightCount).
     - At last return leftCount + rightCount

     Time Complexity: O(2ⁿ) (Exponential) -> Because each call generates two more calls unless it hits a base case
     Space Complexity: O(n) -> due to recursion stack (function calls)
    */
    public static int countSubsequencesWithSumK(int index, int[] arr, int sum, int currentSum)
    {
        if(index == arr.length)
        {
            if(currentSum == sum)
            {
                return 1;
            }
            return 0;
        }

        // take the element
        currentSum += arr[index];
        int leftCount = countSubsequencesWithSumK(index+1, arr, sum, currentSum);

        // not take the element
        currentSum -= arr[index];
        int rightCount = countSubsequencesWithSumK(index+1, arr, sum, currentSum);

        return leftCount + rightCount;
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

        int count = countSubsequencesWithSumK(0, arr, sum, 0);
        System.out.println("Count of all subsequences are : " + count);
    }
}
