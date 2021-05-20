package ds.sorting;

import ds.Util;
import java.util.Arrays;
import javafx.util.Pair;

public class MergeSort {

  public static void merge(int[] a, int low, int mid, int high) {
    int[] sorted = new int[high+1-low];
    int i = low;
    int j = mid;
    int k=0;
    for(;i < mid+1 && j < high; k++) {
      if(a[i] < a[j+1]) {
        sorted[k] = a[i];
        i++;
      } else {
        sorted[k] = a[j+1];
        j++;
      }
    }
    for(;i<mid+1;k++) {
      sorted[k] = a[i];
      i++;
    }
    for(;j < high;k++) {
      sorted[k] = a[j+1];
      j++;
    }

    for (int value : sorted) {
      a[low++] = value;
    }

  }
  public static void mergeSort(int[] a, int i, int j) {
    if(a == null || i>=j || a.length <= i) {
      return;
    }
    int mid = (i+j)/2;
    mergeSort(a,i,mid);
    mergeSort(a,mid+1,j);
    merge(a, i, mid, j);

  }

  public static void sort(int[] a) {
    mergeSort(a, 0, a.length-1);
  }


  public static void main(final String[] args) {
    int[] original = {53, 99, 4, 94, 88, 31, 10};
    System.out.println("Original: " + Arrays.toString(original));
    MergeSort.sort(original);
    System.out.println("Sorted: "+ Arrays.toString(original));
    System.out.println("isSorted?: "+ Util.isSorted(original));

  }

}
