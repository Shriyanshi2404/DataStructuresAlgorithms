package com.data.structures.algorithms.Recursion;

import java.util.Scanner;

public class FindNthFibonacciNumber {

    /*
     Approach: Iterative method
     Base case: if n=0 then return 0 or if n=1 then return 1
     To compute F(n), we don’t need the entire array of Fibonacci numbers. We only need the last two values.
     Let: prev2 = F(n - 2)
          prev1 = F(n - 1)
     We iterate from i = 2 to n, and at each step, calculate: the next fibonacci number in the series and store that value in curr
     keep updating prev2 and prev1
     At last return curr, that is the nth fibonacci number of the series
     Time Complexity: O(n)
     Space Complexity: O(1)
    */
    public static int NthFibonacciNumberIterative(int n)
    {
        if(n == 0)
            return 0;

        if(n == 1)
            return 1;

        int prev2 = 0;
        int prev1 = 1;
        int curr = 0;
        for(int i=2; i<=n; i++)
        {
            curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return curr;
    }

    /*
     Approach: Recursive Fibonacci
     f(n) = f(n-1) + f(n-2)
     For every n, recursively compute F(n-1) and F(n-2)
     This continues until we hit the base cases: n == 0 or n == 1
     Then, you return values back up the call stack

     Time Complexity: O(2ⁿ) (Exponential) -> Because each call generates two more calls unless it hits a base case
     Space Complexity: O(n) -> due to recursion stack (function calls)
    */
    public static int NthFibonacciNumber(int n)
    {
        if( n == 0)
            return 0;
        if(n == 1)
            return 1;

        return NthFibonacciNumber(n-1) + NthFibonacciNumber(n-2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println("Fibonacci number of " + n + "th index is : " + NthFibonacciNumber(n));
    }
}
