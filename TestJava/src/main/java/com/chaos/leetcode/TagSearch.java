package com.chaos.leetcode;

import java.math.BigDecimal;

public class TagSearch {
	// 29
	public int divide(int dividend, int divisor) {
		int rst = 0;
		while (divisor < dividend) {
			dividend -= divisor;
			rst++;
		}

		return rst;
	}

	public static int[] searchRange(int[] nums, int target) {
		int[] rst = { -1, -1 };
		if (nums.length <= 0) {
			return rst;
		}

		int left = 0;
		int right = nums.length - 1;

		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] < target) {
				left = mid + 1;
			} else if (nums[mid] > target) {
				right = mid - 1;
			} else {
				for (int j = mid; j >= left && nums[j] == target; j--) {
					rst[0] = j;
				}
				for (int j = mid; j <= right && nums[j] == target; j++) {
					rst[1] = j;
				}
				break;
			}
		}
		return rst;
	}

	// 50
	public static double fastPow(double x, long n) {
		if (n == 0) {
			return 1.0;
		}
		double half = fastPow(x, n / 2);
		if (n % 2 == 0) {
			return half * half;
		} else {
			return half * half * x;
		}
	}

	public static double myPowO(double x, int n) {
		long N = n;
		if (N < 0) {
			x = 1 / x;
			N = -N;
		}
		return fastPow(x, N);
	}

	public static double myPow2(double x, int n) {
		if (n == 0) {
			return 1;
		}
		if (x == 1) {
			return 1;
		}
		boolean nag = false;
		long lN = n;
		if (n < 0) {
			nag = true;
			lN = -lN;
		}

		if (n == 1) {
			return x;
		}

		long mod = lN % 2;

		double half = myPow2(x, (int) lN / 2);
		double rst;
		if (mod == 1) {
			rst = half * half;
		} else {
			rst = half * half * x;
		}

		if (rst == 0) {
			if (nag) {
				return Double.MAX_VALUE;
			}
			return 0;
		}
		// if (rst.compareTo(BigDecimal.valueOf(Double.MAX_VALUE)) > 0) {
		// rst = BigDecimal.valueOf(Double.MAX_VALUE);
		// }

		if (nag) {
			return 1 / rst;
		}
		return rst;
	}

	public static double myPow(double x, int n) {
		if (n == 0) {
			return 1;
		}
		if (x == 1) {
			return 1;
		}
		boolean nag = false;
		long lN = n;
		if (n < 0) {
			nag = true;
			lN = Math.abs(lN);
		}

		if (n == 1) {
			return x;
		}

		long sub = lN / 2;
		long mod = lN % 2;

		BigDecimal rst = new BigDecimal(1);
		if (sub > 0) {
			rst = rst.multiply(BigDecimal.valueOf(myPow(x, (int) sub)));
			rst = rst.multiply(rst);
		}
		if (mod == 1) {
			rst = rst.multiply(BigDecimal.valueOf(x));
		}

		if (rst.compareTo(BigDecimal.valueOf(0)) == 0) {
			if (nag) {
				return Double.MAX_VALUE;
			}
			return 0;
		}
		if (rst.compareTo(BigDecimal.valueOf(Double.MAX_VALUE)) > 0) {
			rst = BigDecimal.valueOf(Double.MAX_VALUE);
		}

		if (nag) {
			return 1 / rst.doubleValue();
		}
		return rst.doubleValue();
	}

	// 374
	public static int guess(int num) {
		return -1;
	}

	public static int guessNumber(int n) {
		if (n < -0) {
			return 0;
		}

		int left = 1;
		int right = n;
		int mid = -1;
		while (left <= right) {
			mid = left + (right - left) / 2;
			if (guess(mid) > 0) {
				left = mid + 1;
			} else if (guess(mid) < 0) {
				right = mid - 1;
			} else {
				return mid;
			}
		}

		return mid;
	}

	// 441
	public static int arrangeCoins(int n) {
		long rst = 0;
		if (n <= 0) {
			return (int) rst;
		}

		int w = 0;
		long y = (long) n * 2;
		long y2 = y;
		long tmpL1, longX;

		longX = y;
		if (y > 10) {
			while (y >= 10) {
				if (y < 100) {
					y2 = y;
				}
				y = y / 10;
				w++;
			}
		}
		int w1 = w / 2;
		int w2 = w % 2;
		int w3 = 1;
		while (w1 > 0) {
			w3 *= 10;
			w1--;
		}

		long left = 1;
		long right = y;
		if (w2 > 0) {
			left = 3 * w3;
			right = y2 * w3 * 2;
		} else {
			left = w3;
			if (w3 >= 10) {
				right = y2 * w3 / 10 * 2;
			} else {
				right = y * w3;
			}
		}

		rst = (left + right) / 2;
		while (left <= right) {
			// System.out.println(left + ", " + right);
			rst = (left + right) / 2;

			tmpL1 = rst * (rst + 1);
			if (tmpL1 > longX) {
				right = rst - 1;
				tmpL1 = right * (right + 1);
				if (tmpL1 <= longX) {
					return (int) right;
				}
			} else if (tmpL1 < longX) {
				left = rst + 1;
				tmpL1 = left * (left + 1);
				if (tmpL1 > longX) {
					return (int) rst;
				} else if (tmpL1 == longX) {
					return (int) left;
				}
			} else {
				return (int) rst;
			}
		}

		return (int) rst;
	}

	// 367
	public static boolean isPerfectSquare(int num) {
		int x = num;
		if (x <= 0) {
			return false;
		}

		int w = 0;
		int y = x;
		int y2 = y;
		if (x > 10) {
			while (y >= 10) {
				if (y < 100) {
					y2 = y;
				}
				y = y / 10;
				w++;
			}
		}
		int w1 = w / 2;
		int w2 = w % 2;
		int w3 = 1;
		while (w1 > 0) {
			w3 *= 10;
			w1--;
		}

		long left = 1;
		long right = x;
		if (w2 > 0) {
			left = 3 * w3;
			right = y2 * w3 * 2;
		} else {
			left = w3;
			if (w3 > 10) {
				right = y2 * w3 / 10 * 2;
			} else {
				right = y * w3;
			}
		}

		long tmpL1, longX;

		longX = x;
		long rst = (left + right) / 2;
		while (left <= right) {
			// System.out.println(left + ", " + right);
			rst = (left + right) / 2;

			tmpL1 = rst * rst;
			if (tmpL1 > longX) {
				right = rst - 1;
				tmpL1 = right * right;
				if (tmpL1 < longX) {
					return false;
				} else if (tmpL1 == longX) {
					return true;
				}
			} else if (tmpL1 < longX) {
				left = rst + 1;
				tmpL1 = left * left;
				if (tmpL1 > longX) {
					return false;
				} else if (tmpL1 == longX) {
					return true;
				}
			} else {
				return true;
			}
		}

		return false;
	}

	public static boolean isBadVersion(int n) {
		return false;
	}

	// 278
	// 在二分查找中，选取 mid 的方法一般为 mid =(left+right)/2;
	// 如果使用的编程语言会有整数溢出的情况（例如 C++，Java），那么可以用
	// mid = left + (right - left) / 2; 代替前者。

	public int firstBadVersion(int n) {

		int left = 0;
		int right = n;
		int zpos = -1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (!isBadVersion(mid)) {
				if (mid + 1 > right) {
					zpos = mid;
					break;
				}
				left = mid + 1;
				if (isBadVersion(left)) {
					zpos = left;
					break;
				}
			} else {
				if (mid - 1 < left) {
					break;
				}
				right = mid - 1;
				if (!isBadVersion(right)) {
					zpos = mid;
					break;
				}
			}
		}

		return zpos;
	}

	// 69
	public static int mySqrt(int x) {
		if (x <= 0) {
			return 0;
		}

		double pre = (double) x / 2;
		double cur = (x + x / pre) / 2;
		while (pre != cur) {
			pre = cur;
			cur = (pre + x / pre) / 2;
		}

		return (int) cur;
	}

	// 69
	public static int mySqrtSlow(int x) {
		if (x <= 0) {
			return 0;
		}

		int w = 0;
		int y = x;
		int y2 = y;
		if (x > 10) {
			while (y >= 10) {
				if (y < 100) {
					y2 = y;
				}
				y = y / 10;
				w++;
			}
		}
		int w1 = w / 2;
		int w2 = w % 2;
		int w3 = 1;
		while (w1 > 0) {
			w3 *= 10;
			w1--;
		}

		long left = 1;
		long right = x;
		if (w2 > 0) {
			left = 3 * w3;
			right = y2 * w3 * 2;
		} else {
			left = w3;
			if (w3 > 10) {
				right = y2 * w3 / 10 * 2;
			} else {
				right = y * w3;
			}
		}

		long tmpL1, longX;

		longX = x;
		long rst = (left + right) / 2;
		while (left <= right) {
			System.out.println(left + ", " + right);
			rst = (left + right) / 2;

			tmpL1 = rst * rst;
			if (tmpL1 > longX) {
				right = rst - 1;
				tmpL1 = right * right;
				if (tmpL1 <= longX) {
					return (int) right;
				}
			} else if (tmpL1 < longX) {
				left = rst + 1;
				tmpL1 = left * left;
				if (tmpL1 > longX) {
					return (int) rst;
				} else if (tmpL1 == longX) {
					return (int) left;
				}
			} else {
				return (int) rst;
			}
		}

		return (int) rst;
	}

	// 704
	public static int search(int[] nums, int target) {
		int rst = -1;
		if (nums.length <= 0) {
			return rst;
		}

		int left = 0;
		int right = nums.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] < target) {
				left = mid + 1;
			} else if (nums[mid] > target) {
				right = mid - 1;
			} else {
				rst = mid;
				break;
			}
		}

		return rst;
	}

	public static int findZeroPositoin(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;
		int zpos = -1;
		while (left < right) {
			int mid = (left + right) / 2;
			if (nums[mid] < 0) {
				if (mid + 1 > right) {
					zpos = mid;
					break;
				}
				left = mid + 1;
				if (nums[left] >= 0) {
					zpos = mid;
					break;
				}
			} else if (nums[mid] > 0) {
				if (mid - 1 < left) {
					break;
				}
				right = mid - 1;
				if (nums[right] < 0) {
					zpos = right;
					break;
				}
			} else {
				zpos = mid;
				break;
			}
		}

		return zpos;
	}
}
