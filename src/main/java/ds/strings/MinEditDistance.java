package ds.strings;

/**
 * Blend Intw Questions
 *
 * <p>Given two strings, find the minimum number of operations required to convert the first string
 * into the second string. An operation can be 1) Add a character 2) Remove a character 3) Replace a
 * character All these operations have a cost of 1 Ex) str1 -> cat, str2 -> cut Min number of
 * operations - 1 (replace 'a' with a 'u')
 *
 * <p>str1 -> blend, str2 -> bleand Min number of operations - 1(add an 'a')
 *
 * <p>str1 -> sunday, str2 -> saturday Min number of operations - 3(add 'a', 't', replace 'n' with a
 * 'r')
 *
 * <p>f(i, j) := minimum cost (or steps) required to convert first i characters of word1 to first j
 * characters of word2
 *
 * <p>Case 1: word1[i] == word2[j], i.e. the ith and jth characters match.
 *
 * <p>f(i, j) = f(i - 1, j - 1)
 *
 * <p>Case 2: word1[i] != word2[j], then we must either insert, delete or replace, whichever is
 * cheaper
 *
 * <p>f(i, j) = 1 + min { f(i, j - 1), f(i - 1, j), f(i - 1, j - 1) }
 *
 * <p>f(i, j - 1) represents insert operation f(i - 1, j) represents delete operation f(i - 1, j -
 * 1) represents replace operation
 */
public class MinEditDistance {}
