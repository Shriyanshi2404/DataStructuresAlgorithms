package com.data.structures.algorithms.Arrays.Easy;

import java.util.Scanner;

public class MaximumConsecutiveOnes {

    // optimal solution  T(N) = O(N)
    static int findMaxConsecutiveOnes(int[] nums) {
        int count = 0, max = 0;
        for(int i=0; i<nums.length; i++)
        {
            if(nums[i] == 1)
            {
                // keeping the count of consecutive one's
                count++;
                max = Math.max(count, max);
            }
            else
            {
                // resetting count to 0 when there is zero's coming in btw the array
                count = 0;
            }
        }
        return max;
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
        System.out.println(findMaxConsecutiveOnes(arr));
    }
}
