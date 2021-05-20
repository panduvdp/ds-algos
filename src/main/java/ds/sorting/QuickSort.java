package ds.sorting;

import static ds.Util.swapElems;

import ds.Util;
import java.util.Arrays;

public class QuickSort {
  public static void quickSort(int[] a, int i, int j) {
    if(a == null || i>=j || a.length <= i) {
      return;
    }

    int low = i;
    int high = j;
    int pivot = a[i];

    while(low < high) {
      while (low <= high && pivot >= a[low])
        low++;
      while(i < high && pivot < a[high] )
        high--;
      if(low < high)
        swapElems(a, low, high);
    }
    a[i] = a[high];
    a[high] = pivot;
    quickSort(a, i, high-1);
    quickSort(a, high+1, j);

  }

  public static void sort(int[] a) {
    quickSort(a, 0, a.length-1);
  }

  public static void main(final String[] args) {
    int[] original = {53, 99, 4, 94, 88, 31, 10};
    System.out.println("Original: " + Arrays.toString(original));
    QuickSort.sort(original);
    System.out.println("Sorted: "+ Arrays.toString(original));
    System.out.println("isSorted?: "+ Util.isSorted(original));

  }


}
