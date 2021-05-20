package ds.strings;

/**
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for
 * '?' and '*'.
 *
 * <p>'?' Matches any single character. '*' Matches any sequence of characters (including the empty
 * sequence).
 *
 * <p>The matching should cover the entire input string (not partial).
 *
 * <p>Note:
 *
 * <p>s could be empty and contains only lowercase letters a-z. p could be empty and contains only
 * lowercase letters a-z, and characters like ? or *.
 */
public class WildcardMatch {
  public boolean isMatch(String s, String p) {
    if( p == null && s == null) {
      return true;
    }
    if(p == null  ||  s==null) {
      return false;
    }
    if(p.equals(s)) {
      return  true;
    }

    if(p.equals("*") || (p.equals("?") && s.length() ==1)) {
      return true;
    }

    int strPos = 0;
    for(int i =0; i<p.length(); i++) {
      char current = p.charAt(i);
      if((current != '*' || current != '?')) {
       // if(str)
      };
    }
    return false;

  }

  public static void main(String[] args) {

  }

}
