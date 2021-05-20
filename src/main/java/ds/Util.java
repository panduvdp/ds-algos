package ds;

import java.util.Arrays;

public class Util {

  public static int findMaxElemIndex(int[] a , int i, int j) {
    int maxLength = a.length -1;
    if(maxLength < i && maxLength< j)
      return -1;
    if(maxLength < i)
      return j;
    if(maxLength < j)
      return i;

    return a[i] > a[j] ? i : j;
  }

  public static void swapElems(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  public static int[] generateRandomArray(int size, int maxVal) {
    int[] result = new int[size];
    for(int i = 0; i < size; i++) {
      result[i] = (int) (Math.random() * maxVal);
    }
    return result;
  }

  public static boolean isSorted(int[] a) {
    int[] b = a.clone();
    Arrays.sort(b);
    return Arrays.equals(a,b);
  }

}
