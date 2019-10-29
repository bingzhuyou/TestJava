package com.chaos.alg.leetcode;

import org.junit.Test;

import com.chaos.leetcode.TagArray;
import com.chaos.leetcode.TagDynamicPlan;

public class TestDynamicPlan {
	public static void printArray(int nums[]) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println("");
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
	public void testrob() {
		int[] nums = { 1, 2, 3, 1 };
		System.out.println(TagDynamicPlan.rob(nums));
		int[] nums1 = { 2, 7, 9, 3, 1 };
		System.out.println(TagDynamicPlan.rob(nums1));
		int[] nums2 = { 1, 7, 1, 1, 9, 1, 1 };
		System.out.println(TagDynamicPlan.rob(nums2));
		int[] nums3 = { 1, 7, 1, 1, 9, 1, 1, 8, 1 };
		System.out.println(TagDynamicPlan.rob(nums3));
	}

	@Test
	public void testcoinChange() {
		int[] nums = { 1, 2, 5 };
		System.out.println(TagDynamicPlan.coinChange(nums, 11));
		int[] nums1 = { 2 };
		System.out.println(TagDynamicPlan.coinChange(nums1, 3));
		int[] nums2 = { 1, 7, 9 };
		System.out.println(TagDynamicPlan.coinChange(nums2, 20));
		int[] nums3 = { 1, 7, 9, 8 };
		System.out.println(TagDynamicPlan.coinChange(nums3, 100));
		int[] nums4 = { 186, 419, 83, 408 };
		System.out.println(TagDynamicPlan.coinChange(nums4, 6249));
	}
}
