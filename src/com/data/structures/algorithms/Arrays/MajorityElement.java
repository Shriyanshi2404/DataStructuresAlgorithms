package com.data.structures.algorithms.Arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MajorityElement {

    // Brute Force approach
    // T(n) = O(N^2)
    // We will run a loop that will select the elements of the array one by one.
    // Now, for each element, we will run another loop and count its occurrence in the given array.
    // If any element occurs more than the floor of (N/2), we will simply return it.
    public static int majorityElementBruteForce(int[] nums) {
        int n=nums.length;
        for(int i=0;i<n;i++)
        {
            int ele=nums[i];
            int count=1;
            // will find if further in array we have same element or not
            for(int j=i+1;j<n;j++) {
                if (nums[j] == ele) {
                    count++;
                }
            }
            if(count > (n/2))
                return ele;
        }
        return -1;
    }

    // Better Approach
    // T(n) = O(N*logN) + O(N)
    // Use a hashmap and store as (key, value) pairs. (Can also use frequency array based on the size of nums)
    // Here the key will be the element of the array and the value will be the number of times it occurs.
    // Traverse the array and update the value of the key.
    // Later traverse through map, check if the value of key is greater than the floor of N/2, if yes return that key.
    public static int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        // will store frequency of each element
        for(int i=0;i<nums.length;i++)
        {
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
        }

        int n = nums.length;
        // will iterate over a map, wherever value is greater than (n/2) will return the element
        for(Map.Entry<Integer, Integer> ele : map.entrySet())
        {
            int value = ele.getValue();
            if(value > (n/2))
            {
                // returning majority element
                return ele.getKey();
            }
        }
        return -1;
    }

    // Optimal solution
    // T(n) = O(N) + O(N)
    // Idea is Moore's voting algorithm
    // If the array contains a majority element, its occurrence must be greater than the floor(N/2).
    // Now, we can say that the count of minority elements and majority elements is equal up to a certain point in the array.
    // So when we traverse through the array we try to keep track of the count of elements and the element itself for which we are tracking the count.
    // After traversing the whole array, we will check the element stored in the variable.
    // If the question states that the array must contain a majority element, the stored element will be that one
    // but if the question does not state so, then we need to check if the stored element is the majority element or not. If not, then the array does not contain any majority element.
    public static int majorityElementOptimalSolution(int[] nums) {
        int n = nums.length;
        int Element=0;
        int count = 0;

        for(int i=0; i<n; i++)
        {
            if(count == 0)
            {
                Element = nums[i];
                count=1;
            }
            else if(Element == nums[i])
                count++;
            else if(Element != nums[i])
                count--;
        }
        return Element;
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
        System.out.println("Majority element is: " + majorityElement(nums));
    }
}
