package com.data.structures.algorithms.Recursion;

import java.util.List;
import java.util.Scanner;

public class CheckIfSubsequenceExists {

    public static boolean findSubsequence(int index, int[] arr, int K, int currentSum)
    {
        if(index == arr.length)
        {
            if(currentSum == K)
                return true;
            return false;
        }

        // take the element
        currentSum += arr[index];
        if(findSubsequence(index+1, arr, K, currentSum))
            return true;

        // not take the element
        currentSum -= arr[index];
        if(findSubsequence(index+1, arr, K, currentSum))
            return true;

        return false;
    }

    public static boolean checkSubsequenceSum(int N, int[] arr, int K) {
        return findSubsequence(0, arr, K, 0);
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
        int K = sc.nextInt();
        System.out.println("Subsequence exists: " + checkSubsequenceSum(n, arr, K));
    }
}
