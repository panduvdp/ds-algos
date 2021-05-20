package test;

/*

We are working on a security system for a badged-access room in our company's building.

We want to find employees who badged into our secured room unusually often. We have an unordered list of names and entry times over a single day. Access times are given as numbers up to four digits in length using 24-hour time, such as "800" or "2250".

Write a function that finds anyone who badged into the room three or more times in a one-hour period, and returns each time that they badged in during that period. (If there are multiple one-hour periods where this was true, just return the first one.)

badge_times = [
  ["Paul",     "1355"],
  ["Jennifer", "1910"],
  ["John",      "830"],
  ["Paul",     "1315"],
  ["John",     "1615"],
  ["John",     "1640"],
  ["John",      "835"],
  ["Paul",     "1405"],
  ["John",      "855"],
  ["John",      "930"],
  ["John",      "915"],
  ["John",      "730"],
  ["John",      "940"],
  ["Jennifer", "1335"],
  ["Jennifer",  "730"],
  ["John",     "1630"],
]

Expected output (in any order)
  John:  830  835  855  915  930
  Paul: 1315 1355 1405

n: length of the badge records array
p: number of people in the input

*/

import java.util.*;

public class WayfairKarat {

  public static Map<String, List<Integer>> printFrequentAccessor(String[][] badgeTimes) {
    Map<String, List<Integer>> frequentAccessors = new HashMap<>();
    if(badgeTimes == null || badgeTimes.length <1) {
      return frequentAccessors;
    }

    Map<String, List<Integer>> accessByEmployee = new HashMap<>();

    for(String[] access: badgeTimes) {
      if(accessByEmployee.containsKey(access[0])) {
        accessByEmployee.get(access[0]).add(Integer.parseInt(access[1]));
      } else {
        List<Integer> accessTimes = new ArrayList<>();
        accessTimes.add(Integer.parseInt(access[1]));
        accessByEmployee.put(access[0], accessTimes);
      }
    }


    for(String person: accessByEmployee.keySet()) {

      List<Integer> accessTimes = accessByEmployee.get(person);
      if(accessTimes.size() < 3) {
        continue;
      }
      Collections.sort(accessTimes);

      int start = 0;
      List<Integer> result = new ArrayList<>();
      result.add(accessTimes.get(0));
      for(int end = 1; end<accessTimes.size(); ) {
        if(accessTimes.get(end) - accessTimes.get(start) <= 100 ) {
          result.add(accessTimes.get(end));
          if(result.size() > 2) {
            frequentAccessors.put(person, result);

          }
          end++;
        } else {

          if(result.size() > 2) {
            frequentAccessors.put(person, result);
            break;
          }

          if(result.size() > 0) {
            result.remove(0);
          }


          start++;
          end = start+1;
        }
      }

    }

    return frequentAccessors;
  }



  public static void main(String[] argv) {


    String[][] badgeTimes = new String[][] {
        {"Paul",     "1355"},
        {"Jennifer", "1910"},
        {"John",      "830"},
        {"Paul",     "1315"},
        {"John",     "1615"},
        {"John",     "1640"},
        {"John",      "835"},
        {"Paul",     "1405"},
        {"John",      "855"},
        {"John",      "930"},
        {"John",      "915"},
        {"John",      "730"},
        {"John",      "940"},
        {"Jennifer", "1335"},
        {"Jennifer",  "730"},
        {"John",     "1630"},
    };

    Map<String, List<Integer>> result = printFrequentAccessor(badgeTimes);
    System.out.println(result);

  }
}
