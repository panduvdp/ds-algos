package ds;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BasicCalculator {

  /**
   * https://leetcode.com/problems/basic-calculator-ii/
   * Implement a basic calculator to evaluate a simple expression string.
   * The expression string contains only <b>non-negative</b> ints.
   *  +,-,*,/ operators and empty spaces . The integer division should truncate toward zero.
   *  eg 3/2 =1, 3+5 / 2 = 5
   */

  private static int executeResult(int left, int right, char operand) {
    switch (operand) {
      case '+':
        return left + right;
      case '-':
        return left - right;
      case '*':
        return left * right;
      case '/':
        return left / right;
      default:
        throw new IllegalArgumentException("invalid operand received: "+ operand);
    }
  }

  public static int calculate(String s) {

    Stack<Character> operators = new Stack<>();
    Stack<Integer> numbers = new Stack<>();
    char[] chars = s.toCharArray();
    Map<Character, Integer> charPriority = new HashMap<>();
    charPriority.put('+', 1);
    charPriority.put('-', 1);
    charPriority.put('/', 2);
    charPriority.put('*', 2);

    int i=0;

    while(i< chars.length) {
      char c = chars[i];
      if (c == ' ') {
        i++;
        continue;
      }
      if(charPriority.containsKey(c) ) {
        while(!operators.isEmpty()) {
          if(charPriority.get(c) - charPriority.get(operators.peek()) < 1) {
            int right = numbers.pop();
            int left = numbers.pop();
            numbers.push(executeResult(left, right, operators.pop()));
          } else {
            break;
          }
        }
        operators.push(c);
        i++;

      } else { //number
        int val =  Character.getNumericValue(c);
        i++;
        while(i< chars.length) {
          c = chars[i];
          if(!Character.isDigit(c)) {
            break;
          }
          val = val * 10 + Character.getNumericValue(c);
          i++;
        }
        numbers.push(val);
      }
    }

    while(!operators.isEmpty()){
      int right = numbers.pop();
      int left = numbers.pop();
      numbers.push(executeResult(left, right, operators.pop()));
    }
    return numbers.pop();

  }

  public static void main(String[] args) {
    System.out.println(calculate("1*2-3/4+5*6-7*8+9/10"));
  }
}
