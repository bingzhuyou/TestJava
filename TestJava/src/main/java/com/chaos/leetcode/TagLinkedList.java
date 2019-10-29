package com.chaos.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TagLinkedList {

	// 206
	public static ListNode reverseList(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode cur = head;
		ListNode next;
		ListNode pre = null;

		while (cur != null) {
			next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}

		return pre;
	}

	// 141
	public static boolean hasCycle(ListNode head) {
		if (head == null) {
			return false;
		}
		ListNode curNode = head;
		int pos = -1;
		Map<ListNode, Integer> sln = new HashMap<ListNode, Integer>();
		while (curNode != null) {
			if (sln.containsKey(curNode)) {
				return true;
			}
			pos++;
			sln.put(curNode, pos);
			curNode = curNode.next;
		}
		return false;
	}

	// 160
	public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		Set<ListNode> sln = new HashSet<ListNode>();

		ListNode curNodeA = headA;
		ListNode curNodeB = headB;

		while (curNodeA != null) {
			sln.add(curNodeA);
			curNodeA = curNodeA.next;
		}
		while (curNodeB != null) {
			if (sln.contains(curNodeB)) {
				return curNodeB;
			}
			curNodeB = curNodeB.next;
		}

		return null;
	}

	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode lRet = new ListNode(-1);
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}

		ListNode cL1 = l1;
		ListNode cL2 = l2;
		ListNode cLRet = lRet;

		while (cL1 != null && cL2 != null) {
			if (cL1.val < cL2.val) {
				cLRet.next = cL1;
				cL1 = cL1.next;
			} else {
				cLRet.next = cL2;
				cL2 = cL2.next;
			}
			cLRet = cLRet.next;
		}

		if (cL1 != null) {
			cLRet.next = cL1;
		}

		if (cL2 != null) {
			cLRet.next = cL2;
		}

		return lRet.next;
	}

	// 83
	public static ListNode deleteDuplicates(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode per = head;
		ListNode current = head.next;
		while (current != null) {
			if (per.val == current.val) {
				per.next = current.next;
			} else {
				per = current;
			}
			current = current.next;
		}

		return head;
	}
}
