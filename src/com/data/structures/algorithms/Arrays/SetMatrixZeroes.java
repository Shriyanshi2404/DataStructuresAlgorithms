package com.data.structures.algorithms.Arrays;

import java.util.Scanner;

import static com.data.structures.algorithms.Arrays.LongestConsecutiveSequence.longestConsecutive;

public class SetMatrixZeroes {

    // Brute force approach
    // T(n) = O(N^3)
    // Idea: marking cell of ith row and jth row with value -1 and then traversing again to mark value -1 to 0
    public static void setRow(int[][] matrix, int i)
    {
        int m=matrix[0].length;

        // set all non-zero elements as -1 in the row i:
        for(int j=0;j<m;j++)
        {
            if(matrix[i][j] != 0)
                matrix[i][j] = -1;
        }
    }

    public static void setColumn(int[][] matrix, int j)
    {
        int n=matrix.length;

        // set all non-zero elements as -1 in the col j:
        for(int i=0;i<n;i++)
        {
            if(matrix[i][j] != 0)
                matrix[i][j] = -1;
        }
    }

    public static void setZeroes(int[][] matrix)
    {

        int n=matrix.length;
        int m=matrix[0].length;

        // Set -1 for rows and cols that contains 0. Don't mark any 0 as -1:
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(matrix[i][j] == 0)
                {
                    setRow(matrix, i);
                    setColumn(matrix, j);
                }
            }
        }

        //  Finally, mark all -1 as 0:
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(matrix[i][j] == -1)
                {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    // Better approach
    // T(n) = O(N^2)
    // Idea: using two arrays i.e. row and column array
    public static void setZeroesBetterApproach(int[][] matrix) {

        int n=matrix.length;
        int m=matrix[0].length;

        int[] row = new int[n]; // row array
        int[] col = new int[m]; // col array

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(matrix[i][j] == 0)
                {
                    // mark ith index of row wih 1:
                    row[i] = 1;
                    // mark jth index of col wih 1:
                    col[j] = 1;
                }
            }
        }

        // Finally, mark all (i, j) as 0
        // if row[i] or col[j] is marked with 1.
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(row[i] == 1 || col[j] == 1)
                {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    // Optimal approach
    // T(n) = O(N)
    public static void setZeroesOptimalApproach(int[][] matrix) {

        int n=matrix.length;
        int m=matrix[0].length;

        // int[] row = new int[n]; // row array --> matrix[..][0]
        // int[] col = new int[m]; // col array --> matrix[0][..]
        int col0 = 1;

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(matrix[i][j] == 0)
                {
                    // mark i-th row:
                    matrix[i][0] = 0;

                    // mark j-th column:
                    if (j != 0)
                        matrix[0][j] = 0;
                    else
                        col0 = 0;
                }
            }
        }

        // Step 2: Mark with 0 from (1,1) to (n-1, m-1):
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] != 0)
                {
                    // check for col & row:
                    if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    {
                        matrix[i][j] = 0;
                    }
                }
            }
        }

        //step 3: Finally mark the 1st col & then 1st row:
        if (matrix[0][0] == 0)
        {
            for(int j = 0; j < m; j++)
            {
                matrix[0][j] = 0;
            }
        }
        if (col0 == 0)
        {
            for(int i = 0; i < n; i++)
            {
                matrix[i][0] = 0;
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
        setZeroes(matrix);
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
