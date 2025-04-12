package com.data.structures.algorithms.Arrays.Medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class LeadersInArray {

    /*
     brute force approach
     T(n) = O(N^2) S(n) = O(N)
     1. we will check for each element in the array whether it is a leader or not.
     2. Will run outer loop from i=0 to n-1, and set flag as true
     3. Run inner loop as from j=i+1 to n-1
     4. inside inner loop, will check if there is any element to the right that is greater than the element currently traversed by the outer loop.
      - If any element traversed is found greater than the element currently set as a leader
      - it will not go to the list and we won't go further in that loop, so set flag as false and break from loop
      - we increment the outer loop pointer by 1 and assume the next element as the current leader.
     5. after traversing through inner loop, if flag is still true
      - there is no element on right side which is greater than current element
      - so add current element into list of leaders
     */
    public static ArrayList<Integer> leaders(int n, int arr[]) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            boolean flag=true;
            for(int j=i+1;j<n;j++)
            {
                if(arr[j]>arr[i])
                {
                    flag=false;
                    break;
                }
            }
            if(flag==true)
                list.add(arr[i]);
        }
        return list;
    }

    /*
     Optimal approach
     T(n) = O(n) S(n) = O(N)
     1. last element in array will always be a leader since on it's right there is no element
     2. will keep a track of maximum element so far
     3. Traverse the array from (n-2)th index till 0th index element
     4. we will check if current element is greater than max element so far
        - if yes, then it means all the elements right of the current element are smaller
        - hence, current element is also a leader
     5. since we started traversing from end, hence we need to reverse a list
     6. return the list
     */
    public static ArrayList<Integer> leadersOptimalApproach(int n, int arr[]) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(arr[n-1]);
        int max = arr[n-1];

        for(int i=n-2; i>=0; i--)
        {
            if(arr[i] > max)
            {
                max = arr[i];
                list.add(arr[i]);
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
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

        ArrayList<Integer> list = leadersOptimalApproach(n, nums);
        for(int ele : list) {
            System.out.println(ele + " ");
        }
    }
}
