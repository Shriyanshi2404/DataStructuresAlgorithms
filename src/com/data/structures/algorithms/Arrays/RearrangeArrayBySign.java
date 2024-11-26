package com.data.structures.algorithms.Arrays;

import java.util.ArrayList;
import java.util.Scanner;

public class RearrangeArrayBySign {

    // Brute force approach
    // T(n) = O(N + N/2)
    // picking positive and negative elements in separate two arrays
    // after separating, just start assigning elements in exisitng array in a manner like positive element, negative element and so on
    public static int[] rearrangeArray(int[] nums)
    {
        int n=nums.length;
        int positives[]=new int[n/2];
        int negatives[]=new int[n/2];
        int index=0,l=0;
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]>0)
            {
                positives[index]=nums[i];
                index++;
            }
            else
            {
                negatives[l]=nums[i];
                l++;
            }
        }

        int p1=0,n1=0,i=0;
        while(i<n)
        {
            nums[i]=positives[p1];
            nums[i+1]=negatives[n1];
            i+=2;
            p1++;
            n1++;
        }

        return nums;
    }

    // Optimal approach
    // T(n) = O(N)
    // Here, we will traverse array only once.
    // Idea is to keep posIndex and negIndex pointer at 0, 1 respectively (since array should have positive element at 0th index)
    // during traversal, if we found positive element, then store current element in new array at index posIndex and increment posIndex by 2.
    // if we found negative element, then store current element in new array at index negIndex and increment negIndex by 2.
    public static int[] rearrangeArrayOptimalApproach(int[] nums) {
        int n=nums.length;
        int ans[]=new int[n];

        int p1=0,n1=1;
        for(int i=0;i<n;i++)
        {
            if(nums[i]>0)
            {
                ans[p1]=nums[i];
                p1+=2;
            }
            else
            {
                ans[n1]=nums[i];
                n1+=2;
            }
        }
        return ans;
    }

    // Brute force approach
    // T(n) = O(2N)
    // here positives and negatives might not be equal in number
    // Idea: all the positive numbers would be placed at even indices (2*i) and negatives at the odd indices (2*i+1)
    // we now put all the remaining elements ( whether positive or negative) at the last of the array by running a single loop
    // {if positives > negatives} then will run a loop from neg.size() to pos.size() and start assigning positive elements to end of array.
    // {if negatives > positives} then will run a loop from pos.size() to neg.size() and start assigning negative element to end of array.
    public static int[] rearrangeArrayVariety2(int[] nums) {
        int n=nums.length;
        int ans[]=new int[n];
        ArrayList<Integer> pos=new ArrayList<>();
        ArrayList<Integer> neg=new ArrayList<>();

        for(int i=0;i<n;i++)
        {
            if(nums[i]>0)
            {
                pos.add(nums[i]);
            }
            else
            {
                neg.add(nums[i]);
            }
        }

        if(pos.size()>neg.size())
        {
            for(int i=0;i<neg.size();i++)
            {
                ans[i*2]=pos.get(i);
                ans[i*2+1]=neg.get(i);
            }
            int index=neg.size()*2;
            for(int i=neg.size();i<pos.size();i++)
            {
                ans[index]=pos.get(i);
                index++;
            }
        }
        else
        {
            for(int i=0;i<pos.size();i++)
            {
                ans[i*2]=pos.get(i);
                ans[i*2+1]=neg.get(i);
            }
            int index=pos.size()*2;
            for(int i=pos.size();i<neg.size();i++)
            {
                ans[index]=neg.get(i);
                index++;
            }
        }
        return ans;
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

        nums = rearrangeArray(nums);
        for(int i=0;i<nums.length;i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
