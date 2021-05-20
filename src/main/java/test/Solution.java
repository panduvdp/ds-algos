package test;

import java.util.Stack;

public class Solution implements Runnable {

  public static int getDistance(
      char[][] routeMatrix,
      int officerX,
      int officerY,
      Integer[][] calculatedDistances,
      int distance) {
    if (calculatedDistances[officerX][officerY] != null) {
      return calculatedDistances[officerX][officerY];
    }
    if (routeMatrix[officerX][officerY] == 'T') {
      return distance;
    }

    if (routeMatrix[officerX][officerY] == 'V') {
      return Integer.MAX_VALUE;
    }

    routeMatrix[officerX][officerY] = 'V';

    int downCost = Integer.MAX_VALUE;
    if (officerX + 1 < routeMatrix.length && routeMatrix[officerX + 1][officerY] != 'X') {
      downCost =
          getDistance(routeMatrix, officerX + 1, officerY, calculatedDistances, distance + 1);
    }

    int rightCost = Integer.MAX_VALUE;
    if (officerY + 1 < routeMatrix[officerX].length && routeMatrix[officerX][officerY + 1] != 'X') {
      rightCost =
          getDistance(routeMatrix, officerX, officerY + 1, calculatedDistances, distance + 1);
    }

    int upCost = Integer.MAX_VALUE;
    if (officerX - 1 >= 0 && routeMatrix[officerX - 1][officerY] != 'X') {
      upCost = getDistance(routeMatrix, officerX - 1, officerY, calculatedDistances, distance + 1);
    }

    int leftCost = Integer.MAX_VALUE;
    if (officerY - 1 >= 0 && routeMatrix[officerX][officerY - 1] != 'X') {
      leftCost =
          getDistance(routeMatrix, officerX, officerY - 1, calculatedDistances, distance + 1);
    }

    int minDistance = Math.min(Math.min(downCost, rightCost), Math.min(upCost, leftCost));
    calculatedDistances[officerX][officerY] = minDistance;
    return minDistance;
  }

  public static int calculateDistance(String cityMap) {
    if (cityMap == null
        || cityMap.length() == 0
        || !cityMap.contains("O")
        || !cityMap.contains("T")) {
      return Integer.MAX_VALUE;
    }
    String[] rows = cityMap.split(";");
    int validRows = 0;
    for (String row : rows) {
      if (row.length() != 0) {
        validRows++;
      }
    }
    int officerX = -1;
    int officerY = -1;
    int maxRowLength = 0;
    int rowC = 0;
    char[][] charMatrix = new char[validRows][];
    for (int i = 0; i < rows.length; i++) {
      if (rows[i].length() == 0) {
        continue;
      }
      charMatrix[rowC] = rows[i].toCharArray();
      if (rows[i].contains("O")) {
        officerX = rowC;
        officerY = rows[i].indexOf('O');
      }
      maxRowLength = Math.max(maxRowLength, rows[i].length());
      rowC++;
    }
    Integer[][] calculatedDistances = new Integer[charMatrix.length][maxRowLength];
    String xxc = "\tGood";

    return getDistance(charMatrix, officerX, officerY, calculatedDistances, 0);
  }

  /**
   * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct
   * numbers from the original list.
   *
   * <p>Example 1: Input: 1->2->3->3->4->4->5 Output: 1->2->5
   *
   * <p>Example 2: Input: 1->1->1->2->3 Output: 2->3
   */
  public static void removeDuplicates(Node node) {
    if (node == null || node.next == null) {
      return;
    }
    Node current = node;
    Node prev = null;
    boolean isDuplicate = false;

    while (current != null) {
      while (current.next != null && current.data == current.next.data) {
        isDuplicate = true;
        current = current.next;
      }
      if (isDuplicate) {
        isDuplicate = false;
      } else {
        if (prev != null && prev.next != current) {
          prev.next = current;
        }
        prev = current;
      }
      current = current.next;
    }
  }

  public static void main(String[] args) {
    Stack<Character> charStack = new Stack<>();

    // int minDistance = calculateDistance(";;;");
    Solution x = new Solution();
    // x.run();
    // x.testRemoveDeplicates();

    int[] a = new int[] {2, 1};
    // Arrays.sort(a);

    String xxc = "\t\t\t\tWoohoo! Let’s begin.";
    String[] xdf = xxc.split("\n");
    // System.out.println(xdf);
  }

  @Override
  public void run() {
    System.out.println("hello");
  }

  public void testRemoveDeplicates() {
    Node start = new Node();
    Node start1 = new Node();
    Node start2 = new Node();
    Node start3 = new Node();
    Node start4 = new Node();
    Node start5 = new Node();
    Node start6 = new Node();
    start.data = 1;
    start.next = start1;
    start1.data = 2;
    start1.next = start2;
    start2.data = 3;
    start2.next = start3;
    start3.data = 3;
    start3.next = start4;
    start4.data = 4;
    start4.next = start5;
    start5.data = 4;
    start5.next = start6;
    start6.data = 5;

    removeDuplicates(start);
    System.out.println(start);
  }

  class Node {
    int data;
    Node next;
  }
}
