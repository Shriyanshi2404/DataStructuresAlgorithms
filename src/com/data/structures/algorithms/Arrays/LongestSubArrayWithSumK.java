package com.data.structures.algorithms.Arrays;

import java.util.Scanner;

// Note all array elements will be Positive
public class LongestSubArrayWithSumK {

    // Brute Force approach T(N) = O(N^3)
    static int lenOfLongSubarr(int A[], int N, int K) {
        int length = 0;
        for(int i=0; i<N; i++)
        {
            for(int j=i; j<N; j++)
            {
                long sum = 0;
                for (int index = i; index <= j; index++) {
                    sum += A[index];
                }
                if(sum == K)
                    length = Math.max(length, (j-i+1));
            }
        }
        return length;
    }

    // Better approach T(N) = O(N^2)
    static int lenOfLongSubarrBetter(int A[], int N, int K) {
        int length = 0;
        for(int i=0; i<N; i++)
        {
            int sum = 0;
            for(int j=i; j<N; j++)
            {
                sum += A[j];
                if(sum == K)
                {
                    length = Math.max(length, (j-i+1));
                }
            }
        }
        return length;
    }

    // Optimal Approach T(N) = O(2N)
    static int lenOfLongSubarrOptimal(int A[], int N, int K) {
        int length = 0, sum = A[0];
        int i = 0, j = 0;
        while(j < N)
        {
            // if sum exceeds K, then we need to shift left pointer further
            // also needs to removed left pointer element from sum
            while(i <= j && sum > K)
            {
                sum -= A[i];
                i++;
            }

            // check if sum is equal to K, calculate length
            if(sum == K)
            {
                length = Math.max(length, (j-i+1));
            }

            // increment right pointer and add right pointer element to sum
            j++;

            // we can only add elements till (n-1)th index
            if(j < N)
                sum += A[j];
        }
        return length;
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
        int K = s.nextInt();
        System.out.println(lenOfLongSubarr(arr, n, K));
    }
}
