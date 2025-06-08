package com.data.structures.algorithms.Recursion;

import java.util.Scanner;

public class FactorialOfN {
    public static int factorial(int n)
    {
        if(n == 1)
            return 1;

        return n * factorial(n-1);
    }

    public static void findFactorial(int i, int ans)
    {
        if(i == 1)
        {
            System.out.print("Factorial of using parameterised way is : " + ans);
            return;
        }
        findFactorial(i-1, ans * i);
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // using functional way
        System.out.println("Factorial of "+ n + " is : " + factorial(n));

        // using parameterised way
        findFactorial(n, 1);
    }
}
