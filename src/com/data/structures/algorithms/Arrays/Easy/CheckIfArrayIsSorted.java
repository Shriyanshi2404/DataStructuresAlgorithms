package com.data.structures.algorithms.Arrays.Easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CheckIfArrayIsSorted {

    // Brute force approach
    // We will start with the element at the 0th index, and will compare it with all of its future elements that are present in the array.
    // If the picked element is smaller than or equal to all of its future values then we will move to the next Index/element until the whole array is traversed.
    // If any of the picked elements is greater than its future elements, Then simply we will return False.
    public static boolean arraySortedOrNotBruteForce(List<Integer> arr)
    {
        for(int i=0;i<arr.size();i++)
        {
            for(int j=i+1;j<arr.size();j++)
            {
                if(arr.get(i) > arr.get(j))
                {
                    return false;
                }
            }
        }
        return true;
    }

    // Optimal Approach
    // T(n) = O(N)
    // We will check every element with its previous element
    // if the previous element is smaller than or equal to the current element then we will move to the next index.
    public static boolean arraySortedOrNot(List<Integer> arr) {
        for(int i=1;i<arr.size();i++)
        {
            if(arr.get(i-1) > arr.get(i))
                return false;
        }
        return true;
    }

    // main function
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        ArrayList<Integer> nums = new ArrayList<>();
        for(int i=0; i<n; i++)
        {
            nums.add(s.nextInt());
        }
        System.out.println(arraySortedOrNot(nums));
    }
}
