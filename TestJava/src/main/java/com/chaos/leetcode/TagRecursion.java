package com.chaos.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TagRecursion {

	// 746
	public static int minCostClimbingStairs(int[] cost) {
		int costTotal = 0;
		int i = -1;
		while (i < cost.length) {
			if (i + 4 < cost.length) {
				if (cost[i + 1] + cost[i + 3] > cost[i + 2] + cost[i + 4]) {
					costTotal += cost[i + 2];
					i += 2;
				} else if (cost[i + 1] + cost[i + 3] == cost[i + 2] + cost[i + 4]) {
					if (cost[i + 1] > cost[i + 2]) {
						costTotal += cost[i + 2];
						i += 2;
					} else {
						costTotal += cost[i + 1];
						i += 1;
					}
				} else {
					if (cost[i + 1] + cost[i + 3] > cost[i + 2]) {
						costTotal += cost[i + 2];
						i += 2;
					} else if (cost[i + 1] + cost[i + 3] == cost[i + 2]) {
						if (cost[i + 1] > cost[i + 2]) {
							costTotal += cost[i + 2];
							i += 2;
						} else {
							costTotal += cost[i + 1];
							i += 1;
						}
					} else {
						costTotal += cost[i + 1];
						i += 1;
					}
				}
			} else if (i + 3 < cost.length) {
				if (cost[i + 1] + cost[i + 3] > cost[i + 2]) {
					costTotal += cost[i + 2];
					i += 2;
				} else if (cost[i + 1] + cost[i + 3] == cost[i + 2]) {
					if (cost[i + 1] > cost[i + 2]) {
						costTotal += cost[i + 2];
						i += 2;
					} else {
						costTotal += cost[i + 1];
						i += 1;
					}
				} else {
					costTotal += cost[i + 1];
					i += 1;
				}
			} else if (i + 2 < cost.length) {
				if (cost[i + 1] > cost[i + 2]) {
					costTotal += cost[i + 2];
					i += 2;
				} else {
					costTotal += cost[i + 1];
					i += 1;
				}
			} else {
				break;
			}
		}

		return costTotal;
	}

	// 70
	public static Map<Integer, Integer> store = new HashMap<Integer, Integer>();

	// divid and compuse
	public static int climbStairs(int n) {
		if (n <= 0) {
			return 0;
		}
		if (n == 1) {
			store.put(1, 1);
			return 1;
		}
		if (n == 2) {
			store.put(2, 2);
			return 2;
		}
		if (store.containsKey(n)) {
			return store.get(n);
		}
		for (int i = 3; i <= n; i++) {
			store.put(i, climbStairs(i - 1) + climbStairs(i - 2));
		}
		return store.get(n);
	}

	public static int climbStairsR(int n) {
		if (n <= 0) {
			return 0;
		}
		if (n == 1) {
			store.put(1, 1);
			return 1;
		}
		if (n == 2) {
			store.put(2, 2);
			return 2;
		}
		if (store.containsKey(n)) {
			return store.get(n);
		}
		store.put(n, climbStairsR(n - 1) + climbStairsR(n - 2));
		return store.get(n);
	}

	// 856
	public static int scoreOfParentheses(String S) {
		int ret = 0;
		if (S == null || S.length() == 0) {
			return ret;
		}

		Stack<Integer> sc = new Stack<Integer>();
		Stack<Integer> sv = new Stack<Integer>();

		for (int i = 0; i < S.length(); i++) {
			int tv = 0;
			if (S.charAt(i) == '(') {
				sc.push(i);
				sv.push(0);
				continue;
			} else if (S.charAt(i) == ')') {
				int lb = sc.pop();
				if (lb + 1 == i) {
					sv.pop();
					tv = 1;
				} else {
					tv = sv.pop() * 2;
				}
			} else {
				tv = Integer.parseInt("" + S.charAt(i));
			}

			if (sv.isEmpty()) {
				ret += tv;
			} else {
				sv.push(sv.pop() + tv);
			}
		}

		while (!sv.isEmpty()) {
			ret += sv.pop();
		}

		return ret;
	}

	// 509
	public static int fib(int N) {
		int preV = 1;
		int prePV = 0;

		if (N <= 0) {
			return 0;
		}

		for (int i = 1; i < N; i++) {
			int tmp = preV;
			preV = preV + prePV;
			prePV = tmp;
		}

		return preV;
	}

	public static int fibR(int N) {
		if (N <= 0) {
			return 0;
		} else if (N == 1) {
			return 1;
		} else {
			return fib(N - 1) + fib(N - 2);
		}
	}
}
