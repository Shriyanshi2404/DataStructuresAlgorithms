package com.data.structures.algorithms.Arrays.Easy;

import java.util.*;

public class FindTheUnion {


    // Brute Force approach
    // T(n) = O((M+N) * log(M+N)) S(n) = O(N+M)
    // using hashmap, we will store the common and distinct elements of both arrays
    // at last traverse through map, and add each key of map as an element in result array list
    public static ArrayList<Integer> findUnionBruteForce(int arr1[], int arr2[], int n, int m)
    {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int i=0; i<n; i++)
        {
            int ele = arr1[i];
            map.put(ele, map.getOrDefault(ele, 0)+1);
        }

        for(int i=0; i<m; i++)
        {
            int ele = arr2[i];
            map.put(ele, map.getOrDefault(ele, 0)+1);
        }

        ArrayList<Integer> arr = new ArrayList<>();
        for(Map.Entry<Integer, Integer> mp : map.entrySet())
        {
            arr.add(mp.getKey());
        }

        return arr;
    }

    // Better approach
    // T(n) = O((M+N) * log(M+N)) S(n) = O(N+M)
    // using hashset, which stores only distict elements without any duplicate
    public static ArrayList<Integer> findUnionBetterApproach(int arr1[], int arr2[], int n, int m)
    {
        HashSet<Integer> s=new HashSet<>();
        ArrayList < Integer > Union=new ArrayList<>();
        for(int i = 0; i < n; i++)
            s.add(arr1[i]);
        for(int i = 0; i < m; i++)
            s.add(arr2[i]);

        for(int it: s)
            Union.add(it);

        return Union;
    }

    // Optimal approach (2-pointer)
    // T(N) = O(M+N)
    // NOTE: There may be cases like the element to be inserted is already present in the union, in that case, we are inserting duplicates which is not desired.
    // So while inserting always check whether the last element in the union arrayList is equal or not to the element to be inserted.
    // If equal we are trying to insert duplicates, so don’t insert the element, else insert the element in the union.
    // This makes sure that we are not inserting any duplicates in the union because we are inserting elements in sorted order.
    public static ArrayList<Integer> findUnion(int arr1[], int arr2[], int n, int m)
    {
        ArrayList<Integer> Union = new ArrayList<>();
        int i=0,j=0;

        while(i<n && j<m)
        {
            if(arr1[i] <= arr2[j])
            {
                if(Union.size()==0 || Union.get(Union.size()-1)!=arr1[i])
                    Union.add(arr1[i]);
                i++;
            }
            else if(arr1[i] > arr2[j])
            {
                if(Union.size()==0 || Union.get(Union.size()-1)!=arr2[j])
                    Union.add(arr2[j]);
                j++;
            }
        }

        while(i<n)
        {
            if(Union.size()==0 || Union.get(Union.size()-1)!=arr1[i])
                Union.add(arr1[i]);
            i++;
        }

        while(j<m)
        {
            if(Union.size()==0 || Union.get(Union.size()-1)!=arr2[j])
                Union.add(arr2[j]);
            j++;
        }
        return Union;
    }

    // main function
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int arr1[] = new int[n];
        for(int i=0; i<n; i++)
        {
            arr1[i] = s.nextInt();
        }

        int m = s.nextInt();
        int arr2[] = new int[m];
        for(int i=0; i<m; i++)
        {
            arr2[i] = s.nextInt();
        }

        ArrayList<Integer> arr = findUnion(arr1, arr2, n, m);
        for(int ele : arr)
        {
            System.out.print(ele + " ");
        }
    }
}
