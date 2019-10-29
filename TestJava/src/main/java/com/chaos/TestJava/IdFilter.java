package com.chaos.TestJava;

import java.util.Random;

// NOT THREAD SAFE
public class IdFilter {
	private int[][] ids;
	private int[] pn;
	private int[] sid;

	IdFilter(int idLen) {
		ids = new int[idLen][10];
		pn = new int[2];
		sid = new int[idLen];
		for (int i = 0; i < idLen; i++) {
			for (int j = 0; j < 10; j++) {
				ids[i][j] = 0;
			}
		}
		pn[0] = 0;
		pn[1] = 0;
	}

	private void getIds(long lv) {
		int kk;
		int j = 0;

		if (lv < 0) {
			pn[0]++;
		} else {
			pn[1]++;
		}

		while (lv > 0) {
			kk = (int) (lv % 10);
			lv = lv / 10;
			j++;
			sid[j] = kk;
		}
	}

	public boolean addId(long id) {
		getIds(id);
		for (int i = 0; i < ids.length; i++) {
			ids[i][sid[i]]++;
		}
		return false;
	}

	public boolean reduceId(long id) {
		return false;
	}

	public static void main(String argv[]) {
		IdFilter idf = new IdFilter(10);
	}

	public static void speedTest() {
		String str = null;
		Random k = new Random();
		long[] ids = new long[20];

		long start = System.currentTimeMillis();

		for (int i = 0; i <= 10000000; i++) {

			str = Long.toString(k.nextLong());

			for (int j = 0; j < str.length(); j++) {
				ids[j] = str.charAt(j) - '0';
			}

		}

		System.out.println(System.currentTimeMillis() - start);
		start = System.currentTimeMillis();

		for (int i = 0; i <= 10000000; i++) {
			long num = k.nextLong();
			long kk;
			int j = 0;
			while (num > 0) {
				kk = num % 10;
				num = num / 10;
				j++;
				ids[j] = kk;
			}

		}
		System.out.println(System.currentTimeMillis() - start);
	}
}
