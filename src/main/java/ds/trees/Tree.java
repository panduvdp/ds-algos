package ds.trees;

import java.util.List;

public interface Tree<T> {

  T getData();

  void insert(T data);

  void remove(T data);

  boolean isExists(T data);

  List<? extends Tree<T>> getChildren();
}
