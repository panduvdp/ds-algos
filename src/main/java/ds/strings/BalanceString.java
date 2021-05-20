package ds.strings;

public class BalanceString {

  /**
   * The input should be a substring of the result.
   *
   * <p>// Reputation.com
   *
   * @param input
   * @return
   */
  public static String balanceParenthesis(final String input) {

    if (input == null || input.length() == 0) {
      return input;
    }
    String output = "";
    final char[] inputChars = input.toCharArray();

    int i = 0;
    int unBalanced = 0;

    while (i < inputChars.length) {
      if (inputChars[i] == '<') {
        unBalanced++;
        output = output + "<";
        i++;
      } else if (inputChars[i] == '>') {
        if (unBalanced > 0) {
          output = output + ">";
          unBalanced--;
        } else {
          output = "<" + output + ">";
        }
        i++;
      }
    }
    while (unBalanced > 0) {
      unBalanced--;
      output = output + ">";
    }
    return output;
  }

  public static void main(final String[] args) {
    // >><
    // <<><><
    final String input = "<<><><"; // "<><<><";
    final String output = balanceParenthesis(input);
    System.out.println(output + "   " + output.contains(input));
    System.out.println("--- ---");
    System.out.println(input);
    System.out.println("--- ---");
  }
}
