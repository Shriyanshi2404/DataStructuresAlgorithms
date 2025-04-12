package com.data.structures.algorithms.Arrays.Easy;

import java.util.HashMap;
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

    // Better approach (Hashing using prefix sum approach)
    // T(n) = O(N)
    // First, we will declare a map to store the prefix sums and the indices.
    // Then we will run a loop(say i) from index 0 to n-1(n = size of the array).
    // For each index i, we will do the following:
    // We will add the current element i.e. a[i] to the prefix sum.
    // If the sum is equal to k, we should consider the length of the current subarray i.e. i+1. We will compare this length with the existing length and consider the maximum one.
    // We will calculate the prefix sum i.e. x-k, of the remaining subarray.
    // If that sum of the remaining part i.e. x-k exists in the map, we will calculate the length i.e. i-preSumMap[x-k], and consider the maximum one comparing it with the existing length we have achieved until now.
    // If the sum, we got after step 3.1, does not exist in the map we will add that with the current index into the map. We are checking the map before insertion because we want the index to be as minimum as possible and so we will consider the earliest index where the sum x-k has occurred. [Detailed discussion in the edge case section]
    public static int lenOfLongSubarrUsingHashing(int A[], int N, int K) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxLen=0, sum=0;

        for(int i=0;i<N;i++)
        {
            // will add current element into sum
            sum += A[i];

            // check if sum equals to K, if yes then check length and store maximum one
            if(sum == K)
            {
                maxLen =  Math.max(maxLen, i+1);
            }

            // calculate prefix sum of remaining subarray
            int prefSum = sum-K;

            // if prefix sum exists in map, then check length and store maximum one
            if(map.containsKey(prefSum))
            {
                int len = i - map.get(prefSum);
                maxLen = Math.max(maxLen, len);
            }

            // if map does not contains existing sum, then add it with current index
            if(!map.containsKey(sum))
            {
                map.put(sum, i);
            }
        }
        return maxLen;
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
