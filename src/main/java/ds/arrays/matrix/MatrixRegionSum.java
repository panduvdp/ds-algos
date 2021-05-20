package ds.arrays.matrix;
// rippling
// package whatever; // don't place package name!

// Input 1: Which is a 2D array. Dimensions are M*N
// Input 2: r

// Output: A 2D array with sum of all elements in each region.

// input = [
//   [1,1,1,1,1],
//   [1,1,1,1,1],
//   [1,1,1,1,1],
//   [1,1,1,1,1],
// ]

// When r = 1
// output = [
//   [4,6,6,6,4],
//   [6,9,9,9,6],
//   [6,9,9,9,6],
//   [4,6,6,6,4]
// ]

// When r = 2
// output = [
// 	[9, 12, 15, 12, 9],
// 	[12, 16, 20, 16, 12],
// 	[12, 16, 20, 16, 12],
// 	[9, 12, 15, 12, 9]
// ]

import java.util.Arrays;

public class MatrixRegionSum {
  public static void calculateRegionSum(
      final int[][] input, final int[][] output, final int r, final int i, final int j) {

    /**
     * Calculate sum regino for [0][0] element first and then for every position i) in the same row,
     * subtract 1st column sum of previous position and add new column sum to the previous region
     * total. ii) in the same column, subtract 1st row sum of previous position and add new bew row
     * sum to the previous region total.
     */
    final int firstColumn = j - r - 1; // 1st col of prev. region
    final int nextColumn = j + r; // last col of current region
    final int firstRow = i - r - 1; // 1st row of prev region
    final int nextRow = i + r; // last row of current region
    int sumToDeduct = 0;
    int sumToAdd = 0;

    if (j != 0) {
      for (int row = i - r; row <= i + r; row++) {
        if (!(row < 0 || row >= input.length)) {

          if (!(firstColumn < 0 || firstColumn >= input[0].length)) {
            sumToDeduct += input[row][firstColumn];
          }
          if (!(nextColumn < 0 || nextColumn >= input[0].length)) {
            sumToAdd += input[row][nextColumn];
          }
        }
      }
      output[i][j] = output[i][j - 1] + sumToAdd - sumToDeduct;
    } else if (i != 0) {
      for (int col = j - r; col <= j + r; col++) {
        if (!(col < 0 || col >= input.length)) {

          if (!(firstRow < 0 || firstRow >= input.length)) {
            sumToDeduct += input[firstRow][col];
          }
          if (!(nextRow < 0 || nextRow >= input.length)) {
            sumToAdd += input[nextRow][col];
          }
        }
      }
      output[i][j] = output[i - 1][j] + sumToAdd - sumToDeduct;

    } else {
      int sum = 0;
      for (int row = i - r; row <= i + r; row++) {
        for (int col = j - r; col <= j + r; col++) {
          if (row < 0 || row >= input.length || col < 0 || col >= input[0].length) {
            continue;
          }
          sum += input[row][col];
        }
      }
      output[i][j] = sum;
    }
  }

  public static int[][] getRegionSumArray(final int[][] input, final int r) {

    if (input == null || input.length == 0) {
      return null;
    }
    final int[][] output = new int[input.length][input[0].length];

    for (int i = 0; i < input.length; i++) {
      for (int j = 0; j < input[0].length; j++) {
        calculateRegionSum(input, output, r, i, j);
      }
    }

    return output;
  }

  public static void main(final String[] args) {
    final int[][] input = new int[4][5];

    for (int i = 0; i < input.length; i++) {
      Arrays.fill(input[i], 1);
    }

    final int[][] output = getRegionSumArray(input, 2);
    for (int i = 0; i < input.length; i++) {
      for (int j = 0; j < input[0].length; j++) {
        System.out.print(output[i][j] + " ");
      }
      System.out.println(" ");
    }
  }
}
