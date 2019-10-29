package com.chaos.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagGreed {

	// 1005
	public static int largestSumAfterKNegations(int[] A, int K) {
		int rst = 0;

		Arrays.sort(A);
		int left = 0;
		int right = A.length - 1;
		int zpos = -1;
		while (left < right) {
			int mid = (left + right) / 2;
			if (A[mid] < 0) {
				if (mid + 1 > right) {
					zpos = mid;
					break;
				}
				left = mid + 1;
				if (A[left] >= 0) {
					zpos = mid;
					break;
				}
			} else if (A[mid] > 0) {
				if (mid - 1 < left) {
					break;
				}
				right = mid - 1;
				if (A[right] < 0) {
					zpos = right;
					break;
				}
			} else {
				zpos = mid;
				break;
			}
		}

		int reservId = 0;
		int le = zpos + 1;
		if (zpos == -1) {
			reservId = K % 2;
		} else {
			if (le < K) {
				reservId = (K - le) % 2;
			} else {
				le = K;
			}
		}

		for (int i = 0; i < A.length; i++) {
			if (le > 0) {
				if (le == 1 && reservId > 0) {
					A[i] *= -1;
					if (i + 1 < A.length && A[i] > A[i + 1]) {
						rst += A[i];
						rst -= A[i + 1];
						i++;
					} else {
						rst += A[i] * -1;
					}
					reservId--;
				} else {
					rst += A[i] * -1;
				}
				le--;
			} else {
				if (reservId > 0) {
					rst += A[i] * -1;
					reservId--;
				} else {
					rst += A[i];
				}
			}
		}

		return rst;
	}

	// 874
	public static int robotSim(int[] commands, int[][] obstacles) {
		int dim[] = { 0, 0 };
		int chg[] = { 0, 1, 0, -1 };
		int xDirect = 0;
		int yDirect = 1;
		int rst = 0;

		Map<Integer, List<Integer>> obsOrdX = new HashMap<Integer, List<Integer>>();
		Map<Integer, List<Integer>> obsOrdY = new HashMap<Integer, List<Integer>>();

		for (int i = 0; i < obstacles.length; i++) {
			if (obsOrdX.get(obstacles[i][0]) != null) {
				obsOrdX.get(obstacles[i][0]).add(obstacles[i][1]);
			} else {
				List<Integer> tmpList = new ArrayList<Integer>();
				tmpList.add(obstacles[i][1]);
				obsOrdX.put(obstacles[i][0], tmpList);
			}

			if (obsOrdY.get(obstacles[i][1]) != null) {
				obsOrdY.get(obstacles[i][1]).add(obstacles[i][0]);
			} else {
				List<Integer> tmpList = new ArrayList<Integer>();
				tmpList.add(obstacles[i][0]);
				obsOrdY.put(obstacles[i][1], tmpList);
			}
		}

		for (int i = 0; i < commands.length; i++) {
			System.out.println("commands : " + commands[i] + ", current pos : (" + dim[0] + ", " + dim[1]
					+ "), direct : " + chg[xDirect] + ", " + chg[yDirect]);
			if (commands[i] > 0) {
				int dim0 = chg[xDirect] * commands[i];
				int dim1 = chg[yDirect] * commands[i];
				boolean blocked = false;
				if (chg[xDirect] != 0) {
					List<Integer> tmpList = obsOrdY.get(dim[1]);
					if (tmpList != null) {
						for (int j = 0; j < tmpList.size(); j++) {
							System.out.print(" # " + tmpList.get(j));
							if (chg[xDirect] > 0 && tmpList.get(j) > dim[0] && tmpList.get(j) <= dim[0] + dim0) {
								dim[0] = tmpList.get(j) - 1;
								blocked = true;
								break;
							} else if (chg[xDirect] < 0 && tmpList.get(j) < dim[0] && tmpList.get(j) >= dim[0]
									+ dim0) {
								dim[0] = tmpList.get(j) + 1;
								blocked = true;
								break;
							}
						}
						System.out.println("###" + dim[1]);
					}
					if (!blocked) {
						dim[0] += dim0;
					} else {
						System.out.println("blocked");
					}
					rst = Math.max(rst, dim[0] * dim[0] + dim[1] * dim[1]);
				} else if (chg[yDirect] != 0) {
					List<Integer> tmpList = obsOrdX.get(dim[0]);
					if (tmpList != null) {
						System.out.print(dim[0] + " ### ");
						for (int j = 0; j < tmpList.size(); j++) {
							System.out.print(" # " + tmpList.get(j));
							if (chg[yDirect] > 0 && tmpList.get(j) > dim[1] && tmpList.get(j) <= dim[1] + dim1) {
								dim[1] = tmpList.get(j) - 1;
								blocked = true;
								break;
							} else if (chg[yDirect] < 0 && tmpList.get(j) < dim[1] && tmpList.get(j) >= dim[1]
									+ dim1) {
								dim[1] = tmpList.get(j) + 1;
								blocked = true;
								break;
							}
						}
						System.out.println("###");
					}
					if (!blocked) {
						dim[1] += dim1;
					} else {
						System.out.println("blocked");
					}
					rst = Math.max(rst, dim[0] * dim[0] + dim[1] * dim[1]);
				}
			} else if (commands[i] == -1) {
				xDirect += 1;
				yDirect += 1;
			} else if (commands[i] == -2) {
				xDirect -= 1;
				yDirect -= 1;
			} else {
				break;
			}

			if (xDirect >= 4) {
				xDirect = 0;
			} else if (xDirect < 0) {
				xDirect = 3;
			}

			if (yDirect >= 4) {
				yDirect = 0;
			} else if (yDirect < 0) {
				yDirect = 3;
			}
		}

		return rst;
	}

	// 392
	public static boolean isSubsequence(String s, String t) {
		if (s == null || t == null) {
			return false;
		}
		if (s.length() > t.length()) {
			return false;
		}
		int tPos = 0;
		int i = 0;
		while (i < s.length() && tPos < t.length()) {
			while (s.charAt(i) != t.charAt(tPos)) {
				tPos++;
				if (tPos >= t.length()) {
					return false;
				}
			}
			tPos++;
			i++;
		}

		if (i == s.length()) {
			return true;
		}

		return false;
	}

	public static boolean lemonadeChange(int[] bills) {
		int fives = 0;
		int tens = 0;

		for (int i = 0; i < bills.length; i++) {
			if (bills[i] == 5) {
				fives++;
			} else if (bills[i] == 10) {
				tens++;
				if (fives > 0) {
					fives--;
				} else {
					return false;
				}
			} else if (bills[i] == 20) {
				if (tens > 0) {
					tens--;
					if (fives > 0) {
						fives--;
					} else {
						return false;
					}
				} else {
					if (fives >= 3) {
						fives -= 3;
					} else {
						return false;
					}

				}
			}
		}

		return true;
	}

	public static int findContentChildren(int[] g, int[] s) {
		if (g.length == 0 || s.length == 0) {
			return 0;
		}
		Arrays.sort(g);
		Arrays.sort(s);

		int cnt = 0;
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < g.length; j++) {
				if (g[j] == 0) {
					continue;
				}
				if (s[i] >= g[j]) {
					cnt++;
					g[j] = 0;
					break;
				}
			}
		}

		return cnt;
	}
}
