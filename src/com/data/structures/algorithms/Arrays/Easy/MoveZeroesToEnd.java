package com.data.structures.algorithms.Arrays.Easy;

import java.util.ArrayList;
import java.util.Scanner;

public class MoveZeroesToEnd {

    // Brute force approach
    // there are N-X zeros and X non-zero elements in the array.
    // We will first copy those X non-zero elements from the original array to a temporary array.
    // Then, we will copy the elements from the temporary array one by one and fill the first X places of the original array.
    // The last N-X places of the original array will be then filled with zero.
    public static void moveZeroesBruteForce(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]!=0)
                list.add(nums[i]);
        }

        int zeroes = nums.length-list.size();
        for(int i=0;i<list.size();i++)
        {
            nums[i] = list.get(i);
        }

        for(int i=list.size();i<nums.length;i++)
        {
            nums[i] = 0;
        }
    }

    // Optimal approach (2-pointer)
    // T(n) = O(N)
    // we will find index of very first 0 element say index 'j'
    // then will run another loop starting from next of jth index
    // if arr[i] != 0 i.e. arr[i] is a non-zero element: We will swap arr[i] and arr[j].
    // Now, the current j is pointing to the non-zero element a[i].
    // So, we will shift the pointer j by 1 so that it can again point to the first zero.
    public static void moveZeroes(int[] nums) {
        int index=-1;
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i] == 0)
            {
                index = i;
                break;
            }
        }

        if(index<0)
            return;

        for(int i=index+1;i<nums.length;i++)
        {
            if(nums[i] != 0)
            {
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
                index++;
            }
        }
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
        moveZeroes(nums);
        for(int i=0;i<nums.length;i++)
        {
            System.out.println(nums[i] +" ");
        }
    }
}
