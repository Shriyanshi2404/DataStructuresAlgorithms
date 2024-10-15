package com.data.structures.algorithms.Arrays;

import java.util.Arrays;
import java.util.Scanner;

public class MissingNumber {

    // Brute/Better approach
    // T(n) = O(N+NlogN)
    // Idea: First we will sort the array
    // Then, we will traverse over the array to find missing element by calculating difference between the elements.
    public static int missingNumberBruteForce(int[] nums) {
        Arrays.sort(nums);
        if(nums[0]!=0)
            return 0;

        int n=nums.length;
        for(int i=0;i<n-1;i++)
        {
            // if difference between two elements is not equal to 1
            // that means we have missing element between them
            if(nums[i+1]-nums[i] != 1)
            {
                return (nums[i+1]+nums[i])/2;
            }
        }
        // if we din't find any missing element while traversing array
        // then it means missing number will be next to the last element of array;
        return nums[n-1]+1;
    }

    // Optimal approach 1
    // T(N) = O(N)
    // Idea: Summation of the first N numbers is (N*(N+1))/2.We can say this Sum1.
    // Now, in the given array, every number between 1 to N except one number is present.
    // So, if we add the numbers of the array (say Sum2), the difference between Sum1 and Sum2 will be the missing number.
    // Because, while adding all the numbers of the array, we did not add that particular number that is missing.
    public static int missingNumberOptimalApproach(int[] nums) {
        int n = nums.length;
        int sum1 = ((n)*(n+1))/2;

        int sum2 = 0;
        for(int i=0; i<n;i++)
        {
            sum2 += nums[i];
        }
        return sum1-sum2;
    }


    // Optimal approach 1
    // T(N) = O(N)
    // XOR of two same numbers is always 0 i.e. a ^ a = 0. ←Property 1.
    // XOR of a number with 0 will result in the number itself i.e. 0 ^ a = a.  ←Property 2
    // Now, let’s XOR all the numbers between 1 to N.
    // xor1 = 1^2^.......^N
    //
    // Let’s XOR all the numbers in the given array.
    // xor2 = 1^2^......^N (contains all the numbers except the missing one).
    //
    //Now, if we again perform XOR between xor1 and xor2, we will get:
    //xor1 ^ xor2 = (1^1)^(2^2)^........^(missing Number)^.....^(N^N), hence you will gett missing number
    public static int missingNumber(int[] nums) {
        int n = nums.length;

        int xor1 = 0;
        for(int i=1; i<=n; i++)
        {
            xor1 = xor1 ^ i;
        }

        int xor2 = 0;
        for(int i=0; i<n;i++)
        {
            xor2 = xor2 ^ nums[i];
        }
        return xor1^xor2;
    }


    // main function
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int nums[] = new int[n];
        for(int i=0; i<n; i++)
        {
            nums[i] = s.nextInt();
        }
        System.out.println(missingNumber(nums));
    }
}
