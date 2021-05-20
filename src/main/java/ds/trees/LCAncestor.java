package ds.trees;

public class LCAncestor {

  BinaryTree<Integer> findLca(
      final BinaryTree<Integer> root, final BinaryTree<Integer> p, final BinaryTree<Integer> q) {
    if (root == null || covers(root, p) || covers(root, q)) {
      return null;
    }
    return findAncestorHelper(root, p, q);
  }

  BinaryTree<Integer> findAncestorHelper(
      final BinaryTree<Integer> root, final BinaryTree<Integer> p, final BinaryTree<Integer> q) {

    if (root == null || root == p || root == q) {
      return root;
    }
    final boolean isPOnLeft = covers(root.left, p);
    final boolean isQOnLeft = covers(root.left, q);

    if (isPOnLeft && isQOnLeft) {
      return findAncestorHelper(root.left, p, q);
    } else if (!isPOnLeft && !isQOnLeft) {
      return findAncestorHelper(root.right, p, q);
    } else {
      return root;
    }
  }

  boolean covers(final BinaryTree<Integer> root, final BinaryTree<Integer> p) {
    if (root == null) {
      return false;
    }
    if (root == p) {
      return true;
    }
    return covers(root.left, p) || covers(root.right, p);
  }
}
