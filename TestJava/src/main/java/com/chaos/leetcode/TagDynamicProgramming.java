package com.chaos.leetcode;

import java.util.Arrays;

public class TagDynamicPlan {

	// 1046
	public static int lastStoneWeight(int[] stones) {
		if (stones.length <= 0) {
			return 0;
		}
		if (stones.length == 1) {
			return stones[0];
		}
		Arrays.sort(stones);

		for (int i = stones.length - 1; i > 0; i--) {
			stones[i - 1] = stones[i] - stones[i - 1];
			if (stones[i - 1] > 0) {
				Arrays.sort(stones, 0, i);
			} else {
				i--;
			}
		}

		return stones[0];
	}

	// 322
	public static int coinChange(int[] coins, int amount) {
		int[] dp = new int[amount + 1];
		Arrays.fill(dp, amount + 1);
		dp[0] = 0;
		for (int i = 1; i <= amount; i++) {
			for (int coin : coins)
				if (coin <= i)
					dp[i] = Math.min(dp[i], dp[i - coin] + 1);
		}
		return dp[amount] > amount ? -1 : dp[amount];
	}

	// 198
	public static int rob(int[] nums) {
		if (nums.length <= 0) {
			return 0;
		}
		int prevMax = 0;
		int currMax = 0;
		for (int x : nums) {
			int temp = currMax;
			currMax = Math.max(prevMax + x, currMax);
			prevMax = temp;
		}
		return currMax;
	}
}
