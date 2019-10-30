package com.chaos.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TagHash {

	// 506
	public String[] findRelativeRanks(int[] nums) {
		if (nums.length <= 0) {
			return null;
		}
		Map<Integer, Integer> origOrder = new HashMap<Integer, Integer>();
		List<Integer> lnums = new ArrayList<Integer>();

		for (int i = 0; i < nums.length; i++) {
			origOrder.put(nums[i], i);
			lnums.add(nums[i]);
		}

		Collections.sort(lnums, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 > o2) {
					return -1;
				} else if (o1 < o2) {
					return 1;
				} else {
					return 0;
				}
			}

		});
		String[] order = new String[nums.length];

		for (int i = 0; i < nums.length; i++) {
			int o = i + 1;
			if (i == 0) {
				order[origOrder.get(lnums.get(i))] = "Gold Medal";
			} else if (i == 1) {
				order[origOrder.get(lnums.get(i))] = "Silver Medal";
			} else if (i == 2) {
				order[origOrder.get(lnums.get(i))] = "Bronze Medal";
			} else {
				order[origOrder.get(lnums.get(i))] = "" + o;
			}
		}

		return order;
	}

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
