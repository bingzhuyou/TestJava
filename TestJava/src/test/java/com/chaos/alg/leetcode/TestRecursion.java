package com.chaos.alg.leetcode;

import java.util.Random;

import org.junit.Test;

import com.chaos.leetcode.TagRecursion;

public class TestRecursion {
	@Test
	public void testminCostClimbingStairs() {
		int[] cost = { 10, 15, 20 };
		System.out.println(TagRecursion.minCostClimbingStairs(cost));
		int[] cost1 = { 1, 100, 1, 1, 1, 100, 1, 1, 100, 1 };
		System.out.println(TagRecursion.minCostClimbingStairs(cost1));
		int[] cost2 = { 0, 1, 2, 2 };
		System.out.println(TagRecursion.minCostClimbingStairs(cost2));
		int[] cost3 = { 1, 0, 0, 1 };
		System.out.println(TagRecursion.minCostClimbingStairs(cost3));
		int[] cost4 = { 1, 0, 0, 2 };
		System.out.println(TagRecursion.minCostClimbingStairs(cost4));
	}

	@Test
	public void testFib() {
		Random r = new Random();
		int len = 30;

		for (int i = 0; i < len; i++) {
			int fibSeed = r.nextInt(len);
			System.out.println("fib(" + fibSeed + ") is " + TagRecursion.fib(fibSeed));
		}
	}

	@Test
	public void testscoreOfParentheses() {
		System.out.println(TagRecursion.scoreOfParentheses("()"));
		System.out.println(TagRecursion.scoreOfParentheses("()()"));
		System.out.println(TagRecursion.scoreOfParentheses("(())()"));
		System.out.println(TagRecursion.scoreOfParentheses("((())())"));
	}

	@Test
	public void testclimbStairs() {
		System.out.println(TagRecursion.climbStairs(0));
		System.out.println(TagRecursion.climbStairs(1));
		System.out.println(TagRecursion.climbStairs(2));
		System.out.println(TagRecursion.climbStairs(3));
		System.out.println(TagRecursion.climbStairs(4));
		System.out.println(TagRecursion.climbStairs(44));
	}
}
