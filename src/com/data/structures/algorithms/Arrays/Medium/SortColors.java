package com.data.structures.algorithms.Arrays.Medium;

import java.util.Scanner;

public class SortColors {

    // brute force approach - bubble sort
    // T(n) = O(N^2)
    public static void sortColors(int[] nums) {
        int n=nums.length;
        for(int i=n-1; i>=1; i--)
        {
            for(int j=0; j<i; j++)
            {
                if(nums[j] > nums[j+1])
                {
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }
    }

    /*
     better approach
     will keep count of 0's, 1's and 2's and then place each value according to frequency in same array
     T(n) = O(2N)
     */
    public static void sortColorsBetterSolution(int[] nums) {
        int n=nums.length;
        // will keep a count of 0's, 1's and 2's
        int count_0=0, count_1=0, count_2=0;
        for(int i=0;i<n;i++)
        {
            if(nums[i]==0)
                count_0++;
            else if(nums[i]==1)
                count_1++;
            else if(nums[i]==2)
                count_2++;
        }

        // placing 0's in array
        for(int i=0;i<count_0;i++)
        {
            nums[i]=0;
        }
        // placing 1's in array
        for(int i=count_0;i<count_0+count_1;i++)
        {
            nums[i]=1;
        }
        // placing 2's in array
        for(int i=count_0+count_1;i<n;i++)
        {
            nums[i]=2;
        }
    }

    /*
    better approach
    T(n) = O(N) S(n) = O(1)
    1. we will keep count of 0's, 1's and 2's and then place each value according to frequency in same array
    2. Traverse through array for 1st time and count all 0's, 1's and 2's
    3. Now run a traverse the array again, and start assigning 0's, 1's and 2's
        - Start assigning 0's until count of 0's becomes 0
        - then start assigning 1's until count of 1's becomes 0
        - then start assigning 2's until count of 2's becomes 0
    */
    public void sortColorsBetterApproach(int[] nums) {
        int zeroes = 0, ones = 0, twos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                zeroes++;
            else if (nums[i] == 1)
                ones++;
            else
                twos++;
        }

        for (int i = 0; i < nums.length; i++) {
            if (zeroes != 0) {
                nums[i] = 0;
                zeroes--;
            } else if (ones != 0) {
                nums[i] = 1;
                ones--;
            } else if (twos != 0) {
                nums[i] = 2;
                twos--;
            }
        }
    }

    /*
     Optimal approach -  Dutch National Flag Algorithm
     T(n) = O(N)
     it has 3 pointers- low,mid,high with 3 rules
     rule 1: element between (0 to low-1) index will always be 0
     rule 2: element between (low to mid-1) index will always be 1
     NOTE: arr[mid....high-1] contains unsorted part
     rule 3: element between (high+1 to n-1) index will always be 2
     */

    public static void sortColorsOptimalSolution(int[] nums) {
        int n=nums.length;
        int low=0,mid=0,high=n-1;

        while(mid<=high)
        {
            // element is 0 then swap element at mid index with element at low index
            // increment both index
            if(nums[mid] == 0)
            {
                swap(nums, mid, low);
                low++;
                mid++;
            }
            // element is 1, then simply increment mid index
            else if(nums[mid] == 1)
            {
                mid++;
            }
            // element is 2 then swap element at mid index with element at high index
            // decrement high index
            else if(nums[mid] == 2)
            {
                swap(nums, mid, high);
                high--;
            }
        }
    }

    public static void swap(int[] nums, int i, int j)
    {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
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
        sortColorsOptimalSolution(nums);
        for(int i=0; i<nums.length; i++)
        {
            System.out.print(nums[i] + " ");
        }
    }
}
