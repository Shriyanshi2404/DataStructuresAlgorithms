package com.data.structures.algorithms.Recursion;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Scanner;

public class PrintingAllSubsequences {

    /*
     Approach : Recursive method
     - Maintain a temp arraylist (say temp),which is empty initally.
     - Now you have two options,either you can pick the element or not pick the element and move to the next index.
     - Firstly we pick the element at ith index and then move to the next index (temp + arr[i])
     - If the base condition is hit,i.e i==arr.length() ,then we print the temp list and return.
     - Now while backtracking we have to pop the last element since now we have to implement the non-pick condition and then move to next index.

     Time Complexity: O(2ⁿ) (Exponential) -> Because each call generates two more calls unless it hits a base case
     Space Complexity: O(n) -> due to recursion stack (function calls)
    */
    public static void printSubsequence(int index, ArrayList<Integer> temp, int[] arr)
    {
        if(index >= arr.length)
        {
            for(int ele : temp)
            {
                System.out.print(ele + " ");
            }
            System.out.println();
            return;
        }

        // take the current element
        temp.add(arr[index]);
        printSubsequence(index+1, temp, arr);

        // not take the current element
        temp.remove(temp.size()-1);
        printSubsequence(index+1, temp, arr);
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

        ArrayList<Integer> temp = new ArrayList<>();
        printSubsequence(0, temp, arr);
    }
}
