package ds.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuildPostOrder {
  private static int preIndex = 0;

  public static int search(int[] a, int s, int e, int data) {
    for (int i = s; i <= e; i++) {
      if (a[i] == data) {
        return i;
      }
    }
    return -1;
  }

  public static void buildPostOrder(
      int in[], int pre[], int inStrt, int inEnd, List<Integer> result) {
    if (inStrt > inEnd) {
      return;
    }

    int inIndex = search(in, inStrt, inEnd, pre[preIndex++]);

    buildPostOrder(in, pre, inStrt, inIndex - 1, result);
    buildPostOrder(in, pre, inIndex + 1, inEnd, result);
    result.add(in[inIndex]);
  }

  public static void main(String[] args) throws Exception {
    int a1[] = new int[] {4, 2, 5, 1, 3};
    int a2[] = new int[] {1, 2, 4, 5, 3};

    // Node root = BinaryTree.buildTree(a1, a2, 0, 4);
    // System.out.println(root);
    List<Integer> a3 = new ArrayList<>();
    buildPostOrder(a1, a2, 0, 4, a3);
    // for (int i : a3) System.out.println(i);

    // System.out.println(get("ISBN:0451526538"));
    int[][] input = new int[4][5];
    Arrays.fill(input, new Integer(1));
    System.out.println(input[0][0]);
  }
}
