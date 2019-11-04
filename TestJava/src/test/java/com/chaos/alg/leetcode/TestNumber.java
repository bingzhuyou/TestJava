package com.chaos.alg.leetcode;

import java.lang.reflect.Method;

import org.junit.Test;

import com.chaos.leetcode.TagNumber;

public class TestNumber {

	public boolean testValue() {
		return true;
	}

	public Boolean testValue1() {
		return true;
	}

	public int testValue2() {
		return 1;
	}

	public int testValue3() {
		return 0;
	}

	@Test
	public void testPrimitiveType() {

		Method[] methods = TestNumber.class.getMethods();

		for (Method m : methods) {
			if (m.getName().startsWith("testValue")) {
				Class<?> returnType = m.getReturnType();

				if (returnType.isPrimitive()) {
					System.out.println(m.getName() + " is primitive");
					if (boolean.class.isAssignableFrom(returnType)) {
						System.out.println(m.getName() + " is boolean");
					} else {
						System.out.println(m.getName() + " is not boolean");
					}
				} else {
					System.out.println(m.getName() + " is not primitive");
					if (Boolean.class.isAssignableFrom(returnType)) {
						System.out.println(m.getName() + " is Boolean");
					} else {
						System.out.println(m.getName() + " is not Boolean");
					}
				}
			}
		}
		Boolean object = false;
		Boolean b1 = true;
		// boolean result = boolean.class;
		boolean result1 = b1 instanceof Boolean;

		boolean[] bArr = { true, false };

		if (bArr.getClass().isArray()) {

			Class<?> componentType;
			componentType = bArr.getClass().getComponentType();

			if (componentType.isPrimitive()) {
				System.out.println("is isPrimitive");
				if (boolean.class.isAssignableFrom(componentType)) {
					System.out.println("is boolean");
				} else {
					System.out.println("not boolean");
				}
			} else {

				System.out.println("not isPrimitive");
			}
		}

		// }
	}

	@Test
	public void testReserveBit() {
		// String input = "11111111111111111111111111111101";
		// long k = 4294967293L;
		// long j = 3221225471L;
		//
		// k -= Integer.MAX_VALUE;
		// System.out.println(k);

		// System.out.println(Integer.parseInt(input, 2));
		// System.out.println(Integer.parseInt(input, 2));
	}

	@Test
	public void testlargestNumber() {
		int[] digits = { 10, 2 };
		System.out.println(TagNumber.largestNumber(digits));
		int[] digits2 = { 3, 30 };
		System.out.println(TagNumber.largestNumber(digits2));
		int[] digits3 = { 34, 3, 30 };
		System.out.println(TagNumber.largestNumber(digits3));
		int[] digits4 = { 333, 3, 334, 330 };
		System.out.println(TagNumber.largestNumber(digits4));
		int[] digits5 = { 20, 1 };
		System.out.println(TagNumber.largestNumber(digits5));
		int[] digits6 = { 1, 20 };
		System.out.println(TagNumber.largestNumber(digits6));
		int[] digits7 = { 121, 12 };
		System.out.println(TagNumber.largestNumber(digits7));
		int[] digits8 = { 12, 121 };
		System.out.println(TagNumber.largestNumber(digits8));
		int[] digits9 = { 0, 0 };
		System.out.println(TagNumber.largestNumber(digits9));
		int[] digits10 = { 0, 0, 0 };
		System.out.println(TagNumber.largestNumber(digits10));
		int[] digits11 = { 0, 0, 0, 0 };
		System.out.println(TagNumber.largestNumber(digits11));
	}

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
