package ds.arrays;

import com.google.common.collect.ImmutableMap;
import java.util.PriorityQueue;

public class MaxSumNonOverlap {
  public static void main(final String[] args) {
    final int[] a =
        new int[] {4, 5, 14, 16, 16, 20, 7, 13, 8, 15}; // {8, 20, 6, 2, 20, 17, 6, 3, 20, 8, 12};
    System.out.println(maxSumTwoNoOverlap(a, 3, 5)); // 4,5
    ImmutableMap.builder();
  }

  public static int maxSumTwoNoOverlap(final int[] A, final int L, final int M) {
    final PriorityQueue<Elem> max1 = new PriorityQueue<>((c1, c2) -> c2.val - c1.val);
    final PriorityQueue<Elem> max2 = new PriorityQueue<>((c1, c2) -> c2.val - c1.val);
    int sum1 = 0, sum2 = 0;

    for (int i = 0; i < A.length; i++) {
      sum1 += A[i];
      sum2 += A[i];
      if (i >= L - 1) {
        sum1 = i > L - 1 ? sum1 - A[i - L] : sum1;
        max1.add(new Elem(i - L + 1, i, sum1));
      }
      if (i >= M - 1) {
        sum2 = i > M - 1 ? sum2 - A[i - M] : sum2;
        max2.add(new Elem(i - M + 1, i, sum2));
      }
    }

    final int max = -1;
    System.out.println(max2);
    System.out.println(max1);
    Elem current = max2.poll();
    final Elem temp = current;
    Elem other = max1.poll();

    System.out.println(other);

    while (!(current.i > other.j || current.j < other.i) && max2.size() > 0 && max1.size() > 0) {

      if (current.val + max1.peek().val > other.val + max2.peek().val) {
        other = max1.poll();
      } else {
        current = max2.poll();
      }
      //      temp = max2.poll();
      //      System.out.println("-- " + temp);
    }
    //    System.out.println(temp);
    //    max = Math.max(max, other.val + temp.val);
    //
    //    temp = other;
    //
    //    System.out.println(current);
    //    while (!(temp.i > current.j || temp.j < current.i) && max1.size() > 0) {
    //      temp = max1.poll();
    //      System.out.println("-- " + temp);
    //    }
    //    System.out.println(temp);
    //
    //    return Math.max(max, current.val + temp.val);

    return current.val + other.val;
  }

  static class Elem {
    int i;
    int j;
    int val;

    Elem(final int i1, final int j2, final int val1) {
      this.i = i1;
      this.j = j2;
      this.val = val1;
    }

    @Override
    public String toString() {
      return String.format("%d %d %d", this.i, this.j, this.val);
    }
  }
}
