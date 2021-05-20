package ds.strings;

public class IsValidPalindrome {

  public static boolean validPalindrome(String s) {
    int front = 0;
    int end = s.length() - 1;
    while (front < end) {
      if (s.charAt(front) == s.charAt(end)) {
        front++;
        end--;
      } else {
        return isPalindrome(s, front + 1, end) || isPalindrome(s, front, end - 1);
      }
    }
    return true;
  }

  private static boolean isPalindrome(String str, int s, int e) {

    while (s < e) {
      if (str.charAt(s) == str.charAt(e)) {
        s++;
        e--;
      } else {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    String s =
        "aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga";
    // System.out.println(validPalindrome(s));
  }
}
