package com.chaos.leetcode;

import java.util.Arrays;
import java.util.List;

public class IntegertoRoman {
	public static String intToRoman(int num) {
		if (num <= 0 || num > 3999) {
			return "";
		}

		List<String> strInt0 = Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX");
		List<String> strInt1 = Arrays.asList("X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC");
		List<String> strInt2 = Arrays.asList("C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM");

		int remainder = num;
		int quotient = 0;
		StringBuffer romanStr = new StringBuffer();

		while (remainder > 0) {
			if (remainder >= 1000) {
				quotient = remainder / 1000;
				for (int j = 0; j < quotient; j++) {
					romanStr.append('M');
				}
				remainder = remainder % 1000;
			} else if (remainder >= 100) {
				quotient = remainder / 100;
				romanStr.append(strInt2.get(quotient - 1));
				remainder = remainder % 100;
			} else if (remainder >= 10) {
				quotient = remainder / 10;
				romanStr.append(strInt1.get(quotient - 1));
				remainder = remainder % 10;
			} else if (remainder > 0) {
				romanStr.append(strInt0.get(remainder - 1));
				remainder = 0;
			}
		}

		return romanStr.toString();
	}

	public static void main(String args[]) {
		System.out.println(intToRoman(1));
		System.out.println(intToRoman(4));
		System.out.println(intToRoman(7));
		System.out.println(intToRoman(9));
		System.out.println(intToRoman(10));
		System.out.println(intToRoman(40));
		System.out.println(intToRoman(99));
		System.out.println(intToRoman(1436));
		System.out.println(intToRoman(3636));

	}
}
