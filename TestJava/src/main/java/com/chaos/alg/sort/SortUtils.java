package com.chaos.alg.sort;

public class SortUtils {
	public static void printArray(int[] input) {
		StringBuffer strb = new StringBuffer();
		boolean isSorted = true;
		for (int i = 0; i < input.length; i++) {
			if (i < input.length - 1) {
				if (input[i] < input[i + 1]) {
					isSorted = false;
				}
			}
			strb.append(" " + input[i]);
		}
		if (isSorted) {
			System.out.println("Sorted!");
		} else {

			System.out.println("Not sorted!");
		}
		System.out.println(strb.toString());
	}

	public static void swap(int a, int b) {
		int temp = a;
		a = b;
		b = temp;
	}

	public static void bubbleSort(int[] inArray) {
		int len = inArray.length;

		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len - i - 1; j++) {
				if (inArray[j] < inArray[j + 1]) {
					int temp = inArray[j + 1];
					inArray[j + 1] = inArray[j];
					inArray[j] = temp;
				}
			}
		}
	}

	public static void selectSort(int[] inArray) {
		int len = inArray.length;

		for (int i = 0; i < len; i++) {
			int maxpos = i;
			for (int j = i + 1; j < len; j++) {
				if (inArray[j] > inArray[maxpos]) {
					maxpos = j;
				}
			}
			int temp = inArray[i];
			inArray[i] = inArray[maxpos];
			inArray[maxpos] = temp;
		}
	}

	public static void insertSort(int[] inArray) {
		int len = inArray.length;
		if (len < 2) {
			return;
		}
		for (int i = 1; i < len; i++) {
			int j;
			int temp = inArray[i];
			for (j = i - 1; j >= 0; j--) {
				if (temp > inArray[j]) {
					inArray[j + 1] = inArray[j];
				} else {
					break;
				}
			}
			inArray[j + 1] = temp;
		}
	}

	public static void shellSort(int[] inArray) {

	}

	public static void quickSort(int[] inArray) {

	}

	public static void heapSort(int[] inArray) {

	}
}
