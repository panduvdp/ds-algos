package ds.trees;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T> implements Tree<T> {
  T data;
  BinaryTree<T> left;
  BinaryTree<T> right;
  List<BinaryTree<T>> children;

  public BinaryTree(final T data, final BinaryTree<T> left, final BinaryTree<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
    this.children = new ArrayList<>();
    this.children.add(left);
    this.children.add(right);
  }

  public BinaryTree<T> getLeftChild() {
    return this.left;
  }

  public BinaryTree<T> getRightChild() {
    return this.right;
  }

  @Override
  public T getData() {
    return this.data;
  }

  @Override
  public List<BinaryTree<T>> getChildren() {
    return this.children;
  }

  @Override
  public void insert(final T data) {
    // TODO
  }

  @Override
  public void remove(final T data) {
    // TODO
  }

  @Override
  public boolean isExists(final T data) {
    // TODO
    return false;
  }
}
