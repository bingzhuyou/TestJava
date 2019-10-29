package com.chaos.leetcode;

public class bitOp {

	public static int divide(int dividend, int divisor) {

		long a = dividend;
		long b = divisor;
		boolean neg = (a > 0) ^ (b > 0);
		if (a < 0)
			a = -a;
		if (b < 0)
			b = -b;
		if (a < b)
			return 0;

		if (dividend == -2147483648 && divisor == -1) {
			return Integer.MAX_VALUE;
		}
		int msb = 0;
		for (msb = 0; msb < 32; msb++) {
			if ((b << msb) >= a)
				break;
		}
		long q = 0;
		for (int i = msb; i >= 0; i--) {
			if ((b << i) > a)
				continue;
			q |= (1 << i);
			a -= (b << i);
		}

		System.out.println(neg + " " + q);
		if (q > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}
		if (neg)
			return (int) -q;
		return (int) (q - 1);
	}

	public static void main(String args[]) {
		System.out.println(divide(-2147483648, -1));
		boolean value = true;
		byte[] bytes = new byte[1000];
		int index = 100;
		if (value)
			// 通过给定位index，先定位到对应byte
			// 并根据value值进行不同位操作：
			// 1.如果value为true，则目标位应该做“或”运算。则需构建“目标位为1，其他为0”的操作数，为了只合理操作目标位，而不影响其他位
			// 2.如果value为false，则目标位应该做“与”运算。则需构建“目标位为0，其他为1”的操作数
			bytes[index >> 3] |= 1 << (index & 7); // bytes[index/8] =
													// bytes[index/8] | (0b0001
													// << (index%8))
		else
			bytes[index >> 3] &= ~(1 << (index & 7));
		System.out.println(index >> 3);
		System.out.println(index & 4);
		System.out.println(index & 5);
		System.out.println(index & 6);
		System.out.println(index & 7);
		byte bb = (byte) (1 << (index & 7));
		byte bb2 = (byte) ~(1 << (index & 7));
		System.out.println(bb);
		System.out.println(bb2);

	}
}
