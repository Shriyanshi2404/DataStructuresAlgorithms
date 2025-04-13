package com.data.structures.algorithms.Arrays.Medium;

import java.util.Scanner;

public class SetMatrixZeroes {

    /*
     Brute force approach
     T(n) = O((N*M) * (N+M)) + O(N*M) S(n) = O(1)
     Idea: marking cell of ith row and jth row with value -1 and then traversing again to mark value -1 to 0
     1. First, we will use two loops(nested loops) to traverse all the cells of the matrix.
     2. If any cell (i,j) contains the value 0, we will mark all cells in row i and column j with -1 except those which contain 0.
     3. We will perform step 2 for every cell containing 0.
     4. Finally, we will mark all the cells containing -1 with 0.
     5. Thus, the given matrix will be modified according to the question.
     */
    public void setRow(int[][] matrix, int row)
    {
        // set all non-zero elements as -1 in the given row
        for(int j=0;j<matrix[0].length;j++)
        {
            if(matrix[row][j] != 0)
                matrix[row][j] = -1;
        }
    }

    public void setCol(int[][] matrix, int col)
    {
        // set all non-zero elements as -1 in the given column
        for(int i=0;i<matrix.length;i++)
        {
            if(matrix[i][col] != 0)
                matrix[i][col] = -1;
        }
    }

    public void setZeroes(int[][] matrix) {
        // Set -1 for rows and cols that contains 0. Don't mark any 0 as -1:
        for(int i=0;i<matrix.length;i++)
        {
            for(int j=0;j<matrix[0].length;j++)
            {
                if(matrix[i][j] == 0)
                {
                    setRow(matrix, i);
                    setCol(matrix, j);
                }
            }
        }
        //  Finally, mark all -1 as 0:
        for(int i=0;i<matrix.length;i++)
        {
            for(int j=0;j<matrix[0].length;j++)
            {
                if(matrix[i][j] == -1)
                    matrix[i][j] = 0;
            }
        }
    }

    /*
    Better Approach (Using two extra arrays) i.e. row and column array
    T(n) = O(2*(N*M)) S(n) = O(N)+O(M)
    1. First, we will declare two arrays: a row array of size N and a col array of size M and both are initialized with 0.
    2. Then, we will use two loops(nested loops) to traverse all the cells of the matrix.
    3. If any cell (i,j) contains the value 0, we will mark ith index of row array i.e. row[i] and jth index of col array col[j] as 1.
       - It signifies that all the elements in the ith row and jth column will be 0 in the final matrix.
    4. We will perform step 3 for every cell containing 0.
    5. Finally, we will again traverse the entire matrix and we will put 0 into all the cells (i, j) for which either row[i] or col[j] is marked as 1.
    6. Thus we will get our final matrix.
    */
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

    /*
    Optimal approach
    T(n) = O(N*M)
    1. First, we will traverse the matrix and mark the proper cells of 1st row and 1st column with 0 accordingly.
       - The marking will be like this: if cell(i, j) contains 0,
       - we will mark the i-th row i.e. matrix[i][0] with 0
       - and we will mark j-th column i.e. matrix[0][j] with 0.
    2. If i is 0, we will mark matrix[0][0] with 0 but if j is 0, we will mark the col0 variable with 0 instead of marking matrix[0][0] again.
    3. After step 1 and 2 is completed, we will modify the cells from (1,1) to (n-1, m-1) using the values from the 1st row, 1st column, and col0 variable.
    4. We will not modify the 1st row and 1st column of the matrix here as the modification of the rest of the matrix(i.e. From (1,1) to (n-1, m-1)) is dependent on that row and column.
       - Finally, we will change the 1st row and column using the values from matrix[0][0] and col0 variable.
       - Here also we will change the row first and then the column.
    5. If matrix[0][0] = 0, we will change all the elements from the cell (0,1) to (0, m-1), to 0.
    6. If col0 = 0, we will change all the elements from the cell (0,0) to (n-1, 0), to 0.
    */
    public static void setZeroesOptimalApproach(int[][] matrix) {
        // int[] row = new int[n]; // row array --> matrix[...][0]
        // int[] col = new int[m]; // col array --> matrix[0][...]

        int col0 = 1;
        for(int i=0;i<matrix.length;i++)
        {
            for(int j=0;j<matrix[0].length;j++)
            {
                if(matrix[i][j] == 0)
                {
                    // mark ith row
                    matrix[i][0] = 0;

                    // mark jth row
                    if(j!=0)
                        matrix[0][j] = 0;
                    else
                        col0 = 0;
                }
            }
        }

        int n = matrix.length;
        int m = matrix[0].length;

        // Step 2: Mark with 0 from (1,1) to (n-1, m-1):
        for(int i=n-1; i>0; i--)
        {
            for(int j=m-1; j>0; j--)
            {
                if(matrix[i][j] != 0)
                {
                    // check for row and col
                    if(matrix[i][0] == 0 || matrix[0][j] == 0)
                        matrix[i][j] = 0;
                }
            }
        }

        //step 3: Finally mark the 1st col & then 1st row:
        if(matrix[0][0] == 0)
        {
            // mark 1st row elements as 0
            for(int j=0; j<m; j++)
                matrix[0][j] = 0;
        }

        if(col0 == 0)
        {
            // mark 1st column elements as 0
            for(int i=0; i<n; i++)
                matrix[i][0] = 0;
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
        setZeroesOptimalApproach(matrix);
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
