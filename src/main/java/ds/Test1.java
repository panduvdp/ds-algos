package ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Test1 {
  public static List<List<Integer>> getSubsets(
      int[] a, int k, int current, Map<String, List<List<Integer>>> visited) {
    if (current > a.length - 1) {
      return null;
    }
    String key = String.format("%d_%d", k, current);
    if (visited.containsKey(key)) {
      return visited.get(key);
    }

    String input = "sd";
    int[][] neeighbours = new int[][] {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    List<List<Integer>> result = new ArrayList<>();
    List<List<Integer>> subresult;
    // include current index
    if (a[current] <= k) {
      subresult = getSubsets(a, k - a[current], current + 1, visited);
      if (subresult != null) {
        for (List<Integer> sub : subresult) {
          sub.add(0, a[current]);
          result.add(sub);
        }
      }
    }
    subresult = getSubsets(a, k, current + 1, visited);
    if (subresult != null) {
      result.addAll(subresult);
    }
    result = result.size() == 0 ? null : result;
    visited.put(key, result);
    return result;
  }

  public static List<List<Integer>> getSubsets(int[] input, int k) {
    Map<String, List<List<Integer>>> visited = new HashMap<>();

    List<List<Integer>> result = getSubsets(input, k, 0, visited);

    if (result == null || result.size() == 1) {
      return result;
    }

    Set<Set<Integer>> nonDuplicates = new HashSet<>();

    List<List<Integer>> result1 = new ArrayList<>();
    for (List<Integer> sub : result) {
      Set<Integer> subset = new HashSet<>(sub);
      if (nonDuplicates.contains(subset)) {
        continue;
      }
      nonDuplicates.add(subset);
      result1.add(sub);
    }

    return result1;
  }

  public static List<CommentNode> flatToTree(List<Comment> comments) {
    /**
     * Convert the comments from a flat list to a tree-like structure. The comments should be
     * returned in the same order provided to the function.
     */
    List<CommentNode> result = new ArrayList<>();
    if (comments == null || comments.size() == 0) {
      return result;
    }

    List<Comment> parentNodes = new LinkedList<>();
    Map<Long, List<Comment>> childrenMap = new HashMap<>();
    Map<Long, CommentNode> processedChildren = new HashMap<>();

    for (Comment comment : comments) {
      if (comment != null) {
        if (comment.parentId == null) {
          parentNodes.add(comment);
        } else {
          List<Comment> childrenList = childrenMap.getOrDefault(comment.parentId, new ArrayList());
          childrenList.add(comment);
          childrenMap.put(comment.parentId, childrenList);
        }
      }
    }

    while (parentNodes.size() > 0) {
      Comment parent = parentNodes.remove(0);
      CommentNode parentNode = null;
      if (parent.parentId == null) {
        parentNode = new CommentNode(parent, null);
      } else {
        parentNode = processedChildren.get(parent.id);
      }
      List<CommentNode> childrenList = new ArrayList<>();
      if (childrenMap.containsKey(parent.id)) {

        List<Comment> commentList = childrenMap.get(parent.id);

        for (Comment comment : commentList) {
          CommentNode node = new CommentNode(comment, parentNode);
          processedChildren.put(comment.id, node);
          childrenList.add(node);
        }
      }

      parentNode.addChildren(childrenList);
      if (parentNode.parentComment == null) {
        result.add(parentNode);
      }

      parentNodes.addAll(childrenMap.getOrDefault(parent.id, new ArrayList<>()));
    }

    return result;
  }

  public static void main(String[] args) {
    // Example comments
    List<Comment> comments = new ArrayList<>();
    comments.add(new Comment(1L, null));
    comments.add(new Comment(2L, 1L));
    comments.add(new Comment(3L, 1L));
    comments.add(new Comment(4L, null));
    comments.add(new Comment(5L, 4L));
    comments.add(new Comment(6L, 5L));
    // treeNodes should contain two CommentNodes
    List<CommentNode> treeNodes = flatToTree(comments);

    for (CommentNode root : treeNodes) {
      System.out.println(root.comment.id);
      System.out.println(root.children);
      Character x;
    }
  }

  public void test() {}

  public static class Comment {
    /** Representation of a comment. */
    public final long id;

    public final Long parentId;

    public Comment(long id, Long parentId) {
      this.id = id;
      this.parentId = parentId;
    }
  }

  public static class CommentNode {
    /** A node for representing the tree structure for comments and their replies. */
    public final Comment comment;

    public final CommentNode parentComment;
    public final List<CommentNode> children = new ArrayList<>();

    public CommentNode(Comment comment, CommentNode parentComment) {
      this.comment = comment;
      this.parentComment = parentComment;
    }

    public void addChild(CommentNode child) {
      this.children.add(child);
    }

    public void addChildren(List<CommentNode> children) {
      this.children.addAll(children);
    }
  }
}
