package com.chaos.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TagLinkedList {
	// 82
	public static ListNode deleteDuplicatesII(ListNode head) {
		Map<Integer, Integer> dupChkMap = new HashMap<Integer, Integer>();

		ListNode cur = head;
		while (cur != null) {
			if (dupChkMap.containsKey(cur.val)) {
				dupChkMap.put(cur.val, dupChkMap.get(cur.val) + 1);
			} else {
				dupChkMap.put(cur.val, 1);
			}
			cur = cur.next;
		}

		cur = head;
		ListNode per = null;
		while (cur != null) {
			if (dupChkMap.get(cur.val) > 1) {
				if (per != null) {
					per.next = cur.next;
				}
				if (head == cur) {
					head = cur.next;
				}
			} else {
				per = cur;
			}
			cur = cur.next;
		}

		return head;
	}

	// 19
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode cur = head;
		ListNode pre = null;
		ListNode tmp;

		while (cur != null) {
			tmp = cur.next;
			cur.next = pre;
			pre = cur;
			cur = tmp;
		}
		head = pre;

		cur = head;
		pre = null;

		for (int i = 1; i < n && cur != null; i++) {
			pre = cur;
			cur = cur.next;
		}

		if (pre == null) {
			head = head.next;
		} else {
			pre.next = cur.next;
		}

		cur = head;
		pre = null;

		while (cur != null) {
			tmp = cur.next;
			cur.next = pre;
			pre = cur;
			cur = tmp;
		}
		head = pre;

		return head;
	}

	// 237
	public void deleteNode(ListNode node) {
		node.val = node.next.val;
		node.next = node.next.next;
	}

	// 203
	public static ListNode removeElements(ListNode head, int val) {
		if (head == null) {
			return head;
		}

		ListNode cur = head;
		ListNode per = null;
		while (cur != null) {
			if (cur.val == val) {
				if (per != null) {
					per.next = cur.next;
				}
				if (head == cur) {
					head = cur.next;
				}
			} else {
				per = cur;
			}
			cur = cur.next;
		}

		return head;
	}

	// 234
	public static boolean isPalindrome(ListNode head) {
		ListNode cur = head;
		List<Integer> li = new ArrayList<Integer>();
		while (cur != null) {
			li.add(cur.val);
			cur = cur.next;
		}

		int left = 0;
		int right = li.size() - 1;
		while (right > left) {
			if (!li.get(left).equals(li.get(right))) {
				return false;
			}
			left++;
			right--;
		}

		return true;
	}

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
