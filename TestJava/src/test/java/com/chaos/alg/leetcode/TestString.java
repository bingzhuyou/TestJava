package com.chaos.alg.leetcode;

import org.junit.Test;

import com.chaos.leetcode.TagString;

public class TestString {

	@Test
	public void testfirstUniqChar() {
		System.out.println(TagString.firstUniqChar("leetcode"));
		System.out.println(TagString.firstUniqChar("loveleetcode"));
		// System.out.println(TagString.firstUniqChar("3[a]2[bc]"));
		// System.out.println(TagString.firstUniqChar("3[a2[c]]"));
		// System.out.println(TagString.firstUniqChar("3[a2[c5[d]]]"));
		// System.out.println(TagString.firstUniqChar("2[abc]3[cd]ef"));
		// System.out.println(TagString.firstUniqChar("aa2[abc]3[cd]ef"));
		// System.out.println(TagString.firstUniqChar("aaaaaaaaa"));
	}

	@Test
	public void testdecodeString() {
		System.out.println(TagString.decodeString("a2[c]"));
		System.out.println(TagString.decodeString("a2[c]ee"));
		System.out.println(TagString.decodeString("3[a]2[bc]"));
		System.out.println(TagString.decodeString("3[a2[c]]"));
		System.out.println(TagString.decodeString("3[a2[c5[d]]]"));
		System.out.println(TagString.decodeString("2[abc]3[cd]ef"));
		System.out.println(TagString.decodeString("aa2[abc]3[cd]ef"));
		System.out.println(TagString.decodeString("aaaaaaaaa"));
	}

	@Test
	public void testremoveDuplicates() {
		System.out.println(TagString.removeDuplicates(""));
		System.out.println(TagString.removeDuplicates("b"));
		System.out.println(TagString.removeDuplicates("abbaca"));
		System.out.println(TagString.removeDuplicates("aaaaaaaa"));
		System.out.println(TagString.removeDuplicates("aaaaaaaaa"));
	}

	@Test
	public void testcanConstruct() {
		System.out.println(TagString.canConstruct("a", "b"));
		System.out.println(TagString.canConstruct("bg", "bkadsjfkabgljkjljflaksdjfklggldkjabbb"));
	}

	@Test
	public void testatoi() {
		System.out.println(TagString.myAtoi(""));
		System.out.println(TagString.myAtoi("42"));
		System.out.println(TagString.myAtoi("-42"));
		System.out.println(TagString.myAtoi("    31122"));
		System.out.println(TagString.myAtoi("   2222jkjdkf"));
		System.out.println(TagString.myAtoi("kkk  2222jkjdkf"));
		System.out.println(TagString.myAtoi("22222222222222222222222222"));
		System.out.println(TagString.myAtoi("-22222222222222222222222222"));
	}

	@Test
	public void testconvert() {
		System.out.println(TagString.convert("LEETCODEISHIRING", 3));
		System.out.println(TagString.convert("LEETCODEISHIRING", 4));
		System.out.println(TagString.convert("LEETCODEISHIRING", 5));
		System.out.println(TagString.convert("LEETCODEISHIRING", 2));
	}

	@Test
	public void testlongestPalindrome() {
		System.out.println(TagString.longestPalindrome("cbababb"));
		System.out.println(TagString.longestPalindrome("bbabababb"));
		System.out.println(TagString.longestPalindrome("a"));
		System.out.println(TagString.longestPalindrome("ac"));
	}

	@Test
	public void testreverseWords() {
		System.out.println(TagString.reverseWords("this is a new world"));
		System.out.println(TagString.reverseWords("this is    a    new world"));
	}

	@Test
	public void testReveserStr() {
		System.out.println(TagString.reverseStr("ajdkfjkajdskf"));
		System.out.println(TagString.reverseStr("1ajdkfjkajdskf2"));
		System.out.println(TagString.reverseStr2("ajdkfjkajdskf"));
		System.out.println(TagString.reverseStr2("1ajdkfjkajdskf2"));
	}

	@Test
	public void testStrStr() {
		System.out.println(TagString.strStr("", ""));
		System.out.println(TagString.strStr("", "ab"));
		System.out.println(TagString.strStr("ab", ""));
		System.out.println(TagString.strStr("abbbbb", "ab"));
		System.out.println(TagString.strStr("a", "a"));
		System.out.println(TagString.strStr("abbbbb", "bbbbbbbbb"));
		System.out.println(TagString.strStr("abcdefgachkajdkfj", "jdk"));

	}

	@Test
	public void testLengthOfLastWord() {
		System.out.println(TagString.lengthOfLastWord(""));
		System.out.println(TagString.lengthOfLastWord("            "));
		System.out.println(TagString.lengthOfLastWord(null));
		System.out.println(TagString.lengthOfLastWord("kdjfaldsf akljdsflkjasdlf lkjds"));
		System.out.println(TagString.lengthOfLastWord("kdjfaldsf akljdsflkjasdlf lkjds   "));
		System.out.println(TagString.lengthOfLastWord("kdjfaldsf"));
	}

	@Test
	public void testLengthOfLongestSubstring() {
		System.out.println(TagString.lengthOfLongestSubstring(""));
		System.out.println(TagString.lengthOfLongestSubstring("            "));
		System.out.println(TagString.lengthOfLongestSubstring(null));
		System.out.println(TagString.lengthOfLongestSubstring("kdjfaldsf akljdsflkjasdlf lkjds"));
		System.out.println(TagString.lengthOfLongestSubstring("au"));
		System.out.println(TagString.lengthOfLongestSubstring("abcabcbb"));
		System.out.println(TagString.lengthOfLongestSubstring("pwwkew"));
		System.out.println(TagString.lengthOfLongestSubstring("bbbbb"));
		System.out.println(TagString.lengthOfLongestSubstring("dvdf"));
		System.out.println(TagString.lengthOfLongestSubstring("cdd"));
	}

	@Test
	public void testIsValidBrackets() {
		System.out.println(TagString.isValidBrackets("()"));
		System.out.println(TagString.isValidBrackets("[[[(){}]]]"));
		System.out.println(TagString.isValidBrackets("[[[(){}]]"));
		System.out.println(TagString.isValidBrackets("[[[(){}]]]}"));
	}

}
