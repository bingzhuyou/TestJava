package com.chaos.leetcode;

import java.util.ArrayList;
import java.util.List;

public class StringToInteger {
	public static int myAtoi(String str) {

		str = str.trim();
		int k = 0;
		boolean neg = false;

		if (str == null || str.length() < 1) {
			return k;
		}

		char[] chArr = str.toCharArray();
		List<Integer> intList = new ArrayList<Integer>();

		for (int i = 0; i < chArr.length; i++) {
			if (chArr[i] < 48 || chArr[i] > 57) {
				if (i == 0) {
					if (chArr[i] == 43) {
						neg = false;
					} else if (chArr[i] == 45) {
						neg = true;
					} else {
						break;
					}
				} else {
					break;
				}
			} else {
				if (intList.size() == 0) {
					if (chArr[i] != 48) {
						intList.add((int) (chArr[i] - 48));
					}
				} else {
					intList.add((int) (chArr[i] - 48));
					if (intList.size() > 10) {
						if (neg) {
							return Integer.MIN_VALUE;
						} else {
							return Integer.MAX_VALUE;
						}
					}
				}
			}
		}

		// Integer.MAX_VALUE; 2147483647
		// Integer.MIN_VALUE; -2147483648

		long lval = 0;
		long powd = 0;

		for (int i = intList.size() - 1; i >= 0; i--) {
			powd = 1;
			for (int j = 0; j < intList.size() - i - 1; j++) {
				powd *= 10;
			}
			lval += intList.get(i) * powd;
		}

		if (neg) {
			lval = -lval;
		}

		if (lval > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		} else if (lval < Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		}

		return (int) lval;
	}

	public static void main(String args[]) {
		String k = "1234343";

		System.out.println(myAtoi(k));
		k = "-1232132";
		System.out.println(myAtoi(k));
		k = "+232132";
		System.out.println(myAtoi(k));
		k = "-0001232132";
		System.out.println(myAtoi(k));
		k = "+00000000001232132";
		System.out.println(myAtoi(k));
		k = "1231231231232132";
		System.out.println(myAtoi(k));
		k = "-121232131231231232132";
		System.out.println(myAtoi(k));
		k = "1232132adfdf123";
		System.out.println(myAtoi(k));
		k = "aaaa1232132";
		System.out.println(myAtoi(k));
		k = "     100";
		System.out.println(myAtoi(k));
	}
}
