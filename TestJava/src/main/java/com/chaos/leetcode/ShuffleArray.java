package com.chaos.leetcode;

public class ShuffleArray {
	public int[] m_nums;
	public int[] m_nums_s;

	public ShuffleArray(int[] nums) {
		m_nums = new int[nums.length];
		m_nums_s = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			m_nums[i] = nums[i];
			m_nums_s[i] = nums[i];
		}
	}

	/** Resets the array to its original configuration and return it. */
	public int[] reset() {
		if (m_nums.length == 0) {
			return m_nums;
		}
		for (int i = 0; i < m_nums.length; i++) {
			m_nums_s[i] = m_nums[i];
		}
		return m_nums;
	}

	/** Returns a random shuffling of the array. */
	public int[] shuffle() {
		if (m_nums_s.length == 0) {
			return m_nums_s;
		}
		int temp;
		temp = m_nums_s[0];
		for (int i = 0; i < m_nums_s.length - 1; i++) {
			m_nums_s[i] = m_nums_s[i + 1];
		}
		m_nums_s[m_nums_s.length - 1] = temp;
		return m_nums_s;
	}
}
