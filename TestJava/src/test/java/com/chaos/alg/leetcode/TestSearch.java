package com.chaos.alg.leetcode;

import org.junit.Test;

import com.chaos.leetcode.TagGreed;
import com.chaos.leetcode.TagSearch;

public class TestSearch {
	public static void printArray(int nums[]) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println("");
	}

	@Test
	public void testmyPow() {
		System.out.println(TagSearch.myPow(1, 10));
		System.out.println(TagSearch.myPow(0, 10));
		System.out.println(TagSearch.myPow(2, 10));
		System.out.println(TagSearch.myPow(0.00001, 2147483647));
	}

	@Test
	public void testmySqrt() {
		System.out.println(TagSearch.mySqrt(-1));
		System.out.println(TagSearch.mySqrt(0));
		System.out.println(TagSearch.mySqrt(1));
		System.out.println(TagSearch.mySqrt(2));
		System.out.println(TagSearch.mySqrt(3));
		System.out.println(TagSearch.mySqrt(4));
		System.out.println(TagSearch.mySqrt(5));
		System.out.println(TagSearch.mySqrt(6));
		System.out.println(TagSearch.mySqrt(7));
		System.out.println(TagSearch.mySqrt(8));
		System.out.println(TagSearch.mySqrt(9));
		System.out.println(TagSearch.mySqrt(2147395599));
		System.out.println(TagSearch.mySqrt(183692038));
		System.out.println(TagSearch.mySqrt(1041080284));
		System.out.println(TagSearch.mySqrt(102306870));
	}

	@Test
	public void testarrangeCoins() {
		System.out.println(TagSearch.arrangeCoins(-1));
		System.out.println(TagSearch.arrangeCoins(0));
		System.out.println(TagSearch.arrangeCoins(1));
		System.out.println(TagSearch.arrangeCoins(2));
		System.out.println(TagSearch.arrangeCoins(3));
		System.out.println(TagSearch.arrangeCoins(4));
		System.out.println(TagSearch.arrangeCoins(5));
		System.out.println(TagSearch.arrangeCoins(6));
		System.out.println(TagSearch.arrangeCoins(7));
		System.out.println(TagSearch.arrangeCoins(8));
		System.out.println(TagSearch.arrangeCoins(9));
		System.out.println(TagSearch.arrangeCoins(75));
		System.out.println(TagSearch.arrangeCoins(2147395599));
		System.out.println(TagSearch.arrangeCoins(183692038));
		System.out.println(TagSearch.arrangeCoins(1041080284));
		System.out.println(TagSearch.arrangeCoins(102306870));
	}

	@Test
	public void testlargestSumAfterKNegations() {

		int[] commands = { 4, -1, 4, -2, 4 };
		System.out.println(TagGreed.largestSumAfterKNegations(commands, 1));
		int[] commands1 = { 2, -3, -1, 5, -4 };
		System.out.println(TagGreed.largestSumAfterKNegations(commands1, 2));
		int[] commands2 = { 3, -1, 0, 2 };
		System.out.println(TagGreed.largestSumAfterKNegations(commands2, 3));
		int[] commands3 = { -4, -6, 0, -5, 7, 7, -5, 4 };
		System.out.println(TagGreed.largestSumAfterKNegations(commands3, 5));
	}
}
