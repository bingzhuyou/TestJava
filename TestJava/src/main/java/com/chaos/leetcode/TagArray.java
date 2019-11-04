package com.chaos.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TagArray {

	// 48 rotate an image
	// 0,0 0,2 0, subLen - 1
	// 0,1 1,2 1, subLen - 1
	// 0,2 2,2 2, subLen - 1
	// 1,0 0,1 0, subLen - 1 - 1
	// 1,1 1,1 1, subLen - 1 - 1
	// 1,2 2,1 2, subLen - 1 - 1
	// 2,0 0,0 0, subLen - 1 - 2
	// 2,1 1,0 1, subLen - 1 - 2
	// 2,2 2,0 2, subLen - 1 - 2
	public static void rotate(int[][] matrix) {
		int subLen = matrix[0].length;

		for (int i = 0; i < subLen; i++) {
			int walker, temp, tind;
			int oi, oj, ti, tj;
			for (int j = i; j < subLen - i - 1; j++) {
				oi = i;
				oj = j;
				ti = oi;
				tj = oj;
				walker = matrix[oi][oj];
				while (true) {
					tind = tj;
					tj = subLen - 1 - ti;
					ti = tind;
					if (ti == oi && tj == oj) {
						matrix[ti][tj] = walker;
						break;
					}
					temp = matrix[ti][tj];
					matrix[ti][tj] = walker;
					walker = temp;
				}
			}
		}
	}

	//
	public static boolean isValidSudoku(char[][] board) {
		int[][] cv = new int[9][9];
		int[][] lv = new int[9][9];
		int[][] sv = new int[9][9];

		int[][] sind = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } };

		int sc, si;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.') {
					continue;
				}
				int k = board[i][j] - 49;
				if (lv[i][k] == 1) {
					return false;
				} else {
					lv[i][k] = 1;
				}
				if (cv[j][k] == 1) {
					return false;
				} else {
					cv[j][k] = 1;
				}
				if (i < 3) {
					sc = 0;
				} else if (i < 6) {
					sc = 1;
				} else {
					sc = 2;
				}
				if (j < 3) {
					si = sind[sc][0];
				} else if (j < 6) {
					si = sind[sc][1];
				} else {
					si = sind[sc][2];
				}

				if (sv[si][k] == 1) {
					return false;
				} else {
					sv[si][k] = 1;
				}
			}
		}

		return true;
	}

	// 17
	public static List<String> letterCombinations(String digits) {
		Map<Integer, List<Character>> cML = new HashMap<Integer, List<Character>>();
		List<Character> l2 = null;
		char c;
		for (c = 'a'; c <= 'z'; c++) {
			if (c % 3 == 1) {
				l2 = new ArrayList<Character>();
			}
			l2.add(c);
			if (c % 3 == 0) {
				cML.put(c - 95, l2);
				l2 = null;
			}
		}

		if (l2 != null) {
			cML.put(c - 96, l2);
		}

		List<String> ret = new ArrayList<String>();
		List<StringBuilder> retB = new ArrayList<StringBuilder>();

		for (int i = 0; i < digits.length(); i++) {

		}

		return ret;
	}

	// 500
	public static String[] findWords(String[] words) {
		List<String> ret = new ArrayList<String>();

		List<Set<Character>> keyboardSets = new ArrayList<Set<Character>>();

		Set<Character> s1 = new HashSet<Character>();
		s1.add('q');
		s1.add('w');
		s1.add('e');
		s1.add('r');
		s1.add('t');
		s1.add('y');
		s1.add('u');
		s1.add('i');
		s1.add('o');
		s1.add('p');
		s1.add('Q');
		s1.add('W');
		s1.add('E');
		s1.add('R');
		s1.add('T');
		s1.add('Y');
		s1.add('U');
		s1.add('I');
		s1.add('O');
		s1.add('P');
		Set<Character> s2 = new HashSet<Character>();
		s2.add('a');
		s2.add('s');
		s2.add('d');
		s2.add('f');
		s2.add('g');
		s2.add('h');
		s2.add('j');
		s2.add('k');
		s2.add('l');
		s2.add('A');
		s2.add('S');
		s2.add('D');
		s2.add('F');
		s2.add('G');
		s2.add('H');
		s2.add('J');
		s2.add('K');
		s2.add('L');
		Set<Character> s3 = new HashSet<Character>();
		s3.add('z');
		s3.add('x');
		s3.add('c');
		s3.add('v');
		s3.add('b');
		s3.add('n');
		s3.add('m');
		s3.add('Z');
		s3.add('X');
		s3.add('C');
		s3.add('V');
		s3.add('B');
		s3.add('N');
		s3.add('M');
		keyboardSets.add(s1);
		keyboardSets.add(s2);
		keyboardSets.add(s3);

		// { { , 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p' },
		// { 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l' },
		// { 'z', 'x', 'c', 'v', 'b', 'n', 'm' } };

		for (String w : words) {
			int key = -1;
			boolean iskbword = true;
			for (int i = 0; i < w.length(); i++) {
				if (key != -1) {
					if (keyboardSets.get(key).contains(w.charAt(i))) {
						continue;
					} else {
						iskbword = false;
						break;
					}
				} else {
					for (int j = 0; j < keyboardSets.size(); j++) {
						if (keyboardSets.get(j).contains(w.charAt(i))) {
							key = j;
							break;
						}
					}
					if (key == -1) {
						iskbword = false;
						break;
					}
				}
			}

			if (iskbword) {
				ret.add(w);
			}
		}
		String[] retSA = new String[ret.size()];
		ret.toArray(retSA);
		return retSA;
	}

	// 448
	public static List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer> ret = new ArrayList<Integer>();

		if (nums.length <= 0) {
			return ret;
		}

		int curv = 1;

		Arrays.sort(nums);

		for (int i = 0; i < nums.length; i++) {
			while (nums[i] > curv) {
				ret.add(curv);
				curv++;
			}
			curv = nums[i] + 1;
		}

		while (nums.length >= curv) {
			ret.add(curv);
			curv++;
		}

		return ret;
	}

	// 215
	public static int findKthLargest(int[] nums, int k) {
		int[] kVals = new int[k];
		for (int i = 0; i < k; i++) {
			kVals[i] = Integer.MIN_VALUE;
		}
		if (nums.length <= 0) {
			return Integer.MIN_VALUE;
		}

		int cnt = 0;

		for (int i = 0; i < nums.length; i++) {
			// skip dup element
			// boolean dup = false;
			// for (int j = 0; j < k - 1; j++) {
			// if (kVals[j] == nums[i] && cnt > j) {
			// dup = true;
			// break;
			// }
			// }
			// if (dup) {
			// continue;
			// }

			for (int j = 0; j < k; j++) {
				if (kVals[j] < nums[i]) {
					for (int p = k - 1; p > j; p--) {
						kVals[p] = kVals[p - 1];
					}
					kVals[j] = nums[i];
					cnt++;
					break;
				}
			}

			if (nums[i] == kVals[k - 1]) {
				cnt++;
			}
		}

		if (cnt < k) {
			return kVals[0];
		}

		return kVals[k - 1];
	}

	// 414
	public static int thirdMax(int[] nums) {
		int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
		if (nums.length <= 0) {
			return max3;
		}

		int cnt = 0;

		for (int i = 0; i < nums.length; i++) {
			if (max1 == nums[i] && cnt > 0 || max2 == nums[i] && cnt > 1) {
				continue;
			}
			if (max1 < nums[i]) {
				max3 = max2;
				max2 = max1;
				max1 = nums[i];
				cnt++;
			} else if (max2 < nums[i]) {
				max3 = max2;
				max2 = nums[i];
				cnt++;
			} else if (max3 <= nums[i]) {
				max3 = nums[i];
				cnt++;
			}
		}

		if (cnt < 3) {
			return max1;
		}

		return max3;
	}

	// 41
	public static int firstMissingPositive(int[] nums) {
		if (nums.length <= 0) {
			return 1;
		}

		Arrays.sort(nums);

		int ret = 1;

		for (int n : nums) {
			if (n > 0) {
				if (ret == n) {
					ret++;
				} else if (n > ret) {
					return ret;
				}
			}
		}
		if (nums[nums.length - 1] <= 0) {
			return 1;
		} else {
			return nums[nums.length - 1] + 1;
		}
	}

	// 31
	public static void nextPermutation(int[] nums) {
		if (nums.length <= 1) {
			return;
		}

		boolean beg = false;
		int pre = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (!beg) {
				if (pre < nums[i]) {
					beg = true;
				}
			}
			if (pre < nums[i]) {
				nums[i - 1] = nums[i];
				nums[i] = pre;
				return;
			}
			pre = nums[i];
		}
		int left = 0;
		int right = nums.length - 1;
		while (left < right) {
			pre = nums[left];
			nums[left] = nums[right];
			nums[right] = pre;
		}

	}

	// 240
	public static boolean searchMatrix(int[][] matrix, int target) {
		if (matrix.length <= 0) {
			return false;
		}

		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i].length <= 0) {
				break;
			}
			int left = 0;
			int right = matrix[i].length - 1;
			if (matrix[i][left] > target || matrix[i][right] < target) {
				continue;
			}
			while (right >= left) {
				int t = (right + left) / 2;

				if (matrix[i][t] > target) {
					right = t - 1;
				} else if (matrix[i][t] < target) {
					left = t + 1;
				} else {
					return true;
				}
			}
		}

		return false;
	}

	// 830
	public static List<List<Integer>> largeGroupPositions(String S) {
		List<List<Integer>> ll = new ArrayList<List<Integer>>();
		if (S == null || S.length() < 3) {
			return ll;
		}
		char[] cArr = S.toCharArray();
		char curC = cArr[0];
		int len = 1;
		int i = 1;
		for (; i < cArr.length; i++) {
			if (curC == cArr[i]) {
				len++;
			} else {
				if (len >= 3) {
					List<Integer> tl = new ArrayList<Integer>();
					tl.add(i - len);
					tl.add(i - 1);
					ll.add(tl);
				}
				curC = cArr[i];
				len = 1;
			}
		}
		if (len >= 3) {
			List<Integer> tl = new ArrayList<Integer>();
			tl.add(i - len);
			tl.add(i - 1);
			ll.add(tl);
		}

		return ll;
	}

	// 832
	public static int[][] flipAndInvertImage(int[][] A) {
		for (int i = 0; i < A.length; i++) {
			int left = 0;
			int right = A[i].length - 1;
			int temp;
			while (right > left) {
				temp = A[i][left];
				A[i][left] = A[i][right];
				A[i][right] = temp;

				if (A[i][left] == 0) {
					A[i][left] = 1;
				} else {
					A[i][left] = 0;
				}

				if (A[i][right] == 0) {
					A[i][right] = 1;
				} else {
					A[i][right] = 0;
				}

				left++;
				right--;
			}
			if (right == left) {
				if (A[i][right] == 0) {
					A[i][right] = 1;
				} else {
					A[i][right] = 0;
				}
			}
		}

		return A;
	}

	// 349
	public static int[] intersection(int[] nums1, int[] nums2) {
		Set<Integer> retL = new HashSet<Integer>();
		Set<Integer> n1 = new HashSet<Integer>();
		Set<Integer> n2 = new HashSet<Integer>();
		for (int i = 0; i < nums1.length; i++) {
			n1.add(nums1[i]);
		}
		for (int i = 0; i < nums2.length; i++) {
			n2.add(nums2[i]);
		}
		Set<Integer> small, large;
		if (n1.size() > n2.size()) {
			small = n2;
			large = n1;
		} else {
			small = n1;
			large = n2;
		}

		Iterator<Integer> sit = small.iterator();
		while (sit.hasNext()) {
			Integer tmp = sit.next();
			if (large.contains(tmp)) {
				retL.add(tmp);
			}
		}

		int[] ret = new int[retL.size()];

		Iterator<Integer> it = retL.iterator();
		int j = 0;
		while (it.hasNext()) {
			ret[j] = it.next();
			j++;
		}
		return ret;
	}

	public static int[] intersectionSort(int[] nums1, int[] nums2) {
		Set<Integer> retL = new HashSet<Integer>();
		List<Integer> n1 = new ArrayList<Integer>();
		List<Integer> n2 = new ArrayList<Integer>();
		for (int i = 0; i < nums1.length; i++) {
			n1.add(nums1[i]);
		}
		for (int i = 0; i < nums2.length; i++) {
			n2.add(nums2[i]);
		}

		Collections.sort(n1);
		Collections.sort(n2);

		int n1p = 0;
		int n2p = 0;
		while (n1p < n1.size() && n2p < n2.size()) {
			if (n1.get(n1p) < n2.get(n2p)) {
				n1p++;
			} else if (n1.get(n1p) > n2.get(n2p)) {
				n2p++;
			} else {
				retL.add(n1.get(n1p));
				n1p++;
				n2p++;
			}
		}

		int[] ret = new int[retL.size()];

		Iterator<Integer> it = retL.iterator();
		int j = 0;
		while (it.hasNext()) {
			ret[j] = it.next();
			j++;
		}
		return ret;
	}

	// 350
	public static int[] intersectionIISort(int[] nums1, int[] nums2) {
		List<Integer> retL = new ArrayList<Integer>();
		List<Integer> n1 = new ArrayList<Integer>();
		List<Integer> n2 = new ArrayList<Integer>();
		for (int i = 0; i < nums1.length; i++) {
			n1.add(nums1[i]);
		}
		for (int i = 0; i < nums2.length; i++) {
			n2.add(nums2[i]);
		}

		Collections.sort(n1);
		Collections.sort(n2);

		int n1p = 0;
		int n2p = 0;
		while (n1p < n1.size() && n2p < n2.size()) {
			if (n1.get(n1p) < n2.get(n2p)) {
				n1p++;
			} else if (n1.get(n1p) > n2.get(n2p)) {
				n2p++;
			} else {
				retL.add(n1.get(n1p));
				n1p++;
				n2p++;
			}
		}

		int[] ret = new int[retL.size()];

		Iterator<Integer> it = retL.iterator();
		int j = 0;
		while (it.hasNext()) {
			ret[j] = it.next();
			j++;
		}
		return ret;
	}

	public char nextGreatestLetter(char[] letters, char target) {
		if (letters.length == 1) {
			return letters[0];
		}

		if (target < letters[0] || target >= letters[letters.length - 1]) {
			return letters[0];
		}

		int i = 0;
		for (; i < letters.length; i++) {
			if (letters[i] > target) {
				break;
			}
		}
		return letters[i];
	}

	public static int halfSearch(int[] nums, int target) {

		if (nums.length <= 0) {
			return -1;
		}
		int posB = 0;
		int posE = nums.length - 1;

		if (target > nums[posE] || target < nums[0]) {
			return -1;
		}

		while (posE >= posB) {
			int tpos = (posB + posE) / 2;

			if (nums[tpos] > target) {
				posE = tpos - 1;
			} else if (nums[tpos] < target) {
				posB = tpos + 1;
			} else {
				return tpos;
			}
		}

		return -1;
	}

	public static int halfSearchRe(int[] nums, int target) {
		if (nums.length <= 0) {
			return -1;
		}
		if (target > nums[nums.length - 1] || target < nums[0]) {
			return -1;
		}
		return halfSearchReD(nums, target, 0, nums.length - 1);
	}

	public static int halfSearchReD(int[] nums, int target, int begin, int end) {

		if (begin > end) {
			return -1;
		}
		if (begin == end) {
			if (nums[begin] == target) {
				return begin;
			} else {
				return -1;
			}
		}
		int tpos = (begin + end) / 2;

		if (nums[tpos] > target) {
			return halfSearchReD(nums, target, begin, tpos - 1);
		} else if (nums[tpos] < target) {
			return halfSearchReD(nums, target, tpos + 1, end);
		} else {
			return tpos;
		}
	}

	public static int findRotatedPos(int[] nums) {
		if (nums.length <= 0) {
			return -1;
		}
		int posB = 0;
		int posE = nums.length - 1;
		int pos = 0;
		if (nums[nums.length - 1] < nums[0]) {
			while (posE > posB) {
				if (posE == posB + 1) {
					pos = posE;
					break;
				}
				int tposB = (posB + posE) / 2;
				if (nums[tposB] > nums[posB]) {
					if (tposB < nums.length - 1 && nums[tposB + 1] < nums[posE]) {
						pos = tposB + 1;
						break;
					} else if (tposB >= nums.length - 1 && nums[tposB + 1 - nums.length] < nums[posE]) {
						pos = tposB + 1 - nums.length;
						break;
					} else {
						posB = tposB;
					}
				} else {
					if (tposB > 0 && nums[tposB - 1] > nums[posB]) {
						pos = tposB;
						break;
					} else if (tposB == 0 && nums[nums.length - 1] > nums[posB]) {
						pos = tposB;
						break;
					} else {
						posE = tposB;
					}
				}
			}
		}
		return pos;
	}

	// 33
	public static int searchRotatedArray(int[] nums, int target) {
		if (nums.length <= 0) {
			return -1;
		}
		int posB = 0;
		int posE = nums.length - 1;
		int pos = 0;
		if (nums[nums.length - 1] < nums[0]) {
			while (posE > posB) {
				if (posE == posB + 1) {
					pos = posE;
					break;
				}
				int tposB = (posB + posE) / 2;
				if (nums[tposB] > nums[posB]) {
					if (tposB < nums.length - 1 && nums[tposB + 1] < nums[posE]) {
						pos = tposB + 1;
						break;
					} else if (tposB >= nums.length - 1 && nums[tposB + 1 - nums.length] < nums[posE]) {
						pos = tposB + 1 - nums.length;
						break;
					} else {
						posB = tposB;
					}
				} else {
					if (tposB > 0 && nums[tposB - 1] > nums[posB]) {
						pos = tposB;
						break;
					} else if (tposB == 0 && nums[nums.length - 1] > nums[posB]) {
						pos = tposB;
						break;
					} else {
						posE = tposB;
					}
				}
			}
		}

		System.out.println("r : " + pos);

		posB = pos;
		posE = pos + nums.length - 1;

		if ((pos > 0 && target > nums[pos - 1]) || (pos == 0 && target > nums[nums.length - 1]) || target < nums[pos]) {
			return -1;
		}

		while (posE >= posB) {
			int tpos = (posB + posE) / 2;
			int rpos = tpos;

			if (rpos > nums.length - 1) {
				rpos -= nums.length;
			}

			if (nums[rpos] > target) {
				posE = tpos - 1;
			} else if (nums[rpos] < target) {
				posB = tpos + 1;
			} else {
				return rpos;
			}
		}

		return -1;
	}

	// 18
	public static List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> ll = new ArrayList<List<Integer>>();

		if (nums.length < 4) {
			return ll;
		}

		List<Integer> lnums = new ArrayList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			lnums.add(nums[i]);
		}

		Collections.sort(lnums);

		Set<String> dupCheck = new HashSet<String>();

		for (int h = 0; h < lnums.size() - 3; h++) {
			int t = lnums.size() - 1;

			while (t > h) {
				int l = h + 1;
				int r = t - 1;
				int v = lnums.get(h) + lnums.get(t);
				while (r > l) {
					int v2 = v + lnums.get(l) + lnums.get(r);

					if (v2 == target) {
						StringBuffer sb = new StringBuffer();
						sb.append(lnums.get(h));
						sb.append("_");
						sb.append(lnums.get(l));
						sb.append("_");
						sb.append(lnums.get(r));
						sb.append("_");
						sb.append(lnums.get(t));
						if (!dupCheck.contains(sb.toString())) {
							List<Integer> tl = new ArrayList<Integer>();
							tl.add(lnums.get(h));
							tl.add(lnums.get(l));
							tl.add(lnums.get(r));
							tl.add(lnums.get(t));
							dupCheck.add(sb.toString());
							ll.add(tl);
						}
						l++;
						r--;
					} else if (v2 > target) {
						r--;
					} else {
						l++;
					}
				}

				t--;
			}
		}

		return ll;
	}

	public static List<List<Integer>> fourSumSlow(int[] nums, int target) {
		List<List<Integer>> ll = new ArrayList<List<Integer>>();

		if (nums.length < 4) {
			return ll;
		}

		List<Integer> lnums = new ArrayList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			lnums.add(nums[i]);
		}

		Collections.sort(lnums);

		Set<String> dupCheck = new HashSet<String>();

		int cmt = 0, cmt1 = 0, cmt2 = 0, cmt3 = 0;
		for (int i = 0; i < lnums.size() - 3; i++) {
			cmt = lnums.get(i);
			if (cmt > target && lnums.get(i + 1) > 0) {
				break;
			}
			for (int j = i + 1; j < nums.length - 2; j++) {
				cmt1 = cmt + lnums.get(j);
				if (cmt1 > target && lnums.get(j + 1) > 0) {
					break;
				}
				for (int k = j + 1; k < nums.length - 1; k++) {
					cmt2 = cmt1 + lnums.get(k);
					if (cmt2 > target && lnums.get(k + 1) > 0) {
						break;
					}
					for (int l = k + 1; l < nums.length; l++) {
						cmt3 = cmt2 + lnums.get(l);
						if (cmt3 == target) {
							List<Integer> inds = new ArrayList<Integer>();
							StringBuffer sb = new StringBuffer();
							inds.add(lnums.get(i));
							inds.add(lnums.get(j));
							inds.add(lnums.get(k));
							inds.add(lnums.get(l));
							sb.append(lnums.get(i));
							sb.append("_");
							sb.append(lnums.get(j));
							sb.append("_");
							sb.append(lnums.get(k));
							sb.append("_");
							sb.append(lnums.get(l));
							if (!dupCheck.contains(sb.toString())) {
								dupCheck.add(sb.toString());
								ll.add(inds);
							}
						}
						if (cmt3 > target) {
							break;
						}

					}
				}
			}
		}

		return ll;
	}

	// 16
	public static int threeSumClosest(int[] nums, int target) {
		if (nums.length < 3) {
			return 0;
		}
		int minDis = Integer.MAX_VALUE;
		int mT = 0, cmT, dis;
		for (int i = 0; i < nums.length - 2; i++) {
			for (int j = i + 1; j < nums.length - 1; j++) {
				for (int k = j + 1; k < nums.length; k++) {
					cmT = nums[i] + nums[j] + nums[k];
					dis = Math.abs(cmT - target);
					if (dis < minDis) {
						mT = cmT;
						minDis = dis;
					}
				}
			}
		}
		return mT;
	}

	// 11
	public static int maxArea(int[] height) {
		if (height.length < 2) {
			return 0;
		}
		int maxA = 0;
		int curA = 0;
		for (int i = 0; i < height.length - 1; i++) {
			for (int j = 1; j < height.length; j++) {
				if (height[i] < height[j]) {
					curA = height[i] * (j - i);
				} else {
					curA = height[j] * (j - i);
				}

				if (curA > maxA) {
					maxA = curA;
				}
			}
		}

		return maxA;
	}

	// 303
	class NumArray {
		List<Integer> m_nums = new ArrayList<Integer>();

		public NumArray(int[] nums) {
			for (int i = 0; i < nums.length; i++) {
				m_nums.add(nums[i]);
			}
		}

		public int sumRange(int i, int j) {
			int sum = 0;
			for (int k = i; k <= j; k++) {
				sum += m_nums.get(k);
			}

			return sum;
		}
	}

	public int sumRange(int i, int j) {
		List<Integer> m_nums = new ArrayList<Integer>();

		int sum = 0;
		for (int k = i; k <= j; k++) {
			sum += m_nums.get(k);
		}

		return sum;
	}

	// 189
	public static void rotate(int[] nums, int k) {
		if (nums.length <= 1) {
			return;
		}
		int temp;
		int curPos;
		int k1 = k % nums.length;
		for (int j = 0; j < k1; j++) {
			int pos = 0;
			int elm = nums[pos];
			for (int i = 0; i < nums.length; i++) {
				curPos = (i + 1) % nums.length;
				temp = nums[curPos];
				nums[curPos] = elm;
				elm = temp;
			}
		}
	}

	public static void rotateReverse(int[] nums, int k) {
		if (nums.length <= 1) {
			return;
		}

		k = k % nums.length;

		int temp;
		int left = 0;
		int right = nums.length - 1;

		while (left < right) {
			temp = nums[left];
			nums[left] = nums[right];
			nums[right] = temp;
			left++;
			right--;
		}

		left = 0;
		right = k - 1;

		while (left < right) {
			temp = nums[left];
			nums[left] = nums[right];
			nums[right] = temp;
			left++;
			right--;
		}

		left = k;
		right = nums.length - 1;

		while (left < right) {
			temp = nums[left];
			nums[left] = nums[right];
			nums[right] = temp;
			left++;
			right--;
		}
	}

	public static void rotateSlow(int[] nums, int k) {
		if (nums.length <= 1) {
			return;
		}
		int temp;
		int curPos;
		int k1 = k % nums.length;
		for (int j = 0; j < k1; j++) {
			int pos = 0;
			int elm = nums[pos];
			for (int i = 0; i < nums.length; i++) {
				curPos = (i + 1) % nums.length;
				temp = nums[curPos];
				nums[curPos] = elm;
				elm = temp;
			}
		}
	}

	// 169
	public static int majorityElement(int[] nums) throws Exception {
		if (nums.length == 0) {
			throw new Exception("Array is empty, there isn't a majority element.");
		}

		Map<Integer, Integer> majorCnt = new HashMap<Integer, Integer>();

		int majorTh = nums.length / 2;

		for (int i = 0; i < nums.length; i++) {
			Integer cnt = majorCnt.get(nums[i]);
			if (cnt == null) {
				cnt = 1;
			} else {
				cnt += 1;
			}

			if (cnt > majorTh) {
				return nums[i];
			}
			majorCnt.put(nums[i], cnt);
		}
		throw new Exception("Array is empty, there isn't a majority element.");
		// return 0;
	}

	public static int majorityElementMoore(int[] nums) throws Exception {
		if (nums.length == 0) {
			throw new Exception("Array is empty, there isn't a majority element.");
		}

		int majority = nums[0];
		int count = 1;
		int threshold = nums.length / 2;

		for (int i = 1; i < nums.length; i++) {
			if (count == 0) {
				majority = nums[i];
				count = 1;
			} else {
				if (majority == nums[i]) {
					count++;
					if (count > threshold) {
						return majority;
					}
				} else {
					count--;
				}
			}
		}

		return majority;
	}

	// 26
	public static int removeDuplicates(int[] nums) {
		int len = nums.length;
		if (len <= 0) {
			return len;
		}
		int trueLen = len;
		int curVal = nums[0];
		int curPos = 0;
		for (int k = 1; k < len; k++) {
			if (nums[k] == curVal) {
				trueLen--;
			} else {
				curPos++;
				nums[curPos] = nums[k];
				curVal = nums[k];
			}
		}
		return trueLen;
	}

	// 35
	public int searchInsert(int[] nums, int target) {
		int len = nums.length;
		if (len <= 0) {
			return 0;
		}

		int i = 0;

		for (; i < len; i++) {
			if (target <= nums[i]) {
				return i;
			}
		}

		return i;
	}

	// 53
	public static int maxSubArray(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int curVal = nums[0];
		int tmpVal = 0;
		if (curVal > max) {
			max = curVal;
		}
		for (int i = 1; i < nums.length; i++) {
			if (curVal < 0) {
				curVal = 0;
			}
			tmpVal = curVal + nums[i];
			if (tmpVal > max) {
				max = tmpVal;
			}
			if (tmpVal > 0) {
				curVal = tmpVal;
			} else {
				curVal = 0;
			}
		}

		return max;
	}

	public int[] twoSum(int[] nums, int target) {
		int[] ret = new int[2];
		if (nums.length < 2) {
			return ret;
		}
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					ret[0] = i;
					ret[1] = j;
					return ret;
				}
			}
		}
		return ret;
	}

	// 167
	public int[] twoSumII(int[] numbers, int target) {
		int[] ret = new int[2];
		if (numbers.length < 2) {
			return ret;
		}
		for (int i = 0; i < numbers.length - 1; i++) {
			for (int j = i + 1; j < numbers.length; j++) {
				if (numbers[i] + numbers[j] == target) {
					ret[0] = i + 1;
					ret[1] = j + 1;
					return ret;
				}
			}
		}
		return ret;
	}

	public static int removeDup(int[] nums) {

		int len = nums.length;
		if (len <= 1) {
			return len;
		}
		for (int i = 1; i < len; i++) {
			int j = i - 1;
			int temp = nums[i];
			for (; j >= 0 && nums[j] < temp; j--) {
				nums[j + 1] = nums[j];
			}

			nums[j + 1] = temp;
		}

		int trueLen = len;
		int curVal = nums[0];
		int curPos = 0;
		for (int k = 1; k < len; k++) {
			if (nums[k] == curVal) {
				trueLen--;
			} else {
				curPos++;
				nums[curPos] = nums[k];
				curVal = nums[k];
			}
		}
		System.out.println("curLen : " + trueLen);

		for (int l = 0; l < trueLen; l++) {
			System.out.print(nums[l] + " ");
		}
		System.out.println("");
		return trueLen;
	}

	public static List<List<Integer>> threeSumSlow(int[] nums) {
		List<List<Integer>> ret = new ArrayList<List<Integer>>();

		int len = nums.length;
		if (len <= 1) {
			return ret;
		}
		for (int i = 1; i < len; i++) {
			int j = i - 1;
			int temp = nums[i];
			for (; j >= 0 && nums[j] < temp; j--) {
				nums[j + 1] = nums[j];
			}

			nums[j + 1] = temp;
		}

		// int trueLen = len;
		// int curVal = nums[0];
		// int curPos = 0;
		// for (int k = 1; k < len; k++) {
		// if (nums[k] == curVal) {
		// trueLen--;
		// } else {
		// curPos++;
		// nums[curPos] = nums[k];
		// curVal = nums[k];
		// }
		// }
		// len = trueLen;
		for (int i = 0; i < len - 2; i++) {
			for (int j = i + 1; j < len - 1; j++) {
				int sum2 = nums[i] + nums[j];
				for (int k = j + 1; k < len; k++) {
					if (sum2 + nums[k] == 0) {
						List<Integer> innerList = new ArrayList<Integer>();
						innerList.add(nums[i]);
						innerList.add(nums[j]);
						innerList.add(nums[k]);

						Collections.sort(innerList);
						boolean dup = false;
						for (int t = 0; t < ret.size(); t++) {
							List<Integer> curInnerList = ret.get(t);
							if (curInnerList.get(0) == innerList.get(0) && curInnerList.get(1) == innerList.get(1)
									&& curInnerList.get(2) == innerList.get(2)) {
								dup = true;
								break;
							}
						}
						if (!dup) {
							ret.add(innerList);
						}
					}
				}
			}
		}

		return ret;
	}

	public static List<List<Integer>> threeSumSlow2(int[] nums) {
		List<List<Integer>> ret = new ArrayList<List<Integer>>();

		int len = removeDup(nums);

		int zeroPos = -1;
		for (int i = 0; i < len - 2; i++) {
			for (int j = i + 1; j < len - 1; j++) {
				int sum2 = nums[i] + nums[j];
				if (sum2 > 0) {
					break;
				}
				for (int k = zeroPos > j + 1 ? zeroPos : j + 1; k < len; k++) {
					if (nums[k] < 0) {
						continue;
					}
					if (zeroPos < 0) {
						zeroPos = k;
					}
					if (sum2 + nums[k] == 0) {
						List<Integer> innerList = new ArrayList<Integer>();
						innerList.add(nums[i]);
						innerList.add(nums[j]);
						innerList.add(nums[k]);

						Collections.sort(innerList);
						boolean dup = false;
						for (int t = 0; t < ret.size(); t++) {
							List<Integer> curInnerList = ret.get(t);
							if (curInnerList.get(0) == innerList.get(0) && curInnerList.get(1) == innerList.get(1)
									&& curInnerList.get(2) == innerList.get(2)) {
								dup = true;
								break;
							}
						}
						if (!dup) {
							ret.add(innerList);
						}
					}
				}
			}
		}

		return ret;
	}

	public static int removeElement(int[] nums, int val) {
		if (nums == null) {
			return 0;
		}
		int len = nums.length;
		if (len < 1) {
			return len;
		}
		int trueLen = len;

		int curPos = 0;
		for (int i = 0; i < len; i++) {
			if (nums[i] == val) {
				trueLen--;
			} else {
				nums[curPos] = nums[i];
				curPos++;
			}
		}

		System.out.println("curLen : " + trueLen);

		for (int l = 0; l < trueLen; l++) {
			System.out.print(nums[l] + " ");
		}
		System.out.println("");
		return trueLen;
	}

	// 88
	public static void merge(int[] nums1, int m, int[] nums2, int n) {
		int l = m + n;

		for (int i = l; i > 0; i--) {
			if (m <= 0) {
				nums1[i - 1] = nums2[n - 1];
				n--;
			} else if (n <= 0) {
				nums1[i - 1] = nums1[m - 1];
				m--;
			} else if (nums1[m - 1] >= nums2[n - 1]) {
				nums1[i - 1] = nums1[m - 1];
				m--;
			} else {
				nums1[i - 1] = nums2[n - 1];
				n--;
			}
		}
	}

	// 118
	public static List<List<Integer>> generateYHAngle(int numRows) {
		List<List<Integer>> yhaList = new ArrayList<List<Integer>>();
		if (numRows <= 0) {
			return yhaList;
		}
		List<Integer> tmpList1 = new ArrayList<Integer>();
		tmpList1.add(1);
		yhaList.add(tmpList1);
		if (numRows == 1) {
			return yhaList;
		}

		List<Integer> tmpList2 = new ArrayList<Integer>();
		tmpList2.add(1);
		tmpList2.add(1);
		yhaList.add(tmpList2);
		if (numRows == 2) {
			return yhaList;
		}

		for (int i = 3; i <= numRows; i++) {
			List<Integer> tmpList = new ArrayList<Integer>();
			List<Integer> perList = yhaList.get(i - 2);
			tmpList.add(1);
			for (int j = 1; j < i - 1; j++) {
				int val = perList.get(j - 1) + perList.get(j);
				tmpList.add(val);
			}
			tmpList.add(1);
			yhaList.add(tmpList);
		}

		for (int i = 0; i < yhaList.size(); i++) {
			List<Integer> tmp = yhaList.get(i);
			for (int j = 0; j < tmp.size(); j++) {
				System.out.print(tmp.get(j) + " ");
			}
			System.out.println(" ");
		}

		return yhaList;
	}

	// 119
	public static List<Integer> generateYHAngleK(int rowIndex) {
		List<Integer> tmpList = new ArrayList<Integer>();
		if (rowIndex < 0) {
			return tmpList;
		} else if (rowIndex == 0) {
			tmpList.add(1);
			return tmpList;
		} else if (rowIndex == 1) {
			tmpList.add(1);
			tmpList.add(1);
			return tmpList;
		}
		tmpList.add(1);
		tmpList.add(1);
		List<Integer> perList;
		for (int i = 3; i <= rowIndex + 1; i++) {
			perList = tmpList;
			tmpList = new ArrayList<Integer>();
			tmpList.add(1);
			for (int j = 1; j < i - 1; j++) {
				int val = perList.get(j - 1) + perList.get(j);
				tmpList.add(val);
			}
			tmpList.add(1);
		}

		for (int j = 0; j < tmpList.size(); j++) {
			System.out.print(tmpList.get(j) + " ");
		}
		System.out.println(" ");
		for (int j = 0; j < tmpList.size(); j++) {
			System.out.print(tmpList.get(j) + " ");
		}
		System.out.println(" ");

		return tmpList;
	}

	// 121
	public static int maxProfit(int[] prices) {
		if (prices.length <= 1) {
			return 0;
		}
		int minPrice = prices[0];
		int maxProfit = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] < minPrice) {
				minPrice = prices[i];
			} else if (prices[i] > minPrice) {
				int tmpMax = prices[i] - minPrice;
				if (tmpMax > maxProfit) {
					maxProfit = tmpMax;
				}
			}
		}
		return maxProfit;
	}

	// 122
	public static int maxProfitII(int[] prices) {
		if (prices.length <= 1) {
			return 0;
		}
		int minPrice = prices[0];
		int maxPrice = minPrice;
		int maxProfit = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] > maxPrice) {
				maxPrice = prices[i];
			} else if (prices[i] < maxPrice) {
				if (prices[i] < minPrice) {
					if (maxPrice > minPrice) {
						maxProfit += maxPrice - minPrice;
					}
					minPrice = prices[i];
					maxPrice = minPrice;
				} else if (prices[i] >= minPrice) {
					maxProfit += maxPrice - minPrice;
					minPrice = prices[i];
					maxPrice = minPrice;
				}
			}
		}
		maxProfit += maxPrice - minPrice;
		return maxProfit;
	}

	public static boolean containsDuplicate(int[] nums) {
		if (nums.length == 0) {
			return false;
		}
		Set<Integer> dupChecker = new HashSet<Integer>();

		dupChecker.add(nums[0]);
		for (int i = 1; i < nums.length; i++) {
			if (dupChecker.contains(nums[i])) {
				return true;
			} else {
				dupChecker.add(nums[i]);
			}
		}
		return false;
	}

	public static boolean containsNearbyDuplicate(int[] nums, int k) {
		if (nums.length == 0) {
			return false;
		}

		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j <= i + k && j < nums.length; j++) {
				if (nums[i] == nums[j]) {
					return true;
				}
			}
		}

		return false;
	}

	public static int missingNumber(int[] nums) {
		if (nums.length < 1) {
			return 0;
		}

		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}

		int s_sum = (nums.length + 1) * nums.length / 2;

		return s_sum - sum;
	}

	public static void moveZeroes(int[] nums) {
		int zeroNum = 0;
		int zPos = -1;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				zeroNum++;
				if (zPos == -1) {
					zPos = i;
				}
			} else {
				if (zPos >= 0) {
					nums[zPos] = nums[i];
					zPos++;
				}
			}
		}

		for (int i = zeroNum; i > 0; i--) {
			nums[nums.length - i] = 0;
		}

		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + ", ");

		}
		System.out.println(" ");
	}
}
