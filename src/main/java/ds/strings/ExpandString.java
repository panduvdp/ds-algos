package ds.strings;

import java.util.Stack;

public class ExpandString {
  // 1{a}2{ab} - aaaabab
  // 20{2{ab}c} - ababcababc

  public static String expandStr(String input) {

    Stack<String> strStack = new Stack<>();

    char[] chars = input.toCharArray();

    for (int i = 0; i < chars.length; ) {
      StringBuilder strBuilder = new StringBuilder();

      if (Character.isDigit(chars[i])) {
        while (i < chars.length && Character.isDigit(chars[i])) {
          strBuilder.append(chars[i]);
          i++;
        }
        strStack.push(strBuilder.toString());
      } else if (Character.isAlphabetic(chars[i])) {
        while (i < chars.length && Character.isAlphabetic(chars[i])) {
          strBuilder.append(chars[i]);
          i++;
        }
        strStack.push(strBuilder.toString());
      } else if (chars[i] == '{') {
        i++;
      } else {
        String current = generateString(strStack);
        int repetitions = Integer.parseInt(strStack.pop());

        while (repetitions > 0) {
          strBuilder.append(current);
          repetitions--;
        }

        strStack.push(strBuilder.toString());
        System.out.println(strStack);
        i++;
      }
    }

    System.out.println(strStack);
    ;

    return generateString(strStack);
  }

  private static String generateString(Stack<String> strStack) {
    String prev = strStack.pop();
    while (!strStack.isEmpty()) {
      String current = strStack.peek();
      if (Character.isAlphabetic(current.charAt(0))) {
        prev = strStack.pop() + prev;
      } else {
        break;
      }
    }
    return prev;
  }

  public static void main(String[] args) {
    System.out.println(expandStr("2{2{ab}c}"));
  }
}
