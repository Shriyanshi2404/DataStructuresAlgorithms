package com.data.structures.algorithms.Arrays.Medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TwoSum {

    // Brute Force Approach T(N) = O(N^2)
    static int[] twoSum(int[] nums, int target)
    {
        int arr[] = {-1,-1};
        // pickup each element
        for(int i=0; i<nums.length; i++)
        {
            // now traverse through remaining array to find second element if sum up to target
            for(int j=i+1; j<nums.length; j++)
            {
                if(nums[i] + nums[j] == target)
                {
                    arr[0] = i;
                    arr[1] = j;
                    return arr;
                }
            }
        }
        return arr;
    }

    // Better Approach using HashMap T(N) = O(N logN)
    static int[] twoSumBetterApproach(int[] nums, int target) {
        int arr[] = {-1,-1};

        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<nums.length; i++)
        {
            // second element will be
            int element = (target - nums[i]);

            // find if second element is there in hashmap
            if(map.containsKey(element))
            {
                // return indices for first and second element
                arr[0] = i;
                arr[1] = map.get(element);
                return arr;
            }

            // when map does not contain second element, it means no such pair found
            // add current elemnet with index in hashmap
            else
            {
                map.put(nums[i], i);
            }
        }
        return arr;
    }

    // Optimal Approach using 2-pointer T(N) = O(N)+O(N logN)
    static int[] twoSumOptimalApproach(int[] nums, int target) {
        int arr[] = {-1,-1};

        // to get actual index we have to store elements in another array
        List<Integer> list =  Arrays.stream(nums).boxed().collect(Collectors.toList());

        // sorting the array
        Arrays.sort(nums);

        // keeping two pointers at first and last element respectively
        int i = 0, j = nums.length-1;

        // run a loop until left pointer does not crosses right pointer
        while(i < j)
        {
            int sum = nums[i] + nums[j];

            // check if sum is equal to targt, if yes return indices
            if(sum == target)
            {
                arr[0] = list.indexOf(nums[i]);
                arr[1] = list.lastIndexOf(nums[j]);
                return arr;
            }

            // if sum is less than target, which means we have to shift forward
            // so that sum can increase
            else if(sum < target)
                i++;

                // if sum is greater than target, which means we are exceeding need to shift backward
                // so that sum can decrease
            else
                j--;
        }
        return arr;
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
        int target = s.nextInt();
        int ans[] = twoSumOptimalApproach(arr, target);
        for(int i=0; i<ans.length; i++)
        {
            System.out.println(ans[i] + " ");
        }
    }
}
