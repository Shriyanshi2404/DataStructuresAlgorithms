package com.data.structures.algorithms.Arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class LeadersInArray {

    // brute force approach
    // T(n) = O(N^2)
    public static ArrayList<Integer> leaders(int n, int arr[]) {
        ArrayList<Integer> list = new ArrayList<>();
        // will check for each element in the array whether it is a leader or not.
        for(int i=0;i<n;i++)
        {
            boolean flag=true;
            // checks if there is any element to the right that is greater than the element currently traversed by the outer loop.
            for(int j=i+1;j<n;j++)
            {
                // If any element traversed is found greater than the element currently set as a leader,
                // it will not go to the list and we won't go further in that loop
                // we increment the outer loop pointer by 1 and assume the next element as the current leader.
                if(arr[j]>arr[i])
                {
                    flag=false;
                    break;
                }
            }
            // it means there is no element on right side which is greater than current element
            if(flag==true)
                list.add(arr[i]);
        }
        return list;
    }

    // Optimal approach
    // T(n) = O(n)
    public static ArrayList<Integer> leadersOptimalApproach(int n, int arr[]) {
        ArrayList<Integer> list = new ArrayList<>();

        // last element in array will always be a leader since on it's right there is no element
        list.add(arr[n-1]);

        // will keep a track of maximum element
        int max = arr[n-1];

        // Start checking from the end whether a number is greater
        // than max no. from right, hence leader.
        for(int i=n-2; i>=0; i--)
        {
            // we will check if current element is greater than max element so far
            // if yes, then it means all the elements right of the current element are greater
            // hence, current element is also a leader
            if(arr[i] > max)
            {
                max = arr[i];
                list.add(arr[i]);
            }
        }
        // since we started traversing from end, hence we need to reverse a list
        Collections.sort(list, Collections.reverseOrder());
        return list;
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

        ArrayList<Integer> list = leadersOptimalApproach(n, nums);
        for(int ele : list) {
            System.out.println(ele + " ");
        }
    }
}
