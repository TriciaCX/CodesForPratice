package interviewTop100;

import struct.ListNode;

public class leetcode237
{
	/**
	 * 删除链表的节点
	 * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
	 * @param node
	 */
	public void deleteNode(ListNode node) {
		node.val = node.next.val;
		node.next = node.next.next;
	}
}
