package com.chaos.leetcode;

import java.util.HashSet;
import java.util.Set;

public class TagHash {
	public static boolean isHappy(int n) {
		Set<Integer> chkSet = new HashSet<Integer>();

		int nv = 0;
		while (n != 1) {
			if (chkSet.contains(n)) {
				return false;
			} else {
				chkSet.add(n);
			}

			int tmp = 0;
			while (n > 0) {
				tmp = n % 10;
				nv += tmp * tmp;
				n = n / 10;
			}
			n = nv;
			nv = 0;
		}

		return true;
	}
}
