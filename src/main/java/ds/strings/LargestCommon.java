package ds.strings;

import java.util.ArrayList;
import java.util.List;

public class LargestCommon {

  public static List<String> findLargestContiguousStrings(String[] user1, String[] user2) {
    int[][] maxLen = new int[user1.length + 1][user2.length + 1];
    int maxLength = 0;
    int endIdx = -1;
    for (int i = 1; i <= user1.length; i++) {
      for (int j = 1; j <= user2.length; j++) {
        if (user1[i - 1].equals(user2[j - 1])) {
          maxLen[i][j] = 1 + maxLen[i - 1][j - 1];
          if (maxLen[i][j] > maxLength) {
            maxLength = maxLen[i][j];
            endIdx = i - 1;
            ;
          }
        }
      }
    }

    List<String> result = new ArrayList<>();
    if (maxLength > 0) {
      for (int i = endIdx; i > endIdx - maxLength; i--) {
        result.add(0, user1[i]);
      }
    }
    return result;
  }

  public static List<String> findLargestSubSeqStrings(String[] user1, String[] user2) {
    int[][] maxLen = new int[user1.length + 1][user2.length + 1];
    for (int i = 1; i <= user1.length; i++) {
      for (int j = 1; j <= user2.length; j++) {
        if (user1[i - 1].equals(user2[j - 1])) {
          maxLen[i][j] = 1 + maxLen[i - 1][j - 1];
        } else {
          maxLen[i][j] = Math.max(maxLen[i - 1][j], maxLen[i][j - 1]);
        }
      }
    }

    int maxLength = maxLen[user1.length][user2.length];

    List<String> result = new ArrayList<>();
    int i = user1.length;
    int j = user2.length;
    while (maxLength != 0) {
      if (maxLength == maxLen[i - 1][j]) {
        i = i - 1;
      } else if (maxLength == maxLen[i][j - 1]) {
        j = j - 1;
      } else {
        result.add(0, user1[i - 1]);
        i = i - 1;
        j = j - 1;
        maxLength--;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    String[] user0 = {"/start", "/green", "/blue", "/pink", "/register", "/orange", "/one/two"};
    String[] user1 = {"/start", "/pink", "/register", "/orange", "/red", "a"};
    String[] user2 = {"a", "/one", "/two"};
    String[] user3 = {
      "/pink",
      "/orange",
      "/yellow",
      "/plum",
      "/blue",
      "/tan",
      "/red",
      "/amber",
      "/HotRodPink",
      "/CornflowerBlue",
      "/LightGoldenRodYellow",
      "/BritishRacingGreen"
    };
    String[] user4 = {
      "/pink",
      "/orange",
      "/amber",
      "/BritishRacingGreen",
      "/plum",
      "/blue",
      "/tan",
      "/red",
      "/lavender",
      "/HotRodPink",
      "/CornflowerBlue",
      "/LightGoldenRodYellow"
    };
    String[] user5 = {"a"};
    System.out.println(findLargestSubSeqStrings(user0, user1));
    System.out.println(findLargestSubSeqStrings(user0, user2));
    System.out.println(findLargestSubSeqStrings(user1, user2));

    System.out.println(findLargestSubSeqStrings(user5, user2));

    System.out.println(findLargestSubSeqStrings(user3, user4));

    System.out.println(findLargestSubSeqStrings(user4, user3));
  }
}
