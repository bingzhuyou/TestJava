package com.chaos.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class TagTree {
	// 1110
	public static List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
		List<TreeNode> ltn = new ArrayList<TreeNode>();
		Stack<TreeNode> stn = new Stack<TreeNode>();

		Set<Integer> si = new HashSet<Integer>();

		for (int i = 0; i < to_delete.length; i++) {
			si.add(to_delete[i]);
		}

		TreeNode cur = root;
		while (cur != null || stn.size() > 0) {
			if (si.contains(cur.val)) {
				si.remove(root.val);
			}
		}

		return ltn;
	}

	public static void delNodesSet(TreeNode root, Set<Integer> si, List<TreeNode> ltn) {
		if (root == null || si.size() <= 0) {
			return;
		}
		if (si.contains(root.val)) {
			ltn.add(root);
			si.remove(root.val);
		}
		delNodesSet(root.left, si, ltn);
		if (root.left != null) {
			if (si.contains(root.left.val)) {
				root.left = null;
			}
		}
		delNodesSet(root.right, si, ltn);
		if (root.right != null) {
			if (si.contains(root.right.val)) {
				root.right = null;
			}
		}
	}

	// 872
	public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
		Stack<Integer> si = new Stack<Integer>();
		Stack<TreeNode> stn = new Stack<TreeNode>();

		TreeNode cur = root1;
		while (cur != null || stn.size() > 0) {
			if (cur == null) {
				cur = stn.pop();
			}
			if (cur.right != null) {
				if (cur.left != null) {
					stn.push(cur.right);
					cur = cur.left;
				} else {
					cur = cur.right;
				}
			} else {
				if (cur.left != null) {
					cur = cur.left;
				} else {
					si.push(cur.val);
					cur = null;
				}
			}
		}

		cur = root2;
		while (cur != null || stn.size() > 0) {
			if (cur == null) {
				cur = stn.pop();
			}
			if (cur.left != null) {
				if (cur.right != null) {
					stn.push(cur.left);
					cur = cur.right;
				} else {
					cur = cur.left;
				}
			} else {
				if (cur.right != null) {
					cur = cur.right;
				} else {
					if (si.size() <= 0) {
						return false;
					}
					if (cur.val != si.peek()) {
						return false;
					} else {
						si.pop();
					}
					cur = null;
				}
			}
		}

		if (si.size() > 0) {
			return false;
		}

		return true;
	}

	// 429
	public static List<List<Integer>> levelOrder(Node root) {
		List<List<Integer>> ret = new ArrayList<List<Integer>>();

		if (root == null) {
			return ret;
		}

		List<Integer> tmpList = new ArrayList<Integer>();
		tmpList.add(root.val);
		ret.add(tmpList);

		List<Node> childList = root.children;
		List<Node> tmpChildList = new ArrayList<Node>();
		while (childList != null && childList.size() > 0) {
			tmpList = new ArrayList<Integer>();
			tmpChildList = new ArrayList<Node>();

			for (int i = 0; i < childList.size(); i++) {
				tmpList.add(childList.get(i).val);
				if (childList.get(i).children != null) {
					tmpChildList.addAll(childList.get(i).children);
				}
			}
			ret.add(tmpList);
			childList = tmpChildList;
		}

		return ret;
	}

	// 404
	public static int sumOfLeftLeaves(TreeNode root) {
		int ret = 0;
		if (root == null) {
			return ret;
		}

		if (root.left != null) {
			if (root.left.left == null && root.left.right == null) {
				ret += root.left.val;
			}
			ret += sumOfLeftLeaves(root.left);
		}
		ret += sumOfLeftLeaves(root.right);

		return ret;
	}

	// 226
	public static TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return root;
		}
		invertTree(root.left);
		invertTree(root.right);
		TreeNode tmp = root.left;
		root.left = root.right;
		root.right = tmp;

		return root;
	}

	// 94

	public static List<Integer> inorderTraversalNR(TreeNode root) { // 非递归
		List<Integer> rl = new ArrayList<Integer>();
		if (root == null) {
			return rl;
		}

		Stack<TreeNode> st = new Stack<TreeNode>();
		TreeNode cur = root;
		while (st.size() > 0 || cur != null) {
			if (cur == null) {
				cur = st.pop();
				rl.add(cur.val);
				cur = cur.right;
			} else {
				if (cur.left != null) {
					st.push(cur);
					cur = cur.left;
				} else {
					rl.add(cur.val);
					cur = cur.right;
				}
			}
		}

		return rl;
	}

	public static List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> rl = new ArrayList<Integer>();

		inorderTraversalS(root, rl);

		return rl;
	}

	public static void inorderTraversalS(TreeNode root, List<Integer> l) {
		if (root == null) {
			return;
		}

		inorderTraversalS(root.left, l);
		l.add(root.val);
		inorderTraversalS(root.right, l);

	}

	public boolean isUnivalTree(TreeNode root) {
		if (root == null) {
			return true;
		}
		Stack<TreeNode> rtStack = new Stack<TreeNode>();

		TreeNode curNode = root;
		int key = curNode.val;
		while (curNode != null && !rtStack.isEmpty()) {
			if (curNode.left != null) {
				if (curNode.right != null) {
					rtStack.push(curNode.right);
				}
				curNode = curNode.left;
			} else if (curNode.right != null) {
				curNode = curNode.right;
			} else {
				if (rtStack.isEmpty()) {
					break;
				} else {
					curNode = rtStack.pop();
				}
			}

			if (key != curNode.val) {
				return false;
			}
		}

		return true;
	}

	// 100
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null) {
			if (q == null) {
				return true;
			} else {
				return false;
			}
		} else {
			if (q == null) {
				return false;
			} else {
				if (p.val != q.val) {
					return false;
				}
			}
		}

		if (!isSameTree(p.left, q.left)) {
			return false;
		}

		if (!isSameTree(p.right, q.right)) {
			return false;
		}

		return true;
	}

	public static int addToStack(Stack<TreeNode> st, TreeNode root, int level) {
		if (root == null) {
			return level - 1;
		}
		st.push(root);
		if (root.left == root.right && root.left == null) {
			return level;
		}
		if (root.left == null) {
			st.push(null);
		}
		int nlevel1 = addToStack(st, root.left, level + 1);
		int nlevel2 = addToStack(st, root.right, level + 1);

		if (nlevel1 == nlevel2) {
			if (nlevel1 == -1) {
				return level + 1;
			} else {
				return nlevel1;
			}
		}
		return -1;
	}

	public static boolean isSymmetricbad(TreeNode root) {
		if (root == null) {
			return true;
		}
		Stack<TreeNode> st = new Stack<TreeNode>();
		int level = addToStack(st, root, 0);

		if (level == -1) {
			return false;
		}

		for (int l = level; l > 0; l--) {
			int elmNum = (int) Math.pow(2, level);
			;
			List<TreeNode> lt = new ArrayList<TreeNode>();

			for (int i = 0; i < elmNum; i++) {
				lt.add(st.pop());
			}

			int left = 0;
			int right = elmNum - 1;
			while (right > left) {
				if ((lt.get(left) == null && lt.get(right) != null) ||
						(lt.get(right) == null && lt.get(left) != null) ||
						(lt.get(left) != null && lt.get(right) != null && lt.get(left).val != lt.get(right).val)) {
					return false;
				}
			}

		}

		return true;
	}

	public static boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}

		List<TreeNode> lt = new ArrayList<TreeNode>();
		lt.add(root.left);
		lt.add(root.right);
		while (lt.size() > 0) {
			List<TreeNode> tmpT = new ArrayList<TreeNode>();
			int left = 0;
			int right = lt.size() - 1;

			while (right > left) {
				TreeNode leftNode = lt.get(left);
				TreeNode rightNode = lt.get(right);

				if (leftNode == null && rightNode == null) {
					right--;
					left++;
					continue;
				} else if (leftNode != null && rightNode != null && leftNode.val == rightNode.val) {
					tmpT.add(leftNode.left);
					tmpT.add(leftNode.right);
					tmpT.add(rightNode.left);
					tmpT.add(rightNode.right);
				} else {
					return false;
				}
				right--;
				left++;
			}
			lt = tmpT;
		}

		return true;
	}

	public static boolean isSymmetricR(TreeNode root) {
		if (root == null) {
			return true;
		}
		Queue<TreeNode> lt = new LinkedList<TreeNode>();
		lt.add(root.left);
		lt.add(root.right);
		while (lt.size() > 0) {
			TreeNode leftNode = lt.poll();
			TreeNode rightNode = lt.poll();

			if (leftNode == null && rightNode == null) {
				continue;
			} else if (leftNode != null && rightNode != null && leftNode.val == rightNode.val) {
				lt.add(leftNode.left);
				lt.add(rightNode.right);
				lt.add(leftNode.right);
				lt.add(rightNode.left);
			} else {
				return false;
			}
		}
		return true;
	}

	public static boolean isSymmetricOffical(TreeNode root) {
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		q.add(root);
		while (!q.isEmpty()) {
			TreeNode t1 = q.poll();
			TreeNode t2 = q.poll();
			if (t1 == null && t2 == null)
				continue;
			if (t1 == null || t2 == null)
				return false;
			if (t1.val != t2.val)
				return false;
			q.add(t1.left);
			q.add(t2.right);
			q.add(t1.right);
			q.add(t2.left);
		}
		return true;
	}

	public static int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		Stack<TreeNode> st = new Stack<TreeNode>();
		Stack<Integer> it = new Stack<Integer>();

		int maxDepth = 1;
		int curDepth = 1;
		TreeNode curNode = root;
		while (curNode != null) {
			if (curNode.left != null) {
				if (curNode.right != null) {
					st.push(curNode.right);
					it.push(curDepth + 1);
				}
				curNode = curNode.left;
				curDepth += 1;
			} else {
				if (curNode.right != null) {
					curNode = curNode.right;
					curDepth += 1;
				} else {
					if (st.size() > 0) {
						curNode = st.pop();
						curDepth = it.pop();
					} else {
						curNode = null;
						curDepth = -1;
					}
				}
			}

			if (curDepth > maxDepth) {
				maxDepth = curDepth;
			}
		}

		return maxDepth;
	}

	public static int maxDepthR(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int leftDepth = maxDepthR(root.left);
		int rightDepth = maxDepthR(root.right);

		return leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1;
	}

	public static List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> resultList = new ArrayList<List<Integer>>();
		TreeNode curNode = root;
		if (curNode == null) {
			return resultList;
		}
		List<TreeNode> tmpList = new ArrayList<TreeNode>();
		List<Integer> tmpRusltList = new ArrayList<Integer>();
		tmpList.add(curNode);
		tmpRusltList.add(curNode.val);
		resultList.add(tmpRusltList);
		List<TreeNode> perList = tmpList;
		while (perList.size() > 0) {
			tmpList = new ArrayList<TreeNode>();
			tmpRusltList = new ArrayList<Integer>();
			for (int i = 0; i < perList.size(); i++) {
				curNode = perList.get(i);
				if (curNode.left != null) {
					tmpList.add(curNode.left);
					tmpRusltList.add(curNode.left.val);
				}
				if (curNode.right != null) {
					tmpList.add(curNode.right);
					tmpRusltList.add(curNode.right.val);
				}
			}
			perList = tmpList;
			if (tmpRusltList.size() > 0) {
				resultList.add(tmpRusltList);
			}
		}

		return resultList;
	}

	public static List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> resultList = new ArrayList<List<Integer>>();
		TreeNode curNode = root;
		if (curNode == null) {
			return resultList;
		}
		List<TreeNode> tmpList = new ArrayList<TreeNode>();
		List<Integer> tmpRusltList = new ArrayList<Integer>();
		tmpList.add(curNode);
		tmpRusltList.add(curNode.val);
		resultList.add(tmpRusltList);
		List<TreeNode> perList = tmpList;
		while (perList.size() > 0) {
			tmpList = new ArrayList<TreeNode>();
			tmpRusltList = new ArrayList<Integer>();
			for (int i = 0; i < perList.size(); i++) {
				curNode = perList.get(i);
				if (curNode.left != null) {
					tmpList.add(curNode.left);
					tmpRusltList.add(curNode.left.val);
				}
				if (curNode.right != null) {
					tmpList.add(curNode.right);
					tmpRusltList.add(curNode.right.val);
				}
			}
			perList = tmpList;
			if (tmpRusltList.size() > 0) {
				resultList.add(tmpRusltList);
			}
		}

		List<List<Integer>> resultList2 = new ArrayList<List<Integer>>();
		int i = resultList.size() - 1;
		for (; i >= 0; i--) {
			resultList2.add(resultList.get(i));
		}

		return resultList2;
	}

	public static TreeNode sortedArrayToBST(int[] nums, int beg, int end) {
		TreeNode root = null;
		if (nums.length == 0) {
			return root;
		}

		if (beg == end) {
			return new TreeNode(nums[beg]);
		} else if (beg + 1 == end) {
			root = new TreeNode(nums[beg]);
			root.right = new TreeNode(nums[end]);
		} else {
			int mid = (beg + end) / 2;
			root = new TreeNode(nums[mid]);
			root.left = sortedArrayToBST(nums, beg, mid - 1);
			root.right = sortedArrayToBST(nums, mid + 1, end);
		}

		return root;
	}

	public static TreeNode sortedArrayToBST(int[] nums) {
		return sortedArrayToBST(nums, 0, nums.length - 1);
	}

	public static TreeNode sortedListToBST(ListNode head) {
		TreeNode root = null;

		return root;
	}

	public boolean isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}

		if ((Math.abs(maxDepthR(root.left) - maxDepthR(root.right)) <= 1) &&
				isBalanced(root.left) &&
				isBalanced(root.right)) {
			return true;
		}
		return false;
	}

	// 111
	public static int minDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		if ((root.left == null) && (root.right == null)) {
			return 1;
		}

		int min_depth = Integer.MAX_VALUE;
		if (root.left != null) {
			min_depth = Math.min(minDepth(root.left), min_depth);
		}
		if (root.right != null) {
			min_depth = Math.min(minDepth(root.right), min_depth);
		}
		return min_depth + 1;
	}

	// 112
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null) {
			return false;
		}

		int sum2 = sum - root.val;
		if (sum2 == 0 && root.left == null && root.right == null) {
			return true;
		}

		boolean have = false;
		if (root.left != null) {
			have = hasPathSum(root.left, sum2);
		}

		if (!have && root.right != null) {
			have = hasPathSum(root.right, sum2);
		}

		return have;
	}

	public static List<List<Integer>> visitAfter(TreeNode root) {
		return null;
	}

	public static List<List<Integer>> pathSum(TreeNode root, int sum) {
		visitAfter(root.left);
		visitAfter(root.right);
		visitAfter(root);

		List<List<Integer>> resultList = new ArrayList<List<Integer>>();
		if (root == null) {
			return resultList;
		}

		int sum2 = sum - root.val;
		if (sum2 == 0 && root.left == null && root.right == null) {
			return resultList;
		}

		boolean have = false;
		if (root.left != null) {
			// have = hasPathSum(root.left, sum2);
		}

		if (!have && root.right != null) {
			// have = hasPathSum(root.right, sum2);
		}

		return resultList;
	}
}
