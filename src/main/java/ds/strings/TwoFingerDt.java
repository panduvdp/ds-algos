package ds.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TwoFingerDt {
  static Map<Character, List<Integer>> lCharLocations = new HashMap<>();
  static Map<Character, List<Integer>> rCharLocations = new HashMap<>();
  static Map<Set<Character>, Integer> distances = new HashMap<>();

  public static void calculateDts() {

    int x = 0;
    int y = 0;

    for (char c = 'A'; c <= 'Z'; c++) {
      x = (c - 'A') / 6;
      y = (c - 'A') % 6;
      List<Integer> loc = new ArrayList<>();
      loc.add(x);
      loc.add(y);

      if (y < 3) {
        calculate(c, loc, lCharLocations, distances);
        lCharLocations.put(c, loc);
      } else {
        calculate(c, loc, rCharLocations, distances);
        rCharLocations.put(c, loc);
      }
    }

    System.out.println(lCharLocations);
    System.out.println(rCharLocations);

    System.out.println(distances);
  }

  private static void calculate(
      char c,
      List<Integer> loc,
      Map<Character, List<Integer>> charLoc,
      Map<Set<Character>, Integer> distances) {
    for (char ch : charLoc.keySet()) {
      List<Integer> loc1 = charLoc.get(ch);
      Integer dt = Math.abs(loc1.get(0) - loc.get(0)) + Math.abs(loc1.get(1) - loc.get(1));
      Set<Character> chars = new HashSet<>();
      chars.add(c);
      chars.add(ch);
      distances.put(chars, dt);
    }
  }

  private static int getDistance(List<Integer> loc, List<Integer> loc1) {
    return Math.abs(loc1.get(0) - loc.get(0)) + Math.abs(loc1.get(1) - loc.get(1));
  }

  public static int minimumDistanceWithPreCalculated(String word) {
    int ld = 0;
    Character prevLchar = null;
    Character prevRchar = null;
    int rd = 0;
    for (char c : word.toCharArray()) {

      if (lCharLocations.containsKey(c)) {
        if (prevLchar != null) {
          Set<Character> newPair = new HashSet<>();
          newPair.add(c);
          newPair.add(prevLchar);
          ld += distances.getOrDefault(newPair, 0);
        }
        prevLchar = c;
      } else {
        if (prevRchar != null) {
          Set<Character> newPair = new HashSet<>();
          newPair.add(c);
          newPair.add(prevRchar);
          rd += distances.getOrDefault(newPair, 0);
        }
        prevRchar = c;
      }
    }
    return ld + rd;
  }

  public static void main(String[] args) {
    String s =
        "aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga";
    // System.out.println(validPalindrome(s));
    // calculateDts();
    System.out.println(minimumDistance("JDX"));
  }

  public static int minimumDistance(String word) {
    Map<Character, List<Integer>> lCharLocations = new HashMap<>();
    Map<Character, List<Integer>> rCharLocations = new HashMap<>();
    int distance = 0;
    Character prevLchar = null;
    Character prevRchar = null;
    for (char c : word.toCharArray()) {
      int x = (c - 'A') / 6;
      int y = (c - 'A') % 6;
      List<Integer> loc = new ArrayList<>();
      loc.add(x);
      loc.add(y);

      if (y < 3) {
        lCharLocations.put(c, loc);
        if (prevLchar != null) {
          distance += getDistance(loc, lCharLocations.get(prevLchar));
        }
        prevLchar = c;

      } else {
        rCharLocations.put(c, loc);
        if (prevRchar != null) {
          distance += getDistance(loc, rCharLocations.get(prevRchar));
        }
        prevRchar = c;
      }
    }
    return distance;
  }

  public List<String> fullJustify(String[] words, int maxWidth) {
    int left = 0;
    List<String> result = new ArrayList<>();
    int curW = 0;
    int linW = 0;
    List<String> wordsPerLine = new ArrayList<>();

    while (left < words.length) {
      if (words[left].length() + curW < maxWidth) {
        wordsPerLine.add(words[left]);
        curW = words[left].length() + curW + 1;
        linW += words[left].length();
      } else {
        addToResult(wordsPerLine, maxWidth, linW);
        curW = 0;
        linW = 0;
      }
      //      int right = findRight(left, words, maxWidth);
      //      result.add(justify(left, right, words, maxWidth));
      //      left = right + 1;
    }

    return result;
  }

  public String addToResult(List<String> wordsPerLine, int maxWidth, int linW) {
    int remainingSpace = maxWidth - linW;
    int maxSpots = wordsPerLine.size() - 1;
    if (maxSpots == 0) {
      maxSpots++;
    }
    // devide remainingSpaces to the maxSpots equally
    int[] spacesPerSpot = new int[maxSpots];
    return "";
  }

  private void devidespaces(int[] spacesPerSpot, int pos, int maxSpots, int remainingSpace) {}
}
