package com.chaos.leetcode;

public class RegularExpressionMatching {
	public static boolean isMatch(String s, String p) {
		char[] pChArr = p.toCharArray();
		char[] sChArr = s.toCharArray();

		int mPos = 0;
		for (int i = 0; i < sChArr.length && mPos < pChArr.length;) {
			if (pChArr[mPos] == '.') {
				mPos++;
				i++;
			} else if (pChArr[mPos] == '*') {
				if (mPos == 0) {
					mPos++;
					i--;
				} else {
					if (pChArr[mPos - 1] == '.') {
						return true;
					}
					if (sChArr[i] != pChArr[mPos - 1]) {
						mPos++;
					}
				}
			} else {
				if (sChArr[i] != pChArr[mPos]) {
					return false;
				}
				mPos++;
				i++;
			}
		}
		return true;
	}

	public static void main(String args[]) {

		System.out.println("isMatch(\"aaab\", \"a*b\") :" + isMatch("aaab", "a*b"));
		System.out.println("isMatch(\"aaab\", \"a*b\") :" + isMatch("aaab", "aaab"));
		System.out.println("isMatch(\"aaab\", \"*aaab\") :" + isMatch("aaab", "*aaab"));
		System.out.println("isMatch(\"b\", \"a*c*b*\") :" + isMatch("b", "*a*c*b*"));
	}
}
