package com.chaos.alg.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.chaos.leetcode.TagArray;

public class TestArray {
	// 17
	public static List<String> letterCombinations(String digits) {
		Map<Integer, List<Character>> cML = new HashMap<Integer, List<Character>>();
		List<Character> l2 = null;
		char c;
		for (c = 'a'; c <= 'z'; c++) {
			if (c % 3 == 1) {
				l2 = new ArrayList<Character>();
			}
			l2.add(c);
			if (c % 3 == 0) {
				cML.put(c - 95, l2);
				l2 = null;
			}
		}

		if (l2 != null) {
			cML.put(c - 96, l2);
		}

		List<String> ret = new ArrayList<String>();
		List<StringBuilder> retB = new ArrayList<StringBuilder>();

		for (int i = 0; i < digits.length(); i++) {

		}

		return ret;
	}

	public static void printArray(int nums[]) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println("");
	}

	@Test
	public void testfindKthLargest() {
		int[] nums = { 2, 2, 3, 1 };
		System.out.println(TagArray.findKthLargest(nums, 3));
		int[] nums1 = { 1, 1, 2 };
		System.out.println(TagArray.findKthLargest(nums1, 3));
		int[] nums2 = { 1, 2, 2, 5, 3, 5 };
		System.out.println(TagArray.findKthLargest(nums2, 3));
		int[] nums3 = { 1, -2147483648, 2 };
		System.out.println(TagArray.findKthLargest(nums3, 3));

	}

	@Test
	public void testthirdMax() {
		int[] nums = { 2, 2, 3, 1 };
		System.out.println(TagArray.thirdMax(nums));
		int[] nums1 = { 1, 1, 2 };
		System.out.println(TagArray.thirdMax(nums1));
		int[] nums2 = { 1, 2, 2, 5, 3, 5 };
		System.out.println(TagArray.thirdMax(nums2));
		int[] nums3 = { 1, -2147483648, 2 };
		System.out.println(TagArray.thirdMax(nums3));

	}

	@Test
	public void testrotate() {
		int[] nums = { 1 };
		TagArray.rotate(nums, 1);
		printArray(nums);
		int[] nums1 = { 1, 2, 3, 4, 5, 6, 7 };
		TagArray.rotate(nums1, 3);
		printArray(nums1);
		int[] nums2 = { -1, -100, 3, 99 };
		TagArray.rotate(nums2, 2);
		printArray(nums2);

	}

	@Test
	public void testmaxSubArray() {
		int[] nums = { 1 };
		System.out.println(TagArray.maxSubArray(nums));
		int[] nums1 = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		System.out.println(TagArray.maxSubArray(nums1));
		int[] nums2 = { -2, -1, -3, -5 };
		System.out.println(TagArray.maxSubArray(nums2));
		int[] nums3 = { -2, 1 };
		System.out.println(TagArray.maxSubArray(nums3));
	}

	@Test
	public void testRemoveDup() {
		int[] nums = { 1 };
		TagArray.removeDup(nums);
		int[] nums1 = { 1, 1 };
		TagArray.removeDup(nums1);
		int[] nums2 = { 1, 6 };
		TagArray.removeDup(nums2);
		int[] nums3 = { 1, 2, 3, 4, 5, 6 };
		TagArray.removeDup(nums3);
		int[] nums4 = { 1, 1, 2, 2, 2, 3, 3, 4, 5, 5, 6 };
		TagArray.removeDup(nums4);
		int[] nums5 = { 4, 5, 6, 7, 2, 3, 45, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 5, 5, 6 };
		TagArray.removeDup(nums5);
		// int[] nums5 = { 1, 1, 2, 2, 2, 3, 3, 4, 5, 5, 6 };
		// testRD(nums5);
		// int[] nums6 = { 1, 1, 2, 2, 2, 3, 3, 4, 5, 5, 6 };
		// testRD(nums6);
		// int[] nums7 = { 1, 1, 2, 2, 2, 3, 3, 4, 5, 5, 6 };
		// testRD(nums7);
		// int[] nums8 = { 1, 1, 2, 2, 2, 3, 3, 4, 5, 5, 6 };
		// testRD(nums8);

	}

	@Test
	public void testRemoveElement() {
		int[] nums = { 1 };
		TagArray.removeElement(nums, 1);
		int[] nums1 = { 1, 1 };
		TagArray.removeElement(nums1, 1);
		int[] nums2 = { 1, 6 };
		TagArray.removeElement(nums2, 6);
		int[] nums3 = { 1, 2, 3, 4, 5, 6 };
		TagArray.removeElement(nums3, 3);
		int[] nums4 = { 1, 1, 2, 2, 2, 3, 3, 4, 5, 5, 6 };
		TagArray.removeElement(nums4, 2);
		int[] nums5 = { 4, 5, 6, 7, 2, 3, 45, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 5, 5, 6 };
		TagArray.removeElement(nums5, 1);
		// int[] nums5 = { 1, 1, 2, 2, 2, 3, 3, 4, 5, 5, 6 };
		// testRD(nums5);
		// int[] nums6 = { 1, 1, 2, 2, 2, 3, 3, 4, 5, 5, 6 };
		// testRD(nums6);
		// int[] nums7 = { 1, 1, 2, 2, 2, 3, 3, 4, 5, 5, 6 };
		// testRD(nums7);
		// int[] nums8 = { 1, 1, 2, 2, 2, 3, 3, 4, 5, 5, 6 };
		// testRD(nums8);

	}

	@Test
	public void testGenerateYHAngle() {
		System.out.println(TagArray.generateYHAngle(5));
		System.out.println(TagArray.generateYHAngle(10));
	}

	@Test
	public void testGenerateYHAngleK() {
		System.out.println(TagArray.generateYHAngleK(5));
		System.out.println(TagArray.generateYHAngleK(10));
	}

	@Test
	public void testMaxProfit() {
		int[] prices1 = { 7, 1, 5, 3, 6, 4 };
		System.out.println(TagArray.maxProfit(prices1));
		int[] prices2 = { 7, 6, 4, 3, 1 };
		System.out.println(TagArray.maxProfit(prices2));
		int[] prices3 = { 72 };
		System.out.println(TagArray.maxProfit(prices3));
		int[] prices4 = {};
		System.out.println(TagArray.maxProfit(prices4));
	}

	@Test
	public void testMaxProfitII() {
		int[] prices1 = { 7, 1, 5, 3, 6, 4 };
		System.out.println(TagArray.maxProfitII(prices1));
		int[] prices2 = { 7, 6, 4, 3, 1 };
		System.out.println(TagArray.maxProfitII(prices2));
		int[] prices3 = { 72 };
		System.out.println(TagArray.maxProfitII(prices3));
		int[] prices4 = {};
		System.out.println(TagArray.maxProfitII(prices4));
		int[] prices5 = { 2, 4, 1 };
		System.out.println(TagArray.maxProfitII(prices5));
		int[] prices6 = { 1, 2, 3, 4, 5 };
		System.out.println(TagArray.maxProfitII(prices6));
		int[] prices7 = { 7, 6, 4, 3, 1 };
		System.out.println(TagArray.maxProfitII(prices7));
	}

	@Test
	public void testContainsNearbyDuplicate() {
		int[] input = { 1, 2, 3, 1, 2, 3 };
		System.out.println(TagArray.containsNearbyDuplicate(input, 2));
		int[] input1 = { 1, 1 };
		System.out.println(TagArray.containsNearbyDuplicate(input1, 2));
		int[] input2 = { 1, 2, 3, 1 };
		System.out.println(TagArray.containsNearbyDuplicate(input2, 3));
		int[] input3 = { 3, 3 };
		System.out.println(TagArray.containsNearbyDuplicate(input3, 3));
	}

	@Test
	public void testMissingNumber() {
		int[] input = { 3, 0, 1 };
		System.out.println(TagArray.missingNumber(input));
		int[] input1 = { 9, 6, 4, 2, 3, 5, 7, 0, 1 };
		System.out.println(TagArray.missingNumber(input1));
		int[] input2 = { 1 };
		System.out.println(TagArray.missingNumber(input2));
		int[] input3 = { 0 };
		System.out.println(TagArray.missingNumber(input3));
	}

	@Test
	public void testMoveZeroes() {
		int[] input = { 3, 0, 1 };
		TagArray.moveZeroes(input);
		int[] input1 = { 9, 6, 4, 2, 3, 5, 7, 0, 1 };
		TagArray.moveZeroes(input1);
		int[] input2 = { 1 };
		TagArray.moveZeroes(input2);
		// int[] input3 = { 0 };
		// TagArray.moveZeroes(input3);
		int[] input4 = { 0, 1, 0, 3, 12 };
		TagArray.moveZeroes(input4);
	}

	@Test
	public void testFourSum() {
		int[] input = { 1, 0, -1, 0, -2, 2 };
		System.out.println(TagArray.fourSum(input, 0));
		int[] input1 = { 1, -2, -5, -4, -3, 3, 3, 5 };
		System.out.println(TagArray.fourSum(input1, -11));
	}

	@Test
	public void testHalfSearch() {
		int[] input1 = { 0, 1, 2, 3, 4, 5, 6, 7 };
		System.out.println(TagArray.halfSearch(input1, 0));
		System.out.println(TagArray.halfSearch(input1, 1));
		System.out.println(TagArray.halfSearch(input1, 2));
		System.out.println(TagArray.halfSearch(input1, 4));
		System.out.println(TagArray.halfSearch(input1, 5));
		System.out.println(TagArray.halfSearch(input1, 6));
		System.out.println(TagArray.halfSearch(input1, 7));
		System.out.println(TagArray.halfSearchRe(input1, 0));
		System.out.println(TagArray.halfSearchRe(input1, 1));
		System.out.println(TagArray.halfSearchRe(input1, 2));
		System.out.println(TagArray.halfSearchRe(input1, 4));
		System.out.println(TagArray.halfSearchRe(input1, 5));
		System.out.println(TagArray.halfSearchRe(input1, 6));
		System.out.println(TagArray.halfSearchRe(input1, 7));
	}

	@Test
	public void testFindRotatedPos() {
		int[] input1 = { 4, 5, 6, 7, 0, 1, 2 };
		System.out.println(TagArray.findRotatedPos(input1));
		int[] input2 = { 3, 1 };
		System.out.println(TagArray.findRotatedPos(input2));
		int[] input3 = {};
		System.out.println(TagArray.findRotatedPos(input3));
	}

	@Test
	public void testsearchRotatedArray() {
		int[] input1 = { 4, 5, 6, 7, 0, 1, 2 };
		System.out.println(TagArray.searchRotatedArray(input1, -1));
		System.out.println(TagArray.searchRotatedArray(input1, 0));
		System.out.println(TagArray.searchRotatedArray(input1, 1));
		System.out.println(TagArray.searchRotatedArray(input1, 2));
		System.out.println(TagArray.searchRotatedArray(input1, 3));
		System.out.println(TagArray.searchRotatedArray(input1, 4));
		System.out.println(TagArray.searchRotatedArray(input1, 5));
		System.out.println(TagArray.searchRotatedArray(input1, 6));
		System.out.println(TagArray.searchRotatedArray(input1, 7));
		System.out.println(TagArray.searchRotatedArray(input1, 100));
		int[] input2 = { 3, 1 };
		System.out.println(TagArray.searchRotatedArray(input2, 0));
		System.out.println(TagArray.searchRotatedArray(input2, 1));
		System.out.println(TagArray.searchRotatedArray(input2, 3));
	}

	@Test
	public void testintersection() {
		int[] input1 = { 1, 2, 2, 1 };
		int[] input2 = { 2, 2 };
		printArray(TagArray.intersection(input1, input2));
		int[] input3 = { 4, 9, 5 };
		int[] input4 = { 9, 4, 9, 8, 4 };
		printArray(TagArray.intersection(input3, input4));
	}
}
