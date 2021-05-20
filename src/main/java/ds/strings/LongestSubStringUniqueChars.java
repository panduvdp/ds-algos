package ds.strings;

import java.util.HashMap;
import java.util.Map;

public class LongestSubStringUniqueChars {
  public static long solution(final String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    int maxLength = 0, curStart = 0, currentLength = 0;
    final Map<Character, Integer> charIndexMap = new HashMap<>();

    for (int current = 0; current < s.length(); current++) {
      final Character c = s.charAt(current);
      // Increment length if we see the current character 1st time or the currentLength doesn't
      // contain the current character
      if (!charIndexMap.containsKey(c) || curStart > charIndexMap.get(c)) {
        currentLength++;
      } else {
        // before resetting, update maxLength if currentLength exceeds the previous maxLength
        if (currentLength > maxLength) {
          maxLength = currentLength;
        }
        // update counters accordingly
        currentLength = current - charIndexMap.get(c);
        curStart = charIndexMap.get(c) + 1;
      }
      charIndexMap.put(c, current);
    }
    return Math.max(currentLength, maxLength);
  }

  public static void main(final String[] args) {
    System.out.println(solution("nndNfdfdf"));
  }
}
