package com.data.structures.algorithms.Arrays;

import java.util.Scanner;

public class StockBuyAndSell {

    // Brute force approach
    // T(n) = O(N^2)
    // Intuition: We can simply use 2 loops and track every transaction and maintain a variable maxProfit to contain the max value among all transactions.
    public static int maxProfit(int[] prices) {
        int maxProfit = 0;
        int diff=0;
        int n=prices.length;

        for(int i=0;i<n;i++)
        {
            for(int j=i+1;j<n;j++)
            {
                // selling price should be greater than the cost price
                // if not then profit will be in negative
                if(prices[j] > prices[i])
                {
                    diff = prices[j] - prices[i];
                    maxProfit = Math.max(diff, maxProfit);
                }
            }
        }
        return maxProfit;
    }

    // Optimal Approach
    // T(n) = O(N)
    // We can maintain a minimum from the start of the array and compare it with every element of the array
    // if it is greater than the minimum then take the difference and maintain it in max, otherwise update the minimum.
    public static int maxProfitOptimalApproach(int[] prices) {
        int maxProfit = 0;
        int minPrice = prices[0];
        int n=prices.length;

        for(int i=1;i<n;i++)
        {
            if(minPrice > prices[i])
            {
                minPrice = prices[i];
            }
            int diff = prices[i]-minPrice;
            maxProfit = Math.max(maxProfit, diff);
        }
        return maxProfit;
    }

    // main function
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int arr[] = new int[n];
        for(int i=0; i<n; i++)
        {
            arr[i] = s.nextInt();
        }
        System.out.println(maxProfit(arr));
    }
}
