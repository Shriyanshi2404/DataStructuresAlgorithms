package com.data.structures.algorithms.Arrays.Medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TwoSum {

    /*
     Brute force
     T(n) = O(N^2)
     1. Traverse through array using outer loop from i=0 to n-1
     2. Now traverse through array from j=i+1 t n-1
     3. everytime check if arr[i]+arr[j] == target,
     4. if yes, then assign 'i' and 'j' to result array and return
     5. if no indices found then simply return array with {-1,-1}
    */
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

    /*
    Better Approach
    T(n) = O(N*logN)
    1. Create a hashmap to store the elements and it's index
    2. Traverse through array using loop from i=0 to n-1
    3. Now find the remaining sum by target-nums[i]
    4. Check if remaining sum as a key exists in hashmap
    5. if yes, then assign 'i' and value of that key from map to result array and return
    6. if no, then add current element and it's index into hashmap
    7. at last return resultant array
    */
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

    /*
    Optimal Approach
    T(n) = O(N) + O(N*logN)
    1. We will sort the given array first.
    2. Now, we will take two pointers i.e. left, which points to the first index, and right, which points to the last index.
    3. Now using a loop we will check the sum of arr[left] and arr[right] until left < right.
       - If arr[left] + arr[right] > target, we will decrement the right pointer.
       - If arr[left] + arr[right] < target, we will increment the left pointer.
       - If arr[left] + arr[right] == target, we will return the result.
    4. Finally, if no results are found we will return “No” or {-1, -1}.
    */
    public static int[] twoSumOptimal(int[] nums, int target) {
        int[] arr = {-1, -1};
        // to get actual index we have to store elements in another array
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());

        int left = 0;
        int right = nums.length-1;
        Arrays.sort(nums);
        while(left < right)
        {
            int sum = nums[left]+nums[right];
            // check if sum is equal to targt, if yes return indices
            if(sum == target)
            {
                arr[0] = list.indexOf(nums[left]);
                arr[1] = list.lastIndexOf(nums[right]);
                left++;
                right--;
                return arr;
            }
            // if sum is less than target, which means we have to shift forward
            // so that sum can increase
            else if(sum < target)
                left++;
            // if sum is greater than target, which means we are exceeding need to shift backward
            // so that sum can decrease
            else
                right--;
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
        int ans[] = twoSumOptimal(arr, target);
        for(int i=0; i<ans.length; i++)
        {
            System.out.print(ans[i] + " ");
        }
    }
}
