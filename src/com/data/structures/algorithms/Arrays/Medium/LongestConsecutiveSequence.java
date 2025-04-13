package com.data.structures.algorithms.Arrays.Medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class LongestConsecutiveSequence {

    /*
    Brute force approach
    T(N) = O(N^2)
    Idea: For every element in an array say 'x' we will try to find out the next consecutive element i.e. (x+1) in rest of the array and if found then find (x+2), (x+3).. so on
    1. Define variables to store max length as 1
    2. Traverse through the array from i=0 to n-1
    3. during traversal, store current element as 'x' and currentLength as 1
    4. Now, through inner while loop do linear search on array and try to find out next element i.e. (x+1)
       - if (x+1) is found in an array
       - then increment the current length by 1
       - also increment x by x+1
       - and run this inner loop until you are able to find next consecutive elements in an array
    5. After coming out from while loop, compare maxLength with currentLength and update it
    6. return maxLength
    */
    public static int lengthOfLongestConsecutiveSequenceBruteForce(int[] arr, int N) {
        int maxLength = 1;
        for(int i=0;i<N;i++)
        {
            int x = arr[i];
            int count = 1;
            while(linearSearch(arr, x+1) == true)
            {
                x = x+1;
                count++;
            }
            maxLength = Math.max(maxLength, count);
        }
        return maxLength;
    }
    public static boolean linearSearch(int[] nums, int x)
    {
        for(int ele : nums)
        {
            if(ele == x)
                return true;
        }
        return false;
    }

    // Better approach
    // T(n) = O(N*logN) + O(N)
    public static int longestConsecutiveBetterApproach(int[] nums) {

        int count=0, longest=1;
        int lastSmaller = Integer.MIN_VALUE;

        if(nums.length == 0)
            return 0;

        Arrays.sort(nums);

        for(int i=0;i<nums.length;i++)
        {
            // If arr[i]-1 == lastSmaller:
            // The last element in our sequence is labeled as 'lastSmaller' and the current element 'arr[i]' is exactly 'lastSmaller'+1.
            // It indicates that 'arr[i]' is the next consecutive element.
            // we update 'lastSmaller' with the value of 'arr[i]' and increment the length of the current sequence, denoted as 'count', by 1.
            if(nums[i]-1 == lastSmaller)
            {
                lastSmaller = nums[i];
                count++;
            }

            // if lastSmaller != arr[i]:
            // we can conclude that the current element, arr[i] > lastSmaller+1.
            // It indicates that arr[i] cannot be a part of the current sequence.
            // So, we will start a new sequence from arr[i] by updating ‘lastSmaller’ with the value of arr[i].
            // And we will set the length of the current sequence(cnt) to 1.
            else if(nums[i] != lastSmaller)
            {
                lastSmaller = nums[i];
                count = 1;
            }

            // If arr[i] == lastSmaller: If the current element, arr[i], matches the last element of the sequence (represented by 'lastSmaller'),
            // we can skip it since we have already included it before.
            else if(nums[i] == lastSmaller)
                continue;

            longest = Math.max(count, longest);
        }
        return longest;
    }

    /*
    Optimal approach
    T(N) = O(N)  S(N) = O(N)
    Idea: To remove duplicates elements we will use hashset to store elements of array
    1. Define variables to store max length as 1 and count as 0 and hashset
    2. Traverse through the array from i=0 to n-1 and store elements into hashset
    3. Now, do traversal on set and check for below two conditions
       - if (x-1) exists in set, then 'x' can't be starting point, so continue the traversal
       - if (x-1) does not exists in set, then 'x' is the starting point, find next consecutive elements
            - keep count as 1
            - run a while loop until you find (x+1) element exists in set
            - and everytime keep incrementing count by 1 and 'x' by x+1
    4. once while loop finishes, mean you have found the length of consecutive elements
    5. compare the maxLength with current length
    6. return maxLength
    */
    public static int lengthOfLongestConsecutiveSequence(int[] arr, int N) {
        HashSet<Integer> set = new HashSet<>();
        int count=0;
        int maxLength = 1;

        for(int ele : arr)
        {
            set.add(ele);
        }

        for(int x : set)
        {
            // to check if 'x' is starting element of the sequence
            if(!set.contains(x-1))
            {
                count = 1;
                // find next consecutive elements
                // it will run until we keep finding. like x+1, x+2 ...
                while(set.contains(x+1))
                {
                    x = x+1;
                    count++;
                }
                // compare length of current seq. and maxLength so far stored in longest
                maxLength = Math.max(maxLength, count);
            }
        }
        return maxLength;
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
        System.out.println(lengthOfLongestConsecutiveSequence(nums, n));
    }
}
