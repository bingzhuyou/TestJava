package com.chaos.alg.leetcode;

import org.junit.Test;

import com.chaos.leetcode.TagTree;
import com.chaos.leetcode.TreeNode;

public class TestTree {

	@Test
	public void testisValidBST() {
		TreeNode root = new TreeNode(2);
		System.out.println(TagTree.isSymmetric(root));
		TreeNode left = new TreeNode(1);
		TreeNode right = new TreeNode(3);
		root.left = left;
		root.right = right;
		System.out.println(TagTree.isValidBST(root));
		TreeNode lr = new TreeNode(3);
		TreeNode rr = new TreeNode(3);
		left.right = lr;
		right.right = rr;
		System.out.println(TagTree.isValidBST(root));
		TreeNode ll = new TreeNode(3);
		TreeNode rl = new TreeNode(4);
		lr.val = 4;
		left.left = ll;
		right.left = rl;
		System.out.println(TagTree.isValidBST(root));
		TreeNode lrl = new TreeNode(8);
		TreeNode lrr = new TreeNode(9);
		TreeNode rll = new TreeNode(9);
		TreeNode rlr = new TreeNode(8);
		lr.left = lrl;
		lr.right = lrr;
		rl.left = rll;
		rlr.right = rlr;
		System.out.println(TagTree.isValidBST(root));
	}

	@Test
	public void testIsSymmetric() {
		TreeNode root = new TreeNode(1);
		System.out.println(TagTree.isSymmetric(root));
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(2);
		root.left = left;
		root.right = right;
		System.out.println(TagTree.isSymmetric(root));
		TreeNode lr = new TreeNode(3);
		TreeNode rr = new TreeNode(3);
		left.right = lr;
		right.right = rr;
		System.out.println(TagTree.isSymmetric(root));
		TreeNode ll = new TreeNode(3);
		TreeNode rl = new TreeNode(4);
		lr.val = 4;
		left.left = ll;
		right.left = rl;
		System.out.println(TagTree.isSymmetric(root));
		TreeNode lrl = new TreeNode(8);
		TreeNode lrr = new TreeNode(9);
		TreeNode rll = new TreeNode(9);
		TreeNode rlr = new TreeNode(8);
		lr.left = lrl;
		lr.right = lrr;
		rl.left = rll;
		rlr.right = rlr;
		System.out.println(TagTree.isSymmetric(root));
	}

	@Test
	public void testIsSymmetricOffical() {
		TreeNode root = new TreeNode(1);
		System.out.println(TagTree.isSymmetricOffical(root));
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(2);
		root.left = left;
		root.right = right;
		System.out.println(TagTree.isSymmetricOffical(root));
		TreeNode lr = new TreeNode(3);
		TreeNode rr = new TreeNode(3);
		left.right = lr;
		right.right = rr;
		System.out.println(TagTree.isSymmetricOffical(root));
		TreeNode ll = new TreeNode(3);
		TreeNode rl = new TreeNode(4);
		lr.val = 4;
		left.left = ll;
		right.left = rl;
		System.out.println(TagTree.isSymmetricOffical(root));
		TreeNode lrl = new TreeNode(8);
		TreeNode lrr = new TreeNode(9);
		TreeNode rll = new TreeNode(9);
		TreeNode rlr = new TreeNode(8);
		lr.left = lrl;
		lr.right = lrr;
		rl.left = rll;
		rlr.right = rlr;
		System.out.println(TagTree.isSymmetricOffical(root));
	}

	@Test
	public void testMaxDepth() {
		TreeNode root = new TreeNode(1);
		System.out.println(TagTree.maxDepth(root));
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(2);
		root.left = left;
		root.right = right;
		System.out.println(TagTree.maxDepth(root));
		TreeNode lr = new TreeNode(3);
		TreeNode rr = new TreeNode(3);
		left.right = lr;
		right.right = rr;
		System.out.println(TagTree.maxDepth(root));
		TreeNode ll = new TreeNode(3);
		TreeNode rl = new TreeNode(4);
		lr.val = 4;
		left.left = ll;
		right.left = rl;
		System.out.println(TagTree.maxDepth(root));
		TreeNode lrl = new TreeNode(8);
		TreeNode lrr = new TreeNode(9);
		TreeNode rll = new TreeNode(9);
		TreeNode rlr = new TreeNode(8);
		lr.left = lrl;
		lr.right = lrr;
		rl.left = rll;
		rlr.right = rlr;
		System.out.println(TagTree.maxDepth(root));

		TreeNode root2 = new TreeNode(1);
		TreeNode r1 = new TreeNode(8);
		TreeNode r2 = new TreeNode(9);
		TreeNode r3 = new TreeNode(9);
		TreeNode r4 = new TreeNode(8);
		root2.right = r1;
		r1.right = r2;
		r2.right = r3;
		r3.right = r4;
		System.out.println(TagTree.maxDepth(root2));
	}

	@Test
	public void testMaxDepthR() {
		TreeNode root = new TreeNode(1);
		System.out.println(TagTree.maxDepthR(root));
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(2);
		root.left = left;
		root.right = right;
		System.out.println(TagTree.maxDepthR(root));
		TreeNode lr = new TreeNode(3);
		TreeNode rr = new TreeNode(3);
		left.right = lr;
		right.right = rr;
		System.out.println(TagTree.maxDepthR(root));
		TreeNode ll = new TreeNode(3);
		TreeNode rl = new TreeNode(4);
		lr.val = 4;
		left.left = ll;
		right.left = rl;
		System.out.println(TagTree.maxDepthR(root));
		TreeNode lrl = new TreeNode(8);
		TreeNode lrr = new TreeNode(9);
		TreeNode rll = new TreeNode(9);
		TreeNode rlr = new TreeNode(8);
		lr.left = lrl;
		lr.right = lrr;
		rl.left = rll;
		rlr.right = rlr;
		System.out.println(TagTree.maxDepthR(root));

		TreeNode root2 = new TreeNode(1);
		TreeNode r1 = new TreeNode(8);
		TreeNode r2 = new TreeNode(9);
		TreeNode r3 = new TreeNode(9);
		TreeNode r4 = new TreeNode(8);
		root2.right = r1;
		r1.right = r2;
		r2.right = r3;
		r3.right = r4;
		System.out.println(TagTree.maxDepthR(root2));
	}

	@Test
	public void testLevelOrder() {
		TreeNode root = new TreeNode(1);
		System.out.println(TagTree.levelOrder(root));
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(2);
		root.left = left;
		root.right = right;
		System.out.println(TagTree.levelOrder(root));
		TreeNode lr = new TreeNode(3);
		TreeNode rr = new TreeNode(3);
		left.right = lr;
		right.right = rr;
		System.out.println(TagTree.levelOrder(root));
		TreeNode ll = new TreeNode(3);
		TreeNode rl = new TreeNode(4);
		lr.val = 4;
		left.left = ll;
		right.left = rl;
		System.out.println(TagTree.levelOrder(root));
		TreeNode lrl = new TreeNode(8);
		TreeNode lrr = new TreeNode(9);
		TreeNode rll = new TreeNode(9);
		TreeNode rlr = new TreeNode(8);
		lr.left = lrl;
		lr.right = lrr;
		rl.left = rll;
		rlr.right = rlr;
		System.out.println(TagTree.levelOrder(root));

		TreeNode root2 = new TreeNode(1);
		TreeNode r1 = new TreeNode(8);
		TreeNode r2 = new TreeNode(9);
		TreeNode r3 = new TreeNode(9);
		TreeNode r4 = new TreeNode(8);
		root2.right = r1;
		r1.right = r2;
		r2.right = r3;
		r3.right = r4;
		System.out.println(TagTree.levelOrderBottom(root2));
	}

	@Test
	public void testMinDepth() {
		TreeNode root = new TreeNode(1);
		TreeNode left = new TreeNode(2);
		root.left = left;
		System.out.println(TagTree.minDepth(root));

	}
}
