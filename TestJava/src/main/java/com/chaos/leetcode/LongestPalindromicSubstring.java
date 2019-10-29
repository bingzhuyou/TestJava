package com.chaos.leetcode;

public class LongestPalindromicSubstring {
	public static boolean isPalindrome(char[] cha, int beg, int end) {

		while (beg < end) {
			if (cha[beg] == cha[end]) {
				beg++;
				end--;
			} else {
				return false;
			}
		}
		return true;
	}

	public static String longestPalindrome(String s) {

		char[] chArr = s.toCharArray();
		if (chArr.length < 1) {
			return null;
		}

		int maxLen = 0;
		int pos = 0;
		int pos2 = 0;
		for (int i = 0; i < chArr.length - 1; i++) {
			if (maxLen > chArr.length - i - 1) {
				break;
			}
			for (int j = chArr.length - 1; j > i; j--) {

				if (isPalindrome(chArr, i, j)) {
					if (j - i > maxLen) {
						maxLen = j - i;
						pos = i;
						pos2 = j;
					}
					break;
				}
			}
		}

		return s.substring(pos, pos2 + 1);
	}

	public static void main(String args[]) {
		String s = "a";

		System.out.println(longestPalindrome(s));

	}
}
