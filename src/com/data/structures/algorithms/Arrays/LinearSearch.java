package com.data.structures.algorithms.Arrays;

import java.util.Scanner;

public class LinearSearch {

    static int findAnElement(int n, int arr[], int K) {
        for(int i=0; i<arr.length; i++)
        {
            if(arr[i] == K)
                return 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int arr[] = new int[n];
        for(int i=0; i<n; i++)
        {
            arr[i] = s.nextInt();
        }
        // element to find
        int K = s.nextInt();
        System.out.println(findAnElement(n, arr, K));
    }
}