package ds.sorting;

import static ds.Util.*;

import ds.Util;
import java.util.Arrays;

public class HeapSort {

  /**
   * Builds a Max heap in O(n) time
   *
   * @param a array of int elements to build heap out of
   * @param len denotes no. of elements to be considere to build the heap out of the given array
   */
  public static void buildMaxHeap(int[] a, int len) {
    if(len <= 1) {
      return;
    }
    for(int i = len/2 -1; i>-1; i--) {
      heapify(a, i, a.length);
    }

  }

  public static void heapify(int[] a, int start, int len) {
    int i = start;
    while (2*i+1 < len) {
      int maxElemIndex = 2*i+1;
      if (maxElemIndex+1 < len) {
        //Find max element in the children
        maxElemIndex = findMaxElemIndex(a, maxElemIndex, maxElemIndex+1);
      }
      maxElemIndex = findMaxElemIndex(a, maxElemIndex, i);
      //Swap it with max element in the children only when the parent has lesser value
      if (i != maxElemIndex ) {
        swapElems(a, i, maxElemIndex);
        i = maxElemIndex;
      } else {
        break;
      }
    }
  }

  public static void sort(int[] a) {
    buildMaxHeap(a, a.length);
    System.out.println("Max Heap: "+ Arrays.toString(a));
    for(int i = a.length-1; i > -1; i--) {
      swapElems(a, 0, i);
      heapify(a, 0, i);
    }

  }

  public static void main(final String[] args) {
    int[] original = {53, 99, 4, 94, 88, 31, 10};
    System.out.println("Original: " + Arrays.toString(original));
    HeapSort.sort(original);
    System.out.println("Sorted: "+ Arrays.toString(original));
    System.out.println("isSorted?: "+ Util.isSorted(original));

  }
}
