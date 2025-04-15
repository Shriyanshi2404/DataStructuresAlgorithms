package com.data.structures.algorithms.Arrays.Medium;

import java.util.Scanner;

public class RotateImageBy90degree {

    /*
     Brute Force approach
     T(n) = O(N^2)
     Take another dummy matrix of n*m
     then take the first row of the matrix and put it in the last column of the dummy matrix, take the second row of the matrix, and put it in the second last column of the matrix and so.
     */
    public static int[][] rotateBruteForce(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] arr = new int[n][m];

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                arr[j][n-1-i] = matrix[i][j];
            }
        }
        return arr;
    }

    /*
     Optimal Approach
     T(n) = O(N)
     Step 1: Transpose the matrix. (transposing means changing columns to rows and rows to columns)
     Step 2: Reverse each row of the matrix.
    */
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        // Transpose of matrix
        // we have to traverse only right side of matrix, as diagonal elements will be in same position
        for(int i=0;i<=n-2;i++)
        {
            for(int j=i;j<=n-1;j++)
            {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Reverse each row of matrix
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n/2;j++)
            {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n-1-j];
                matrix[i][n-1-j] = temp;
            }
        }
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
        rotate(matrix);
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
