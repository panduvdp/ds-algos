package ds;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Test {
  public static void main(final String[] args) {

    final Integer i = 200;
    //    System.out.println(Integer.MAX_VALUE);
    //    System.out.println(Integer.MIN_VALUE);
    //    System.out.println(i.byteValue());
    //    System.out.println(Integer.toBinaryString(i));
    //    System.out.println(Integer.toBinaryString(i).substring(0, 5));
    final String x = getOddCharCountString(46);
    System.out.println(x.length());
    System.out.println(x);
  }

  private static String getIntermediate(final int n) {
    final String complete = "abcdefghijklmnopqrstuvwxyz";
    final StringBuilder builder = new StringBuilder();
    while (2 * builder.length() <= n) {
      if (builder.length() == 0) {
        builder.append(complete, 0, Math.min(26, n));
      } else {
        builder.append(builder);
      }
    }
    return builder.toString();
  }

  private static String getOddCharCountString(final int n) {
    final StringBuilder builder = new StringBuilder();
    int counter = 0;
    while (builder.length() < n) {
      if ((counter + 1) % 2 == 0) {
        builder.append(getIntermediate(n - builder.length()));
      }
      counter++;
    }
    return builder.toString();
  }

  public int solution(final int[] A, final int[] B, final int N) {

    final Map<Integer, Set<Integer>> cityNetwork = new HashMap<>();
    for (int i = 0; i < A.length; i++) {
      if (!cityNetwork.containsKey(A[i])) {
        final Set<Integer> connectedCities = new HashSet<>();
        cityNetwork.put(A[i], connectedCities);
      }

      if (!cityNetwork.containsKey(B[i])) {
        final Set<Integer> connectedCities = new HashSet<>();
        cityNetwork.put(B[i], connectedCities);
      }

      cityNetwork.get(A[i]).add(B[i]);
      cityNetwork.get(B[i]).add(A[i]);
    }

    int maxRank = 0;
    int currentCount = 0;

    for (final Integer city : cityNetwork.keySet()) {
      final Set<Integer> connectedCities = cityNetwork.get(city);
      currentCount += connectedCities.size();
      final int temp = currentCount;
      for (final Integer connectedCity : connectedCities) {
        if (cityNetwork.containsKey(connectedCity)) {
          final Set<Integer> secondLevel = cityNetwork.get(connectedCity);
          currentCount += secondLevel.size();
          currentCount -= 1;
        }
        if (maxRank < currentCount) {
          maxRank = currentCount;
        }
        currentCount = temp;
      }

      currentCount = 0;
    }
    return maxRank;
  }
}
