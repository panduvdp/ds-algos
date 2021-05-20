package ds.strings;

import java.util.ArrayList;
import java.util.List;

public class MultiplyStrings {

  /**
   * https://leetcode.com/problems/multiply-strings/
   *
   * <p>Given two non-negative integers num1 and num2 represented as strings, return the product of
   * num1 and num2, also represented as a string.
   *
   * @param num1
   * @param num2
   * @return
   */
  public String multiply(String num1, String num2) {
    if (num1.equals("0") || num2.equals("0")) {
      return "0";
    }

    final List<List<Integer>> product = new ArrayList<>();

    if (num2.length() > num1.length()) {
      final String temp = num1;
      num1 = num2;
      num2 = temp;
    }
    int carry = 0;

    int maxLen = 0;
    for (int i = num2.length() - 1; i > -1; i--) {
      final int cur = num2.charAt(i) - '0';
      final List<Integer> curProd = new ArrayList<>();

      for (int j = num1.length() - 1; j > -1; j--) {
        final int temp = (num1.charAt(j) - '0') * cur + carry;
        // if(product.size() <= i) {
        //     product.add(0);
        // }

        // temp += product.get(product.size()-1-i);
        // product.add(product.size()-1-i, temp%10);
        curProd.add(0, temp % 10);
        carry = temp / 10;
      }

      while (carry > 0) {
        curProd.add(0, carry % 10);
        carry = carry / 10;
      }
      for (int k = i; k < num2.length() - 1; k++) {
        curProd.add(0);
      }
      product.add(curProd);
      if (maxLen < curProd.size()) {
        maxLen = curProd.size();
      }
    }
    final List<Integer> result = new ArrayList<>();
    carry = 0;
    // System.out.println(product);

    for (int i = 0; i < maxLen; i++) {
      int temp = carry;
      for (final List<Integer> cur : product) {
        if (cur.size() - i > 0) {
          temp = temp + cur.get(cur.size() - i - 1);
        }
      }
      result.add(0, temp % 10);
      carry = temp / 10;
    }

    while (carry > 0) {
      result.add(0, carry % 10);
      carry = carry / 10;
    }

    final StringBuilder resultBuilder = new StringBuilder();
    for (final Integer i : result) {
      resultBuilder.append(i);
    }
    return resultBuilder.toString();
  }

  public String multiplyOptimal(String num1, String num2) {
    if (num1.equals("0") || num2.equals("0")) {
      return "0";
    }

    final List<List<Integer>> product = new ArrayList<>();

    if (num2.length() > num1.length()) {
      final String temp = num1;
      num1 = num2;
      num2 = temp;
    }

    final int size2 = num2.length();
    final int size1 = num1.length();

    int carry = 0;

    int maxLen = 0;
    final List<Integer> curProd = new ArrayList<>();

    for (int i = size2 - 1; i > -1; i--) {
      final int cur = num2.charAt(i) - '0';
      final int offset = size2 - 1 - i;
      for (int j = size1 - 1; j > -1; j--) {
        final int temp = (num1.charAt(j) - '0') * cur + carry;
        if (curProd.size() > offset) {
          // TODO:  product.add(0);
        }

        // temp += product.get(product.size()-1-i);
        // product.add(product.size()-1-i, temp%10);
        curProd.add(0, temp % 10);
        carry = temp / 10;
      }

      while (carry > 0) {
        curProd.add(0, carry % 10);
        carry = carry / 10;
      }
      for (int k = i; k < num2.length() - 1; k++) {
        curProd.add(0);
      }
      product.add(curProd);
      if (maxLen < curProd.size()) {
        maxLen = curProd.size();
      }
    }
    final List<Integer> result = new ArrayList<>();
    carry = 0;
    // System.out.println(product);

    for (int i = 0; i < maxLen; i++) {
      int temp = carry;
      for (final List<Integer> cur : product) {
        if (cur.size() - i > 0) {
          temp = temp + cur.get(cur.size() - i - 1);
        }
      }
      result.add(0, temp % 10);
      carry = temp / 10;
    }

    while (carry > 0) {
      result.add(0, carry % 10);
      carry = carry / 10;
    }

    final StringBuilder resultBuilder = new StringBuilder();
    for (final Integer i : result) {
      resultBuilder.append(i);
    }
    return resultBuilder.toString();
  }
}
