package ds.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/** Q 4,9. Given a BST, print all sequences of input that could have generated that tree */
public class BstSequences {

  public static void main(final String[] args) {
    final BstSequences t = new BstSequences();
    //    final List<Integer> l1 = new LinkedList<>();
    //    l1.add(1);
    //    l1.add(2);
    //    l1.add(3);
    //
    //    final List<Integer> l2 = new LinkedList<>();
    //        l2.add(4);
    //        l2.add(5);
    //        l2.add(6);
    //    System.out.println(l2);
    //    System.out.println(l1);
    //    for (final Integer i : l2) {
    //      System.out.println(l1);
    //    }
    //
    //    final List<List<Integer>> results = new ArrayList<>();
    final List<Integer> prefix = new LinkedList<>();
    // t.weaveLists(l1, l2, results, prefix);

    final BinarySearchTree<Integer> bsTree = new BinarySearchTree<>(3);
    bsTree.insert(1);
    bsTree.insert(2);
    bsTree.insert(4);
    bsTree.insert(5);
    System.out.println(bsTree);
    System.out.println(t.generateAllSequences(bsTree));
  }

  /**
   * Make the lists {2,3} & {4,5} to {{2,3,4,5}, {2,4,3,5}, {4,2,3,5}, {4,2,5,3}, {4,5,2,3},
   * {2,4,5,3} } . i.e. Generate all the different combination of numbers from both the arrays while
   * maintaining the order of the numbers from each array.
   *
   * @param first
   * @param second
   * @param prefix
   * @return
   */
  private void weaveLists(
      final List<Integer> first,
      final List<Integer> second,
      final List<List<Integer>> results,
      final List<Integer> prefix) {
    if (first.isEmpty() || second.isEmpty()) {
      final List<Integer> subList = new LinkedList<>(prefix);
      subList.addAll(first);
      subList.addAll(second);
      results.add(subList);
      return;
    }

    final Integer firstHead = first.remove(0);
    prefix.add(firstHead);
    weaveLists(first, second, results, prefix);
    prefix.remove(prefix.remove(prefix.size() - 1)); // alternative is removeLast from LL
    first.add(0, firstHead);

    final Integer secondHead = second.remove(0);
    prefix.add(secondHead);
    weaveLists(first, second, results, prefix);
    prefix.remove(prefix.remove(prefix.size() - 1)); // alternative is removeLast from LL
    second.add(0, secondHead);
  }

  public List<List<Integer>> generateAllSequences(final BinaryTree<Integer> bstRoot) {
    final List<List<Integer>> result = new ArrayList<>();

    if (bstRoot == null) {
      result.add(new LinkedList<>());
      return result;
    }
    final List<Integer> prefix = new ArrayList<>();
    prefix.add(bstRoot.data);

    final List<List<Integer>> leftSequences = generateAllSequences(bstRoot.getLeftChild());
    final List<List<Integer>> rightSequences = generateAllSequences(bstRoot.getRightChild());

    for (final List<Integer> lSeq : leftSequences) {
      for (final List<Integer> rSeq : rightSequences) {
        weaveLists(lSeq, rSeq, result, prefix);
      }
    }

    return result;
  }
}
