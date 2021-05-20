package ds.trees;

public class ValidateBst {

   // Definition for a binary tree node.
    public class TreeNode {
        int val;
       TreeNode left;
        TreeNode right;
       TreeNode(int x) { val = x; }
    }


  public boolean isValidBST(TreeNode root) {
    return this.checkBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);

  }

  public boolean checkBST(TreeNode n, Integer min, Integer max) {
    if(n == null)
      return true;
    if((min!=null && n.val<=min) || (max!=null && n.val >= max))
      return false;
    if(!checkBST(n.left,min,n.val) || !checkBST(n.right, n.val, max))
      return false;
    return true;
  }

}
