package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import javafx.util.Pair;




public class Test {

  /**
   * Write a recursive algorithm that replaces each single binary tree node with  multiple nodes with smaller values.
   * Your algorithm is called with two parameters: a reference to a TreeNode pointer representing the root of a binary tree,
   * and an integer "stretching factor" K.
   *Your function should replace each node N with K nodes, each of which stores a data value that is 1/K of N's original value,
   * using integer division.  The new clones of node N should extend from their parent in the same direction that N extends from its parent.
   * For example, if N is its parent's left child, the stretched clones of N should also be their parent's left child, and vice versa if N was a right child.
   * The root node is a special case because it has no parent; we will handle this by saying that its stretched clones should extend to the left.   
   * Constraints: Do not modify the signature of the stretch function  signature: void stretch(root *TreeNode, stretchAmount int)
   *   You must use recursion in your solution to visit each tree node  https://imgur.com/a/mfholT9 
   * @param
   */
  enum Token {
    white, red, green, black, blue
  }
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
  }


  private TreeNode stretch(TreeNode node, int stretchFactor, boolean isLeftChild) {
    if(node == null) {
      return node;
    }


    TreeNode lSubTree = stretch(node.left, stretchFactor, true);
    TreeNode rSubTree = stretch(node.right, stretchFactor, false);

    int updatedVal = node.val/stretchFactor;
    TreeNode temp = node;
    temp.left = null;
    temp.right = null;

    for(int i=0; i< stretchFactor-1; i++ ){
      TreeNode child = new TreeNode(updatedVal);
      if(isLeftChild) {
        temp.left = child;
      } else {
        temp.right = child;
      }
      temp = child;
    }

    temp.left = lSubTree;
    temp.right = rSubTree;
    node.val = updatedVal;

    return node;
  }

  public void stretch(TreeNode node, int stretchFactor) {
    if(stretchFactor == 1) {
      return;
    }
    stretch(node, stretchFactor, true);
  }
  public int findIndexOfMaxInLeftSubArray(int[] nums) {
    for(int i=0; i< nums.length-1; i++) {
      if(nums[i] > nums[i+1]) {
        return i;
      }
    }
    return nums.length - 1;
  }

  public int findIndexOfMinInRightSubArray(int[] nums) {
    for(int i=nums.length-1; i > 0; i--) {
      if(nums[i] < nums[i-1]) {
        return i;
      }
    }
    return 0;
  }

  public Pair<Integer, Integer> findMaxMinOfUnsortedArray(int[] nums, int start, int end) {
    if(start > end) { //occurs when there are only 2 sorted suborders ex: 1 2 3 4 5 2 3 4
      return new Pair<>(nums[end+1], nums[start-1]);
    }
    int min = nums[start], max = nums[start];
    for(int i = start+1; i< end+1; i++) {
      if(nums[i] > max) {
        max = nums[i];
      } else if(nums[i] < min) {
        min = nums[i];
      }
    }
    return new Pair<>(min, max);
  }

  public int findUnsortedSubarray(int[] nums) {
    if(nums.length<2){
      return 0;
    }
    int maxLeft = findIndexOfMaxInLeftSubArray(nums);
    //already sorted
    if(maxLeft == nums.length-1) {
      return 0;
    }
    int minRight = findIndexOfMinInRightSubArray(nums);
    int minIndex = minRight;
    int maxIndex = maxLeft;
    for(int i = maxLeft+1; i< minRight; i++) {
      if(nums[i] > nums[maxIndex]) {
        maxIndex = i;
      } else if(nums[i] < nums[minIndex]) {
        minIndex = nums[i];
      }
    }


    int end = minIndex;
    for(; end<nums.length; end++) {
      if(nums[end] >= nums[maxIndex]) {
        break;
      }

    }

    int start = maxIndex-1;
    for(; start>=0; start--) {
      if(nums[start] <= nums[minIndex]) {
        break;
      }
    }

    System.out.println("[ "+ (start) + ", "+ (end-1) + " ]");

    return end-start;


  }

  public int findUnsortedSubarray1(int[] nums) {
    if(nums.length<2){
      return 0;
    }
    int maxLeft = findIndexOfMaxInLeftSubArray(nums);
    //already sorted
    if(maxLeft == nums.length-1) {
      return 0;
    }
    int minRight = findIndexOfMinInRightSubArray(nums);


    int min = nums[minRight];
    int max = nums[maxLeft];

    int end = maxLeft;
    for(; end<=minRight; end++) {
      if(nums[end] >= max) {
        max = nums[end];
      }
      if(nums[end]<min) {
        min = nums[end];
      }
    }

    int start = maxLeft;
    for(; start>=0; start--) {
      if(nums[start] <= min) {
        break;
      }
    }

    end = minRight;
    for(; end< nums.length; end++) {
      if(nums[end] >= max) {
        break;
      }
    }


    // System.out.println("[ "+ (start+1) + ", "+ (end-1) + " ]");

    return end-start-1;


  }


  public static void main(String[] args) {
    System.out.println("hello..world");
    TreeNode root = new TreeNode(12);
    TreeNode root1 = new TreeNode(81);
    TreeNode root2 = new TreeNode(56);
    TreeNode root3 = new TreeNode(34);
    TreeNode root4 = new TreeNode(19);

    root3.right = new TreeNode(6);
    root3.left = root4;

    root1.right = root2;

    root.left = root1;
    root.right = root3;

    Test t = new Test();
   // t.stretch(root, 3);
    //System.out.println(root);
    Stack<Integer> x = new Stack<>();
    Queue<Integer> x1 = new LinkedList<>();
    PriorityQueue<Integer> x2 = new PriorityQueue<>();
    HashSet<Integer> x3 = new HashSet<>();
    x1.add(null);
    x1.add(null);
    x3.add(null);
    x3.add(null);

    //System.out.println(x1.size());

    int[] nums = new int[] {2, 6, 4, 8, 10, 9, 15};
    //nums =  new int[] {1, 2, 4, 7, 10, 11, 7,12, 6,7,16,18};
    nums =  new int[] { 5,5,5,5,5,3,4,5}; //{1, 2, 3, 4, 5, 2, 3, 4}; //
   System.out.println(t.findUnsortedSubarray1(nums));

    Pair<Integer, Integer> x5  = new Pair<>(3, 4);
    Map<String, Integer> m = new HashMap<>();

    m.getOrDefault("charan",1);
    List<String> x6 = new ArrayList<>();
    LinkedHashMap<Integer, Integer> y = new LinkedHashMap<>();
    PriorityQueue<Integer> xe = new PriorityQueue<>();
    //System.out.println(findNumbers(new int[] { 2, 3, 1, 8, 2, 3, 5, 1 }));

  }



  public static List<Integer> findNumbers(int[] nums) {
    int i = 0;
    while (i < nums.length) {
      if (nums[i] != nums[nums[i] - 1])
        swap(nums, i, nums[i] - 1);
      else
        i++;
    }


    List<Integer> missingNumbers = new ArrayList<>();
    for (i = 0; i < nums.length; i++)
      if (nums[i] != i + 1)
        missingNumbers.add(i + 1);

    return missingNumbers;
  }

  public static int avgRotorSpeed(String statusQuery, int parentId) {
    String urlStr = "https://jsonmock.hackerrank.com/api/iot_devies/search?status=%s&page=%d";

    int counter = 0;
    int sum = 0;
    String firstPage = getDataFromApi(String.format(urlStr, statusQuery, 1));

    if(firstPage == null) {
      return 0;
    }

    int totalPages = getTotalPages(firstPage);

    int[] data = getTotalSpeedAndCountByChildren(firstPage, parentId);

    for(int i= 2; i<=totalPages; i++ ) {
      String pageData = getDataFromApi(String.format(urlStr, statusQuery, i));
      data =  getTotalSpeedAndCountByChildren(pageData, parentId);
    }

    return 0;
  }

  public static int getTotalPages(String data) {
    return 1;
  }


  public static int[] getTotalSpeedAndCountByChildren(String data, int parentId) {
    return new int[] {0,0};
  }

  /*
   * Complete the 'minCost' function below.
   *
   * The function is expected to return a LONG_INTEGER.
   * The function accepts following parameters:
   *  1. INTEGER n
   *  2. INTEGER_ARRAY proj
   *  3. INTEGER_ARRAY bids
   */

  public static long minCost(int n, List<Integer> proj, List<Integer> bids) {

    if(proj == null || proj.isEmpty()) {
      return -1;
    }
    if(bids == null || bids.isEmpty()) {
      return -1;
    }

    if(bids.size() != proj.size()) {
      return  -1;
    }

    Map<Integer, Integer> projcetByMinBid = new HashMap<>();

    for(int i =0; i< proj.size(); i++) {
        projcetByMinBid.put(proj.get(i), Math.min(projcetByMinBid.getOrDefault(proj.get(i), Integer.MAX_VALUE), bids.get(i)));
    }

    if(projcetByMinBid.size() != n) {
      return -1;
    }

    int totalCost = 0;
    for(Integer key: projcetByMinBid.keySet()) {
      totalCost += projcetByMinBid.get(key);
    }

    return totalCost;

  }

  public static String getDataFromApi(String urlStr) {
    try {
      URL url = new URL(urlStr);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      int responseCode = con.getResponseCode();
      if (responseCode == HttpURLConnection.HTTP_OK) {
        BufferedReader in = new BufferedReader(new InputStreamReader(
            con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
        }
        in.close();

        return response.toString();
      }
    } catch(Exception e) {
      System.out.println("exception occurred :"+ e.getMessage());
      e.printStackTrace();

    }

    return null;

  }

  private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }



}
