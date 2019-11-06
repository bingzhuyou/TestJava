package com.chaos.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class TagString {
	// 929
	public static int numUniqueEmails(String[] emails) {
		if (emails.length <= 0) {
			return 0;
		}

		Set<String> emailSet = new HashSet<String>();

		for (String m : emails) {
			String[] fields = m.split("@");
			if (fields.length != 2) {
				continue;
			}
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < fields[0].length(); i++) {
				if (fields[0].charAt(i) == '+') {
					break;
				} else if (fields[0].charAt(i) == '.') {
					continue;
				}
				sb.append(fields[0].charAt(i));
			}
			sb.append('@');
			sb.append(fields[1]);
			emailSet.add(sb.toString());
		}

		return emailSet.size();
	}

	public static int firstUniqChar(String s) {
		Map<Character, Integer> mi = new LinkedHashMap<Character, Integer>();
		Map<Character, Integer> cl = new HashMap<Character, Integer>();

		for (int i = 0; i < s.length(); i++) {
			Character c = s.charAt(i);
			if (mi.containsKey(c)) {
				mi.put(c, mi.get(c) + 1);
			} else {
				mi.put(c, 1);
			}

			if (!cl.containsKey(c)) {
				cl.put(c, i);
			}
		}

		for (Map.Entry<Character, Integer> en : mi.entrySet()) {
			if (en.getValue() == 1) {
				return cl.get(en.getKey());
			}
		}

		return -1;
	}

	// 394
	public static String decodeString(String s) {
		StringBuilder sb = new StringBuilder();
		StringBuilder innerSb = new StringBuilder();
		StringBuilder snb = new StringBuilder();

		int innerLB = 0;

		int num = 0;
		boolean isInner = false;

		for (int i = 0; i < s.length(); i++) {

			if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
				if (isInner) {
					innerSb.append(s.charAt(i));
				} else {
					snb.append(s.charAt(i));
				}
			} else if (s.charAt(i) == '[') {
				if (isInner) {
					innerSb.append(s.charAt(i));
					innerLB++;
				} else {
					isInner = true;
					if (snb.length() > 0) {
						try {
							num = Integer.parseInt(snb.toString());
							snb.delete(0, snb.length());
						} catch (Exception e) {
							return "";
						}
					} else {
						return "";
					}
				}
			} else if (s.charAt(i) == ']') {
				if (isInner) {
					if (innerLB > 0) {
						innerLB--;
						innerSb.append(s.charAt(i));
					} else {
						String innStr = decodeString(innerSb.toString());
						innerSb.delete(0, innerSb.length());
						for (int j = 0; j < num; j++) {
							sb.append(innStr);
						}
						num = 0;
						isInner = false;
					}
				} else {
					return "";
				}
			} else {
				if (isInner) {
					innerSb.append(s.charAt(i));
				} else {
					sb.append(s.charAt(i));
				}
			}
		}

		return sb.toString();
	}

	// 1047
	public static String removeDuplicates(String S) {
		StringBuilder sb = new StringBuilder();
		Stack<Character> sc = new Stack<Character>();
		// boolean isDup = false;
		int i = 0;
		while (i < S.length()) {
			if (sc.isEmpty()) {
				sc.push(S.charAt(i));
				// isDup = false;
				i++;
			} else {
				// if (isDup) {
				// if (!sc.peek().equals(S.charAt(i))) {
				// isDup = false;
				// sc.pop();
				// } else {
				// i++;
				// }
				// } else {
				if (sc.peek().equals(S.charAt(i))) {
					sc.pop();
					i++;
				} else {
					sc.push(S.charAt(i));
					i++;
				}
				// }
			}
		}
		// if (isDup) {
		// sc.pop();
		// }

		while (sc.size() > 0) {
			sb.insert(0, sc.pop());
		}

		return sb.toString();
	}

	// 434
	public static int countSegments(String s) {
		int num = 0;
		boolean isAlpha = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			// if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
			if (c != ' ') {
				isAlpha = true;
			} else {
				if (isAlpha) {
					num++;
				}
				isAlpha = false;
			}
		}
		if (isAlpha) {
			num++;
		}
		return num;
	}

	// 383
	public static boolean canConstruct(String ransomNote, String magazine) {
		Map<Character, Integer> rm = new HashMap<Character, Integer>();
		Map<Character, Integer> mm = new HashMap<Character, Integer>();

		for (int i = 0; i < ransomNote.length(); i++) {
			Character c = ransomNote.charAt(i);
			if (rm.containsKey(c)) {
				int val = rm.get(c);
				rm.put(c, val + 1);
			} else {
				rm.put(c, 1);
			}
		}

		for (int i = 0; i < magazine.length(); i++) {
			Character c = magazine.charAt(i);
			if (mm.containsKey(c)) {
				int val = mm.get(c);
				mm.put(c, val + 1);
			} else {
				mm.put(c, 1);
			}
		}

		for (Map.Entry<Character, Integer> en : rm.entrySet()) {
			if (!mm.containsKey(en.getKey()) || !(mm.get(en.getKey()).intValue() >= en.getValue().intValue())) {
				return false;
			}
		}

		return true;
	}

	// 345
	public static String reverseVowels(String s) {
		if (s == null || s.length() <= 1) {
			return s;
		}
		char[] chr = s.toCharArray();

		List<Integer> li = new ArrayList<Integer>();

		for (int i = 0; i < chr.length; i++) {
			if (chr[i] == 'a' || chr[i] == 'e' || chr[i] == 'o' || chr[i] == 'i' || chr[i] == 'u' || chr[i] == 'A'
					|| chr[i] == 'E' || chr[i] == 'O' || chr[i] == 'I' || chr[i] == 'U') {
				li.add(i);
			}
		}

		int left = 0;
		int right = li.size() - 1;

		while (right > left) {
			char temp = chr[li.get(left)];
			chr[li.get(left)] = chr[li.get(right)];
			chr[li.get(right)] = temp;
			left++;
			right--;
		}

		return new String(chr);
	}

	// 242
	public boolean isAnagram(String s, String t) {

		if (s == null || t == null) {
			return false;
		}

		if (s.length() == 0 && t.length() == 0) {
			return true;
		}

		if (s.length() != t.length()) {
			return false;
		}

		char[] scArr = s.toCharArray();
		char[] tcArr = t.toCharArray();
		Arrays.sort(scArr);
		Arrays.sort(tcArr);
		return Arrays.equals(scArr, tcArr);
	}

	// 8
	public static int myAtoi(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		char[] chs = str.toCharArray();
		long tempV = 0;
		int beg = 0;
		boolean nagtive = false;

		while (beg < chs.length && chs[beg] == ' ') {
			beg++;
		}

		for (int i = beg; i < chs.length; i++) {
			if (chs[i] == '-') {
				if (i == beg) {
					nagtive = true;
				} else {
					break;
				}
			} else if (chs[i] == '+') {
				if (i == beg) {
					nagtive = false;
				} else {
					break;
				}
			} else if (chs[i] >= '0' && chs[i] <= '9') {

				if (tempV == 0) {
					tempV = (int) chs[i] - 48;
				} else {
					tempV *= 10;
					tempV += (int) chs[i] - 48;
				}

				if (!nagtive && tempV > Integer.MAX_VALUE) {
					return Integer.MAX_VALUE;
				} else if (nagtive && tempV - 1 > Integer.MAX_VALUE) {
					return Integer.MIN_VALUE;
				}
			} else {
				break;
			}
		}
		System.out.println("jjjj" + tempV);
		if (nagtive) {
			tempV *= -1;
		}

		return (int) tempV;
	}

	// 6
	/*
	 * 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 L E E T C O D E I S H I R I N G 0,0 1,0
	 * 2,0 1,1 0,2 1,2 2,2 1,3 0,4 1,4 2,4 1,5 0,6 1,6 2,6 1,7
	 * 
	 * L C I R E T O E S I I G E D H N
	 * 
	 * 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 L E E T C O D E I S H I R I N G 0,0 1,0
	 * 2,0 3,0 2,1 1,2 0,3 1,3 2,3 3,3 2,4 1,5 0,6 1,6 2,6 3,6
	 * 
	 * L D R E O E I I E C I H N T S G
	 */
	public static String convert(String s, int numRows) {
		if (s.length() < 3 || numRows <= 1) {
			return s;
		}

		char[] chs = s.toCharArray();
		int m = numRows * 2 - 2;
		int l = chs.length / m;
		if (chs.length > m * l) {
			l += 1;
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < numRows; i++) {
			int k = i;
			int k1 = m - i;
			while (k < chs.length) {
				sb.append(chs[k]);
				k += m;
				if (i != 0 && i != numRows - 1) {
					if (k1 >= chs.length) {
						break;
					}
					sb.append(chs[k1]);
					k1 += m;
				}
			}
		}

		return sb.toString();
	}

	public static String longestPalindrome(String s) {
		if (s == null || s.length() < 2) {
			return s;
		}
		char[] chs = s.toCharArray();
		String ret = "";
		boolean found = true;

		for (int i = 0; i < chs.length - 1; i++) {
			int j;
			for (j = chs.length - 1; j > i; j--) {
				int end = j;
				found = true;
				int beg = i;
				while (end > beg) {
					if (chs[beg] == chs[end]) {
						beg++;
						end--;
					} else {
						found = false;
						break;
					}
				}

				if (found) {
					if (j - i > ret.length() - 1) {
						ret = new String(chs, i, j - i + 1);
					}
					break;
				}
			}
		}
		if (ret.length() == 0) {
			return s.substring(0, 1);
		}
		return ret;
	}

	// 151
	public static String reverseWords(String s) {
		StringBuffer sb = new StringBuffer();

		char[] chs = s.toCharArray();
		Stack<String> ss = new Stack<String>();

		for (int i = 0; i < chs.length; i++) {
			if (chs[i] != ' ') {
				sb.append(chs[i]);
			} else {
				if (sb.length() > 0) {
					ss.push(sb.toString());
					sb = new StringBuffer();
				}
			}
		}
		if (sb.length() > 0) {
			ss.push(sb.toString());
		}
		sb = new StringBuffer();
		while (ss.size() > 0) {
			sb.append(ss.pop());
			if (ss.size() > 0) {
				sb.append(" ");
			}
		}

		return sb.toString();
	}

	// 344
	public static String reverseStr(String str) {
		if (str == null || str.length() < 2) {
			return str;
		}

		char[] strArr = str.toCharArray();
		int len = str.length();
		int hlen = len / 2;

		for (int i = 0; i < hlen; i++) {
			char ch = strArr[i];
			strArr[i] = strArr[len - i - 1];
			strArr[len - i - 1] = ch;
		}
		return new String(strArr);
	}

	// 344
	public static String reverseStr2(String str) {
		if (str == null || str.length() < 2) {
			return str;
		}

		char[] strArr = str.toCharArray();
		int right = str.length() - 1;
		int left = 0;
		while (right > left) {
			char ch = strArr[right];
			strArr[right] = strArr[left];
			strArr[left] = ch;
			right--;
			left++;
		}
		return new String(strArr);
	}

	public static int strStr(String haystack, String needle) {
		if (needle.length() == 0) {
			return 0;
		}

		if (haystack.length() == 0 || needle.length() > haystack.length()) {
			return -1;
		}

		char hr[] = haystack.toCharArray();
		char nr[] = needle.toCharArray();

		for (int i = 0; i < hr.length; i++) {
			if (hr.length - i < nr.length) {
				return -1;
			}

			boolean find = true;
			for (int j = 0; j < nr.length; j++) {
				if (hr[i + j] != nr[j]) {
					find = false;
					break;
				}
			}

			if (find) {
				return i;
			}
		}

		return -1;
	}

	// 58
	public static int lengthOfLastWord(String s) {
		if (s == null) {
			return 0;
		}
		int len = s.length();
		if (len == 0) {
			return 0;
		}
		char[] chrArr = s.toCharArray();

		boolean checkbegin = false;
		int lastWordLen = 0;
		for (int i = len - 1; i >= 0; i--) {
			if (!checkbegin) {
				if (chrArr[i] == ' ') {
					continue;
				} else {
					checkbegin = true;
					lastWordLen = 1;
					continue;
				}
			}
			if (chrArr[i] == ' ') {
				return lastWordLen;
			} else {
				lastWordLen++;
			}
		}

		return lastWordLen;
	}

	// 3
	public static int lengthOfLongestSubstring(String s) {
		if (s == null) {
			return 0;
		}
		int len = s.length();
		if (len < 2) {
			return len;
		}

		char[] chrArr = s.toCharArray();

		int max = 0;
		int subBeg = 0;
		int subEnd = 0;
		Map<Integer, Integer> ck = new HashMap<Integer, Integer>();
		for (int i = 0; i < len; i++) {
			subEnd = i;
			Integer tmp = ck.get((int) chrArr[i]);
			if (tmp != null && tmp >= subBeg) {
				if (subEnd - subBeg > max) {
					max = subEnd - subBeg;
				}
				subBeg = tmp + 1;
			}
			ck.put((int) chrArr[i], i);
		}
		if (subEnd - subBeg + 1 > max) {
			max = subEnd - subBeg + 1;
		}

		return max;
	}

	// 14
	public static String longestCommonPrefix(String[] strs) {

		String prefix = "";
		if (strs.length > 0) {
			prefix = strs[0];
		}
		for (int i = 1; i < strs.length; i++) {
			char[] pCh = prefix.toCharArray();
			char[] pCh1 = strs[i].toCharArray();
			int j = 0;
			for (; j < pCh.length && j < pCh1.length; j++) {
				if (pCh[j] != pCh1[j]) {
					break;
				}
			}
			prefix = prefix.substring(0, j);
		}
		return prefix;
	}

	// 20
	public static boolean isValidBrackets(String s) {
		Stack<String> vs = new Stack<String>();

		char[] chrArr = s.toCharArray();

		for (int i = 0; i < chrArr.length; i++) {
			if (chrArr[i] == '(' || chrArr[i] == '[' || chrArr[i] == '{') {
				vs.push("" + chrArr[i]);
			} else {
				if (vs.empty()) {
					return false;
				}
				String tmpS = vs.pop();
				if ((chrArr[i] == ')' && tmpS.equals("(")) || (chrArr[i] == ']' && tmpS.equals("["))
						|| (chrArr[i] == '}' && tmpS.equals("{"))) {
					continue;
				} else {
					return false;
				}
			}
		}

		if (!vs.empty()) {
			return false;
		}

		return true;
	}

	public static boolean isPalindrome(String s) {
		if (s == null) {
			return true;
		}
		int len = s.length();
		if (len == 0) {
			return true;
		}

		char[] chrArr = s.toCharArray();

		int left = 0;
		int right = len - 1;
		while (right > left) {
			if ((chrArr[right] >= 48 && chrArr[right] <= 57) || (chrArr[right] >= 65 && chrArr[right] <= 90)
					|| (chrArr[right] >= 97 && chrArr[right] <= 122)) {

			} else {
				right--;
				continue;
			}

			if ((chrArr[left] >= 48 && chrArr[left] <= 57) || (chrArr[left] >= 65 && chrArr[left] <= 90)
					|| (chrArr[left] >= 97 && chrArr[left] <= 122)) {

			} else {
				left++;
				continue;
			}

			if (chrArr[right] >= 65 && chrArr[right] <= 90) {
				chrArr[right] += 32;
			}

			if (chrArr[left] >= 65 && chrArr[left] <= 90) {
				chrArr[left] += 32;
			}

			if (chrArr[left] != chrArr[right]) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}
}
