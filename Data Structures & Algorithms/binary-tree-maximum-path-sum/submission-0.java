/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

     private int dfs(TreeNode node) {
            if (node == null)
                return 0;
            int left = Math.max(0, dfs(node.left)); // 负数舍弃
            int right = Math.max(0, dfs(node.right));
            maxSum = Math.max(maxSum, left + right + node.val); // 更新全局答案（穿过节点的路径）
            return Math.max(left, right) + node.val; // 返回给父节点（单边路径）
        }
}
