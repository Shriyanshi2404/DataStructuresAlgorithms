package com.data.structures.algorithms.Arrays.Medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NextPermutation {

    /*
    Brute force
    Step 1: Find all possible permutations of elements present and store them.
    Step 2: Search input from all possible permutations.
    Step 3: Print the next permutation present right after it.
    Below function is to find out all the permutations of given array
     */

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        findAllPermutations(0, nums, ans);
        return ans;
    }

    public void findAllPermutations(int index, int[] nums, List<List<Integer>> ans)
    {
        if(index == nums.length)
        {
            List<Integer> tempList = new ArrayList<>();
            for(int i=0;i<nums.length;i++)
                tempList.add(nums[i]);

            ans.add(tempList);
            return;
        }

        for(int i=index; i<nums.length; i++)
        {
            swap(nums, i, index);
            findAllPermutations(index+1, nums, ans);
            swap(nums, i, index);
        }
    }
    public void swap(int[] nums, int i, int j)
    {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /*
    Optimal solution
    T(N) = O(N) S(N) = O(1)
    Find the break-point, i: Break-point means the first index i from the back of the given array where arr[i] becomes smaller than arr[i+1].
    For example, if the given array is {2,1,5,4,3,0,0}, the break-point will be index 1(0-based indexing). Here from the back of the array, index 1 is the first index where arr[1] i.e. 1 is smaller than arr[i+1] i.e. 5.
    To find the break-point, using a loop we will traverse the array backward and store the index i where arr[i] is less than the value at index (i+1) i.e. arr[i+1].
    If such a break-point does not exist i.e. if the array is sorted in decreasing order, the given permutation is the last one in the sorted order of all possible permutations. So, the next permutation must be the first i.e. the permutation in increasing order.
            So, in this case, we will reverse the whole array and will return it as our answer.
    If a break-point exists:
    Find the smallest number i.e. > arr[i] and in the right half of index i(i.e. from index i+1 to n-1) and swap it with arr[i].
    Reverse the entire right half(i.e. from index i+1 to n-1) of index i. And finally, return the array.
    */
    public static void nextPermutation(int[] nums) {
        int n = nums.length;
        // finding the breaking point
        int index = -1;
        for(int i=n-2; i>=0; i--)
        {
            if(nums[i] < nums[i+1])
            {
                index = i;
                break;
            }
        }
        // when there is no breaking point, means next permutation is the reverse of the original array
        if(index == -1)
        {
            reverse(nums, 0, n-1);
            return;
        }
        // if break points exists,
        // find the closest greater element than the element which is breaking point from right half of array
        for(int i=n-1; i>index; i--)
        {
            if(nums[i] > nums[index])
            {
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
                break;
            }
        }
        // reverse the right side of the array from breaking point (index+1 to n-1)
        reverse(nums, index+1, n-1);
    }

    public static void reverse(int[] nums,int i, int j)
    {
        while(i<=j)
        {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }

    /* main function */
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int nums[] = new int[n];
        for(int i=0; i<n; i++)
        {
            nums[i] = s.nextInt();
        }

        nextPermutation(nums);
        for(int ele : nums) {
            System.out.print(ele + " ");
        }
    }
}
