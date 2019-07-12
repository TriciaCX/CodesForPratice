package interviewTop100;

import struct.TreeNode;

public class leetcode108
{
	/**
	 * leetcode108 将有序数组转换为二叉树
	 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树
	 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
	 */
	public TreeNode sortedArrayToBST(int[] nums) {
		if(nums==null || nums.length == 0)
			return null;
		return toBSTCore(nums,0,nums.length-1);
	}

	private TreeNode toBSTCore(int[] nums, int left, int right)
	{
		if(left>right)
			return null;
		int mid =(left+right)/2;
		TreeNode root = new TreeNode(nums[mid]);
		root.left = toBSTCore(nums, left, mid-1);
		root.right = toBSTCore(nums, mid+1, right);
		return root;
	}
}
