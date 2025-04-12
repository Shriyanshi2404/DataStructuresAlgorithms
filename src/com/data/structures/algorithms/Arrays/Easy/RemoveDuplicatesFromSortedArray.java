package com.data.structures.algorithms.Arrays.Easy;

import java.util.HashSet;
import java.util.Scanner;

public class RemoveDuplicatesFromSortedArray {

    // Brute force approach
    // T(n) = O(N^2)
    // We have to think of a data structure that does not store duplicate elements.
    // So can we use a Hash set? Yes! We can. As we know HashSet only stores unique elements.
    public static int removeDuplicatesBruteForce(int[] nums) {

        HashSet<Integer> set = new HashSet<>();
        for(int i=0;i<nums.length;i++)
        {
            set.add(nums[i]);
        }
        int k = set.size();
        int i = 0;
        for(int ele : set)
        {
            nums[i] = ele;
            i++;
        }
        return k;
    }

    // Optimal approach
    // T(n) = O(N)
    // We can think of using two pointers ‘i’ and ‘j’, we move ‘j’ till we don't get a number arr[j] which is different from arr[i].
    // As we got a unique number we will increase the i pointer and update its value by arr[j].
    public static int removeDuplicates(int[] nums) {
        int i=0;
        for(int j=1;j<nums.length;j++)
        {
            if(nums[i]==nums[j])
                continue;
            else
            {
                nums[i+1]=nums[j];
                i++;
            }
        }
        return i+1;
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
        System.out.println(removeDuplicates(nums));
    }
}
