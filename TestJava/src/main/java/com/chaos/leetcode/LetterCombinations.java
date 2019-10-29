package com.chaos.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinations {
	public static List<String> letterCombinations(String digits) {
		List<String> retStrs = new ArrayList<String>();
		Map<Integer, char[]> phoneNumberMap = new HashMap<Integer, char[]>();

		char[] l0 = { '+' };
		char[] l1 = { ' ' };
		char[] l2 = { 'a', 'b', 'c' };
		char[] l3 = { 'd', 'e', 'f' };
		char[] l4 = { 'g', 'h', 'i' };
		char[] l5 = { 'j', 'k', 'l' };
		char[] l6 = { 'm', 'n', 'o' };
		char[] l7 = { 'p', 'q', 'r', 's' };
		char[] l8 = { 't', 'u', 'v' };
		char[] l9 = { 'w', 'x', 'y', 'z' };

		phoneNumberMap.put(0, l0);
		phoneNumberMap.put(1, l1);
		phoneNumberMap.put(2, l2);
		phoneNumberMap.put(3, l3);
		phoneNumberMap.put(4, l4);
		phoneNumberMap.put(5, l5);
		phoneNumberMap.put(6, l6);
		phoneNumberMap.put(7, l7);
		phoneNumberMap.put(8, l8);
		phoneNumberMap.put(9, l9);

		char[] digitsCh = digits.toCharArray();

		List<Integer> intList = new ArrayList<Integer>();

		for (int i = 0; i < digitsCh.length; i++) {
			int tmp = (int) (digitsCh[i] - 48);
			System.out.println(tmp);
			if (tmp > 10 || tmp < 0) {
				break;
			}

			intList.add(tmp);
		}

		if (intList.isEmpty()) {
			return retStrs;
		}

		List<StringBuffer> retStrBufs = new ArrayList<StringBuffer>();

		char[] curChArr = phoneNumberMap.get(intList.get(0));
		for (int j = 0; j < curChArr.length; j++) {
			StringBuffer tmpSB = new StringBuffer();
			tmpSB.append(curChArr[j]);
			retStrBufs.add(tmpSB);
		}

		for (int i = 1; i < intList.size(); i++) {
			curChArr = phoneNumberMap.get(intList.get(i));

			int curSize = retStrBufs.size();
			for (int j = 0; j < curSize; j++) {
				StringBuffer tmpSB = retStrBufs.get(j);
				for (int k = 1; k < curChArr.length; k++) {
					StringBuffer tmpSB1 = new StringBuffer(tmpSB);
					tmpSB1.append(curChArr[k]);
					retStrBufs.add(tmpSB1);
				}
				tmpSB.append(curChArr[0]);
			}

		}

		for (int i = 0; i < retStrBufs.size(); i++) {
			retStrs.add(retStrBufs.get(i).toString());
		}

		return retStrs;
	}

	public static void main(String args[]) {
		String strs2 = "234";

		List<String> retStrs = letterCombinations(strs2);

		if (retStrs.isEmpty()) {
			System.out.println("It's empty!");
		}
		for (String k : retStrs) {
			System.out.println(k);
		}
	}
}
