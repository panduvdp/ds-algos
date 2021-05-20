package test;

import java.util.PriorityQueue;
import javafx.util.Pair;

/**
 * Note- Only consider three alpabhets a ,b ,c A string is called diverse if no 3 consecutive
 * letters are same. In other words diverse string may not contain any of the strings
 * "aaa","bbb","ccc"
 *
 * <p>Given three integers x,y,z write a function to return any longest possible diverse string
 * containing at most x letters 'a', y letters 'b',z letters 'c'. If no possiblity of building any
 * string return empty string
 *
 * <p>example1 x=8 ,y=1, z=1 then function may return aabaacaa or aacaabaa example 2 x=1,y=3,z=1
 * then function may return abbcb
 *
 * <p>z,y,z in range 1 to 100
 */
public class BuildMaxString {
  private  static void addCharFreq(PriorityQueue<Pair<Character, Integer>> heap, Pair<Character, Integer> val) {
    if(val.getValue() > 0) {
      heap.add(val);
    }
  }
  public static String solution1(int a, int b, int c) {
    StringBuilder builder = new StringBuilder();
    PriorityQueue<Pair<Character, Integer>> maxFreqHeap =
        new PriorityQueue<>((e1, e2) -> -1 * e1.getValue().compareTo(e2.getValue()));
    addCharFreq(maxFreqHeap, new Pair<>('a',a));
    addCharFreq(maxFreqHeap, new Pair<>('b',b));
    addCharFreq(maxFreqHeap, new Pair<>('c',c));

    while(maxFreqHeap.size() != 0) {
      Pair<Character, Integer> maxChar = maxFreqHeap.poll();
      if(builder.length()< 2 || ((builder.charAt(builder.length()-1) != maxChar.getKey() ||
          builder.charAt(builder.length()-2) != maxChar.getKey()))){
        builder.append(maxChar.getKey());
        addCharFreq(maxFreqHeap, new Pair<>(maxChar.getKey(), maxChar.getValue()-1));
      } else if(maxFreqHeap.size()>0) {
        Pair<Character, Integer> secondMax = maxFreqHeap.poll();
        builder.append(secondMax.getKey());
        addCharFreq(maxFreqHeap, new Pair<>(secondMax.getKey(), secondMax.getValue()-1));
        addCharFreq(maxFreqHeap, maxChar);
      } else {
        break;
      }
    }
    return builder.toString();
  }

  public static void main(String[] args) {
    System.out.println(solution1(1,3,4));
  }
}
