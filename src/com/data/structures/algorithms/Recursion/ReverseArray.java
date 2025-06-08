package com.data.structures.algorithms.Recursion;

import java.util.Scanner;

public class ReverseArray {
    public static void reverseArrayIterative(int n, int[] arr)
    {
        int low = 0;
        int high = n-1;
        while(low<high)
        {
            int temp = arr[low];
            arr[low] = arr[high];
            arr[high] = temp;
            low++;
            high--;
        }
    }

    public static void reverseArray(int low, int high, int[] arr)
    {
        if(low>high)
            return;

        int temp = arr[low];
        arr[low] = arr[high];
        arr[high] = temp;
        reverseArray(low+1, high-1, arr);
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

        reverseArrayIterative(n, arr);
        System.out.println("Reversed array using iterative method is : ");
        for(int i=0; i<n; i++)
        {
            System.out.print(arr[i] + " ");
        }

        reverseArray(0, n-1, arr);
        System.out.println("Reversed array using recursive method is : ");
        for(int i=0; i<n; i++)
        {
            System.out.print(arr[i] + " ");
        }
    }
}
