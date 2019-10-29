package com.chaos.alg.exercises;

import com.chaos.TestJava.ListNode;

public class Exercise1 {
	public static boolean isStrDupChar(String str) {

		char[] strarr = str.toCharArray();

		int pos = 1;
		for (char ch : strarr) {
			for (int i = pos; i < strarr.length; i++) {
				if (ch == strarr[i]) {
					return true;
				}
			}
			pos++;
		}

		return false;
	}

	public static boolean isDupStr(String str1, String str2) {

		if (str1.length() != str2.length()) {
			return false;
		}

		char[] chrarr1 = str1.toCharArray();
		char[] chrarr2 = str2.toCharArray();

		boolean finded = false;
		for (int i = 0; i < chrarr1.length; i++) {
			finded = false;
			for (int j = i; j < chrarr2.length; j++) {
				if (chrarr1[i] == chrarr2[j]) {
					char c = chrarr2[i];
					chrarr2[i] = chrarr2[j];
					chrarr2[j] = c;
					finded = true;
				}
			}
			if (!finded) {
				return false;
			}
		}

		return true;
	}

	public static String replaceSpace(String str) {
		char[] chrarr = str.toCharArray();

		char[] newchrarr = new char[chrarr.length * 3];

		int pos = 0;
		for (int i = 0; i < chrarr.length; i++) {
			if (chrarr[i] == ' ') {
				newchrarr[pos] = '%';
				newchrarr[pos + 1] = '2';
				newchrarr[pos + 2] = '0';
				pos += 3;
			} else {
				newchrarr[pos] = chrarr[i];
				pos += 1;
			}
		}

		return new String(newchrarr, 0, pos);
	}

	public static String compressStr(String str) {
		char[] ar = str.toCharArray();
		StringBuffer newar = new StringBuffer();

		if (ar.length > 1) {
			char tmp = ar[0];
			int cnt = 1;
			for (int i = 1; i < ar.length; i++) {
				if (ar[i] == tmp) {
					cnt++;
				} else {
					newar.append(tmp);
					newar.append(cnt);
					tmp = ar[i];
					cnt = 1;
				}
			}
			newar.append(tmp);
			newar.append(cnt);

			if (ar.length > newar.length()) {
				return newar.toString();
			} else {
				return str;
			}
		}

		return str;
	}

	public char[] reserveStr(char[] input) {
		char[] revStr = new char[input.length];

		return revStr;
	}

	public static int reverseInt(int x) {

		long kx;
		boolean neg = false;
		if (x < 0) {
			neg = true;
			kx = 0L - x;
		} else {
			kx = x;
		}
		String k = "" + kx;

		char[] ar = k.toCharArray();

		for (int i = 0; i < ar.length / 2; i++) {
			char tmp = ar[i];
			ar[i] = ar[ar.length - i - 1];
			ar[ar.length - i - 1] = tmp;
		}

		String kk = new String(ar);

		long ret = Long.parseLong(kk);

		if (neg) {
			ret = -ret;
			if (ret < Integer.MIN_VALUE) {
				return 0;
			}
		} else {
			if (ret > Integer.MAX_VALUE) {
				return 0;
			}
		}

		return (int) ret;
	}

	public static void sortColors(int[] nums) {
		int tmp = -1;
		int j = nums.length - 1;
		int k = 0;

		for (int i = 0; i <= j;) {
			if (nums[i] == 0) {
				if (k != i) {
					tmp = nums[k];
					nums[k] = nums[i];
					nums[i] = tmp;
				} else {
					i++;
				}
				k++;
			} else if (nums[i] == 2) {
				tmp = nums[j];
				nums[j] = nums[i];
				nums[i] = tmp;
				j--;
			} else if (nums[i] == 1) {
				i++;
			}
		}
	}

	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		double ret = 0;

		int[] nums3 = new int[nums1.length + nums2.length];

		int i = 0, j = 0;

		while (i < nums1.length && j < nums2.length) {
			if (nums1[i] < nums2[j]) {
				nums3[i + j] = nums1[i];
				i++;
			} else {
				nums3[i + j] = nums2[j];
				j++;
			}
		}

		if (i == nums1.length) {
			for (; j < nums2.length; j++) {
				nums3[i + j] = nums2[j];
			}
		}

		if (j == nums2.length) {
			for (; i < nums1.length; i++) {
				nums3[i + j] = nums1[i];
			}
		}

		System.out.println(i + " " + j);

		for (int k : nums3) {
			System.out.print(k + " ");
		}
		System.out.println("###\n");
		if (i + j < 2) {
			ret = nums3[0];
		} else if ((i + j) % 2 == 0) {
			ret = (nums3[(i + j) / 2] + nums3[(i + j) / 2 - 1]);
			ret /= 2;
		} else {
			ret = nums3[(i + j) / 2];
		}

		System.out.println(ret);
		return ret;
	}

	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}
		ListNode l3 = null, ltmp = null;

		while (l1 != null && l2 != null) {
			if (l1.val > l2.val) {
				if (ltmp == null) {
					ltmp = l2;
				} else {
					ltmp.next = l2;
				}
				l2 = l2.next;

			} else {
				if (ltmp == null) {
					ltmp = l1;
				} else {
					ltmp.next = l1;
				}
				l1 = l1.next;
			}
			if (l3 == null) {
				l3 = ltmp;
			} else {
				ltmp = ltmp.next;
			}
		}

		if (l1 != null) {
			ltmp.next = l1;
		}

		if (l2 != null) {
			ltmp.next = l2;
		}

		return l3;
	}

}
