package com.data.structures.algorithms.Recursion;

import java.util.Scanner;

public class ReverseArray {
    /*
     using iterative method
     Time Complexity: O(n)
     Space Complexity: O(1)
     */
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

    /*
     using recursive method
     Time Complexity: O(n)
     Space Complexity: O(n) → due to recursion stack (function calls)
    */
    public static void reverseArray(int low, int high, int[] arr)
    {
        if(low>high)
            return;

        int temp = arr[low];
        arr[low] = arr[high];
        arr[high] = temp;
        reverseArray(low+1, high-1, arr);
    }

    /*
     Optimal Solution
     Base case: Once the current index 'i' reaches or exceeds the midpoint (n/2), all elements have been swapped. So, recursion stops.
     Else, Swap the element at index 'i' with the element at the symmetric position from the end: 'n - i - 1'
     Then, recursively call the function with (i+1, n, arr).
     Time Complexity: O(n/2) → O(n)
     Space Complexity: O(n/2) → due to recursion stack (function calls)
   */
    public static void ReverseArrayOptimalSolution(int i, int n, int[] arr)
    {
        if(i >= (n/2))
            return;

        int temp = arr[i];
        arr[i] = arr[n-i-1];
        arr[n-i-1] = temp;
        ReverseArrayOptimalSolution(i+1, n, arr);
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

        ReverseArrayOptimalSolution(0, n, arr);
        System.out.println("Reversed array using recursive method is : ");
        for(int i=0; i<n; i++)
        {
            System.out.print(arr[i] + " ");
        }

        /*
        Commented out because we are solving using recursion
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
        */
    }
}
