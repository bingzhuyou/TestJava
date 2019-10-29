package com.chaos.alg.leetcode;

import java.util.Random;

import org.junit.Test;

import com.chaos.leetcode.TagRecursion;

public class TestRecursion {
	@Test
	public void testFib() {
		Random r = new Random();
		int len = 30;

		for (int i = 0; i < len; i++) {
			int fibSeed = r.nextInt(len);
			System.out.println("fib(" + fibSeed + ") is " + TagRecursion.fib(fibSeed));
		}
	}
}
