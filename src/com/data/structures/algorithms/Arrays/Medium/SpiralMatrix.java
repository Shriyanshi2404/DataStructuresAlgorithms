package com.data.structures.algorithms.Arrays.Medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpiralMatrix {

    // Optimal approch
    // T(n) = O(N*M)
    // In this approach, we will be using four loops to print all four sides of the matrix.
    //1st loop: This will print the elements from left to right.
    //2nd loop: This will print the elements from top to bottom.
    //3rd loop: This will print the elements from right to left.
    //4th loop: This will print the elements from bottom to top.
    public static List<Integer> spiralOrder(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        ArrayList<Integer> list = new ArrayList<>();

        int right=m-1, bottom=n-1, top=0, left=0;

        while(top<=bottom && left<=right)
        {
            // printing towards right
            for(int i=left; i<=right; i++)
            {
                list.add(matrix[top][i]);
            }
            top++;

            // printing towards bottom
            for(int i=top; i<=bottom; i++)
            {
                list.add(matrix[i][right]);
            }
            right--;

            // two edge conditions are being added in the last two ‘for’ loops:
            // when we are moving from right to left and from bottom to top.
            // These conditions are added to check if the matrix is a single column or a single row

            // whenever the elements in a single row are traversed they cannot be traversed again backward so the condition is checked in the right-to-left loop.
            if(top<=bottom)
            {
                // printing towards left
                for(int i=right; i>=left; i--)
                {
                    list.add(matrix[bottom][i]);
                }
                bottom--;
            }

            // When a single column is present, the condition is checked in the bottom-to-top loop as elements from bottom to top cannot be traversed again.
            if(left<=right)
            {
                // printing towards top
                for(int i=bottom; i>=top; i--)
                {
                    list.add(matrix[i][left]);
                }
                left++;
            }
        }
        return list;
    }
    // main function
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();
        int matrix[][] = new int[n][m];
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                matrix[i][j] = s.nextInt();
            }
        }
        List<Integer> list = spiralOrder(matrix);
        for(int ele : list)
        {
            System.out.print(ele +" ");
        }
    }
}
