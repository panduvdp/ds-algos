package ds.arrays;

import java.util.Stack;
import test.Solution;

/**
 * Given an unsorted integer array, find the smallest missing positive integer.
 * https://leetcode.com/problems/first-missing-positive/
 */
public class FirstMissingPositive {
  public static void swap( int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;

  }
  public static int find1stMissingPositive(int[] A) {
    int maxIndex = A.length-1;
    int i=0;
    while( i <= maxIndex) {
      int val = A[i];
      if(val == i+1) {
        i++;
        continue;
      }
      //if the values is negative or greater than size of maxIndex or duplicated move it to the right side
      if(val<1 || val > maxIndex+1 || A[val-1] == val){
        swap(A, i, maxIndex);
        maxIndex--;
      } else {
        swap(A, i, val-1);
      }
    }

    return i+1;
  }
  public static void main(String[] args) {

    int[] a = new int[] {2,1};
    System.out.println(find1stMissingPositive(a));
  }


}
