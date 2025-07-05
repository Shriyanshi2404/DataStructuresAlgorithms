package com.data.structures.algorithms.Stack.Medium;


import java.util.Stack;

public class SumOfSubarraysMinimum {

    /**
     * Algorithm:
     * 1. We will iterate through each element of the array, initializing a sum variable to 0.
     * 2. We will run a loop from i=0 to n-1, where n is the length of the array.
     *    - Assign the current element as min.
     *    - Add the min to the sum.
     * 3. Now, run a nested loop from j=i+1 to n-1 for checking the subarray starting from i.
     *    - For each element, we will find the minimum in the subarray starting from that element and update if required.
     *    - We will add the minimum to the sum.
     * 4. Finally, we will return the total sum modulo (10^9 + 7).
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public static int sumSubarrayMins(int[] arr) {
        int sum = 0;
        int mod = (int) (1e9+7);
        for(int i=0; i<arr.length; i++)
        {
            int min = arr[i];
            sum = (sum+min)%mod;
            for(int j=i+1; j<arr.length; j++)
            {
                min = Math.min(arr[j], min);
                sum = (sum+min)%mod;
            }
        }
        return sum;
    }

    /**
     * Optimal Approach: contribution of each element as the minimum in all subarrays
     * Algorithm:
     * 1. We will use two arrays to store the indices of the next smaller element (NSE) and previous smaller element (PSE) for each element in the array.
     * 2. We will iterate through the array to fill the NSE and PSE arrays using a stack.
     * 3. The NSE array will store the index of the next smaller element for each element, and the PSE array will store the index of the previous smaller element.
     * 4. Traverse the array again to calculate the contribution of each element as the minimum in all subarrays:
     *   - Calculate the number of subarrays where arr[i] is the minimum and store as left count and right count.
     *   - For each element, we will calculate the number of subarrays where it is the minimum by multiplying the count of elements to its left (PSE) and the count of elements to its right (NSE).
     *   - We will multiply the count by the element itself and add it to the sum.
     * 5. Finally, we will return the total sum modulo (10^9 + 7).
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int sumSubarrayMinimum(int[] arr) {
        int n = arr.length;
        int[] NSE = new int[n]; // Next Smaller Element
        int[] PSE = new int[n]; // Previous Smaller Element

        findNSE(arr, NSE);
        findPSE(arr, PSE);

        int mod = (int) (1e9+7);
        long sum = 0;
        for (int i=0; i<n; i++)
        {
            long left = i - PSE[i];
            long right = NSE[i] - i;
            long cur = (left * right) % mod;
            cur = (cur * arr[i]) % mod;
            sum = (sum + cur) % mod;
        }
        return (int) sum;
    }

    public static void findNSE(int[] arr, int[] NSE)
    {
        Stack<Integer> st = new Stack<>();
        for(int i=arr.length-1; i>=0; i--)
        {
            int ele = arr[i];

            while(!st.isEmpty() && st.peek() >= ele)
                st.pop();
            if(st.isEmpty())
                NSE[i] = arr.length; // No smaller element to the right
            else
                NSE[i] = st.peek();
            st.push(ele);
        }
    }

    public static void findPSE(int[] arr, int[] PSE)
    {
        Stack<Integer> st = new Stack<>();
        for(int i=0; i<arr.length; i++)
        {
            int ele = arr[i];

            while(!st.isEmpty() && st.peek() >= ele)
                st.pop();

            if(st.isEmpty())
                PSE[i] = -1; // No smaller element to the left
            else
                PSE[i] = st.peek();
            st.push(ele);
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 4};
        int result = sumSubarrayMinimum(arr);
        System.out.println("Sum of subarray minimums: " + result); // Output: 17
    }
}
