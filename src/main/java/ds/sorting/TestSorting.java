package ds.sorting;

import ds.Util;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestSorting {

  /**
   * https://leetcode.com/problems/longest-substring-without-repeating-character
   * @param s given string
   * @return
   */
  public int lengthOfLongestSubstring(String s) {
    if(s == null || s.length() == 0) {
      return 0;
    }
    Map<Character, Integer> charMap =  new HashMap<>();
    int maxL=0, currentL=0, indexStart=0;
    for(int i = 0; i < s.length(); i++) {
      if(charMap.containsKey(s.charAt(i)) && indexStart <= charMap.get(s.charAt(i))) {
          if (maxL < currentL) {
            maxL = currentL;
          }
          indexStart = charMap.get(s.charAt(i)) + 1;
          currentL = i - indexStart ;
      } else {
        currentL++;
      }
      charMap.put(s.charAt(i), i);
    }
    return Math.max(maxL, currentL);
  }

  public static void main(final String[] args) {

    int[] original = Util.generateRandomArray(15, 1000);
    System.out.println("Original: " + Arrays.toString(original));
    //HeapSort.sort(original);
    //QuickSort.sort(original);
    MergeSort.sort(original);
    System.out.println("Sorted: "+ Arrays.toString(original));
    System.out.println("isSorted?: "+Util.isSorted(original));
  }

}
