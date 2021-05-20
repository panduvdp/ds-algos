package ds.trees;

import com.google.common.base.MoreObjects;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

  public BinarySearchTree(final T data) {
    super(data, null, null);
  }

  @Override
  public void insert(final T data) {
    if (data == null) {
      return;
    }
    if (this.data.compareTo(data) >= 0) {
      if (this.left == null) {
        this.left = new BinarySearchTree<>(data);
        this.children.add(this.left);
      } else {
        this.left.insert(data);
      }
    } else if (this.right == null) {
      this.right = new BinarySearchTree<>(data);
      this.children.add(this.right);
    } else {
      this.right.insert(data);
    }
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

  /**
   * Prints the immutable value {@code Attribute} with attribute values.
   *
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return MoreObjects.toStringHelper("")
        .omitNullValues()
        .add("data", this.data)
        .add("left", this.left)
        .add("right", this.right)
        .toString();
  }
}
