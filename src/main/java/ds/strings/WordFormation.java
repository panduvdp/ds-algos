package ds.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
RallyHealth
 * You are encouraged to solve this task according to the task description, using any language you may know.
 * You are given a collection of ABC blocks. Just like the ones you had when you were a kid. The sample blocks are:
 *
 * ((B, O),
 * (D, O),
 * (G, T))
 * The goal of this task is to write a function that takes a string and a collection of blocks and determine whether you can spell the word with the blocks or not.
 * The rule is simple: Once a letter on a block is used that block cannot be used again
 * Assume each block contains at least one letter from the word
 * Example word that can be spelled: Dog, Too
 * Example word that cannot be spelled: Boo
 */
public class WordFormation {

  public boolean isWordCanBeFormed(final List<List<Character>> cards, final String word) {
    if (cards == null || cards.size() == 0) {
      return word == null || word.length() == 0;
    }
    if (cards.size() < word.length()) {
      return false;
    }

    final Map<Character, List<List<Character>>> charFreq = new HashMap<>();

    for (final List<Character> card : cards) {
      if (!charFreq.containsKey(card.get(0))) {
        charFreq.put(card.get(0), new ArrayList<>());
      }

      if (!charFreq.containsKey(card.get(1))) {
        charFreq.put(card.get(1), new ArrayList<>());
      }

      charFreq.get(card.get(0)).add(card);
      charFreq.get(card.get(1)).add(card);
    }

    return findMatch(charFreq, word);
  }

  private boolean findMatch(
      final Map<Character, List<List<Character>>> charFreq, final String word) {
    if (word.length() == 0) {
      return true;
    }
    for (int i = 0; i < word.length(); i++) {
      final Character c = word.charAt(i);
      if (!charFreq.containsKey(c)) {
        return false;
      }

      final List<List<Character>> matchedCards = charFreq.get(c);

      for (int k = 0; i < matchedCards.size(); k++) {
        final List<Character> card = matchedCards.get(i);
        final List<List<Character>> updatedCards = new ArrayList<>(matchedCards);
        updatedCards.remove(k);
        charFreq.put(c, updatedCards);
        final List<List<Character>> otherCards = charFreq.get(card.get(1));
        final List<List<Character>> updatedOtherCards = new ArrayList<>(otherCards);
        for (int j = 0; j < otherCards.size(); j++) {
          final List<Character> othercard = otherCards.get(j);
          if (othercard == card) {
            updatedOtherCards.remove(j);
            charFreq.put(card.get(1), updatedOtherCards);
            break;
          }
        }
        final boolean found = findMatch(charFreq, word.substring(i + 1));
        if (found) {
          return true;
        }
        charFreq.put(card.get(1), otherCards);
      }
    }

    return false;
  }
}
