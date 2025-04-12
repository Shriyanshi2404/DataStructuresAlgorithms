package com.data.structures.algorithms.Arrays.Medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class LongestConsecutiveSequence {

    public static boolean linearSearch(int[] nums, int element)
    {
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i] == element)
                return true;
        }
        return false;
    }

    // Brute Force approach
    // T(n) = O(N^2)
    public static int longestConsecutive(int[] nums) {

        int count=1,maxLength=1;
        if(nums.length == 0)
            return 0;

        for(int i=0;i<nums.length;i++)
        {
            count = 1;
            int x = nums[i];

            // will find if consecutive element of x is present in array or not
            while(linearSearch(nums, x+1) == true)
            {
                // if present, then we will find next consecutive element
                x = x+1;
                count++;
            }
            maxLength = Math.max(count, maxLength);
        }
        return maxLength;
    }

    // Better approach
    // T(n) = O(N*logN) + O(N)
    public static int longestConsecutiveBetterApproach(int[] nums) {

        int count=0, longest=1;
        int lastSmaller = Integer.MIN_VALUE;

        if(nums.length == 0)
            return 0;

        Arrays.sort(nums);

        for(int i=0;i<nums.length;i++)
        {
            // If arr[i]-1 == lastSmaller:
            // The last element in our sequence is labeled as 'lastSmaller' and the current element 'arr[i]' is exactly 'lastSmaller'+1.
            // It indicates that 'arr[i]' is the next consecutive element.
            // we update 'lastSmaller' with the value of 'arr[i]' and increment the length of the current sequence, denoted as 'count', by 1.
            if(nums[i]-1 == lastSmaller)
            {
                lastSmaller = nums[i];
                count++;
            }

            // if lastSmaller != arr[i]:
            // we can conclude that the current element, arr[i] > lastSmaller+1.
            // It indicates that arr[i] cannot be a part of the current sequence.
            // So, we will start a new sequence from arr[i] by updating ‘lastSmaller’ with the value of arr[i].
            // And we will set the length of the current sequence(cnt) to 1.
            else if(nums[i] != lastSmaller)
            {
                lastSmaller = nums[i];
                count = 1;
            }

            // If arr[i] == lastSmaller: If the current element, arr[i], matches the last element of the sequence (represented by 'lastSmaller'),
            // we can skip it since we have already included it before.
            else if(nums[i] == lastSmaller)
                continue;

            longest = Math.max(count, longest);
        }
        return longest;
    }

    // Optimal Approach
    // T(n) = O(N)
    // using set data structure and finding starting element of sequence
    public static int longestConsecutiveOptimalApproach(int[] nums) {

        int count=0, longest=1;
        if(nums.length == 0)
            return 0;

        // to remove duplicates, we are using set data structure
        HashSet<Integer> set = new HashSet<>();
        for(int i=0;i<nums.length;i++)
        {
            set.add(nums[i]);
        }

        // traversing through each element stored in set
        for(int x : set)
        {
            // to check if 'x' is starting element of the sequence
            if(!set.contains(x-1))
            {
                count = 1;
                // find next consecutive elements
                // it will run until we keep finding. like x+1, x+2 ...
                while(set.contains(x+1))
                {
                    x = x+1;
                    count++;
                }

                // compare length of current seq. and maxLength so far stored in longest
                longest = Math.max(count, longest);
            }
        }
        return longest;
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
        System.out.println(longestConsecutive(nums));
    }
}
