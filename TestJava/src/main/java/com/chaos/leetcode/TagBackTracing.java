package com.chaos.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagBackTracing {

	// 401
	public static List<String> readBinaryWatch(int num) {
		List<String> ls = new ArrayList<String>();
		if (num <= 0) {
			return ls;
		}

		int[] hour = { 1, 2, 4, 8 };
		int[] min = { 1, 2, 4, 8, 16, 32 };
		Map<Integer, List<Integer>> hourMap = new HashMap<Integer, List<Integer>>();
		Map<Integer, List<Integer>> minMap = new HashMap<Integer, List<Integer>>();

		for (int i = 1; i < 4; i++) {
			List<Integer> li = new ArrayList<Integer>();
			int v = 0;
			for (int j = 0; j < hour.length; j++) {
				v = hour[j];
				for (int k = i - 1; k > 0; k--) {
					v += hour[k + j];
				}
				if (v < 13) {
					li.add(v);
				}
			}
			hourMap.put(i, li);
		}

		return ls;
	}

	// 784
	public static List<String> letterCasePermutation(String S) {
		List<String> ls = new ArrayList<String>();

		if (S == null) {
			return ls;
		}

		ls.add(S);

		Map<Integer, Character> lc = new HashMap<Integer, Character>();

		char[] chrArr = S.toCharArray();
		for (int i = 0; i < chrArr.length; i++) {
			if ((chrArr[i] >= 'A' && chrArr[i] <= 'Z') || (chrArr[i] >= 'a' && chrArr[i] <= 'z')) {
				lc.put(i, chrArr[i]);
				int cSize = ls.size();
				char tc = chrArr[i];
				if (chrArr[i] >= 'A' && chrArr[i] <= 'Z') {
					tc += 32;
				}
				if (chrArr[i] >= 'a' && chrArr[i] <= 'z') {
					tc -= 32;
				}
				for (int j = 0; j < cSize; j++) {
					char[] n = ls.get(j).toCharArray();
					n[i] = tc;
					ls.add(new String(n));
				}
			}
		}

		return ls;
	}
}
