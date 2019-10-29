package com.chaos.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TagNumber {

	// 415
	public static String addStrings(String num1, String num2) {
		StringBuilder sb = new StringBuilder();
		if (num1 == null || num1.length() == 0) {
			if (num2 == null || num2.length() == 0) {
				sb.append(0);
				return sb.toString();
			} else {
				return num2;
			}
		} else {
			if (num2 == null || num2.length() == 0) {
				return num1;
			}
		}

		int upS = 0;
		int sum = 0;
		int n1 = 0;
		int n2 = 0;

		int nl1 = num1.length() - 1;
		int nl2 = num2.length() - 1;

		for (; nl1 >= 0 || nl2 >= 0; nl1--, nl2--) {
			if (nl1 < 0) {
				n1 = 0;
			} else {
				n1 = num1.charAt(nl1) - 48;
			}
			if (nl2 < 0) {
				n2 = 0;
			} else {
				n2 = num2.charAt(nl2) - 48;
			}
			sum = n1 + n2 + upS;
			if (sum >= 10) {
				upS = 1;
				sum = sum % 10;
			} else {
				upS = 0;
			}
			sb.insert(0, sum);
		}
		if (upS > 0) {
			sb.insert(0, upS);
		}

		return sb.toString();
	}

	// 371
	public static int getSum(int a, int b) {
		int x = a ^ b;
		int y = a & b;

		while (y != 0) {
			y = y << 1;
			int temp = x;
			x = x ^ y;
			y = temp & y;
		}

		return x;
	}

	// 326
	public boolean isPowerOfThree(int n) {
		int y = n;
		int b = 0;
		while (y > 1) {
			b = y % 3;
			if (b != 0) {
				return false;
			}
			y = y / 3;
		}
		if (y == 1) {
			return true;
		} else {
			return false;
		}
	}

	public int reverseBits(int n) {
		char[] chrArr = Integer.toBinaryString(n).toCharArray();
		int left = 0;
		int right = chrArr.length - 1;

		while (right > left) {
			char tmp = chrArr[left];
			chrArr[left] = chrArr[right];
			chrArr[right] = tmp;
			left++;
			right--;
		}

		int ret = -1;
		try {
			ret = Integer.parseInt(new String(chrArr), 2);
		} catch (Exception e) {

		}

		return ret;
	}

	public static String countAndSay(int n) {
		if (n < 1 || n > 30) {
			return "";
		}
		if (n == 1) {
			return "1";
		}
		char[] initBuff = new char[1];
		initBuff[0] = '1';
		for (int i = 0; i < n - 1; i++) {
			int curVal = initBuff[0];
			int cnt = 1;
			StringBuffer sb = new StringBuffer();
			for (int j = 1; j < initBuff.length; j++) {
				if (curVal != initBuff[j]) {
					sb.append(cnt);
					sb.append(curVal - 48);
					curVal = initBuff[j];
					cnt = 1;
				} else {
					cnt++;
				}
			}
			sb.append(cnt);
			sb.append(curVal - 48);
			initBuff = sb.toString().toCharArray();

			System.out.println(i + " : " + sb.toString());
		}

		return new String(initBuff);
	}

	// 66
	public static int[] plusOne(int[] digits) {
		int len = digits.length;

		for (int l = 0; l < len; l++) {
			System.out.print(digits[l] + " ");
		}

		System.out.println(" ");

		boolean isLast = true;
		int last9Number = 0;
		for (int i = len - 1; i >= 0; i--) {
			if (isLast && digits[i] == 9) {
				last9Number++;
			} else {
				isLast = false;
				break;
			}
		}
		int ret[];

		int retlen = len;
		int chgVal;
		if (last9Number == len) {
			retlen += 1;
			chgVal = 1;
		} else {
			chgVal = digits[retlen - last9Number - 1] + 1;
		}

		ret = new int[retlen];
		for (int k = retlen - 1; k > retlen - last9Number - 1; k--) {
			ret[k] = 0;
		}
		ret[retlen - last9Number - 1] = chgVal;

		for (int j = retlen - last9Number - 2; j >= 0; j--) {
			ret[j] = digits[j];
		}

		for (int l = 0; l < retlen; l++) {
			System.out.print(ret[l] + " ");
		}

		System.out.println(" ");

		return ret;

	}

	// 2
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode retL = null;
		ListNode curL = null;

		ListNode curL1 = l1;
		ListNode curL2 = l2;

		int upVal = 0;
		while (curL1 != null || curL2 != null) {
			if (curL1 != null && curL2 != null) {
				int tempVal = curL1.val + curL2.val + upVal;
				if (tempVal >= 10) {
					tempVal -= 10;
					upVal = 1;
				} else {
					upVal = 0;
				}
				ListNode tempNode = new ListNode(tempVal);
				if (curL == null) {
					curL = tempNode;
					if (retL == null) {
						retL = tempNode;
					}
				} else {
					curL.next = tempNode;
					curL = curL.next;
				}
				curL1 = curL1.next;
				curL2 = curL2.next;
			} else if (curL1 != null) {
				int tempVal = curL1.val + upVal;
				if (tempVal >= 10) {
					tempVal -= 10;
					upVal = 1;
				} else {
					upVal = 0;
				}
				ListNode tempNode = new ListNode(tempVal);
				if (curL == null) {
					curL = tempNode;
					if (retL == null) {
						retL = tempNode;
					}
				} else {
					curL.next = tempNode;
					curL = curL.next;
				}
				curL1 = curL1.next;
			} else if (curL2 != null) {
				int tempVal = curL2.val + upVal;
				if (tempVal >= 10) {
					tempVal -= 10;
					upVal = 1;
				} else {
					upVal = 0;
				}
				ListNode tempNode = new ListNode(tempVal);
				if (curL == null) {
					curL = tempNode;
					if (retL == null) {
						retL = tempNode;
					}
				} else {
					curL.next = tempNode;
					curL = curL.next;
				}
				curL2 = curL2.next;
			} else {
				break;
			}
		}

		if (upVal > 0) {
			ListNode tempNode = new ListNode(upVal);
			curL.next = tempNode;
		}

		return retL;
	}

	// 7
	public static int reverseInt(int x) {
		long ret = x;
		boolean isNagtive = false;
		if (ret < 0) {
			ret *= -1;
			isNagtive = true;
		}
		String xStr = "" + ret;
		char[] xChrArr = xStr.toCharArray();

		int left = 0;
		int right = xChrArr.length - 1;

		while (right > left) {
			char tmp = xChrArr[left];
			xChrArr[left] = xChrArr[right];
			xChrArr[right] = tmp;
			right--;
			left++;
		}

		ret = Long.parseLong(new String(xChrArr));
		if (isNagtive) {
			ret *= -1;
		}
		if (ret > Integer.MAX_VALUE) {
			return 0;
		} else if (ret < Integer.MIN_VALUE) {
			return 0;
		}

		return (int) ret;
	}

	// 9
	public static boolean isPalindrome(int x) {
		if (x < 0) {
			return false;
		}

		String xStr = "" + x;
		char[] xChrArr = xStr.toCharArray();

		int left = 0;
		int right = xChrArr.length - 1;

		while (right > left) {
			if (xChrArr[left] != xChrArr[right]) {
				return false;
			}
			right--;
			left++;
		}

		return true;
	}

	// 13

	public static int romanToInt(String s) {
		Map<String, Integer> romanToIntMap = new HashMap<String, Integer>();
		romanToIntMap.put(" ", 0);
		romanToIntMap.put("I", 1);
		romanToIntMap.put("V", 5);
		romanToIntMap.put("X", 10);
		romanToIntMap.put("L", 50);
		romanToIntMap.put("C", 100);
		romanToIntMap.put("D", 500);
		romanToIntMap.put("M", 1000);

		int ret = 0;

		char[] chrArr = s.toCharArray();
		int len = chrArr.length;
		char perChar = ' ';
		for (int i = 0; i < len; i++) {
			if (chrArr[i] == 'V') {
				if (perChar == 'I') {
					ret += 4;
					perChar = ' ';
				} else {
					ret += romanToIntMap.get("" + perChar);
					ret += 5;
					perChar = ' ';
				}
			} else if (chrArr[i] == 'X') {
				if (perChar == 'I') {
					ret += 9;
					perChar = ' ';
				} else {
					ret += romanToIntMap.get("" + perChar);
					perChar = 'X';
				}
			} else if (chrArr[i] == 'L') {
				if (perChar == 'X') {
					ret += 40;
					perChar = ' ';
				} else {
					ret += romanToIntMap.get("" + perChar);
					ret += 50;
					perChar = ' ';
				}
			} else if (chrArr[i] == 'C') {
				if (perChar == 'X') {
					ret += 90;
					perChar = ' ';
				} else {
					ret += romanToIntMap.get("" + perChar);
					perChar = 'C';
				}
			} else if (chrArr[i] == 'D') {
				if (perChar == 'C') {
					ret += 400;
					perChar = ' ';
				} else {
					ret += romanToIntMap.get("" + perChar);
					ret += 500;
					perChar = ' ';
				}
			} else if (chrArr[i] == 'M') {
				if (perChar == 'C') {
					ret += 900;
					perChar = ' ';
				} else {
					ret += romanToIntMap.get("" + perChar);
					ret += 1000;
					perChar = ' ';
				}
			} else if (chrArr[i] == 'I') {
				ret += romanToIntMap.get("" + perChar);
				perChar = 'I';
			} else {
				System.out.println("unknown roman char");
				break;
			}
		}
		ret += romanToIntMap.get("" + perChar);

		return ret;
	}

	public static int romantoInteger(String rStr) {
		int ret = 0;
		if (rStr == null || rStr.trim().length() == 0) {
			return ret;
		}

		char[] rChArr = rStr.toCharArray();
		//
		// char[] cR0 = { 'I', 'X', 'C' };
		// char[] cR1 = { 'V', 'L', 'D' };
		// char[] cR2 = { 'X', 'C', 'M' };

		for (int i = 0; i < rChArr.length; i++) {
			if (rChArr[i] == 'I') {
				if (i + 1 < rChArr.length) {
					if (rChArr[i + 1] == 'V') {
						ret += 4;
						i++;
					} else if (rChArr[i + 1] == 'X') {
						ret += 9;
						i++;
					} else {
						ret += 1;
					}
				} else {
					ret += 1;
				}
			} else if (rChArr[i] == 'X') {
				if (i + 1 < rChArr.length) {
					if (rChArr[i + 1] == 'L') {
						ret += 40;
						i++;
					} else if (rChArr[i + 1] == 'C') {
						ret += 90;
						i++;
					} else {
						ret += 10;
					}
				} else {
					ret += 10;
				}
			} else if (rChArr[i] == 'C') {
				if (i + 1 < rChArr.length) {
					if (rChArr[i + 1] == 'D') {
						ret += 400;
						i++;
					} else if (rChArr[i + 1] == 'M') {
						ret += 900;
						i++;
					} else {
						ret += 100;
					}
				} else {
					ret += 100;
				}
			} else if (rChArr[i] == 'V') {
				ret += 5;
			} else if (rChArr[i] == 'L') {
				ret += 50;
			} else if (rChArr[i] == 'D') {
				ret += 500;
			} else if (rChArr[i] == 'M') {
				ret += 1000;
			}
		}

		return ret;
	}

	// 67
	public static String addBinary(String a, String b) {
		if (a == null) {
			return b;
		}
		if (b == null) {
			return a;
		}

		int lenA = a.length();
		int lenB = b.length();
		char[] chrArrSub;
		char[] chrArrMain;
		int lenMain, lenSub;
		if (lenA >= lenB) {
			chrArrMain = a.toCharArray();
			chrArrSub = b.toCharArray();
			lenMain = lenA;
			lenSub = lenB;
		} else {
			chrArrMain = b.toCharArray();
			chrArrSub = a.toCharArray();
			lenMain = lenB;
			lenSub = lenA;
		}

		int upVal = 0;

		while (lenMain > 0) {
			int val = chrArrMain[lenMain - 1] + upVal - 48;

			if (lenSub > 0) {
				val += chrArrSub[lenSub - 1] - 48;
				lenSub--;
			}

			if (val == 2) {
				upVal = 1;
				chrArrMain[lenMain - 1] = 48;
			} else if (val == 3) {
				upVal = 1;
				chrArrMain[lenMain - 1] = 49;
			} else {
				chrArrMain[lenMain - 1] = (char) (val + 48);
				upVal = 0;
			}

			lenMain--;
		}

		if (upVal > 0) {
			return new String(upVal + new String(chrArrMain));
		}
		return new String(chrArrMain);
	}

	// 69
	public static int mySqrt(int x) {
		if (x <= 0) {
			return 0;
		}

		int ret = x;
		return ret;
	}

	public static int singleNumber(int[] nums) {
		int len = nums.length;
		if (len == 0) {
			return 0;
		}
		int ret = nums[0];

		for (int i = 1; i < len; i++) {
			ret ^= nums[i];
		}
		return ret;
	}

	// 168
	public static String convertToTitle(int n) {
		if (n <= 0) {
			return "";
		}
		Stack<Character> sc = new Stack<Character>();
		while (n > 0) {
			if (n < 27) {
				sc.push((char) (n + 64));
				n = -1;
				break;
			}
			int n1 = n % 26;
			n = n / 26;
			if (n1 == 0) {
				n -= 1;
				sc.push('Z');
			} else {
				sc.push((char) (n1 + 64));
			}
		}
		StringBuffer sb = new StringBuffer();
		while (!sc.empty()) {
			sb.append(sc.pop());
		}

		return sb.toString();
	}

	// 171
	public static int titleToNumber(String s) {
		if (s == null) {
			return 0;
		}

		char[] chrArr = s.toCharArray();

		int ret = 0;
		int pos = 1;
		for (int i = chrArr.length - 1; i >= 0; i--) {
			ret += pos * (chrArr[i] - 64);
			pos *= 26;
		}

		return ret;
	}
}
