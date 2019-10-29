package com.chaos.alg.leetcode;

import org.junit.Test;

import com.chaos.leetcode.TagNumber;

public class TestNumber {

	@Test
	public void testCountAndSay() {
		System.out.println(TagNumber.countAndSay(1));
		System.out.println(TagNumber.countAndSay(2));
		System.out.println("############################################");
		TagNumber.countAndSay(30);
	}

	@Test
	public void testaddStrings() {
		System.out.println(TagNumber.addStrings("", ""));
		System.out.println(TagNumber.addStrings("", "123213"));
		System.out.println(TagNumber.addStrings("123123213", ""));
		System.out.println(TagNumber.addStrings(null, ""));
		System.out.println(TagNumber.addStrings("", null));
		System.out.println(TagNumber.addStrings("", ""));
		System.out.println(TagNumber.addStrings(null, null));
		System.out.println(TagNumber.addStrings("123124567645330999999", "123213"));
		System.out.println(TagNumber.addStrings("99999", "123124567645330999999"));
		System.out.println(TagNumber.addStrings("99999", "1"));
	}

	@Test
	public void testPlusOne() {
		int[] digits = { 9 };
		TagNumber.plusOne(digits);
		int[] digits2 = { 9, 9 };
		TagNumber.plusOne(digits2);
		int[] digits3 = { 8, 9, 9 };
		TagNumber.plusOne(digits3);
		int[] digits4 = { 9, 1 };
		TagNumber.plusOne(digits4);
		int[] digits5 = { 9, 2, 3 };
		TagNumber.plusOne(digits5);
		int[] digits6 = { 9, 9, 9 };
		TagNumber.plusOne(digits6);
		int[] digits7 = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		TagNumber.plusOne(digits7);
		int[] digits8 = { 1, 9, 9 };
		TagNumber.plusOne(digits8);
	}

	@Test
	public void testIsPalindrome() {
		System.out.println(TagNumber.isPalindrome(-1000));
		System.out.println(TagNumber.isPalindrome(0));
		System.out.println(TagNumber.isPalindrome(2));
		System.out.println(TagNumber.isPalindrome(10));
		System.out.println(TagNumber.isPalindrome(101));
		System.out.println(TagNumber.isPalindrome(12222221));
		System.out.println(TagNumber.isPalindrome(1222332221));
	}

	@Test
	public void testReserveInt() {
		System.out.println(TagNumber.reverseInt(-1000));
		System.out.println(TagNumber.reverseInt(-123));
		System.out.println(TagNumber.reverseInt(234));
		System.out.println(TagNumber.reverseInt(109));
		System.out.println(TagNumber.reverseInt(1010));
		System.out.println(TagNumber.reverseInt(2147483647));
		System.out.println(TagNumber.reverseInt(-2147483648));
	}

	@Test
	public void testRomanToInt() {
		System.out.println(TagNumber.romanToInt("III"));
		System.out.println(TagNumber.romanToInt("IV"));
		System.out.println(TagNumber.romanToInt("IX"));
		System.out.println(TagNumber.romanToInt("LVIII"));
		System.out.println(TagNumber.romanToInt("MCMXCIV"));
		System.out.println(TagNumber.romanToInt("MDCCCLXXXIV"));
	}

	@Test
	public void testAddBinary() {
		System.out.println(TagNumber.addBinary("101", "1"));
		System.out.println(TagNumber.addBinary("1", "101"));
		System.out.println(TagNumber.addBinary("111", "1"));
		System.out.println(TagNumber.addBinary("", ""));
		System.out.println(TagNumber.addBinary(null, null));
		System.out.println(TagNumber.addBinary(null, "101"));
		System.out.println(TagNumber.addBinary("1", null));
		System.out.println(TagNumber.addBinary("", "1100"));
		System.out.println(TagNumber.addBinary("1100", ""));
	}

	@Test
	public void testconvertToTitle() {
		System.out.println(TagNumber.convertToTitle(-1));
		System.out.println(TagNumber.convertToTitle(0));
		System.out.println(TagNumber.convertToTitle(7));
		System.out.println(TagNumber.convertToTitle(27));
		System.out.println(TagNumber.convertToTitle(52));
		System.out.println(TagNumber.convertToTitle(227));
		System.out.println(TagNumber.convertToTitle(260));
		System.out.println(TagNumber.convertToTitle(270));
		System.out.println(TagNumber.convertToTitle(701));
		System.out.println(TagNumber.convertToTitle(2700));
		System.out.println(TagNumber.convertToTitle(2899));
		System.out.println(TagNumber.convertToTitle(270001));
		System.out.println(TagNumber.convertToTitle(27000001));
	}

	@Test
	public void testtitleToNumber() {
		System.out.println(TagNumber.titleToNumber(null));
		System.out.println(TagNumber.titleToNumber(""));
		System.out.println(TagNumber.titleToNumber("G"));
		System.out.println(TagNumber.titleToNumber("AA"));
		System.out.println(TagNumber.titleToNumber("AZ"));
		System.out.println(TagNumber.titleToNumber("HS"));
		System.out.println(TagNumber.titleToNumber("IZ"));
		System.out.println(TagNumber.titleToNumber("JJ"));
		System.out.println(TagNumber.titleToNumber("ZY"));
		System.out.println(TagNumber.titleToNumber("CYV"));
		System.out.println(TagNumber.titleToNumber("DGM"));
		System.out.println(TagNumber.titleToNumber("OIJQ"));
		System.out.println(TagNumber.titleToNumber("BGBDUO"));
	}
}
