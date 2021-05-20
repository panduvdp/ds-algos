package ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

  public static void main(String[] args) {
    Set<int[]> test = new HashSet<>();
    int[] a = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
    int[] b = new int[] {1, 2};
    test.add(a);
    System.out.println(test.contains(b));
    test.add(b);
    Set<List<Integer>> testList = new HashSet<>();
    testList.add(Arrays.asList(1, 2));
    testList.add(Arrays.asList(1, 2));
    System.out.println(test);
    System.out.println(testList);
    ArrayList<Integer> list1 = new ArrayList<>();
    list1.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
    System.out.println(list1);
    int l = list1.size();
    for (int i = 0; i < l; i++) {
      if (i % 2 == 1) {
        list1.set(i, 0);
      }
    }
    System.out.println(list1);
  }
}
