package com.chaos.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GenerateParentheses {
	public static List<String> generateParenthesis(int n) {
		List<String> ret = new ArrayList<String>();
		Stack<StringBuffer> tmpMatcher = new Stack<StringBuffer>();
		Stack<Integer> tmpMark = new Stack<Integer>();

		int mat = 0;
		int left = n, right = n;
		StringBuffer curSB = new StringBuffer();
		while (mat > 0 || left > 0 || right > 0 || !tmpMatcher.isEmpty()) {
			if (left > 0) {
				if (mat > 0) {
					// for
					StringBuffer tmpSB = new StringBuffer(curSB);
					tmpSB.append(')');
					int tmpLeft = left;
					int tmpRight = right - 1;
					int tmpMat = mat - 1;
					tmpMatcher.push(tmpSB);
					tmpMark.push(tmpLeft);
					tmpMark.push(tmpRight);
					tmpMark.push(tmpMat);
				}

				mat++;
				left--;
				curSB.append('(');
			} else {
				if (right > 0) {
					for (; right > 0; right--) {
						curSB.append(')');
						mat--;
					}

					ret.add(curSB.toString());
				}

				if (tmpMatcher.isEmpty()) {
					continue;
				} else {
					curSB = tmpMatcher.pop();
					mat = tmpMark.pop();
					right = tmpMark.pop();
					left = tmpMark.pop();
				}
			}
		}

		return ret;
	}

	public static void main(String args[]) {
		System.out.println(generateParenthesis(4));
	}
}
