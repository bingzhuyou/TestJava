package com.chaos.leetcode;

import java.util.List;

// n branch tree

class Node {
	public int val;
	public List<Node> children;

	public Node() {
		val = 0;
	}

	public Node(int _val, List<Node> _children) {
		val = _val;
		children = _children;
	}
};